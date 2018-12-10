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
public class Pilote extends Aventurier {
    
      public Pilote(Tuile tuile,String nom){
       super(tuile,nom);   
       setClasse("Pilote");
    }

    public void setPositionPilote(Grille g, Tuile t) {

        if (g.tuilesAdjacentesCroix(t).contains(t)){
            setPouvoir(false);
        } else {
            setPouvoir(true);
        }
        setTuile(t);
    }
        
    @Override
    public ArrayList<Tuile> calculDeplacement(Grille g){
        return g.tuilesSeches();
    }
    
   
}
