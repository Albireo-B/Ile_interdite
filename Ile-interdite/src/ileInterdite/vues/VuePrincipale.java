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
    private JButton btnTerminerTour = new JButton("Terminer Tour");
    private JLabel labelNbPA = new JLabel();
    private JLabel labelNomJoueur = new JLabel("", SwingConstants.CENTER);
    private JPanel panelBoutons;

    private String path = "src/images/";

    private ImageIcon imgNiveau = new ImageIcon(new ImageIcon(path + "Niveau.png").getImage().getScaledInstance(130, 400, Image.SCALE_DEFAULT));

    /**
     * On définit un constructeur de VueAventurier avec une VueGrille v
     *
     * @param v
     * @param vuesAventuriers
     */
    public VuePrincipale(VueGrille v, HashMap<Role, VueAventurier> vuesAventuriers) {
        this.btnRecuperer = new JButton(new ImageIcon(path+"icones/iconGet.png"));
        this.btnDonner = new JButton(new ImageIcon(path+"icones/iconGive.png"));
        this.btnAssecher = new JButton(new ImageIcon(path+"icones/iconDry.png"));
        this.btnBouger = new JButton(new ImageIcon(path+"icones/iconMove.png"));
        
        window = new JFrame();
        window.setSize(1600, 800);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setTitle("Ile interdite");
        window.add(panelPrincipal);

        JPanel panelCentre = new JPanel(new BorderLayout());
        panelPlateau.add(v.getPanelGrille(), BorderLayout.CENTER);
       

        labelNomJoueur.setForeground(Color.WHITE);

        JPanel paneGauche = new JPanel(new BorderLayout());

        JPanel paneDroite = new JPanel(new BorderLayout());

        panelPrincipal.add(paneGauche, BorderLayout.WEST);
        panelPrincipal.add(paneDroite, BorderLayout.EAST);
        

        panelPrincipal.add(panelCentre, BorderLayout.CENTER);

        panelCentre.add(panelPlateau, BorderLayout.CENTER);
        //=====================================================================
        panelBoutons = new JPanel(new GridLayout(3, 2));

        panelBoutons.add(btnDonner);
        panelBoutons.add(btnRecuperer);
        panelBoutons.add(btnBouger);
        panelBoutons.add(btnAssecher);
        panelBoutons.add(btnTerminerTour);
        panelBoutons.add(labelNbPA);

        panelPlateau.add(new JLabel(imgNiveau), BorderLayout.EAST);

        panelPlateau.add(panelBoutons, BorderLayout.SOUTH);

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
        paneGauche.add(listeVuesAv.get(3).getPanelGeneral(),BorderLayout.SOUTH);
        paneGauche.setPreferredSize(new Dimension(330,200));

        paneDroite.add(listeVuesAv.get(1).getPanelGeneral(),BorderLayout.NORTH);
        paneDroite.add(listeVuesAv.get(2).getPanelGeneral(),BorderLayout.SOUTH);
        paneDroite.setPreferredSize(new Dimension(330, 200));
    }

    public void actualiserVue(String nomJoueur, Role classe, Color couleur, int nombrePA) {
        for (Role roleVueAventurier : panelAventuriers.keySet()) {
            panelAventuriers.get(roleVueAventurier).getPanelGeneral().setBorder(BorderFactory.createLineBorder(Color.WHITE));
        }
        if (nombrePA == 0) {
            getBtnBouger().setVisible(false);
        } else {
            getBtnBouger().setVisible(true);
        }

        getPanelPrincipal().setBorder(BorderFactory.createLineBorder(couleur, 2));

        getPanelAventuriers().get(classe).getPanelGeneral().setBackground(couleur);
        getLabelNomJoueur().setText(classe + " ( " + nomJoueur + " ) ");

        panelPlateau.setBorder(new MatteBorder(0, 0, 2, 0, couleur));

        getLabelNbPA().setText("Nombre d'actions restantes : " + nombrePA);

        panelAventuriers.get(classe).getPanelGeneral().setBorder(BorderFactory.createLineBorder(Color.MAGENTA,10));
        getWindow().setVisible(true);
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
    public HashMap<Role, VueAventurier> getPanelAventuriers() {
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
