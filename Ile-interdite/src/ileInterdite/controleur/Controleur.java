/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.controleur;

import ileInterdite.EtatTuile;
import ileInterdite.Grille;
import ileInterdite.Position;
import ileInterdite.Tuile;
import ileInterdite.actions.*;
import ileInterdite.aventurier.*;
import ileInterdite.controleur.utilitaires.Utils;
import ileInterdite.message.*;
import ileInterdite.vues.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


/**
 *
 * @author vinetg
 */
public class Controleur implements Observer {
    private VueAventurier vueAventurier;
    private VueGrille vueGrille;
    private ArrayList<Aventurier> joueurs;
    private Grille grille;
    private Aventurier aventurierCourant;
    
    public Controleur(ArrayList<Aventurier> joueurs,Grille grille){
        //Initialisation des joueurs et du joueur courant
        setJoueurs(joueurs);
        setAventurierCourant(getJoueurs().get(0));
        
        // Création de la vue aventurier
        vueAventurier = new VueAventurier(aventurierCourant.getNomJoueur(),aventurierCourant.getClasse(),Utils.Pion.ROUGE.getCouleur(),aventurierCourant.getNbAction());
        System.out.println(joueurs);
        vueAventurier.addObserver(this);
        
        //Initialisation de la Grille
        setGrille(grille);
        
        
    }
    
    
    //Fonction globale qui gère le déplacement
    public void gererDeplacement(){

    proposerTuiles(getAventurierCourant().calculDeplacement(getGrille()));
    }

    //Fonction globale qui gère l'asséchement
    public void gererAssechement(){
 
    proposerTuiles(getAventurierCourant().calculAssechement(getGrille()));
    }
    
    /* affiche les cases possibles en les rendant cliquables*/
    public void proposerTuiles(ArrayList<Tuile> ct){
          for (Tuile t : ct){
          setCliquable(t.getPosition());
          }
    }   
    
    /*fais apparaître une Tuile*/
    public void setCliquable(Position pos){
        
    }
    
    public void aventurierSuivant(){
       
       setAventurierCourant(getJoueurs().get((getJoueurs().indexOf(aventurierCourant) + 1) % 4));
    }
    
    public void nextTurn(){
        getAventurierCourant().setPouvoir(true);
        getAventurierCourant().resetPA();
        aventurierSuivant();
        getVueAventurier().actualiser();
    }
    
    //s'occupe de toute les opérations
    @Override
    public void update(Observable o, Object arg) {
        Message message = (Message) arg;
    
        if (message.getAction()== Action.ASSECHER){
            gererAssechement();
            
        }
        else if (message.getAction()==Action.DEPLACER){
            gererDeplacement();
        }
       
        if (arg instanceof MessagePos){
            MessagePos messagepos = (MessagePos) arg;
            if (messagepos.getAction()==Action.DEPLACER){
                if (getAventurierCourant() instanceof Pilote) {
                    Pilote p = (Pilote) getAventurierCourant();
                    p.setPositionPilote(getGrille(),grille.getTuile(messagepos.getPosition()));    
                } else {
                    getAventurierCourant().setTuile(grille.getTuile(messagepos.getPosition()));
                }          
            } else if (messagepos.getAction()==Action.ASSECHER){
                grille.getTuile(messagepos.getPosition()).setEtat(EtatTuile.SECHE);
                if (getAventurierCourant() instanceof Ingenieur){
                    if(getAventurierCourant().getPouvoir()) {
                        getAventurierCourant().setPouvoir(false);
                        gererAssechement();
                    } else {
                        getAventurierCourant().decremente();
                    }
                } else { 
                    getAventurierCourant().decremente();
                }
                         
            }
         
         }
        
    }
    
    
    
    
    
    
    /**
     * @return the joueurs
     */
    public ArrayList<Aventurier> getJoueurs() {
        return joueurs;
    }

    /**
     * @param joueurs the joueurs to set
     */
    public void setJoueurs(ArrayList<Aventurier> joueurs) {
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
