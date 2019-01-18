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
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import utilitaires.Action;

/**
 *
 * @author vinetg
 */
public class VuePrincipale extends Observable {

    public enum Bouton {
        DONNER, RECUPERER, ASSECHER, DEPLACER, TERMINER_TOUR;
    };

    private JFrame window;

    private JPanel panelPlateau = new JPanel(new BorderLayout());

    private HashMap<Role, VueAventurier> panelAventuriers;
    private JPanel panelPrincipal = new JPanel(new BorderLayout());

    private JButton btnBouger;
    private JButton btnAssecher;
    private JButton btnDonner;
    private JButton btnRecuperer;
    private JButton btnTerminerTour = new JButton("Terminer le Tour");
    private JLabel labelNbPA = new JLabel();
    private JLabel labelNomJoueur = new JLabel("", SwingConstants.CENTER);
    private JPanel panelBoutons;
    private VueNiveau vueNiveau;
    private Color myNewBlue = new Color (30,144,255);
    
    private int width = 1600;
    private int height = 1000;

    private String path = "src/images/";

    private ImageIcon imgNiveau = new ImageIcon(new ImageIcon(path + "NiveauRotated-ConvertImage.png").getImage().getScaledInstance(300, 130, Image.SCALE_SMOOTH));

    /**
     * On définit un constructeur de VueAventurier avec une VueGrille v
     *
     * @param v
     * @param vuesAventuriers
     */
    public VuePrincipale(VueGrille v, HashMap<Role,VueAventurier> vuesAventuriers) {
        btnRecuperer = new JButton(new ImageIcon(path+"icones/iconGet.png"));
        btnRecuperer.setText("Récupérer un trésor");
        btnDonner = new JButton(new ImageIcon(path+"icones/iconGive.png"));
        btnDonner.setText("Donner une carte trésor");
        btnAssecher = new JButton(new ImageIcon(path+"icones/iconDry.png"));
        btnAssecher.setText("Assécher une case");
        btnBouger = new JButton(new ImageIcon(path+"icones/iconMove.png"));
        btnBouger.setText("Se déplacer sur une case");
        
        window = new JFrame();
        window.setSize(width, height);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setTitle("Ile interdite");
        window.add(panelPrincipal);
        window.setResizable(true);
        
        
        labelNbPA.setFont(new Font("Arial", Font.BOLD, 14));
        labelNbPA.setForeground(Color.yellow);
        btnTerminerTour.setFont(new Font("Arial", Font.BOLD, 25));

        JPanel panelCentre = new JPanel(new BorderLayout());
       
        JPanel panelGrille = new JPanel();
        panelGrille.add(v.getPanelGrille());
        
        v.getPanelGrille().setPreferredSize(new Dimension(850,850));
        panelPlateau.add(panelGrille, BorderLayout.CENTER);
        labelNomJoueur.setForeground(Color.WHITE);

        JPanel paneGauche = new JPanel(new BorderLayout());

        JPanel paneDroite = new JPanel(new BorderLayout());

 
        panelPrincipal.add(panelCentre, BorderLayout.CENTER);
        

        panelCentre.add(panelPlateau, BorderLayout.CENTER);
        //=====================================================================
        
        
        
      
        panelBoutons = new JPanel(new GridLayout(2, 2));
        panelBoutons.setPreferredSize(new Dimension(30, 120));
        panelBoutons.add(btnBouger);
        panelBoutons.add(btnDonner);
        panelBoutons.add(btnAssecher);
        panelBoutons.add(btnRecuperer);
        
        vueNiveau=new VueNiveau(0);
    
        panelPlateau.add(panelBoutons, BorderLayout.SOUTH);
        
        panelCentre.add(panelBoutons, BorderLayout.SOUTH);

        btnBouger.setVisible(true);
        btnBouger.addActionListener((ActionEvent e) -> {
            setChanged();
            notifyObservers(new Message(Action.DEPLACER, null));
            clearChanged();
        });

        btnAssecher.setVisible(true);
        btnAssecher.addActionListener((ActionEvent e) -> {
            setChanged();
            notifyObservers(new Message(Action.ASSECHER, null));
            clearChanged();
        });

        btnTerminerTour.addActionListener((ActionEvent e) -> {
            setChanged();
            notifyObservers(new Message(Action.TERMINER, null));
            clearChanged();
        });
        

        btnDonner.setVisible(true);
        btnDonner.addActionListener((ActionEvent arg0) -> {
            setChanged();
            notifyObservers(new Message(Action.DONNER, null));
            clearChanged();
        });

        btnRecuperer.setVisible(true);
        btnRecuperer.addActionListener((ActionEvent arg) -> {
            setChanged();
            notifyObservers(new Message(Action.RECUPERER_TRESOR, null));
            clearChanged();
        });

        //===================pour chaque aventurier different=================
        panelAventuriers = vuesAventuriers;
        

        ArrayList<VueAventurier> listeVuesAv = new ArrayList(vuesAventuriers.values());
        paneGauche.add(listeVuesAv.get(0).getPanelGeneral(),BorderLayout.NORTH);
        paneGauche.add(labelNbPA,BorderLayout.SOUTH);
        paneGauche.add(listeVuesAv.get(3).getPanelGeneral(),BorderLayout.SOUTH);
        paneGauche.setPreferredSize(new Dimension(330,200));
        paneGauche.add(vueNiveau,BorderLayout.CENTER);
        
        JPanel paneSuperDroite=new JPanel(new GridLayout(2,1));
        paneSuperDroite.add(labelNbPA);
        paneSuperDroite.add(btnTerminerTour);
        
        paneDroite.add(listeVuesAv.get(1).getPanelGeneral(),BorderLayout.NORTH);
        paneSuperDroite.add(btnTerminerTour,BorderLayout.SOUTH);
        paneDroite.add(listeVuesAv.get(2).getPanelGeneral(),BorderLayout.SOUTH);
        paneDroite.setPreferredSize(new Dimension(330, 200));
        paneDroite.add(paneSuperDroite,BorderLayout.CENTER);
        
        
        
        panelPrincipal.add(paneGauche, BorderLayout.WEST);
        panelPrincipal.add(paneDroite, BorderLayout.EAST);
        paneDroite.setBackground(myNewBlue);
        paneGauche.setBackground(myNewBlue);
        paneSuperDroite.setBackground(myNewBlue);
        panelCentre.setBackground(myNewBlue);
        panelGrille.setBackground(myNewBlue);
        vueNiveau.setBackground(myNewBlue);
        panelBoutons.setBackground(myNewBlue);
        panelPrincipal.setBackground(myNewBlue);
        
    }
    
