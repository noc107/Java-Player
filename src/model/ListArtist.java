/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Anibal Dominguez
 * @author Manuel Pacheco
 * @author José Suárez
 */
public class ListArtist 
{
    SortedSet <Artist> list;
    ArtistComparator anc;
    

    public ListArtist()
    {
        anc = new ArtistComparator();
        list = new TreeSet(anc);
    }
    /**
     * Method to add an artist to the list
     * @param a
     * @return 
     */
    public boolean addArtist(Artist a)
    {
        return this.list.add(a);
    }
    /**
     * Method to remove an artist from the list
     * @param a
     * @return 
     */
    public boolean removeArtist(Artist a)
    {
        return this.list.remove(a);
    }
    
}
