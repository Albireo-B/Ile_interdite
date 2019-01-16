/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import ileInterdite.message.MessageCarte;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
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
    private int width=120;
    private int height=200;

    public VueAventurier(Role roleAventurier, boolean gauche) {

        panelGeneral = new JPanel(new BorderLayout());

        this.roleAventurier = roleAventurier;

        //====================== principal========================
        JPanel panelPrincipal = new JPanel(new GridLayout(2, 3));

        //===================pannel en haut avec les button et la classe====
        paneClass = new JPanel(new BorderLayout());
        
       
        switch (roleAventurier){
            case Explorateur:
                ImageIcon iconeAv = new ImageIcon(new ImageIcon(pathPerso+ "explorateur.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
                carteJoueur = new IAventurier(new JButton(iconeAv), roleAventurier);
                break;
            case Ingénieur:
                ImageIcon iconeIng = new ImageIcon(new ImageIcon(pathPerso+ "ingenieur.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
                carteJoueur = new IAventurier(new JButton(iconeIng), roleAventurier);
                break;
                 
            case Messager:
                ImageIcon iconeMess = new ImageIcon(new ImageIcon(pathPerso+ "messager.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
                carteJoueur = new IAventurier(new JButton(iconeMess), roleAventurier);
                break;
            case Navigateur:
                ImageIcon iconeNav = new ImageIcon(new ImageIcon(pathPerso+ "navigateur.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
                carteJoueur = new IAventurier(new JButton(iconeNav), roleAventurier);
                break;
            case Pilote:
                ImageIcon iconePil = new ImageIcon(new ImageIcon(pathPerso+ "pilote.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
                carteJoueur = new IAventurier(new JButton(iconePil), roleAventurier);
                break;
            case Plongeur:
                ImageIcon iconePlong = new ImageIcon(new ImageIcon(pathPerso+ "plongeur.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
                carteJoueur = new IAventurier(new JButton(iconePlong), roleAventurier);
                break;
        }
       

        buttonCartes = new ArrayList<>();

        //=============================================
        paneClass.add(carteJoueur.getBoutonAventurier());

        for (int i = 0; i < 5; i++) {
            if ((i == 0 && !gauche) || (i == 2 && gauche)) {
                panelPrincipal.add(paneClass);
            }
            buttonCartes.add(new ICarte(null, Action.DONNER));
            panelPrincipal.add(buttonCartes.get(i));

        }
        
        panelGeneral.add(panelPrincipal);
    }

    
    public void actualiserVueAventurier(ArrayList<String> listeCarte) {
        int j = 0;

        while ( j < listeCarte.size() && j<5) {
            getButtonCartes().get(j).setImage(listeCarte.get(j));
            getButtonCartes().get(j).removeActionListener();                
            if (buttonCartes.get(j).getNom().equals("Helicoptere") ){
                buttonCartes.get(j).addActionListener((ActionEvent arg0) -> {
                    setChanged();
                    notifyObservers(new MessageCarte("Helicoptere",Action.CARTESPECIALE,roleAventurier));
                    clearChanged();
                });

            }else if (buttonCartes.get(j).getNom().equals("SacsDeSable")){
                buttonCartes.get(j).addActionListener((ActionEvent arg0) -> {

                    setChanged();
                    notifyObservers(new MessageCarte("SacsDeSable",Action.CARTESPECIALE,roleAventurier));
                    clearChanged();
                });
            }
            j++;
        }
        for (int i = j + 1; i < 5; i++) {
            getButtonCartes().get(i).setImage(null);

            getButtonCartes().get(i).removeActionListener();
        }

    }

    public void rescale(ImageIcon image,int resizedWidth,int resizedHeight){
        image.getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_DEFAULT);
        
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

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

}
