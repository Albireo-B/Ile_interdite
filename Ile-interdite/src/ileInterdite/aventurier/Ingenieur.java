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
public class Ingenieur extends Aventurier {
    

    /**
     * On définit le constructeur de Ingenieur avec une tuile Tuile et un nom String
     * @param tuile
     * @param nom 
     */
    public Ingenieur(String nom,Tuile tuile){
       super(nom,tuile);   
       setRole(Role.Ingénieur);
       setPion(Pion.ROUGE);
    }
      
}
