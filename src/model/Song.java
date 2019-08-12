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
public class Song 
{
    private String name;
    private Artist artist;
    private int number;
    private int albumID;
    private String gender;
/**
 * Constructor of the Song Class
 * @param name
 * @param artist
 * @param number
 * @param albumID 
 */
    public Song(String name, Artist artist, int number, int albumID) {
        this.name = name;
        this.artist = artist;
        this.number = number;
        this.albumID = albumID;
    }
/**
 * Constructor of the Song Class.
 * @param name
 * @param artist
 * @param number
 * @param albumID
 * @param gender 
 */
    public Song(String name, Artist artist, int number, int albumID, String gender) {
        this.name = name;
        this.artist = artist;
        this.number = number;
        this.albumID = albumID;
        this.gender = gender;
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
    public Artist getArtist() {
        return artist;
    }
 /**
 * Method to set the attribute.
 * @return 
 */
    public void setArtist(Artist artist) {
        this.artist = artist;
    }
/**
 * Method to get the attribute.
 * @return 
 */
    public int getNumber() {
        return number;
    }
 /**
 * Method to set the attribute.
 * @return 
 */
    public void setNumber(int number) {
        this.number = number;
    }
/**
 * Method to get the attribute.
 * @return 
 */
    public int getAlbumId() {
        return albumID;
    }
 /**
 * Method to set the attribute.
 * @return 
 */
    public void setAlbumId(int albumID) {
        this.albumID = albumID;
    }
/**
 * Method to get the attribute.
 * @return 
 */
    public String getGender() {
        return gender;
    }
 /**
 * Method to set the attribute.
 * @return 
 */
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    

  
/**
 * Override of the hash code of the Song Class.
 * @return 
 */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.name);
        hash = 73 * hash + Objects.hashCode(this.artist);
        hash = 73 * hash + this.number;
        hash = 73 * hash + this.albumID;
        return hash;
    }

    /**
     * Override of the equals method of the Song Class.
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
        final Song other = (Song) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.artist, other.artist)) {
            return false;
        }
        if (!Objects.equals(this.albumID, other.albumID)) {
            return false;
        }
        return true;
    }
    
}
