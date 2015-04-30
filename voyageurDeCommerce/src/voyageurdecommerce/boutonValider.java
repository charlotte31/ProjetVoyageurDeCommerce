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
    
    private VoyageurDeCommerceInterface vdci;
    private Object g;
    public boutonValider(String nom_bouton, VoyageurDeCommerceInterface vdci){
        super(nom_bouton);
        this.vdci=vdci;

        
    }
    
    public void painComponent(Graphics g){
        getVdci().getMap().paintComponents(g);
        for (Arc a : getVdci().getCarteVoyageurDeCommerce().getListe_arcs()) {
            g.drawLine(a.getV1().getPosition_x(), a.getV2().getPosition_x(), a.getV1().getPosition_y(), a.getV2().getPosition_y());
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (getVdci().getCarteVoyageurDeCommerce().getListe_arcs().size()!=0){
                  for (int i=0; i<getVdci().getCarteVoyageurDeCommerce().getListe_arcs().size();i++){
                      getVdci().getModelTableau().addData(getVdci().getCarteVoyageurDeCommerce().getListe_arcs().get(i));
                      int v1_x=getVdci().getCarteVoyageurDeCommerce().getListe_arcs().get(i).getV1().getPosition_x();
                      int v2_x=getVdci().getCarteVoyageurDeCommerce().getListe_arcs().get(i).getV2().getPosition_x();
                      int v1_y=getVdci().getCarteVoyageurDeCommerce().getListe_arcs().get(i).getV1().getPosition_y();
                      int v2_y=getVdci().getCarteVoyageurDeCommerce().getListe_arcs().get(i).getV2().getPosition_y();
                      
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
    
