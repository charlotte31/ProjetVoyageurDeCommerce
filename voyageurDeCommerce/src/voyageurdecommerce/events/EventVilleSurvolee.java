/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce.events;

import java.util.EventObject;

/**
 *
 * @author Charlotte
 */
public class EventVilleSurvolee extends EventObject{
    
    private String nomVille;
    private int x;
    private int y;

    public EventVilleSurvolee(Object source, String nomVille, int x, int y) {
        super(source);
        this.nomVille = nomVille;
        this.x = x;
        this.y = y;
    }

    public String getNomVille() {
        return nomVille;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
