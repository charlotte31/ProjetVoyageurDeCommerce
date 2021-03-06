/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import voyageurdecommerce.events.EventVilleSurvolee;
import voyageurdecommerce.events.VilleSurvoleeListener;
import voyageurdecommerce.graphisme.RenduHeaderTableau;
import voyageurdecommerce.graphisme.RenduTableau;

/**
 *
 * @author Charlotte
 */
public class VoyageurDeCommerceInterface extends JFrame implements WindowListener {

    // Déclaration
    private Carte carteVoyageurDeCommerce;
    private JMenuItem itemNouveau;
    private ItemOuvrir itemOuvrir;
    private ItemSauvegarder itemEnregistrerSous;
    private ItemSauvegarder itemEnregistrer;
    private JMenuItem itemQuitter;
    private JTabbedPane jt;
    private ItemAlgorithme plusProcheVoisin;
    private ItemAlgorithme plusEloignes;
    private ItemAlgorithme moindreCout;
    private ItemAlgorithme twoOpt;
    private ItemAlgorithme prim;

    private ItemComparaison itemComparaison;
    private ItemGeneration itemGeneration;
    private ItemNouvelleFenetre itemNouvellefenetre;
    private MapPanel map;

    private JLabel labelX;
    private JLabel labelY;
    private JLabel labelVille;

    private BoutonValider boutonValider;

    private boolean b;
    private JTable tableau;
    private ModelTable modelTableau;
    
    // onglet 2 
    private JPanel principal;
    private JLabel algo1;
    private JPanel pAlgo1;
    private JLabel algo2;
    private JPanel pAlgo2;       
    private JLabel algo3 ;
    private JPanel pAlgo3;
    private JLabel algo4 ;
    private JPanel pAlgo4 ;
    private JLabel algo5 ;
    private JPanel pAlgo5 ;
    private JButton raffraichir;
    
    // Fin Déclaration

