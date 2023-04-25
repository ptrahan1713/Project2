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
 * This class will graph the original function using JFreeCharts. I used all of 
 * the methods and variables to utilize the JFreeCharts accessibility.
 * 
 * @author Patrick Trahan
 * @param <E> using generics
 */
public class OriginalGrapher<E> extends ApplicationFrame
{
    //global variables
    private XYSeries originalGraph;
    private XYSeriesCollection dataset = new XYSeriesCollection();
    
    /**
     * This method is the constructor for the OriginalGrapher class. This will
     * set up the graph.
     * 
     * @param applicationTitle is the name of the window that pops up.
     * @param chartTitle is the title of the Graph.
     * @param function is the Function object containing all of the components of the function.
     */
    public OriginalGrapher(String applicationTitle, String chartTitle, Function function)
    {
        //calls the super class of the ApplicationFrame 
        super(applicationTitle);
        
        //creates the JFreeChart object with all of the parameters
        JFreeChart graph = ChartFactory.createXYLineChart(chartTitle, "x-values", 
                "y-values", createDataset(function), PlotOrientation.VERTICAL, true, true, false);
        
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
        renderer.setSeriesPaint(0, Color.RED);
        
        //sets the size of the graph line
        renderer.setSeriesStroke(0, new BasicStroke(4.0f));
        
        //sets the renderer of the plot with the renderer
        plot.setRenderer(renderer);
        
        //sets the contentpane
        setContentPane(chartPanel);
    }
    
    /**
     * This method sets the function to the dataset.
     * 
     * @param function is the Function object.
     */
    public void setDataSet(Function function)
    {    
        //calls the setOriginal method
        setOriginal(function);
        
        //adds the originalGraph to the dataset
        dataset.addSeries(originalGraph);
    }
    
    /**
     * This method adds all of the contents of the function to the originalGraph
     * variable.
     * 
     * @param function is the Function object.
     */
    public void setOriginal(Function function)
    {
        //creates a XYSeries object named originalGraph
        originalGraph = new XYSeries("Orginal Graph");
        
        //sets the dataArray to the function array
        E[] dataArray = (E[]) function.getFunctionList();
        
        //loops through the length of dataArray
        for(int i = 0; i < dataArray.length; i++)
        {
            //splits the dataArray at the given position to a string array called temp
            String[] temp = splitValue(dataArray, i);
             
            //adds the x and y value to the original graph
            originalGraph.add(Double.parseDouble(temp[0]), Double.parseDouble(temp[1]));
        }
    }
    
    /**
     * This method calls the setDataSet method and the returns the dataset.
     * 
     * @param function is the Function object.
     * @return the created dataSet.
     */
    public XYDataset createDataset(Function function)
    {
        //calls the method to set the DataSet
        setDataSet(function);
        
        //returns the dataset
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
