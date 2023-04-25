package graphwithlibrary;

import org.jfree.ui.RefineryUtilities;
        
/**
 * This class will create a Function, Salter, and Smoother object. It will call
 * the classes to solve the lists of each of the objects. After that it will create
 * Graph objects to output the results.
 * 
 * @author Patrick Trahan
 */
public class GraphWithLibrary
{
    //global variables
    private Function function = new Function();
    private Salter salter = new Salter();
    private Smoother smoother = new Smoother();
    private OriginalGrapher originalGraph;
    private SaltedGraph saltedGraph;
    private SmoothedGraph smoothedGraph;
    
    /**
     * This method will call the methods to run the whole program.
     */
    public void run()
    {
        //calls the run method from the function object to solve the function
        function.run();
        
        //calls the loopArray method from the salter class to solve the salter
        salter.loopArray(function.getFunctionList());
        
        //calls the run method from the smoother class to solve the smoother 
        smoother.run(salter.getSaltedList());
        
        //creates the graph objects and passes in all of the parameters
        originalGraph = new OriginalGrapher("Graphing with Libraries", "Function: x^2", function);
        
        saltedGraph = new SaltedGraph("Graphing with Libraries", "Salted of x^2", salter);
        
        smoothedGraph = new SmoothedGraph("Graphing with Libraries", "Smoothed of x^2", smoother);
        
        originalGraph.pack();
        saltedGraph.pack();
        smoothedGraph.pack();
        
        //sets the frames to the screen at the given horizontal position
        RefineryUtilities.positionFrameOnScreen(originalGraph, 0, 0.5);
        RefineryUtilities.positionFrameOnScreen(saltedGraph, 0.5, 0.5);
        RefineryUtilities.positionFrameOnScreen(smoothedGraph, 1, 0.5);
        
        //sets the graph to visible
        originalGraph.setVisible(true);
        saltedGraph.setVisible(true);
        smoothedGraph.setVisible(true);
    }
}
