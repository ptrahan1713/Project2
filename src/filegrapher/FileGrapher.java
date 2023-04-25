package filegrapher;

/**
 * This is the main method for the FileGrapher project. This purpose of the project
 * is to write into a .csv file. Inside the Function run method it calls the methods
 * to create the file. Then it loops the function to calculate the y value with 
 * the given x. After it increments the x value with the given increment value.
 * Further, once the loop is finished we export the values into the file to write
 * them into the file and then closes the file.
 * 
 * @author Patrick Trahan
 */
public class FileGrapher
{
    public static void main(String[] args)
    {
        Function funct = new Function();
        
        funct.run();
    }
    
}
