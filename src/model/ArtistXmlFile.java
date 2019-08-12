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
public class ArtistXmlFile
{   
       /**
        * Method to read the artist info in the xml
        * @param listArtist 
        */
    public static void readAllArtistFromDataBase(ListArtist listArtist)
    {
        Artist          artist = null;
        Document        doc;
        Element         root,child;
        List <Element>  rootChildrens;
        String          artistID, name;
        int             pos = 0;

        SAXBuilder      builder = new SAXBuilder();

        try
        {
            doc = builder.build(Util.ARTIST_XML_PATH);

            root = doc.getRootElement();

            rootChildrens = root.getChildren();

            while (pos < rootChildrens.size())
            {
                child = rootChildrens.get(pos);

                artistID          = child.getAttributeValue(Util.ARTIST_ID_TAG);
                name        = child.getAttributeValue(Util.ARTIST_NAME_TAG);

                if(artistID != null && name != null)
                {
                    artist = new Artist(name ,UtilFunctions.stringToInteger(artistID));
                    
                    listArtist.addArtist(artist);
                }
                else
                {
                    if (name == null)
                        System.out.println(Util.ERROR_ARTIST_NAME_TAG);

                    if (artistID == null)
                        System.out.println(Util.ERROR_ARTIST_ID_TAG);
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
     * Method to save the artist info in the xml
     * @param artist
     * @return 
     */
    public static boolean saveArtistInDataBase(Artist artist)
    {
        Document    doc;
        Element     root, newChild;

        SAXBuilder  builder = new SAXBuilder();

        try
        {
            doc = builder.build(Util.ARTIST_XML_PATH);

            root = doc.getRootElement();

            // Creamos una nueva etiqueta
            newChild = new Element(Util.ARTIST_TAG);

            newChild.setAttribute(Util.ARTIST_NAME_TAG, artist.getName());
            newChild.setAttribute(Util.ARTIST_ID_TAG, UtilFunctions.integerToString(artist.getArtistId()));
            

            // La añadimos como hija a una etiqueta ya existente
            root.addContent(newChild);

            try
            {
                Format format = Format.getPrettyFormat();

                /* Se genera un flujo de salida de datos XML */
                XMLOutputter out = new XMLOutputter(format);

                /* Se asocia el flujo de salida con el archivo donde se guardaran los datos */
                FileOutputStream file = new FileOutputStream(Util.ARTIST_XML_PATH);

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

        return true;
    }
    /**
     * Method to get the wanted artist info from the xml
     * @param idArtist
     * @return 
     */
    public static Artist getArtistFromDataBase(int idArtist)
    {
        Document        doc;
        Element         root,child;
        List <Element>  rootChildrens;
        String          name, artistID;
        boolean         found = false;
        int             pos = 0;
        Artist          artist = null;

        SAXBuilder      builder = new SAXBuilder();

        try
        {
            doc = builder.build(Util.ARTIST_XML_PATH);

            root = doc.getRootElement();

            rootChildrens = root.getChildren();

            while (!found && pos < rootChildrens.size())
            {
                child = rootChildrens.get(pos);

                artistID              = child.getAttributeValue(Util.ARTIST_ID_TAG);
                name            = child.getAttributeValue(Util.ARTIST_NAME_TAG);
                
                if(name != null && artistID != null && idArtist == UtilFunctions.stringToInteger(artistID))
                {
                    found = true;
                    artist = new Artist(name , idArtist);
                }
                else
                {
                    if (name == null)
                        System.out.println(Util.ERROR_ARTIST_NAME_TAG);
                    
                    if (artistID == null)
                        System.out.println(Util.ERROR_ARTIST_ID_TAG);

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

        return artist;
    }
}
