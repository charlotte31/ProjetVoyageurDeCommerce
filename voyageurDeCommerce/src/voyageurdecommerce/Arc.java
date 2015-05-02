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
    private float distance;
    
    // Getters and Setters
    public Ville getV1() {
        return v1;
    }
    
    // Constructor
    public Arc(Ville v1, Ville v2){
        this.v1=v1;
        this.v2=v2;
        int posX_source=v1.getPosition_x();
        int posX_destination=v2.getPosition_x();
        int posY_source=v1.getPosition_y();
        int posY_destination=v2.getPosition_y();

        distance=Math.abs((float)(Math.sqrt(Math.pow(posX_source-posX_destination,2)+Math.pow(posY_source-posY_destination,2))));
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
     
    public String getNomV1() {
        return v1.getNom();
    }
    public void setNomV1(String nom) {
        v1.setNom(nom);
    }  
    
    public String getNomV2() {
        return v2.getNom();
    }

    public void setNomV2(String nom) {
        v2.setNom(nom);
    }  
    
    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
    
    

// Pour tester si besoin
    public static void main(String[] args) {
    }

}