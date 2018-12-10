/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import ileInterdite.actions.*;
import ileInterdite.aventurier.*;
import ileInterdite.controleur.utilitaires.Utils.*;
import ileInterdite.message.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
     
    private final JPanel panelBoutons ;
    private final JPanel panelCentre ;
    private final JFrame window;
    private final JPanel panelAventurier;
    private final JPanel mainPanel;
    private final JButton btnBouger  ;
    private final JButton btnAssecher;
    private final JLabel nbPA;
    private final JButton btnTerminerTour;
    private VueGrille vueGrille;

   
   
   
    
    public VueAventurier(String nomJoueur, String nomAventurier, Color couleur,int nombrePA){

        this.window = new JFrame();
        window.setSize(650, 650);
        //le titre = nom du joueur 
        window.setTitle(nomJoueur);
        System.out.println(nomJoueur);
        mainPanel = new JPanel(new BorderLayout());
        this.window.add(mainPanel);

        mainPanel.setBackground(new Color(230, 230, 230));
        mainPanel.setBorder(BorderFactory.createLineBorder(couleur, 2)) ;

        // =================================================================================
        // NORD : le titre = nom de l'aventurier sur la couleurActive du pion

        this.panelAventurier = new JPanel();
        panelAventurier.setBackground(couleur);
        panelAventurier.add(new JLabel(nomAventurier,SwingConstants.CENTER ));
        mainPanel.add(panelAventurier, BorderLayout.NORTH);
   
        // =================================================================================
        // CENTRE : 1 ligne pour position courante
        this.panelCentre = new JPanel(new GridLayout(2, 1));
        this.panelCentre.setOpaque(false);
        this.panelCentre.setBorder(new MatteBorder(0, 0, 2, 0, couleur));
        mainPanel.add(this.panelCentre, BorderLayout.CENTER);
        
        panelCentre.add(new JLabel ("Position", SwingConstants.CENTER));
        vueGrille = new  VueGrille(); 
        //panelCentre.add(vueGrille);


        // =================================================================================
        // SUD : les boutons
        this.panelBoutons = new JPanel(new GridLayout(2,2));
        this.panelBoutons.setOpaque(false);
        mainPanel.add(this.panelBoutons, BorderLayout.SOUTH);

        this.btnBouger = new JButton("Bouger") ;
        this.btnAssecher = new JButton( "Assecher");
        this.nbPA = new JLabel("Nombre d'actions restantes : " + nombrePA);
        this.btnTerminerTour = new JButton("Terminer Tour") ;
        
        this.panelBoutons.add(btnBouger);
        this.panelBoutons.add(btnAssecher);
        this.panelBoutons.add(nbPA);
        this.panelBoutons.add(btnTerminerTour);

        this.window.setVisible(true);
        
        btnBouger.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(new Message(Action.DEPLACER));
                clearChanged();
            }
        });

        btnAssecher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(new Message(Action.ASSECHER));
                clearChanged();
            }
        });

        btnTerminerTour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers();
                clearChanged();
            }
        });
    
    }
    
        public void actualiser(){
           mainPanel.removeAll();
           panelAventurier.setBackground(this.);
           panelAventurier.add(new JLabel(aventurierCourant.getNomJoueur(),SwingConstants.CENTER ));
           
           
           
        }
    
   
    public JButton getBtnBouger() {
        return btnBouger;
    }
    
    public JButton getBtnAssecher() {
        return btnAssecher;
    }

    public JButton getBtnTerminerTour() {
        return btnTerminerTour;
    }
 
}

 



