/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Charlotte
 */
public class MapPanel extends JPanel {
    
    private BufferedImage background;
    private List<Point> points;
    private List<JLabel> cities;
    
    public MapPanel() {
        try {
            background = ImageIO.read(new File("./resources/france.gif"));
        } catch (IOException ex) {
            Logger.getLogger(MapPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        points = new ArrayList<>();
        cities = new ArrayList<>();
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                
                points.add(new Point(e.getX() - 4, e.getY() - 4));
                JLabel city = new JLabel("Toulouse");
                cities.add(city);
                add(city);
                city.setLocation(e.getX() - 10 , e.getY() - 15);
                validate();
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
    }
    
    
    @Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this);
        g.setColor(Color.red);
        for (Point p: points) {            
            g.fillOval(p.x, p.y, 8, 8);
        }
//        for(int i = 1; i < points.size(); i++) {
//            Point p1 = points.get(i - 1);
//            Point p2 = points.get(i);
//            
//            g.drawLine(p1.x, p1.y, p2.x, p2.y);
//        }
    }
    
    
}
