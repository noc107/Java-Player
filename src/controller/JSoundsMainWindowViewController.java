/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import jmusic.JMusicPlayerList;
import jmusic.JMusicSong;
import jmusic.MusicPlayerControl;
import jmusic.player.view.components.JMusicList;
import model.Album;
import model.AlbumXmlFile;
import model.Artist;
import model.ListAlbum;
import model.PlaylistXmlFile;
import model.Song;
import model.Util;
import model.UtilFunctions;
import view.AboutWindow;
import view.EditSongWindow;
import view.JLabelTableRenderer;
import view.JListTableEditor;
import view.JListTableRenderer;
import view.JSoundsMainWindow;
import view.MyOwnJFrame;
import view.PlaylistChooser;
import view.ViewUtilFunctions;

/**
 * @author Anibal Dominguez
 * @author Manuel Pacheco
 * @author José Suárez
 */
public class JSoundsMainWindowViewController
{
    private static final int ALBUM_COVER_COLUMN         = 0;
    private static final int ALBUM_INFO_COLUMN          = 1;
    private static final int NUMBER_SONG_COLUMN         = 2;
    private static final int LIST_SONG_COLUMN           = 3;
    private static final int GENDER_SONG_COLUMN         = 4;
    private static final int LAST_PLAYED_SONG_COLUMN    = 5;
    private static final int TOTAL_COLUMNS              = 6;
        
    private static final String ALBUM_COLUMN_NAME               = "Album";
    private static final String ALBUM_INFO_COLUMN_NAME          = "";
    private static final String NUMBER_SONG_COLUMN_NAME         = "#";
    private static final String LIST_SONG_COLUMN_NAME           = "Name";
    private static final String GENDER_SONG_COLUMN_NAME         = "Gender";
    private static final String LAST_PLAYED_SONG_COLUMN_NAME    = "Last played";
        
    private static String[] columnNames = {JSoundsMainWindowViewController.ALBUM_COLUMN_NAME,
                                JSoundsMainWindowViewController.ALBUM_INFO_COLUMN_NAME,
                                JSoundsMainWindowViewController.NUMBER_SONG_COLUMN_NAME,
                                JSoundsMainWindowViewController.LIST_SONG_COLUMN_NAME,
                                JSoundsMainWindowViewController.GENDER_SONG_COLUMN_NAME,
                                JSoundsMainWindowViewController.LAST_PLAYED_SONG_COLUMN_NAME};
    
    private static JTable table;
    
    private static JSoundsMainWindow jSoundsMainWindow;        
    private static JPanel jPListContainer;
    private static JLabel jLInformationSong;
    private static JButton jBPlayButton;
    private static JToggleButton orderByAlbum;
    private static JToggleButton orderByArtist;
    private static JToggleButton orderByYear;
    private static JFileChooser jFileChooser1;
    private static MyOwnJFrame actualWindow;
    private static JFileChooser jfcOneFile;
    private static JToggleButton jtbRepeatSongs;
    public static boolean isPlaylist=false;
    
    public static int [] numberOfPlaylist;
    public static int playlistSelectedInCombo;
    private static String genderGenius;
    public static String songName;
    
    
    private static JMusicList jlActualListSongs;
    private static boolean isSelected;
    private static boolean iAmPlaying;
    private static boolean iAmPausing;
    private static boolean iAmResuming;
    private static boolean fixedIndex = false;
    public static boolean alreadyPlaying = false;
    
    private static ListSizePerRow listSizePerRow;
    
    
    /**
     * Method to inicialize the components of the main JFrame
     * @param jPListContainer
     * @param jLInformationSong
     * @param jBPlayButton
     * @param orderByAlbum
     * @param orderByArtist
     * @param orderByYear
     * @param jFileChooser1
     * @param actualWindow
     * @param jcmbiRepeatFromTheBeginning
     */
    public static void initOutlets(JPanel jPListContainer, JLabel jLInformationSong, JButton jBPlayButton, JToggleButton orderByAlbum, JToggleButton orderByArtist,
            JToggleButton orderByYear, JFileChooser jFileChooser1, MyOwnJFrame actualWindow, JFileChooser jfcOneFile, JToggleButton jtbRepeatSongs)
    {
        JSoundsMainWindowViewController.jPListContainer = jPListContainer;
        JSoundsMainWindowViewController.jLInformationSong = jLInformationSong;
        JSoundsMainWindowViewController.jBPlayButton = jBPlayButton;
        JSoundsMainWindowViewController.orderByAlbum = orderByAlbum;
        JSoundsMainWindowViewController.orderByArtist = orderByArtist;
        JSoundsMainWindowViewController.orderByYear = orderByYear;
        JSoundsMainWindowViewController.jFileChooser1 = jFileChooser1;   
        JSoundsMainWindowViewController.jlActualListSongs = null;
        JSoundsMainWindowViewController.iAmPlaying = true;
        JSoundsMainWindowViewController.iAmPausing = false;
        JSoundsMainWindowViewController.iAmResuming = false;   
        JSoundsMainWindowViewController.actualWindow = actualWindow;
        JSoundsMainWindowViewController.jtbRepeatSongs = jtbRepeatSongs;
        JSoundsMainWindowViewController.jtbRepeatSongs.setSelected(false);       
        JSoundsMainWindowViewController.jfcOneFile = jfcOneFile; 
    }
    
    
    /**
     *  Method to Set the Toggle Buttons To False or True
     * @param orderByArtist
     * @param orderByAlbum
     * @param orderByYear 
     */
    private static void updateOrderButtons(boolean orderByArtist, boolean orderByAlbum, boolean orderByYear)
    {
        JSoundsMainWindowViewController.orderByAlbum.setSelected(false);
        JSoundsMainWindowViewController.orderByArtist.setSelected(false);
        JSoundsMainWindowViewController.orderByYear.setSelected(false);
            
        if (orderByAlbum)
        {
            JSoundsMainWindowViewController.orderByAlbum.setSelected(true);
            JSoundsMainWindowViewController.orderByArtist.setSelected(false);
            JSoundsMainWindowViewController.orderByYear.setSelected(false);
        }
        else if (orderByArtist)
        {
            JSoundsMainWindowViewController.orderByAlbum.setSelected(false);
            JSoundsMainWindowViewController.orderByArtist.setSelected(true);
            JSoundsMainWindowViewController.orderByYear.setSelected(false);
        }
        else if (orderByYear)
        {
            JSoundsMainWindowViewController.orderByAlbum.setSelected(false);
            JSoundsMainWindowViewController.orderByArtist.setSelected(false);
            JSoundsMainWindowViewController.orderByYear.setSelected(true);
        }
    }
    
     /**
     * Method to get a path to copy directory from the File Chooser
     */
    public static void processDirectorySelecction()
    {
        int answer = JSoundsMainWindowViewController.jFileChooser1.showOpenDialog(JSoundsMainWindowViewController.jSoundsMainWindow);
        
        if (answer == JFileChooser.APPROVE_OPTION)
        {
            File actualDirectory = JSoundsMainWindowViewController.jFileChooser1.getCurrentDirectory();
            String directory = actualDirectory.getAbsolutePath() + "/" + JSoundsMainWindowViewController.jFileChooser1.getSelectedFile().getName();
            UtilFunctions.listFilesAndFilesSubDirectories(directory);
            JSoundsMainWindowViewController.orderBy(true, false, false);
            
          
        }
    }
    
    /**
     * Method to import one file to jSoundsLibrary.
     */
        public static void processOneFileSelecction()
    {
        int answer = JSoundsMainWindowViewController.jfcOneFile.showOpenDialog(JSoundsMainWindowViewController.jSoundsMainWindow);
        
        if (answer == JFileChooser.APPROVE_OPTION)
        {
            File actualDirectory = JSoundsMainWindowViewController.jfcOneFile.getCurrentDirectory();
            String directory = actualDirectory.getAbsolutePath() + "/" + JSoundsMainWindowViewController.jfcOneFile.getSelectedFile().getName();
            UtilFunctions.listOneFile(JSoundsMainWindowViewController.jfcOneFile.getSelectedFile());
            JSoundsMainWindowViewController.orderBy(true, false, false);          
        }
    }
     
    /**
     * Method to go to Edit Song Window
     */
    public static void goToEditSongWindow()
    {
        EditSongWindow editSongWindow = new EditSongWindow();
        editSongWindow.setVisible(true);
        editSongWindow.setFatherWindow(actualWindow, false);
    }
         
   /**
     * Method to go to About Window
     */
    public static void goToAboutWindow()
    {
        AboutWindow aboutWindow = new AboutWindow();
        aboutWindow.setVisible(true);
        aboutWindow.setFatherWindow(actualWindow, false);
    }
    
    
    /**
     * Method to launch the playlist chooser window.
     */
        public static void goToPlaylistChooserWindow()
    {
        PlaylistChooser playlistChooser = new PlaylistChooser();
        playlistChooser.setVisible(true);
        playlistChooser.setFatherWindow(actualWindow, false);
    }
         
         /**
     * Method to change the properties of the selected Song in the Edit Song Window
     * @param list
     * @param index
     * @param name
     * @param artistName
     * @param albumName
     * @param number
     */
    public static void editSong (JMusicPlayerList list, int index, String name, String artistName, String albumName, String number)
         {
             list.getSongAtIndex(index).setSongNameForDisplay(name);
             list.getSongAtIndex(index).setArtistForDisplay(artistName);
             list.getSongAtIndex(index).setAlbumForDisplay(albumName);
             list.getSongAtIndex(index).setNumberSong(UtilFunctions.stringToInteger(number));
         }
         
