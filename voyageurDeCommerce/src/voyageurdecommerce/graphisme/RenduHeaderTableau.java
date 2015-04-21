/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce.graphisme;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import sun.swing.table.DefaultTableCellHeaderRenderer;
import voyageurdecommerce.ModelTable;

/**
 *
 * @author Melany
 */
public class RenduHeaderTableau extends DefaultTableCellHeaderRenderer {
    
    public RenduHeaderTableau(){
        super();
        
    }
    
        public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column){
        ModelTable tableau = (ModelTable) table.getModel();
         
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        ((JLabel) cell).setHorizontalAlignment(JLabel.CENTER);
        super.setFont(new Font("calibri",Font.CENTER_BASELINE,12));
        super.setForeground(new Color(174,49,111));
 
 return cell;
    
}}