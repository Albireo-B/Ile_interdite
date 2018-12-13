/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author grosa
 */
public class BoutonTuile extends JPanel {
    private JButton bouton;
    
    private ArrayList<Color> couleursJoueurs = new ArrayList();
    private ArrayList<JPanel> couleurs = new ArrayList();
    
    public BoutonTuile(String nom) {
        super(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        JPanel panelCouleurs = new JPanel(new GridLayout(1, 6));
        bouton = new JButton("["+nom+"]");
        this.add(bouton, BorderLayout.CENTER);
        
        for (int c = 0; c < 6; c++) {
            JPanel p = new JPanel();
            panelCouleurs.add(p);
            p.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            couleurs.add(p);
        }
        
        this.add(panelCouleurs, BorderLayout.SOUTH);
    }
    
    public void removeAventurier(Color j) {
        if (couleursJoueurs.contains(j)) {
            couleursJoueurs.remove(j);
            couleurs.get(couleursJoueurs.size()).setBackground(Color.GRAY);
        }
    }
    
    public void addAventurier(Color j) {
        if (couleursJoueurs.size() < 6) {
            couleurs.get(couleursJoueurs.size()).setBackground(j);
            couleursJoueurs.add(j);
        }
    }
    
    public void setButtonBackground(Color c) {
        bouton.setBackground(c);
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
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        getBouton().setText("["+nom+"]");
    }

    /**
     * @return the bouton
     */
    public JButton getBouton() {
        return bouton;
    }
}
