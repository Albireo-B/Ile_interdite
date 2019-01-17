/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import ileInterdite.controleur.Controleur;
import ileInterdite.controleur.ControleurInit;
import ileInterdite.message.MessageCarte;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
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
    private String nomJoueur;
    private String pathPerso = "src/images/personnages/";
    private String pathCartes = "src/images/cartes/";
    private JPanel paneNom=new JPanel();
    private int width=100;
    private int height=140;

    public VueAventurier(Role roleAventurier,String nomJoueur, boolean gauche) {

        panelGeneral = new JPanel(new BorderLayout());

        this.roleAventurier = roleAventurier;
        this.nomJoueur=nomJoueur;

        //====================== principal========================
        JPanel panelPrincipal = new JPanel(new GridLayout(2, 3));

        //===================pannel en haut avec les button et la classe====
        paneClass = new JPanel(new BorderLayout());
        
        ImageIcon icone = new ImageIcon(new ImageIcon(pathPerso+ roleAventurier.toString().toLowerCase() + ".png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        carteJoueur = new IAventurier(new JButton(icone), roleAventurier);

        buttonCartes = new ArrayList<>();

        //=============================================
        
        
        paneNom.add(new JLabel(nomJoueur));
        paneNom.setPreferredSize(new Dimension(20,30));
        paneClass.add(paneNom,BorderLayout.NORTH);
        paneClass.add(carteJoueur.getBoutonAventurier(),BorderLayout.CENTER);
 

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
            ICarte carte = buttonCartes.get(j);
            carte.setImage(listeCarte.get(j));
            carte.removeActionListener();                
            if (carte.getNom().equals("Helicoptere") ){
                carte.setAction(Action.CARTESPECIALE);
                carte.addActionListener((ActionEvent arg0) -> {
                    setChanged();
                    notifyObservers(carte.getMessage(roleAventurier));
                    clearChanged();
                });

            }else if (carte.getNom().equals("SacDeSable")){
                carte.setAction(Action.CARTESPECIALE);
                carte.addActionListener((ActionEvent arg0) -> {
                    setChanged();
                    notifyObservers(carte.getMessage(roleAventurier));
                    clearChanged();
                });
            }else{
                carte.setAction(Action.DONNER);
            }
            j++;
        }
        for (int i = j; i < 5; i++) {
            buttonCartes.get(i).setImage(null);
            buttonCartes.get(i).removeActionListener();
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
    
    public void desactiverCartes() {
        for (ICarte carte : buttonCartes) {
            carte.removeActionListener();
        }
    }
    

    //Getters et Setters :
    
    /**
     * @return the nomAventurier
     */
    public Role getRoleAventurier() {
        return roleAventurier;
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
