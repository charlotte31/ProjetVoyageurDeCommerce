/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

/**
 *
 * @author Melany
 */
public class ItemOuvrir extends JMenuItem implements ActionListener{

    public ItemOuvrir(String nom){
        super(nom);
    }
    public void actionPerformed(ActionEvent e) {
               JFileChooser chooser = new JFileChooser();
        int rep = chooser.showOpenDialog(this);
    }
    
}
