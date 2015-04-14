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
    private Ville v1;
    private Ville v2;
    
    public Ville getV1() {
        return v1;
    }

    public void setV1(Ville v1) {
        this.v1 = v1;
    }

    public Ville getV2() {
        return v2;
    }

    public void setV2(Ville v2) {
        this.v2 = v2;
    }
  
    float distance;
    
    public Arc(Ville v1, Ville v2){
        int posX_source=v1.getPosition_x();
        int posX_destination=v2.getPosition_x();
        int posY_source=v1.getPosition_y();
        int posY_destination=v2.getPosition_y();
//        a verifier (float)
        
        distance=Math.abs((float)Math.sqrt(Math.pow(posX_source-posY_source,2)+Math.pow(posX_destination-posY_destination,2)));
    }
    
}
