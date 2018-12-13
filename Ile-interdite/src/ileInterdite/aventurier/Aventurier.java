/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.aventurier;

import ileInterdite.Grille;
import ileInterdite.Position;
import ileInterdite.Tuile;
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
    
    public Aventurier(String nom) {
        this.tuile=null;   
        setNomJoueur(nom);
        resetPA();
    }
    
    /**
     * On définit le constructeur de Aventurier avec une tuile Tuile et un nom String
     * @param tuile
     * @param nom 
     */
    public Aventurier(Tuile tuile,String nom) {
        this.tuile=tuile;   
        setNomJoueur(nom);
        resetPA();
    }
    
      
    /**
     * On renvoie la liste des tuiles adjacentes en croix
     * @param g
     * @return 
     */
    public ArrayList<Tuile> calculDeplacement(Grille g){
        return g.tuilesAdjacentesCroix(getTuile()); 
    }
    
    /**
     * On renvoie la liste des tuiles adjacentes en croix
     * @param g
     * @return 
     */
    public ArrayList<Tuile> calculAssechement(Grille g){
        return g.tuilesAdjacentesCroix(getTuile());
    }
    
    /**
     * On remet le nombre d'actions à 3
     */
    public void resetPA(){
        setNbAction(3);
    }
    
    /**
     * Fais diminuer le nombre d'actions de 1
     */
    public void decremente(){
        this.setNbAction(getNbAction()-1);
    }

    /**
    * Permet de déplacer un aventurier sur une nouvelle Tuile
    * en supprimant sa position précédente
     * @param tuile
    */
    public void setTuile(Tuile tuile){
        if (this.tuile != null)
            getTuile().removeAventurier(this);
        
        tuile.addAventurier(this);
        this.tuile = tuile;
    }
    
    
    //Getters et Setters :
    public Position getPosition() {
        return this.tuile.getPosition();
    }
    
      /**
     * @return the tuile
     */   
    public Tuile getTuile(){
        return tuile;
    }
    
     /**
     * @return the nbAction
     */
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

    /**
     * @return the caseDepart
     */
    public abstract String getCaseDepart();


}
