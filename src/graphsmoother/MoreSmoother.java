package graphsmoother;

import java.io.IOException;

/**
 * This program will smooth the y-values further with the given amount of loops.
 * The static value is 3 because there are only 3 more files created. 
 * 
 * @author Patrick Trahan
 * @param <E>
 */
public class MoreSmoother<E>
{
    private FileSmoother file = new FileSmoother();
    private E[] plotArray;
    private E[] yValueArray;
    private double averageYValue;
    private static int range = 3;
    private static int smoothingLoop = 3;
    
    /**
     * This method runs the program to smooth the graph more than once.
     * 
     * @param userPlotArray is the starting x and y values of the salted function.
     * @param userYValueArray is the array of the first loop of smoothing the y values.
     * @throws IOException 
     */
    public void run(E[] userPlotArray, E[] userYValueArray) throws IOException
    {
        plotArray = userPlotArray;
        
        yValueArray = userYValueArray;
        
        file.createRestFile();
        
        loopAmountSmoother(smoothingLoop);
        
        file.closeRestFile();
    }
    
    /**
     * This method will loop though smoothing the array by the given number of 
     * loops.
     * 
     * @param loop amount of times you are smoothing the graph.
     */
    public void loopAmountSmoother(int loop)
    {
        for(int i = 0; i < loop; i++)
        {
            loopArray(i);
        }
    }
    
    /**
     * This method loops through the array. In each iteration we find the average 
     * of y with the range, divides the average of the y-values by 4, and finally 
     * passes the values into the file.
     */
    public void loopArray(int numSmoother)
    {
        for(int i = 0; i < plotArray.length - range; i++)
        {
            //sets the average to 0
            averageYValue = 0;
            
            //find the average of y of the given position
            averageY(i);
            
            yValueArray[i] = (E) Double.valueOf(averageYValue);
            
            //passes the values of the given position into the file
            passToFile(i, numSmoother);
        }
    }
    
    /**
     * This method will call the correct method to find the average y of the 
     * given position.
     * 
     * @param position is the value of the given position in the array.
     */
    public void averageY(int position)
    {
        //starting value
        if(position == 0)
        {
            positionZero(position);
            
            //calculates the average with the amount of range
            averageYValue = averageYValue / 4;
        }
        //second value
        else if(position == 1)
        {
            secondPosition(position);
            
            //calculates the average with the amount of range
            averageYValue = averageYValue / 5;
        }
        //third value
        else if(position == 2)
        {
            thirdPosition(position);
            
            //calculates the average with the amount of range
            averageYValue = averageYValue / 6;
        }
        //third to last position
        else if(position == (plotArray.length - 3))
        {
            thirdLastPosition(position);
            
            //calculates the average with the amount of range
            averageYValue = averageYValue / 6;
        }
        //second to last value
        else if(position == (plotArray.length - 2))
        {
            secondLastPosition(position);
            
            //calculates the average with the amount of range
            averageYValue = averageYValue / 5;
        }
        //last value
        else if(position == (plotArray.length - 1))
        {
            lastPosition(position);
            
            //calculates the average with the amount of range
            averageYValue = averageYValue / 4;
        }
        //every other value
        else
        {
            standardPosition(position);
            
            //calculates the average with the amount of range
            averageYValue = averageYValue / 7;
        }
    }
    
    /**
     * This method will take the given position and find the value of the y-value. 
     * Also takes the next 3 values and the 3 previous values and adds all of them
     * to the average. Returns the average value.
     * 
     * @param position is the value of the given position in the array.
     */
    public void standardPosition(int position)
    {
        //gets the y-value at the given position
        double yValue = ((Number) yValueArray[position]).doubleValue();
            
        //adds the y-value to the average
        averageYValue = averageYValue + yValue;
        
        //calls the method to loop backwards and adds it to the average
        averageYValue = averageYValue + loopBackward(position);
        
        //calls the method to loop forwards and adds it to the average
        averageYValue = averageYValue + loopForward(position);
    }
    
    /**
     * This method will start at position 0 and goes to position 3 and adds up the
     * average of all of the y-values.
     * 
     * @param position is the value of the given position in the array.
     */
    public void positionZero(int position)
    {
        //gets the y-value at the given position
        double yValue = ((Number) yValueArray[position]).doubleValue();
            
        //adds the y-value to the average
        averageYValue = averageYValue + yValue;
        
        //calls the method to loop forwards and adds it to the average
        averageYValue = averageYValue + loopForward(position);
    }
    
    /**
     * This method will start at the second position. It takes the previous 
     * y-value and the next 3 y-values and adds them all to the average.
     * 
     * @param position is the value of the given position in the array.
     */
    public void secondPosition(int position)
    {
        //gets the y-value at the given position
        double yValue = ((Number) yValueArray[position]).doubleValue();
            
        //adds the y-value to the average
        averageYValue = averageYValue + yValue;
        
        //gets the y-value at the previous position
        yValue = ((Number) yValueArray[position - 1]).doubleValue();
            
        //adds the y-value to the average
        averageYValue = averageYValue + yValue;
        
        //calls the method to loop forwards and adds it to the average
        averageYValue = averageYValue + loopForward(position);
    }
    
