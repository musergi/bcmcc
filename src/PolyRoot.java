import java.util.ArrayList;
import java.util.List;
import jmoreira.pfc.galois.ExtendedGaloisField;
import jmoreira.pfc.galois.GFException;
import jmoreira.pfc.galois.GFPolynomial;
import jmoreira.pfc.galois.GaloisField;
import jmoreira.pfc.galois.RootGaloisField;

public class PolyRoot {
    public static void main(String[] args) {
        try {
            GaloisField gf = new RootGaloisField(3);
            GFPolynomial poly = getGFPoly(gf, new int[]{1, 2, 0, 1});
            ExtendedGaloisField gf27 = new ExtendedGaloisField(poly);
            
            GaloisField.Element[] validCoefficients = new GaloisField.Element[3];
            validCoefficients[0] = gf27.element[0];
            validCoefficients[1] = gf27.element[1];
            validCoefficients[2] = gf27.element[1].add(gf27.element[1]);
            
            for (GaloisField.Element testedElement: gf27.element) {
                // Init coefficients
                List<GaloisField.Element> coefficients = new ArrayList<>();
                coefficients.add(gf27.element[1]);
                
                while(true) {
                    // Test
                    GaloisField.Element[] testedCoefficients = new GaloisField.Element[coefficients.size()];
                    coefficients.toArray(testedCoefficients);
                    GFPolynomial testedPoly = new GFPolynomial(testedCoefficients, gf27);
                    if (testedPoly.eval(testedElement).equals(gf27.element[0])) {
                        break;
                    }
                    // Update
                    int i = 0;
                    while (true) {
                        // Increment
                        coefficients.set(i, coefficients.get(i).add(gf27.element[1]));
                        
                        // If looping back
                        if (coefficients.get(i).equals(gf27.element[0])) {
                            i++;
                            if (i == coefficients.size()) {
                                // If the next element does not exists
                                // Add it and exit
                                coefficients.add(gf27.element[1]);
                                break;
                            }
                        } else {
                            // If not we are done
                            break;
                        }
                    }
                    
                }
                
                // Create final polynomial
                GaloisField.Element[] finalCoefficients = new GaloisField.Element[coefficients.size()];
                coefficients.toArray(finalCoefficients);
                GFPolynomial finalPoly = new GFPolynomial(finalCoefficients, gf27);
                
                // Show resulting poly
                System.out.print("Element: ");
                System.out.print(testedElement);
                System.out.print(" or ");
                System.out.print(gf27.toPolynomial(testedElement));
                System.out.print(" -> Polynomial: ");
                System.out.println(finalPoly);
            }
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
