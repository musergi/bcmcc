package dev.musergi.bcmcc;

import java.util.List;
import java.util.ArrayList;

public class GreatestCommonDivisor {
    private final List<Integer> r = new ArrayList<>();
    private final List<Integer> t = new ArrayList<>();
    private final List<Integer> s = new ArrayList<>();
    private final List<Integer> q = new ArrayList<>();
    private final List<Integer> i = new ArrayList<>();
    
    public GreatestCommonDivisor(int a, int b) {
        r.add(a);
        t.add(0);
        s.add(1);
        r.add(b);
        t.add(1);
        s.add(0);
        q.add(0);
        q.add(0);
        i.add(0);
        i.add(1);
        solve();
    }
    
    private void solve() {
        while(!isSolved()) {
            step();
        }
    }
    
    private boolean isSolved() {
        return r.get(r.size() - 1) == 0;
    }
    
    private void step() {
        int a = r.get(r.size() - 2);
        int b = r.get(r.size() - 1);
        int q = a / b;
        int sStep = s.get(s.size() - 2) - s.get(s.size() - 1) * q;
        int tStep = t.get(t.size() - 2) - t.get(t.size() - 1) * q;
        r.add(a % b);
        s.add(sStep);
        t.add(tStep);
        this.q.add(q);
        i.add(i.size());
    }
    
    public int get() {
        return r.get(r.size() - 2);
    }
    
    public String toTable() {
        TableBuilder tb = new TableBuilder();
        tb.addColumn("r", r);
        tb.addColumn("s", s);
        tb.addColumn("t", t);
        tb.addColumn("q", q);
        tb.addColumn("i", i);
        return tb.toString();
    }
}
