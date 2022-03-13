
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import jmoreira.pfc.galois.ExtendedGaloisField;
import jmoreira.pfc.galois.GFException;
import jmoreira.pfc.galois.GFPolynomial;
import jmoreira.pfc.galois.GFVector;
import jmoreira.pfc.galois.GaloisField;
import jmoreira.pfc.galois.RootGaloisField;


public class MainReedSolomonToComplete {

    public static void main(String[] args) {
        try {

            // Reed-Solomon code of length 8 and dimension 2 over field gf8
            // See also the end of the file
            // k is the dimension of the code
            int k = 2;
            // Build the field GF8
            GaloisField gf2 = new RootGaloisField(2);
            ExtendedGaloisField gf8 = new ExtendedGaloisField(gf2, 3);
            // Acces the elements of the field GaloisField.Element using the attribute element
            // element is an array of GaloisField.Element
            GaloisField.Element[] elementsGF8 = gf8.element;

            // Vector to store the code
            Vector<GFVector> code = new Vector();

            // Coefficients of the ``message polynomial''
            GaloisField.Element[] coefs = new GaloisField.Element[k];

            /**
             * ******************************************************************
             * To create the code iterate over all polynomials of degree less
             * than k and then use:
             *
             * Generate the message polynomials
             * 
             * GFPolynomial polM = new GFPolynomial(coefs, gf8);
             * 
             * Then
             * 
             * GaloisField.Element[] codewordVec = new GaloisField.Element[elements.length]; 
             * 
             * The evaluate the polynomial for all elements 
             * 
             * GFVector codeword = new GFVector(codewordVec,gf8);
             */


            // Compute the distance of the code and check with the theoretical value


            // Compute the minimum weight and check with the theoretical value
            // and with the distance value

            
            
            // Error correction
            GFVector c1 = code.elementAt(10);
            System.out.println("codeword : " + c1);
            // Introduce 3 errors
            GFVector c2 = c1.copy();
            c2.setElementAt(c1.elementAt(2).add(gf8.oneElement()), 2);
            c2.setElementAt(c1.elementAt(4).add(gf8.oneElement()), 4);
            c2.setElementAt(c1.elementAt(6).add(gf8.oneElement()), 6);

            System.out.println("corrupted codeword : " + c2);

            //  Check how many codewords at distance 3

            // Introduce two more errors


            //  Check how many codewords at distance 5
            for (GFVector c : code) {
                if (c.dist(c2) <= 5) {
                    System.out.println("decoded codeword : " + c);
                }
            }

        } catch (GFException ex) {
            Logger.getLogger(MainReedSolomonToComplete.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }
}
// You can also try this:

//            RSEvaluationPointSet evalPoints = new RSEvaluationPointSet(gf8.cardinality(), gf8);
//            ReedSolomon code = new ReedSolomon(evalPoints, k, gf8);
//            Vector<GFVector> codewords = code.codewords();
//            for(GFVector c : codewords){
//                System.out.println(c);
//            }

