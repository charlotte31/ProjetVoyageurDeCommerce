/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

import javax.swing.JComponent;

/**
 *
 * @author Melany
 */
public class Noeud extends JComponent{
    private int position_x;
    private int position_y;
    
    public Noeud(int posX, int posY){
        position_x=posX;
        position_y=posY;
    }

    public int getPosition_x() {
        return position_x;
    }

    public void setPosition_x(int position_x) {
        this.position_x = position_x;
    }

    public int getPosition_y() {
        return position_y;
    }

    public void setPosition_y(int position_y) {
        this.position_y = position_y;
    }
    
    
}
