/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


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
public class SongXmlFile
{/**
 * Method to get the songs info in the xml
 * @param album 
 */
    public static void readAllSongsFromDataBase(Album album)
    {
        Song            song = null;
        Artist          artist = null;
        Document        doc;
        Element         root,child;
        List <Element>  rootChildrens;
        String          artistID, albumID, name, numberSong,gender;
        int             pos = 0;

        SAXBuilder      builder = new SAXBuilder();

        try
        {
            doc = builder.build(Util.SONG_XML_PATH);

            root = doc.getRootElement();

            rootChildrens = root.getChildren();

            while (pos < rootChildrens.size())
            {
                child = rootChildrens.get(pos);

                artistID    = child.getAttributeValue(Util.SONG_ARTIST_ID_TAG);
                albumID     = child.getAttributeValue(Util.SONG_ALBUM_ID_TAG);
                name        = child.getAttributeValue(Util.SONG_NAME_TAG);
                numberSong  = child.getAttributeValue(Util.SONG_NUMBER_TAG);
                gender      = child.getAttributeValue(Util.SONG_GENDER_TAG);
                                
                if(artistID != null && albumID != null && name != null && numberSong != null &&
                   UtilFunctions.stringToInteger(albumID) == album.getAlbumId() &&
                   album.getArtist().getArtistId() == UtilFunctions.stringToInteger(artistID))
                {
                    artist = ArtistXmlFile.getArtistFromDataBase(UtilFunctions.stringToInteger(artistID));
                    
                    if (artist != null)
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
      * Method to save the song info in the xml
      * @param song
      * @return 
      */
    public static boolean saveSongInDataBase(Song song)
    {
        Artist artist = new Artist ("Unknown Artist",1);
        
        Album album = new Album ("Unknown Album", artist, "5", "2012", " ", 1);
        
        SongXmlFile.readAllSongsFromDataBase(album);
        
        
            Document    doc;
            Element     root, newChild;

            SAXBuilder  builder = new SAXBuilder();

            try
            {
                doc = builder.build(Util.SONG_XML_PATH);

                root = doc.getRootElement();


                newChild = new Element(Util.SONG_TAG);


                newChild.setAttribute(Util.SONG_NAME_TAG, song.getName());
                newChild.setAttribute(Util.SONG_ALBUM_ID_TAG, UtilFunctions.integerToString(song.getAlbumId()));
                newChild.setAttribute(Util.SONG_ARTIST_ID_TAG, UtilFunctions.integerToString(song.getArtist().getArtistId()));
                newChild.setAttribute(Util.SONG_NUMBER_TAG, UtilFunctions.integerToString(song.getNumber()));
                newChild.setAttribute(Util.SONG_GENDER_TAG, song.getGender());
                
                root.addContent(newChild);

                try
                {
                    Format format = Format.getPrettyFormat();


                    XMLOutputter out = new XMLOutputter(format);

                    FileOutputStream file = new FileOutputStream(Util.SONG_XML_PATH);


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
    
    
}
