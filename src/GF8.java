import jmoreira.pfc.galois.ExtendedGaloisField;
import jmoreira.pfc.galois.GFException;
import jmoreira.pfc.galois.GFPolynomial;
import jmoreira.pfc.galois.GaloisField;
import jmoreira.pfc.galois.RootGaloisField;

public class GF8 {
    public static void main(String[] args) {
        try {
            GaloisField gf2 = new RootGaloisField(2);
            GFPolynomial moduloPolynomial = getGFPoly(gf2, new int[]{1, 1, 0, 1});
            ExtendedGaloisField gf8 = new ExtendedGaloisField(moduloPolynomial);
            
            System.out.println("Elements of GF(8):");
            for (GaloisField.Element element: gf8.element) {
                System.out.print(element);
                System.out.print(" -> ");
                System.out.println(gf8.toPolynomial(element));
            }
            System.out.println();
            
            System.out.println("Periodic multiplication with x:");
            GaloisField.Element initial = gf8.element[1];
            GaloisField.Element accumulator = initial;
            int period = -1;
            for (int i = 0; i < 20; i++) {
                System.out.print("x^");
                System.out.print(i);
                System.out.print(" -> ");
                System.out.println(gf8.toPolynomial(accumulator));
                if (accumulator == initial && i != 0 && period == -1) {
                    period = i;
                    break;
                }
                accumulator = accumulator.mul(gf8.element[2]);
            }
            System.out.print("Found period: ");
            System.out.println(period);
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
