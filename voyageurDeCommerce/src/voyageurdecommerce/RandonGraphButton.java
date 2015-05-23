/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFrame;

/**
 *
 * @author Melany
 */
public class RandonGraphButton extends JButton implements ActionListener {

    private int nbVilles;
    private Carte randomCarte;
    private HistoGeneration hg;
    private VoyageurDeCommerceInterface vdci;
    private ArrayList<Algorithme> listeAlgo;

    public RandonGraphButton(String name, VoyageurDeCommerceInterface vdci) {
        super(name);
        nbVilles = 0;
        randomCarte = new Carte();
        this.vdci = vdci;
        listeAlgo = new ArrayList<Algorithme>();
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(listeAlgo.size());
        String nom = JOptionPane.showInputDialog("Nombre de villes à générer:", "2");
        if (nom != null) {
            nbVilles = Integer.parseInt(nom);
        }
        while (nbVilles < 2 | nom == null) {
            nom = JOptionPane.showInputDialog("Nombre de villes à générer:", "2");
            if (nom != null) {
                nbVilles = Integer.parseInt(nom);
            }
        }
        vdci.getCarteVoyageurDeCommerce().getListe_villes().removeAll(vdci.getCarteVoyageurDeCommerce().getListe_villes());
        vdci.getCarteVoyageurDeCommerce().getListe_arcs().removeAll(vdci.getCarteVoyageurDeCommerce().getListe_arcs());
        vdci.getMap().getChemin().removeAll(vdci.getMap().getChemin());
        for (int i = 0; i < nbVilles; i++) {
            Double posX = Math.random() * 580 + 1;
            int pos_X = posX.intValue();
            Double posY = Math.random() * 533 + 1;
            int pos_Y = posY.intValue();
            vdci.getCarteVoyageurDeCommerce().ajouterNoeud(new Ville(String.valueOf(i), pos_X, pos_Y));

        }
        vdci.getCarteVoyageurDeCommerce().creerClique();
        vdci.getMap().repaint();
        System.out.println(listeAlgo.contains(Algorithme.MOINDRE_COUT));
        if (listeAlgo.contains(Algorithme.TWO_OPT) == true) {
            vdci.getKruskal().actionPerformed(e);
        }
        if (listeAlgo.contains(Algorithme.PRIM) == true) {
            vdci.getPrim().actionPerformed(e);
        }
        if (listeAlgo.contains(Algorithme.PLUS_ELOIGNES) == true) {
            vdci.getPlusEloignes().actionPerformed(e);
        }
        if (listeAlgo.contains(Algorithme.PLUS_PROCHE_VOISIN) == true) {
            vdci.getPlusProcheVoisin().actionPerformed(e);
        }
        if (listeAlgo.contains(Algorithme.MOINDRE_COUT) == true) {
            vdci.getMoindreCout().actionPerformed(e);
        }
        if (listeAlgo.size() == 0) {
            JOptionPane.showMessageDialog(this, "Sélectionnez au moins 1 algorithme");
            vdci.getItemComparaison().actionPerformed(e);
        } else {
            hg = new HistoGeneration("Random graph analysis", "Comparaison : distances et temps d'exécution", vdci);
        }

    }

    /**
     * @return the listeAlgo
     */
    public ArrayList<Algorithme> getListeAlgo() {
        return listeAlgo;
    }

    /**
     * @param listeAlgo the listeAlgo to set
     */
    public void setListeAlgo(ArrayList<Algorithme> listeAlgo) {
        this.listeAlgo = listeAlgo;
    }

}
