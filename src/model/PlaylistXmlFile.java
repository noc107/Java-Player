/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.JSoundsMainWindowViewController;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.JDOMParseException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * @author Anibal Dominguez
 * @author Manuel Pacheco
 * @author José Suárez
 */
public class PlaylistXmlFile {
    /**
     * Read all playlist from the xml files.
     * @param listAlbum
     * @param hasSongs 
     */
       public static void readAllAlbumsListFromDataBase(ListAlbum listAlbum, boolean hasSongs)
    {
        
        Album           album = null;
        Artist          artist;
        Document        doc;
        Element         root,child;
        List <Element>  rootChildrens;
        String          albumID, artistID, name, year, score,gender;
        int             pos = 0;

        SAXBuilder      builder = new SAXBuilder();
       
        
        try
        {
            doc = builder.build(Util.ALBUM_XML_PATH);

            root = doc.getRootElement();

            rootChildrens = root.getChildren();

            //while (pos < rootChildrens.size())
            {
                child = rootChildrens.get(pos);

                albumID     = child.getAttributeValue(Util.ALBUM_ID_TAG);
                artistID    = child.getAttributeValue(Util.ALBUM_ARTIST_ID_TAG);
                name        = child.getAttributeValue(Util.ALBUM_NAME_TAG);
                score        = child.getAttributeValue(Util.ALBUM_SCORE_TAG);
                year        = child.getAttributeValue(Util.ALBUM_YEAR_TAG);
                gender       = child.getAttributeValue(Util.ALBUM_GENDER_TAG);
                
                if(albumID != null && artistID != null && name != null && year != null)
                {
                    artist = ArtistXmlFile.getArtistFromDataBase(UtilFunctions.stringToInteger(artistID));
                    
                    if (artist != null)
                    {
                        album = new Album(name,artist,score,year,gender, UtilFunctions.stringToInteger(albumID));
                    
                        listAlbum.addAlbum(album);
                        
                        if (hasSongs)
                            readAllPlaylistSongsFromDataBase(album);
                    }
                }
                else
                {
                    if (name == null)
                        System.out.println(Util.ERROR_ALBUM_NAME_TAG);

                    if (artistID == null)
                        System.out.println(Util.ERROR_ALBUM_ARTIST_ID_TAG);
                }
                
                pos++;
            }
        }
        catch(JDOMParseException e)
        {
            System.out.println(Util.ERROR_XML_EMPTY_FILE);
            e.printStackTrace();
        }
        catch(JDOMException e)
        {
            System.out.println(Util.ERROR_XML_PROCESSING_FILE);
            e.printStackTrace();
        }
        catch(IOException e)
        {
            System.out.println(Util.ERROR_XML_PROCESSING_FILE);
            e.printStackTrace();
        }
          
        
        
    }
       /**
        * Read all the playlist from the xml file.
        * @param album 
        */
           public static void readAllPlaylistSongsFromDataBase(Album album)
    {
        Song            song = null;
        Artist          artist = null;
        Document        doc;
        Element         root,child;
        List <Element>  rootChildrens;
        String          artistID, albumID, name, numberSong,playlistID, gender;
        int             pos = 0;

        SAXBuilder      builder = new SAXBuilder();

        try
        {
            doc = builder.build(Util.PLAYLIST_XML_PATH);

            root = doc.getRootElement();

            rootChildrens = root.getChildren();

            while (pos < rootChildrens.size())
            {
                child = rootChildrens.get(pos);

                artistID    = child.getAttributeValue(Util.SONG_ARTIST_ID_TAG);
                albumID     = child.getAttributeValue(Util.SONG_ALBUM_ID_TAG);
                name        = child.getAttributeValue(Util.SONG_NAME_TAG);
                numberSong  = child.getAttributeValue(Util.SONG_NUMBER_TAG);
                playlistID  = child.getAttributeValue(Util.SONG_PLAYLIST_ID_TAG);
                gender      = child.getAttributeValue(Util.SONG_GENDER_TAG);
                                
                if(artistID != null && albumID != null && name != null && numberSong != null &&
                   UtilFunctions.stringToInteger(albumID) == album.getAlbumId() &&
                   album.getArtist().getArtistId() == UtilFunctions.stringToInteger(artistID))
                {
                    artist = ArtistXmlFile.getArtistFromDataBase(UtilFunctions.stringToInteger(artistID));
                    
                    if ((artist != null) && (UtilFunctions.stringToInteger(playlistID)==(JSoundsMainWindowViewController.playlistSelectedInCombo)))
                    {
                        song = new Song(name, artist, UtilFunctions.stringToInteger(numberSong), UtilFunctions.stringToInteger(albumID),gender);
                        album.getSongs().addSong(song);
                    }
                }
                else
                {
                    
                }
                
                pos++;
            }
        }
        catch(JDOMParseException e)
        {
            System.out.println(Util.ERROR_XML_EMPTY_FILE);
            e.printStackTrace();
        }
        catch(JDOMException e)
        {
            System.out.println(Util.ERROR_XML_PROCESSING_FILE);
            e.printStackTrace();
        }
        catch(IOException e)
        {
            System.out.println(Util.ERROR_XML_PROCESSING_FILE);
            e.printStackTrace();
        }
    }
           
           
      /**
       * Save the playlist in the xml file.
       * @param song
       * @param playlistNumber
       * @return 
       */     
           public static boolean savePlaylistSongsInDataBase(Song song,int playlistNumber)
    {
        Artist artist = new Artist ("Unknown Artist",1);
        
        Album album = new Album ("Unknown Album", artist, "5", "2012", " ", 1);
        
        SongXmlFile.readAllSongsFromDataBase(album);
        
        
            Document    doc;
            Element     root, newChild;

            SAXBuilder  builder = new SAXBuilder();

            try
            {
                doc = builder.build(Util.PLAYLIST_XML_PATH);

                root = doc.getRootElement();


                newChild = new Element(Util.SONG_TAG);


                newChild.setAttribute(Util.SONG_NAME_TAG, song.getName());
                newChild.setAttribute(Util.SONG_ALBUM_ID_TAG, UtilFunctions.integerToString(song.getAlbumId()));
                newChild.setAttribute(Util.SONG_ARTIST_ID_TAG, UtilFunctions.integerToString(song.getArtist().getArtistId()));
                newChild.setAttribute(Util.SONG_NUMBER_TAG, UtilFunctions.integerToString(song.getNumber()));
                newChild.setAttribute(Util.SONG_PLAYLIST_ID_TAG, UtilFunctions.integerToString(playlistNumber));
                newChild.setAttribute(Util.SONG_GENDER_TAG, song.getGender());
                root.addContent(newChild);

                try
                {
                    Format format = Format.getPrettyFormat();


                    XMLOutputter out = new XMLOutputter(format);

                    FileOutputStream file = new FileOutputStream(Util.PLAYLIST_XML_PATH);


                    out.output(doc,file);


                    file.flush();
                    file.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            catch(JDOMParseException e)
            {
                System.out.println(Util.ERROR_XML_EMPTY_FILE);
                e.printStackTrace();
            }
            catch(JDOMException e)
            {
                System.out.println(Util.ERROR_XML_PROCESSING_FILE);
                e.printStackTrace();
            }
            catch(IOException e)
            {
                System.out.println(Util.ERROR_XML_PROCESSING_FILE);
                e.printStackTrace();
            }
        
        return true;
    }
           
           /**
            * Get the selected Playlist from the xml file.
            * @param albumName
            * @param bool
            * @return 
            */
            public static Album getPlaylistAlbumFromDataBase(String albumName,boolean bool)
    {
        Document        doc;
        Element         root,child;
        List <Element>  rootChildrens;
        String          albumID, name, artistID, year,score, genre;          
        boolean         found = false;
        int             pos = 0;
        Artist          artist = null;
        Album           album = null;

        SAXBuilder      builder = new SAXBuilder();

        try
        {
            doc = builder.build(Util.ALBUM_XML_PATH);

            root = doc.getRootElement();

            rootChildrens = root.getChildren();

            while (!found && pos < rootChildrens.size())
            {
                child = rootChildrens.get(pos);

                albumID     = child.getAttributeValue(Util.ALBUM_ID_TAG);
                artistID    = child.getAttributeValue(Util.ALBUM_ARTIST_ID_TAG);
                name        = child.getAttributeValue(Util.ALBUM_NAME_TAG);
                score        = child.getAttributeValue(Util.ALBUM_SCORE_TAG);
                year        = child.getAttributeValue(Util.ALBUM_YEAR_TAG);
                genre       = child.getAttributeValue(Util.ALBUM_GENDER_TAG);
                
                
                if(name != null && artistID != null && year != null && name.compareTo(albumName) == 0)
                {
                    artist = ArtistXmlFile.getArtistFromDataBase(UtilFunctions.stringToInteger(artistID));
                    
                    if (artist != null)
                    {
                        found = true;
                        album = new Album(name,artist,score,year,genre, UtilFunctions.stringToInteger(albumID));
                    }
                    
                    
                        if (bool)
                            PlaylistXmlFile.readAllPlaylistSongsFromDataBase(album);
                }
                else
                {
                    pos++;
                }
            }
        }
        catch(JDOMParseException e)
        {
            System.out.println(Util.ERROR_XML_EMPTY_FILE);
            e.printStackTrace();
        }
        catch(JDOMException e)
        {
            System.out.println(Util.ERROR_XML_PROCESSING_FILE);
            e.printStackTrace();
        }
        catch(IOException e)
        {
            System.out.println(Util.ERROR_XML_PROCESSING_FILE);
            e.printStackTrace();
        }

        return album;
    }
            
  /**
   * Get the number of playlist loaded.
   * @return 
   */      
   public static int getNumberOfPlaylist()
    {
        Document        doc;
        Element         root,child;
        List <Element>  rootChildrens;
        String          playlistID;
        int             pos = 0;
        
        int numberOfPlaylist=0;
        SAXBuilder      builder = new SAXBuilder();
        

        try
        {
            doc = builder.build(Util.PLAYLIST_XML_PATH);

            root = doc.getRootElement();

            rootChildrens = root.getChildren();
            

            while (pos < rootChildrens.size())
            {
                child = rootChildrens.get(pos);

                playlistID  = child.getAttributeValue(Util.SONG_PLAYLIST_ID_TAG);
                
                if ((UtilFunctions.stringToInteger(playlistID)) > numberOfPlaylist)
                    numberOfPlaylist=(UtilFunctions.stringToInteger(playlistID));

                
                pos++;
            }
        }
        catch(JDOMParseException e)
        {
            System.out.println(Util.ERROR_XML_EMPTY_FILE);
            e.printStackTrace();
        }
        catch(JDOMException e)
        {
            System.out.println(Util.ERROR_XML_PROCESSING_FILE);
            e.printStackTrace();
        }
        catch(IOException e)
        {
            System.out.println(Util.ERROR_XML_PROCESSING_FILE);
            e.printStackTrace();
        }
        return (numberOfPlaylist);
    }
            
}
