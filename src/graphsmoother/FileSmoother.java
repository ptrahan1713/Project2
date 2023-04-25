package graphsmoother;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Creates a csv file and pass in the values of the function into the 
 * file.
 * 
 * @author Patrick Trahan
 */
public class FileSmoother
{
    private FileWriter functionOutput;
    private FileWriter secondOutput;
    private FileWriter thirdOutput;
    private FileWriter fourthOutput;
    private BufferedWriter writer;
    private BufferedWriter secondWriter;
    private BufferedWriter thirdWriter;
    private BufferedWriter fourthWriter;
    
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
            functionOutput = new FileWriter("smoothFunction.csv");
            
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
    
    public void createRestFile()
    {
        try
        {
            secondOutput = new FileWriter("smoothFunction1.csv");
            
            thirdOutput = new FileWriter("smoothFunction2.csv");
            
            fourthOutput = new FileWriter("smoothFunction3.csv");
            
            secondWriter = new BufferedWriter(secondOutput);
                    
            thirdWriter = new BufferedWriter(thirdOutput);
            
            fourthWriter = new BufferedWriter(fourthOutput);
            
            secondWriter.write("x Values, y Values");
            
            secondWriter.newLine();
            
            thirdWriter.write("x Values, y Values");
            
            thirdWriter.newLine();
            
            fourthWriter.write("x Values, y Values");
            
            fourthWriter.newLine();
        }
        catch (IOException e)
        {
            System.out.println("An error occurred");
        }
    }
    public void passNextValue(String value, int file)
    {
        if(file == 0)
        {
            try
            {
                secondWriter.write(value);
                
                secondWriter.newLine();
            }
            catch (IOException e)
            {
                System.out.println("An error occurred");
            }
        }
        else if(file == 1)
        {
            try
            {
                thirdWriter.write(value);
                
                thirdWriter.newLine();
            }
            catch (IOException e)
            {
                System.out.println("An error occurred");
            }
        }
        else
        {
            try
            {
                fourthWriter.write(value);
                
                fourthWriter.newLine();
            }
            catch (IOException e)
            {
                System.out.println("An error occurred");
            }
        } 
    }
    
    /**
     * This method closes the files after you are done writing into them.
     * 
     * @throws IOException 
     */
    public void closeRestFile() throws IOException
    {
        secondWriter.close();
        
        thirdWriter.close();
        
        fourthWriter.close();
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
