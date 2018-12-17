/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.model.aventurier;

import ileInterdite.model.Grille;
import ileInterdite.model.Tuile;
import java.util.ArrayList;

/**
 *
 * @author vinetg
 */
public class Plongeur extends Aventurier {

    /**
     * On définit le constructeur de Plongeur avec une tuile Tuile et un nom String
     * @param tuile
     * @param nom 
     */
    public Plongeur(String nom,Tuile tuile){
       super(nom,tuile);
       setRole(Role.Plongeur);
       setPion(Pion.NOIR);
    }
        
    /**
     * Renvoie une liste de tuiles où peut aller le plongeur
     * @param g
     * @return 
     */  
    @Override
    public ArrayList<Tuile> calculDeplacement(Grille g){
        return g.tuilesAccessiblesPlongeur(getTuile());
    }
}
