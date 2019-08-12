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
public class ArtistAlbumComparator implements Comparator
{
/**
 * Override of the Comparator to compare albums ID and artists ID
 * @param o1
 * @param o2
 * @return 
 */
    @Override
    public int compare(Object o1, Object o2) 
    {
        Album ao1 = (Album) o1;
        Album ao2 = (Album) o2;
        
        int result = ao1.getArtist().getName().compareTo(ao2.getArtist().getName());
        
        if(result == 0)
        {
            result = ao1.getName().compareTo(ao2.getName());
        }
        
        return result;
    }
   
}
