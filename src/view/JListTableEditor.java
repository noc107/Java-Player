/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import javax.swing.AbstractCellEditor;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableCellEditor;

/**
 * @author Anibal Dominguez
 * @author Manuel Pacheco
 * @author José Suárez
 */

public class JListTableEditor extends AbstractCellEditor implements TableCellEditor {
    JList component; //= new JList();
    /**
     * Method to edit the table
     */
    public JListTableEditor() {
    super();
    component = new JList();
  }
    
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
          int rowIndex, int vColIndex)
    {
        if (value != null)
        {
            component = ((JList) value);
            
            return ((JList) value);
        }
        
        return null;
    }
/**
 * Method to get the cell editor value
 * @return 
 */
    public Object getCellEditorValue()
    {
        return component;
    }

    }
