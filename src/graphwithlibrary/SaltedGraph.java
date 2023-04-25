package graphwithlibrary;

import java.awt.Color;
import java.awt.BasicStroke;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

/**
 * This class will graph the salted function using JFreeCharts. I used all of 
 * the methods and variables to utilize the JFreeCharts accessibility.
 * 
 * @author Patrick Trahan
 * @param <E> using generics
 */
public class SaltedGraph<E> extends ApplicationFrame
{
    //global variables
    private XYSeries saltedGraph;
    private XYSeriesCollection dataset = new XYSeriesCollection();
    
    /**
     * This method is the constructor for the SaltedGraph class. This will
     * set up the graph.
     * 
     * @param applicationTitle is the name of the window that pops up.
     * @param chartTitle is the title of the Graph.
     * @param salter is the Salter object.
     */
    public SaltedGraph(String applicationTitle, String chartTitle, Salter salter)
    {
        //calls the super class of the ApplicationFrame 
        super(applicationTitle);
        
        //creates the JFreeChart object with all of the parameters
        JFreeChart graph = ChartFactory.createXYLineChart(chartTitle, "x-values", 
                "y-values", createDataset(salter), PlotOrientation.VERTICAL, 
                true, true, false);
        
        //creates a ChartPanel object containing the graph variable
        ChartPanel chartPanel = new ChartPanel(graph);
        
        //sets the size of the chart window
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
        
        //creates the XYPlot object 
        //its for the xy graph
        final XYPlot plot = graph.getXYPlot();
        
        //creates the XYLineAndShapeRenderer object
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        
        //sets the color of the graph
        renderer.setSeriesPaint(1, Color.GREEN);
        
        //sets the size of the graph line
        renderer.setSeriesStroke(1, new BasicStroke(3.0f));
        
        //sets the renderer of the plot with the renderer
        plot.setRenderer(renderer);
        
        //sets the contentpane
        setContentPane(chartPanel);
    }
    
    /**
     * This method sets the function to the dataset.
     * 
     * @param salter is the Salter object.
     */
    public void setDataSet(Salter salter)
    {
        //sets the salter
        setSalter(salter);
        
        //adds the saltedGraph to the dataSet
        dataset.addSeries(saltedGraph);
    }
    
    /**
     * This method adds all of the contents of the function to the saltedGraph
     * variable.
     * 
     * @param salter is the Salter object.
     */
    public void setSalter(Salter salter)
    {
        //creates a XYSeries object named saltedGraph
        saltedGraph = new XYSeries("Salted Graph");
        
        //sets the dataArray to the salter array
        E[] dataArray = (E[]) salter.getSaltedList();
        
        //loops through the length of dataArray
        for(int i = 0; i < dataArray.length; i++)
        {
            //splits the dataArray at the given position to a string array called temp
            String[] temp = splitValue(dataArray, i);
            
            //adds the x and y value to the salted graph
            saltedGraph.add(Double.parseDouble(temp[0]), Double.parseDouble(temp[1]));
        }
    }
    
    /**
     * This method calls the setDataSet method and the returns the dataset.
     * 
     * @param salter is the Salter object.
     * @return the created dataSet.
     */
    public XYDataset createDataset(Salter salter)
    {
        //calls the method to set the dataSet
        setDataSet(salter);
        
        //returns the dataSet
        return dataset;
    }
    
    /**
     * This method will split the values of the array at the given position.
     * 
     * @param dataArray is the passed in array.
     * @param position is the position where its being split.
     * @return a string array of the x and y value of the dataArray.
     */
    public String[] splitValue(E[] dataArray, int position)
    {
        //creates a string that contains the x and y values of the given position
        String value = dataArray[position].toString();
        
        //creates a String array that contains a x-value and a y-value
        String[] splitValue = value.split(",");
        
        return splitValue;
    } 
}