             /**
     * Method to set the repeat the song list from the beginning
     */
    public static void repeatFromTheBeginning()
    {
        if (JSoundsMainWindowViewController.jtbRepeatSongs.isSelected())
            MusicPlayerControl.repeatSongsFromTheBeginning(true);
        else
            MusicPlayerControl.repeatSongsFromTheBeginning(false);
    }
    /**
     * Method to set size of the table
     * @param table
     * @param column
     * @param size 
     */
    private static void setSize(JTable table, int column, int size) {
        table.getColumnModel().getColumn(column).setMaxWidth(size);
        table.getColumnModel().getColumn(column).setMinWidth(size);
        table.getColumnModel().getColumn(column).setWidth(size);
        table.getColumnModel().getColumn(column).setPreferredWidth(size);
    }
    /**
     * Method to set the table with the data
     */
    private static void setTableWithData()
    {
        /* Se establecen el tamaño de la tabla */
        table.setSize(1280, 630);
        table.setMaximumSize(new Dimension(1280, 630));
        table.setMinimumSize(new Dimension(1280, 630));

        /* Se garantiza que cada fila (album) tiene un alto proporcional de acuerdo con el numero de cancioens */
        Iterator iteratorPerRow = listSizePerRow.iterator();
        int sizePerRow, i;
        i = 0;
        while (iteratorPerRow.hasNext())
        {
            sizePerRow =  (Integer) iteratorPerRow.next(); 
            table.setRowHeight(i, sizePerRow);
            i++;
        }

        /* Se setea el ancho de cada columna */
        setSize(table, JSoundsMainWindowViewController.ALBUM_COVER_COLUMN, 200);
        setSize(table, JSoundsMainWindowViewController.ALBUM_INFO_COLUMN, 230);
        setSize(table, JSoundsMainWindowViewController.NUMBER_SONG_COLUMN, 50);
        setSize(table, JSoundsMainWindowViewController.LIST_SONG_COLUMN, 400);
        setSize(table, JSoundsMainWindowViewController.GENDER_SONG_COLUMN, 230);

        /* Renderizado del la imagen del album */
        JLabelTableRenderer jltcr = new JLabelTableRenderer();
        jltcr.setVerticalAlignment(SwingConstants.TOP);
        jltcr.setHorizontalAlignment(SwingConstants.CENTER);

        table.getColumnModel().getColumn(0).setCellRenderer(jltcr);


        /* Renderizados de cada columna */
        table.getColumnModel().getColumn(JSoundsMainWindowViewController.ALBUM_INFO_COLUMN).setCellRenderer(new JListTableRenderer());
        table.getColumnModel().getColumn(JSoundsMainWindowViewController.ALBUM_INFO_COLUMN).setCellEditor(new JListTableEditor());

        table.getColumnModel().getColumn(JSoundsMainWindowViewController.NUMBER_SONG_COLUMN).setCellRenderer(new JListTableRenderer());
        table.getColumnModel().getColumn(JSoundsMainWindowViewController.NUMBER_SONG_COLUMN).setCellEditor(new JListTableEditor());

        table.getColumnModel().getColumn(JSoundsMainWindowViewController.LIST_SONG_COLUMN).setCellRenderer(new JListTableRenderer());
        table.getColumnModel().getColumn(JSoundsMainWindowViewController.LIST_SONG_COLUMN).setCellEditor(new JListTableEditor());

        table.getColumnModel().getColumn(JSoundsMainWindowViewController.GENDER_SONG_COLUMN).setCellRenderer(new JListTableRenderer());
        table.getColumnModel().getColumn(JSoundsMainWindowViewController.GENDER_SONG_COLUMN).setCellEditor(new JListTableEditor());

        table.getColumnModel().getColumn(JSoundsMainWindowViewController.LAST_PLAYED_SONG_COLUMN).setCellRenderer(new JListTableRenderer());
        table.getColumnModel().getColumn(JSoundsMainWindowViewController.LAST_PLAYED_SONG_COLUMN).setCellEditor(new JListTableEditor());      
    }
    
/**
 *  Method to create the data for the albums
 * @param orderByArtist
 * @param orderByAlbum
 * @param orderByYear
 * @param uptadeJTable
 * @return 
 */    
    private static Object[][] createDataForAlbums(boolean orderByArtist, boolean orderByAlbum, boolean orderByYear, boolean uptadeJTable)
    {
        ImageIcon albumIcon;

        Object[][] data;
        JList jLAlbumInfo, jLSongNumber, jLListGenders, jLListLastPlayed;
        JSoundsMainWindowViewController.listSizePerRow  = new ListSizePerRow();
        JLabel iconLabel                                = null;
        DefaultListModel listModel                      = null;
        //JSoundsMainWindowViewController.table           = null;
        
        ListAlbum listAlbum = new ListAlbum(orderByArtist, orderByAlbum, orderByYear);
        AlbumXmlFile.readAllAlbumsFromDataBase(listAlbum, true);
        
        data = new Object[listAlbum.size()][JSoundsMainWindowViewController.TOTAL_COLUMNS];
        
        int actualAlbumIndex = 0;
        
        Album actualAlbum;
        
        Iterator albumIterator = listAlbum.iterator();
        
        //JSoundsMainWindowViewController.jPListContainer.removeAll();
        
        while (albumIterator.hasNext())
        {
            actualAlbum = (Album) albumIterator.next();
            
            /* Se genera la imagen del album */
            String path = Util.JSOUNDS_LIBRARY_PATH + UtilFunctions.removeStrangeCharacters(actualAlbum.getArtist().getName()) + "/" + UtilFunctions.removeStrangeCharacters(actualAlbum.getName()) + "/cover.jpg";
            /* Se genera un icono con el path del archivo */
            albumIcon = new ImageIcon(path);
            /* Se crea un label */
            iconLabel = new JLabel();
            /* Se configura el tamaño del label */
            iconLabel.setSize(albumIcon.getIconWidth(), albumIcon.getIconHeight());
            /* Se introduce la imagen en el label */
            iconLabel.setIcon(albumIcon);
            /* Se alina el label en el tope */
            iconLabel.setVerticalAlignment(JLabel.TOP);


            /* Se genera la informacion del album */
            /* Se crea una lista */
            jLAlbumInfo = new JList();

            /* Se crea un modelo para la lista. Siempre se necesita un modelo para un JList */
            listModel = new DefaultListModel();
            /* Se indica que el modelo de la lista es el definido */
            jLAlbumInfo.setModel(listModel);

            /* Se añaden campos a la lista en cada posicion */
            listModel.add(0, actualAlbum.getName());
            listModel.add(1, actualAlbum.getArtist().getName());
            listModel.add(2, " ");
            listModel.add(3, actualAlbum.getYear());
            listModel.add(4, " ");

            /* Se establece cual es la imagen asociada al score del album */
            StarScore starScore = new StarScore(UtilFunctions.stringToInteger(actualAlbum.getScore()));
            listModel.add(5, starScore.getImage());

            /* Se genera una lista para los números de las canciones */
            jLSongNumber = new JList();

            /* Se crea un modelo para la lista. Siempre se necesita un modelo para un JList */
            listModel = new DefaultListModel();
            /* Se indica que el modelo de la lista es el definido */
            jLSongNumber.setModel(listModel);
            
            jLListLastPlayed = new JList();
            DefaultListModel listLasPlayedModel = new DefaultListModel();
            jLListLastPlayed.setModel(listLasPlayedModel);
            

            /* Se genera un iterador para la lista de canciones del album */
            Iterator songsIterator = actualAlbum.getSongs().iterator();
            int actualSongIndex = 0;

            while (songsIterator.hasNext())
            {
                /* Se añade el número de la canción al modelo de la lista */
                Song song = (Song) songsIterator.next();
                listModel.add(actualSongIndex, UtilFunctions.integerToString(song.getNumber()));

                listLasPlayedModel.add(actualSongIndex, " ");
                actualSongIndex++;
            }


            /* Se degine una lista de tipo JSoundsList */
            /* Es un JList pero que tiene dos campos adicioanles: nombre del album y nombre del artista */
            
            JMusicList jLListSongs;
            jLListSongs = new JMusicList(actualAlbum.getArtist().getName(), actualAlbum.getName(), actualAlbumIndex, jLListLastPlayed);

            /* Se crea un modelo para la lista */
            listModel = new DefaultListModel();
            jLListSongs.setModel(listModel);

            /* Se recorre la lista */
            songsIterator = actualAlbum.getSongs().iterator();
            actualSongIndex = 0;

            while (songsIterator.hasNext())
            {
                /* Se añade el nombre de cada cancion del album */
                Song song = (Song) songsIterator.next();
                listModel.add(actualSongIndex, song.getName());

                actualSongIndex++;
            }


            JSoundsMainWindowViewController.isSelected = false;
            /* Se añade un evento a la lista */
            /* El evento se dispara cada vez que se selecciona un elemento de la lista */
            jLListSongs.addListSelectionListener(new ListSelectionListener() 
            {
                @Override
                public void valueChanged(ListSelectionEvent le) {
                    /* Se obtiene cual fue la lista que disparo el evento */
                    JMusicList list = (JMusicList) le.getSource();

                    /* Si la lista existe y no ha sido seleccionada aun */
                    if (list != null && JSoundsMainWindowViewController.isSelected)
                    {
                        /* Se obtiene el indice seleccionado. Si es distinto de -1 algo se selecciono */
                        int idx = list.getSelectedIndex();
                        if (idx != -1)
                        {
                            /* Si se selecciono la lista de otro album, se borra la seleccion de la lista anterior */
                            if (JSoundsMainWindowViewController.jlActualListSongs != null &&
                                !JSoundsMainWindowViewController.jlActualListSongs.equals(list))
                                JSoundsMainWindowViewController.jlActualListSongs.clearSelection();

                            /* La lista actual es la seleccionada */
                            JSoundsMainWindowViewController.jlActualListSongs = list;                                
                        }

                        JSoundsMainWindowViewController.isSelected = false;
                    }
                    else
                    {
                        if (!JSoundsMainWindowViewController.isSelected)
                            JSoundsMainWindowViewController.isSelected = true;
                    }
                }
              });

            jLListSongs.addMouseListener(new MouseAdapter() 
            {
                @Override
                public void mouseClicked(MouseEvent evt) 
                {
                    JList list = (JList)evt.getSource();
                    
                    if ((evt.getClickCount() == 1)&& alreadyPlaying)
                    {
                        JSoundsMainWindowViewController.fillEditWindow(JSoundsMainWindowViewController.jlActualListSongs.getSelectedIndex());
                        
                        songName=listSongs.getSongAtIndex(JSoundsMainWindowViewController.jlActualListSongs.getSelectedIndex()).getSongName();
                    }
                    if (evt.getClickCount() == 2) 
                    {
                        genderGenius=findGender(songName);
                        fixedIndex= false;
                        if (alreadyPlaying == false)
                        {                            
                            playListSongs(true); 
                            genderGenius=findGender(songName);
                        }
                        else
                        {
                            if (iAmPlaying==true)
                            {
                            JSoundsMainWindowViewController.stopSong();
                            playListSongs(true);
                            }
                            if (iAmPausing==true)
                            {
                            JSoundsMainWindowViewController.stopSong();
                            playListSongs(true);  
                            }
                            if (iAmResuming == true)
                            {
                            JSoundsMainWindowViewController.stopSong();
                            fixedIndex=true;
                            playListSongs(true);   
                            }
                        }
                    }
                }   
            });

            /* Para cada fila (album) se guarda el tamaño (número de canciones) */
            listSizePerRow.addSize(jLListSongs.getModel().getSize());


            /* Se genera una lista para manejar los géneros */
            jLListGenders = new JList();

            /* Se crea el modelo asociado a la lista */
            listModel = new DefaultListModel();
            jLListGenders.setModel(listModel);

            /* Se recorre la lista de canciones */
            songsIterator = actualAlbum.getSongs().iterator();
            actualSongIndex = 0;

            while (songsIterator.hasNext())
            {
                /* A todas las canciones se les asigna el midmo género */
                /* ESTO ES UN EJEMPLO */
                Song song = (Song) songsIterator.next();
                if (song.getGender()=="")
                    listModel.add(actualSongIndex, "Gender");
                else
                    listModel.add(actualSongIndex, song.getGender());
                
                actualSongIndex++;
            }

            /* Se añade cada campo a una fila nueva de la matriz de datos */
            data[actualAlbumIndex][JSoundsMainWindowViewController.ALBUM_COVER_COLUMN] = iconLabel;
            data[actualAlbumIndex][JSoundsMainWindowViewController.ALBUM_INFO_COLUMN] = jLAlbumInfo;
            data[actualAlbumIndex][JSoundsMainWindowViewController.NUMBER_SONG_COLUMN] = jLSongNumber;
            data[actualAlbumIndex][JSoundsMainWindowViewController.LIST_SONG_COLUMN] = jLListSongs;
            data[actualAlbumIndex][JSoundsMainWindowViewController.GENDER_SONG_COLUMN] = jLListGenders;
            data[actualAlbumIndex][JSoundsMainWindowViewController.LAST_PLAYED_SONG_COLUMN] = jLListLastPlayed;
            
            
            if (uptadeJTable)
            {
                DefaultTableModel dm = (DefaultTableModel) JSoundsMainWindowViewController.table.getModel();
                dm.addRow(new Object[]{  data[actualAlbumIndex][JSoundsMainWindowViewController.ALBUM_COVER_COLUMN],
                                            data[actualAlbumIndex][JSoundsMainWindowViewController.ALBUM_INFO_COLUMN],
                                            data[actualAlbumIndex][JSoundsMainWindowViewController.NUMBER_SONG_COLUMN],
                                            data[actualAlbumIndex][JSoundsMainWindowViewController.LIST_SONG_COLUMN],
                                            data[actualAlbumIndex][JSoundsMainWindowViewController.GENDER_SONG_COLUMN],
                                            data[actualAlbumIndex][JSoundsMainWindowViewController.LAST_PLAYED_SONG_COLUMN]});            
            }
            actualAlbumIndex++;
        }
        
        return data;
    }
    
