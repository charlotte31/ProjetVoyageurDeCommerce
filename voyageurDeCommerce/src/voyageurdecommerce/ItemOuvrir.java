/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.regex.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Melany Classe à part entière pour rendre plus lisible le constructeur
 * de la classe VoyageurDeCommerceInerface. La méthode du listener permet
 * d'ouvrir un fichier (seule les extensions .vdc (VoyageurDeCommerce) sont
 * permises). Une fois que l'utilisateur ouvre le fichier, une nouvelle fenêtre
 * apparait et affiche toutes les informations.
 *
 */
public class ItemOuvrir extends JMenuItem implements ActionListener {

    private VoyageurDeCommerceInterface vdci;
    private File myFile;
    private JTextField nom_fichier;
    private JTextArea texte;
    private Pattern p;
    private Pattern p2;
    private Matcher m;

    public ItemOuvrir(String nom, VoyageurDeCommerceInterface vdci) {
        super(nom);
        this.vdci = vdci;

    }

    public void actionPerformed(ActionEvent e) {
        // Parser : ouverture d'un file (extension .vdc), et chargement du graphe dans une nouvelle fenêtre
        JFileChooser fileChooser = new JFileChooser(".");
        FileFilter filter1 = new ExtensionFileFilter("txt", new String[]{"vdc"}); // Création de l'extension permise pour les fichiers d'ouvertures
        for (int i = 0; i < fileChooser.getChoosableFileFilters().length; i++) {  // Suppression de toutes les extensions permises par défaut (càd toutes)
            fileChooser.removeChoosableFileFilter(fileChooser.getChoosableFileFilters()[i]);
        }
        fileChooser.addChoosableFileFilter(filter1); // Ajout de la seule permise (.vdc)
        int rep = fileChooser.showOpenDialog(null); //  Pour savoir que l'utilisater clique sur "ouvrir"
        if (rep == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile(); // Récupération du fichier sélectionner
            System.out.println(file);
            try {
                BufferedReader bufR = new BufferedReader(new FileReader(file));
                VoyageurDeCommerceInterface vdci = new VoyageurDeCommerceInterface(false); // Nouvelle fenêtre              
                String line = bufR.readLine();
                while (line != null) {
                    if (line.split("\t").length > 1) { // pour éviter les entêtes du format .vdc
                        p = Pattern.compile("\\d");  // modélisation du pattern pour les positions
                        p2 = Pattern.compile("\\D"); // idem pour les successeurs
                        //  Fichier .vdc : deux cas possibles pour les lignes : 
                        // (i): A   posX    posY
                        // (ii): A  B   distance
                        // D'abord, ce sont les lignes (i) vues : on crée les villes 
                        // Puis ensuite, on crée les arcs

                        m = p.matcher(line.split("\t")[1]); // on regarde si ça match avec (i)
                        boolean b = m.find();
                        m = p2.matcher(line.split("\t")[1]); // on regarde si ça match avec (ii)
                        boolean b2 = m.find();

                        if (b) {
                            int pos_x = Integer.parseInt(line.split("\t")[1]);
                            int pos_y = Integer.parseInt(line.split("\t")[2]);
                            System.out.println(pos_x + " " + pos_y); // pour tester
                            vdci.getCarteVoyageurDeCommerce().getListe_villes().add(new Ville(line.split("\t")[0], pos_x, pos_y));
                        }

                        if (b2) {
                            Ville v1 = vdci.getCarteVoyageurDeCommerce().getVille(line.split("\t")[0], vdci.getCarteVoyageurDeCommerce().getListe_villes());
                            Ville v2 = vdci.getCarteVoyageurDeCommerce().getVille(line.split("\t")[1], vdci.getCarteVoyageurDeCommerce().getListe_villes());
                            if (v1 != null && v2 != null) {
                                vdci.getCarteVoyageurDeCommerce().ajouterArc(new Arc(v1, v2));
                                vdci.getModelTableau().addData(new Arc(v1, v2));
                            } else {
                                JOptionPane.showMessageDialog(this, "Failed reading file");
                            }
                        }
                    }
                    line = bufR.readLine();
                }

                System.out.println(vdci.getCarteVoyageurDeCommerce().getListe_villes().size()); // pour tester
                System.out.println(vdci.getCarteVoyageurDeCommerce().getListe_arcs().size());
                bufR.close();

            } catch (IOException ex) {
                // TODO Auto-generated catch block
                ex.printStackTrace();
            }
        }

    }
}
