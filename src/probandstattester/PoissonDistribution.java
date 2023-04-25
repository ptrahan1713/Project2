package probandstattester;

import static java.lang.Math.exp;
import static java.lang.Math.pow;


/**
 * This class will solve for the Poisson Distribution for the given parameters.
 * 
 * @author Patrick Trahan
 */
public class PoissonDistribution
{
    //global variables
    private double poisson;
    private double topPart;
    private double bottomPart;
    private Factorial fact = new Factorial();
    
    /**
     * This method will call the methods to solve the Poisson Distribution and 
     * then print the value.
     * 
     * @param lamba is the mean value.
     * @param y is the value for y.
     */
    public void run(double lamba, int y)
    {
        solvePoisson(lamba, y);
        
        printPoissonDistribution();
    }
    
    /**
     * This method solves the Poisson Distribution for the given parameters.
     * 
     * @param lamba is the mean value.
     * @param y is the value for y.
     */
    public void solvePoisson(double lamba, int y)
    {
        //solves the top part of the Poisson Distribution
        topPart = exp(-lamba) * pow(lamba, y);
        
        //solves the bottom part of the Poisson Distribution
        bottomPart = fact.factorialCalculator(y).doubleValue();
        
        //solves the Poisson Distribution
        poisson = topPart / bottomPart;
    }
    
    /**
     * This method prints the value of the Poisson Distribution.
     */
    public void printPoissonDistribution()
    {
        System.out.println("The Poisson Distribution is " + poisson);
    }
}
