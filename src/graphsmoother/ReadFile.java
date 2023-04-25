package graphsmoother;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class reads the given .csv file passed in from the main method. It then 
 * reads the contents of the .csv file and adds it into an array. Finally it 
 * returns the array so it can be salted.
 * 
 * @author Patrick Trahan
 * @param <E>
 */
public class ReadFile<E>
{
    //global variables
    private BufferedReader reader;
    private boolean run = true;
    private E[] plotList;
    
    /**
     * This method takes in a file path and creates a BufferedReader to read
     * the contents of the file. For every iteration it will add it to the array.
     * 
     * @param userFile is the String value containing the file path.
     * @return the array containing the contents of the file.
     */
    public E[] readFile(String userFile)
    {
        try
        {
            //ceates the BufferedReader object to read the contents of the file
            reader = new BufferedReader(new FileReader(userFile));
            
            //skips the first line of the file
            reader.readLine();
            
            //loops till run becomes false
            while(run)
            {
                //temp is set to the line that is read from the file
                String temp = reader.readLine();
                
                //if temp is not null
                if(temp != null)
                {
                    //add the line to the plotList array
                    plotList = addElement(plotList, (E)temp);
                }
                //if its null then set run to false
                //assuming that the file is empty now
                else
                {
                    run = false;
                }
            }
            
            //closes the file
            reader.close();
        }
        catch(IOException e)
        {
            System.out.println("An error occurred.");
        }
        
        return plotList;
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