    /**
     * This method will start at the third position. It takes the previous 2
     * y-values and the next 3 y-values and adds them all to the average.
     * 
     * @param position is the value of the given position in the array.
     */
    public void thirdPosition(int position)
    {
        //gets the y-value at the given position
        double yValue = ((Number) yValueArray[position]).doubleValue();
            
        //adds the y-value to the average
        averageYValue = averageYValue + yValue;
        
        //gets the y-value at the previous position
        yValue = ((Number) yValueArray[position - 1]).doubleValue();
            
        //adds the y-value to the average
        averageYValue = averageYValue + yValue;
        
        //gets the y-value at the next previous position
        yValue = ((Number) yValueArray[position - 1]).doubleValue();
            
        //adds the y-value to the average
        averageYValue = averageYValue + yValue;
        
        //calls the method to loop forwards and adds it to the average
        averageYValue = averageYValue + loopForward(position);
    }
    /**
     * This method will start at the third to last position. It takes the next 2
     * y-values and the previous 3 y-values and adds them all to the average.
     * 
     * @param position is the value of the given position in the array.
     */
    public void thirdLastPosition(int position)
    {
        //gets the y-value at the given position
        double yValue = ((Number) yValueArray[position]).doubleValue();
            
        //adds the y-value to the average
        averageYValue = averageYValue + yValue;
        
        //gets the y-value at the next position
        yValue = ((Number) yValueArray[position + 1]).doubleValue();
            
        //adds the y-value to the average
        averageYValue = averageYValue + yValue;
        
        //gets the y-value at the second position
        yValue = ((Number) yValueArray[position + 2]).doubleValue();
            
        //adds the y-value to the average
        averageYValue = averageYValue + yValue;
        
        //calls the method to loop backwards and adds it to the average
        averageYValue = averageYValue + loopBackward(position);
    }
    
    /**
     * This method will start at the second to last position in the array. It will
     * add up the 3 values around the position.
     * 
     * @param position is the value of the given position in the array.
     */
    public void secondLastPosition(int position)
    {
        //gets the y-value at the given position
        double yValue = ((Number) yValueArray[position]).doubleValue();
            
        //adds the y-value to the average
        averageYValue = averageYValue + yValue;
        
        //gets the y-value at the next position
        yValue = ((Number) yValueArray[position + 1]).doubleValue();
            
        //adds the y-value to the average
        averageYValue = averageYValue + yValue;
        
        //calls the method to loop backwards and adds it to the average
        averageYValue = averageYValue + loopBackward(position);
    }
    
    /** 
     * This method will start at the last position and find the y-value. Then, 
     * find the previous 3 y-values and adds them to the average. 
     * 
     * @param position is the value of the given position in the array.
     */
    public void lastPosition(int position)
    {
        //gets the y-value at the given position
        double yValue = ((Number) yValueArray[position]).doubleValue();
            
        //adds the y-value to the average
        averageYValue = averageYValue + yValue;
        
        //calls the method to loop backwards and adds it to the average
        averageYValue = averageYValue + loopBackward(position);
    }
    
    /**
     * This method will loop through the range to get the next 3 values from the
     * given position. It will add the temp value to the yValue and finally 
     * returns the yValue.
     * 
     * @param position is the value of where you are in the array.
     * @return the average sum of the y-values.
     */
    public double loopForward(int position)
    {
        double yValue = 0;
        
        for(int i = 0; i < range; i++)
        {
            double temp = ((Number) yValueArray[position + 1 + i]).doubleValue();
            
            yValue = yValue + temp;
        }
        
        return yValue;
    }
    
    /**
     * This method will loop through the range to get the previous 3 values from
     * the given position. It will add the temp value to the yValue and 
     * finally returns the yValue.
     * 
     * @param position is the value of where you are in the array.
     * @return the average sum of the y-values.
     */
    public double loopBackward(int position)
    {
        double yValue = 0;
        
        for(int i = 0; i < range; i++)
        {
            double temp = ((Number) yValueArray[position - 1 - i]).doubleValue();
            
            yValue = yValue + temp;
        }
        
        return yValue;
    }
    
    /**
     * This method will take the y-value and the position of the array. Then, 
     * pass the x and y values into the file.
     * 
     * @param position is the value of where you are in the array.
     */
    public void passToFile(int position, int numSmoother)
    {
        //calls the method to get the x-value at the given position
        String xValue = splitX(position);
        
        //converts the y-value to a String 
        String yValue = Double.toString(averageYValue);
        
        //concatenates the x and y value with a ","
        String value = xValue + ", " + yValue; 
        
        //passes the value into the file
        file.passNextValue(value, numSmoother);
    }
    
    /**
     * This method will split the y-value from the array at the given position.
     * 
     * @param position is the value of where you are in the array.
     * @return the String value of the y-value.
     */
    public String splitX(int position)
    {
        //creates a String that contains the x and y values of the given position
        String value = plotArray[position].toString();
        
        //creates a String array that contains a x-value and a y-value
        String[] splitValue = value.split(",");
        
        //returns the x-value from the splitValue array
        return splitValue[0];
    }
}
