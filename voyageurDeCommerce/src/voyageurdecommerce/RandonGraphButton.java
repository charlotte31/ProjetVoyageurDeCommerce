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
    private RandomHisto rh;
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
        nbVilles = Integer.parseInt(nom);
        while (nbVilles < 2 | nom == null) {
            nom = JOptionPane.showInputDialog("Saisie incorrecte! entrez au moins 2 villes", "");
            nbVilles = Integer.parseInt(nom);
        }
        for (int i = 0; i < nbVilles; i++) {
            Double posX = Math.random() * 580 + 1;
            int pos_X = posX.intValue();
            Double posY = Math.random() * 533 + 1;
            int pos_Y = posY.intValue();
            vdci.getCarteVoyageurDeCommerce().ajouterNoeud(new Ville(String.valueOf(i), pos_X, pos_Y));

        }
        vdci.getCarteVoyageurDeCommerce().creerClique();
        vdci.getMap().repaint();
        System.out.println(listeAlgo.contains(Algorithme.moindreCout));
        if (listeAlgo.contains(Algorithme.Kruskal) == true) {
            vdci.getKruskal().actionPerformed(e);
        }
        if (listeAlgo.contains(Algorithme.Prim) == true) {
            vdci.getPrim().actionPerformed(e);
        }
        if (listeAlgo.contains(Algorithme.plusEloignes) == true) {
            vdci.getPlusEloignes().actionPerformed(e);
        }
        if (listeAlgo.contains(Algorithme.plusProcheVoisin) == true) {
            vdci.getPlusProcheVoisin().actionPerformed(e);
        }
        if (listeAlgo.contains(Algorithme.moindreCout) == true) {
            vdci.getMoindreCout().actionPerformed(e);
            System.out.println("ok");
        }
        if (listeAlgo.size() == 0) {
            JOptionPane.showMessageDialog(this, "Sélectionnez au moins 1 algorithme");
            vdci.getItemComparaison().actionPerformed(e);
        }

        rh = new RandomHisto("Graphe aléatoire", "Comparaison : distances et temps d'exécution", vdci);
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
