
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
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
 * @author Charlotte
 * Méthode masquée pour le MouseListener (via utilisation de MouseAdapter):
 *  - public void mousePressed(MouseEvent e) {}
 *  - public void mouseReleased(MouseEvent e) {}
 *  - public void mouseEntered(MouseEvent e) {}
 *  - public void mouseExited(MouseEvent e) {}
 * 
 * 
 * Méthode masquée pour le MouseMotionListener (via utilisation de MouseMotionAdapter):
 * - public void mouseDragged(MouseEvent e) {}
 * 
 */

public class MapPanel extends JPanel {

    private BufferedImage background;
    private Carte carte;
    //private List<Ville> liste_villes;
    private List<VilleSurvoleeListener> ville_survolee_listeners;
    private HashMap<Algorithme, ArrayList<String>> hashChemin;
    private ArrayList<Ville> chemin;
    boolean bool;

    public MapPanel() {
        hashChemin = new HashMap(new Hashtable<Algorithme, ArrayList<String>>());
        chemin = new ArrayList<Ville>();
        bool = false;
        try {
            background = ImageIO.read(new File("./ressources/france2.png"));
        } 
        catch (IOException ex) {
            Logger.getLogger(MapPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        //liste_villes = new ArrayList<>();
        carte = new Carte();
        ville_survolee_listeners = new ArrayList<>();
        
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String nom = JOptionPane.showInputDialog("Nom de la ville à ajouter", "votre ville");
                if (nom != null) {
                    Ville ville = new Ville(nom, e.getX(), e.getY());
                    //liste_villes.add(ville);
                    getCarte().ajouterNoeud(ville);
                    getCarte().creerClique();
                    System.out.println("(Map Panel Class)Test: nb ville = " + carte.getListe_villes().size());
                }
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
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
        Graphics2D g2d = (Graphics2D) g;
        Graphics2D g1 = (Graphics2D) g;
        g1.drawImage(background, 0, 0, this);
        g1.setColor(Color.yellow);
        g1.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
        g1.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        if (bool == false) { // Pour rendre fonctionel l'item "Nouvelle Fenetre" : permet de la remettre à blanc
            for (Ville ville : getCarte().getListe_villes()) {
                g1.drawString(ville.getNom(), ville.getPosition_x(), ville.getPosition_y());
                g1.drawOval(ville.getPosition_x(), ville.getPosition_y(), 10, 10);
            }
            for (Arc a : getCarte().getListe_arcs()) {
                g1.setColor(Color.LIGHT_GRAY);
                int[] xs = {a.getV1().getPosition_x(), a.getV2().getPosition_x()};
                int[] ys = {a.getV1().getPosition_y(), a.getV2().getPosition_y()};
                g1.drawPolyline(xs, ys, ys.length);

            }
            if (chemin.size() != 0 ) {

                float[] style = {10, 5};
                g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
                g2d.setColor(Color.YELLOW);
                g2d.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
                for (int i = 0; i < chemin.size() - 1; i++) {
                    g2d.drawString(chemin.get(0).getNom(), chemin.get(0).getPosition_x(), chemin.get(0).getPosition_y());
                    g2d.drawLine(chemin.get(i).getPosition_x(), chemin.get(i).getPosition_y(), chemin.get(i + 1).getPosition_x(), chemin.get(i + 1).getPosition_y());

                }
            }
        } 
        else {
            //super.paintComponent(g1);
            //g2d.drawImage(background, 0,0, this);
            g1.drawImage(background, 0, 0, this);
            repaint();
            chemin.removeAll(chemin);
            bool = false;
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

    /**
     * @return the hashChemin
     */
    public HashMap<Algorithme, ArrayList<String>> getHashChemin() {
        return hashChemin;
    }

    /**
     * @param hashChemin the hashChemin to set
     */
    public void setHashChemin(Algorithme algo, ArrayList<String> chemin) {
        this.hashChemin.put(algo, chemin);;
    }

}
