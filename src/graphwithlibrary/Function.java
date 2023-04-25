package graphwithlibrary;

/**
 * This is solve the function with the given x-values and will loop with the 
 * given amount of loops.
 * 
 * @author Patrick Trahan
 * @param <E> is for generics.
 */
public class Function <E>
{
    //Global variables
    private E yValue;
    private E xValue;
    
    private E[] list;
    
    private XandYValue value = new XandYValue();
    
    /**
     * This method will call the loop method to solve for the y-values and sets 
     * the list to the range of y-values.
     */
    public void run()
    {
        loop(50, (E)Double.valueOf(0), (E)Double.valueOf(0), (E)Double.valueOf(2));
        
        list = (E[]) value.getList();
    }
    
    /**
     * This method will loop with the given loopValue to solve for the y-value
     * with the given x-value. It will increment the x-value by the incrementSize
     * after each loop.
     * 
     * @param loopValue the amount of times you want to loop.
     * @param x is a generic value.
     * @param y is a generic value.
     * @param incrementSize is the increment value of x.
     */
    public void loop(int loopValue, E x, E y, E incrementSize)
    {
        //sets x
        xValue = x;
        
        //loops through the for loop the amount of times that loop is equal to
        for(int i = 0; i < loopValue; i++)
        {
            //calls the function to solve for y
            yValue = solveXSquared(xValue);
            
            //sets the x and y values as a pair
            value.setPoint(xValue, yValue);
            
            //increments xValue by one and done it two steps
            
            Double temp = (((Number) xValue).doubleValue() + ((Number) incrementSize).doubleValue());
            
            xValue = (E) temp;
        }
    }
    
    /**
     * This method finds the solution with the given x value.
     * 
     * @param userXValue is the value of x.
     * @return the solution as a generic.
     */
    public E solveXSquared(E userXValue)
    {
        Double solution = ((Number) userXValue).doubleValue() * ((Number) userXValue).doubleValue();
        
        return (E) solution;
    }
    
    /**
     * This method will return the list of x and y values.
     * 
     * @return the list.
     */
    public E[] getFunctionList()
    {
        return list;
    }
}
