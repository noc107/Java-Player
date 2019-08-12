/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import model.ArtistXmlFile;
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
public class AlbumXmlFile 
{  
    /**
     * Method to extract the album info in the xml
     * @param listAlbum
     * @param hasSongs 
     */
    public static void readAllAlbumsFromDataBase(ListAlbum listAlbum, boolean hasSongs)
    {
        Album           album = null;
        Artist          artist;
        Document        doc;
        Element         root,child;
        List <Element>  rootChildrens;
        String          albumID, artistID, name, year, score,genre;
        int             pos = 0;

        SAXBuilder      builder = new SAXBuilder();
       
        
        try
        {
            doc = builder.build(Util.ALBUM_XML_PATH);

            root = doc.getRootElement();

            rootChildrens = root.getChildren();

            while (pos < rootChildrens.size())
            {
                child = rootChildrens.get(pos);

                albumID     = child.getAttributeValue(Util.ALBUM_ID_TAG);
                artistID    = child.getAttributeValue(Util.ALBUM_ARTIST_ID_TAG);
                name        = child.getAttributeValue(Util.ALBUM_NAME_TAG);
                score        = child.getAttributeValue(Util.ALBUM_SCORE_TAG);
                year        = child.getAttributeValue(Util.ALBUM_YEAR_TAG);
                genre       = child.getAttributeValue(Util.ALBUM_GENDER_TAG);
                
                if(albumID != null && artistID != null && name != null && year != null)
                {
                    artist = ArtistXmlFile.getArtistFromDataBase(UtilFunctions.stringToInteger(artistID));
                    
                    if (artist != null)
                    {
                        album = new Album(name,artist,score,year,genre, UtilFunctions.stringToInteger(albumID));
                    
                        listAlbum.addAlbum(album);
                        
                        if (hasSongs)
                            SongXmlFile.readAllSongsFromDataBase(album);
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
    * Method to save the albums in the xml
    * @param album
    * @return 
    */
public static boolean saveAlbumInDataBase(Album album)
    {
        ListAlbum listAlbum = new ListAlbum(true,false,false);
        
        AlbumXmlFile.readAllAlbumsFromDataBase(listAlbum, true);
        
        if (listAlbum.isRepeated(album))
        {
            
            System.out.println("Album Repetido");
            return false;
        }
        
        else {
            

            Document    doc;
            Element     root, newChild;

            SAXBuilder  builder = new SAXBuilder();

            try
            {
                doc = builder.build(Util.ALBUM_XML_PATH);

                root = doc.getRootElement();

                
                newChild = new Element(Util.ALBUM_TAG);

                newChild.setAttribute(Util.ALBUM_ID_TAG, UtilFunctions.integerToString(album.getAlbumId()));
                newChild.setAttribute(Util.ALBUM_NAME_TAG, album.getName());
                newChild.setAttribute(Util.ALBUM_ARTIST_ID_TAG, UtilFunctions.integerToString(album.getArtist().getArtistId()));
                newChild.setAttribute(Util.ALBUM_YEAR_TAG, album.getYear());
                newChild.setAttribute(Util.ALBUM_SCORE_TAG, album.getScore());
                newChild.setAttribute(Util.ALBUM_GENDER_TAG, album.getGender());
                
                root.addContent(newChild);

                try
                {
                    Format format = Format.getPrettyFormat();

                    /* Se genera un flujo de salida de datos XML */
                    XMLOutputter out = new XMLOutputter(format);

                    /* Se asocia el flujo de salida con el archivo donde se guardaran los datos */
                    FileOutputStream file = new FileOutputStream(Util.ALBUM_XML_PATH);

                    /* Se manda el documento generado hacia el archivo XML */
                    out.output(doc,file);

                    /* Se limpia el buffer ocupado por el objeto file y se manda a cerrar el archivo */
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
        }
        return true;
    }
     /**
      * Method to get an album from the xml
      * @param albumName
      * @param bool
      * @return 
      */
     public static Album getAlbumFromDataBase(String albumName,boolean bool)
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
                            SongXmlFile.readAllSongsFromDataBase(album);
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
     
     
     
   
}
