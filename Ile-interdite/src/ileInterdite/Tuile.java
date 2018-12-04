/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite;

import java.util.ArrayList;
import ileInterdite.aventurier.*;

/**
 *
 * @author grosa
 */
public class Tuile {
    private EtatTuile etat = EtatTuile.SECHE;
    private ArrayList<Aventurier> aventuriers;
    public Position position;
    
    public Tuile(Position pos) {
        this.position = pos;
    }
    
    public void setEtat(EtatTuile etat) {
        this.etat = etat;
    }
    
    public EtatTuile getEtat() {
        return etat;
    }
    
    public void addAventurier(Aventurier av) {
        aventuriers.add(av);
    }
    
    public void removeAventurier(Aventurier av) {
        aventuriers.remove(av);
    }
    
    public Position getPosition() {
        return this.position;
    }
}
