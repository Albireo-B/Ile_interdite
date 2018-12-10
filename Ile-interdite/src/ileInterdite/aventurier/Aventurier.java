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
        this.setNbAction(getNbAction()-1);
    }
    
    

    
    
    public Tuile getTuile(){
        return tuile;
    }
    public void setTuile(Tuile tuile){
        getTuile().removeAventurier(this);
        tuile.addAventurier(this);
        this.decremente();
    }
    public int getNbAction() {
        return nbAction;
    }

    
    /**
     * @param nbAction the nbAction to set
     */
    public void setNbAction(int nbAction) {
        this.nbAction = nbAction;
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
