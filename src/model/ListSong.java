/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Anibal Dominguez
 * @author Manuel Pacheco
 * @author José Suárez
 */
public class ListSong 
{
    SortedSet <Song> list;
    SongComparator soc;
    
    public ListSong()
    {
        soc = new SongComparator();
        list = new TreeSet(soc);
    }
    /**
     * Method to add a song to the list
     * @param newSong
     * @return 
     */
    public boolean addSong(Song newSong)
    {
        return this.list.add(newSong);
    }
    /**
     * Method to remove a song of the list
     * @param newSong
     * @return 
     */
    public boolean removeSong(Song newSong)
    {
        return this.list.remove(newSong);
    }
    /**
     * Method to go over the list
     * @return 
     */
    public  Iterator iterator()
    {
        return this.list.iterator();
    }
    /**
     * Method to get the size of the list
     * @return size
     */
    public int size()
    {
        return this.list.size();
    }
    
}
