/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Anibal Dominguez
 * @author Manuel Pacheco
 * @author José Suárez
 */
public class ListSizePerRow
{
    List <Integer> list;
    
    /**
     * Method to List Size Per Row
     */
    public ListSizePerRow()
    {
        this.list = new ArrayList();
    }
    
    /**
     * Method to Add Size
     * @param size
     * @return
     */
    public boolean addSize(int size)
    {
        if (size <= 11)
            return this.list.add(205);
        
        return this.list.add(size*19);
    }
    
    /**
     * Method to go over the list
     * @return
     */
    public Iterator iterator()
    {
        return this.list.iterator();
    }
}
