package graphwithlibrary;

/**
 * This program contains the values for x and y. They are set as generics
 * because the value the user passes could be a integer or a double.
 * 
 * @author Patrick Trahan
 * @param <E> is for generics.
 */
public class XandYValue<E>
{
    //global variables
    private E[] listPoint;
    
    /**
     * This will set the x and y value to a combined String and then add
     * the String to the array.
     * 
     * @param x is the generic value of x.
     * @param y is the generic value of y.
     */
    public void setPoint(E x, E y)
    {
        String temp;
        
        temp = x.toString() + ", " + y.toString();
        
        listPoint = addElement(listPoint, (E)((Object) temp));
    }
    
    /**
     * This method returns the whole list of points.
     * 
     * @return the generic array.
     */
    public E[] getList()
    {
        return listPoint;
    }
    
    /**
     * This method adds an element to the userList array.
     * 
     * @param userList is the given array.
     * @param element is the element getting adding to the array.
     * @return the new array.
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
