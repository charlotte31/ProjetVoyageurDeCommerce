/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Melany Classe qui contient la liste des arcs, des noeuds, et les
 * méthodes correspondant aux différents algorithmes
 */
public class Carte {

    private ArrayList<Ville> liste_villes;
    private ArrayList<Arc> liste_arcs;

    public Carte() {
        liste_villes = new ArrayList<>();
        liste_arcs = new ArrayList<>();
    }

    public void ajouterNoeud(Ville v) {
        // Ajoute un noeud dans la liste de la carte, sans aurotiser les doubles
        if (this.getListe_villes().contains(v) == false) {
            this.getListe_villes().add(v);
        } else {
            System.out.println("Noeud double");
        }
    }

    public void ajouterArc(Arc a) {
        this.getListe_arcs().add(a);
    }

    public ArrayList<Arc> arcsCopie() {
        ArrayList<Arc> copie = new ArrayList();
        for (int i = 0; i < this.getListe_arcs().size(); i++) {
            copie.add(this.getListe_arcs().get(i));
        }
        return (copie);
    }

    public ArrayList<Ville> villesCopie() {
        ArrayList<Ville> copie = new ArrayList();
        for (int i = 0; i < this.getListe_villes().size(); i++) {
            copie.add(this.getListe_villes().get(i));
        }
        return (copie);
    }

    public void creerArc(Ville v1, Ville v2) {
// Génère un arc entre deux noeuds donnés            
        Arc nouvel_arc = new Arc(v1, v2);
        this.ajouterArc(nouvel_arc);
    }

    public void creerClique() {
        // Génère les arêtes connectant tous les sommets d'un graphe pour en obtenir la clique
        this.getListe_arcs().removeAll(getListe_arcs());
        ArrayList<Ville> copie = this.villesCopie();
        for (int j = 0; j < copie.size(); j++) { // pour chaque noeud source
            for (int k = 0; k < copie.size(); k++)// pour chaque noeud destination
            {
                if (!copie.get(j).getNom().equals(copie.get(k).getNom())) {
                    this.creerArc(copie.get(j), copie.get(k));// creation de l'arc
                }
            }
            copie.remove(copie.get(j)); // et élimination pour éviter de créer des doubles
            j = 0;

        }
    }

    public void supprimerVille(Ville v, ArrayList<Ville> liste_villes) {
        // Supprime une ville d'une liste donnée

        for (int i = 0; i < liste_villes.size(); i++) {

            if (v.getNom().equals(liste_villes.get(i).getNom())) {
                liste_villes.remove(v);
                if (i - 1 >= 0 && i != liste_villes.size() - 1) {
                    i = i - 1;
                };
            }
        }
    }

    public Ville getVille(String nomVille, ArrayList<Ville> liste_villes) {
        // Recherche une ville d'une liste donnée
        Ville v_res = null;
        for (int i = 0; i < liste_villes.size(); i++) {
            if (nomVille.equals(liste_villes.get(i).getNom())) {
                v_res = liste_villes.get(i);
            }
        }
        return (v_res);
    }

    public Arc getArcV(Ville v1, Ville v2, ArrayList<Arc> liste_arc) {
        // Recherche l'arc joignant deux villes données
        Arc a = null;
        for (int i = 0; i < liste_arc.size(); i++) {
            if ((liste_arc.get(i).getNomV1() == v1.getNom() && liste_arc.get(i).getNomV2() == v2.getNom())
                    | (liste_arc.get(i).getNomV2() == v1.getNom() && liste_arc.get(i).getNomV1() == v2.getNom())) {
                a = liste_arc.get(i);
            }
        }
        return (a);
    }

    public void supprimerArcV(Ville v1, Ville v2, ArrayList<Arc> liste_arc) {
        // supprime un arc joignant deux villes données.
        Arc a = null;
        for (int i = 0; i < liste_arc.size(); i++) {
            if ((liste_arc.get(i).getNomV1() == v1.getNom() && liste_arc.get(i).getNomV2() == v2.getNom())
                    | (liste_arc.get(i).getNomV2() == v1.getNom() && liste_arc.get(i).getNomV1() == v2.getNom())) {
                liste_arc.remove(liste_arc.get(i));
                if (i - 1 >= 0 && i != liste_arc.size() - 1) {
                    i = i - 1;
                };
            }
        }
    }

