package graphsalter;
        
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * This class will create a file path to the function.csv file. It will call a 
 * method to read the contents of the file so we can edit the values. We loop 
 * through the array and edit the y-value for every iteration in the loop. Finally,
 * it will create a new .csv file and pass in the x and new y values into the file.
 * 
 * @author Patrick Trahan
 * 
 * @param <E> using generics.
 */
public class Salter<E>
{
    //global variables
    private ReadFile file = new ReadFile();
    private FileSalter fileSalter = new FileSalter();
    private Random rand = new Random();
    private E[] plotArray;
    private final int range = 1000;
    private double changedY;
    private String userDir;
    private String[] userDirList;
    private String filePath;
    
    /**
     * This method runs the project for the user. It passes in the file path 
     * to read the contents of the file. Next, it creates the file. Then, loops
     * through the contents of the file to edit the y-value and finally it closes
     * the file.
     * 
     * @throws IOException 
     */
    public void run() throws IOException
    {
        //creates the file path to the orginal .csv file
        findPath();
        
        //creates an array that holds the x and y values of the file
        plotArray = (E[]) file.readFile(filePath);
        
        //creates the file
        fileSalter.createFile();
        
        //calls the method to edit the y-value
        loopArray();
        
        //closes the file
        fileSalter.closeFile();
    }
    
    /**
     * This method will get the file path of this program. Next, it separates the
     * String of the filePath to move out of the directory. Now it sets the file 
     * path to the FileGrapher/function.csv at the end of the file path. Finally,
     * it validates to make sure the function.csv file exists. If it does not exists
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
        
        for(int i = 0; i < userDirList.length - 1; i++)
        {
            filePath = filePath + userDirList[i] + "/";
        }
        
        //adds the string of the rest of the file path to the existing file path
        filePath = filePath + "FileGrapher/function.csv";
        
        //validates to see if the file path exists
        if(!((new File(filePath)).exists()))
        {
            System.out.println("The file does not exist");
            System.out.println("Ending the program and create the orginal csv file");
            System.exit(0);
        }
    }
    
    /**
     * This method loops through the array and edits the y-value in every 
     * iteration of the loop. Then passes the x and y values to the file.
     */
    public void loopArray()
    {
        for(int i = 0; i < plotArray.length; i++)
        {
            changedY = randomizeY(i);
            
            passToFile(changedY, i);
        }
    }
    
    /**
     * This method randomizes the y-value. Calls the appropriate method if you 
     * are adding or subtracting from the y-value.
     * 
     * @param position is the value of where you are in the array.
     * @return the new y-value.
     */
    public double randomizeY(int position)
    {
        //generates a random number from 0 or 1
        int temp = rand.nextInt(2);
        
        //if the number is 0 then we are adding the random number to the y-value
        if(temp == 0)
        {
            return addY(position);
        }
        
        //we are subtracting the random number from the y-value
        return subtractY(position);
    }
    
    /**
     * This method will create a random number from the range of 0 to 99. Then, 
     * it will add the random number to the y-value. 
     * 
     * @param position is the value of where you are in the array.
     * @return the new y-value.
     */
    public double addY(int position)
    {
        //generates the random number
        int temp = rand.nextInt(range);
        
        //calls the method to get the y-value at the given position
        //then parses it to a double
        double yValue = Double.parseDouble(splitY(position));
        
        //adds the random number to the original y-value
        yValue = yValue + temp;
        
        //returns the new y-value
        return yValue;
    }
    
    /**
     * This method will create a random number from the range of 0 to 99. Then,
     * it will subtract the random number to the y-value.
     * 
     * @param position is the value of where you are in the array.
     * @return the new y-value.
     */
    public double subtractY(int position)
    {
        //generates the random number
        int temp = rand.nextInt(range);
        
        //calls the method to get teh y-value at the given position
        //then parses it to a double
        double yValue = Double.parseDouble(splitY(position));
        
        //subtracts the random number from the y-value
        yValue = yValue - temp;
        
        //returns the new y-value
        return yValue;
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
     * This method will take the y-value and the position of the array. Then, 
     * pass the x and y values into the file.
     * 
     * @param userYValue is the newly created y-value.
     * @param position is the value of where you are in the array.
     */
    public void passToFile(double userYValue, int position)
    {
        //calls the method to get the x-value at the given position
        String xValue = splitX(position);
        
        //converts the y-value to a String 
        String yValue = Double.toString(userYValue);
        
        //concatenates the x and y value with a ","
        String value = xValue + ", " + yValue;
        
        //passes the value into the file
        fileSalter.passValue(value);
    }
    
}
