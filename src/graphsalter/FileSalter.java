package graphsalter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Creates a csv file and pass in the values of the function into the 
 * file.
 * 
 * @author Patrick Trahan
 */
public class FileSalter
{
    private FileWriter functionOutput;
    private BufferedWriter writer;
    
    /**
     * This method creates the file called function.csv. From creating the file
     * it will set the first line to have two columns, first being x Values and 
     * second being y Values. If there is an error the catch will look for the 
     * IOException and output the error.
     */
    public void createFile()
    {
        //tries to create the file
        try
        {
            functionOutput = new FileWriter("saltedFunction.csv");
            
            writer = new BufferedWriter(functionOutput);
            
            writer.write("x Values, y Values");
            
            writer.newLine();
        }
        catch (IOException e)
        {
            System.out.println("An error occurred");
        }
    }
    
    /**
     * This method's goal is to take the x and y values and write them into the 
     * file. It takes in the value and converts it into a String and then writes 
     * it into the file.
     * 
     * @param value 
     */
    public void passValue(String value)
    {
        //tries to write into the file
        try
        {
            writer.write(value);
            
            writer.newLine();
        }
        catch (IOException e)
        {
            System.out.println("An error occurred");
        }
    }
    
    /**
     * This method closes the file after you are done writing into it.
     * 
     * @throws IOException 
     */
    public void closeFile() throws IOException
    {
        writer.close();
    }
}
