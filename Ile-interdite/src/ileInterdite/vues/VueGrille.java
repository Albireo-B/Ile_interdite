/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import ileInterdite.EtatTuile;
import ileInterdite.Grille;
import ileInterdite.Position;
import ileInterdite.Tuile;
import ileInterdite.actions.Action;
import ileInterdite.message.MessagePos;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
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
    
    // Remplit la grille de boutons
    public VueGrille() {
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
    
    public void tousBoutonsInertes() {
        for (JButton bouton : bTuiles.values()) {
            for (ActionListener ac : bouton.getActionListeners()) {
                bouton.removeActionListener(ac);
            }
        }
    }
    
    // Rends tous les boutons avec cette position cliquables, ils jetterons l'action act
    public void rendreBoutonsCliquable(ArrayList<Position> posBoutons, Action act) {
        for (Position pos : posBoutons) {
            if (bTuiles.keySet().contains(pos)) {
                JButton bouton = bTuiles.get(pos);
                    
                bouton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setChanged();
                        notifyObservers(new MessagePos(act, pos));
                        clearChanged();
                    }
                });
            }
        }
    }
    
    public void setEtat(EtatTuile etat, Position pos) {
        JButton bouton = bTuiles.get(pos);
        
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
    
    public JPanel getPanelGrille() {
        return panelGrille;
    }
}
