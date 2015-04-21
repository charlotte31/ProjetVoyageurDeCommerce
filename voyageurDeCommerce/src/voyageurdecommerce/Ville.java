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
public class Ville extends Noeud{
    private String nom;
    
 // Getters and Setters
        public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }  

// Constructor
    public Ville(String nom,int posX, int posY){
        super(posX,posY);
        this.nom=nom;
    }



    
    
// Pour tester si besoin
    public static void main(String[] args) {

}


}
