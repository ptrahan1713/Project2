package filegrapher;

import java.io.IOException;

/**
 * This program loops through the math function that the user chose and pass the 
 * values into a .csv file for Excel to graph.
 * 
 * @author Patrick Trahan
 * @param <E> is for generics.
 */
public class Function <E>
{
    //Global variables
    private E yValue;
    private E xValue;
    
    private FileCreator creator = new FileCreator();
    private XandYValue value = new XandYValue();
    
    /**
     * This method will create the file and then loop from the starting value 
     * to the amount of runs with increments of a given value.
     */
    public void run()
    {
        creator.createFile();
        
        loop(50, (E)Double.valueOf(0), (E)Double.valueOf(0), (E)Double.valueOf(2));
        
        exportPlot();
    }
    /**
     * This method takes in the parameters and calls a function to find the x and
     * y values then passes those values to the csv class to plot the values. 
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
    
    public void exportPlot()
    {
        E[] temp = (E[]) value.getList();
        
        for(int i = 0; i < temp.length; i++)
        {
            //passes the values to the csv class
            creator.passValue((E) value.getPoint(i));
        }
        
        //trys to close the file
        try
        {
            creator.closeFile();
        }
        catch(IOException e)
        {
            System.out.println("An error occurred");
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
     * This method prints the x and y values.
     * 
     * @param x is the generic value of x.
     * @param y is the generic value of y. 
     */
    public void printValues(E x, E y)
    {
        System.out.println(x + ", " + y);
    }
}
