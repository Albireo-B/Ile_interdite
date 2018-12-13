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
public class Messager extends Aventurier {
    
    private final String caseDepart = "La Porte d’Argent";
    
    /**
     * On définit le constructeur de Messager avec une Tuile tuile et un string nom 
     * @param tuile
     * @param nom
     */
     public Messager(Tuile tuile,String nom){
       super(tuile,nom);   
       setClasse("Messager");
       setPion(Utils.Pion.ORANGE);
    }
     
    @Override
    public String getCaseDepart() {
        return caseDepart;
    }
}
