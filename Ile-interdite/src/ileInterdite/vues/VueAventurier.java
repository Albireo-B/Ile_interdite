/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import ileInterdite.message.MessageCarte;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Observable;
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

    public VueAventurier(Role roleAventurier, boolean gauche) {

        panelGeneral = new JPanel(new BorderLayout());

        this.roleAventurier = roleAventurier;

        //====================== principal========================
        JPanel panelPrincipal = new JPanel(new GridLayout(2, 3));

        //===================pannel en haut avec les button et la classe====
        paneClass = new JPanel(new BorderLayout());
        carteJoueur = new IAventurier(new JButton(roleAventurier.toString()), roleAventurier);

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
        while ( j < listeCarte.size() && j<5) {
            getButtonCartes().get(j).setNom(listeCarte.get(j));
            getButtonCartes().get(j).removeActionListener();                //A ROLE IS NEEDED MY BOY
            if (buttonCartes.get(j).getNom().equals("Helicoptere") ){
                buttonCartes.get(j).addActionListener((ActionEvent arg0) -> {
                    setChanged();
                    notifyObservers(new MessageCarte("Helicoptere",Action.CARTESPECIALE,roleAventurier));
                    clearChanged();
                });
            }else if (buttonCartes.get(j).getNom().equals("SacDeSable")){
                buttonCartes.get(j).addActionListener((ActionEvent arg0) -> {
                    setChanged();
                    notifyObservers(new MessageCarte("SacDeSable",Action.CARTESPECIALE,roleAventurier));
                    clearChanged();
                });
            }
            j++;
        }
        for (int i = j ; i < 5; i++) {
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