    public Arc getArcD(float d, ArrayList<Arc> liste_arc) {
        // recherche un arc d'un certain poids
        Arc a = null;
        for (int i = 0; i < liste_arc.size(); i++) {
            if (liste_arc.get(i).getDistance() == d) {
                a = liste_arc.get(i);
            }
        }
        return (a);
    }

    public void supprimerArcD(float d, ArrayList<Arc> liste_arc) {
        // supprime un arc d'un certain poids
        for (int i = 0; i < liste_arc.size(); i++) {
            if (liste_arc.get(i).getDistance() == d) {
                liste_arc.remove(liste_arc.get(i));
                if (i - 1 >= 0 && i != liste_arc.size() - 1) {
                    i = i - 1;
                };
            }
        }
    }

    public ArrayList<Object> rechercherMinDistance(Ville v, ArrayList<Arc> arcs) {
        // récupère l'arête de poids minimal + le noeud destination dans un ensemble d'arêtes donné et pour un sommet donné
        ArrayList<Object> result = new ArrayList<Object>();
        float dMin = -1;
        Ville v1 = null;
        for (int iArc = 0; iArc < arcs.size(); iArc++) {
            if (v.getNom().equals(arcs.get(iArc).getNomV1())) {
                dMin = arcs.get(iArc).getDistance();
                v1 = arcs.get(iArc).getV2();
            }

            if (v.getNom().equals(arcs.get(iArc).getNomV2())) {
                dMin = arcs.get(iArc).getDistance();
                v1 = arcs.get(iArc).getV1();
            }
        }
        for (int iArc = 0; iArc < arcs.size(); iArc++) {
            if (v.getNom().equals(arcs.get(iArc).getNomV1())) {
                if (arcs.get(iArc).getDistance() < dMin) {
                    dMin = arcs.get(iArc).getDistance();
                    v1 = arcs.get(iArc).getV2();
                }
            }
            if (v.getNom().equals(arcs.get(iArc).getNomV2())) {
                if (arcs.get(iArc).getDistance() < dMin) {
                    dMin = arcs.get(iArc).getDistance();
                    v1 = arcs.get(iArc).getV1();
                }
            }
        }
        result.add(v1);
        result.add(dMin);
        return (result);
    }

    public ArrayList<Object> rechercherMaxDistance(Ville v, ArrayList<Arc> arcs) {
        // récupère l'arête de poids maximal + le noeud destination dans un ensemble d'arêtes donné et pour un sommet donné
        ArrayList<Object> result = new ArrayList<Object>();
        float dMax = -1;
        Ville v1 = null;
        for (int iArc = 0; iArc < arcs.size(); iArc++) {
            if (v.getNom().equals(arcs.get(iArc).getNomV1()) | v.getNom().equals(arcs.get(iArc).getNomV2())) {
                dMax = arcs.get(iArc).getDistance();
            }
        }
        for (int iArc = 0; iArc < arcs.size(); iArc++) {
            if (v.getNom().equals(arcs.get(iArc).getNomV1())) {
                if (arcs.get(iArc).getDistance() > dMax) {
                    dMax = arcs.get(iArc).getDistance();
                    v1 = arcs.get(iArc).getV2();
                }
            }
            if (v.getNom().equals(arcs.get(iArc).getNomV2())) {
                if (arcs.get(iArc).getDistance() > dMax) {
                    dMax = arcs.get(iArc).getDistance();
                    v1 = arcs.get(iArc).getV1();
                }
            }
        }
        result.add(v1);
        result.add(dMax);
        return (result);
    }

    public float rechercherDistance(Ville courante, Ville voisin) {
        // retourne la distance entre deux villes
        float d = -1;
        for (int i = 0; i < this.getListe_arcs().size(); i++) {
            if ((this.getListe_arcs().get(i).getNomV1().equals(courante.getNom()) && this.getListe_arcs().get(i).getNomV2().equals(voisin.getNom()))
                    | (this.getListe_arcs().get(i).getNomV2().equals(courante.getNom()) && this.getListe_arcs().get(i).getNomV1().equals(voisin.getNom()))) {
                d = this.getListe_arcs().get(i).getDistance();
            }
        }
        return (d);
    }

