/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

import java.awt.Color;
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
    private int nbIterations;
    private int nbVilles;
    private Carte randomCarte;
    private HistoDistanceTemps hg;
    private VoyageurDeCommerceInterface vdci;
    private ArrayList<Algorithme> listeAlgo;
    private HistoDistanceFonctionVilles hdfv;
    private HistoTempsFonctionVilles htfv;

    public RandonGraphButton(String name, VoyageurDeCommerceInterface vdci, int nbIterations) {
        super(name);
        super.setForeground(new Color(10, 59, 89));
        this.nbIterations=nbIterations;
        this.vdci=vdci;
        nbVilles = 0;
        randomCarte = new Carte();
        listeAlgo = new ArrayList<Algorithme>();
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(getNbIterations());
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
            JOptionPane.showMessageDialog(vdci, "Sélectionnez au moins 1 algorithme");
            
        } else {
            JOptionPane.showMessageDialog(vdci, "C'est partie pour l'analyse!");
            hg = new HistoDistanceTemps("Random graph analysis", "Comparaison : distances et temps d'exécution", vdci);
            hdfv= new HistoDistanceFonctionVilles("Random graph analysis","Distance en fonction du temps", getNbIterations(),listeAlgo);
            htfv= new HistoTempsFonctionVilles("Random graph analysis","Temps d'exécution en fonction du temps", getNbIterations(),listeAlgo);
                
        
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

    /**
     * @return the nbIterations
     */
    public int getNbIterations() {
        return nbIterations;
    }

    /**
     * @param nbIterations the nbIterations to set
     */
    public void setNbIterations(int nbIterations) {
        this.nbIterations = nbIterations;
    }

}
