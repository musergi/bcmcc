import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import jmoreira.pfc.galois.ExtendedGaloisField;
import jmoreira.pfc.galois.GFException;
import jmoreira.pfc.galois.GaloisField;

public class OrderP3M5 {
    public static void main(String[] args) {
        try {
            ExtendedGaloisField gf = new ExtendedGaloisField(3, 4);
            HashMap<Integer, Integer> counts = new HashMap<>();
            HashMap<Integer, List<GaloisField.Element>> groups = new HashMap<>();
            for (int i = 2; i < gf.cardinality(); i++) {
                System.out.print(gf.element[i]);
                System.out.print(": ");
                int order = getOrder(gf.element[i]);
                System.out.println(order);
                if (counts.containsKey(order)) {
                    counts.put(order, counts.get(order) + 1);
                    groups.get(order).add(gf.element[i]);
                } else {
                    counts.put(order, 1);
                    List<GaloisField.Element> newGroup = new ArrayList<>();
                    newGroup.add(gf.element[i]);
                    groups.put(order, newGroup);
                }
            }
            System.out.println(counts);
            System.out.println(groups);
        } catch (GFException e) {
            System.out.println(e);
        }
    }
    
    public static int getOrder(GaloisField.Element e) throws GFException {
        int order = 1;
        GaloisField.Element acc = e;
        while (!acc.isOneElement()) {
            acc = acc.mul(e);
            order++;
        }
        return order;
    }
}
