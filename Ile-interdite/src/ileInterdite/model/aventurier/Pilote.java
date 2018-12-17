/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.model.aventurier;

import ileInterdite.model.Grille;
import ileInterdite.model.Tuile;
import java.util.ArrayList;

/**
 *
 * @author vinetg
 */
public class Pilote extends Aventurier {

    /**
     * On définit le constructeur de Pilote avec une tuile Tuile et nom String
     * @param tuile
     * @param nom
     */ 
    public Pilote(String nom,Tuile tuile){
      super(nom,tuile);   
      setRole(Role.Pilote);
      setPion(Pion.BLEU);
    }

    /**
     * On passe le pouvoir du Pilote à utilisé et on change sa position
     * @param g
     * @param t
     */ 
    public void setPositionPilote(Grille g, Tuile t) {

        if (!(g.tuilesAdjacentesCroix(getTuile()).contains(t))){
            setPouvoir(false);
        }
        setTuile(t);
    }
     
    /**
     * On renvoit la liste de toutes les tuiles non-coulées
     * @param g
     * @return 
     */ 
    @Override
    public ArrayList<Tuile> calculDeplacement(Grille g){
        ArrayList<Tuile> liste;
        if (getPouvoir()){
            liste = g.tuilesNonCoulees(getTuile());
        }
        else{
            liste = g.tuilesAdjacentesCroix(getTuile());
        }
        return liste;
    }
    

}