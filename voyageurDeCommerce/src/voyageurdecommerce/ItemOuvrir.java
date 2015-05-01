/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FilenameFilter;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 *
 * @author Melany
 */
public class ItemOuvrir extends JMenuItem implements ActionListener{
    private VoyageurDeCommerceInterface vdci;
    private File myFile;
    private JTextField nom_fichier;
    private JTextArea texte;
    public ItemOuvrir(String nom,VoyageurDeCommerceInterface vdci){
        super(nom);
        this.vdci=vdci;
        
    }
    
    
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser(".");
        FileFilter filter1 = new ExtensionFileFilter("vdc", new String[]{"vdc"});
        for (int i = 0; i < fileChooser.getChoosableFileFilters().length; i++) {
            fileChooser.removeChoosableFileFilter(fileChooser.getChoosableFileFilters()[i]);
        }
        fileChooser.addChoosableFileFilter(filter1);
        int rep = fileChooser.showOpenDialog(null);
        if (rep == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            System.out.println(file);
            //nom_fichier.setText(file.getName());
            try {
                BufferedReader bufR = new BufferedReader(new FileReader(file));
                String line = bufR.readLine();
                while (line != null) {
                    System.out.println(line);
                    line = bufR.readLine();
                }

                bufR.close();
            } catch (IOException ex) {
                // TODO Auto-generated catch block
                ex.printStackTrace();
            }
        }

    }
}
