package graphwithlibrary;

import java.util.Random;

/**
 * This class will salt the original array by changing all of the values in the 
 * given array. 
 * 
 * @author Patrick Trahan
 * @param <E> using generics
 */
public class Salter<E>
{
    //global variables
    private E[] saltArray;
    private double changedY;
    private Random rand = new Random();
    private final int range = 1000;
    
    /**
     * This method will loop through the array and randomize the y-values.
     * 
     * @param dataArray is the given array.
     */
    public void loopArray(E[] dataArray)
    {
        //loops through the dataArray
        for(int i = 0; i < dataArray.length; i++)
        {
            //calls the method to randomize the y-value
            changedY = randomizeY(dataArray, i);
            
            //setting the new y-value to a string of the x-value of the dataArray
            E temp = (E) (splitX(dataArray, i) + ", " + changedY);
            
            //adds temp to the saltArray
            saltArray = addElement(saltArray, temp);
        }
    }
    
    /**
     * This method randomizes the y-value.Calls the appropriate method if you 
     * are adding or subtracting from the y-value.
     * 
     * @param dataArray
     * @param position is the value of where you are in the array.
     * @return the new y-value.
     */
    public double randomizeY(E[] dataArray, int position)
    {
        //generates a random number from 0 or 1
        int temp = rand.nextInt(2);
        
        //if the number is 0 then we are adding the random number to the y-value
        if(temp == 0)
        {
            return addY(dataArray, position);
        }
        
        //we are subtracting the random number from the y-value
        return subtractY(dataArray, position);
    }
    
    /**
     * This method will create a random number from the range of 0 to 99.Then, 
     * it will add the random number to the y-value. 
     * 
     * @param array
     * @param position is the value of where you are in the array.
     * @return the new y-value.
     */
    public double addY(E[] array, int position)
    {
        //generates the random number
        int temp = rand.nextInt(range);
        
        //calls the method to get the y-value at the given position
        //then parses it to a double
        double yValue = Double.parseDouble(splitY(array, position));
        
        //adds the random number to the original y-value
        yValue = yValue + temp;
        
        //returns the new y-value
        return yValue;
    }
    
    /**
     * This method will create a random number from the range of 0 to 99.Then,
 it will subtract the random number to the y-value.
     * 
     * @param array
     * @param position is the value of where you are in the array.
     * @return the new y-value.
     */
    public double subtractY(E[] array, int position)
    {
        //generates the random number
        int temp = rand.nextInt(range);
        
        //calls the method to get teh y-value at the given position
        //then parses it to a double
        double yValue = Double.parseDouble(splitY(array, position));
        
        //subtracts the random number from the y-value
        yValue = yValue - temp;
        
        //returns the new y-value
        return yValue;
    }
    
    /**
     * This method will split the y-value from the array at the given position.
     * 
     * @param plotArray
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
     * @param plotArray
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
     * This method returns the salted Array.
     * 
     * @return the list.
     */
    public E[] getSaltedList()
    {
        return saltArray;
    }
}
