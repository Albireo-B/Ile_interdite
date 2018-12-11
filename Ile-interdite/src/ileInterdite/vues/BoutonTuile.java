/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import java.awt.Button;
import java.util.ArrayList;

/**
 *
 * @author grosa
 */
public class BoutonTuile extends Button {
    private String nom;
    private ArrayList<String> joueurs = new ArrayList();
    
    public BoutonTuile(String nom) {
        super();
        this.setNom(nom);
    }
    
    private void updateText() {
        String text = "["+this.nom+"]";
        for (String j : joueurs) {
            text += "\n - "+j;
        }
        super.setLabel(text);
    }
    
    public void removeAventurier(String j) {
        if (joueurs.contains(j))
            joueurs.remove(j);
        updateText();
    }
    
    public void addAventurier(String j) {
        joueurs.add(j);
        updateText();
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
        updateText();
    }
}
