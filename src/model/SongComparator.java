/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Comparator;

/**
 * @author Anibal Dominguez
 * @author Manuel Pacheco
 * @author José Suárez
 */
public class SongComparator implements Comparator
{
/**
 * Override of the Comparator to compare songs
 * @param o1
 * @param o2
 * @return 
 */
    @Override
    public int compare(Object o1, Object o2) 
    {
        Song so1 = (Song) o1;
        Song so2 = (Song) o2;
        
        if(so1.getNumber() < so2.getNumber())
            return -1;
        
        return 1;
    }
    
}
