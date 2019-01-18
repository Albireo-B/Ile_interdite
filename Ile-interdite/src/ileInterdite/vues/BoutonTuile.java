/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import utilitaires.Pion;

/**
 *
 * @author grosa
 */
public class BoutonTuile extends JPanel {
    private JButton bouton;
    private HashMap<JLabel, Boolean> labels = new HashMap();
    private String path = "src/images/tuiles/";
    private int width=141;
    private int height=width;
    private String nom;
    private HashMap<Pion, JLabel> labelsAventuriers = new HashMap();
    private int nbAventuriers = 0;
    

    protected BoutonTuile(String nom) {
        super(new BorderLayout());
        
        this.nom=nom;
        
        JLayeredPane layeredPane = new JLayeredPane();
        
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        ImageIcon imgTuile = new ImageIcon(new ImageIcon(path+nom+".png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        
        bouton = new JButton(imgTuile);
        bouton.setBounds(1, 1, width-4, height-4);
        
        layeredPane.setPreferredSize(new Dimension(width-4, height-4));
        
        JPanel panelAventuriers = new JPanel(new BorderLayout());
        
        JPanel panelHaut = new JPanel(new BorderLayout());
        JLabel p1 = new JLabel();
        panelHaut.add(p1, BorderLayout.WEST);
        labels.put(p1, Boolean.FALSE);
        
        JLabel p2 = new JLabel();
        panelHaut.add(p2, BorderLayout.EAST);
        labels.put(p2, Boolean.FALSE);
        
        
        JPanel panelBas = new JPanel(new BorderLayout());
        JLabel p3 = new JLabel();
        panelBas.add(p3, BorderLayout.WEST);
        labels.put(p3, Boolean.FALSE);
        
        JLabel p4 = new JLabel();
        panelBas.add(p4, BorderLayout.EAST);
        labels.put(p4, Boolean.FALSE);
        
        panelAventuriers.add(panelHaut, BorderLayout.NORTH);
        panelAventuriers.add(panelBas, BorderLayout.SOUTH);
        
        panelAventuriers.setBounds(8, 0, width, height-20);
        panelAventuriers.setOpaque(false);
        
        panelHaut.setOpaque(false);
        panelBas.setOpaque(false);
        
        layeredPane.add(bouton, new Integer(0));
        layeredPane.add(panelAventuriers, new Integer(1));
        
        add(layeredPane, BorderLayout.CENTER);
    }

    protected void removeAventurier(Pion pJoueur) {
        labelsAventuriers.get(pJoueur).setIcon(null);
        labels.replace(labelsAventuriers.get(pJoueur), Boolean.FALSE);
        nbAventuriers --;
    }

    protected void addAventurier(Pion pJoueur) {
        System.out.println(labelsAventuriers.size());
        if (nbAventuriers < 4) {
            JLabel label = null;
            
            for (Entry<JLabel, Boolean> entry : labels.entrySet()) {
                
                if (Objects.equals(Boolean.FALSE, entry.getValue())) {
                    nbAventuriers ++;
                    label = entry.getKey();
                    System.out.println("Okay");
                    label.setIcon(pJoueur.getImage());
                    if (labelsAventuriers.containsKey(pJoueur))
                        labelsAventuriers.replace(pJoueur, label);
                    else
                        labelsAventuriers.put(pJoueur, label);
                    break;
                }
            }
            if (label != null) {
                labels.replace(label, Boolean.TRUE);
                
            }
        }
    }
    

    protected void setButtonBorder(Color c) {

        bouton.setBorder(BorderFactory.createLineBorder(c,3));
    }

    protected void setButtonEnabled(boolean e) {
        bouton.setEnabled(e);
    }

    protected void addActionListener(ActionListener a) {
        bouton.addActionListener(a);
    }

    protected void removeActionListener(ActionListener a) {
        bouton.removeActionListener(a);
    }

    
    //Getters et Setter :
    
    
    /**
     * @return the bouton
     */
    
    protected JButton getBouton() {
        return bouton;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }
}