    //Constructeur
    public VoyageurDeCommerceInterface(boolean b) {
        // Contenu principal
        super("Voyageur de commerce");
        setLocation(150, 50);

        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(VoyageurDeCommerceInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.b = b;
        if (b == true) {
            String s = "                             PROJET JAVA 2015\n   "
                    + "                                          ~*~\n"
                    + " \n                                      Bienvenue!  \n \n  "
                    + "Ceci est une application JAVA destinée à résoudre\n     les problèmes des voyageurs de commerce.\n"
                    + "  Commencez par sélectionner vos villes en cliquant\n sur la carte afin de les enregistrer "
                    + "puis valider\n                         lorsque vous avez terminé. \n"
                    + "      Profitez ensuite des algorithmes à disposition \n"
                    + "                   ainsi que des outils d'analyses!";
            JOptionPane.showMessageDialog(map, s, "Charlotte Ramé, Mélany Tanchon", 2, new ImageIcon("./ressources/Img_accueil.png"));

        }

        this.setPreferredSize(new Dimension(1060, 600));
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
        jt = new JTabbedPane(JTabbedPane.SCROLL_TAB_LAYOUT );
        jt.add("Map",map);
        content.add(jt);
        addOnglet("Résultat", jt);
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

        // 1er onglet
        itemNouvellefenetre.addActionListener(itemNouvellefenetre);
        itemEnregistrerSous.addActionListener(this.itemEnregistrerSous);
        itemOuvrir.addActionListener(this.itemOuvrir);
        itemEnregistrer.addActionListener(this.itemEnregistrerSous);
        itemNouveau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nrowIni = modelTableau.getRowCount();
                for (int i = 0; i < nrowIni; i++) {
                    modelTableau.removeData(modelTableau.getRowCount() - 1);
                }
                getCarteVoyageurDeCommerce().getListe_villes().removeAll(getCarteVoyageurDeCommerce().getListe_villes());
                getCarteVoyageurDeCommerce().getListe_arcs().removeAll(getCarteVoyageurDeCommerce().getListe_arcs());
                map.setBool(true);
                map.repaint();
            }
        });
        itemQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(EXIT_ON_CLOSE);
            }

        });

        // 2eme onglet
        boutonValider.addActionListener(this.boutonValider);
        plusProcheVoisin.addActionListener(plusProcheVoisin);
        plusEloignes.addActionListener(plusEloignes);
        moindreCout.addActionListener(moindreCout);
        twoOpt.addActionListener(twoOpt);
        prim.addActionListener(prim);

        // 3eme onglet
        itemComparaison.addActionListener(itemComparaison);
        itemGeneration.addActionListener(itemGeneration);

        addWindowListener(this);
        pack();
    }
    // Fin Constructeur

    // Méthodes
   
    private void addOnglet(String name, JTabbedPane jt){
        principal = new JPanel(new GridLayout(11,1));
        algo1 = new JLabel("Plus Proches Voisins",0);       
        pAlgo1 = new JPanel(new GridLayout(3,1));
        getpAlgo1().setBackground(new Color(10, 59, 89));
        algo1.setFont(new Font("calibri", Font.CENTER_BASELINE, 20));
        getpAlgo1().setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.yellow));
        algo1.setForeground(Color.WHITE);
        
        algo2 = new JLabel("Insertion du plus éloigné",0);
        pAlgo2 = new JPanel(new GridLayout(3,1));       
        getpAlgo2().setBackground(new Color(10, 59, 89));
        algo2.setFont(new Font("calibri", Font.CENTER_BASELINE, 20));
        getpAlgo2().setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.yellow));
        algo2.setForeground(Color.WHITE);
        
        algo3 = new JLabel("Insertion au moindre coût",0);
        pAlgo3 = new JPanel(new GridLayout(3,1));
        getpAlgo3().setBackground(new Color(10, 59, 89));
        algo3.setFont(new Font("calibri", Font.CENTER_BASELINE, 20));
        getpAlgo3().setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.yellow));
        algo3.setForeground(Color.WHITE);
        
        algo4 = new JLabel("Prim",0);
        pAlgo4 = new JPanel(new GridLayout(3,1));
        getpAlgo4().setBackground(new Color(10, 59, 89));
        algo4.setFont(new Font("calibri", Font.CENTER_BASELINE, 20));
        getpAlgo4().setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.yellow));
        algo4.setForeground(Color.WHITE);
        
        algo5 = new JLabel("2-Opt",0);
        pAlgo5 = new JPanel(new GridLayout(3,1));
        getpAlgo5().setBackground(new Color(10, 59, 89));
        getpAlgo5().setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY));
        algo5.setFont(new Font("calibri", Font.CENTER_BASELINE, 20));
        
        algo5.setForeground(Color.WHITE);
        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        raffraichir = new JButton("Reset");
        panelButton.add(raffraichir);

        
        panelButton.setBackground(new Color(10, 59, 89));
        principal.add(algo1);
        principal.add(getpAlgo1());
        principal.add(algo2);
        principal.add(getpAlgo2());
        principal.add(algo3);
        principal.add(getpAlgo3());
        principal.add(algo4);
        principal.add(getpAlgo4());
        principal.add(algo5);
        principal.add(getpAlgo5());
        principal.add(panelButton);
        principal.setBackground(new Color(10, 59, 89));
        
       principal.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.yellow));
       
        jt.add(name, principal);
    }
    private void addJMenuBar() {
        JMenuBar menu = new JMenuBar();
        JMenu menuFichier = new JMenu("Fichier");

        itemNouveau = new JMenuItem("Nouveau");
        setItemNouvellefenetre(new ItemNouvelleFenetre("Nouvelle fenêtre"));
        itemOuvrir = new ItemOuvrir("Ouvrir", this);
        itemEnregistrer = new ItemSauvegarder("Enregistrer", this);
        itemEnregistrerSous = new ItemSauvegarder("Enregistrer Sous", this);

        itemQuitter = new JMenuItem("Quitter");

        menuFichier.add(itemNouveau);
        menuFichier.add(getItemNouvellefenetre());
        menuFichier.add(itemOuvrir);
        menuFichier.add(itemEnregistrer);
        menuFichier.add(itemEnregistrerSous);
        menuFichier.add(itemQuitter);
        menu.add(menuFichier);

        JMenu menuCalculer = new JMenu("Voy. de Comm.");
        menuCalculer.setToolTipText("Choisissez une heuristique");
        plusProcheVoisin = new ItemAlgorithme("plusProcheVoisin", this);
        plusEloignes = new ItemAlgorithme("plusEloignes", this);
        moindreCout = new ItemAlgorithme("moindreCout", this);
        twoOpt = new ItemAlgorithme("2-Opt", this);
        prim = new ItemAlgorithme("Prim", this);
        menuCalculer.add(plusProcheVoisin);
        menuCalculer.add(plusEloignes);
        menuCalculer.add(moindreCout);
        menuCalculer.add(twoOpt);
        menuCalculer.add(prim);
        menu.add(menuCalculer);
        
        JMenu menuOutil = new JMenu("Outil");
        itemComparaison = new ItemComparaison("Comparaison", this);
        itemComparaison.setToolTipText("Analyses comparatives de diverses heuristiques");
        itemGeneration = new ItemGeneration("Génération", this);
        itemGeneration.setToolTipText("Génère un graphe aléatoire d'ordre souhaité");
        menuOutil.add(itemComparaison);
        menuOutil.add(itemGeneration);
        menu.add(menuOutil);
        this.setJMenuBar(menu);

    }

    private void addJpanel(Container content) {
        map = new MapPanel();
        map.setSize(600, 400);
//        ImageIcon icon = new ImageIcon("./ressources/France.png");
//        JLabel img = new JLabel(icon);
//        map.add(img);
        map.setLayout(new FlowLayout());
        map.setBorder(new BevelBorder(BevelBorder.RAISED, Color.white,
                Color.yellow));
        //JLabel city = new JLabel("Toulouse");

        //map.add(city);
        map.repaint();

        content.add(map, BorderLayout.CENTER);
    }

    private void addJLabel(Container content) {
        JPanel panelInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInfo.setBackground(new Color(10, 59, 89));
        labelX = new JLabel("X");
        labelY = new JLabel("Y");
        labelVille = new JLabel("Ville");
        labelX.setForeground(Color.white);
        labelY.setForeground(Color.white);
        labelVille.setForeground(Color.white);

        panelInfo.add(labelVille);
        panelInfo.add(labelX);
        panelInfo.add(labelY);

        content.add(panelInfo, BorderLayout.SOUTH);

    }

    private void addJTable(Container content) {
        // Le tableau des distances        
        JPanel panelTable = new JPanel();
        panelTable.setLayout(new FlowLayout());
        panelTable.setBackground(new Color(10, 59, 89));
        this.modelTableau = new ModelTable();

        tableau = new JTable(this.getModelTableau());
        miseEnForme(tableau);
        JScrollPane js = new JScrollPane(tableau);
        js.getViewport().setBackground(new Color(10, 59, 89));
        js.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.white));
        js.setBackground(new Color(10, 59, 89));

        panelTable.add(js, BorderLayout.EAST);
        panelTable.setBorder(BorderFactory.createMatteBorder(2, 2, 0, 2, Color.yellow));
        // Le bouton valider    
        JPanel panelBouton = new JPanel();
        panelBouton.setLayout(new FlowLayout());
        boutonValider = new BoutonValider("Valider", this);

        panelBouton.add(boutonValider, BorderLayout.SOUTH);
        panelBouton.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, Color.yellow));
        panelBouton.setBackground(new Color(10, 59, 89));

        JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelTable, panelBouton);
        sp.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 2));
        sp.setDividerSize(0);
        sp.setDividerLocation(450);

        sp.setBackground(Color.yellow);

        // Les deux    
        JPanel panelDouble = new JPanel();
        panelDouble.setLayout(new FlowLayout());
        panelDouble.setBackground(new Color(10, 59, 89));
        sp.setPreferredSize(new Dimension(470, 515));
        panelDouble.add(sp);
        panelDouble.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        content.add(panelDouble, BorderLayout.EAST);

    }

    private void miseEnForme(JTable jt) {
        // Mettre à jour le rendu des lignes et du hearder (style modifiable dans RenduHeaderTableau et RenduTableau)
        for (int i = 0; i < jt.getColumnCount(); i++) {
            jt.getColumnModel().getColumn(i).setCellRenderer(new RenduTableau());
            jt.getColumnModel().getColumn(1).setCellRenderer(new RenduTableau());
            jt.getColumnModel().getColumn(2).setCellRenderer(new RenduTableau());
            jt.getColumnModel().getColumn(i).setHeaderRenderer(new RenduHeaderTableau());
            jt.getColumnModel().getColumn(1).setHeaderRenderer(new RenduHeaderTableau());
            jt.getColumnModel().getColumn(2).setHeaderRenderer(new RenduHeaderTableau());
        }
    }

    //Getters and Setters
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

    public void setItemOuvrir(ItemOuvrir itemOuvrir) {
        this.itemOuvrir = itemOuvrir;
    }

    public ItemSauvegarder getItemEnregistrerSous() {
        return itemEnregistrerSous;
    }

    public void setItemEnregistrerSous(ItemSauvegarder itemSauvegarder) {
        this.itemEnregistrerSous = itemSauvegarder;
    }

    public JMenuItem getItemQuitter() {
        return itemQuitter;
    }

    public void setItemQuitter(JMenuItem itemQuitter) {
        this.itemQuitter = itemQuitter;
    }

    public ItemComparaison getItemComparaison() {
        return itemComparaison;
    }

    public void setItemComparaison(ItemComparaison itemComparaison) {
        this.itemComparaison = itemComparaison;
    }

    public ItemGeneration getItemGeneration() {
        return itemGeneration;
    }

    public void setItemGeneration(ItemGeneration itemGeneration) {
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

    public ItemAlgorithme getPlusProcheVoisin() {
        return plusProcheVoisin;
    }

    public ItemAlgorithme getPlusEloignes() {
        return plusEloignes;
    }

    public ItemAlgorithme getMoindreCout() {
        return moindreCout;
    }

    public ItemAlgorithme getKruskal() {
        return twoOpt;
    }

    public ItemAlgorithme getPrim() {
        return prim;
    }

    public ModelTable getModelTableau() {
        return modelTableau;
    }

    public ItemNouvelleFenetre getItemNouvellefenetre() {
        return itemNouvellefenetre;
    }

    public void setItemNouvellefenetre(ItemNouvelleFenetre itemNouvellefenetre) {
        this.itemNouvellefenetre = itemNouvellefenetre;
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
        JOptionPane.showMessageDialog(this, "Merci, à bientôt!","Fermer",0);
    }

    public void windowClosed(WindowEvent e) {
        
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }
    public JButton getRaffraichir(){
        return raffraichir;
    }
    public JPanel getpAlgo1() {
        return pAlgo1;
    }

    public JPanel getpAlgo2() {
        return pAlgo2;
    }

    public JPanel getpAlgo3() {
        return pAlgo3;
    }


    public JPanel getpAlgo4() {
        return pAlgo4;
    }

    public JPanel getpAlgo5() {
        return pAlgo5;
    }
    public JTabbedPane getJt(){
        return jt;
    }
    public static void main(String[] args) {
        VoyageurDeCommerceInterface i = new VoyageurDeCommerceInterface(true);
    }
}
