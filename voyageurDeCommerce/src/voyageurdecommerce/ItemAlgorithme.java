/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Melany Selon l'item choisit par l'utilisateur, la fonction du bon
 * algorithme sera appellée Création d'une classe distincte qui étend du
 * JMenuItem simplement pour rendre plus lisible le constructure de la classe
 * VoyageurDeCommerceInterface qui est déjà bien remplie. Histoire de s'y
 * retrouver
 */
public class ItemAlgorithme extends JMenuItem implements ActionListener {

    private VoyageurDeCommerceInterface vdci;

    public ItemAlgorithme(String m, VoyageurDeCommerceInterface vdci) {
        super(m);
        this.vdci = vdci;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Ville v = null;
        if (vdci.getCarteVoyageurDeCommerce().getListe_villes().size() == 0) {
            JOptionPane.showMessageDialog(this, "Sélectionnez au moins 2 villes puis validez.");
        } else {
            String m = JOptionPane.showInputDialog("", "Entrez la ville de départ");
            for (int i = 0; i < vdci.getCarteVoyageurDeCommerce().getListe_villes().size(); i++) {
                if (vdci.getCarteVoyageurDeCommerce().getListe_villes().get(i).getNom().equals(m)) {
                    v = vdci.getCarteVoyageurDeCommerce().getListe_villes().get(i);
                }
            }
            if (v == null) {
                //JOptionPane.showMessageDialog(this, "incorrect");
            } else {
                if (super.getText().equals("plusProcheVoisin")) {
                    ArrayList<Object> res = vdci.getCarteVoyageurDeCommerce().plusProcheVoisins(v);
                    ArrayList<String> resString = vdci.getCarteVoyageurDeCommerce().toString((ArrayList<Ville>) res.get(0));
                    JOptionPane.showMessageDialog(this, "[[Chemin] - Distance - Performance (ms)]\n"
                            + resString + " - " + res.get(1) + " - " + res.get(2));
                    //repaint();
                    vdci.getMap().setHashChemin(Algorithme.PLUS_PROCHE_VOISIN, res);
                    vdci.getMap().setChemin((ArrayList<Ville>) res.get(0));

                    vdci.getMap().repaint();
                }
                if (super.getText().equals("moindreCout")) {
                    ArrayList<Object> res = vdci.getCarteVoyageurDeCommerce().moindreCout(v);
                    ArrayList<String> resString = vdci.getCarteVoyageurDeCommerce().toString((ArrayList<Ville>) res.get(0));
                    JOptionPane.showMessageDialog(this, "[[Chemin - Distance - Performance (ms)]\n"
                            + resString + " - " + res.get(1) + " - " + res.get(2));
                    vdci.getMap().setHashChemin(Algorithme.MOINDRE_COUT, res);
                    vdci.getMap().setChemin((ArrayList<Ville>) res.get(0));

                }
                if (super.getText().equals("plusEloignes")) {
                    JOptionPane.showMessageDialog(this, "[[Chemin], Distance, Performance (ms)]\n"
                            + "Pas encore implémenté");
                }
                if (super.getText().equals("2-Opt")) {
                    ArrayList<Object> res = vdci.getCarteVoyageurDeCommerce().twoOpt(v);
                    ArrayList<String> resString = vdci.getCarteVoyageurDeCommerce().toString((ArrayList<Ville>) res.get(0));
                    JOptionPane.showMessageDialog(this, "[[Chemin - Distance - Performance (ms)]\n"
                            + resString + " - " + res.get(1)+ " - " + res.get(2));
                    vdci.getMap().setHashChemin(Algorithme.TWO_OPT, res);
                    vdci.getMap().setChemin((ArrayList<Ville>) res.get(0));
                }
                if (super.getText().equals("Prim")) {
                    ArrayList<Object> res = vdci.getCarteVoyageurDeCommerce().prim(v);
                    ArrayList<String> resString = vdci.getCarteVoyageurDeCommerce().toString((ArrayList<Ville>) res.get(0));
                    JOptionPane.showMessageDialog(this, "[[Chemin - Distance - Performance (ms)]\n"
                            + resString + " - " + res.get(1)+ " - " + res.get(2));
                    vdci.getMap().setHashChemin(Algorithme.PRIM, res);
                    vdci.getMap().setChemin((ArrayList<Ville>) res.get(0));
                }

                // ArrayList<Object> ipe = vdci.getCarteVoyageurDeCommerce().moindreCout(v);
                //System.out.println(ipe.get(0));
            }
        }

    }
}
