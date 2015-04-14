/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

import java.util.ArrayList;

/**
 *
 * @author Melany
 */
public class Carte {
    private ArrayList<Ville> liste_villes;
    private ArrayList<Arc> liste_arcs;

public Carte(){
    liste_villes = new ArrayList<>();
    liste_arcs = new ArrayList<>();
}

public void ajouterNoeud(Ville v){
    this.liste_villes.add(v);
}

public void ajouterArc(Arc a){
    this.liste_arcs.add(a);
}

public static void main(String[] args){
    Ville v1 = new Ville("Toulouse",0,100);
    Ville v2 = new Ville("Paris",100,500);
    Ville v3 = new Ville("Nord",700,300);
    Ville v4 = new Ville("Marseille",200,20);
    Carte carteTest = new Carte();
    carteTest.ajouterNoeud(v1);
    carteTest.ajouterNoeud(v2);
    carteTest.ajouterNoeud(v3);
    carteTest.ajouterNoeud(v4);
    Arc a1 = new Arc(v1, v2);
    Arc a2 = new Arc(v1, v3);
    Arc a3 = new Arc(v1, v4);

    Arc a4 = new Arc(v2, v3);
    Arc a5 = new Arc(v2, v4);
    Arc a6 = new Arc(v3, v4);

            
    carteTest.ajouterArc(a1);
    carteTest.ajouterArc(a2);
    carteTest.ajouterArc(a3);
    carteTest.ajouterArc(a4);
    carteTest.ajouterArc(a5);
    carteTest.ajouterArc(a6);
    System.out.println(carteTest.liste_arcs.get(0).distance);
    //System.out.println(carteTest.liste_arcs.get(0).distance);
    }

}