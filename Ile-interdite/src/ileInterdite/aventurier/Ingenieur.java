/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.aventurier;

import ileInterdite.Grille;
import ileInterdite.Tuile;
import ileInterdite.controleur.utilitaires.Utils;
import java.util.ArrayList;

/**
 *
 * @author vinetg
 */
public class Ingenieur extends Aventurier {
    
    /**
     * On définit le constructeur de Ingenieur avec une tuile Tuile et un nom String
     * @param tuile
     * @param nom 
     */
    public Ingenieur(Tuile tuile,String nom){
       super(tuile,nom);   
       setClasse("Ingenieur");
       setPion(Utils.Pion.ROUGE);
    }
   
    /**
     * On renvoie une liste des tuiles adjacentes en croix
     * @param g
     * @return 
     */
    @Override
      public ArrayList<Tuile> calculAssechement(Grille g){
        return g.tuilesAdjacentesCroix(getTuile());
        
    }
}
