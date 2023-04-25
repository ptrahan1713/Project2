package graphsmoother;

import java.io.File;
import java.io.IOException;

/**
 * This class will call a method to read the file path of the saltedFunction.csv.
 * Next it will create the file. The main function is to loop through the array 
 * to find the average of the values of the y-values. Finally, it plots the values
 * and then closes the file.
 * 
 * @author Patrick Trahan
 * @param <E> is the generic variable.
 */
public class Smoother<E>
{
    //global variables
    private ReadFile file = new ReadFile();
    private FileSmoother smoother = new FileSmoother();
    private MoreSmoother rest = new MoreSmoother();
    private E[] plotArray;
    private E[] yValueArray;
    private String filePath;
    private String userDir;
    private String[] userDirList;
    private double averageYValue;
    private static int range = 3;
    
    /**
     * This method runs the smoother. It gets an array of the contents of the 
     * saltedFunction.csv. Then create the file. Next it loops through the array
     * to find the average of the range of y-values. Then closes the file.
     * 
     * @throws IOException 
     */
    public void run() throws IOException
    {
        //creates the file path to the orginal .csv file
        findPath();
        
        //an array containing the x and y values of the saltedFunction.csv
        plotArray = (E[]) file.readFile(filePath);
        
        //creates the file
        smoother.createFile();
        
        //calls a method to loop through the array
        loopArray();
        
        //closes the file
        smoother.closeFile();
        
        rest.run(plotArray, yValueArray);
    }
    
    /**
     * This method will get the file path of this program. Next, it separates the
     * String of the filePath to move out of the directory. Now it sets the file 
     * path to the GraphSalter/saltedFunction.csv at the end of the file path. Finally,
     * it validates to make sure the saltedFunction.csv file exists. If it does not exists
     * it tells the user to create the file and exits the program.
     */
    public void findPath()
    {
        //gets the directory where the program is located
        userDir = System.getProperty("user.dir");
        
        //splits the string by every backslash
        userDirList = userDir.split("\\\\");
        
        //sets the string to null;
        filePath = "";
        
        //adds the directory but not including the last value to the filePath 
        for(int i = 0; i < userDirList.length - 1; i++)
        {
            //uses "/" because thats how java reads files
            filePath = filePath + userDirList[i] + "/";
        }
        
        //adds the string of the rest of the file path to the existing file path
        filePath = filePath + "GraphSalter/saltedFunction.csv";
        
        //validates to see if the file path exists
        if(!((new File(filePath)).exists()))
        {
            System.out.println("The file does not exist");
            System.out.println("Ending the program and create the salted csv file");
            System.exit(0);
        }
    }
    
    /**
     * This method loops through the array. In each iteration we find the average 
     * of y with the range, divides the average of the y-values by 4, and finally 
     * passes the values into the file.
     */
    public void loopArray()
    {
        for(int i = 0; i < plotArray.length; i++)
        {
            //sets the average to 0
            averageYValue = 0;
            
            //find the average of y of the given position
            averageY(i);
            
            yValueArray = addElement(yValueArray, (E) Double.valueOf(averageYValue));
            
            //passes the values of the given position into the file
            passToFile(i);
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
        double yValue = Double.parseDouble(splitY(position));
            
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
        double yValue = Double.parseDouble(splitY(position));
            
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
        double yValue = Double.parseDouble(splitY(position));
            
        //adds the y-value to the average
        averageYValue = averageYValue + yValue;
        
        //gets the y-value at the previous position
        yValue = Double.parseDouble(splitY(position - 1));
            
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
        double yValue = Double.parseDouble(splitY(position));
            
        //adds the y-value to the average
        averageYValue = averageYValue + yValue;
        
        //gets the y-value at the previous position
        yValue = Double.parseDouble(splitY(position - 1));
            
        //adds the y-value to the average
        averageYValue = averageYValue + yValue;
        
        //gets the y-value at the next previous position
        yValue = Double.parseDouble(splitY(position - 2));
            
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
        double yValue = Double.parseDouble(splitY(position));
            
        //adds the y-value to the average
        averageYValue = averageYValue + yValue;
        
        //gets the y-value at the next position
        yValue = Double.parseDouble(splitY(position + 1));
            
        //adds the y-value to the average
        averageYValue = averageYValue + yValue;
        
        //gets the y-value at the second position
        yValue = Double.parseDouble(splitY(position + 2));
            
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
        double yValue = Double.parseDouble(splitY(position));
            
        //adds the y-value to the average
        averageYValue = averageYValue + yValue;
        
        //gets the y-value at the next position
        yValue = Double.parseDouble(splitY(position + 1));
            
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
        double yValue = Double.parseDouble(splitY(position));
            
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
            double temp = Double.parseDouble(splitY(position + 1 + i));
            
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
            double temp = Double.parseDouble(splitY(position - 1 - i));
            
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
    public void passToFile(int position)
    {
        //calls the method to get the x-value at the given position
        String xValue = splitX(position);
        
        //converts the y-value to a String 
        String yValue = Double.toString(averageYValue);
        
        //concatenates the x and y value with a ","
        String value = xValue + ", " + yValue;
        
        //passes the value into the file
        smoother.passValue(value);
    }
    
    /**
     * This method will split the y-value from the array at the given position.
     * 
     * @param position is the value of where you are in the array.
     * @return the String value of the y-value.
     */
    public String splitY(int position)
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