    public void setNiveau(int niveau) {
        vueNiveau.setNiveau(niveau);
    }

    public void actualiserVue(String nomJoueur, Role classe, Color couleur, int nombrePA) {

        
        for (Role r: panelAventuriers.keySet()){
            if (r!=classe){
                panelAventuriers.get(r).getPanelGeneral().setBorder(BorderFactory.createLineBorder(new Color(40, 26, 13), 10));
            }
            else {
                panelAventuriers.get(r).getPanelGeneral().setBorder(BorderFactory.createLineBorder(couleur,10));
            }
        }
        
        panelPrincipal.setBorder(BorderFactory.createLineBorder(couleur, 2));


        panelAventuriers.get(classe).getPanelGeneral().setBackground(couleur);
        labelNomJoueur.setText(classe + " ( " + nomJoueur + " ) ");

        panelPlateau.setBorder(new MatteBorder(0,0,2,0,couleur));

        labelNbPA.setText("Nombre d'actions restantes : " + nombrePA);

        
        window.setVisible(true);
    }

    public void activerBouton(Bouton bouton, Boolean b) {
        switch (bouton) {
            case DEPLACER:
                btnBouger.setEnabled(b);
                break;
            case ASSECHER:
                btnAssecher.setEnabled(b);
                break;
            case DONNER:
                btnDonner.setEnabled(b);
                break;
            case RECUPERER:
                btnRecuperer.setEnabled(b);
                break;
            case TERMINER_TOUR:
                btnTerminerTour.setEnabled(b);
        }
    }
    
     /**
     * Ferme la fenêtre
     */
    public void close() {
        getWindow().dispose();
    }


    //Getters et Setters :
 
    /**
     * @return the window
     */
    public JFrame getWindow() {
        return window;
    }

    /**
     * @return the panelAventuriers
     */
    public HashMap<Role, VueAventurier> getPanelAventuriers() {
        return panelAventuriers;
    }

}
