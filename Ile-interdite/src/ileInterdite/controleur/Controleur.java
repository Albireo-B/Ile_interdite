/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.controleur;

import ileInterdite.aventurier.Aventurier;
import java.util.HashMap;


/**
 *
 * @author vinetg
 */
public class Controleur {
    private VueAventurier vueAventurier;
    private VueGrille vueGrille;
    private HashMap<Integer,Aventurier> joueurs;
    private Grille grille;
    public Aventurier aventurierCourant;
    
    
    public void gererDeplacement(){

    this.proposerTuiles(aventurierCourant.calculDeplacement(getGrille()));
    }

    public void gererAssechement(){
 
    this.proposerTuiles(aventurierCourant.calculAssechement(getGrille()));
    }
    
    public void proposerTuiles(ArrayList<Tuile> ct){
          
    }   
    
    public void aventurierSuivant(){
        
    }
    public void nextTurn(){
        
    }
    
    public void traiterMessage(Message action){
        
    }
    
    public void traiterMessage(MessagePos action){
        
    }
    
    
    
    
    
    
    
    /**
     * @return the joueurs
     */
    public HashMap<Integer,Aventurier> getJoueurs() {
        return joueurs;
    }

    /**
     * @param joueurs the joueurs to set
     */
    public void setJoueurs(HashMap<Integer,Aventurier> joueurs) {
        this.joueurs = joueurs;
    }
     
      /**
     * @return the vueAventurier
     */
    public VueAventurier getVueAventurier() {
        return vueAventurier;
    }
    
      /**
     * @param vueAventurier the vueAventurier to set
     */
      public void setVueAventurier(VueAventurier vueAventurier) {
        this.vueAventurier = vueAventurier;
    }
      
      
      /**
     * @return the vueGrille
     */
    public VueGrille getVueGrille() {
        return vueGrille;
    }
    
      /**
     * @param vueGrille the vueGrille to set
     */
      public void setVueGrille(VueGrille vueGrille) {
        this.vueGrille = vueGrille;
    }
      
        
      /**
     * @return the grille
     */
    public Grille getGrille() {
        return grille;
    }
    
      /**
     * @param grille the grille to set
     */
      public void setGrille(Grille grille) {
        this.grille = grille;
    }
}
