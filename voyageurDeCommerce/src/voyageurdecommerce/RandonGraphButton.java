/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFrame;

/**
 *
 * @author Melany
 */
public class RandonGraphButton extends JButton implements ActionListener{
    private int nbVilles;
    private Carte randomCarte;
    private RandomHisto rh;
    private VoyageurDeCommerceInterface vdci;

    public RandonGraphButton(String name, VoyageurDeCommerceInterface vdci){
        super(name);
        nbVilles=0;
        randomCarte=new Carte();
        this.vdci=vdci;
    }
    public void actionPerformed(ActionEvent e) {
        String nom = JOptionPane.showInputDialog("Nombre de villes à générer:", "2");
        nbVilles = Integer.parseInt(nom);
        while (nbVilles<2 | nom==null){
            nom = JOptionPane.showInputDialog("Incorrect! entrez au moins 2 villes.", "");
            nbVilles = Integer.parseInt(nom);}
        for (int i=0; i<nbVilles;i++){
            Double posX=Math.random()*580+1;
            int pos_X=posX.intValue();
            Double posY=Math.random()*533+1;
            int pos_Y=posY.intValue();
            vdci.getCarteVoyageurDeCommerce().getListe_villes().add(new Ville(String.valueOf(i),pos_X,pos_Y));

        }
        vdci.getCarteVoyageurDeCommerce().creerClique();
        vdci.getMap().repaint();
        //rh = new RandomHisto("Test exemple", ":)");
    }
    
}
