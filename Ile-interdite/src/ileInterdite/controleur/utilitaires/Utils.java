/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.controleur.utilitaires;

import ileInterdite.aventurier.Aventurier;
import java.awt.Color;
import java.security.Policy.Parameters;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

/**
 *
 * @author vinetg
 */
public class Utils {

    public static enum Pion {
        ROUGE( new Color(255, 0, 0)),
        VERT( new Color(0, 195, 0)),
        BLEU( new Color(55,194,198)),
        ORANGE( new Color(255, 148, 0)),
        VIOLET(new Color(204, 94, 255)),
        JAUNE( new Color(255, 255, 0)) ;    


        private final Color couleur ;


        Pion ( Color couleur) {
  
            this.couleur = couleur ;
        }
   


        public Color getCouleur() {
            return this.couleur ;
        }
        
    }

    /*public static ArrayList<Aventurier> melangerAventuriers(ArrayList<Aventurier> arrayList) {
        if (Parameters.ALEAS) {
            Collections.shuffle(arrayList);
        }
        return arrayList ;
    }*/
    
    /**
     * Permet de poser une question à laquelle l'utilisateur répond par oui ou non
     * @param question texte à afficher
     * @return true si l'utilisateur répond oui, false sinon
     */
    public static Boolean poserQuestion(String question) {
        System.out.println("Divers.poserQuestion(" + question + ")");
        int reponse = JOptionPane.showConfirmDialog (null, question, "", JOptionPane.YES_NO_OPTION) ;
        System.out.println("\tréponse : " + (reponse == JOptionPane.YES_OPTION ? "Oui" : "Non"));
        return reponse == JOptionPane.YES_OPTION;
    }    
    
    /**
     * Permet d'afficher un message d'information avec un bouton OK
     * @param message Message à afficher 
     */
    public static void afficherInformation(String message) {
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.OK_OPTION);
    }
}
