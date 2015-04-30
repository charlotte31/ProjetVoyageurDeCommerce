/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

import com.sun.javafx.scene.traversal.Algorithm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Melany
 */
public class AlgorithmeApplication extends JMenuItem implements ActionListener {

    VoyageurDeCommerceInterface vdci;
    public AlgorithmeApplication(String m, VoyageurDeCommerceInterface vdci){
        super(m);
        this.vdci=vdci;
        System.out.println(super.getText());

        
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        Ville v=null;
        if (vdci.getCarteVoyageurDeCommerce().getListe_villes().size()==0)
        {
            JOptionPane.showMessageDialog(this, "Sélectionnez au moins 2 villes puis validez.");
        }
        else{
            String m =JOptionPane.showInputDialog("","Entrez la ville de départ");
            for (int i=0;i<vdci.getCarteVoyageurDeCommerce().getListe_villes().size();i++){
                if (vdci.getCarteVoyageurDeCommerce().getListe_villes().get(i).getNom().equals(m))
                {
                    v=vdci.getCarteVoyageurDeCommerce().getListe_villes().get(i);
                }
            }
         if (v == null){
            JOptionPane.showMessageDialog(this, "incorrect");
        }           
        

        
        else {
            if (super.getText().equals("plusProcheVoisin")){
                JOptionPane.showMessageDialog(this, "[[Chemin], Distance, Performance (ms)]\n"
                        +vdci.getCarteVoyageurDeCommerce().plusProcheVoisins(v));
            }
            if (super.getText().equals("moindreCout")){
            JOptionPane.showMessageDialog(this, "[[Chemin], Distance, Performance (ms)]\n"
                    +vdci.getCarteVoyageurDeCommerce().moindreCout(v));
            }
            if (super.getText().equals("plusEloignes")){
            JOptionPane.showMessageDialog(this, "[[Chemin], Distance, Performance (ms)]\n"
                    +vdci.getCarteVoyageurDeCommerce().insertionPlusEloignes(v));
            }
            if (super.getText().equals("Kruskal")){
            JOptionPane.showMessageDialog(this, "[[Chemin], Distance, Performance (ms)]\n"
                    +"Pas encore implémenté");
            }
            if (super.getText().equals("Prim")){
            JOptionPane.showMessageDialog(this, "[[Chemin], Distance, Performance (ms)]\n"
                    +"Pas encore implémenté");
            }
                
           // ArrayList<Object> ipe = vdci.getCarteVoyageurDeCommerce().moindreCout(v);
            //System.out.println(ipe.get(0));
        }
    }

    }
}
