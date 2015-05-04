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

/**
 *
 * @author Melany
 */
public class CurrentGraphButton extends JButton implements ActionListener {
    private VoyageurDeCommerceInterface vdci;
    private ArrayList<Algorithme> listeAlgo;
    public CurrentGraphButton(String name, VoyageurDeCommerceInterface vdci){
        super(name);
        this.vdci = vdci;
        listeAlgo=new ArrayList<Algorithme>();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
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
