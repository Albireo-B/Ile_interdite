/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.aventurier;

import java.util.ArrayList;

/**
 *
 * @author vinetg
 */
public class Ingenieur extends Aventurier {
    
   
    @Override
      public ArrayList<Tuile> calculAssechement(Grille g){
        Tuile t=getPosition();
        return g.TuilesAdjacentes(t);
        
    }
}
