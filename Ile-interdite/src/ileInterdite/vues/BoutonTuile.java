/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author grosa
 */
public class BoutonTuile extends JPanel {

    private JButton bouton;
    private Color myWhite = new Color(241, 241, 241);
    private Color couleurTexte = Color.BLACK;
    private ArrayList<Color> couleursJoueurs = new ArrayList();
    private ArrayList<JPanel> couleurs = new ArrayList();
    private String path = "src/images/tuiles/";
    private int width=130;
    private int height=130;
    private String nom;
    

    public BoutonTuile(String nom) {
        super(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        ImageIcon imgTuile = new ImageIcon(new ImageIcon(path+nom+".png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        bouton = new JButton(imgTuile);
        add(bouton, BorderLayout.CENTER);
        
        this.nom=nom;
        
        JPanel panelCouleurs = new JPanel(new GridLayout(1, 4));
        for (int c = 0; c < 4; c++) {
            JPanel p = new JPanel();
            panelCouleurs.add(p);
            couleurs.add(p);
        }

        add(panelCouleurs, BorderLayout.SOUTH);

    }

    public void removeAventurier(Color j) {
        if (couleursJoueurs.contains(j)) {
            for (int i = couleursJoueurs.indexOf(j); i < couleursJoueurs.size() - 1; i++) {
                couleurs.get(i).setBackground(couleursJoueurs.get(i + 1));
            }
            couleurs.get(couleursJoueurs.size() - 1).setBackground(myWhite);
            couleursJoueurs.remove(j);
        }
    }

    public void addAventurier(Color j) {
        if (couleursJoueurs.size() < 4) {
            couleurs.get(couleursJoueurs.size()).setBackground(j);
            couleursJoueurs.add(new Color(j.getRGB()));
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

    
    //Getters et Setter :
    
    
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
