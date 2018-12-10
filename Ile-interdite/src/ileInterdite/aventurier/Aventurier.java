/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.aventurier;

import ileInterdite.Grille;
import ileInterdite.Tuile;
import ileInterdite.controleur.utilitaires.Utils.*;
import java.util.ArrayList;

/**
 *
 * @author vinetg
 */
public abstract class Aventurier {
    private int nbAction;
    private Tuile tuile;
    private Boolean pouvoir;
    private String classe;
    private String nomJoueur;
    private Pion pion;

    public Aventurier(Tuile tuile,String nom){
        this.tuile=tuile;   
        setNomJoueur(nom);
        resetPA();
    }
    
      
    
    public ArrayList<Tuile> calculDeplacement(Grille g){

        return g.tuilesAdjacentesCroix(getTuile());
        
    }
    
    public ArrayList<Tuile> calculAssechement(Grille g){
/* pblm fction retourne tuile[]*/
        return g.tuilesAdjacentesCroix(getTuile());
        
    }
    
    public void resetPA(){
        setNbAction(3);
    }
    
    public void decremente(){
        this.setNbAction(getNbAction()-1);
    }
    
    

    
    
    public Tuile getTuile(){
        return tuile;
    }
    public void setTuile(Tuile tuile){
        getTuile().removeAventurier(this);
        tuile.addAventurier(this);
        this.decremente();
    }
    public int getNbAction() {
        return nbAction;
    }

    
    /**
     * @param nbAction the nbAction to set
     */
    public void setNbAction(int nbAction) {
        this.nbAction = nbAction;
    }
    
    
    /**
     * @return the pouvoir
     */
    public Boolean getPouvoir() {
        return pouvoir;
    }

    /**
     * @param pouvoir the pouvoir to set
     */
    public void setPouvoir(Boolean pouvoir) {
        this.pouvoir = pouvoir;
    }

    /**
     * @return the classe
     */
    public String getClasse() {
        return classe;
    }


    /**
     * @return the nomJoueur
     */
    public String getNomJoueur() {
        return nomJoueur;
    }

    /**
     * @param nomJoueur the nomJoueur to set
     */
    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    /**
     * @param classe the classe to set
     */
    public void setClasse(String classe) {
        this.classe = classe;
    }

    /**
     * @return the pion
     */
    public Pion getPion() {
        return pion;
    }

    /**
     * @param pion the pion to set
     */
    public void setPion(Pion pion) {
        this.pion = pion;
    }

}
