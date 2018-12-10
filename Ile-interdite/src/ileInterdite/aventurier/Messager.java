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
public class Messager extends Aventurier {
    
     public Messager(Tuile tuile,String nom,String classe){
       super(tuile,nom);   
       setClasse(classe);
    }
}
