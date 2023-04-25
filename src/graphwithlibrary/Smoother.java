package graphwithlibrary;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 * This class will smooth the passed in array. We assume that it is a salted array
 * where the goal is to redo the salting of the array and try to get back to the 
 * original.
 * 
 * @author Patrick Trahan
 * @param <E> using generics
 */
public class Smoother<E>
{
    //global variables
    private DescriptiveStatistics stats = new DescriptiveStatistics();   
    private E[] smoothArray;
    private int windowSize;
    
    /**
     * This method will take in the salted array and smooth the array.
     * 
     * @param saltArray is the salted array that was originally passed in.
     */
    public void run(E[] saltArray)
    {
        //this sets the range
        windowSize = 4;
        
        //calls the method to smooth the array
        smooth(saltArray);
    }
    
    /**
     * This method will smooth the array through a for loop.
     * 
     * @param saltArray is the original array.
     */
    public void smooth(E[] saltArray)
    {
        //loops through the salt array 
        for(int i = 0; i < saltArray.length; i++)
        {
            //clears the values of the stat values
            stats.clear();
            
            //if i is less than the value of the window size
            if(i < windowSize)
            {
                //the loop will loop windowSize plus i 
                for(int j = 0; j < (i + windowSize); j++)
                {
                    //if j is less than the length of the saltArray
                    if(j < saltArray.length)
                    {
                        //sets the Window Size for the Stats library with the range
                        stats.setWindowSize(windowSize + i);
                        
                        //adds the value to the addValue method of the stats
                        stats.addValue(Double.parseDouble(splitY(saltArray, j)));
                    }
                }
            }
            //if i is greater than the length of the saltArray minus windowSize plus 1
            else if(i > ((saltArray.length - windowSize) + 1))
            {
                //decrements starting at the length of saltArray and stops at when
                //j greater than i minus windowSize
                for(int j = saltArray.length; j > (i - windowSize); j--)
                {
                    //if j is less than the length of the saltArray
                    if(j < saltArray.length)
                    {
                        //sets the Window Size for the Stats library with the range
                        stats.setWindowSize(windowSize + (saltArray.length - j));
                        
                        //adds the value to the addValue method of the stats
                        stats.addValue(Double.parseDouble(splitY(saltArray, j)));
                    }
                }
            }
            else
            {
                //starts at the negative value of windowSize to the value of windowSize
                for(int j = -(windowSize); j < windowSize; j++)
                {
                    //if i + j is less than the length of the saltArray
                    if((i + j) < saltArray.length)
                    {
                        //sets the Window Size for the Stats library with the range
                        stats.setWindowSize(windowSize * 2);
                        
                        //adds the value to the addValue method of the stats
                        stats.addValue(Double.parseDouble(splitY(saltArray, i + j)));
                    }
                }
            }
            //gets the mean of the added values 
            addSmootherArray((E) splitX(saltArray, i), (E) ((Number) stats.getMean()));
        }
    }
    
    /**
     * This method will take in a x and y value and adds it to the smoothArray.
     * 
     * @param xValue is the x-value of the saltedArray.
     * @param yValue is the value of the rolling mean.
     */
    public void addSmootherArray(E xValue, E yValue)
    {
        String temp = xValue.toString() + ", " + yValue.toString();
        
        smoothArray = addElement(smoothArray, (E) temp);
    }
    
    /**
     * This method adds an element to the userList array.
     * 
     * @param userList is the given array.
     * @param element is the element getting adding to the array.
     * @return the new array.
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
    
    /**
     * This method will split the y-value from the array at the given position.
     * 
     * @param plotArray is the given array.
     * @param position is the value of where you are in the array.
     * @return the String value of the y-value.
     */
    public String splitY(E[] plotArray, int position)
    {
        //creates a string that contains the x and y values of the given position
        String value = plotArray[position].toString();
        
        //creates a String array that contains a x-value and a y-value
        String[] splitValue = value.split(",");
        
        //returns the y-value from the splitValue array
        return splitValue[1];
    }
    
    /**
     * This method will split the y-value from the array at the given position.
     * 
     * @param plotArray is the given array.
     * @param position is the value of where you are in the array.
     * @return the String value of the y-value.
     */
    public String splitX(E[] plotArray, int position)
    {
        //creates a String that contains the x and y values of the given position
        String value = plotArray[position].toString();
        
        //creates a String array that contains a x-value and a y-value
        String[] splitValue = value.split(",");
        
        //returns the x-value from the splitValue array
        return splitValue[0];
    }
    
    /**
     * This method will return the smooth array.
     * 
     * @return the array.
     */
    public E[] getSmootherList()
    {
        return smoothArray;
    }
}
