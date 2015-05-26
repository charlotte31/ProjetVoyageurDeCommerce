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

/**
 *
 * @author Melany
 */
public class CurrentGraphButton extends JButton implements ActionListener {

    private VoyageurDeCommerceInterface vdci;
    private ArrayList<Algorithme> listeAlgo;
    private HistoGeneration ch;

    public CurrentGraphButton(String name, VoyageurDeCommerceInterface vdci) {
        super(name);
        this.vdci = vdci;
        listeAlgo = new ArrayList<Algorithme>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (vdci.getCarteVoyageurDeCommerce().getListe_villes().size() < 2) {
            JOptionPane.showMessageDialog(this, "Saisie du graphe incorrecte");
        } else {
            for (int i = 0; i < listeAlgo.size(); i++) {
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
                    JOptionPane.showMessageDialog(this, "Sélectionnez au moins 1 algorithme");
                    vdci.getItemComparaison().actionPerformed(e);
                }
            }
                ch = new HistoGeneration("Current graph analysis", "Comparaison : distances et temps d'exécution", vdci);

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
