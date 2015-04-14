/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

/**
 *
 * @author Melany
 */
public class Arc {
    int posX_source;
    int posY_source;
    int posX_destination;
    int posY_destination;
    float distance;
    
    public Arc(Ville v1, Ville v2){
        posX_source=v1.position_x;
        posX_destination=v2.position_x;
        posY_source=v1.position_y;
        posY_destination=v2.position_y;
//        a verifier (float)
        distance=Math.abs((float)Math.sqrt((posX_destination-posX_source)^2+(posY_destination-posX_source)^2));
    }
    
}
