/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
                if (k != j) {
                    this.creerArc(copie.get(j), copie.get(k));
                }
            }
        }
    }

    public void supprimerVille(Ville v, ArrayList<Ville> liste_villes) {
        // Supprime une ville d'une liste donnée

        for (int i = 0; i < liste_villes.size(); i++) {

            if (v.getNom().equals(liste_villes.get(i).getNom())) {
                liste_villes.remove(v);
                if (i - 1 >= 0 && i != liste_villes.size() - 1) {
                    i = i - 1;
                }
            }
        }
    }

    public Ville getVille(String nomVille, ArrayList<Ville> liste_villes) {
        // Recherche une ville d'une liste donnée
        Ville v_res = null;
        for (Ville liste_ville : liste_villes) {
            if (nomVille.equals(liste_ville.getNom())) {
                v_res = liste_ville;
            }
        }
        return (v_res);
    }

    public Arc getArcV(Ville v1, Ville v2, ArrayList<Arc> liste_arc) {
        // Recherche l'arc joignant deux villes données
        Arc a = null;
        for (Arc liste_arc1 : liste_arc) {
            if ((liste_arc1.getNomV1().equals(v1.getNom())
                    && liste_arc1.getNomV2().equals(v2.getNom()))
                    || (liste_arc1.getNomV2().equals(v1.getNom())
                    && liste_arc1.getNomV1().equals(v2.getNom()))) {
                a = liste_arc1;
            }
        }
        return (a);
    }

    public void supprimerArcV(Ville v1, Ville v2, ArrayList<Arc> liste_arc) {
        // supprime un arc joignant deux villes données.
        Arc a = null;
        for (int i = 0; i < liste_arc.size(); i++) {
            if ((liste_arc.get(i).getNomV1().equals(v1.getNom()) && liste_arc.get(i).getNomV2().equals(v2.getNom()))
                    | (liste_arc.get(i).getNomV2().equals(v1.getNom()) && liste_arc.get(i).getNomV1().equals(v2.getNom()))) {
                liste_arc.remove(liste_arc.get(i));
                if (i - 1 >= 0 && i != liste_arc.size() - 1) {
                    i = i - 1;
                }
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

        Ville vIni = v;
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
            //villes.remove(v);
            resultMin = this.rechercherMinDistance(vTemp, arcs);           
            v = vTemp;
        }
        if (chemin.contains(villes.get(villes.size() - 1).getNom()) == false) {
            chemin.add(villes.get(villes.size() - 1).getNom());
        }
        chemin.add(vIni.getNom());
      
       
        for (int i = 0; i < 10000000; i++) {
            System.currentTimeMillis();
        }
        duree = System.currentTimeMillis() - debut;
        ArrayList<Ville> cheminRes = toVille(chemin);
        // Résultats
        distance=0;
        result.add(cheminRes);
           for (int i = 0; i < cheminRes.size() - 1; i++) {
            distance = distance + this.rechercherDistance(cheminRes.get(i), cheminRes.get(i + 1));
        }
        result.add(distance);
        result.add(duree);
        System.out.println("Le chemin le plus court est :");
        for (int i = 0; i < chemin.size(); i++) {
            System.out.println(chemin.get(i));
        }
        System.out.println("Distance de ce chemin: "+distance); 
        System.out.println("Performance algorithmique (ms): "+duree);
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
        chemin.add(v);

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
        for (int i = 0; i < chemin.size(); i++) {
            System.out.println(chemin.get(i).getNom());
        }
       
        System.out.println("Distance de ce chemin: "+distance); 
        System.out.println("Performance algorithmique (ms): "+duree);
        return (result);
    }

    public ArrayList<Object> prim(Ville v) {
        System.out.println("Prim");
        ArrayList<Object> res = new ArrayList<>();
        ArrayList<Ville> villes = new ArrayList<>();
        ArrayList<Arc> arcs = new ArrayList<>();
        long duree;
        long debut = System.currentTimeMillis();
        float distance = 0;
        villes.add(v);

        while (villes.size() != liste_villes.size()) {
            Arc minArc = null;
            ArrayList<Ville> intersect = intersection(liste_villes, villes);
            System.out.println("INTERSECTION");
            for (int index = 0; index < intersect.size(); index++) {
                System.out.println(intersect.get(index).getNom());
            }
            System.out.println("-----------------------");
            for (int i = 0; i < liste_arcs.size(); i++) {
                Arc arc = liste_arcs.get(i);
                System.out.println("Arc testé : " + arc.getNomV1() + "-- " + arc.getDistance() + " --" + arc.getNomV2());
                if (villes.contains(arc.getV1()) && intersect.contains(arc.getV2())) {
                    if (minArc == null) {
                        minArc = arc;
                        System.out.println("Min Arc : " + minArc.getNomV1() + "-- " + minArc.getDistance() + " --" + minArc.getNomV2());
                    } else if (arc.getDistance() < minArc.getDistance()) {
                        minArc = arc;
                        System.out.println("Nouveau min Arc : " + minArc.getNomV1() + "-- " + minArc.getDistance() + " --" + minArc.getNomV2());
                    } else {
                        System.out.println("Arc rejeté!");
                    }
                }
            }
            if (minArc == null) {
                System.out.println("Euh on aurait pas dû être là. Maintenant on va boucler à l'infini.... ");
            } else {
                System.out.println("On ajoute à V' : " + minArc.getV2().getNom());
                villes.add(minArc.getV2());
                arcs.add(minArc);
            }
        }
        villes.add(v);
        for (Ville ville : villes) {
            System.out.println(ville.getNom());
        }
        int i = 1;

        for (Arc arc : arcs) {
            System.out.println("Arc n°" + i + " : " + arc.getNomV1() + "-- " + arc.getDistance() + " --" + arc.getNomV2());
            i++;
        }
        for (int index = 0; index < 10000000; index++) {
            System.currentTimeMillis();
        }
        duree = System.currentTimeMillis() - debut;
        for (int index = 0; index < villes.size() - 1; index++) {
            distance = distance + this.rechercherDistance(villes.get(index), villes.get(index + 1));
        }
        res.add(villes);
       // res.add(arcs);
        res.add(distance);
        res.add(duree);
        System.out.println("\n\nRésultats:");
        System.out.println("Le chemin le plus court est :");
        for (int nb = 0; nb < villes.size(); nb++) {
            System.out.println(villes.get(nb).getNom());
        }    
        System.out.println("Distance de ce chemin: "+distance); 
        System.out.println("Performance algorithmique (ms): "+duree);
        return res;
    }

    /**
     *
     * @param v1 : liste de toutes les villes; la plus grande
     * @param v2
     * @return
     */
    public ArrayList<Ville> intersection(ArrayList<Ville> v1, ArrayList<Ville> v2) {
        ArrayList<Ville> res = new ArrayList<>(v1);
        for (int i = 0; i < v2.size(); i++) {
            for (int j = 0; j < v1.size(); j++) {
                if (v2.get(i) == v1.get(j)) {
                    res.remove(v1.get(j));
                }
            }
        }
        return res;
    }

    public ArrayList<Object> insertionPlusEloignes(Ville v) {
        //(pour tester dans l'interface)
        System.out.println("insertionPlusEloignes");
        // Initialisation des résultats
        ArrayList<Object> result = new ArrayList<>();
        ArrayList<String> chemin = new ArrayList<>();
        
        long debut = System.currentTimeMillis();;
        long duree;
        
        // Initialisation pour l'heuristique
        float distRes=0;
        float distance = 0;
        Ville vSucc = null;
        Ville vPred = null;
      
        ArrayList<Ville> aVisiter = villesCopie();
        ArrayList<Ville> dejaVu = new ArrayList<Ville>();
        aVisiter.remove(v);
        
        dejaVu.add(v);
        chemin.add(v.getNom());
        chemin.add(((Ville)rechercherMinDistance(v, liste_arcs).get(0)).getNom());
        dejaVu.add((Ville)rechercherMinDistance(v, liste_arcs).get(0));
        aVisiter.remove((Ville)rechercherMinDistance(v, liste_arcs).get(0));
       
        while (chemin.size()!= liste_villes.size()){
            ArrayList<Float> listeDist = new ArrayList<Float>();
            ArrayList<Ville> listSucc = new ArrayList<Ville>();
            ArrayList<Ville> listPred = new ArrayList<Ville>(); 

            float distMAX = 0;
            for (int i=0; i<aVisiter.size();i++){
                distance = 0;

                for (int j=0;j<dejaVu.size();j++){
                    float d = rechercherDistance(aVisiter.get(i), dejaVu.get(j));
                    if ( d <= distance | distance ==0){
                        distance = d;
                        vSucc=dejaVu.get(j);
                        vPred=aVisiter.get(i);
                        
                    }
                listSucc.add(vSucc);
                listPred.add(vPred);
                System.out.println("ICI¨"+vPred.getNom());
                listeDist.add(distance);
                }         
            for(int j=0; j<listeDist.size();j++){
                if(listeDist.get(j)>distMAX){
                    distance=distMAX;
                    vSucc=listSucc.get(j);
                    vPred=listPred.get(j);
                    
                }
            
            }         
            chemin.add(result.indexOf(vSucc)+2, vPred.getNom());
            System.out.println("TEST"+chemin.get(0));
            System.out.println("ICI2222¨"+vSucc.getNom());
            dejaVu.add(vPred);
            aVisiter.remove(vPred);
            }
        }
        chemin.add(v.getNom()); 
        for (int i = 0; i < 10000000; i++) {
            System.currentTimeMillis();
        }
        duree = System.currentTimeMillis() - debut;
        ArrayList<Ville> cheminRes = toVille(chemin);
        for(int i=0; i<cheminRes.size()-1;i++){
            distRes=distRes+rechercherDistance(cheminRes.get(i),cheminRes.get(i+1));
        }
        // Résultats
        result.add(cheminRes);
        result.add(distRes);
        result.add(duree);
        System.out.println("Le chemin le plus court est :");
        for (int i = 0; i < chemin.size(); i++) {
           System.out.println(chemin.get(i));
        }
        System.out.println("Distance de ce chemin: "+distRes); 
        System.out.println("Performance algorithmique (ms): "+duree);
        return (result);
    }

    public ArrayList<Object> twoOpt(Ville v) {
        System.out.println("2-Opt");
        ArrayList<Object> res = new ArrayList<>();
        ArrayList<Ville> randomPath = generateRandomPath(v);
        boolean ameliore = true;
        long debut = System.currentTimeMillis();
        long duree;
        float distance = 0;
        while (ameliore) {
            ameliore = false;
            for (int i = 0; i < randomPath.size() - 1; i++) {
                for (int j = 0; j < randomPath.size() - 1; j++) {
                    if (j != i - 1 && j != i + 1 && i != j) {
                        System.out.println("i : " + i + " j : " + j);
                        
                        System.out.println("i : " + randomPath.get(i).getNom() + "  i + 1 :" + randomPath.get(i + 1).getNom());
                        float distanceI_I1 = getArcV(randomPath.get(i), randomPath.get(i + 1), liste_arcs).getDistance();
                        
                        System.out.println("j : " + randomPath.get(j).getNom() + "  j + 1 :" + randomPath.get(j + 1).getNom());
                        float distanceJ_J1 = getArcV(randomPath.get(j), randomPath.get(j + 1), liste_arcs).getDistance();
                        
                        System.out.println("i : " + randomPath.get(i).getNom() + "  j :" + randomPath.get(j).getNom());
                        float distanceI_J = getArcV(randomPath.get(i), randomPath.get(j), liste_arcs).getDistance();
                        
                        System.out.println("i +1 : " + randomPath.get(i + 1).getNom() + "  j + 1 :" + randomPath.get(j + 1).getNom());
                        float distanceI1_J1 = getArcV(randomPath.get(i + 1), randomPath.get(j + 1), liste_arcs).getDistance();

                        if (distanceI_I1 + distanceJ_J1 > distanceI_J + distanceI1_J1) {
                            System.out.println("Permutation!!");
                            Ville xi1 = randomPath.get(i + 1);
                            Ville xj = randomPath.get(j);

                            System.out.println("xi1 : " + xi1.getNom() + " / xj : " + xj.getNom());
                            randomPath.set(i + 1, xj);
                            randomPath.set(j, xi1);
                            for (Ville ville : randomPath) {
                                System.out.println(ville.getNom());
                            }
                            ameliore = true;
                        }
                        System.out.println("");
                    }
                }
            }
        }
        randomPath.add(v);
        for (Ville ville : randomPath) {
            System.out.println(ville.getNom());
        }
        
        for (int index = 0; index < randomPath.size() - 1; index++) {
            distance = distance + this.rechercherDistance(randomPath.get(index), randomPath.get(index + 1));
        }
        for (int index = 0; index < 10000000; index++) {
            System.currentTimeMillis();
        }
        duree = System.currentTimeMillis() - debut;
        res.add(randomPath);
        res.add(distance);
        res.add(duree);
        System.out.println("Le chemin le plus court est :");
        for (int i = 0; i < randomPath.size(); i++) {
            System.out.println(randomPath.get(i).getNom());
        }
       
        System.out.println("Distance de ce chemin: "+distance); 
        System.out.println("Performance algorithmique (ms): "+duree);
        return res;
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
                for (int i =0; i<carteTest.liste_arcs.size ();i++){
        System.out.println(carteTest.liste_arcs.get(i).getNomV1()+" "+carteTest.liste_arcs.get(i).getNomV2()+" "+carteTest.liste_arcs.get(i).getDistance());
    }
        //carteTest.twoOpt(v1);
        carteTest.moindreCout(v1);
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

    private ArrayList<Ville> generateRandomPath(Ville v) {
        ArrayList<Arc> arcs = new ArrayList<>();
        ArrayList<Ville> villes = new ArrayList<>();
        ArrayList<Arc> possibleArcs = new ArrayList<>();
        Random r = new Random();
        villes.add(v);
        int index = 0;
        while (villes.size() != liste_villes.size()) {
            Ville currentVille = villes.get(index);
            possibleArcs.clear();
            for (int i = 0; i < liste_arcs.size(); i++) {
                Arc arc = liste_arcs.get(i);
                if (arc.getV1() == currentVille && intersection(liste_villes, villes).contains(arc.getV2())) {
                    possibleArcs.add(arc);
                }
            }

            int val = r.nextInt(possibleArcs.size());
            arcs.add(possibleArcs.get(val));
            villes.add(possibleArcs.get(val).getV2());
            index++;
        }
        //villes.add(v);
        for (Ville ville : villes) {
            System.out.println(ville.getNom());
        }
        return villes;
    }
}
