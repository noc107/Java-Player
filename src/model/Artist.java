/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 * @author Anibal Dominguez
 * @author Manuel Pacheco
 * @author José Suárez
 */
public class Artist 
{
    private String      name;
    private ListAlbum   albums;
    private int         artistID;
/**
 * Constructor of the Artist Class
 * @param name
 * @param artistID 
 */
    public Artist(String name, int artistID) 
    {
        this.name = name;
        this.albums = new ListAlbum(true,false,false);
        this.artistID = artistID;
    }

/**
 * Method to get the attribute.
 * @return 
 */
    public String getName() {
        return name;
    }
 /**
 * Method to set the attribute.
 * @return 
 */
    public void setName(String name) {
        this.name = name;
    }
/**
 * Method to get the attribute.
 * @return 
 */
    public ListAlbum getAlbums() {
        return albums;
    }
 /**
 * Method to set the attribute.
 * @return 
 */
    public void setAlbums(ListAlbum albums) {
        this.albums = albums;
    }
/**
 * Method to get the attribute.
 * @return 
 */
    public int getArtistId() {
        return artistID;
    }
 /**
 * Method to set the attribute.
 * @return 
 */
    public void setArtistId(int artistID) {
        this.artistID = artistID;
    }
/**
 * Override of the hash code of the Artist Class.
 * @return 
 */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.name);
        return hash;
    }
/**
 * Override of the equals method of the Artist Class.
 * @param obj
 * @return 
 */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Artist other = (Artist) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
}
