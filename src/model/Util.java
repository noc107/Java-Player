/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
/**
 * @author Anibal Dominguez
 * @author Manuel Pacheco
 * @author José Suárez
 */
public class Util
{/**
 * Tags of the xml files
 */
    public static final String ERROR_XML_EMPTY_FILE = "Error loading XML File - The file is empty";
    public static final String ERROR_XML_PROCESSING_FILE = "Error loading XML - It's not possible porcessing the file";
    
    public static final String ARTIST_TAG = "artist";
    public static final String ARTIST_NAME_TAG = "name";
    public static final String ARTIST_ID_TAG = "artistID";
    
    public static final String ERROR_ARTIST_NAME_TAG = "Error loading Artist from XML - Error in the attribute " + ARTIST_NAME_TAG + "of the XML tag";
    public static final String ERROR_ARTIST_ID_TAG = "Error loading Artist from XML -  Error in the attribe " + ARTIST_ID_TAG + "of the XML tag";
    
    public static final String SONG_TAG = "song";
    public static final String SONG_NAME_TAG = "name";
    public static final String SONG_ALBUM_ID_TAG = "albumID";
    public static final String SONG_ARTIST_ID_TAG = "artistID";
    public static final String SONG_NUMBER_TAG = "number";
    public static final String SONG_PLAYLIST_ID_TAG= "playlistID";
    public static final String SONG_GENDER_TAG= "gender";
    
    public static final String ERROR_SONG_NAME_TAG = "Error loading Student from XML - Error in the atribute" + SONG_NAME_TAG + "of the XML tag";
    
    public static final String ALBUM_TAG = "album";
    public static final String ALBUM_ID_TAG = "albumID";
    public static final String ALBUM_NAME_TAG = "name";
    public static final String ALBUM_ARTIST_ID_TAG = "artistID";
    public static final String ALBUM_YEAR_TAG = "year";
    public static final String ALBUM_SCORE_TAG = "score";
    public static final String ALBUM_GENDER_TAG = "gender";
    
    public static final String ERROR_ALBUM_NAME_TAG = "Error loading Artist from XML - Error in the attribute " + ALBUM_NAME_TAG + " of the XML tag";
    public static final String ERROR_ALBUM_ARTIST_ID_TAG= "Error loading Artist from XML - Error in the attribute " + ALBUM_ARTIST_ID_TAG + " of the XML tag";
    public static final String ERROR_ALBUM_YEAR_TAG= "Error loading Artist from XML - Error in the attribute " + ALBUM_YEAR_TAG + " of the XML tag";

    public static final String ARTIST_XML_PATH = "src/model/artist.xml";
    public static final String ALBUM_XML_PATH = "src/model/album.xml";
    public static final String SONG_XML_PATH = "src/model/song.xml";
    public static final String PLAYLIST_XML_PATH = "src/model/playlist.xml";
    

    public static final String JSOUNDS_LIBRARY_PATH = "C:/Users/AEDM/Desktop/jSounds Library/"; 
}
