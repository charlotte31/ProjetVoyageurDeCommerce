/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Melany
 * Classe créée à part entière pour éviter de trop charger le constructeur de VoyageurDeCommerceInterface
 * Permet de définir la méthode rendant le bouton valider fonctionnel. Quand l'utilisateur cliquera dessus, les 
 * informations concernant les arcs du graphe qu'il a créé seront ajoutées dans le tableau
 */
public class BoutonValider extends JButton implements ActionListener{
    
    private VoyageurDeCommerceInterface vdci;
    private Object g;
    
    public BoutonValider(String nom, VoyageurDeCommerceInterface vdci){
        super(nom);
        this.vdci=vdci;  
        setToolTipText("Cliquez ici pour visualiser les distances intervilles");
    }
    
    public void painComponent(Graphics g){
        getVdci().getMap().paintComponents(g);
        for (Arc a : getVdci().getCarteVoyageurDeCommerce().getListe_arcs()) {
            g.drawLine(a.getV1().getPosition_x(), a.getV2().getPosition_x(), a.getV1().getPosition_y(), a.getV2().getPosition_y());
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (getVdci().getModelTableau().getRowCount() != getVdci().getCarteVoyageurDeCommerce().getListe_arcs().size()) {
            // On vide le tableau s'il avait déjà été rempli
            for (int i = 0; i < getVdci().getModelTableau().getRowCount(); i++) {
                getVdci().getModelTableau().removeData(i);
            }
            ArrayList <Ville> dejaVu = new  ArrayList<Ville>();
            if (getVdci().getCarteVoyageurDeCommerce().getListe_arcs().size() != 0) {
                for (int i = 0; i < getVdci().getCarteVoyageurDeCommerce().getListe_arcs().size(); i++) {
                    // Puis le réinitialise
                   if (! dejaVu.contains(getVdci().getCarteVoyageurDeCommerce().getListe_arcs().get(i).getV2())){
                    getVdci().getModelTableau().addData(getVdci().getCarteVoyageurDeCommerce().getListe_arcs().get(i));
                    dejaVu.add(getVdci().getCarteVoyageurDeCommerce().getListe_arcs().get(i).getV1());
                    // Et on ajoute les nouveaux arcs à la liste de la carte
                    int v1_x = getVdci().getCarteVoyageurDeCommerce().getListe_arcs().get(i).getV1().getPosition_x();
                    int v2_x = getVdci().getCarteVoyageurDeCommerce().getListe_arcs().get(i).getV2().getPosition_x();
                    int v1_y = getVdci().getCarteVoyageurDeCommerce().getListe_arcs().get(i).getV1().getPosition_y();
                    int v2_y = getVdci().getCarteVoyageurDeCommerce().getListe_arcs().get(i).getV2().getPosition_y();
                   }
                   System.out.println("(BoutonValider)Test: nb ville = " + getVdci().getCarteVoyageurDeCommerce().getListe_villes().size());
                   
                   

                }

            }
        }
    }


    /**
     * @return the vdci
     */
    public VoyageurDeCommerceInterface getVdci() {
        return vdci;
    }

    /**
     * @param vdci the vdci to set
     */
    public void setVdci(VoyageurDeCommerceInterface vdci) {
        this.vdci = vdci;
    }

    /**
     * @return the g
     */
    public Object getG() {
        return g;
    }

    /**
     * @param g the g to set
     */
    public void setG(Object g) {
        this.g = g;
    }
}
    
