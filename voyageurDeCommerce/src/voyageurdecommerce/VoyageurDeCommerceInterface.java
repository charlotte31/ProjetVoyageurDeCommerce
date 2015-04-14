/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

import com.sun.webkit.graphics.WCImage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Charlotte
 */
public class VoyageurDeCommerceInterface extends JFrame {
    private Carte carteVoyageurDeCommerce;
    private JMenuItem itemNouveau;
    private JMenuItem itemOuvrir;
    private JMenuItem itemSauvegarder;
    private JMenuItem itemQuitter;

    private JMenuItem itemAlgorithme;

    private JMenuItem itemComparaison;
    private JMenuItem itemGeneration;

    private MapPanel map;

    private JLabel labelX;
    private JLabel labelY;
    private JLabel labelDist;

    private JTable tableau;

   public VoyageurDeCommerceInterface() {
        super("Voyageur de commerce");
        this.setPreferredSize(new Dimension(800, 600));
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        Container content = this.getContentPane();
        BorderLayout layout = new BorderLayout();
        content.setLayout(layout);
        addJMenuBar();
        addJpanel(content);
        addJLabel(content);
        addJTable(content);
        pack();

    }

    private void addJMenuBar() {
        JMenuBar menu = new JMenuBar();
        JMenu menuFichier = new JMenu("Fichier");
        itemNouveau = new JMenuItem("Nouveau");
        itemOuvrir = new JMenuItem("Ouvrir");
        itemSauvegarder = new JMenuItem("Sauvegarder");
        itemQuitter = new JMenuItem("Quitter");

        menuFichier.add(itemNouveau);
        menuFichier.add(itemOuvrir);
        menuFichier.add(itemSauvegarder);
        menuFichier.add(itemQuitter);
        menu.add(menuFichier);

        JMenu menuCalculer = new JMenu("Calculer");
        itemAlgorithme = new JMenuItem("Algorithme1");
        menuCalculer.add(itemAlgorithme);
        menu.add(menuCalculer);

        JMenu menuOutil = new JMenu("Outil");
        itemComparaison = new JMenuItem("Comparaison");
        itemGeneration = new JMenuItem("Generation");
        menuOutil.add(itemComparaison);
        menuOutil.add(itemGeneration);
        menu.add(menuOutil);
        this.setJMenuBar(menu);
    }

    private void addJpanel(Container content) {
        map = new MapPanel();
        map.setSize(600, 400);
//        ImageIcon icon = new ImageIcon("./resources/France.png");
//        JLabel img = new JLabel(icon);
//        map.add(img);
        map.setLayout(null);
        map.setBorder(new BevelBorder(BevelBorder.RAISED, Color.lightGray,
                Color.yellow));
        JLabel city = new JLabel("Toulouse");

        map.add(city);
        map.repaint();
        content.add(map, BorderLayout.CENTER);
    }

    private void addJLabel(Container content) {
        JPanel panelInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelX = new JLabel("X");
        labelY = new JLabel("Y");
        labelDist = new JLabel("Ville");

        panelInfo.add(labelDist);
        panelInfo.add(labelX);
        panelInfo.add(labelY);

        content.add(panelInfo, BorderLayout.SOUTH);
    }

    private void addJTable(Container content) {
        JPanel panelTable = new JPanel();
        ModelTable model = new ModelTable();
        tableau = new JTable(model);
        panelTable.add(tableau);
        content.add(panelTable, BorderLayout.EAST);
        model.addData("Coucou");
        model.addData("Charlotte");
    }

    public static void main(String[] args) {
        VoyageurDeCommerceInterface i = new VoyageurDeCommerceInterface();
    }
}
