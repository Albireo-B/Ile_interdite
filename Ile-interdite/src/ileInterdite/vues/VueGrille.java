/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;


import ileInterdite.EtatTuile;
import ileInterdite.Grille;
import ileInterdite.Position;
import ileInterdite.actions.Action;
import ileInterdite.message.MessagePos;
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
    private HashMap<String, Position> aventuriers = new HashMap();
    
    /**
     * On définit le constructeur de VueGrille
     * @param positions
     * @param noms
     * @param classesAventuriers
     */
    public VueGrille(ArrayList<Position> positions, ArrayList<String> noms, ArrayList<String> classesAventuriers) {
        
        for(String av : classesAventuriers) {
            aventuriers.put(av, new Position(2, 0));
        }
        
        panelGrille = new JPanel(new GridLayout(6, 6));
        
        ArrayList<Position> positionTuiles = Grille.getAllTilesPositions();
        
        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 6 ; x++) {
                Position pos = new Position(x, y);
                if (positionTuiles.contains(pos)) {
                    if (positions.contains(pos)) {
                        String nom = noms.get(positions.indexOf(pos));
                        bTuiles.put(pos, new BoutonTuile(nom));
                    
                        panelGrille.add(bTuiles.get(pos));
                    }
                    else {
                        System.out.println("Il vous manque une case ou quoi?");
                        panelGrille.add(new JPanel());
                    }
                }
                else {
                    panelGrille.add(new JPanel());
                }
            }
        }
    }
    
    /**
     * 
     */
    public void tousBoutonsInertes() {
        for (BoutonTuile bouton : bTuiles.values()) {
            for (ActionListener ac : bouton.getActionListeners()) {
                bouton.removeActionListener(ac);
                bouton.setForeground(Color.BLACK);
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
                bouton.setForeground(Color.RED);
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
                bouton.setEnabled(false);
                bouton.setBackground(Color.BLACK);
            case SECHE:
                bouton.setEnabled(true);
                bouton.setBackground(Color.LIGHT_GRAY);
            case INONDEE:
                bouton.setEnabled(true);
                bouton.setBackground(Color.CYAN);
        }
    }

     /**
     * @param position
     * @param aventurier
     */
    public void actualiserPositionJoueur(Position position, String aventurier) {
        Position posAv = aventuriers.get(aventurier);
        bTuiles.get(posAv).removeAventurier(aventurier);
        bTuiles.get(position).addAventurier(aventurier);
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


