/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

import java.util.Comparator;

/**
 *
 * @author Melany
 */
public class Arc implements Comparable {

    private Ville v1;
    private Ville v2;
    private float distance;

    // Getters and Setters
    public Ville getV1() {
        return v1;
    }

    // Constructor
    public Arc(Ville v1, Ville v2) {
        this.v1 = v1;
        this.v2 = v2;
        int posX_source = v1.getPosition_x();
        int posX_destination = v2.getPosition_x();
        int posY_source = v1.getPosition_y();
        int posY_destination = v2.getPosition_y();

        distance = Math.abs((float) (Math.sqrt(Math.pow(posX_source - posX_destination, 2) + Math.pow(posY_source - posY_destination, 2))));
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

    
    public boolean equals(Object obj) {
      // Used for comparisions during add/remove operations
      Arc arc = (Arc) obj;
      return (distance == arc.distance &&
              v1 == arc.getV1() && v2 == arc.getV2());
    }

    @Override
    public int compareTo(Object o) {
        float cost1 = this.distance;
        float cost2 = ((Arc) o).getDistance();
        Ville from1 = this.v1;
        Ville from2 = ((Arc) o).getV1();
        Ville to1 = this.v2;
        Ville to2 = ((Arc) o).getV2();

        if (cost1 < cost2) {
            return (-1);
        } else if (cost1 == cost2 && from1 == from2 && to1 == to2) {
            return (0);
        } else if (cost1 == cost2) {
            return (-1);
        } else if (cost1 > cost2) {
            return (1);
        } else {
            return (0);
        }
    }
    
    @Override
    public String toString() {
        return ("[" + v1.getNom() + "] \t--- " + this.distance 
                + "\t --- [" + v2.getNom() + "]");
    }

}
