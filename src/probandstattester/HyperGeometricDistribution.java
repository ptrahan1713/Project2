package probandstattester;

import java.math.BigInteger;

/**
 * This class solves for the HyperGeometric Distribution with the given parameters.
 * 
 * @author Patrick Trahan
 */
public class HyperGeometricDistribution
{
    //global Variables
    private double hyperGeometricProb;
    private BigInteger topPart;
    private BigInteger bottomPart;
    private Combination comb = new Combination();
    
    /**
     * This method calls the solve method and then calls the print method for the
     * class.
     * 
     * @param bigN is the value for N.
     * @param littleN is the value for n.
     * @param r is the value for r.
     * @param y is the value for y.
     */
    public void run(int bigN, int littleN, int r, int y)
    {
        solveHyperGeometric(bigN, littleN, r, y);
        
        printHyperGeometric();
    }
    
    /**
     * This method will solve the HyperGeometric Distribution with the given 
     * parameters. 
     * 
     * @param bigN is the value for N.
     * @param littleN is the value for n.
     * @param r is the value for r.
     * @param y is the value for y.
     */
    public void solveHyperGeometric(int bigN, int littleN, int r, int y)
    {
        //solves the top part of the HyperGeometric Distribution
        topPart = comb.combination(r, y).multiply(comb.combination(bigN - r, littleN - y));
        
        //solves the bottom part of the HyperGeometric Distribution
        bottomPart = comb.combination(bigN, littleN);
        
        //solves the HyperGeometric Distribution
        hyperGeometricProb = topPart.doubleValue() / bottomPart.doubleValue();
    }
    
    /**
     * This method prints the HyperGeometric Distribution.
     */
    public void printHyperGeometric()
    {
        System.out.println("The HyperGeometric Distribution is " + hyperGeometricProb);
    }
}
