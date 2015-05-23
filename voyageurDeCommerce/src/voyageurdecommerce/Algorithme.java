/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;
/**
 *
 * @author Melany
 * Création d'une énumération pour la vérification de l'algorithme à appliquer 
 * lorsque l'utilisateur clique sur un item donné
 */
public enum Algorithme {
    PLUS_PROCHE_VOISIN,
    PLUS_ELOIGNES, 
    MOINDRE_COUT, 
    TWO_OPT, 
    PRIM
}
