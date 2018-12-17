/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.aventurier;

import java.awt.Color;

/**
 *
 * @author vinetg
 */
        
        public enum Pion{
    
        ROUGE( new Color(142, 7, 14)),
        VERT( new Color(7, 62, 20)),
        BLEU( new Color(9, 65, 127)),
        GRIS( new Color(160, 160, 162)),
        NOIR(new Color(45, 45, 45)),
        JAUNE( new Color(230, 223, 22)) ;
         
       private Color couleur;
       
       Pion (Color couleur) {
  
            this.couleur = couleur ;
        }


        public Color getCouleur() {
            return this.couleur ;
        }
}

        
    

