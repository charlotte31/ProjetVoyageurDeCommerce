/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Melany
 */
public class boutonValider extends JButton implements ActionListener{
    
    VoyageurDeCommerceInterface vdci;
    private Object g;
    public boutonValider(String nom_bouton, VoyageurDeCommerceInterface vdci){
        super(nom_bouton);
        this.vdci=vdci;

        
    }
    
    public void painComponent(Graphics g){
        vdci.getMap().paintComponents(g);
        for (Arc a : vdci.getCarteVoyageurDeCommerce().getListe_arcs()) {
            g.drawLine(a.getV1().getPosition_x(), a.getV2().getPosition_x(), a.getV1().getPosition_y(), a.getV2().getPosition_y());
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (vdci.getCarteVoyageurDeCommerce().getListe_arcs().size()!=0){
                  for (int i=0; i<vdci.getCarteVoyageurDeCommerce().getListe_arcs().size();i++){
                      vdci.getModelTableau().addData(vdci.getCarteVoyageurDeCommerce().getListe_arcs().get(i));
                      int v1_x=vdci.getCarteVoyageurDeCommerce().getListe_arcs().get(i).getV1().getPosition_x();
                      int v2_x=vdci.getCarteVoyageurDeCommerce().getListe_arcs().get(i).getV2().getPosition_x();
                      int v1_y=vdci.getCarteVoyageurDeCommerce().getListe_arcs().get(i).getV1().getPosition_y();
                      int v2_y=vdci.getCarteVoyageurDeCommerce().getListe_arcs().get(i).getV2().getPosition_y();
                      
    }
        

}
    }
}
    
