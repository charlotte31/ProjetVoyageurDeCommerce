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
import javax.swing.JButton;

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
  
    private JCheckBox cb6;
    private JCheckBox cb7;
    private JLabel label;
    private BufferedImage background;
    private HistoDistanceTemps randomHisto;

    private int val;
    public ItemComparaison(String nom, VoyageurDeCommerceInterface vdci) {
        super(nom);
        this.vdci = vdci;
        val=2;
    }

    public void actionPerformed(ActionEvent e) {
        JFrame jf = new JFrame("Algorithmes : Comparaisons");
        jf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jf.setLocation(450, 250);
        jf.setPreferredSize(new Dimension(480, 280));

        jf.setResizable(false);
        jf.setVisible(true);
        jf.pack();

        Container c = jf.getContentPane();
        c.setLayout(new FlowLayout());
        c.setBackground(new Color(10, 59, 89));
        addAlgorithmChoice(c);
        //addAnalysisChoice(c);
        addCompteur(c);
        addRunChoice(c);
     
        
        cb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cb1.isSelected() == true) {
                    rgb.getListeAlgo().add(Algorithme.TWO_OPT);
                    cgb.getListeAlgo().add(Algorithme.TWO_OPT);
                }
            }
        });
        cb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cb2.isSelected() == true) {
                    rgb.getListeAlgo().add(Algorithme.PRIM);
                    cgb.getListeAlgo().add(Algorithme.PRIM);
                }
            }
        });
        cb3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cb3.isSelected() == true) {
                    rgb.getListeAlgo().add(Algorithme.PLUS_ELOIGNES);
                    cgb.getListeAlgo().add(Algorithme.PLUS_ELOIGNES);
                }
            }
        });
        cb4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cb4.isSelected() == true) {
                    rgb.getListeAlgo().add(Algorithme.PLUS_PROCHE_VOISIN);
                    cgb.getListeAlgo().add(Algorithme.PLUS_PROCHE_VOISIN);
                }
            }
        });
        cb5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cb5.isSelected() == true) {
                    rgb.getListeAlgo().add(Algorithme.MOINDRE_COUT);
                    cgb.getListeAlgo().add(Algorithme.MOINDRE_COUT);
                }
            }
        });
        
       rgb.addActionListener(rgb);
       cgb.addActionListener(cgb);
       
    }

    public void addAlgorithmChoice(Container c) {
       
        JPanel jp = new JPanel();
        JLabel jl = new JLabel("Sélectionnez les algorithmes à comparer");
        jl.setForeground(new Color(9, 2, 46));
        jl.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        jl.setBackground(new Color(10, 59, 89));
        jp.add(jl, BorderLayout.CENTER);
        jp.setBackground(new Color(10, 59, 89));
        c.add(jp);

        JPanel jp1 = new JPanel();
        jp1.setLayout(new GridLayout(2, 2));
        jp1.setBackground(new Color(10, 59, 89));
        cb1 = new JCheckBox(Algorithme.TWO_OPT.toString());
        cb2 = new JCheckBox(Algorithme.PRIM.toString());
        cb3 = new JCheckBox(Algorithme.PLUS_ELOIGNES.toString());
        cb4 = new JCheckBox(Algorithme.PLUS_PROCHE_VOISIN.toString());
        cb5 = new JCheckBox(Algorithme.MOINDRE_COUT.toString());
        cb1.setForeground(Color.white);
        cb2.setForeground(Color.white);
        cb3.setForeground(Color.white);
        cb4.setForeground(Color.white);
        cb5.setForeground(Color.white);
        cb1.setBackground(new Color(10, 59, 89));
        cb2.setBackground(new Color(10, 59, 89));
        cb3.setBackground(new Color(10, 59, 89));
        cb4.setBackground(new Color(10, 59, 89));
        cb5.setBackground(new Color(10, 59, 89));
        jp1.add(cb1);
        jp1.add(cb2);
        jp1.add(cb3);
        jp1.add(cb4);
        c.add(jp1);
        JPanel jp2 = new JPanel();
        jp2.setBackground(new Color(10, 59, 89));
        jp2.add(cb5, BorderLayout.CENTER);
        c.add(jp2);
    }
    public void addCompteur (Container c){       
        //Composants
        JPanel panel = new JPanel(new GridLayout(2,1));
        JPanel pTrans = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel jl = new JLabel("Nombre de villes (ie. itérations)");
        jl.setForeground(new Color(9, 2, 46));
        jl.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        JButton moins = new JButton("<");       
        JButton plus = new JButton(">");
        label = new JLabel("2");
        //Evenements
        moins.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                int n = Integer.parseInt(label.getText());
                if (n>2 && n-1>=2){
                    n=n-1;
                    label.setText(String.valueOf(n));
                    val = n;
                   
                }  
            }
        });
        plus.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                label.setText(String.valueOf(Integer.parseInt(label.getText())+1));
                val=Integer.parseInt(label.getText());
                rgb.setNbIterations(val);
                cgb.setNbIterations(val);
          
            }
        });       
        //Mise en forme
        moins.setForeground(new Color(10, 59, 89));
        label.setForeground(Color.white);
        plus.setForeground(new Color(10, 59, 89));
        panel.setBackground(new Color(10, 59, 89));
        pTrans.setBackground(new Color(10, 59, 89));
        //Legos
        panel.add(jl, BorderLayout.CENTER);
        pTrans.add(moins);
        pTrans.add(label);
        pTrans.add(plus);
        panel.add(pTrans);
        c.add(panel);
       
    }
       
    public void addRunChoice(Container c) {
        JPanel panel = new JPanel(new GridLayout(2,1));
        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JLabel jl = new JLabel("Sur quel graphe la comparaison doit-être faite ?");
        jl.setForeground(new Color(9, 2, 46));
        jl.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        jl.setBackground(new Color(10, 59, 89));
        
        panel.setBackground(new Color(10, 59, 89));
        panel2.setBackground(new Color(10, 59, 89));
      
        cgb = new CurrentGraphButton("Graphe courant",vdci, val);
        rgb = new RandonGraphButton("Graphe aléatoire", vdci,val);
        
        
        panel.add(jl);
        panel2.add(cgb);
        panel2.add(rgb);
        panel.add(panel2);
        c.add(panel);
        
    }
    
    
  
    //public void addAnalysisChoice(Container c) {
    //    JLabel jl = new JLabel("Que voulez-vous analyser?",null,CENTER);
    //    jl.setForeground(new Color(9, 2, 46));
    //    jl.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
    //    jl.setBackground(new Color(10, 59, 89));
     //   JPanel jp = new JPanel(new GridLayout(3,1));
    //    jp.setBackground(new Color(10, 59, 89));
    //    cb6 = new JCheckBox("Comp. relatives. distance + time");
    //    cb7 = new JCheckBox("Comp. (distance + time) ~ nb(Villes)");
    //    cb6.setBackground(new Color(10, 59, 89));
    //    cb7.setBackground(new Color(10, 59, 89));
    //    cb6.setForeground(new Color(9, 2, 46));
    //    cb7.setForeground(new Color(9, 2, 46));
    //    jp.add(jl);
    //    jp.add(cb6);
    //    jp.add(cb7);
        
        //c.add(jl, BorderLayout.NORTH);
    //    c.add(jp);
   // }

}
