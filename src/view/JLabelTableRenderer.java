/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author Anibal Dominguez
 * @author Manuel Pacheco
 * @author José Suárez
 */

public class JLabelTableRenderer extends DefaultTableCellRenderer {

JLabel pane = new JLabel();
/**
 * Method to set the render of the table
 */
public JLabelTableRenderer()
{
    super();
}

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
      boolean hasFocus, int row, int column)
    {
        pane = (JLabel) value;
        
        return pane;
    }
}
