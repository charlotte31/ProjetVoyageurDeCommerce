/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

/**
 *
 * @author Melany
 */
public class ItemNouvelleFenetre extends JMenuItem implements ActionListener {

    private VoyageurDeCommerceInterface vdci;

    public ItemNouvelleFenetre(String nomItem) {
        super(nomItem);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VoyageurDeCommerceInterface vdci = new VoyageurDeCommerceInterface(false); // Pour le pas afficher le pop up de bienvenue du départ

    }

}
