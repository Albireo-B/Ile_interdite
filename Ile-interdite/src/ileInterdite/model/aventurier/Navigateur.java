/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.model.aventurier;

import utilitaires.Role;
import utilitaires.Pion;
import ileInterdite.model.Tuile;

/**
 *
 * @author vinetg
 */
public class Navigateur extends Aventurier {
    
    /**
     * On définit un constructeur de Navigateur avec une Tuile tuile et un nom String
     * @param tuile
     * @param nom 
     */
    public Navigateur(String nom, Tuile tuile){
       super(nom,tuile);   
       setRole(Role.Navigateur);
       setPion(Pion.JAUNE);
    }
    

}
