/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
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
public class HistoDistanceFonctionVilles extends JFrame{
    private int nbIterations;
    private ArrayList<Algorithme> listAlgo;
    private VoyageurDeCommerceInterface vdci;
    private Carte carte;
    private ChartPanel chartPanel;
    public HistoDistanceFonctionVilles(String applicationTitle, String chartTitle, VoyageurDeCommerceInterface vdci, int nbIterations, ArrayList<Algorithme> listAlgo) {
        super(applicationTitle);
        carte=new Carte();
        super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.nbIterations=nbIterations;
        this.listAlgo=listAlgo;
        this.vdci=vdci;
        
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Paramètres",
                "Score",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);
        chartPanel = new ChartPanel(lineChart);
        ChartPanel cPanel = new ChartPanel(lineChart);
        cPanel.setPreferredSize(new java.awt.Dimension(580, 350));
        
       
        setBackground(new Color(10, 59, 89));
        setContentPane(cPanel);
        pack();
        RefineryUtilities.positionFrameOnScreen(this,0.5,0.5);
        setVisible(true);
    }
    public void ajouterOnglet(ChartPanel cp){
        JPanel p = new JPanel(new GridLayout(1,1));
        cp.setPreferredSize(new java.awt.Dimension(550, 380));
        p.add(cp);
        p.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.yellow));
        p.setBackground(new Color(10, 59, 89));
        vdci.getJt().add("Analyse 2", p);      
    }
      
    public ChartPanel getChartPanel(){
        return chartPanel;
    }

    private CategoryDataset createDataset() {
        ArrayList<Object> res = null;
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Double posX = Math.random() * 580 + 1;
        int pos_X = posX.intValue();
        Double posY = Math.random() * 533 + 1;
        int pos_Y = posY.intValue();
        carte.ajouterNoeud(new Ville(String.valueOf(0), pos_X, pos_Y));
        

        for (int nb=0;nb<nbIterations+1;nb++){
            posX = Math.random() * 580 + 1;
            pos_X = posX.intValue();
            posY = Math.random() * 533 + 1;
            pos_Y = posY.intValue();
            carte.ajouterNoeud(new Ville(String.valueOf(nb), pos_X, pos_Y));
            carte.creerClique();
            for (int i=0; i<listAlgo.size();i++){
                String algo = listAlgo.get(i).toString();
                String nbVilles = String.valueOf(nb);
                if (algo.equals(Algorithme.MOINDRE_COUT.toString())){
                     res = carte.moindreCout(carte.getListe_villes().get(0));                    
                }
                if (algo.equals(Algorithme.PLUS_ELOIGNES.toString())){
                    res = carte.insertionPlusEloignes(carte.getListe_villes().get(0));                    
                }
                if (algo.equals(Algorithme.PLUS_PROCHE_VOISIN.toString())){
                    res = carte.plusProcheVoisins(carte.getListe_villes().get(0));                    
                }
                if (algo.equals(Algorithme.PRIM.toString())){
                    res = carte.prim(carte.getListe_villes().get(0));                    
                }
                if (algo.equals(Algorithme.TWO_OPT.toString())){
                    res = carte.twoOpt(carte.getListe_villes().get(0));                    
                }
                dataset.addValue((float)res.get(1), algo, nbVilles);   
            }  
        }       
        return dataset;
    }
    
    
   
}
