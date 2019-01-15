/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import utilitaires.Role;
import ileInterdite.message.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import utilitaires.Action;

/**
 *
 * @author vinetg
 */
public class VuePrincipale extends Observable {
    public enum Bouton{DONNER,RECUPERER,ASSECHER, DEPLACER};
    
    private JFrame window;
    
    private JPanel panelPlateau =  new JPanel(new BorderLayout());
    
    private HashMap<Role,VueAventurier> panelAventuriers;
    private JPanel panelPrincipal = new JPanel(new BorderLayout());
    
    private JButton btnBouger = new JButton("Bouger");
    private JButton btnAssecher=new JButton("Assecher");
    private JButton btnDonner=new JButton("Donner");
    private JButton btnRecuperer=new JButton("Récuper");
    private JButton btnTerminerTour = new JButton("Terminer Tour");
    private JLabel labelNbPA = new JLabel();
    private JLabel labelNomJoueur = new JLabel("", SwingConstants.CENTER);
    private JPanel panelBoutons;
    
    private String path = "src/images/";
    
    private ImageIcon imgNiveau = new ImageIcon(new ImageIcon(path+"Niveau.png").getImage().getScaledInstance(130, 400, Image.SCALE_DEFAULT));
    
    /**
     * On définit un constructeur de VueAventurier avec une VueGrille v
     * @param v
     * @param roleAventurier
     */
    public VuePrincipale(VueGrille v, HashMap<Role, VueAventurier> vuesAventuriers){
        window = new JFrame();
        window.setSize(1600,800);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE); 
        window.setTitle("Ile interdite");
        window.add(panelPrincipal);
        
        JPanel panelCentre = new JPanel(new BorderLayout());
        
        panelPlateau.add(v.getPanelGrille(), BorderLayout.CENTER);
        
        labelNomJoueur.setForeground(Color.WHITE);
        
        JPanel paneGauche=new JPanel(new GridLayout(2,1));
      
        
        JPanel paneDroite=new JPanel(new GridLayout(2,1));
        
        panelPrincipal.add(paneGauche,BorderLayout.WEST);
        panelPrincipal.add(paneDroite,BorderLayout.EAST);
        
       
        
        panelPrincipal.add(panelCentre, BorderLayout.CENTER);
        
        panelCentre.add(panelPlateau, BorderLayout.CENTER);
        //=====================================================================
        panelBoutons = new JPanel(new GridLayout(3,2));
        
        
        
        panelBoutons.add(btnDonner);
        panelBoutons.add(btnRecuperer);
        panelBoutons.add(btnBouger);
        panelBoutons.add(btnAssecher);
        panelBoutons.add(btnTerminerTour);
        panelBoutons.add(labelNbPA);
        
        panelPlateau.add(new JLabel(imgNiveau), BorderLayout.EAST);
        
        panelPlateau.add(panelBoutons, BorderLayout.SOUTH);
        
     
        btnBouger.setVisible(false);
        btnBouger.addActionListener((ActionEvent e) -> {
            setChanged();
            notifyObservers(new Message(Action.DEPLACER,null));
            clearChanged();
        });
        
        btnAssecher.setVisible(false);
        btnAssecher.addActionListener((ActionEvent e) -> {
            setChanged();
            notifyObservers(new Message(Action.ASSECHER,null));
            clearChanged();
        });

        
        btnTerminerTour.addActionListener((ActionEvent e) -> {
            setChanged();
            notifyObservers(new Message(Action.TERMINER,null));
            clearChanged();
        });
        
        btnDonner.setVisible(false);
        btnDonner.addActionListener((ActionEvent arg0) -> {
            setChanged();
            notifyObservers(new Message(Action.DONNER,null));
            clearChanged();
        });
        
        btnRecuperer.setVisible(false);
        btnRecuperer.addActionListener((ActionEvent arg) -> {
            setChanged();
            notifyObservers(new Message(Action.RECUPERER_TRESOR,null));
            clearChanged();
        });
        
        //===================pour chaque aventurier different=================
        
        panelAventuriers=vuesAventuriers;
        
        ArrayList<VueAventurier> listeVuesAv = new ArrayList(vuesAventuriers.values());
        
        paneGauche.add(listeVuesAv.get(0).getPanelGeneral());
        paneGauche.add(listeVuesAv.get(3).getPanelGeneral());
        
        paneDroite.add(listeVuesAv.get(1).getPanelGeneral());
        paneDroite.add(listeVuesAv.get(2).getPanelGeneral());
    }
    
    public void actualiserVue(String nomJoueur, Role classe, Color couleur, int nombrePA) {
        if (nombrePA == 0) {
            getBtnBouger().setVisible(false);
        }
        else {
            getBtnBouger().setVisible(true);
        }
        
        getPanelPrincipal().setBorder(BorderFactory.createLineBorder(couleur, 2));
        

        getPanelAventuriers().get(classe).getPanelGeneral().setBackground(couleur);
        getLabelNomJoueur().setText(classe + " ( " + nomJoueur + " ) ");
        
        panelPlateau.setBorder(new MatteBorder(0, 0, 2, 0, couleur));
        
        getLabelNbPA().setText("Nombre d'actions restantes : " + nombrePA);
        
        getWindow().setVisible(true);
    }
    

    public void cacherBouton(Bouton bouton){
        switch (bouton){
            case DEPLACER :
                btnBouger.setVisible(false);
                System.out.println("bouger"+btnBouger.isVisible());
                break;
            case ASSECHER :
                btnAssecher.setVisible(false);
                System.out.println("Assecher"+btnAssecher.isVisible());
                break;
            case DONNER :
                btnDonner.setVisible(false);
                System.out.println("Donner"+btnDonner.isVisible());
                break;
            case RECUPERER:
                btnRecuperer.setVisible(false);
                System.out.println("Recuperer"+btnRecuperer.isVisible());
                break;
        }
    }
    
    public void afficherBouton(Bouton bouton){
         switch (bouton){
            case DEPLACER :
                btnBouger.setVisible(true);
                System.out.println("bouger"+btnBouger.isVisible());
                break;
            case ASSECHER :
                btnAssecher.setVisible(true);
                System.out.println("Assecher"+btnAssecher.isVisible());
                break;
            case DONNER :
                btnDonner.setVisible(true);
                System.out.println("Donner"+btnDonner.isVisible());
                break;
            case RECUPERER:
                btnRecuperer.setVisible(true);
                System.out.println("Recuperer"+btnRecuperer.isVisible());
                break;
        }
    }
    
     //Getters et Setters :
    
     
    /**
     * Ferme la fenêtre
     */
    public void close() {
        getWindow().dispose();
    }

    /**
     * @return the window
     */
    public JFrame getWindow() {
        return window;
    }

    /**
     * @return the panelAventuriers
     */
    public HashMap<Role,VueAventurier> getPanelAventuriers() {
        return panelAventuriers;
    }

    /**
     * @return the panelPrincipal
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * @return the btnBouger
     */
    public JButton getBtnBouger() {
        return btnBouger;
    }

    /**
     * @return the btnAssecher
     */
    public JButton getBtnAssecher() {
        return btnAssecher;
    }

    /**
     * @return the btnDonner
     */
    public JButton getBtnDonner() {
        return btnDonner;
    }

    /**
     * @return the btnRecuper
     */
    public JButton getBtnRecuperer() {
        return btnRecuperer;
    }

    /**
     * @return the labelNbPA
     */
    public JLabel getLabelNbPA() {
        return labelNbPA;
    }

    /**
     * @return the labelNomJoueur
     */
    public JLabel getLabelNomJoueur() {
        return labelNomJoueur;
    }

    

}

 