    /**
     * Method to set all the album data in the JPanel
     * @param orderByArtist
     * @param orderByAlbum
     * @param orderByYear
     */
    public static void loadAllAlbumsInfoInContainer(boolean orderByArtist, boolean orderByAlbum, boolean orderByYear)
    {
        Object[][] data = createDataForAlbums(orderByArtist, orderByAlbum, orderByYear, false);
        /* Despues del ciclo anterior, la matriz data tiene toda la información que se quiere añadir a la tabla */
        
        /* Se crea un modelo con los datos y los nombres de las columnas */
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        
        /* Se crea una nueva tabla con el modelo y se le añaden unos metodos sobre escritos a la tabla */
        table = new JTable(model)
        {
            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                
                if (column == JSoundsMainWindowViewController.ALBUM_INFO_COLUMN ||
                    column == JSoundsMainWindowViewController.NUMBER_SONG_COLUMN ||
                    column == JSoundsMainWindowViewController.LIST_SONG_COLUMN)
                {
                    return new JListTableEditor();
                }
                
                return null;
            }
            
            @Override
            public boolean isCellEditable(int row, int col) {
                switch (col) {
                    case JSoundsMainWindowViewController.LIST_SONG_COLUMN:
                        return true;
                    default:
                        return false;
                }
            }
        };
        
        setTableWithData();
        
        /* Se crea un scroll y se le añade la tabla */
        JScrollPane scrollPane = new JScrollPane(table);
        
        Dimension d = table.getSize();
        scrollPane.setPreferredSize(new Dimension(d.width,630));
        
