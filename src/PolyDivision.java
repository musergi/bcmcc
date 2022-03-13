import jmoreira.pfc.galois.GFException;
import jmoreira.pfc.galois.GFPolynomial;
import jmoreira.pfc.galois.GaloisField;
import jmoreira.pfc.galois.RootGaloisField;

public class PolyDivision {
    public static void main(String[] args) {
        try {
            GaloisField gf = new RootGaloisField(13);
            GFPolynomial a = getGFPoly(gf, new int[] {8, 2, 8, 10, 10, 0, 1, 0, 1});
            GFPolynomial b = getGFPoly(gf, new int[] {8, 4, 9, 0, 0, 5, 3});
            GFPolynomial q = a.div(b);
            GFPolynomial r = a.mod(b);
            System.out.println(q);
            System.out.println(r);
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
