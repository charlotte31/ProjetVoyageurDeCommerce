/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Melany
 */
public class ItemGeneration extends JMenuItem implements ActionListener {

    private VoyageurDeCommerceInterface vdci_courant;
    private int nbVilles;
    private String rep;

    public ItemGeneration(String nom, VoyageurDeCommerceInterface vdci_courant) {
        super(nom);
        this.vdci_courant = vdci_courant;
        nbVilles = 0;
        rep = null;
    }

    public void actionPerformed(ActionEvent e) {
        if (vdci_courant.getCarteVoyageurDeCommerce().getListe_villes().size() != 0) {
            int oui = JOptionPane.showConfirmDialog(this, "Voulez-vous enregistrer vos anciennes données?", "Sauvegarde", WIDTH);
            if (oui == 0) {
                vdci_courant.getItemEnregistrerSous().actionPerformed(e);
            }

            vdci_courant.getCarteVoyageurDeCommerce().getListe_villes().removeAll(vdci_courant.getCarteVoyageurDeCommerce().getListe_villes());
            vdci_courant.getCarteVoyageurDeCommerce().getListe_arcs().removeAll(vdci_courant.getCarteVoyageurDeCommerce().getListe_arcs());
            
            vdci_courant.getMap().getChemin().removeAll(vdci_courant.getMap().getChemin());
            vdci_courant.getMap().repaint();
            rep = (String) JOptionPane.showInputDialog(this, "Ordre du graphe souhaité", "Génénateur de graphes aléatoires", 3, null, null, "2");
            nbVilles = Integer.parseInt(rep);
            while (nbVilles < 2 | rep == null) {
                JOptionPane.showMessageDialog(this, "Saisie incorrecte, entrez au moins 2 villes");
                rep = (String) JOptionPane.showInputDialog(this, "Ordre du graphe souhaité", "Génénateur de graphes aléatoires", 3, null, null, "2");
                nbVilles = Integer.parseInt(rep);
            }
            for (int i = 0; i < nbVilles; i++) {
                Double posX = Math.random() * 580 + 1;
                int pos_X = posX.intValue();
                Double posY = Math.random() * 533 + 1;
                int pos_Y = posY.intValue();
                vdci_courant.getCarteVoyageurDeCommerce().ajouterNoeud(new Ville(String.valueOf(i), pos_X, pos_Y));

            }
        }

        else{
        rep = (String) JOptionPane.showInputDialog(this, "Ordre du graphe souhaité", "Génénateur de graphes aléatoires", 3, null, null, "2");
        nbVilles = Integer.parseInt(rep);
        while (nbVilles < 2 | rep == null) {
            JOptionPane.showMessageDialog(this, "Saisie incorrecte, entrez au moins 2 villes");
            rep = (String) JOptionPane.showInputDialog(this, "Ordre du graphe souhaité", "Génénateur de graphes aléatoires", 3, null, null, "2");
            nbVilles = Integer.parseInt(rep);
        }
        for (int i = 0; i < nbVilles; i++) {
            Double posX = Math.random() * 580 + 1;
            int pos_X = posX.intValue();
            Double posY = Math.random() * 533 + 1;
            int pos_Y = posY.intValue();
            vdci_courant.getCarteVoyageurDeCommerce().ajouterNoeud(new Ville(String.valueOf(i), pos_X, pos_Y));

        }
    }
        vdci_courant.getCarteVoyageurDeCommerce().creerClique();
        vdci_courant.getMap().repaint();
}
    
}