        /* Se añade el scroll pane al panel */
        JSoundsMainWindowViewController.jPListContainer.setLayout(new GridLayout());
        JSoundsMainWindowViewController.jPListContainer.add(scrollPane);
    }
    /**
     * Method to remove all the elements in te JPanel
     */
    private static void removeAllElementsInContainer()
    {
        DefaultTableModel dm = (DefaultTableModel) JSoundsMainWindowViewController.table.getModel();
        for (int i = dm.getRowCount() - 1; i >= 0; i--) {
            dm.removeRow(i);
        } 
    }
    
    /**
     * Method to order the list by Artist/Album/Year
     * @param orderByArtist
     * @param orderByAlbum
     * @param orderByYear
     */
    public static void orderBy(boolean orderByArtist, boolean orderByAlbum, boolean orderByYear)
    {
        if (orderByArtist)
            Album.compareBy=2;
        if(orderByAlbum)
            Album.compareBy=1;
        if(orderByYear)
            Album.compareBy=3;
        
        updateOrderButtons(orderByArtist, orderByAlbum, orderByYear);
                
        JSoundsMainWindowViewController.removeAllElementsInContainer();
        
        DefaultTableModel model = new DefaultTableModel(createDataForAlbums(orderByArtist,orderByAlbum,orderByYear,true), columnNames);
        table.setModel(model);
        setTableWithData();
        
        DefaultTableModel dm = (DefaultTableModel) JSoundsMainWindowViewController.table.getModel();
        int i = dm.getRowCount() - 1;
        boolean found = false;
        
        while (!found && i >= 0)
        {
            JMusicList actualList = (JMusicList) dm.getValueAt(i, JSoundsMainWindowViewController.LIST_SONG_COLUMN);
            
            if (actualList.equals(JSoundsMainWindowViewController.jlActualListSongs))
            {
                JSoundsMainWindowViewController.jlActualListSongs = actualList;
                found = true;
            }
            else
                i--;
        } 
        
        if (found)
            MusicPlayerControl.updateContainerInformation(jlActualListSongs, jlActualListSongs.getjLLastPlayedSongList(), table, JSoundsMainWindowViewController.jlActualListSongs.getRowInJTable());
    }
    

    /**
     * A List Filled with all Songs Data
     */
    public static JMusicPlayerList listSongs = new JMusicPlayerList(); 
    private static Album selectedAlbum=null;
    private static boolean dontInitPlayer=false;
    private static boolean shutDown=false;
    public static int indexFromSearchedSong=-1;
    /**
     * Method to play the list Selected
     * @param iSelectASong
     */
    public static void playListSongs(boolean iSelectASong)
    {
        Album actualAlbum = AlbumXmlFile.getAlbumFromDataBase(JSoundsMainWindowViewController.jlActualListSongs.getAlbumName(), true);

        
        if ((selectedAlbum!=null) && (!actualAlbum.getName().equals(selectedAlbum.getName())))
                {
                         JSoundsMainWindowViewController.listSongs=null;
                         JSoundsMainWindowViewController.listSongs=new JMusicPlayerList();
                         alreadyPlaying=false;
                         if((!isPlaylist) && (!shutDown))
                         dontInitPlayer=true;
                }
        
        if (JSoundsMainWindowViewController.iAmPlaying && JSoundsMainWindowViewController.jlActualListSongs != null)
        {    
            if (alreadyPlaying==false)
            {
            ViewUtilFunctions.changeIconFromButton(JSoundsMainWindowViewController.jBPlayButton, "src/view/images/IBPause.png");
            JSoundsMainWindowViewController.iAmPlaying = false;
            JSoundsMainWindowViewController.iAmPausing = true;
            JSoundsMainWindowViewController.iAmResuming = false;
            
            selectedAlbum=actualAlbum;
            
            Song    actualSong = null;
            int position;
            if (!isPlaylist) //Si no estoy tratando de reproducir una lista de reproduccion.
            {   
            
            
            if (actualAlbum != null)
            {
                Iterator songsIterator = actualAlbum.getSongs().iterator();
                
                position = 0;
                
                while (songsIterator.hasNext())
                {
                    actualSong = (Song) songsIterator.next();
                    
                    if (actualSong != null)
                    {
                        listSongs.addSongToPlayerList(new JMusicSong(position, UtilFunctions.removeStrangeCharacters(actualSong.getName()), UtilFunctions.removeStrangeCharacters(actualSong.getName()), actualSong.getName(), UtilFunctions.removeStrangeCharacters(actualSong.getArtist().getName()), actualSong.getArtist().getName(), UtilFunctions.removeStrangeCharacters(actualAlbum.getName()), actualAlbum.getName()));
                        position++;
                    }
                }
            }
            }
            else //Si es una lista de reproduccion
            {
                Album actualAlbumPlaylist = PlaylistXmlFile.getPlaylistAlbumFromDataBase(JSoundsMainWindowViewController.jlActualListSongs.getAlbumName(), true);
            
            
            if (actualAlbumPlaylist != null)
            {
                Iterator songsIterator = actualAlbumPlaylist.getSongs().iterator();
                
                position = 0;
                
                while (songsIterator.hasNext())
                {
                    
                    actualSong = (Song) songsIterator.next();
                    
                    if (actualSong != null)
                    {
                        listSongs.addSongToPlayerList(new JMusicSong(position, UtilFunctions.removeStrangeCharacters(actualSong.getName()), UtilFunctions.removeStrangeCharacters(actualSong.getName()), actualSong.getName(), UtilFunctions.removeStrangeCharacters(actualSong.getArtist().getName()), actualSong.getArtist().getName(), UtilFunctions.removeStrangeCharacters(actualAlbumPlaylist.getName()), actualAlbumPlaylist.getName()));
                        position++;
                    }
                }
            }
            }          
            if (!dontInitPlayer) // Inicio el reproductor
            {
            MusicPlayerControl.initMusicPlayer(Util.JSOUNDS_LIBRARY_PATH, JSoundsMainWindowViewController.jlActualListSongs, JSoundsMainWindowViewController.jLInformationSong, JSoundsMainWindowViewController.jlActualListSongs.getjLLastPlayedSongList(), table, JSoundsMainWindowViewController.LIST_SONG_COLUMN, JSoundsMainWindowViewController.jlActualListSongs.getRowInJTable(), JSoundsMainWindowViewController.LAST_PLAYED_SONG_COLUMN);
            MusicPlayerControl.loadSongs(listSongs);
            shutDown=false;
            }
            else // El reproductor ya esta iniciado
            {
               MusicPlayerControl.loadSongs(listSongs);
               dontInitPlayer=false;
            }
            
            if (iSelectASong)
            {
                if (indexFromSearchedSong>(-1))
                {
                MusicPlayerControl.changeSongFromIndexSong(indexFromSearchedSong);
                iSelectASong=false;
                indexFromSearchedSong=-1;
                }
                else
                {
                MusicPlayerControl.changeSongFromIndexSong(JSoundsMainWindowViewController.jlActualListSongs.getSelectedIndex());
                iSelectASong = false;
                }
                
            }
            EditSongWindow.songName =listSongs.getSongAtIndex(JSoundsMainWindowViewController.jlActualListSongs.getSelectedIndex()).getSongName();
            EditSongWindow.index = JSoundsMainWindowViewController.jlActualListSongs.getSelectedIndex();
            EditSongWindow.list = listSongs;
            JSoundsMainWindowViewController.fillEditWindow(JSoundsMainWindowViewController.jlActualListSongs.getSelectedIndex());
            MusicPlayerControl.playSong();
            alreadyPlaying = true;
            }
                        if ((alreadyPlaying==true) && (iSelectASong==true) && (fixedIndex== true))
                    {
                        ViewUtilFunctions.changeIconFromButton(JSoundsMainWindowViewController.jBPlayButton, "src/view/images/IBPause.png");
                        JSoundsMainWindowViewController.iAmPlaying = false;
                        JSoundsMainWindowViewController.iAmPausing = true;
                        JSoundsMainWindowViewController.iAmResuming = false;
                        MusicPlayerControl.changeSongFromIndexSong(JSoundsMainWindowViewController.jlActualListSongs.getSelectedIndex()-1);
                        
                        JSoundsMainWindowViewController.fillEditWindow(JSoundsMainWindowViewController.jlActualListSongs.getSelectedIndex()-1);
                        
                        MusicPlayerControl.playSong();
                    }
                        if ((alreadyPlaying==true) && (iSelectASong==true) && (iAmPausing==true) && (!fixedIndex))
                    {
                        MusicPlayerControl.changeSongFromIndexSong(JSoundsMainWindowViewController.jlActualListSongs.getSelectedIndex());
                        
                        JSoundsMainWindowViewController.fillEditWindow(JSoundsMainWindowViewController.jlActualListSongs.getSelectedIndex());
                        
                        MusicPlayerControl.playSong();
                    }
                         if ((alreadyPlaying==true) && (iSelectASong==true) && (iAmPlaying==true) && (!fixedIndex))
                         {
                             ViewUtilFunctions.changeIconFromButton(JSoundsMainWindowViewController.jBPlayButton, "src/view/images/IBPause.png");
                            JSoundsMainWindowViewController.iAmPlaying = false;
                            JSoundsMainWindowViewController.iAmPausing = true;
                            JSoundsMainWindowViewController.iAmResuming = false;
                            MusicPlayerControl.changeSongFromIndexSong(JSoundsMainWindowViewController.jlActualListSongs.getSelectedIndex());
                            
                            JSoundsMainWindowViewController.fillEditWindow(JSoundsMainWindowViewController.jlActualListSongs.getSelectedIndex());
                            
                            MusicPlayerControl.playSong();
                         }
            
        }
        else
        {
            if (JSoundsMainWindowViewController.iAmPausing && JSoundsMainWindowViewController.jlActualListSongs != null)
            {
                ViewUtilFunctions.changeIconFromButton(JSoundsMainWindowViewController.jBPlayButton, "src/view/images/IBPlay.png");
                JSoundsMainWindowViewController.iAmPlaying = false;
                JSoundsMainWindowViewController.iAmPausing = false;
                JSoundsMainWindowViewController.iAmResuming = true;
                
                MusicPlayerControl.pauseSong();
            }
            else
            {
                if (JSoundsMainWindowViewController.iAmResuming && JSoundsMainWindowViewController.jlActualListSongs != null)
                {
                    ViewUtilFunctions.changeIconFromButton(JSoundsMainWindowViewController.jBPlayButton, "src/view/images/IBPause.png");
                    
                    JSoundsMainWindowViewController.iAmPlaying = false;
                    JSoundsMainWindowViewController.iAmPausing = true;
                    JSoundsMainWindowViewController.iAmResuming = false;
                    
                    MusicPlayerControl.resumeSong();
                }
                
            }
        }
    }
    
    /**
     * Go to Next Song in List
     */
    public static void nextSong()
    {
        MusicPlayerControl.nextSong();
    }
    
    /**
     * Go to Previous Song in List
     */
    public static void previusSong()
    {
        MusicPlayerControl.prevSong();
    }
    
    /**
     * Stop the Actual Playing Song
     */
    public static void stopSong()
    {
        JSoundsMainWindowViewController.iAmPlaying = true;
        JSoundsMainWindowViewController.iAmPausing = false;
        JSoundsMainWindowViewController.iAmResuming = false;
        
        MusicPlayerControl.stopSong();
    }
    
    /**
     * Shut Down the player.
     */
    public static void shutDownPlayer()
    {
        MusicPlayerControl.shutdown();
        JSoundsMainWindowViewController.listSongs=null;
        JSoundsMainWindowViewController.listSongs=new JMusicPlayerList();
        JSoundsMainWindowViewController.alreadyPlaying=false;
        JSoundsMainWindowViewController.dontInitPlayer=false;
        JSoundsMainWindowViewController.shutDown=true;
    }
    
        /**
     * Method to Fill The Edit Song Window
     * @param index
     */
    public static void fillEditWindow (int index)
        {
            EditSongWindow.songName =listSongs.getSongAtIndex(JSoundsMainWindowViewController.jlActualListSongs.getSelectedIndex()).getSongName();
            EditSongWindow.artistEdit =listSongs.getSongAtIndex(JSoundsMainWindowViewController.jlActualListSongs.getSelectedIndex()).getArtist();
            EditSongWindow.albumEdit =listSongs.getSongAtIndex(JSoundsMainWindowViewController.jlActualListSongs.getSelectedIndex()).getAlbum();
            EditSongWindow.genderEdit=(findGender(listSongs.getSongAtIndex(JSoundsMainWindowViewController.jlActualListSongs.getSelectedIndex()).getSongName()));
            EditSongWindow.index = JSoundsMainWindowViewController.jlActualListSongs.getSelectedIndex();
            EditSongWindow.list = listSongs;
        }
    
    
    
    
    
    
    /**
     * Show the songs of the selected playlist.
     * @param orderByArtist
     * @param orderByAlbum
     * @param orderByYear 
     */
     public static void playlistOrderBy(boolean orderByArtist, boolean orderByAlbum, boolean orderByYear)
    {
        updateOrderButtons(orderByArtist, orderByAlbum, orderByYear);
                
        JSoundsMainWindowViewController.removeAllElementsInContainer();
        
        DefaultTableModel model = new DefaultTableModel(createPlaylistDataForAlbums(orderByArtist,orderByAlbum,orderByYear,true), columnNames);
        table.setModel(model);
        setTableWithData();
        
        DefaultTableModel dm = (DefaultTableModel) JSoundsMainWindowViewController.table.getModel();
        int i = dm.getRowCount() - 1;
        boolean found = false;
        
        while (!found && i >= 0)
        {
            JMusicList actualList = (JMusicList) dm.getValueAt(i, JSoundsMainWindowViewController.LIST_SONG_COLUMN);
            
            if (actualList.equals(JSoundsMainWindowViewController.jlActualListSongs))
            {
                JSoundsMainWindowViewController.jlActualListSongs = actualList;
                found = true;
            }
            else
                i--;
        } 
        
        if (found)
            MusicPlayerControl.updateContainerInformation(jlActualListSongs, jlActualListSongs.getjLLastPlayedSongList(), table, JSoundsMainWindowViewController.jlActualListSongs.getRowInJTable());
    }
    
    
    
    /**
     * Create the data of the selected playlist.
     * @param orderByArtist
     * @param orderByAlbum
     * @param orderByYear
     * @param uptadeJTable
     * @return 
     */
        private static Object[][] createPlaylistDataForAlbums(boolean orderByArtist, boolean orderByAlbum, boolean orderByYear, boolean uptadeJTable)
    {
        ImageIcon albumIcon;

        Object[][] data;
        JList jLAlbumInfo, jLSongNumber, jLListGenders, jLListLastPlayed;
        JSoundsMainWindowViewController.listSizePerRow  = new ListSizePerRow();
        JLabel iconLabel                                = null;
        DefaultListModel listModel                      = null;
        //JSoundsMainWindowViewController.table           = null;
        
        ListAlbum listAlbum = new ListAlbum(orderByArtist, orderByAlbum, orderByYear);
        PlaylistXmlFile.readAllAlbumsListFromDataBase(listAlbum, true);
        
        
        data = new Object[listAlbum.size()][JSoundsMainWindowViewController.TOTAL_COLUMNS];
        
        int actualAlbumIndex = 0;
        
        Album actualAlbum;
        
        Iterator albumIterator = listAlbum.iterator();
        
        //JSoundsMainWindowViewController.jPListContainer.removeAll();
        
        while (albumIterator.hasNext())
        {
            actualAlbum = (Album) albumIterator.next();
            
            /* Se genera la imagen del album */
            String path = Util.JSOUNDS_LIBRARY_PATH + UtilFunctions.removeStrangeCharacters(actualAlbum.getArtist().getName()) + "/" + UtilFunctions.removeStrangeCharacters(actualAlbum.getName()) + "/cover.jpg";
            /* Se genera un icono con el path del archivo */
            albumIcon = new ImageIcon(path);
            /* Se crea un label */
            iconLabel = new JLabel();
            /* Se configura el tamaño del label */
            iconLabel.setSize(albumIcon.getIconWidth(), albumIcon.getIconHeight());
            /* Se introduce la imagen en el label */
            iconLabel.setIcon(albumIcon);
            /* Se alina el label en el tope */
            iconLabel.setVerticalAlignment(JLabel.TOP);


            /* Se genera la informacion del album */
            /* Se crea una lista */
            jLAlbumInfo = new JList();

            /* Se crea un modelo para la lista. Siempre se necesita un modelo para un JList */
            listModel = new DefaultListModel();
            /* Se indica que el modelo de la lista es el definido */
            jLAlbumInfo.setModel(listModel);

            /* Se añaden campos a la lista en cada posicion */
            listModel.add(0, actualAlbum.getName());
            listModel.add(1, actualAlbum.getArtist().getName());
            listModel.add(2, " ");
            listModel.add(3, actualAlbum.getYear());
            listModel.add(4, " ");

            /* Se establece cual es la imagen asociada al score del album */
            StarScore starScore = new StarScore(UtilFunctions.stringToInteger(actualAlbum.getScore()));
            listModel.add(5, starScore.getImage());

            /* Se genera una lista para los números de las canciones */
            jLSongNumber = new JList();

            /* Se crea un modelo para la lista. Siempre se necesita un modelo para un JList */
            listModel = new DefaultListModel();
            /* Se indica que el modelo de la lista es el definido */
            jLSongNumber.setModel(listModel);
            
            jLListLastPlayed = new JList();
            DefaultListModel listLasPlayedModel = new DefaultListModel();
            jLListLastPlayed.setModel(listLasPlayedModel);
            

            /* Se genera un iterador para la lista de canciones del album */
            Iterator songsIterator = actualAlbum.getSongs().iterator();
            int actualSongIndex = 0;

            while (songsIterator.hasNext())
            {
                /* Se añade el número de la canción al modelo de la lista */
                Song song = (Song) songsIterator.next();
                listModel.add(actualSongIndex, UtilFunctions.integerToString(song.getNumber()));

                listLasPlayedModel.add(actualSongIndex, " ");
                actualSongIndex++;
            }


            /* Se degine una lista de tipo JSoundsList */
            /* Es un JList pero que tiene dos campos adicioanles: nombre del album y nombre del artista */
            
            JMusicList jLListSongs;
            jLListSongs = new JMusicList(actualAlbum.getArtist().getName(), actualAlbum.getName(), actualAlbumIndex, jLListLastPlayed);

            /* Se crea un modelo para la lista */
            listModel = new DefaultListModel();
            jLListSongs.setModel(listModel);

            /* Se recorre la lista */
            songsIterator = actualAlbum.getSongs().iterator();
            actualSongIndex = 0;

            while (songsIterator.hasNext())
            {
                /* Se añade el nombre de cada cancion del album */
                Song song = (Song) songsIterator.next();
                listModel.add(actualSongIndex, song.getName());

                actualSongIndex++;
            }


            JSoundsMainWindowViewController.isSelected = false;
            /* Se añade un evento a la lista */
            /* El evento se dispara cada vez que se selecciona un elemento de la lista */
            jLListSongs.addListSelectionListener(new ListSelectionListener() 
            {
                @Override
                public void valueChanged(ListSelectionEvent le) {
                    /* Se obtiene cual fue la lista que disparo el evento */
                    JMusicList list = (JMusicList) le.getSource();

                    /* Si la lista existe y no ha sido seleccionada aun */
                    if (list != null && JSoundsMainWindowViewController.isSelected)
                    {
                        /* Se obtiene el indice seleccionado. Si es distinto de -1 algo se selecciono */
                        int idx = list.getSelectedIndex();
                        if (idx != -1)
                        {
                            /* Si se selecciono la lista de otro album, se borra la seleccion de la lista anterior */
                            if (JSoundsMainWindowViewController.jlActualListSongs != null &&
                                !JSoundsMainWindowViewController.jlActualListSongs.equals(list))
                                JSoundsMainWindowViewController.jlActualListSongs.clearSelection();

                            /* La lista actual es la seleccionada */
                            JSoundsMainWindowViewController.jlActualListSongs = list;                                
                        }

                        JSoundsMainWindowViewController.isSelected = false;
                    }
                    else
                    {
                        if (!JSoundsMainWindowViewController.isSelected)
                            JSoundsMainWindowViewController.isSelected = true;
                    }
                }
              });

           jLListSongs.addMouseListener(new MouseAdapter() 
            {
                @Override
                public void mouseClicked(MouseEvent evt) 
                {
                    JList list = (JList)evt.getSource();
                    
                    if ((evt.getClickCount() == 1)&&(alreadyPlaying))
                    {
                        JSoundsMainWindowViewController.fillEditWindow(JSoundsMainWindowViewController.jlActualListSongs.getSelectedIndex());
                        songName=listSongs.getSongAtIndex(JSoundsMainWindowViewController.jlActualListSongs.getSelectedIndex()).getSongName();
                    }
                    if (evt.getClickCount() == 2) 
                    {
                        fixedIndex= false;
                        if (alreadyPlaying == false)
                        {                            
                            playListSongs(true);  
                        }
                        else
                        {
                            if (iAmPlaying==true)
                            {
                            JSoundsMainWindowViewController.stopSong();
                            playListSongs(true);
                            }
                            if (iAmPausing==true)
                            {
                            JSoundsMainWindowViewController.stopSong();
                            playListSongs(true);  
                            }
                            if (iAmResuming == true)
                            {
                            JSoundsMainWindowViewController.stopSong();
                            fixedIndex=true;
                            playListSongs(true);   
                            }
                        }
                    }
                }   
            });

            /* Para cada fila (album) se guarda el tamaño (número de canciones) */
            listSizePerRow.addSize(jLListSongs.getModel().getSize());


            /* Se genera una lista para manejar los géneros */
            jLListGenders = new JList();

            /* Se crea el modelo asociado a la lista */
            listModel = new DefaultListModel();
            jLListGenders.setModel(listModel);

            /* Se recorre la lista de canciones */
            songsIterator = actualAlbum.getSongs().iterator();
            actualSongIndex = 0;

            while (songsIterator.hasNext())
            {
                /* A todas las canciones se les asigna el midmo género */
                /* ESTO ES UN EJEMPLO */
                Song song = (Song) songsIterator.next();
                if (song.getGender()=="")
                    listModel.add(actualSongIndex, "Gender");
                else
                    listModel.add(actualSongIndex, song.getGender());

                actualSongIndex++;
            }

            /* Se añade cada campo a una fila nueva de la matriz de datos */
            data[actualAlbumIndex][JSoundsMainWindowViewController.ALBUM_COVER_COLUMN] = iconLabel;
            data[actualAlbumIndex][JSoundsMainWindowViewController.ALBUM_INFO_COLUMN] = jLAlbumInfo;
            data[actualAlbumIndex][JSoundsMainWindowViewController.NUMBER_SONG_COLUMN] = jLSongNumber;
            data[actualAlbumIndex][JSoundsMainWindowViewController.LIST_SONG_COLUMN] = jLListSongs;
            data[actualAlbumIndex][JSoundsMainWindowViewController.GENDER_SONG_COLUMN] = jLListGenders;
            data[actualAlbumIndex][JSoundsMainWindowViewController.LAST_PLAYED_SONG_COLUMN] = jLListLastPlayed;
            
            
            if (uptadeJTable)
            {
                DefaultTableModel dm = (DefaultTableModel) JSoundsMainWindowViewController.table.getModel();
                dm.addRow(new Object[]{  data[actualAlbumIndex][JSoundsMainWindowViewController.ALBUM_COVER_COLUMN],
                                            data[actualAlbumIndex][JSoundsMainWindowViewController.ALBUM_INFO_COLUMN],
                                            data[actualAlbumIndex][JSoundsMainWindowViewController.NUMBER_SONG_COLUMN],
                                            data[actualAlbumIndex][JSoundsMainWindowViewController.LIST_SONG_COLUMN],
                                            data[actualAlbumIndex][JSoundsMainWindowViewController.GENDER_SONG_COLUMN],
                                            data[actualAlbumIndex][JSoundsMainWindowViewController.LAST_PLAYED_SONG_COLUMN]});            
            }
            actualAlbumIndex++;
        }
        
        return data;
    }
       

        /**
         * Save selected song in playlist.
         * @param playlistNumber 
         */
        public static void saveSongInPlaylist(int playlistNumber)
        {
           Artist artist = new Artist ("Unknown Artist",1);
           Song song = new Song (JSoundsMainWindowViewController.songName,artist,1,0,findGender(songName));
           PlaylistXmlFile.savePlaylistSongsInDataBase(song,playlistNumber);
        }
        
        /**
         * Save selected song in NEW playlist.
         */
        public static void saveSongInNewPlaylist()
        {
           int playlistNumber = (PlaylistXmlFile.getNumberOfPlaylist()+1);
           Artist artist = new Artist ("Unknown Artist",1);
           Song song = new Song (JSoundsMainWindowViewController.songName,artist,1,0,findGender(songName));
           PlaylistXmlFile.savePlaylistSongsInDataBase(song,playlistNumber);
        }
        
        
        /**
         * Load all the playlist in the combo box of playlist chooser window.
         * @param combo 
         */
     public static void loadAllPlaylistInCombo(JComboBox combo)
    {
        
        String text = "";
        
        int i=1;

        int amountOfPlaylist=(PlaylistXmlFile.getNumberOfPlaylist());
        
        while (i<=amountOfPlaylist)
        {
            text = ("Playlist " + i);
            
            combo.addItem(text);
            
            i++;
        }
    }
     /**
      * Load the selected playlist.
      */
     public static void loadPlaylist()
     {
          if (JSoundsMainWindowViewController.alreadyPlaying)
              JSoundsMainWindowViewController.stopSong();
          
        JSoundsMainWindowViewController.shutDownPlayer();
        JSoundsMainWindowViewController.isPlaylist=true;
        JSoundsMainWindowViewController.playlistOrderBy(true, false, false);
     }
     
     /**
      * Load all songs in the jSounds Library.
      */
          public static void loadAllSongs()
     {
          if (JSoundsMainWindowViewController.alreadyPlaying)
              JSoundsMainWindowViewController.stopSong();
          
        JSoundsMainWindowViewController.shutDownPlayer();
        JSoundsMainWindowViewController.isPlaylist=false;
        JSoundsMainWindowViewController.orderBy(false, true, false);
     }
        
        /**
         * Method to search an element in the music library.
         * @param searching
         * @return 
         */
       public static int search (String searching)
          {
        ListAlbum listAlbum = new ListAlbum(true, false, false);
        AlbumXmlFile.readAllAlbumsFromDataBase(listAlbum, true);
        Iterator albumIterator = listAlbum.iterator();
        Album actualAlbum;
        int actualAlbumIndex=0;
        while (albumIterator.hasNext())
        {
            
        actualAlbum = (Album) albumIterator.next();
        
                       if (actualAlbum.getName().equals(searching))
               {
                   JSoundsMainWindowViewController.showAlbumResults(true, false, false, actualAlbum);
                   JSoundsMainWindow.jtfSearchSong.setText("");
                   return 1;
               }
                       else if (actualAlbum.getArtist().getName().equals(searching))
               {
                   JSoundsMainWindowViewController.showAlbumResults(true, false, false, actualAlbum);
                   JSoundsMainWindow.jtfSearchSong.setText("");
                   return 1;
               }
        actualAlbum.getSongs();
        Iterator songsIterator = actualAlbum.getSongs().iterator();
            int actualSongIndex = 0;

            while (songsIterator.hasNext())
            {
                /* Se añade el número de la canción al modelo de la lista */
                Song song = (Song) songsIterator.next();
               if (song.getName().equals(searching))
               {
                   JSoundsMainWindowViewController.showSongResults(true, false, false, actualAlbum,song,actualSongIndex);
                   JSoundsMainWindow.jtfSearchSong.setText("");
                   indexFromSearchedSong=actualSongIndex;
                   if (alreadyPlaying)
                   {
                   JSoundsMainWindowViewController.stopSong();
                   alreadyPlaying=false;
                   dontInitPlayer=true;
                   }
                   //playListSongs(true);
                   return 1;
               }
                actualSongIndex++;
            }
              actualAlbumIndex++;
          }
        JSoundsMainWindow.jtfSearchSong.setText("");
        JOptionPane.showMessageDialog(null, "Not Found :( ");
        return 0;
          }
     /**
      * Method to find the gender of the selected song.
      * @param songName
      * @return
      */   
  public static String findGender (String songName)
   {
        String songGender="";
        ListAlbum listAlbum = new ListAlbum(true, false, false);
        AlbumXmlFile.readAllAlbumsFromDataBase(listAlbum, true);
        Iterator albumIterator = listAlbum.iterator();
        Album actualAlbum;
        int actualAlbumIndex=0;
        while (albumIterator.hasNext())
        {
            
        actualAlbum = (Album) albumIterator.next();
        actualAlbum.getSongs();
        Iterator songsIterator = actualAlbum.getSongs().iterator();
            int actualSongIndex = 0;

            while (songsIterator.hasNext())
            {
                /* Se añade el número de la canción al modelo de la lista */
                Song song = (Song) songsIterator.next();
               if (song.getName().equals(songName))
               {  
                   songGender=song.getGender();
                   return (songGender);
               }
                actualSongIndex++;
            }
              actualAlbumIndex++;
          }
        return ("Gender");
          }
  
  /**
   * Save songs in genius playlist.
   * @param songNameGenius
   * @param playlistNumber 
   */
          public static void saveSongInGeniusPlaylist(String songNameGenius, int playlistNumber)
        {
           Artist artist = new Artist ("Unknown Artist",1);
           Song song = new Song (songNameGenius,artist,1,0,findGender(songNameGenius));
           PlaylistXmlFile.savePlaylistSongsInDataBase(song,playlistNumber);
        }
          
     /**
      * Launch Genius playlist.
      */
    public static void newGenius ()
   {
        int newPlaylistNumber=(PlaylistXmlFile.getNumberOfPlaylist()+1);
        playlistSelectedInCombo=newPlaylistNumber;
        ListAlbum listAlbum = new ListAlbum(true, false, false);
        AlbumXmlFile.readAllAlbumsFromDataBase(listAlbum, true);
        Iterator albumIterator = listAlbum.iterator();
        Album actualAlbum;
        int actualAlbumIndex=0;
        while (albumIterator.hasNext())
        {
            
        actualAlbum = (Album) albumIterator.next();
        actualAlbum.getSongs();
        Iterator songsIterator = actualAlbum.getSongs().iterator();
            int actualSongIndex = 0;

            while (songsIterator.hasNext())
            {
                /* Se añade el número de la canción al modelo de la lista */
                Song song = (Song) songsIterator.next();
               if (song.getGender().equals(genderGenius))
               {  
                  JSoundsMainWindowViewController.saveSongInGeniusPlaylist(song.getName(), newPlaylistNumber); 
               }
                actualSongIndex++;
            }
              actualAlbumIndex++;
          }
        JSoundsMainWindowViewController.loadPlaylist();
          }
    
        
          /**
           * Show the albums finded.
           * @param orderByArtist
           * @param orderByAlbum
           * @param orderByYear
           * @param album 
           */
              public static void showAlbumResults(boolean orderByArtist, boolean orderByAlbum, boolean orderByYear, Album album)
    {
        updateOrderButtons(orderByArtist, orderByAlbum, orderByYear);
                
        JSoundsMainWindowViewController.removeAllElementsInContainer();
        
        DefaultTableModel model = new DefaultTableModel(createDataForAlbumResults(orderByArtist,orderByAlbum,orderByYear,true,album), columnNames);
        table.setModel(model);
        setTableWithData();
        
        DefaultTableModel dm = (DefaultTableModel) JSoundsMainWindowViewController.table.getModel();
        int i = dm.getRowCount() - 1;
        boolean found = false;
        
        while (!found && i >= 0)
        {
            JMusicList actualList = (JMusicList) dm.getValueAt(i, JSoundsMainWindowViewController.LIST_SONG_COLUMN);
            
            if (actualList.equals(JSoundsMainWindowViewController.jlActualListSongs))
            {
                JSoundsMainWindowViewController.jlActualListSongs = actualList;
                found = true;
            }
            else
                i--;
        } 
        
        if (found)
            MusicPlayerControl.updateContainerInformation(jlActualListSongs, jlActualListSongs.getjLLastPlayedSongList(), table, JSoundsMainWindowViewController.jlActualListSongs.getRowInJTable());
    }
        
              /**
               * Create the data of the finded albums.
               * @param orderByArtist
               * @param orderByAlbum
               * @param orderByYear
               * @param uptadeJTable
               * @param album
               * @return 
               */
     private static Object[][] createDataForAlbumResults(boolean orderByArtist, boolean orderByAlbum, boolean orderByYear, boolean uptadeJTable, Album album)
    {
        ImageIcon albumIcon;

        Object[][] data;
        JList jLAlbumInfo, jLSongNumber, jLListGenders, jLListLastPlayed;
        JSoundsMainWindowViewController.listSizePerRow  = new ListSizePerRow();
        JLabel iconLabel                                = null;
        DefaultListModel listModel                      = null;
        //JSoundsMainWindowViewController.table           = null;
        
        ListAlbum listAlbum = new ListAlbum(orderByArtist, orderByAlbum, orderByYear);
        listAlbum.addAlbum(album);
        //AlbumXmlFile.readAllAlbumsFromDataBase(listAlbum, true);
        
        data = new Object[listAlbum.size()][JSoundsMainWindowViewController.TOTAL_COLUMNS];
        
        int actualAlbumIndex = 0;
        
        Album actualAlbum;
        
        Iterator albumIterator = listAlbum.iterator();
        
        //JSoundsMainWindowViewController.jPListContainer.removeAll();
        
        while (albumIterator.hasNext())
        {
            actualAlbum = (Album) albumIterator.next();
            
            /* Se genera la imagen del album */
            String path = Util.JSOUNDS_LIBRARY_PATH + UtilFunctions.removeStrangeCharacters(actualAlbum.getArtist().getName()) + "/" + UtilFunctions.removeStrangeCharacters(actualAlbum.getName()) + "/cover.jpg";
            /* Se genera un icono con el path del archivo */
            albumIcon = new ImageIcon(path);
            /* Se crea un label */
            iconLabel = new JLabel();
            /* Se configura el tamaño del label */
            iconLabel.setSize(albumIcon.getIconWidth(), albumIcon.getIconHeight());
            /* Se introduce la imagen en el label */
            iconLabel.setIcon(albumIcon);
            /* Se alina el label en el tope */
            iconLabel.setVerticalAlignment(JLabel.TOP);


            /* Se genera la informacion del album */
            /* Se crea una lista */
            jLAlbumInfo = new JList();

            /* Se crea un modelo para la lista. Siempre se necesita un modelo para un JList */
            listModel = new DefaultListModel();
            /* Se indica que el modelo de la lista es el definido */
            jLAlbumInfo.setModel(listModel);

            /* Se añaden campos a la lista en cada posicion */
            listModel.add(0, actualAlbum.getName());
            listModel.add(1, actualAlbum.getArtist().getName());
            listModel.add(2, " ");
            listModel.add(3, actualAlbum.getYear());
            listModel.add(4, " ");

            /* Se establece cual es la imagen asociada al score del album */
            StarScore starScore = new StarScore(UtilFunctions.stringToInteger(actualAlbum.getScore()));
            listModel.add(5, starScore.getImage());

            /* Se genera una lista para los números de las canciones */
            jLSongNumber = new JList();

            /* Se crea un modelo para la lista. Siempre se necesita un modelo para un JList */
            listModel = new DefaultListModel();
            /* Se indica que el modelo de la lista es el definido */
            jLSongNumber.setModel(listModel);
            
            jLListLastPlayed = new JList();
            DefaultListModel listLasPlayedModel = new DefaultListModel();
            jLListLastPlayed.setModel(listLasPlayedModel);
            

            /* Se genera un iterador para la lista de canciones del album */
            Iterator songsIterator = actualAlbum.getSongs().iterator();
            int actualSongIndex = 0;

            while (songsIterator.hasNext())
            {
                /* Se añade el número de la canción al modelo de la lista */
                Song song = (Song) songsIterator.next();
                listModel.add(actualSongIndex, UtilFunctions.integerToString(song.getNumber()));

                listLasPlayedModel.add(actualSongIndex, " ");
                actualSongIndex++;
            }


            /* Se degine una lista de tipo JSoundsList */
            /* Es un JList pero que tiene dos campos adicioanles: nombre del album y nombre del artista */
            
            JMusicList jLListSongs;
            jLListSongs = new JMusicList(actualAlbum.getArtist().getName(), actualAlbum.getName(), actualAlbumIndex, jLListLastPlayed);

            /* Se crea un modelo para la lista */
            listModel = new DefaultListModel();
            jLListSongs.setModel(listModel);

            /* Se recorre la lista */
            songsIterator = actualAlbum.getSongs().iterator();
            actualSongIndex = 0;

            while (songsIterator.hasNext())
            {
                /* Se añade el nombre de cada cancion del album */
                Song song = (Song) songsIterator.next();
                listModel.add(actualSongIndex, song.getName());

                actualSongIndex++;
            }


            JSoundsMainWindowViewController.isSelected = false;
            /* Se añade un evento a la lista */
            /* El evento se dispara cada vez que se selecciona un elemento de la lista */
            jLListSongs.addListSelectionListener(new ListSelectionListener() 
            {
                @Override
                public void valueChanged(ListSelectionEvent le) {
                    /* Se obtiene cual fue la lista que disparo el evento */
                    JMusicList list = (JMusicList) le.getSource();

                    /* Si la lista existe y no ha sido seleccionada aun */
                    if (list != null && JSoundsMainWindowViewController.isSelected)
                    {
                        /* Se obtiene el indice seleccionado. Si es distinto de -1 algo se selecciono */
                        int idx = list.getSelectedIndex();
                        if (idx != -1)
                        {
                            /* Si se selecciono la lista de otro album, se borra la seleccion de la lista anterior */
                            if (JSoundsMainWindowViewController.jlActualListSongs != null &&
                                !JSoundsMainWindowViewController.jlActualListSongs.equals(list))
                                JSoundsMainWindowViewController.jlActualListSongs.clearSelection();

                            /* La lista actual es la seleccionada */
                            JSoundsMainWindowViewController.jlActualListSongs = list;                                
                        }

                        JSoundsMainWindowViewController.isSelected = false;
                    }
                    else
                    {
                        if (!JSoundsMainWindowViewController.isSelected)
                            JSoundsMainWindowViewController.isSelected = true;
                    }
                }
              });

            jLListSongs.addMouseListener(new MouseAdapter() 
            {
                @Override
                public void mouseClicked(MouseEvent evt) 
                {
                    JList list = (JList)evt.getSource();
                    
                    if ((evt.getClickCount() == 1)&& alreadyPlaying)
                    {
                        JSoundsMainWindowViewController.fillEditWindow(JSoundsMainWindowViewController.jlActualListSongs.getSelectedIndex());
                        
                        songName=listSongs.getSongAtIndex(JSoundsMainWindowViewController.jlActualListSongs.getSelectedIndex()).getSongName();
                    }
                    if (evt.getClickCount() == 2) 
                    {
                        fixedIndex= false;
                        if (alreadyPlaying == false)
                        {                            
                            playListSongs(true);  
                        }
                        else
                        {
                            if (iAmPlaying==true)
                            {
                            JSoundsMainWindowViewController.stopSong();
                            playListSongs(true);
                            }
                            if (iAmPausing==true)
                            {
                            JSoundsMainWindowViewController.stopSong();
                            playListSongs(true);  
                            }
                            if (iAmResuming == true)
                            {
                            JSoundsMainWindowViewController.stopSong();
                            fixedIndex=true;
                            playListSongs(true);   
                            }
                        }
                    }
                }   
            });

            /* Para cada fila (album) se guarda el tamaño (número de canciones) */
            listSizePerRow.addSize(jLListSongs.getModel().getSize());


            /* Se genera una lista para manejar los géneros */
            jLListGenders = new JList();

            /* Se crea el modelo asociado a la lista */
            listModel = new DefaultListModel();
            jLListGenders.setModel(listModel);

            /* Se recorre la lista de canciones */
            songsIterator = actualAlbum.getSongs().iterator();
            actualSongIndex = 0;

            while (songsIterator.hasNext())
            {
                /* A todas las canciones se les asigna el midmo género */
                /* ESTO ES UN EJEMPLO */
                Song song = (Song) songsIterator.next();
                if (song.getGender()=="")
                    listModel.add(actualSongIndex, "Gender");
                else
                    listModel.add(actualSongIndex, song.getGender());

                actualSongIndex++;
            }

            /* Se añade cada campo a una fila nueva de la matriz de datos */
            data[actualAlbumIndex][JSoundsMainWindowViewController.ALBUM_COVER_COLUMN] = iconLabel;
            data[actualAlbumIndex][JSoundsMainWindowViewController.ALBUM_INFO_COLUMN] = jLAlbumInfo;
            data[actualAlbumIndex][JSoundsMainWindowViewController.NUMBER_SONG_COLUMN] = jLSongNumber;
            data[actualAlbumIndex][JSoundsMainWindowViewController.LIST_SONG_COLUMN] = jLListSongs;
            data[actualAlbumIndex][JSoundsMainWindowViewController.GENDER_SONG_COLUMN] = jLListGenders;
            data[actualAlbumIndex][JSoundsMainWindowViewController.LAST_PLAYED_SONG_COLUMN] = jLListLastPlayed;
            
            
            if (uptadeJTable)
            {
                DefaultTableModel dm = (DefaultTableModel) JSoundsMainWindowViewController.table.getModel();
                dm.addRow(new Object[]{  data[actualAlbumIndex][JSoundsMainWindowViewController.ALBUM_COVER_COLUMN],
                                            data[actualAlbumIndex][JSoundsMainWindowViewController.ALBUM_INFO_COLUMN],
                                            data[actualAlbumIndex][JSoundsMainWindowViewController.NUMBER_SONG_COLUMN],
                                            data[actualAlbumIndex][JSoundsMainWindowViewController.LIST_SONG_COLUMN],
                                            data[actualAlbumIndex][JSoundsMainWindowViewController.GENDER_SONG_COLUMN],
                                            data[actualAlbumIndex][JSoundsMainWindowViewController.LAST_PLAYED_SONG_COLUMN]});            
            }
            actualAlbumIndex++;
        }
        
        return data;
    }
     
     /**
      * Show the songs finded.
      * @param orderByArtist
      * @param orderByAlbum
      * @param orderByYear
      * @param album
      * @param song
      * @param indexSong 
      */
      public static void showSongResults(boolean orderByArtist, boolean orderByAlbum, boolean orderByYear, Album album, Song song, int indexSong)
    {
        updateOrderButtons(orderByArtist, orderByAlbum, orderByYear);
                
        JSoundsMainWindowViewController.removeAllElementsInContainer();
        
        DefaultTableModel model = new DefaultTableModel(createDataForSongResults(orderByArtist,orderByAlbum,orderByYear,true,album,song, indexSong), columnNames);
        table.setModel(model);
        setTableWithData();
        
        DefaultTableModel dm = (DefaultTableModel) JSoundsMainWindowViewController.table.getModel();
        int i = dm.getRowCount() - 1;
        boolean found = false;
        
        while (!found && i >= 0)
        {
            JMusicList actualList = (JMusicList) dm.getValueAt(i, JSoundsMainWindowViewController.LIST_SONG_COLUMN);
            
            if (actualList.equals(JSoundsMainWindowViewController.jlActualListSongs))
            {
                JSoundsMainWindowViewController.jlActualListSongs = actualList;
                found = true;
            }
            else
                i--;
        } 
        
        if (found)
            MusicPlayerControl.updateContainerInformation(jlActualListSongs, jlActualListSongs.getjLLastPlayedSongList(), table, JSoundsMainWindowViewController.jlActualListSongs.getRowInJTable());
    }
        
      /**
       * Create data for the songs finded.
       * @param orderByArtist
       * @param orderByAlbum
       * @param orderByYear
       * @param uptadeJTable
       * @param album
       * @param song
       * @param indexSong
       * @return 
       */
    private static Object[][] createDataForSongResults(boolean orderByArtist, boolean orderByAlbum, boolean orderByYear, boolean uptadeJTable, Album album, Song song, int indexSong)
    {
        ImageIcon albumIcon;

        Object[][] data;
        JList jLAlbumInfo, jLSongNumber, jLListGenders, jLListLastPlayed;
        JSoundsMainWindowViewController.listSizePerRow  = new ListSizePerRow();
        JLabel iconLabel                                = null;
        DefaultListModel listModel                      = null;
        //JSoundsMainWindowViewController.table           = null;
        
        ListAlbum listAlbum = new ListAlbum(orderByArtist, orderByAlbum, orderByYear);
        listAlbum.addAlbum(album);
        //AlbumXmlFile.readAllAlbumsFromDataBase(listAlbum, true);
        
        data = new Object[listAlbum.size()][JSoundsMainWindowViewController.TOTAL_COLUMNS];
        
        int actualAlbumIndex = 0;
        
        Album actualAlbum;
        
        Iterator albumIterator = listAlbum.iterator();
        
        
        //JSoundsMainWindowViewController.jPListContainer.removeAll();
        
        while (albumIterator.hasNext())
        {
            actualAlbum = (Album) albumIterator.next();
            
            /* Se genera la imagen del album */
            String path = Util.JSOUNDS_LIBRARY_PATH + UtilFunctions.removeStrangeCharacters(actualAlbum.getArtist().getName()) + "/" + UtilFunctions.removeStrangeCharacters(actualAlbum.getName()) + "/cover.jpg";
            /* Se genera un icono con el path del archivo */
            albumIcon = new ImageIcon(path);
            /* Se crea un label */
            iconLabel = new JLabel();
            /* Se configura el tamaño del label */
            iconLabel.setSize(albumIcon.getIconWidth(), albumIcon.getIconHeight());
            /* Se introduce la imagen en el label */
            iconLabel.setIcon(albumIcon);
            /* Se alina el label en el tope */
            iconLabel.setVerticalAlignment(JLabel.TOP);


            /* Se genera la informacion del album */
            /* Se crea una lista */
            jLAlbumInfo = new JList();

            /* Se crea un modelo para la lista. Siempre se necesita un modelo para un JList */
            listModel = new DefaultListModel();
            /* Se indica que el modelo de la lista es el definido */
            jLAlbumInfo.setModel(listModel);

            /* Se añaden campos a la lista en cada posicion */
            listModel.add(0, actualAlbum.getName());
            listModel.add(1, actualAlbum.getArtist().getName());
            listModel.add(2, " ");
            listModel.add(3, actualAlbum.getYear());
            listModel.add(4, " ");

            /* Se establece cual es la imagen asociada al score del album */
            StarScore starScore = new StarScore(UtilFunctions.stringToInteger(actualAlbum.getScore()));
            listModel.add(5, starScore.getImage());

            /* Se genera una lista para los números de las canciones */
            jLSongNumber = new JList();

            /* Se crea un modelo para la lista. Siempre se necesita un modelo para un JList */
            listModel = new DefaultListModel();
            /* Se indica que el modelo de la lista es el definido */
            jLSongNumber.setModel(listModel);
            
            jLListLastPlayed = new JList();
            DefaultListModel listLasPlayedModel = new DefaultListModel();
            jLListLastPlayed.setModel(listLasPlayedModel);
            

            /* Se genera un iterador para la lista de canciones del album */
            //Iterator songsIterator = actualAlbum.getSongs().iterator();
            int actualSongIndex = 0;

            //while (songsIterator.hasNext())
            {
                /* Se añade el número de la canción al modelo de la lista */

                listModel.add(actualSongIndex, UtilFunctions.integerToString(song.getNumber()));

                listLasPlayedModel.add(actualSongIndex, " ");
                actualSongIndex++;
            }


            /* Se degine una lista de tipo JSoundsList */
            /* Es un JList pero que tiene dos campos adicioanles: nombre del album y nombre del artista */
            
            JMusicList jLListSongs;
            jLListSongs = new JMusicList(actualAlbum.getArtist().getName(), actualAlbum.getName(), actualAlbumIndex, jLListLastPlayed);

            /* Se crea un modelo para la lista */
            listModel = new DefaultListModel();
            jLListSongs.setModel(listModel);

            /* Se recorre la lista */
            //songsIterator = actualAlbum.getSongs().iterator();
            actualSongIndex = 0;

            //while (songsIterator.hasNext())
            {
                /* Se añade el nombre de cada cancion del album */
                //song = (Song) songsIterator.next();
                listModel.add(actualSongIndex, song.getName());

                actualSongIndex++;
            }


            JSoundsMainWindowViewController.isSelected = false;
            /* Se añade un evento a la lista */
            /* El evento se dispara cada vez que se selecciona un elemento de la lista */
            jLListSongs.addListSelectionListener(new ListSelectionListener() 
            {
                @Override
                public void valueChanged(ListSelectionEvent le) {
                    /* Se obtiene cual fue la lista que disparo el evento */
                    JMusicList list = (JMusicList) le.getSource();

                    /* Si la lista existe y no ha sido seleccionada aun */
                    if (list != null && JSoundsMainWindowViewController.isSelected)
                    {
                        /* Se obtiene el indice seleccionado. Si es distinto de -1 algo se selecciono */
                        int idx = list.getSelectedIndex();
                        if (idx != -1)
                        {
                            /* Si se selecciono la lista de otro album, se borra la seleccion de la lista anterior */
                            if (JSoundsMainWindowViewController.jlActualListSongs != null &&
                                !JSoundsMainWindowViewController.jlActualListSongs.equals(list))
                                JSoundsMainWindowViewController.jlActualListSongs.clearSelection();

                            /* La lista actual es la seleccionada */
                            JSoundsMainWindowViewController.jlActualListSongs = list;                                
                        }

                        JSoundsMainWindowViewController.isSelected = false;
                    }
                    else
                    {
                        if (!JSoundsMainWindowViewController.isSelected)
                            JSoundsMainWindowViewController.isSelected = true;
                    }
                }
              });

            jLListSongs.addMouseListener(new MouseAdapter() 
            {
                @Override
                public void mouseClicked(MouseEvent evt) 
                {
                    JList list = (JList)evt.getSource();
                    
                    if ((evt.getClickCount() == 1)&& alreadyPlaying)
                    {
                        JSoundsMainWindowViewController.fillEditWindow(JSoundsMainWindowViewController.jlActualListSongs.getSelectedIndex());
                        
                        songName=listSongs.getSongAtIndex(JSoundsMainWindowViewController.jlActualListSongs.getSelectedIndex()).getSongName();
                    }
                    if (evt.getClickCount() == 2) 
                    {
                        fixedIndex= false;
                        if (alreadyPlaying == false)
                        {                            
                            playListSongs(true);  
                        }
                        else
                        {
                            if (iAmPlaying==true)
                            {
                            JSoundsMainWindowViewController.stopSong();
                            playListSongs(true);
                            }
                            if (iAmPausing==true)
                            {
                            JSoundsMainWindowViewController.stopSong();
                            playListSongs(true);  
                            }
                            if (iAmResuming == true)
                            {
                            JSoundsMainWindowViewController.stopSong();
                            fixedIndex=true;
                            playListSongs(true);   
                            }
                        }
                    }
                }   
            });

            /* Para cada fila (album) se guarda el tamaño (número de canciones) */
            listSizePerRow.addSize(jLListSongs.getModel().getSize());


            /* Se genera una lista para manejar los géneros */
            jLListGenders = new JList();

            /* Se crea el modelo asociado a la lista */
            listModel = new DefaultListModel();
            jLListGenders.setModel(listModel);

            /* Se recorre la lista de canciones */
            //songsIterator = actualAlbum.getSongs().iterator();
            actualSongIndex = 0;

            //while (songsIterator.hasNext())
            {
                /* A todas las canciones se les asigna el midmo género */
                /* ESTO ES UN EJEMPLO */
                //song = (Song) songsIterator.next();
                //listModel.add(actualSongIndex, "Gender");
          
                if (song.getGender()=="")
                    listModel.add(actualSongIndex, "Gender");
                else
                    listModel.add(actualSongIndex, song.getGender());

                actualSongIndex++;
            }

            /* Se añade cada campo a una fila nueva de la matriz de datos */
            data[actualAlbumIndex][JSoundsMainWindowViewController.ALBUM_COVER_COLUMN] = iconLabel;
            data[actualAlbumIndex][JSoundsMainWindowViewController.ALBUM_INFO_COLUMN] = jLAlbumInfo;
            data[actualAlbumIndex][JSoundsMainWindowViewController.NUMBER_SONG_COLUMN] = jLSongNumber;
            data[actualAlbumIndex][JSoundsMainWindowViewController.LIST_SONG_COLUMN] = jLListSongs;
            data[actualAlbumIndex][JSoundsMainWindowViewController.GENDER_SONG_COLUMN] = jLListGenders;
            data[actualAlbumIndex][JSoundsMainWindowViewController.LAST_PLAYED_SONG_COLUMN] = jLListLastPlayed;
            
            
            if (uptadeJTable)
            {
                DefaultTableModel dm = (DefaultTableModel) JSoundsMainWindowViewController.table.getModel();
                dm.addRow(new Object[]{  data[actualAlbumIndex][JSoundsMainWindowViewController.ALBUM_COVER_COLUMN],
                                            data[actualAlbumIndex][JSoundsMainWindowViewController.ALBUM_INFO_COLUMN],
                                            data[actualAlbumIndex][JSoundsMainWindowViewController.NUMBER_SONG_COLUMN],
                                            data[actualAlbumIndex][JSoundsMainWindowViewController.LIST_SONG_COLUMN],
                                            data[actualAlbumIndex][JSoundsMainWindowViewController.GENDER_SONG_COLUMN],
                                            data[actualAlbumIndex][JSoundsMainWindowViewController.LAST_PLAYED_SONG_COLUMN]});            
            }
            actualAlbumIndex++;
        }
        
        return data;
    }
     
}
