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
public class Explorateur extends Aventurier {
    
    /**
     * On définit le constructeur de Explorateur avec une tuile Tuile et un nom String 
     * @param tuile
     * @param nom 
     */
    public Explorateur(Tuile tuile,String nom){
       super(tuile,nom);   
       setClasse("Explorateur");
       setPion(Pion.VERT);
    }
    
    /**
     * On renvoie une liste des tuiles adjacentes en carré
     * @param g
     * @return 
     */    
    @Override
    public ArrayList<Tuile> calculDeplacement(Grille g){
        return g.tuilesAdjacentesCarre(getTuile());
    }
    
    /**
     * On renvoie une liste des tuiles adjacentes en carré
     * @param g
     * @return 
     */
    @Override
      public ArrayList<Tuile> calculAssechement(Grille g){
        return g.tuilesAdjacentesCarre(getTuile());
        
    }
}
