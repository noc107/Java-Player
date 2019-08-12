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
public class ListAlbum 
{
    SortedSet <Album> listAlbum;
    ArtistAlbumComparator sortedComparator;
   
    
    public ListAlbum(boolean orderByArtist, boolean orderByAlbum, boolean orderByYear)
    {
        sortedComparator = new ArtistAlbumComparator();
        listAlbum = new TreeSet();
    }
    
     
    /**
     * Method to check if an album is already in the list
     * @param album
     * @return boolean
     */
     public boolean isRepeated(Album album)
     {
        Iterator    iterator;
        Album     actualAlbum;
        boolean     rep = false;
        
        iterator = this.listAlbum.iterator();
        
        while (iterator.hasNext())
        {
            actualAlbum = (Album) iterator.next();
            if ( (actualAlbum.getName().equals(album.getName())) && ( actualAlbum.getAlbumId()==(album.getAlbumId())) && ((actualAlbum.getGender()).equals(album.getGender())))
            {
                
               rep = true; 
            }
        }
         
       return rep;
     }
    
    /**
     * Method to add an album to the list
     * @param newAlbum
     * @return 
     */
    public boolean addAlbum(Album newAlbum)
    {
        return this.listAlbum.add(newAlbum);
    }
    /**
     * Method to Remove an album from the list
     * @param newAlbum
     * @return 
     */
    public boolean removeAlbum(Album newAlbum)
    {
        return this.listAlbum.remove(newAlbum);
    }
    /**
     * Method to go over the list
     * @return 
     */
    public Iterator iterator()
    {
        return this.listAlbum.iterator();
    }
    /**
     * Method to get the size of the list
     * @return 
     */
    public int size()
    {
        return this.listAlbum.size();
    }
}
