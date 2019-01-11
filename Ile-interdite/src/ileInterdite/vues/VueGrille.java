/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;


import utilitaires.EtatTuile;
import ileInterdite.model.Grille;
import ileInterdite.model.Position;
import utilitaires.Action;
import utilitaires.Pion;
import ileInterdite.message.MessagePos;
import utilitaires.Role;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JPanel;

/**
 *
 * @author vinetg
 */
public class VueGrille extends Observable {
   
    private JPanel panelGrille;
    private HashMap<Position, BoutonTuile> bTuiles = new HashMap();
    private Color myBlue = new Color(30, 73, 158);
    private Color myCyan = new Color(20, 136, 148);
    private Color myRed = new Color(255, 77, 77);
    
    private Color myBackgroundColor = new Color(12, 143, 181);
    
    /**
     * On définit le constructeur de VueGrille
     * @param positions
     * @param noms
     */
    public VueGrille(ArrayList<Position> positions, ArrayList<String> noms) {
        panelGrille = new JPanel(new GridLayout(6, 6));
        panelGrille.setBackground(myBackgroundColor);
        ArrayList<Position> positionTuiles = Grille.getAllTilesPositions();
        
        for (int y = 0; y < 6; y++) {
            for (int x = 5; x >-1 ; x--) {
                Position pos = new Position(x, y);
                if (positionTuiles.contains(pos)) {
                    if (positions.contains(pos)) {
                        String nom = noms.get(positions.indexOf(pos));
                        BoutonTuile bouton = new BoutonTuile(nom);
                        bTuiles.put(pos, bouton);
                        bouton.setButtonBackground(Color.WHITE);
                        panelGrille.add(bouton);
                    }
                    else {
                        System.out.println("Il vous manque une case ou quoi?");
                        JPanel panel = new JPanel();
                        panel.setBackground(myRed);
                        panelGrille.add(panel);
                    }
                }
                else {
                    JPanel panel = new JPanel();
                    panel.setBackground(myBackgroundColor);
                    panelGrille.add(panel);
                }
            }
        }
    }
    
    /**
     * 
     */
    public void tousBoutonsInertes() {
        for (BoutonTuile bouton : bTuiles.values()) {
            for (ActionListener ac : bouton.getBouton().getActionListeners()) {
                bouton.removeActionListener(ac);
                bouton.resetForeground();
            }
        }
    }
    
    /**
     * Rend toutes les positions de la liste cliquables 
     * @param posBoutons
     * @param act
     */
    public void actualiserBoutonsCliquables(ArrayList<Position> posBoutons, Action act) {
        for (Position pos : posBoutons) {
            if (bTuiles.keySet().contains(pos)) {
                BoutonTuile bouton = bTuiles.get(pos);

                bouton.getBouton().setForeground(myRed);
                
                bouton.addActionListener((ActionEvent e) -> {
                    setChanged();
                    notifyObservers(new MessagePos(act, pos));
                    clearChanged();
                });
            }
        }
    }
    
    /**
     * On définit l'état d'une Position (Tuile)
     * @param etat
     * @param pos 
     */
    public void actualiserEtatTuile(Position pos, EtatTuile etat) {
        BoutonTuile bouton = bTuiles.get(pos);
        switch (etat) {
            case COULEE:
                bouton.setButtonEnabled(false);
                bouton.setButtonBackground(myBlue);
                bouton.setButtonForeground(Color.WHITE);
                break;
            case SECHE:
                bouton.setButtonEnabled(true);
                bouton.setButtonForeground(Color.BLACK);
                bouton.setButtonBackground(Color.WHITE);
                break;
            case INONDEE:
                bouton.setButtonEnabled(true);
                bouton.setButtonBackground(myCyan);
                bouton.setButtonForeground(Color.WHITE);
                break;
        }
    }

     /**
     * @param position
     * @param posAv
     * @param p
     */

    public void actualiserPositionJoueur(Position position, Position posAv, Pion p) {
        if (bTuiles.keySet().contains(posAv)) {
            bTuiles.get(posAv).removeAventurier(p.getCouleur());
        }
        bTuiles.get(position).addAventurier(p.getCouleur());
    }
    
    //Getters et Setters :
    
    /**
     * 
     * @return the panelGrille
     */
    public JPanel getPanelGrille() {
        return panelGrille;
    }

    /**
     * @param panelGrille the panelGrille to set
     */
    public void setPanelGrille(JPanel panelGrille) {
        this.panelGrille = panelGrille;
    }
}