    public ArrayList<Object> plusProcheVoisins(Ville v) {
        //(pour tester dans l'interface)
        System.out.println("PlusProcheVoisin");
        // Initialisation des résultats
        ArrayList<Object> result = new ArrayList<Object>();
        ArrayList<String> chemin = new ArrayList<String>();
        float distance = 0;
        long debut;
        long duree;

        // Initialisation pour l'algorithme       
        ArrayList<Ville> villes = this.villesCopie();
        ArrayList<Arc> arcs = this.arcsCopie();
        chemin.add(v.getNom());
        ArrayList<Object> resultMin = this.rechercherMinDistance(v, arcs);
        distance = distance + (float) resultMin.get(1);
        Ville vTemp = v;
        debut = System.currentTimeMillis();
        // Algorithme des plus proches voisins
        for (int iV = 0; iV < villes.size(); iV++) {
            for (int iA = 0; iA < arcs.size(); iA++) {
                if (v.getNom().equals(arcs.get(iA).getNomV1()) | v.getNom().equals(arcs.get(iA).getNomV2())) {
                    if (v.getNom().equals(arcs.get(iA).getNomV1())
                            && arcs.get(iA).getDistance() == (float) resultMin.get(1)
                            && chemin.contains(arcs.get(iA).getNomV2()) == false) {
                        chemin.add(arcs.get(iA).getNomV2());
                        vTemp = arcs.get(iA).getV2();
                    }
                    if (v.getNom().equals(arcs.get(iA).getNomV2())
                            && arcs.get(iA).getDistance() == (float) resultMin.get(1)
                            && chemin.contains(arcs.get(iA).getNomV1()) == false) {
                        chemin.add(arcs.get(iA).getNomV1());
                        vTemp = arcs.get(iA).getV1();
                    }
                    arcs.remove(arcs.get(iA));
                    iA = iA - 1;
                }
            }
            villes.remove(v);
            resultMin = this.rechercherMinDistance(vTemp, arcs);
            distance = distance + (float) resultMin.get(1);
            v = vTemp;
        }
        System.out.println(villes.size());
        if (chemin.contains(villes.get(villes.size() - 1).getNom()) == false) {
            chemin.add(villes.get(villes.size() - 1).getNom());
        }

        for (int i = 0; i < 10000000; i++) {
            System.currentTimeMillis();
        }
        duree = System.currentTimeMillis() - debut;
        ArrayList<Ville> cheminRes = toVille(chemin);
        System.out.println(chemin);
        // Résultats
        result.add(cheminRes);
        result.add(distance);
        result.add(duree);
        System.out.println("Le chemin le plus court est :");
        for (int i = 0; i < chemin.size(); i++) {
            System.out.println(chemin.get(i));
        }
    //System.out.println("Distance de ce chemin: "+distance); 
        //System.out.println("Performance algorithmique (ms): "+duree);
        return (result);
    }

    public ArrayList<Object> moindreCout(Ville v) {
        //(pour tester dans l'interface)
        System.out.println("MoindreCout");
        // Initialisation des variables résultantes
        ArrayList<Object> result = new ArrayList<Object>();
        ArrayList<Ville> chemin = new ArrayList<Ville>();
        float distance = 0;
        long debut = System.currentTimeMillis();
        long duree;

        // Initialisation pour l'algorithme       
        ArrayList<Ville> villes = this.villesCopie();
        ArrayList<Arc> arcs = this.arcsCopie();
        chemin.add(v);
        ArrayList<Object> resultMin = this.rechercherMinDistance(v, arcs);
        float dMin = (float) resultMin.get(1);
        Ville vMin = (Ville) resultMin.get(0);
        chemin.add(vMin);
        this.supprimerVille(v, villes);
        this.supprimerVille(vMin, villes);
        this.supprimerArcV(v, vMin, arcs);

        //Algorithme
        for (int i = 0; i < villes.size(); i++) {
            if (chemin.contains(villes.get(i)) == false) {
                chemin.add(villes.get(i));
            }
            for (int j = 0; j < villes.size(); j++) {
                if (villes.get(i).getNom().equals(villes.get(j).getNom())) {
                } else {
                    if ((this.rechercherDistance(v, villes.get(j)) + this.rechercherDistance(vMin, villes.get(j)) - dMin)
                            < (this.rechercherDistance(v, villes.get(i)) + this.rechercherDistance(vMin, villes.get(i)) - dMin)) {
                        chemin.remove(villes.get(i));
                        if (chemin.contains(villes.get(j)) != true) {
                            chemin.add(villes.get(j));
                        }
                        if (chemin.contains(villes.get(i)) == false) {
                            chemin.add(villes.get(i));
                        }
                    }
                }
            }
        }

        for (int i = 0; i < chemin.size() - 1; i++) {
            distance = distance + this.rechercherDistance(chemin.get(i), chemin.get(i + 1));
        }

        for (int i = 0; i < 10000000; i++) {
            System.currentTimeMillis();
        }
        duree = System.currentTimeMillis() - debut;
        // Résultats
        result.add(chemin);
        result.add(distance);
        result.add(duree);

        // Pour tester
        System.out.println("Le chemin le plus court est :");
 //       for (int i = 0; i < chemin.size(); i++) {
 //           System.out.println(chemin.get(i).getNom());
 //       }
    //System.out.println("Distance de ce chemin: "+distance); 
        //System.out.println("Performance algorithmique (ms): "+duree);
        return (result);
    }

