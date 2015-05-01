/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

import com.sun.javafx.scene.traversal.Algorithm;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Melany
 */
public class AlgorithmeApplication extends JMenuItem implements ActionListener {

    private VoyageurDeCommerceInterface vdci;
    public AlgorithmeApplication(String m, VoyageurDeCommerceInterface vdci){
        super(m);
        this.vdci=vdci;        
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
                ArrayList<Object> res = vdci.getCarteVoyageurDeCommerce().plusProcheVoisins(v);
                ArrayList<String> resString=vdci.getCarteVoyageurDeCommerce().toString((ArrayList<Ville>)res.get(0));
                JOptionPane.showMessageDialog(this, "[[Chemin] - Distance - Performance (ms)]\n"
                        +resString+" - "+res.get(1)+" - "+res.get(2));    
                //repaint();
                vdci.getMap().setHashChemin(Algorithme.plusProcheVoisin,resString);
                vdci.getMap().setChemin((ArrayList<Ville>)res.get(0));
                vdci.getMap().repaint();
            }
            if (super.getText().equals("moindreCout")){
                ArrayList<Object> res =vdci.getCarteVoyageurDeCommerce().moindreCout(v);
                ArrayList<String> resString=vdci.getCarteVoyageurDeCommerce().toString((ArrayList<Ville>)res.get(0));
                JOptionPane.showMessageDialog(this, "[[Chemin - Distance - Performance (ms)]\n"
                    +resString+" - "+res.get(1)+" - "+res.get(2));  
                vdci.getMap().setHashChemin(Algorithme.moindreCout,resString);
                vdci.getMap().setChemin((ArrayList<Ville>)res.get(0));
            }
            if (super.getText().equals("plusEloignes")){
            JOptionPane.showMessageDialog(this, "[[Chemin], Distance, Performance (ms)]\n"
                    +"Pas encore implémenté");
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
 

