/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author Melany
 */
public class HistoDistanceTemps extends JFrame {

    private VoyageurDeCommerceInterface vdci;
    private ChartPanel chartPanel;
    public HistoDistanceTemps(String applicationTitle, String chartTitle, VoyageurDeCommerceInterface vdci) {  
        super(applicationTitle);
        super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        this.vdci = vdci;
        JFreeChart barChart = ChartFactory.createBarChart3D(
                chartTitle,
                "Paramètres",
                "Score",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);
        chartPanel = new ChartPanel(barChart);
        ChartPanel cPanel = new ChartPanel(barChart);
        cPanel.setPreferredSize(new java.awt.Dimension(580, 350));
        super.setBackground(new Color(10, 59, 89));
        super.setContentPane(cPanel);
        super.pack();
        RefineryUtilities.positionFrameOnScreen(this,0,0.5);
        super.setVisible(true);
        }
    
    public void ajouterOnglet(ChartPanel cp){
        JPanel p = new JPanel(new GridLayout(1,1));
        cp.setPreferredSize(new java.awt.Dimension(550, 380));
        p.add(cp);
        p.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.yellow));
        p.setBackground(new Color(10, 59, 89));
        vdci.getJt().add("Analyse 1", p);      
    }
      
    public ChartPanel getChartPanel(){
        return chartPanel;
    }

    private CategoryDataset createDataset() {
        final String ppv = Algorithme.PLUS_PROCHE_VOISIN.toString();
        final String pe = Algorithme.PLUS_ELOIGNES.toString();
        final String mc = Algorithme.MOINDRE_COUT.toString();
        final String prim = Algorithme.PRIM.toString();
        final String twoOpt = Algorithme.TWO_OPT.toString();
        final String temps = "Speed";
        final String distance = "Distance";
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Iterator<Algorithme> keySetIterator = vdci.getMap().getHashChemin().keySet().iterator();
        while (keySetIterator.hasNext()) {
            Algorithme key = keySetIterator.next();
            float d = (Float) vdci.getMap().getHashChemin().get(key).get(1);
            long t = (Long) vdci.getMap().getHashChemin().get(key).get(2);
            dataset.addValue(d, key.toString(), distance);
            dataset.addValue(t, key.toString(), temps);
        }
               
        return dataset;
    }

    //public static void main(String[] args) {
    //    Ville v1 = new Ville("Toulouse", 0, 100);
      //  Ville v2 = new Ville("Paris", 100, 500);
      ////  Ville v3 = new Ville("Lille", 700, 300);
      //  Ville v4 = new Ville("Marseille", 200, 20);
      //  Ville v5 = new Ville("Bordeaux", 500, 200);

      //  VoyageurDeCommerceInterface vdci = new VoyageurDeCommerceInterface(false);

      //  vdci.setCarteVoyageurDeCommerce(new Carte());
      //  vdci.getCarteVoyageurDeCommerce().ajouterNoeud(v1);
       // vdci.getCarteVoyageurDeCommerce().ajouterNoeud(v2);
       // vdci.getCarteVoyageurDeCommerce().ajouterNoeud(v3);
       // vdci.getCarteVoyageurDeCommerce().ajouterNoeud(v4);
       // vdci.getCarteVoyageurDeCommerce().ajouterNoeud(v5);
       // vdci.getCarteVoyageurDeCommerce().creerClique();
       //  vdci.getMap().setCarte(vdci.getCarteVoyageurDeCommerce());

       /// ArrayList<Object> list1 = vdci.getCarteVoyageurDeCommerce().insertionPlusEloignes(v5);
        //ArrayList<Object> list2 = vdci.getCarteVoyageurDeCommerce().moindreCout(v5);
        //vdci.getMap().setHashChemin(Algorithme.PLUS_ELOIGNES, (ArrayList<Object>) list1);
       // vdci.getMap().setHashChemin(Algorithme.MOINDRE_COUT, (ArrayList<Object>) list2);

        //HistoGeneration chart = new HistoGeneration("", "", vdci);

    //}

}
