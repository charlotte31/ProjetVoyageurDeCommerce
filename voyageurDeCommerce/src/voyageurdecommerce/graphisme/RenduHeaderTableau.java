/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce.graphisme;

import java.awt.Component;
import javax.swing.JTable;
import voyageurdecommerce.ModelTable;

/**
 *
 * @author Melany
 */
public class RenduHeaderTableau {

    public RenduHeaderTableau() {
        super();

    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        ModelTable tableau = (ModelTable) table.getModel();

        //Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//        ((JLabel) cell).setHorizontalAlignment(JLabel.CENTER);
//        super.setFont(new Font("calibri", Font.CENTER_BASELINE, 12));
//        super.setForeground(Color.white);
//        //super.setForeground(new Color(174,49,111));
//        super.setBackground(new Color(10, 59, 89));
//        super.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, Color.white));

        return null;

    }
}
