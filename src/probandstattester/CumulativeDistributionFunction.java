package probandstattester;

/**
 * This class will solve the Cumulative Distribution from the given parameters.
 * 
 * @author Patrick Trahan
 * @param <E>
 */
public class CumulativeDistributionFunction<E>
{
    //global variables
    private E[] cdfArray;
    private int n;
    private double p;
    private int startYValue;
    private int endYValue;
    private Combination comb = new Combination();
    
    /**
     * This method will call the methods to solve the cdf and then prints the 
     * value for the user.
     * 
     * @param nValue is the value for n.
     * @param prob is the value for probability.
     * @param startY is the starting Y value.
     * @param endY is the ending Y value.
     */
    public void run(int nValue, double prob, int startY, int endY)
    {
        //sets the n value
        n = nValue;
        
        //sets the probability value
        p = prob;
        
        //sets the starting y value
        startYValue = startY;
        
        //sets the ending y value
        endYValue = endY;
        
        //calls the method to solve the cdf
        solvecdfArray(startY, endY);
        
        //prints the outputs
        printCDF();
    }
    
    /**
     * This method will loop from the starting y value to the ending y value in 
     * a for loop and call the method to solve the cdf value.
     * 
     * @param startY is the starting y value.
     * @param endY is the ending y value.
     */
    public void solvecdfArray(int startY, int endY)
    {
        //increments to solve all of the values between the starting and ending
        //y values
        for(int i = startY; i <= endY; i++)
        {
            //adds the cdf value to the array
            cdfArray = addElement(cdfArray, (E) ((Number) solvecdf(i)));
        }
    }
    
    /**
     * This method will solve the cdf value for the given values.
     * 
     * @param y is the value for y.
     * @return the cdf value.
     */
    public double solvecdf(int y)
    {
        //this is the formula for the cdf value
        return (comb.combination(n, y)).doubleValue() * Math.pow(p, y) * Math.pow(p, n - y);
    }
    
    /**
     * This method will print the output for the cdfArray.
     */
    public void printCDF()
    {
        //prints the starting value
        System.out.println("The Cumulative Distribution Function is: \n");
        System.out.println("F(y) = ");
        System.out.println("0, y < 0,");
        
        //sets the position to 0
        int position = 0;
        
        //sets totalProb to 0
        double totalProb = 0;
        
        //loops through the cdfArray
        for(int i = startYValue; i < endYValue; i++)
        {
            //sets the probability for the given cdf and adds them together
            totalProb = totalProb + ((Number)cdfArray[position]).doubleValue();
            
            //prints the output
            System.out.println(totalProb + ", " + position + " <= y < " + (i + 1) + ",");
            
            //increment the position
            position++;
        }
        
        //prints the ending value
        System.out.println("1, y >= " + endYValue + ".");
    }
    
    /**
     * This method adds an element to the userList array.
     * 
     * @param userList
     * @param element
     * @return m element
     */
    public E[] addElement(E[] userList, E element)
    {
        //base case
        //if the userList array is null 
        if (userList == null)
        {
            //then create a new Object with a size of 1
            E[] temp = (E[]) new Object[1];
            
            //store the element in the first position of the temp array
            temp[0] = element;
            
            //return the temp array
            return temp;
        }
        
        //create a temp array with a length of 1 + the size of the userList array
        E[] temp = (E[]) new Object[userList.length + 1];
        
        //holds the position of temp
        int tempPosition = 0;

        //loops through the length of the userList array
        for (int i = 0; i < userList.length; i++)
        {
            //store the element at position i from the userList array into the temp array
            //at position tempPosition
            temp[tempPosition] = userList[i];
            
            //increment tempPosition by 1
            tempPosition++;
        }
        
            //add the element at the end of the temp array 
        temp[tempPosition] = element;
        
        return temp;
    }
}
