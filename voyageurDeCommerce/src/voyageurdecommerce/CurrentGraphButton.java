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

/**
 *
 * @author Melany
 */
public class CurrentGraphButton extends JButton implements ActionListener {
    private int nbIterations;
    private VoyageurDeCommerceInterface vdci;
    private ArrayList<Algorithme> listeAlgo;
    private HistoDistanceTemps hdt;
    private HistoDistanceFonctionVilles hdfv;
    private HistoTempsFonctionVilles htfv;

    public CurrentGraphButton(String name, VoyageurDeCommerceInterface vdci, int nbIterations) {
        super(name);
        super.setForeground(new Color(10, 59, 89));
        this.nbIterations=nbIterations;
        this.vdci = vdci;
        listeAlgo = new ArrayList<Algorithme>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (vdci.getCarteVoyageurDeCommerce().getListe_villes().size() < 2) {
            JOptionPane.showMessageDialog(this, "Saisie du graphe incorrecte");
        } else {
            
           
                if (listeAlgo.contains(Algorithme.TWO_OPT)) {
                    vdci.getKruskal().actionPerformed(e);
                }
                if (listeAlgo.contains(Algorithme.PRIM)) {
                    vdci.getPrim().actionPerformed(e);
                }
                if (listeAlgo.contains(Algorithme.MOINDRE_COUT)) {
                    vdci.getMoindreCout().actionPerformed(e);
                }
                if (listeAlgo.contains(Algorithme.PLUS_PROCHE_VOISIN)) {
                    vdci.getPlusProcheVoisin().actionPerformed(e);
                }
                if (listeAlgo.contains(Algorithme.PLUS_ELOIGNES)) {
                    vdci.getPlusEloignes().actionPerformed(e);
                }
                if (listeAlgo.size() == 0) {
                    JOptionPane.showMessageDialog(vdci, "Sélectionnez au moins 1 algorithme");
                  
                }
                else{
                JOptionPane.showMessageDialog(vdci, "C'est partie pour l'analyse!");
                hdt = new HistoDistanceTemps("Current graph analysis", "Comparaison : distances et temps d'exécution", vdci);
                hdfv= new HistoDistanceFonctionVilles("Current graph analysis","Distance en fonction du temps",vdci, getNbIterations(),listeAlgo);
                htfv= new HistoTempsFonctionVilles("Current graph analysis","Temps d'exécution en fonction du temps",vdci, getNbIterations(),listeAlgo);
                
                }
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
