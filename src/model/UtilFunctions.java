package model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author Anibal Dominguez
 * @author Manuel Pacheco
 * @author José Suárez
 */
public class UtilFunctions {
    
        private static String sourcePath;
        private static String destName;
        private static Artist artist = new Artist ("Unknown Artist",1);
        private static boolean repeatedSong=true;
        /**
         * Method to copy files to the jSounds Library directory
         * @param sourcePath
         * @param destName 
         */
       public static void copyToJSoundsLibrary(String sourcePath, String destName)
    {
        
        File source = new File(sourcePath);
        File destination = new File   ("C:/Users/AEDM/Desktop/jSounds Library/Unknown Artist/Unknown Album/" + destName);
        
        
              
        try
        {
            UtilFunctions.copyFile(source, destination);
        }
        catch (IOException e)
        {
            
        }
        
        //Windows directory example
        final String directoryWindows ="C:/Users/AEDM/Desktop";

    }
    
    /**
     * Method to check the extension of a file
     * @param fileName
     * @return 
     */

     public static boolean isMP3 (String fileName)
     {
            int checkFileExtension= fileName.lastIndexOf(".");
            String extension=fileName.substring(checkFileExtension+1,fileName.length());
           if (extension.equals("mp3")) 
           {
            return true;
           }
        return false;
     }

     /**
      * Method to Remove the extension in the file name
      * @param fileName
      * @return 
      */
      public static String removeExtension (String fileName)
     {
         
         int pos = fileName.lastIndexOf(".");
         if (pos > 0)
         {
             fileName = fileName.substring(0,pos);
         }
         return fileName;  
         
     }
      

     /**
      * Method to copy files
      * @param sourceFile
      * @param destFile
      * @throws IOException 
      */
    public static void copyFile(File sourceFile, File destFile) throws IOException
    {
        if(!destFile.exists()) {
            destFile.createNewFile();
            repeatedSong = false;            
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        }
        finally {
            if(source != null) {
                source.close();
            }
            if(destination != null) {
                destination.close();
            }
        }
    }
    
    /**
     * List all the files and folders from a directory
     * @param directoryName to be listed
     */
    public static void listFilesAndFolders(String directoryName)
    {
 
        File directory = new File(directoryName);
 
        //get all the files from a directory
        File[] fList = directory.listFiles();
 
        for (File file : fList){
            System.out.println(file.getName());
        }
    }
 
    /**
     * List all the files under a directory
     * @param directoryName to be listed
     */
    public static void listFiles(String directoryName)
    {
 
        File directory = new File(directoryName);
 
        //get all the files from a directory
        File[] fList = directory.listFiles();
 
        for (File file : fList){
            if (file.isFile()){
                System.out.println(file.getName());
            }
        }
    }
 
    /**
     * List all the folder under a directory
     * @param directoryName to be listed
     */
    public static void listFolders(String directoryName)
    {
 
        File directory = new File(directoryName);
 
        //get all the files from a directory
        File[] fList = directory.listFiles();
 
        for (File file : fList){
            if (file.isDirectory()){
                System.out.println(file.getName());
            }
        }
    }
 
    /**
     * List all files from a directory and its subdirectories
     * @param directoryName to be listed
     */
    public static void listFilesAndFilesSubDirectories(String directoryName)
    {
        File directory = new File(directoryName);
        System.out.println(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList){              
            if (file.isFile()){
                boolean fileExtension= isMP3 (file.getAbsolutePath());
                if (fileExtension){
                     sourcePath= file.getAbsolutePath();
                     destName= file.getName();
                     UtilFunctions.copyToJSoundsLibrary(sourcePath, destName);
                     String fileName = removeExtension(file.getName());
                     Song song = new Song (fileName,artist, 1,0,"");
                     System.out.println(file.getAbsolutePath());
                     if (repeatedSong==false)
                     {
                     SongXmlFile.saveSongInDataBase(song);
                     repeatedSong=true;
                     }
                }
            } else if (file.isDirectory()){
                listFilesAndFilesSubDirectories(file.getAbsolutePath());
            }
        }
    }
   
    /**
     * List One file.
     * @param file 
     */
        public static void listOneFile(File file)
    {


                boolean fileExtension= isMP3 (file.getAbsolutePath());
                if (fileExtension){
                     sourcePath= file.getAbsolutePath();
                     destName= file.getName();
                     UtilFunctions.copyToJSoundsLibrary(sourcePath, destName);
                     String fileName = removeExtension(file.getName());
                     Song song = new Song (fileName,artist, 1,0,"");
                     System.out.println(file.getAbsolutePath());
                     if(repeatedSong==false)
                     {
                     SongXmlFile.saveSongInDataBase(song);
                     repeatedSong=true;
                     }
                }
    }

    
/**
 * Method to convert an integer into a String
 * @param integer
 * @return 
 */
    public static String integerToString(int integer)
    {
        return Integer.toString(integer);
    } 
    
    /**
     * Method to convert a String into an integer
     * @param string
     * @return 
     */
        public static int stringToInteger(String string)
    {
        return Integer.parseInt(string);
    }

    
/**
 * Method to remove the spanish characters in a String
 * @param fixedName
 * @return 
 */
    public static String removeStrangeCharacters(String fixedName)

    {
         fixedName=fixedName.replace('Á','A');
         fixedName=fixedName.replace('á','a');
         fixedName=fixedName.replace('É', 'e');
         fixedName=fixedName.replace('é', 'e');
         fixedName=fixedName.replace('Í','I');
         fixedName=fixedName.replace('í','i');
         fixedName=fixedName.replace('Ó','O');
         fixedName=fixedName.replace('ó', 'o');
         fixedName=fixedName.replace('Ú','U');
         fixedName=fixedName.replace('ú','u');
         fixedName=fixedName.replace('¿',' ');
         fixedName=fixedName.replace('¡', ' ');
         fixedName=fixedName.replace('ñ', 'n');

        return fixedName;

    }
    
}
