/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.aventurier;

import ileInterdite.Grille;
import ileInterdite.Tuile;
import java.util.ArrayList;

/**
 *
 * @author vinetg
 */
public abstract class Aventurier {
    private int nbAction;
    private Tuile tuile;
    private Boolean pouvoir;

    public Aventurier(int nbAction,Tuile tuile){
       this.nbAction=nbAction;
        setTuile(tuile);        
    }
    
      
    
    public ArrayList<Tuile> calculDeplacement(Grille g){

        return g.tuilesAdjacentesCroix(getTuile());
        
    }
    
    public ArrayList<Tuile> calculAssechement(Grille g){
/* pblm fction retourne tuile[]*/
        return g.tuilesAdjacentesCroix(getTuile());
        
    }
    
    
    public void decremente(){
        this.nbAction=getNbAction()-1;
    }
    
    
    public void setPositionPilote(Grille g,Tuile t){
       int compteTuile=0;
       for (Tuile tuile : g.tuilesAdjacentesCroix(t)) {
           if (t!=tuile){
              compteTuile++;
           }
       }
        if (compteTuile==4) {
           setPouvoir(false);
       } else {
           setPouvoir(true);
       }
       setTuile(t);       
    }
    
    
    public Tuile getTuile(){
        return tuile;
    }
    public void setTuile(Tuile tuile){
        getTuile().removeAventurier(this);
        tuile.addAventurier(this);
    }
    public int getNbAction() {
        return nbAction;
    }

    /**
     * @return the pouvoir
     */
    public Boolean getPouvoir() {
        return pouvoir;
    }

    /**
     * @param pouvoir the pouvoir to set
     */
    public void setPouvoir(Boolean pouvoir) {
        this.pouvoir = pouvoir;
    }
}
