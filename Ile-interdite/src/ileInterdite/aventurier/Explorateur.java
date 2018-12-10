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
    
    public Explorateur(Tuile tuile,String nom,String classe){
       super(tuile,nom);   
       setClasse(classe);
    }
    
        
    @Override
    public ArrayList<Tuile> calculDeplacement(Grille g){
        return g.tuilesAdjacentesCarre(getTuile());
    }
    
    @Override
      public ArrayList<Tuile> calculAssechement(Grille g){
        return g.tuilesAdjacentesCarre(getTuile());
        
    }
}
