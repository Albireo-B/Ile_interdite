/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitaires;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author vinetg
 */
public enum Pion {
    
    ROUGE(new Color(172, 37, 44), new ImageIcon(new ImageIcon("src/images/pions/pionRouge.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH))),
    VERT(new Color(37, 92, 50), new ImageIcon(new ImageIcon("src/images/pions/pionVert.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH))),
    BLEU(new Color(0, 163, 204), new ImageIcon(new ImageIcon("src/images/pions/pionBleu.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH))),
    GRIS(new Color(200, 200, 202), new ImageIcon(new ImageIcon("src/images/pions/pionGris.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH))),
    NOIR(new Color(95, 95, 95), new ImageIcon(new ImageIcon("src/images/pions/pionNoir.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH))),
    JAUNE(new Color(230, 223, 22), new ImageIcon(new ImageIcon("src/images/pions/pionJaune.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

    private ImageIcon image;
    private Color couleur;

    Pion(Color couleur, ImageIcon image) {
        this.image = image;
        this.couleur = couleur;
    }

    public ImageIcon getImage() {
        return this.image;
    }
    
    public Color getCouleur() {
        return couleur;
    }
}
