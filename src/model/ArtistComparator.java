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
public class ArtistComparator implements Comparator
{/**
 * Override of the Comparator to compare Artists
 * @param o1
 * @param o2
 * @return 
 */
    public int compare(Object o1, Object o2)
    {
        Artist ao1 = (Artist)   o1;
        Artist ao2 = (Artist)   o2;
        
        int result = ao1.getName().compareTo(ao2.getName());
        
        return result;
    }
}
