
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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
import voyageurdecommerce.events.EventVilleSurvolee;
import voyageurdecommerce.events.VilleSurvoleeListener;

/**
 *
 * @author Charlotte
 */
public class MapPanel extends JPanel {
    
    private BufferedImage background;
    
    private List<Ville> liste_villes;
    private List<VilleSurvoleeListener> ville_survolee_listeners; 
    
    public MapPanel() {
        try {
            background = ImageIO.read(new File("./resources/france.gif"));
        } catch (IOException ex) {
            Logger.getLogger(MapPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        liste_villes = new ArrayList<>();
        ville_survolee_listeners = new ArrayList<>();
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                
                String nom = JOptionPane.showInputDialog("Nom de la ville à ajouter", "votre recherche");
                if (nom != null){
                    Ville ville = new Ville(nom, e.getX(), e.getY());
                    liste_villes.add(ville);                    
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
                for(Ville v : liste_villes) {
                    if (e.getX() > v.getPosition_x()-4 && e.getX() < v.getPosition_x() + 15 
                            && e.getY() > v.getPosition_y()-4 && e.getY() < v.getPosition_y() + 15){
                        int posX = e.getX();
                        int posY = e.getY();
                        String nom = v.getNom();
                        fireEventVilleSurvolee(new EventVilleSurvolee(this, nom, posX, posY));
                    }
                }
            }
        });
    }
    
    
    @Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this);
        g.setColor(Color.red);
        
        for (Ville ville : liste_villes){
            g.drawString(ville.getNom(), ville.getPosition_x(), ville.getPosition_y());
            g.drawOval(ville.getPosition_x(), ville.getPosition_y(), 8, 8);
        }  
    }
    
    public void addVilleSurvoleeListener(VilleSurvoleeListener listener) {
        ville_survolee_listeners.add(listener);
    }
    
    public void fireEventVilleSurvolee(EventVilleSurvolee evt) {
        for(VilleSurvoleeListener listener : ville_survolee_listeners) {
            listener.onVilleSurvolee(evt);
        }
    }
    
    
}
