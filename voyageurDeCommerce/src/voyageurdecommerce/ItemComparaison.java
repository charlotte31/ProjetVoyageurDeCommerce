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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.control.CheckBox;
import javax.imageio.ImageIO;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Melany Classe à part entière pour ne pas rendre illisible le
 * constructeur VoyageurDeCommerceInterface Permet de définir les
 * fonctionnalités de l'item comparaison, càd ouverture d'une nouvelle fenetre
 * pour le choix des algorithmes à comparer, puis création de l'histogramme
 */
public class ItemComparaison extends JMenuItem implements ActionListener {

    private VoyageurDeCommerceInterface vdci;
    private CurrentGraphButton cgb;
    private RandonGraphButton rgb;
    private JCheckBox cb1;
    private JCheckBox cb2;
    private JCheckBox cb3;
    private JCheckBox cb4;
    private JCheckBox cb5;
    private BufferedImage background;
    private RandomHisto randomHisto;

    public ItemComparaison(String nom, VoyageurDeCommerceInterface vdci) {
        super(nom);
        this.vdci = vdci;
    }

    public void actionPerformed(ActionEvent e) {
        JFrame jf = new JFrame("Algorithmes : Comparaisons");
        jf.setLocation(450, 250);
        jf.setPreferredSize(new Dimension(480, 190));

        jf.setResizable(false);
        jf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jf.setVisible(true);
        jf.pack();

        Container c = jf.getContentPane();
        c.setLayout(new FlowLayout());
        c.setBackground(new Color(9, 2, 46));
        addAlgorithmChoice(c);
        addRunChoice(c);
        cb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cb1.isSelected() == true) {
                    rgb.getListeAlgo().add(Algorithme.Kruskal);
                    cgb.getListeAlgo().add(Algorithme.Kruskal);
                }
            }
        });
        cb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cb2.isSelected() == true) {
                    rgb.getListeAlgo().add(Algorithme.Prim);
                    cgb.getListeAlgo().add(Algorithme.Prim);
                }
            }
        });
        cb3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cb3.isSelected() == true) {
                    rgb.getListeAlgo().add(Algorithme.plusEloignes);
                    cgb.getListeAlgo().add(Algorithme.plusEloignes);
                }
            }
        });
        cb4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cb4.isSelected() == true) {
                    rgb.getListeAlgo().add(Algorithme.plusProcheVoisin);
                    cgb.getListeAlgo().add(Algorithme.plusProcheVoisin);
                }
            }
        });
        cb5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cb5.isSelected() == true) {
                    rgb.getListeAlgo().add(Algorithme.moindreCout);
                    cgb.getListeAlgo().add(Algorithme.moindreCout);
                }
            }
        });
        rgb.addActionListener(rgb);
        cgb.addActionListener(cgb);
       
    }

    public void addAlgorithmChoice(Container c) {
        JPanel jp = new JPanel();
        JLabel jl = new JLabel("Sélectionnez les algorithmes à comparer");
        jl.setForeground(Color.white);
        jl.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        jl.setBackground(new Color(9, 2, 46));
        jp.add(jl, BorderLayout.CENTER);
        jp.setBackground(new Color(9, 2, 46));
        c.add(jp);

        JPanel jp1 = new JPanel();
        jp1.setLayout(new GridLayout(2, 2));
        jp1.setBackground(new Color(9, 2, 46));
        cb1 = new JCheckBox(Algorithme.Kruskal.toString());
        cb2 = new JCheckBox(Algorithme.Prim.toString());
        cb3 = new JCheckBox(Algorithme.plusEloignes.toString());
        cb4 = new JCheckBox(Algorithme.plusProcheVoisin.toString());
        cb5 = new JCheckBox(Algorithme.moindreCout.toString());
        cb1.setForeground(Color.white);
        cb2.setForeground(Color.white);
        cb3.setForeground(Color.white);
        cb4.setForeground(Color.white);
        cb5.setForeground(Color.white);
        cb1.setBackground(new Color(9, 2, 46));
        cb2.setBackground(new Color(9, 2, 46));
        cb3.setBackground(new Color(9, 2, 46));
        cb4.setBackground(new Color(9, 2, 46));
        cb5.setBackground(new Color(9, 2, 46));
        jp1.add(cb1);
        jp1.add(cb2);
        jp1.add(cb3);
        jp1.add(cb4);
        c.add(jp1);
        JPanel jp2 = new JPanel();
        jp2.setBackground(new Color(9, 2, 46));
        jp2.add(cb5, BorderLayout.CENTER);
        c.add(jp2);
    }

    public void addRunChoice(Container c) {
        JLabel jl = new JLabel("Sur quel graphe la comparaison doit-être faite ?");
        jl.setForeground(Color.white);
        jl.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        jl.setBackground(new Color(9, 2, 46));
        JPanel jp = new JPanel(new GridLayout(1, 2));
        jp.setBackground(new Color(9, 2, 46));
        cgb = new CurrentGraphButton("Graphe courant",vdci);
        rgb = new RandonGraphButton("Graphe aléatoire", vdci);
        jp.add(cgb);
        jp.add(rgb);
        c.add(jl);
        c.add(jp);
    }

}
