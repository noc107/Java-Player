/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * @author Anibal Dominguez
 * @author Manuel Pacheco
 * @author José Suárez
 */
public class ViewUtilFunctions
{/**
 * Method to change the icon of a button
 * @param button
 * @param iconPath 
 */
    public static void changeIconFromButton(JButton button, String iconPath)
    {
        button.setIcon(new ImageIcon(iconPath));
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
    
}
