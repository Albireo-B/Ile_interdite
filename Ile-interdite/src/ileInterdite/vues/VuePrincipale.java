/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import ileInterdite.message.Action;
import ileInterdite.model.aventurier.Role;
import ileInterdite.message.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Observable;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

/**
 *
 * @author vinetg
 */

 
public class VuePrincipale extends Observable {
    
    private JFrame window;
    
    private JPanel panelCentre =  new JPanel(new BorderLayout());
    private JPanel panelAventurier = new JPanel();
    private JPanel panelPrincipal = new JPanel(new BorderLayout());
    
    private JButton btnBouger = new JButton("Bouger");
    
    private JLabel labelNbPA = new JLabel();
    private JLabel labelNomJoueur = new JLabel("", SwingConstants.CENTER);
    
    /**
     * On définit un constructeur de VueAventurier avec une VueGrille v
     * @param v
     */
    public VuePrincipale(VueGrille v){
        window = new JFrame();
        window.setSize(1380,800);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE); 
        window.setTitle("Ile interdite");
        window.add(panelPrincipal);
        
        panelCentre.add(v.getPanelGrille(), BorderLayout.CENTER);
        
        labelNomJoueur.setForeground(Color.WHITE);
        panelAventurier.add(labelNomJoueur);
        panelPrincipal.add(panelAventurier, BorderLayout.NORTH);
        
        panelPrincipal.add(panelCentre, BorderLayout.CENTER);
        
        JPanel panelBoutons = new JPanel(new GridLayout(2,2));
        
        JButton btnAssecher= new JButton("Assecher");
        JButton btnTerminerTour = new JButton("Terminer Tour");
        
        panelBoutons.add(btnBouger);
        panelBoutons.add(btnAssecher);
        panelBoutons.add(btnTerminerTour);
        panelBoutons.add(labelNbPA);
        
        panelPrincipal.add(panelBoutons, BorderLayout.SOUTH);
                 
        btnBouger.addActionListener((ActionEvent e) -> {
            setChanged();
            notifyObservers(new Message(Action.DEPLACER));
            clearChanged();
        });

        btnAssecher.addActionListener((ActionEvent e) -> {
            setChanged();
            notifyObservers(new Message(Action.ASSECHER));
            clearChanged();
        });

        btnTerminerTour.addActionListener((ActionEvent e) -> {
            setChanged();
            notifyObservers(new Message(Action.TERMINER));
            clearChanged();
        });
    }
    
    public void actualiserVue(String nomJoueur, Role classe, Color couleur, int nombrePA) {
        if (nombrePA == 0) {
            btnBouger.setVisible(false);
        }
        else {
            btnBouger.setVisible(true);
        }
        
        panelPrincipal.setBorder(BorderFactory.createLineBorder(couleur, 2));
        
        panelAventurier.setBackground(couleur);
        labelNomJoueur.setText(classe + " ( " + nomJoueur + " ) ");
        
        panelCentre.setBorder(new MatteBorder(0, 0, 2, 0, couleur));
        
        labelNbPA.setText("Nombre d'actions restantes : " + nombrePA);
        
        window.setVisible(true);
    }
    
    /**
     * Ferme la fenêtre
     */
    public void close() {
        window.dispose();
    }
}

 



