/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.aventurier;

import ileInterdite.Tuile;

/**
 *
 * @author vinetg
 */
public class Navigateur extends Aventurier {
    
      public Navigateur(Tuile tuile,String nom){
       super(tuile,nom);   
       setClasse("Navigateur");
    }
}
