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
public class Plongeur extends Aventurier {
    
    private final String caseDepart = "La Porte de Fer";
    
    /**
     * On définit le constructeur de Plongeur avec une tuile Tuile et un nom String
     * @param tuile
     * @param nom 
     */
    public Plongeur(String nom){
       super(nom);   
       setClasse("Plongeur");
       setPion(Utils.Pion.VIOLET);
    }
    /**
     * On définit le constructeur de Plongeur avec une tuile Tuile et un nom String
     * @param tuile
     * @param nom 
     */
    public Plongeur(Tuile tuile,String nom){
       super(tuile,nom);   
       setClasse("Plongeur");
       setPion(Utils.Pion.VIOLET);
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
    
    @Override
    public String getCaseDepart() {
        return caseDepart;
    }
}