    public ArrayList<Ville> toVille(ArrayList<String> nomVilles) {
        ArrayList<Ville> villes = new ArrayList<Ville>();
        for (int i = 0; i < nomVilles.size(); i++) {
            for (int j = 0; j < this.getListe_villes().size(); j++) {
                if (nomVilles.get(i).equals(this.getListe_villes().get(j).getNom())) {
                    villes.add(this.getListe_villes().get(j));
                }
            }
        }
        return (villes);
    }

    public ArrayList<String> toString(ArrayList<Ville> villes) {
     // Les  algos retournent le chemin constitué d'instance de la classe ville,
        // ici on récupère le même chemin mais avec les noms des villes
        // Besoin des deux formes : pour redessiner (en bleu) et pour afficher le chemin (pop up)

        ArrayList<String> nomVilles = new ArrayList<String>();
        for (int i = 0; i < villes.size(); i++) {
            nomVilles.add(villes.get(i).getNom());

        }
        return (nomVilles);
    }

    public ArrayList<Object> insertionPlusEloignes(Ville v) {
        //(pour tester dans l'interface)
        System.out.println("insertionPlusEloignes");
        // Initialisation des résultats
        ArrayList<Object> result = new ArrayList<Object>();

        return (result);
    }

    public static void main(String[] args) {
        Ville v1 = new Ville("Toulouse", 0, 100);
        Ville v2 = new Ville("Paris", 100, 500);
        Ville v3 = new Ville("Nord", 700, 300);
        Ville v4 = new Ville("Marseille", 200, 20);
        Ville v5 = new Ville("Bordeaux", 500, 200);
        Carte carteTest = new Carte();
        carteTest.ajouterNoeud(v1);
        carteTest.ajouterNoeud(v2);
        carteTest.ajouterNoeud(v3);
        carteTest.ajouterNoeud(v4);
        carteTest.ajouterNoeud(v5);
        carteTest.creerClique();
        Arc a1 = new Arc(v1, v2);
        Arc a2 = new Arc(v1, v3);
        Arc a3 = new Arc(v1, v4);

        Arc a4 = new Arc(v2, v3);
        Arc a5 = new Arc(v2, v4);
        Arc a6 = new Arc(v3, v4);
        //System.out.println(a1.getDistance());
        //System.out.println(a2.getDistance());
        //System.out.println(a3.getDistance());
        //System.out.println(a4.getDistance());
        //System.out.println(a5.getDistance());
        //System.out.println(a6.getDistance());
        //carteTest.ajouterArc(a1);
        //carteTest.ajouterArc(a2);
        //carteTest.ajouterArc(a3);
        //carteTest.ajouterArc(a4);
        //carteTest.ajouterArc(a5);
        //carteTest.ajouterArc(a6);

        //ArrayList<Object> ipe = carteTest.moindreCout(v1);
        //System.out.println(carteTest.toString((ArrayList<Ville>)ipe.get(0)));
        //ArrayList<Object> ppv = carteTest.plusProcheVoisins(v1);
        //ArrayList<Object> ipe = carteTest.insertionPlusEloignes(v1);
        //System.out.println(carteTest.liste_arcs.get(0).getDistance());
    }

    /**
     * @return the liste_villes
     */
    public ArrayList<Ville> getListe_villes() {
        return liste_villes;
    }

    /**
     * @return the liste_arcs
     */
    public ArrayList<Arc> getListe_arcs() {
        return liste_arcs;
    }

}
