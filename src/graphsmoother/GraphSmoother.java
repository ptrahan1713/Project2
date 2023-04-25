package graphsmoother; 

import java.io.IOException;

/**
 * This is the main method of the GraphSmoother project. The purpose of the project
 * is to take the saltedFunction.csv and smooth the function. It will take the 
 * average of the 3 values around the given value. Then pass the values into a new
 * file.
 * 
 * @author Patrick Trahan
 */
public class GraphSmoother
{
    public static void main(String[] args) throws IOException
    {
        Smoother run = new Smoother();
        
        run.run();
    }
    
}
