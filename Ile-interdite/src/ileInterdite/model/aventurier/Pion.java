/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.model.aventurier;

import java.awt.Color;

/**
 *
 * @author vinetg
 */
        
public enum Pion{
    
    ROUGE(new Color(192, 57, 64)),
    VERT(new Color(57, 112, 70)),
    BLEU(new Color(59, 115, 177)),
    GRIS(new Color(210, 210, 212)),
    NOIR(new Color(105, 105, 105)),
    JAUNE(new Color(250, 243, 42));
         
    private Color couleur;
       
    Pion (Color couleur) {
        this.couleur = couleur ;
    }


    public Color getCouleur() {
        return this.couleur ;
    }
}

        
    

