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
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.Popup;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;



import voyageurdecommerce.events.EventVilleSurvolee;
import voyageurdecommerce.events.VilleSurvoleeListener;
import voyageurdecommerce.graphisme.RenduHeaderTableau;
import voyageurdecommerce.graphisme.RenduTableau;

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
    
    private JMenuItem plusProcheVoisin;
    private JMenuItem plusEloignes;
    private JMenuItem moindreCout;
    private JMenuItem kruskal;
    private JMenuItem prim;
    
    private JMenuItem itemComparaison;
    private JMenuItem itemGeneration;
    private JMenuItem itemNouvellefenetre;
    private MapPanel map;

    private JLabel labelX;
    private JLabel labelY;
    private JLabel labelVille;
    
    private boutonValider boutonValider;
    
    private boolean b;
    private JTable tableau;
    private ModelTable modelTableau;
    
   public VoyageurDeCommerceInterface(boolean b) {      
        super("Voyageur de commerce");
        this.b =b;
        if(b==true){
        String s= "                             PROJET JAVA 2015\n   "
                + "                                          ~*~\n"
                + " \n                                      Bienvenue!  \n \n  "
                + "Ceci est une application JAVA destinée à résoudre\n     les problèmes des voyageurs de commerce.\n"
                + "  Commencez par sélectionner vos villes en cliquant\n sur la carte afin de les enregistrer"
                + "puis valider\n                         lorsque vous avez terminé. \n"
                + "      Profitez ensuite des algorithmes à disposition !";


        JOptionPane.showMessageDialog(map,s ,"Charlotte Ramé, Mélany Tanchon",2, new ImageIcon("./resources/Img_accueil.png" ));
        }
              
        this.setPreferredSize(new Dimension(1000, 600));
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        Container content = this.getContentPane();
        content.setLayout(new FlowLayout());
        BorderLayout layout = new BorderLayout();
        content.setLayout(layout);
        addJMenuBar();
        addJpanel(content);
        carteVoyageurDeCommerce = map.getCarte();
        addJLabel(content);
        addJTable(content);
        
        map.addVilleSurvoleeListener(new VilleSurvoleeListener() {
            @Override
            public void onVilleSurvolee(EventVilleSurvolee evt) {
                if (evt == null) {
                    labelVille.setText("Nom: ");
                    labelX.setText("X : ");
                    labelY.setText("Y : ");              
                } else {
                    labelVille.setText("Nom: " + evt.getNomVille());
                    labelX.setText("X : " + evt.getX() + "");
                    labelY.setText("Y : " + evt.getY() + "");
                }               
            }
        });
             
        boutonValider.addActionListener(this.boutonValider);
               
              
            
        
        
        
        itemQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
		System.exit(0);}});
        
         itemNouvellefenetre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
		    VoyageurDeCommerceInterface vdci = new VoyageurDeCommerceInterface(false);                         
                }});
         
        itemNouveau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {        
                getCarteVoyageurDeCommerce().getListe_villes().removeAll(getCarteVoyageurDeCommerce().getListe_villes());
                getCarteVoyageurDeCommerce().getListe_arcs().removeAll(getCarteVoyageurDeCommerce().getListe_arcs());
                addJpanel(content);
                
                }});

        pack();

    }

    private void addJMenuBar() {
        JMenuBar menu = new JMenuBar();
        JMenu menuFichier = new JMenu("Fichier");
        itemNouveau = new JMenuItem("Nouveau");
        itemNouvellefenetre = new JMenuItem("Nouvelle fenêtre");
        itemOuvrir = new JMenuItem("Ouvrir");
        itemSauvegarder = new JMenuItem("Sauvegarder");
        itemQuitter = new JMenuItem("Quitter");

        menuFichier.add(itemNouveau);
        menuFichier.add(itemNouvellefenetre);
        menuFichier.add(itemOuvrir);
        menuFichier.add(itemSauvegarder);
        menuFichier.add(itemQuitter);
        menu.add(menuFichier);

        JMenu menuCalculer = new JMenu("Calculer");
        plusProcheVoisin = new JMenuItem("Plus proche voisin");
        plusEloignes = new JMenuItem("Insertion plus éloignés");
        moindreCout = new JMenuItem("Insertion moindre coût");
        kruskal = new JMenuItem("Kruskal");
        prim = new JMenuItem("Prim");
        
        menuCalculer.add(getPlusProcheVoisin());
        menuCalculer.add(getPlusEloignes());
        menuCalculer.add(getMoindreCout());
        menuCalculer.add(getKruskal());
        menuCalculer.add(getPrim());
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
        map.setLayout(new FlowLayout());
        map.setBorder(new BevelBorder(BevelBorder.RAISED, Color.lightGray,
                Color.yellow));
        //JLabel city = new JLabel("Toulouse");

        //map.add(city);
        map.repaint();
        
        content.add(map, BorderLayout.CENTER);
    }

    private void addJLabel(Container content) {
        JPanel panelInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelX = new JLabel("X");
        labelY = new JLabel("Y");
        labelVille = new JLabel("Ville");

        panelInfo.add(labelVille);
        panelInfo.add(labelX);
        panelInfo.add(labelY);

        content.add(panelInfo, BorderLayout.SOUTH);
    }

    private void addJTable(Container content) {
    // Le tableau des distances        
        JPanel panelTable = new JPanel();
        panelTable.setLayout(new FlowLayout());
        this.modelTableau = new ModelTable();
        tableau = new JTable(this.getModelTableau());
        miseEnForme(tableau);        
        JScrollPane js=new JScrollPane(tableau);
        js.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));        
        panelTable.add(js,BorderLayout.EAST);
        panelTable.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
    // Le bouton valider    
        JPanel panelBouton = new JPanel();
        panelBouton.setLayout(new FlowLayout());
        boutonValider = new boutonValider("Valider",this);
        panelBouton.add(boutonValider,BorderLayout.WEST);
        panelBouton.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelTable, panelBouton);
        sp.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
    // Les deux    
        JPanel panelDouble = new JPanel();
        panelDouble.setLayout(new FlowLayout());
        panelDouble.add(sp,BorderLayout.EAST);
        panelDouble.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        content.add(panelDouble,BorderLayout.EAST);
        
    }

    private void miseEnForme(JTable jt){
        // Mettre à jour le rendu des lignes et du hearder (style modifiable dans RenduHeaderTableau et RenduTableau)
        jt.getColumnModel().getColumn(0).setCellRenderer(new RenduTableau());
        jt.getColumnModel().getColumn(1).setCellRenderer(new RenduTableau());
        jt.getColumnModel().getColumn(2).setCellRenderer(new RenduTableau());
        jt.getColumnModel().getColumn(0).setHeaderRenderer(new RenduHeaderTableau());
        jt.getColumnModel().getColumn(1).setHeaderRenderer(new RenduHeaderTableau());
        jt.getColumnModel().getColumn(2).setHeaderRenderer(new RenduHeaderTableau());
    }
    
    
    public Carte getCarteVoyageurDeCommerce() {
        return carteVoyageurDeCommerce;
    }

    public void setCarteVoyageurDeCommerce(Carte carteVoyageurDeCommerce) {
        this.carteVoyageurDeCommerce = carteVoyageurDeCommerce;
    }

    public JMenuItem getItemNouveau() {
        return itemNouveau;
    }

    public void setItemNouveau(JMenuItem itemNouveau) {
        this.itemNouveau = itemNouveau;
    }

    public JMenuItem getItemOuvrir() {
        return itemOuvrir;
    }

    public void setItemOuvrir(JMenuItem itemOuvrir) {
        this.itemOuvrir = itemOuvrir;
    }

    public JMenuItem getItemSauvegarder() {
        return itemSauvegarder;
    }

    public void setItemSauvegarder(JMenuItem itemSauvegarder) {
        this.itemSauvegarder = itemSauvegarder;
    }

    public JMenuItem getItemQuitter() {
        return itemQuitter;
    }

    public void setItemQuitter(JMenuItem itemQuitter) {
        this.itemQuitter = itemQuitter;
    }
    
    public JMenuItem getItemComparaison() {
        return itemComparaison;
    }

    public void setItemComparaison(JMenuItem itemComparaison) {
        this.itemComparaison = itemComparaison;
    }

    public JMenuItem getItemGeneration() {
        return itemGeneration;
    }

    public void setItemGeneration(JMenuItem itemGeneration) {
        this.itemGeneration = itemGeneration;
    }

    public MapPanel getMap() {
        return map;
    }

    public void setMap(MapPanel map) {
        this.map = map;
    }

    public JLabel getLabelX() {
        return labelX;
    }

    public void setLabelX(JLabel labelX) {
        this.labelX = labelX;
    }

    public JLabel getLabelY() {
        return labelY;
    }

    public void setLabelY(JLabel labelY) {
        this.labelY = labelY;
    }

    public JLabel getLabelDist() {
        return labelVille;
    }

    public void setLabelDist(JLabel labelDist) {
        this.labelVille = labelDist;
    }

    public JTable getTableau() {
        return tableau;
    }

    public void setTableau(JTable tableau) {
        this.tableau = tableau;
    }
    

    public static void main(String[] args) {
        VoyageurDeCommerceInterface i = new VoyageurDeCommerceInterface(true);
        
    }

    /**
     * @return the plusProcheVoisin
     */
    public JMenuItem getPlusProcheVoisin() {
        return plusProcheVoisin;
    }

    /**
     * @return the plusEloignes
     */
    public JMenuItem getPlusEloignes() {
        return plusEloignes;
    }

    /**
     * @return the moindreCout
     */
    public JMenuItem getMoindreCout() {
        return moindreCout;
    }

    /**
     * @return the kruskal
     */
    public JMenuItem getKruskal() {
        return kruskal;
    }

    /**
     * @return the prim
     */
    public JMenuItem getPrim() {
        return prim;
    }

    /**
     * @return the modelTableau
     */
    public ModelTable getModelTableau() {
        return modelTableau;
    }
}
