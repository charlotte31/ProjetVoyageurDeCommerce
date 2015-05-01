
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.SwingConstants.CENTER;
import static javax.swing.SwingConstants.TOP;
import voyageurdecommerce.events.EventVilleSurvolee;
import voyageurdecommerce.events.VilleSurvoleeListener;

/**
 *
 * @author Charlotte
 */
public class MapPanel extends JPanel {
    
    private BufferedImage background;
    private Carte carte;
    //private List<Ville> liste_villes;
    private List<VilleSurvoleeListener> ville_survolee_listeners;
    private ArrayList<Ville> chemin;
    public MapPanel() {
        chemin = new ArrayList<Ville>();
        try {
            background = ImageIO.read(new File("./resources/france.gif"));
        } catch (IOException ex) {
            Logger.getLogger(MapPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        //liste_villes = new ArrayList<>();
        carte = new Carte();
        ville_survolee_listeners = new ArrayList<>();
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                String nom = JOptionPane.showInputDialog("Nom de la ville à ajouter", "votre recherche");
                if (nom != null) {
                    Ville ville = new Ville(nom, e.getX(), e.getY());
                    //liste_villes.add(ville);
                    getCarte().ajouterNoeud(ville);
                    getCarte().creerClique();
                    System.out.println("(Map Panel Class)Test: nb ville = "+carte.getListe_villes().size());
                    
                }
                repaint(); 
            }

            @Override
            public void mousePressed(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                boolean verifSurvol = false;
                for (Ville v : getCarte().getListe_villes()) {

                    if (e.getX() > v.getPosition_x() - 4 && e.getX() < v.getPosition_x() + 15
                            && e.getY() > v.getPosition_y() - 4 && e.getY() < v.getPosition_y() + 15) {
                        int posX = v.getPosition_x();
                        int posY = v.getPosition_y();
                        String nom = v.getNom();
                        fireEventVilleSurvolee(new EventVilleSurvolee(this, nom, posX, posY));
                        verifSurvol = true;
                    }
                }
                if (!verifSurvol) {
                    fireEventVilleSurvolee(null);
                }
            }
        });
    }


    public void paintComponent(Graphics g) {
    
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this);
        g.setColor(new Color(0,150,55));
        g.setFont(new Font(Font.DIALOG,Font.BOLD,14));


        for (Ville ville : getCarte().getListe_villes()) {
            g.drawString(ville.getNom(), ville.getPosition_x(), ville.getPosition_y());
            g.drawOval(ville.getPosition_x(), ville.getPosition_y(), 10, 10);
        }
        for (Arc a : getCarte().getListe_arcs()) {
            g.setColor(Color.red);
            int[] xs = {a.getV1().getPosition_x(),a.getV2().getPosition_x()};
            int[] ys ={a.getV1().getPosition_y(),a.getV2().getPosition_y()};
            g.drawPolyline(xs,ys,ys.length);
           
        }
        if (chemin.size()!=0){
            Graphics2D g2d = (Graphics2D)g;
            float[] style = {10,5};
            g2d.setStroke( new BasicStroke(3,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER));
            g2d.setColor(Color.BLUE);
            g2d.setFont(new Font(Font.DIALOG,Font.BOLD,30));
            for (int i=0;i<chemin.size()-1;i++){
                    g2d.drawString(chemin.get(0).getNom(), chemin.get(0).getPosition_x(), chemin.get(0).getPosition_y());
                    g2d.drawLine(chemin.get(i).getPosition_x(), chemin.get(i).getPosition_y(), chemin.get(i+1).getPosition_x(), chemin.get(i+1).getPosition_y());
                
            }    
        }
    }

    
    public void addVilleSurvoleeListener(VilleSurvoleeListener listener) {
        ville_survolee_listeners.add(listener);
    }

    public void fireEventVilleSurvolee(EventVilleSurvolee evt) {
        for (VilleSurvoleeListener listener : ville_survolee_listeners) {
            listener.onVilleSurvolee(evt);
        }
    }

    /**
     * @return the carte
     */
    public Carte getCarte() {
        return carte;
    }

    /**
     * @return the chemin
     */
    public ArrayList<Ville> getChemin() {
        return chemin;
    }

    /**
     * @param chemin the chemin to set
     */
    public void setChemin(ArrayList<Ville> chemin) {
        this.chemin = chemin;
    }

}
