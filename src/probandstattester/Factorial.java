package probandstattester;

import java.math.BigInteger;

/**
 * This program solves the factorial of the given number that is passed in.
 * 
 * @author Patrick Trahan
 */
public class Factorial
{
    private BigInteger factorial;
    
    /**
     * Runs the factorial class.
     * 
     * @param fact is the number you want to calculate.
     */
    public void run(int fact)
    {
        factorialCalculator(fact);
        
        printFactorial();
    }
    
    /**
     * This method calculates the factorial of the given number
     * 
     * @param fact is the given number
     * @return the factorial number of the given number
     */
    public BigInteger factorialCalculator(int fact)
    {
        if(fact == 1)
        {
            return BigInteger.valueOf(fact);
        }
        
        factorial = BigInteger.ONE;
        
        for(int i = 2; i <= fact; i++)
        {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        
        return factorial;
    }
    
    /**
     * Prints the factorial of the given number.
     */
    public void printFactorial()
    {
        System.out.println("Factorial is " + factorial);
    }
}
