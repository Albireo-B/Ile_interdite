/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.aventurier;

import java.util.ArrayList;

/**
 *
 * @author vinetg
 */
public abstract class Aventurier {
    private int nbAction;
    private Tuile position;

    public Aventurier(int nbAction,Tuile position){
        setNbAction(nbAction);
        setPosition(position);        
    }
    
      
    
    public ArrayList<Tuile> calculDeplacable(Grille g){
        Tuile t=getPosition();
        return g.casesAdjacentes(t);
        
    }
    
    public ArrayList<Tuile> calculAssechable(Grille g){
        Tuile t=getPosition();
        return g.casesAdjacentes(t);
        
    }
    
    
    /**
     * @return the nbAction
     */
    public int getNbAction() {
        return nbAction;
    }

    /**
     * @param nbAction the nbAction to set
     */
    public void setNbAction(int nbAction) {
        this.nbAction = nbAction;
    }
    
    public void decremente(){
        setNbAction(getNbAction()-1);
    }
}
