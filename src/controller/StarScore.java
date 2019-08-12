/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.ImageIcon;

/**
 * @author Anibal Dominguez
 * @author Manuel Pacheco
 * @author José Suárez
 */
public class StarScore
{
    private ImageIcon image;
    
    /**
     * Method to set the star score
     * @param numberOfStars
     */
    public StarScore(int numberOfStars)
    {
        switch (numberOfStars)
        {
            case 1:
                image = new ImageIcon("src/view/images/1star.png"); 
                break;
            case 2:
                image = new ImageIcon("src/view/images/2stars.png"); 
                break;
            case 3:
                image = new ImageIcon("src/view/images/3stars.png"); 
                break;
            case 4:
                image = new ImageIcon("src/view/images/4stars.png"); 
                break;    
            case 5:
                image = new ImageIcon("src/view/images/5stars.png");
                break;
            default:
                image = new ImageIcon("src/view/images/1star.png"); 
        }
    }

    /**
     * Method to get the image of the stars
     * @return
     */
    public ImageIcon getImage()
    {
        return image;
    }
}
