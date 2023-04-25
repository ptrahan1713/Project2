package probandstattester;

/**
 * This class will solve the Tchebysheff probability for the given parameters.
 * 
 * @author Patrick Trahan
 */
public class Tchebysheff
{
    //global variables
    private double tchebysheffValue;
    private double within;
    private double lower;
    private double upper;
    private double k;
    
    /**
     * This method will call the methods to solve the Tchebysheff probability
     * and print the results.
     * 
     * @param mean is the value for the mean.
     * @param standardDeviation is the value for the standard deviation.
     * @param lowerBound is the value of the lower bound.
     * @param upperBound is the value of the higher bound.
     */
    public void run(double mean, double standardDeviation, double lowerBound, double upperBound)
    {
        solveTchebysheff(mean, standardDeviation, lowerBound, upperBound);
        
        printTchebysheff();
    }
    
    /**
     * This method solves the Tchebysheff probability of the given parameters.
     * 
     * @param mean is the value for the mean.
     * @param standardDeviation is the value for the standard deviation.
     * @param lowerBound is the value of the lower bound.
     * @param upperBound is the value of the higher bound.
     */
    public void solveTchebysheff(double mean, double standardDeviation, double lowerBound, double upperBound)
    {
        //sets the lower bound
        lower = lowerBound;
        
        //sets the upper bound
        upper = upperBound;
        
        //solves the within value
        within = upperBound - mean;
        
        //checks to see if the within value is different for the upper bound
        if(within != (mean - lowerBound))
        {
            //got an error and prints an output
            System.out.println("An error occurred when calculating the within value");
            System.out.println("The within for the upper and lower bounds are not the same");
        }
        
        //solves the k value
        k = within / standardDeviation;
        
        //solves the Tchebysheff probability
        tchebysheffValue = 1 - 1/(k * k);
    }
    
    /**
     * This method prints the Tchebysheff probability with the given bounds.
     */
    public void printTchebysheff()
    {
        System.out.println((tchebysheffValue * 100) + "% of data values are between " 
                + lower + " and " + upper + " bounds");
    }
}
