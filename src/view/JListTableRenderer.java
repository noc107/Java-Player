/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
/**
 * @author Anibal Dominguez
 * @author Manuel Pacheco
 * @author José Suárez
 */

public class JListTableRenderer extends DefaultTableCellRenderer {

JList pane = new JList();
/**
 *Method to Render the table
 */
public JListTableRenderer()
{
    super();
}

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
      boolean hasFocus, int row, int column)
    {
        
        pane = (JList) value;
        

        return pane;
    }
}
