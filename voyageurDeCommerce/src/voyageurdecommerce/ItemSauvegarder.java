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
 * Classe à part entière pour rendre plus lisible le constructeur
 * de la classe VoyageurDeCommerceInerface.
 * Permet de sauvegarder un fichier. L'utilisateur n'a pas besoin de préciser l'extension, qui sera .vdc
 * Distinction des cas "enregistrer" et "enregistrer sous"
 * 
 */
public class ItemSauvegarder extends JMenuItem implements ActionListener {

    private VoyageurDeCommerceInterface vdci;
    private File myFile;

    public ItemSauvegarder(String nom, VoyageurDeCommerceInterface vdci) {
        super(nom);
        this.vdci = vdci;
        myFile = null;
    }

    public void actionPerformed(ActionEvent e) {
        JFileChooser dialog = new JFileChooser();
        if (myFile == null | e.getSource()==this) { // Si l'utilisateur clique sur enregistrer et qu'il y a déjà un fichier pour le 
                                                    // travail en cours, on le complète (voir plus loin). Si Enregistrer sous, on offre la possiblité d'un nouvel enregistrement
            int rep = dialog.showSaveDialog(this);
            if (rep == JFileChooser.APPROVE_OPTION) {
                try {
                    myFile = new File(dialog.getSelectedFile().getPath() + ".vdc"); // Nom du fichier choisit par l'utilisateur + ajout extension
                    FileWriter savedFile = new FileWriter(myFile);
                    // Ecriture du fichier format .vdc (définit arbitairement)
                    savedFile.write("[LISTE DES NOEUDS ET LOCALISATIONS]\n");
                    for (int i = 0; i < vdci.getCarteVoyageurDeCommerce().getListe_villes().size(); i++) {
                        savedFile.write(vdci.getCarteVoyageurDeCommerce().getListe_villes().get(i).getNom()
                                + "\t" + vdci.getCarteVoyageurDeCommerce().getListe_villes().get(i).getPosition_x()
                                + "\t" + vdci.getCarteVoyageurDeCommerce().getListe_villes().get(i).getPosition_y() + "\n");
                    }
                    savedFile.write("\n[ARCS ET DISTANCES]\n");
                    for (int i = 0; i < vdci.getCarteVoyageurDeCommerce().getListe_arcs().size(); i++) {
                        savedFile.write(vdci.getCarteVoyageurDeCommerce().getListe_arcs().get(i).getNomV1()
                                + "\t" + vdci.getCarteVoyageurDeCommerce().getListe_arcs().get(i).getNomV2()
                                + "\t" + vdci.getCarteVoyageurDeCommerce().getListe_arcs().get(i).getDistance() + "\n");
                    }
                    if (vdci.getMap().getHashChemin().size() != 0) {
                        savedFile.write("\n[VOYAGEUR DE COMMERCE: RESULTATS]\n");
                        Iterator<Algorithme> keySetIterator = vdci.getMap().getHashChemin().keySet().iterator();
                        while (keySetIterator.hasNext()) {
                            Algorithme key = keySetIterator.next();
                            savedFile.write(key + "\t" + vdci.getMap().getHashChemin().get(key) + "\n");
                        }
                    }
                    savedFile.close();
                } catch (IOException ex) {
                    Logger.getLogger(ItemSauvegarder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            // Idem dans le cas où l'utilisateur chosit 'enregistrer', on évite de l'obliger à saisir une nouvelle fois
            // un nom de fichier, etc
            try {
                FileWriter savedFile = new FileWriter(myFile);
                savedFile.write("[LISTE DES NOEUDS ET LOCALISATIONS]\n");
                for (int i = 0; i < vdci.getCarteVoyageurDeCommerce().getListe_villes().size(); i++) {
                    savedFile.write(vdci.getCarteVoyageurDeCommerce().getListe_villes().get(i).getNom()
                            + "\t" + vdci.getCarteVoyageurDeCommerce().getListe_villes().get(i).getPosition_x()
                            + "\t" + vdci.getCarteVoyageurDeCommerce().getListe_villes().get(i).getPosition_y() + "\n");
                }
                savedFile.write("\n[ARCS ET DISTANCES]\n");
                for (int i = 0; i < vdci.getCarteVoyageurDeCommerce().getListe_arcs().size(); i++) {
                    savedFile.write(vdci.getCarteVoyageurDeCommerce().getListe_arcs().get(i).getNomV1()
                            + "\t" + vdci.getCarteVoyageurDeCommerce().getListe_arcs().get(i).getNomV2()
                            + "\t" + vdci.getCarteVoyageurDeCommerce().getListe_arcs().get(i).getDistance() + "\n");
                }
                if (vdci.getMap().getHashChemin().size() != 0) {
                    savedFile.write("\n[VOYAGEUR DE COMMERCE: RESULTATS]\n");
                    Iterator<Algorithme> keySetIterator = vdci.getMap().getHashChemin().keySet().iterator();
                    while (keySetIterator.hasNext()) {
                        Algorithme key = keySetIterator.next();
                        savedFile.write(key + "\t" + vdci.getMap().getHashChemin().get(key) + "\n");
                    }
                }
                savedFile.close();
            } catch (IOException ex) {
                Logger.getLogger(ItemSauvegarder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
