/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import ileInterdite.model.aventurier.Aventurier;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import utilitaires.Pion;
import utilitaires.Role;

/**
 *
 * @author grosa
 */
public class BoutonTuile extends JPanel {
    private JButton bouton;
    private ArrayList<JLabel> labels = new ArrayList();
    
    private String path = "src/images/tuiles/";
    private int width=130;
    private int height=130;
    private String nom;
    
    private HashMap<Pion, JLabel> labelsAventuriers = new HashMap();
    

    public BoutonTuile(String nom) {
        super();
        
        this.nom=nom;
        
        JLayeredPane layeredPane = new JLayeredPane();
        
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        ImageIcon imgTuile = new ImageIcon(new ImageIcon(path+nom+".png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        
        bouton = new JButton(imgTuile);
        bouton.setBounds(0, 0, width, height);
        
        layeredPane.setPreferredSize(new Dimension(width-4, height-4));
        
        JPanel panelAventuriers = new JPanel(new BorderLayout());
        
        JPanel panelHaut = new JPanel(new BorderLayout());
        JLabel p1 = new JLabel();
        panelHaut.add(p1, BorderLayout.WEST);
        labels.add(p1);
        
        JLabel p2 = new JLabel();
        panelHaut.add(p2, BorderLayout.EAST);
        labels.add(p2);
        
        
        JPanel panelBas = new JPanel(new BorderLayout());
        JLabel p3 = new JLabel();
        panelBas.add(p3, BorderLayout.WEST);
        labels.add(p3);
        
        JLabel p4 = new JLabel();
        panelBas.add(p4, BorderLayout.EAST);
        labels.add(p4);
        
        panelAventuriers.add(panelHaut, BorderLayout.NORTH);
        panelAventuriers.add(panelBas, BorderLayout.SOUTH);
        
        panelAventuriers.setBounds(0,0,width,height);
        panelAventuriers.setOpaque(false);
        
        panelHaut.setOpaque(false);
        panelBas.setOpaque(false);
        
        layeredPane.add(bouton, new Integer(0));
        layeredPane.add(panelAventuriers, new Integer(1));
        
        add(layeredPane);
    }

    public void removeAventurier(Pion pJoueur) {
        labelsAventuriers.get(pJoueur).setIcon(null);
        labelsAventuriers.remove(pJoueur);
    }

    public void addAventurier(Pion pJoueur) {
        if (labelsAventuriers.size() < 4) {
            System.out.println(pJoueur.toString());
            JLabel label = labels.get(labelsAventuriers.size());
            label.setIcon(pJoueur.getImage());
            labelsAventuriers.put(pJoueur, label);
        }
    }
    
    
    public void rescale(ImageIcon image,int resizedWidth,int resizedHeight){
        image.getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_DEFAULT);
    }

    public void setButtonForeground(Color c) {
        bouton.setForeground(c);
    }

    public void setButtonBorder(Color c) {
        bouton.setBorder(BorderFactory.createLineBorder(c,3));
    }

    public void setButtonEnabled(boolean e) {
        bouton.setEnabled(e);
    }

    public void addActionListener(ActionListener a) {
        bouton.addActionListener(a);
    }

    public void removeActionListener(ActionListener a) {
        bouton.removeActionListener(a);
    }

    /**
     * @return the bouton
     */
    public JButton getBouton() {
        return bouton;
    }

    /**
     * @return the width
     */
    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     * @return the height
     */
    @Override
    public int getHeight() {
        return this.height;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }
}
