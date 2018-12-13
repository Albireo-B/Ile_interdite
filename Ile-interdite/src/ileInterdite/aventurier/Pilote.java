/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.aventurier;

import ileInterdite.Grille;
import ileInterdite.Tuile;
import ileInterdite.controleur.utilitaires.Utils;
import java.util.ArrayList;

/**
 *
 * @author vinetg
 */
public class Pilote extends Aventurier {
    
    private final String caseDepart = "Heliport";
    
    /**
     * On définit le constructeur de Pilote avec une tuile Tuile et nom String
     * @param tuile
     * @param nom
     */ 
    public Pilote(String nom){
      super(nom);   
      setClasse("Pilote");
      setPion(Utils.Pion.BLEU);
    }
    /**
     * On définit le constructeur de Pilote avec une tuile Tuile et nom String
     * @param tuile
     * @param nom
     */ 
    public Pilote(Tuile tuile,String nom){
      super(tuile,nom);   
      setClasse("Pilote");
      setPion(Utils.Pion.BLEU);
    }

    /**
     * On passe le pouvoir du Pilote à utilisé et on change sa position
     * @param g
     * @param t
     */ 
    public void setPositionPilote(Grille g, Tuile t) {

        if (g.tuilesAdjacentesCroix(t).contains(t)){
            setPouvoir(false);
        } else {
            setPouvoir(true);
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
        return g.tuilesNonCoulees();
    }
    
    @Override
    public String getCaseDepart() {
        return caseDepart;
    }
}
