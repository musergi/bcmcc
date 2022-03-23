import java.util.ArrayList;
import java.util.List;
import jmoreira.pfc.galois.ExtendedGaloisField;
import jmoreira.pfc.galois.GFException;
import jmoreira.pfc.galois.GFPolynomial;
import jmoreira.pfc.galois.GaloisField;
import jmoreira.pfc.galois.RootGaloisField;

public class FiniteFieldSize {
    public static void main(String[] args) {
        try {
            GaloisField gf = new RootGaloisField(3);
            GFPolynomial poly = getGFPoly(gf, new int[]{1, 2, 0, 1});
            ExtendedGaloisField gf27 = new ExtendedGaloisField(poly);
            
            for (GaloisField.Element e: gf27.element) {
                System.out.print(e);
                System.out.print(" -> ");
                System.out.println(gf27.toPolynomial(e));
            }
            
            List<GaloisField.Element> u = new ArrayList<>();
            u.add(gf27.element[0]); 
            GaloisField.Element newElement;
            while ((newElement = gf27.add(u.get(u.size() - 1), gf27.element[1])) != u.get(0)) {
                u.add(newElement);
            }
            System.out.print("u: ");
            System.out.println(u);
            
            List<GaloisField.Element> combinations = new ArrayList<>();
            
            // Add all u_i w_1 to set
            GaloisField.Element w1 = null;
            for (GaloisField.Element e: gf27.element) {
                if (!u.contains(e)) {
                    w1 = e;
                    break;
                }
            }
            for (GaloisField.Element e: u) {
                combinations.add(gf27.mul(e, w1));
            }
            System.out.println(combinations);
            
            // Add all u_i w_2 to set
            GaloisField.Element w2 = null;
            for (GaloisField.Element e: gf27.element) {
                if (!combinations.contains(e)) {
                    w2 = e;
                    break;
                }
            }
            combinations.clear();
            for (GaloisField.Element e1: u) {
                for (GaloisField.Element e2: u) {
                    combinations.add(
                            gf27.add(
                                    gf27.mul(w1, e1),
                                    gf27.mul(w2, e2)
                            )
                    );
                }
            }
            System.out.println(combinations);
            
            
            GaloisField.Element w3 = null;
            for (GaloisField.Element e: gf27.element) {
                if (!combinations.contains(e)) {
                    w3 = e;
                    break;
                }
            }
            combinations.clear();
            for (GaloisField.Element e1: u) {
                for (GaloisField.Element e2: u) {
                    for (GaloisField.Element e3: u) {
                        combinations.add(
                                gf27.add(
                                    gf27.add(
                                            gf27.mul(w1, e1),
                                            gf27.mul(w2, e2)
                                    ),
                                    gf27.mul(w3, e3)
                                )
                        );
                    }
                }
            }
            System.out.println(combinations);
        } catch (GFException ex) {
            System.err.println(ex);
        }
        
    }
    
    public static GFPolynomial getGFPoly(GaloisField gf, int[] coefficients) throws GFException {
        GaloisField.Element[] polyElements = new GaloisField.Element[coefficients.length];
        for (int i = 0; i < coefficients.length; i++) {
            polyElements[i] = gf.element[coefficients[i]];
        }
        return new GFPolynomial(polyElements, gf);
    }
}
