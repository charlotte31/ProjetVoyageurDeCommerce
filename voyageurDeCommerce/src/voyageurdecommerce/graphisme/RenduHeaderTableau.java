/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce.graphisme;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import voyageurdecommerce.ModelTable;

/**
 *
 * @author Melany
 */
public class RenduHeaderTableau extends DefaultTableCellRenderer implements TableCellRenderer{

    public RenduHeaderTableau() {
        super();

    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        ModelTable tableau = (ModelTable) table.getModel();

        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        ((JLabel) cell).setHorizontalAlignment(JLabel.CENTER);
       super.setFont(new Font("calibri", Font.CENTER_BASELINE, 12));
        super.setForeground(Color.white);
  
        super.setBackground(new Color(10, 59, 89));
       super.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, Color.white));

        return cell;

    }
}
