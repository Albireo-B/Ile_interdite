/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.aventurier;

import ileInterdite.Tuile;
import ileInterdite.controleur.utilitaires.Utils;

/**
 *
 * @author vinetg
 */
public class Navigateur extends Aventurier {
    
    private final String caseDepart = "La Porte d’Or";
    
    /**
     * On définit un constructeur de Navigateur avec une Tuile tuile et un nom String
     * @param tuile
     * @param nom 
     */
    public Navigateur(String nom){
       super(nom);   
       setClasse("Navigateur");
       setPion(Utils.Pion.JAUNE);
    }
    /**
     * On définit un constructeur de Navigateur avec une Tuile tuile et un nom String
     * @param tuile
     * @param nom 
     */
    public Navigateur(Tuile tuile,String nom){
       super(tuile,nom);   
       setClasse("Navigateur");
       setPion(Utils.Pion.JAUNE);
    }
    
    @Override
    public String getCaseDepart() {
        return caseDepart;
    }
}
