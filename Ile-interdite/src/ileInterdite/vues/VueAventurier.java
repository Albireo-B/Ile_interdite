/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import ileInterdite.actions.*;
import ileInterdite.aventurier.Role;
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

 
public class VueAventurier extends Observable {
    
    private JFrame window;
    
    private JPanel panelBoutons = new JPanel(new GridLayout(2,2));
    private JPanel panelCentre =  new JPanel(new BorderLayout());
    private JPanel panelAventurier = new JPanel();
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JButton btnBouger = new JButton("Bouger");
    private JButton btnAssecher= new JButton( "Assecher");
    private JLabel nbPA = new JLabel();
    private JButton btnTerminerTour = new JButton("Terminer Tour") ;
    private VueGrille vueGrille;
    
    /**
     * On définit un constructeur de VueAventurier  avec une VueGrille v
     * @param v
     */
    public VueAventurier(VueGrille v){
        vueGrille = v;
        window = new JFrame();
        window.setSize(1080, 720);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE); 
        window.setTitle("Ile interdite");
        window.add(mainPanel);
        
        mainPanel.add(panelAventurier, BorderLayout.NORTH);
        
        panelCentre.setOpaque(false);
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        panelCentre.add(vueGrille.getPanelGrille(),  BorderLayout.CENTER);
        mainPanel.add(panelBoutons, BorderLayout.SOUTH);
        
        panelBoutons.setOpaque(false);
        panelBoutons.add(btnBouger);
        panelBoutons.add(btnAssecher);
        panelBoutons.add(btnTerminerTour);
        panelBoutons.add(nbPA);
                      
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
    
    public void actualiserVue(String nomJoueur, Role classe, Color couleur, int nombrePA){
            
        if (nombrePA == 0) {
            btnBouger.disable();
        }
        
        mainPanel.setBorder(BorderFactory.createLineBorder(couleur, 2)) ;
        
        panelAventurier.setBackground(couleur);
        panelAventurier.removeAll();
        panelAventurier.add(new JLabel(classe + " ( " + nomJoueur + " ) ", SwingConstants.CENTER ));
        
        panelCentre.setBorder(new MatteBorder(0, 0, 2, 0, couleur));
        
        panelBoutons.remove(nbPA);
        nbPA.setText("Nombre d'actions restantes : " + nombrePA);
        panelBoutons.add(nbPA);
        
        window.setVisible(true);
    }
    
    /**
     * Ferme la fenêtre
     */
    public void close() {
        window.dispose();
    }
}

 



