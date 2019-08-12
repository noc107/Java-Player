/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 * 
 * @author Anibal Dominguez
 * @author Manuel Pacheco
 * @author José Suárez
 */
public class Album implements Comparable<Album>
{
    
    public final static int compareByAlbum = 1;
    public final static int compareByArtist = 2;
    public final static int compareByYear = 3;   
    public final static int compareByGender = 4;
    
    
    
    public static int compareBy = compareByAlbum;
    
    private int         albumID;
    private String      albumName;
    private Artist      artist;
    private ListSong    songs;
    private String      score;
    private String      year;
    private String      gender;
/**
 * Constructor of the Album class
 * @param name
 * @param artist
 * @param score
 * @param year
 * @param gender
 * @param albumID 
 */
    public Album(String albumName, Artist artist, String score, String year, String gender, int albumID) {
        this.albumName = albumName;
        this.artist = artist;
        this.songs = new ListSong();
        this.score = score;
        this.year = year;
        this.gender = gender;
        this.albumID = albumID;
    }
/**
 * Constructor of the Album class
 * @param name
 * @param artist
 * @param songs
 * @param score
 * @param year
 * @param gender
 * @param albumID 
 */
    public Album(String albumName, Artist artist, ListSong songs, String score, String year, String gender, int albumID) {
        this.albumName = albumName;
        this.artist = artist;
        this.songs = songs;
        this.score = score;
        this.year = year;
        this.gender = gender;
        this.albumID = albumID;
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
    public String getName() {
        return albumName;
    }
 /**
 * Method to set the attribute.
 * @return 
 */
    public void setName(String albumName) {
        this.albumName = albumName;
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
    public ListSong getSongs() {
        return songs;
    }
 /**
 * Method to set the attribute.
 * @return 
 */
    public void setSongs(ListSong song) {
        this.songs = songs;
    }
/**
 * Method to get the attribute.
 * @return 
 */
    public String getScore() {
        return score;
    }
 /**
 * Method to set the attribute.
 * @return 
 */
    public void setScore(String score) {
        this.score = score;
    }
/**
 * Method to get the attribute.
 * @return 
 */
    public String getYear() {
        return year;
    }
 /**
 * Method to set the attribute.
 * @return 
 */
    public void setYear(String year) {
        this.year = year;
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
 * Override of the hash code of the Album Class.
 * @return 
 */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.albumName);
        hash = 47 * hash + Objects.hashCode(this.artist);
        hash = 47 * hash + Objects.hashCode(this.year);
        hash = 47 * hash + Objects.hashCode(this.gender);
        return hash;
    }
/**
 * Override of the equals class of the Album Class.
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
        final Album other = (Album) obj;
        if (this.albumID != other.albumID) {
            return false;
        }
        if (!Objects.equals(this.albumName, other.albumName)) {
            return false;
        }
        if (!Objects.equals(this.artist, other.artist)) {
            return false;
        }
        if (this.score != other.score) {
            return false;
        }
        if (!Objects.equals(this.year, other.year)) {
            return false;
        }
        if (!Objects.equals(this.gender, other.gender)) {
            return false;
        }
        return true;
    }
/**
 * Override of the compareTo method of the Album Class.
 * @param o
 * @return 
 */
    @Override
    public int compareTo(Album o)
    { 
        if (o == null)
            return 0;
        
        switch(compareBy)
        {
            case (1): 
                if(albumName == null || o.albumName == null)
                    return 0;
                return albumName.compareTo(o.albumName);
            
            case (2): 
                if(artist.getName() == null || o.artist.getName() == null)
                    return 0;
                return artist.getName().compareTo(o.artist.getName());
                
            case (3): 
                if(year == null || o.year == null)
                    return 0;
                return  year.compareTo(o.year);

            case (4): 
                if(gender == null || o.gender == null)
                    return 0;
                return gender.compareTo(o.gender);
        }
        return 0;
    }
   
}
