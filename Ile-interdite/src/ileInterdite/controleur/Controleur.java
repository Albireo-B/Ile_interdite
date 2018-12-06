/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.controleur;

import ileInterdite.aventurier.Aventurier;
import java.util.ArrayList;
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
    private Aventurier aventurierCourant;
    
    //Fonction globale qui gère le déplacement
    public void gererDeplacement(){

    this.proposerTuiles(getAventurierCourant().calculDeplacement(getGrille()));
    }

    //Fonction globale qui gère l'asséchement
    public void gererAssechement(){
 
    this.proposerTuiles(getAventurierCourant().calculAssechement(getGrille()));
    }
    
    /* affiche les cases possibles en les rendant cliquables*/
    public void proposerTuiles(ArrayList<Tuile> ct){
          for (Tuile t : getAventurierCourant().calculAssechement(getGrille())){
              /* setCliquable à rajouter dans la vue grille */setCliquable(t.getPosition());
          }
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

    /**
     * @return the aventurierCourant
     */
    public Aventurier getAventurierCourant() {
        return aventurierCourant;
    }

    /**
     * @param aventurierCourant the aventurierCourant to set
     */
    public void setAventurierCourant(Aventurier aventurierCourant) {
        this.aventurierCourant = aventurierCourant;
    }
}
