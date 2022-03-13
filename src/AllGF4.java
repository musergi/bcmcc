public class AllGF4 {
    public static void main(String[] args) {
        for (int a = 0; a < 4; a++) {
            for (int b = 0; b < 4; b++) {
                for (int c = 0; c < 4; c++) {
                    Matrix m = new Matrix(a, b, c);
                    if (m.hasInverse()) {
                        System.out.println(m);
                    }
                }
            }
        }
    }
    
    public static class Matrix {
        int[] results;
        
        public Matrix(int a, int b, int c) {
            results = new int[4 * 4];
            setZeros();
            setUnit();
            results[2 * 4 + 2] = a;
            results[2 * 4 + 3] = b;
            results[3 * 4 + 2] = b;
            results[3 * 4 + 3] = c;
        }
        
        private void setZeros() {
            for (int row = 0; row < 4; row++) {
                results[row * 4] = 0;
            }
            for (int col = 1; col < 4; col++) {
                results[col] = 0;
            }
        }
        
        private void setUnit() {
            for (int i = 1; i < 4; i++) {
                results[4 + i] = i;
                results[1 + 4 * i] = i;
            }
        }
        
        public boolean hasInverse() {
            for (int row = 1; row < 4; row++) {
                boolean hasInverse = false;
                for (int col = 1; col < 4; col++) {
                    if (results[row * 4 + col] == 1) {
                        hasInverse = true;
                        break;
                    }
                }
                if (!hasInverse) return false;
            }
            return true;
        }
        
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    sb.append(' ');
                    sb.append(results[row * 4 + col]);
                }
                sb.append('\n');
            }
            return sb.toString();
        }
    }
}
