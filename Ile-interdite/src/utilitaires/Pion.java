/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitaires;

import java.awt.Color;

/**
 *
 * @author vinetg
 */
public enum Pion {

    ROUGE(new Color(172, 37, 44)),
    VERT(new Color(37, 92, 50)),
    BLEU(new Color(0, 163, 204)),
    GRIS(new Color(200, 200, 202)),
    NOIR(new Color(95, 95, 95)),
    JAUNE(new Color(230, 223, 22));

    private Color couleur;

    Pion(Color couleur) {
        this.couleur = couleur;
    }

    public Color getCouleur() {
        return this.couleur;
    }
}
