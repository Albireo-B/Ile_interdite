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
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author vinetg
 */
public class VueGrille extends Observable {
   
    private JPanel panelGrille;
    private HashMap<Position, JButton> bTuiles = new HashMap();
    
    /**
     * On définit le constructeur de VueGrille sans paramètres
     */
    public VueGrille(/*ArrayList<Tuile> tuiles*/) {
        panelGrille = new JPanel(new GridLayout(6, 6));
        
        ArrayList<Position> positionTuiles = Grille.getAllTilesPositions();
        
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6 ; y++) {
                Position pos = new Position(x, y);
                if (positionTuiles.contains(pos)) {
                    bTuiles.put(pos, new JButton());
                    
                    panelGrille.add(bTuiles.get(pos));
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
        getbTuiles().values().forEach((bouton) -> {
            for (ActionListener ac : bouton.getActionListeners()) {
                bouton.removeActionListener(ac);
            }
        });
    }
    
    /**
     * Rend toutes les positions de la liste cliquables 
     * @param posBoutons
     * @param act
     */
    public void rendreBoutonsCliquable(ArrayList<Position> posBoutons, Action act) {
        for (Position pos : posBoutons) {
            if (getbTuiles().keySet().contains(pos)) {
                JButton bouton = getbTuiles().get(pos);
                
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
    public void changerEtat(EtatTuile etat, Position pos) {
        JButton bouton = getbTuiles().get(pos);
        
        switch (etat) {
            case COULEE:
                bouton.setEnabled(false);
            case SECHE:
                bouton.setEnabled(true);
                bouton.setBackground(Color.LIGHT_GRAY);
            case INONDEE:
                bouton.setEnabled(true);
                bouton.setBackground(Color.CYAN);
        }
    }
    
    
    //Getters et Setters :
    
    
     /**
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

    /**
     * @return the bTuiles
     */
    public HashMap<Position, JButton> getbTuiles() {
        return bTuiles;
    }

    /**
     * @param bTuiles the bTuiles to set
     */
    public void setbTuiles(HashMap<Position, JButton> bTuiles) {
        this.bTuiles = bTuiles;
    }
}


