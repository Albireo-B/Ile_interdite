/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author grosa
 */
public class VueNiveau extends JPanel {
    private String path = "src/images/";
    private ImageIcon imgNiveau = new ImageIcon(new ImageIcon(path + "NiveauRotated-ConvertImage.png").getImage().getScaledInstance(300, 130, Image.SCALE_SMOOTH));
    private ImageIcon imgCurseur = new ImageIcon(new ImageIcon(path + "stick.png").getImage().getScaledInstance(10, 30, Image.SCALE_SMOOTH));
    private int niveau = 0;
    
    private int offsetDepart = 50;
    
    private JLabel curseur ;

    public VueNiveau(int niveau) {
        super(new BorderLayout());
        
        JLayeredPane layeredPane = new JLayeredPane();
        
        JLabel fond = new JLabel(imgNiveau);
        fond.setBounds(0, 0, imgNiveau.getIconWidth(), imgNiveau.getIconHeight());
        layeredPane.setBounds(0, 0, imgNiveau.getIconWidth(), imgNiveau.getIconHeight());
        
        curseur = new JLabel(imgCurseur);
        curseur.setBounds(offsetDepart, 0, imgCurseur.getIconWidth(), imgCurseur.getIconHeight());
        
        layeredPane.add(fond, new Integer(0));
        layeredPane.add(curseur, new Integer(1));
        
        add(layeredPane, BorderLayout.CENTER);
        setNiveau(niveau);
    }
    
    public void setNiveau(int niveau) {
        this.niveau = niveau;
        curseur.setBounds(offsetDepart + (niveau-1) * 25, 0, imgCurseur.getIconWidth(), imgCurseur.getIconHeight());
    }
}
