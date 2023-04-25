package probandstattester;

import java.math.BigInteger;

/**
 * This program solves the given inputs with combination.
 * 
 * @author Patrick Trahan
 */
public class Combination
{
    private Factorial factorial = new Factorial();
    private BigInteger top;
    private BigInteger bottomPart1;
    private BigInteger bottomPart2;
    private BigInteger combination;
    
    /**
     * This method solves the combination with the given numbers.
     * 
     * @param n is the value for the n variable
     * @param r is the value for the r variable
     */
    public void run(int n, int r)
    {
        combination(n, r);
        
        printCombination(n, r, combination);
    }
    
    /**
     * This method is the backbone of the combination method.
     * 
     * @param n is the parameter
     * @param r is the parameter
     * @return the combination
     */
    public BigInteger combination(int n, int r)
    {
        top = factorial.factorialCalculator(n);
        
        bottomPart1 = factorial.factorialCalculator(r);
        
        bottomPart2 = factorial.factorialCalculator(n - r);
        
        combination = top.divide(bottomPart1.multiply(bottomPart2));
        
        return combination;
    }
    
    /**
     * This method prints the combination.
     * 
     * @param n is one of the parameter
     * @param r is one of the parameter
     * @param combination is the solution to the combination
     */
    public void printCombination(int n, int r, BigInteger combination)
    {
        System.out.println("The Combination with n = " + n + " and r = " + r);
        System.out.println("Combination = " + combination);
    }
}

