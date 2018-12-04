/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite;

import java.util.ArrayList;

/**
 *
 * @author grosa
 */
public class Tuile {
    private EtatCase etat = EtatCase.SECHE;
    private ArrayList<Aventurier> aventuriers;
    public Position position;
    
    public Tuile() {
        
    }
    
    public void setEtat(EtatCase etat) {
        this.etat = etat;
    }
    
    public void addAventurier(Aventurier av) {
        aventuriers.add(av);
    }
    
    public void removeAventurier(Aventurier av) {
        aventuriers.remove(av);
    }
}
