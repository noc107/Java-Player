/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.JSoundsMainWindowViewController;
import javax.swing.JOptionPane;
import jmusic.JMusicPlayerList;
import jmusic.MusicPlayerControl;

/**
 * @author Anibal Dominguez
 * @author Manuel Pacheco
 * @author José Suárez
 */
public class JSoundsMainWindow extends MyOwnJFrame
{
    public static int playListNumber;
    /**
     * Creates new form JSoundsMainWindow
     */
    public JSoundsMainWindow()
    {
        initComponents();
        initMyOwnComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBNext = new javax.swing.JButton();
        jBPrev = new javax.swing.JButton();
        jPListContainer = new javax.swing.JPanel();
        jLInformationSong = new javax.swing.JLabel();
        jTBOrderByArtistName = new javax.swing.JToggleButton();
        jTBOrderByAlbumName = new javax.swing.JToggleButton();
        jTBOrderByYear = new javax.swing.JToggleButton();
        jFileChooser1 = new javax.swing.JFileChooser();
        jBPlay = new javax.swing.JButton();
        jfcOneFile = new javax.swing.JFileChooser();
        jtbRepeatSongs = new javax.swing.JToggleButton();
        jtfSearchSong = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jmiAbout = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenu2 = new javax.swing.JMenu();
        jmiAddOneFile = new javax.swing.JMenuItem();
        jmiAddMusic = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmiOpenEditSongWindow = new javax.swing.JMenuItem();
        List = new javax.swing.JMenu();
        jmiAddSongToNewPlaylist = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jmiSelectPlaylist = new javax.swing.JMenuItem();
        jmiAddSongToList = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jmiLoadPlaylist = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jmiLoadAllSongs = new javax.swing.JMenuItem();
        jmGenius = new javax.swing.JMenu();
        jmiActivateGenius = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 22, 1280, 728));
        setMinimumSize(new java.awt.Dimension(1280, 728));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        jBNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/IBNext.png"))); // NOI18N
        jBNext.setBorder(null);
        jBNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBNextActionPerformed(evt);
            }
        });
        getContentPane().add(jBNext);
        jBNext.setBounds(100, 17, 31, 31);

        jBPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/IBPrev.png"))); // NOI18N
        jBPrev.setBorder(null);
        jBPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPrevActionPerformed(evt);
            }
        });
        getContentPane().add(jBPrev);
        jBPrev.setBounds(10, 17, 31, 31);

        jPListContainer.setMaximumSize(new java.awt.Dimension(1280, 590));
        jPListContainer.setMinimumSize(new java.awt.Dimension(1280, 590));
        jPListContainer.setPreferredSize(new java.awt.Dimension(1280, 590));
        getContentPane().add(jPListContainer);
        jPListContainer.setBounds(0, 70, 1280, 590);

        jLInformationSong.setBackground(new java.awt.Color(204, 204, 204));
        jLInformationSong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLInformationSong.setOpaque(true);
        getContentPane().add(jLInformationSong);
        jLInformationSong.setBounds(350, 10, 530, 50);

        jTBOrderByArtistName.setText("artist");
        jTBOrderByArtistName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTBOrderByArtistNameActionPerformed(evt);
            }
        });
        getContentPane().add(jTBOrderByArtistName);
        jTBOrderByArtistName.setBounds(220, 30, 70, 23);

        jTBOrderByAlbumName.setText("album");
        jTBOrderByAlbumName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTBOrderByAlbumNameActionPerformed(evt);
            }
        });
        getContentPane().add(jTBOrderByAlbumName);
        jTBOrderByAlbumName.setBounds(150, 30, 70, 23);

        jTBOrderByYear.setText("year");
        jTBOrderByYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTBOrderByYearActionPerformed(evt);
            }
        });
        getContentPane().add(jTBOrderByYear);
        jTBOrderByYear.setBounds(280, 30, 70, 23);

        jFileChooser1.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        jFileChooser1.setPreferredSize(new java.awt.Dimension(700, 600));
        getContentPane().add(jFileChooser1);
        jFileChooser1.setBounds(0, 0, 0, 0);

        jBPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/IBPlay.png"))); // NOI18N
        jBPlay.setToolTipText("");
        jBPlay.setBorder(null);
        jBPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPlayActionPerformed(evt);
            }
        });
        getContentPane().add(jBPlay);
        jBPlay.setBounds(40, 10, 60, 50);
        getContentPane().add(jfcOneFile);
        jfcOneFile.setBounds(0, 107, 0, 0);

        jtbRepeatSongs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/IBRepeat.png"))); // NOI18N
        jtbRepeatSongs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbRepeatSongsActionPerformed(evt);
            }
        });
        getContentPane().add(jtbRepeatSongs);
        jtbRepeatSongs.setBounds(880, 10, 30, 50);

        jtfSearchSong.setToolTipText("Search Song");
        jtfSearchSong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfSearchSongActionPerformed(evt);
            }
        });
        getContentPane().add(jtfSearchSong);
        jtfSearchSong.setBounds(1020, 30, 180, 20);

        jMenuBar1.setBackground(new java.awt.Color(204, 204, 204));

        jMenu1.setBackground(new java.awt.Color(204, 204, 204));
        jMenu1.setText("JSounds");
        jMenu1.add(jSeparator2);

        jmiAbout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jmiAbout.setText("About JSounds");
        jmiAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAboutActionPerformed(evt);
            }
        });
        jMenu1.add(jmiAbout);
        jMenu1.add(jSeparator3);

        jMenuBar1.add(jMenu1);

        jMenu2.setBackground(new java.awt.Color(204, 204, 204));
        jMenu2.setText("Music");

        jmiAddOneFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jmiAddOneFile.setText("Add Music File");
        jmiAddOneFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAddOneFileActionPerformed(evt);
            }
        });
        jMenu2.add(jmiAddOneFile);

        jmiAddMusic.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jmiAddMusic.setText("Add Music Folder");
        jmiAddMusic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAddMusicActionPerformed(evt);
            }
        });
        jMenu2.add(jmiAddMusic);
        jMenu2.add(jSeparator1);

        jmiOpenEditSongWindow.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jmiOpenEditSongWindow.setText("Edit Selected Song");
        jmiOpenEditSongWindow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiOpenEditSongWindowActionPerformed(evt);
            }
        });
        jMenu2.add(jmiOpenEditSongWindow);

        jMenuBar1.add(jMenu2);

        List.setText("List");
        List.setActionCommand("jmList");
        List.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListActionPerformed(evt);
            }
        });

        jmiAddSongToNewPlaylist.setText("Add Song To NEW Playlist");
        jmiAddSongToNewPlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAddSongToNewPlaylistActionPerformed(evt);
            }
        });
        List.add(jmiAddSongToNewPlaylist);
        List.add(jSeparator4);

        jmiSelectPlaylist.setText("Select Playlist To Edit...");
        jmiSelectPlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSelectPlaylistActionPerformed(evt);
            }
        });
        List.add(jmiSelectPlaylist);

        jmiAddSongToList.setText("Add Song To Selected Playlist");
        jmiAddSongToList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAddSongToListActionPerformed(evt);
            }
        });
        List.add(jmiAddSongToList);
        List.add(jSeparator5);

        jmiLoadPlaylist.setText("Load Playlist...");
        jmiLoadPlaylist.setActionCommand("");
        jmiLoadPlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiLoadPlaylistActionPerformed(evt);
            }
        });
        List.add(jmiLoadPlaylist);
        List.add(jSeparator6);

        jmiLoadAllSongs.setText("Load All Songs");
        jmiLoadAllSongs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiLoadAllSongsActionPerformed(evt);
            }
        });
        List.add(jmiLoadAllSongs);

        jMenuBar1.add(List);

        jmGenius.setText("Genius");

        jmiActivateGenius.setText("Activate Genius");
        jmiActivateGenius.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiActivateGeniusActionPerformed(evt);
            }
        });
        jmGenius.add(jmiActivateGenius);

        jMenuBar1.add(jmGenius);

        setJMenuBar(jMenuBar1);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-1280)/2, (screenSize.height-728)/2, 1280, 728);
    }// </editor-fold>//GEN-END:initComponents

    private void jBNextActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jBNextActionPerformed
    {//GEN-HEADEREND:event_jBNextActionPerformed
        JSoundsMainWindowViewController.nextSong();
    }//GEN-LAST:event_jBNextActionPerformed

    private void jBPrevActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jBPrevActionPerformed
    {//GEN-HEADEREND:event_jBPrevActionPerformed
        JSoundsMainWindowViewController.previusSong();
    }//GEN-LAST:event_jBPrevActionPerformed

    private void jTBOrderByArtistNameActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTBOrderByArtistNameActionPerformed
    {//GEN-HEADEREND:event_jTBOrderByArtistNameActionPerformed
        JSoundsMainWindowViewController.orderBy(true, false, false);
    }//GEN-LAST:event_jTBOrderByArtistNameActionPerformed

    private void jTBOrderByAlbumNameActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTBOrderByAlbumNameActionPerformed
    {//GEN-HEADEREND:event_jTBOrderByAlbumNameActionPerformed
        JSoundsMainWindowViewController.orderBy(false, true, false);
    }//GEN-LAST:event_jTBOrderByAlbumNameActionPerformed

    private void jTBOrderByYearActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTBOrderByYearActionPerformed
    {//GEN-HEADEREND:event_jTBOrderByYearActionPerformed
        JSoundsMainWindowViewController.orderBy(false, false, true);
    }//GEN-LAST:event_jTBOrderByYearActionPerformed

    private void jmiAddMusicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAddMusicActionPerformed
       JSoundsMainWindowViewController.processDirectorySelecction();
    }//GEN-LAST:event_jmiAddMusicActionPerformed

    private void jmiOpenEditSongWindowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiOpenEditSongWindowActionPerformed
        JSoundsMainWindowViewController.goToEditSongWindow();
    }//GEN-LAST:event_jmiOpenEditSongWindowActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

    private void jmiAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAboutActionPerformed
        JSoundsMainWindowViewController.goToAboutWindow();
    }//GEN-LAST:event_jmiAboutActionPerformed

    private void jmiAddOneFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAddOneFileActionPerformed
        JSoundsMainWindowViewController.processOneFileSelecction();       
    }//GEN-LAST:event_jmiAddOneFileActionPerformed

    private void jtbRepeatSongsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbRepeatSongsActionPerformed
        JSoundsMainWindowViewController.repeatFromTheBeginning();
    }//GEN-LAST:event_jtbRepeatSongsActionPerformed

    private void jBPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPlayActionPerformed
        JSoundsMainWindowViewController.playListSongs(false);
    }//GEN-LAST:event_jBPlayActionPerformed

    private void jmiAddSongToListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAddSongToListActionPerformed
        JSoundsMainWindowViewController.saveSongInPlaylist(playListNumber);
    }//GEN-LAST:event_jmiAddSongToListActionPerformed

    private void ListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListActionPerformed

    }//GEN-LAST:event_ListActionPerformed

    private void jmiLoadPlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiLoadPlaylistActionPerformed
        PlaylistChooser.loadPlaylist=true;
        JSoundsMainWindowViewController.goToPlaylistChooserWindow();
    }//GEN-LAST:event_jmiLoadPlaylistActionPerformed

    private void jmiSelectPlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSelectPlaylistActionPerformed
        JSoundsMainWindowViewController.goToPlaylistChooserWindow();
    }//GEN-LAST:event_jmiSelectPlaylistActionPerformed

    private void jmiLoadAllSongsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiLoadAllSongsActionPerformed
        JSoundsMainWindowViewController.loadAllSongs();
    }//GEN-LAST:event_jmiLoadAllSongsActionPerformed

    private void jtfSearchSongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSearchSongActionPerformed
        String textToSearch = jtfSearchSong.getText();
        JSoundsMainWindowViewController.search(textToSearch);
    }//GEN-LAST:event_jtfSearchSongActionPerformed

    private void jmiAddSongToNewPlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAddSongToNewPlaylistActionPerformed
        JSoundsMainWindowViewController.saveSongInNewPlaylist();
    }//GEN-LAST:event_jmiAddSongToNewPlaylistActionPerformed

    private void jmiActivateGeniusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiActivateGeniusActionPerformed
        JSoundsMainWindowViewController.newGenius();
    }//GEN-LAST:event_jmiActivateGeniusActionPerformed

    private void initMyOwnComponents()
    {
        JSoundsMainWindowViewController.initOutlets(this.jPListContainer, this.jLInformationSong, this.jBPlay, this.jTBOrderByAlbumName, this.jTBOrderByArtistName, 
                this.jTBOrderByYear, this.jFileChooser1, this, this.jfcOneFile,this.jtbRepeatSongs);
        JSoundsMainWindowViewController.loadAllAlbumsInfoInContainer(true,false, false);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(JSoundsMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(JSoundsMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(JSoundsMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(JSoundsMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new JSoundsMainWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu List;
    private javax.swing.JButton jBNext;
    private javax.swing.JButton jBPlay;
    private javax.swing.JButton jBPrev;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLInformationSong;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPListContainer;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JToggleButton jTBOrderByAlbumName;
    private javax.swing.JToggleButton jTBOrderByArtistName;
    private javax.swing.JToggleButton jTBOrderByYear;
    private javax.swing.JFileChooser jfcOneFile;
    private javax.swing.JMenu jmGenius;
    private javax.swing.JMenuItem jmiAbout;
    private javax.swing.JMenuItem jmiActivateGenius;
    private javax.swing.JMenuItem jmiAddMusic;
    private javax.swing.JMenuItem jmiAddOneFile;
    private javax.swing.JMenuItem jmiAddSongToList;
    private javax.swing.JMenuItem jmiAddSongToNewPlaylist;
    private javax.swing.JMenuItem jmiLoadAllSongs;
    private javax.swing.JMenuItem jmiLoadPlaylist;
    private javax.swing.JMenuItem jmiOpenEditSongWindow;
    private javax.swing.JMenuItem jmiSelectPlaylist;
    private javax.swing.JToggleButton jtbRepeatSongs;
    public static javax.swing.JTextField jtfSearchSong;
    // End of variables declaration//GEN-END:variables
}
