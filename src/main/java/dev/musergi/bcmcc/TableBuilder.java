package dev.musergi.bcmcc;

import java.util.ArrayList;
import java.util.List;

public class TableBuilder {
    private final List<Column> columns = new ArrayList<>();
    
    public void addColumn(String name, List<Integer> column) {
        columns.add(new Column(name, column));
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getHorizontalLine());
        sb.append(getHeader());
        sb.append(getHorizontalLine());
        for (int i = 0; i < columns.get(0).size(); i++) {
            sb.append(getDataLine(i));
        }
        sb.append(getHorizontalLine());
        return sb.toString();
    }
    
    private String getHorizontalLine() {
        StringBuilder sb = new StringBuilder();
        for (Column column: columns) {
            sb.append('+');
            sb.append(column.getLine());
        }
        sb.append("+\n");
        return sb.toString();
    }
    
    private String getHeader() {
        StringBuilder sb = new StringBuilder();
        for (Column column: columns) {
            sb.append('|');
            sb.append(column.getHeader());
        }
        sb.append("|\n");
        return sb.toString();
    }
    
    private String getDataLine(int index) {
        StringBuilder sb = new StringBuilder();
        for (Column column: columns) {
            sb.append('|');
            sb.append(column.get(index));
        }
        sb.append("|\n");
        return sb.toString();
    }

    
    private class Column {
        private final String name;
        private final List<Integer> data;
        private int width;
        private String format;
        
        public Column(String name, List<Integer> data) {
            this.name = name;
            this.data = data;
            calculateColumnWidth();
            bulidFormat();
        }
        
        private void calculateColumnWidth() {
            int maxWidth = name.length();
            for (int value: data) {
                int width = getDigits(value);
                if (value < 0) {
                    width++;
                }
                if (width > maxWidth) {
                    maxWidth = width;
                }
            }
            this.width = maxWidth + 1;
        }
        
        private int getDigits(int value) {
            if (value == 0) {
                return 1;
            }
            value = Math.abs(value);
            double log10 = Math.log10(value);
            return (int) Math.ceil(log10);
        }

        private void bulidFormat() {
            StringBuilder sb = new StringBuilder();
            sb.append("%");
            sb.append(width);
            sb.append("d");
            format = sb.toString();
        }
        
        public String getHeader() {
            return center(name, width, ' ');
        }
        
        public String getLine() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < width; i++) {
                sb.append('-');
            }
            return sb.toString();
        }
        
        public int size() {
            return data.size();
        }
        
        public String get(int index) {
            return String.format(format, data.get(index));
        }
        
        private String center(String s, int size, char pad) {
            if (s == null || size <= s.length())
                return s;

            StringBuilder sb = new StringBuilder(size);
            for (int i = 0; i < (size - s.length()) / 2; i++) {
                sb.append(pad);
            }
            sb.append(s);
            while (sb.length() < size) {
                sb.append(pad);
            }
            return sb.toString();
        }
    }
}
