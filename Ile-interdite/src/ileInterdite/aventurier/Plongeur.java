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
public class Plongeur extends Aventurier{
 
      public Plongeur(Tuile tuile,String nom){
       super(tuile,nom);   
       setClasse("Plongeur");
    }
        
    @Override
    public ArrayList<Tuile> calculDeplacement(Grille g){
        return g.tuilesAccessiblesPlongeur(getTuile());
    }
    
}
