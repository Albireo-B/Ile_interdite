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
    
     public Messager(Tuile tuile,String nom){
       super(tuile,nom);   
       setClasse("Messager");
       setPion(Utils.Pion.ORANGE);
    }
}
