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
       this.nbAction=nbAction;
        setPosition(position);        
    }
    
      
    
    public ArrayList<Tuile> calculDeplacement(Grille g){

        return g.TuilesAdjacentes(getPosition());
        
    }
    
    public ArrayList<Tuile> calculAssechement(Grille g){

        return g.TuilesAdjacentes(getPosition());
        
    }
    
    
    public void decremente(){
        this.nbAction=getNbAction()-1);
    }
    
    
    public Tuile getPosition(){
        return position;
    }
    public void setPosition(Tuile position){
        this.position=position;
    }
    public int getNbAction() {
        return nbAction;
    }
}
