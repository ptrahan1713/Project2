package graphsalter;

import java.io.IOException;

/**
 * This is the main method of the Graph Salter project. The propose of the 
 * project is to take the function.csv of the FileGrapher project and salt the 
 * function. It will change the value of all of the y-values by either adding 
 * or subtracting a random number to the existing y-value. Finally, it creates 
 * a salter.csv file and plots the values. 
 * 
 * @author Patrick Trahan
 */
public class GraphSalter
{
    public static void main(String[] args) throws IOException
    {
        Salter saltGraph = new Salter();
        
        saltGraph.run();
    }
    
}
