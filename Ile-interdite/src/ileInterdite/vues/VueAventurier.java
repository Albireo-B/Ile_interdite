/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import ileInterdite.message.MessageCarte;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import utilitaires.Action;
import utilitaires.Role;

/**
 *
 * @author grosa
 */
public class VueAventurier extends Observable {

    private JPanel paneClass;
    private IAventurier carteJoueur;
    private JPanel panelGeneral;
    private ArrayList<ICarte> buttonCartes;
    private Role roleAventurier;
    private String pathPerso = "src/images/personnages/";
    private String pathCartes = "src/images/cartes/";

    public VueAventurier(Role roleAventurier, boolean gauche) {

        panelGeneral = new JPanel(new BorderLayout());

        this.roleAventurier = roleAventurier;

        //====================== principal========================
        JPanel panelPrincipal = new JPanel(new GridLayout(2, 3));

        //===================pannel en haut avec les button et la classe====
        paneClass = new JPanel(new BorderLayout());
        
        switch (roleAventurier){
            case Explorateur:
                 carteJoueur = new IAventurier(new JButton(new ImageIcon(pathPerso+"explorateur.png")), roleAventurier);
                 break;
            case Ingénieur:
                 carteJoueur = new IAventurier(new JButton(new ImageIcon(pathPerso+"ingenieur.png")), roleAventurier);
                 break;
                 
            case Messager:
                 carteJoueur = new IAventurier(new JButton(new ImageIcon(pathPerso+"messager.png")), roleAventurier);
                 break;
            case Navigateur:
                 carteJoueur = new IAventurier(new JButton(new ImageIcon(pathPerso+"navigateur.png")), roleAventurier);
                 break;
            case Pilote:
                 carteJoueur = new IAventurier(new JButton(new ImageIcon(pathPerso+"pilote.png")), roleAventurier);
                 break;
            case Plongeur:
                 carteJoueur = new IAventurier(new JButton(new ImageIcon(pathPerso+"plongeur.png")), roleAventurier);
                 break;
        }
       

        buttonCartes = new ArrayList<>();

        //=============================================
        paneClass.add(carteJoueur.getBoutonAventurier());

        for (int i = 0; i < 5; i++) {
            if ((i == 0 && !gauche) || (i == 2 && gauche)) {
                panelPrincipal.add(paneClass);
            }
            buttonCartes.add(new ICarte("_", Action.DONNER));
            panelPrincipal.add(buttonCartes.get(i));

        }
        
        panelGeneral.add(panelPrincipal);
    }

    
    public void actualiserVueAventurier(ArrayList<String> listeCarte) {
        int j = 0;
        for (int i = 0; i < 5 && i < listeCarte.size(); i++) {
            getButtonCartes().get(i).setNom(listeCarte.get(i));
            getButtonCartes().get(i).removeActionListener();                
            j = i;
            if (buttonCartes.get(i).getNom().equals("Helicoptere") ){
                buttonCartes.get(i).addActionListener((ActionEvent arg0) -> {
                    setChanged();
                    notifyObservers(new MessageCarte("Helicoptere",Action.CARTESPECIALE,roleAventurier));
                    clearChanged();
                });
            }else if (buttonCartes.get(i).getNom().equals("SacDeSable")){
                buttonCartes.get(i).addActionListener((ActionEvent arg0) -> {
                    setChanged();
                    notifyObservers(new MessageCarte("SacDeSable",Action.CARTESPECIALE,roleAventurier));
                    clearChanged();
                });
            }
        }
        for (int i = j + 1; i < 5; i++) {
            getButtonCartes().get(i).setNom("_");
            getButtonCartes().get(i).removeActionListener();
        }

    }

    public void rendreCartesCliquables(ArrayList<Integer> listePos) {
        for (Integer carteCliquable : listePos) {
            ICarte carte = buttonCartes.get(carteCliquable);
            carte.addActionListener((ActionEvent e) -> {
                setChanged();
                notifyObservers(carte.getMessage(roleAventurier));
                clearChanged();
            });
        }
    }

    public void devenirReceveur(String carte) {
        carteJoueur.devenirReceveur(carte);
    }

    public void devenirSuiveur(boolean suivre) {
        carteJoueur.devenirSuiveur(suivre);
    }

    //Getters et Setters :
    /**
     * @return the nomAventurier
     */
    public Role getRoleAventurier() {
        return roleAventurier;
    }

    /**
     * @param roleAventurier
     */
    public void setRoleAventurier(Role roleAventurier) {
        this.roleAventurier = roleAventurier;
    }

    public ArrayList<ICarte> getButtonCartes() {
        return buttonCartes;
    }

    /**
     * @param paneClass the paneClass to set
     */
    public void setPaneClass(JPanel paneClass) {
        this.paneClass = paneClass;
    }

    public void setButtonCartes(ArrayList<ICarte> buttonCartes) {
        this.buttonCartes = buttonCartes;
    }

    /**
     * @return the paneClass
     */
    public JPanel getPaneClass() {
        return paneClass;
    }

    /**
     * @return the carteJoueur
     */
    public IAventurier getCarteJoueur() {
        return carteJoueur;
    }

    /**
     * @return the panelGeneral
     */
    public JPanel getPanelGeneral() {
        return panelGeneral;
    }

}
