/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author Melany
 */
public class HistoGeneration extends ApplicationFrame {
   private VoyageurDeCommerceInterface vdci;
   public HistoGeneration( String applicationTitle , String chartTitle,VoyageurDeCommerceInterface vdci)
   {
      super( applicationTitle );
      this.vdci=vdci;
     
       JFreeChart barChart = ChartFactory.createBarChart3D(
         chartTitle,           
         "Paramètres",            
         "Score",            
         createDataset(),          
               PlotOrientation.VERTICAL,           
         true, true, false);
       
       ChartPanel chartPanel = new ChartPanel( barChart );      
      chartPanel.setPreferredSize(new java.awt.Dimension( 600 , 400 ) );        
      setBackground(new Color(10,59,89));
      setContentPane( chartPanel ); 
       pack( );        
      RefineryUtilities.centerFrameOnScreen( this );        
      setVisible( true ); 
   }
   private CategoryDataset createDataset( )
   {
      final String ppv = Algorithme.plusProcheVoisin.toString();        
      final String pe = Algorithme.plusEloignes.toString();        
      final String mc = Algorithme.moindreCout.toString();
      final String prim = Algorithme.Prim.toString();
      final String kruskal = Algorithme.Kruskal.toString();
      
      final String temps = "Speed";        
      final String distance = "Distance";                
      final DefaultCategoryDataset dataset = 
      new DefaultCategoryDataset();  

      
     Iterator<Algorithme> keySetIterator = vdci.getMap().getHashChemin().keySet().iterator();
     while (keySetIterator.hasNext()) {
     Algorithme key = keySetIterator.next();
        float d = (Float)vdci.getMap().getHashChemin().get(key).get(1);
        long t = (Long)vdci.getMap().getHashChemin().get(key).get(2);
        dataset.addValue(d, key.toString(), distance);
        dataset.addValue(t, key.toString(), temps);
                            
                        }      
      //dataset.addValue( 100 , fiat , speed );        
      //dataset.addValue( 300 , fiat , userrating );        
      //dataset.addValue( 50 , fiat , millage ); 
      //dataset.addValue( 50 , fiat , safety );           

      //dataset.addValue( 5.0 , audi , speed );        
      //dataset.addValue( 6.0 , audi , userrating );       
     // dataset.addValue( 10.0 , audi , millage );        
      //dataset.addValue( 4.0 , audi , safety );

      //dataset.addValue( 4.0 , ford , speed );        
      //dataset.addValue( 2.0 , ford , userrating );        
      //dataset.addValue( 3.0 , ford , millage );        
      //dataset.addValue( 6.0 , ford , safety );               

      return dataset; 
   }
   public static void main( String[ ] args )
   {
               Ville v1 = new Ville("Toulouse", 0, 100);
        Ville v2 = new Ville("Paris", 100, 500);
        Ville v3 = new Ville("Nord", 700, 300);
        Ville v4 = new Ville("Marseille", 200, 20);
        Ville v5 = new Ville("Bordeaux", 500, 200);
        
        VoyageurDeCommerceInterface vdci = new VoyageurDeCommerceInterface(false);
                
        vdci.setCarteVoyageurDeCommerce(new Carte());
        vdci.getCarteVoyageurDeCommerce().ajouterNoeud(v1);
        vdci.getCarteVoyageurDeCommerce().ajouterNoeud(v2);
        vdci.getCarteVoyageurDeCommerce().ajouterNoeud(v3);
        vdci.getCarteVoyageurDeCommerce().ajouterNoeud(v4);
        vdci.getCarteVoyageurDeCommerce().ajouterNoeud(v5);
       vdci.getCarteVoyageurDeCommerce().creerClique();
vdci.getMap().setCarte(vdci.getCarteVoyageurDeCommerce());
        
        ArrayList<Object> list1 = vdci.getCarteVoyageurDeCommerce().insertionPlusEloignes(v5);
        ArrayList<Object> list2 = vdci.getCarteVoyageurDeCommerce().moindreCout(v5);
        vdci.getMap().setHashChemin(Algorithme.plusEloignes, (ArrayList<Object>)list1);
        vdci.getMap().setHashChemin(Algorithme.moindreCout, (ArrayList<Object>)list2);

      HistoGeneration chart = new HistoGeneration("Car Usage Statistics", "Which car do you like?",vdci);

   }

 
}