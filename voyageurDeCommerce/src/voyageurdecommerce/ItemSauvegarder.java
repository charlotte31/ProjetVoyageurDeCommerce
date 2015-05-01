/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

/**
 *
 * @author Melany
 */
public class ItemSauvegarder extends JMenuItem implements ActionListener {
    private VoyageurDeCommerceInterface vdci;
    private File myFile;
    public ItemSauvegarder(String nom, VoyageurDeCommerceInterface vdci){
        super(nom);
        this.vdci=vdci;
    }
    public void actionPerformed(ActionEvent e) {
       myFile = new File("data.vdc");
        try {
            FileWriter savedFile = new FileWriter(myFile);
            savedFile.write("[LISTE DES NOEUDS]\n");
            for (int i=0;i<vdci.getCarteVoyageurDeCommerce().getListe_villes().size();i++){
                savedFile.write(vdci.getCarteVoyageurDeCommerce().getListe_villes().get(i).getNom()+"\n");
            }
            savedFile.write("\n[ARCS ET DISTANCES]\n");
            for (int i=0;i<vdci.getCarteVoyageurDeCommerce().getListe_arcs().size();i++){
                savedFile.write(vdci.getCarteVoyageurDeCommerce().getListe_arcs().get(i).getNomV1()
                        +"\t"+vdci.getCarteVoyageurDeCommerce().getListe_arcs().get(i).getNomV2()
                        +"\t"+vdci.getCarteVoyageurDeCommerce().getListe_arcs().get(i).getDistance()+"\n");
            }
            if (vdci.getMap().getHashChemin().size()!=0){
                savedFile.write("\n[VOYAGEUR DE COMMERCE : RESULTATS]\n");
                Iterator<Algorithme> keySetIterator = vdci.getMap().getHashChemin().keySet().iterator();
                while(keySetIterator.hasNext()){
                    Algorithme key = keySetIterator.next();
                    savedFile.write(key+"\t"+vdci.getMap().getHashChemin().get(key)+"\n");
                }
            }
            savedFile.close();
        } catch (IOException ex) {
            Logger.getLogger(ItemSauvegarder.class.getName()).log(Level.SEVERE, null, ex);
        }

 
    }
    
}
