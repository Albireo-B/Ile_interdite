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
    
    /**
    * On définit le constructeur du controleur avec une liste d'aventuriers joueurs et une Grille grille
     * @param joueurs
     * @param nomTuiles
     * @param grille
    */
    public Controleur(ArrayList<Aventurier> joueurs, ArrayList<String> nomTuiles){
        //Initialisation des joueurs et du joueur courant
        setJoueurs(joueurs);
        setAventurierCourant(getJoueurs().get(0));
        
        grille = new Grille(nomTuiles, joueurs);
        
        //Initialisation de la Grille
        ArrayList<Position> posTuiles = new ArrayList<Position>();
        ArrayList<String> nomsTuiles = new ArrayList<String>();
        for (Tuile t : getGrille().getToutesTuiles()){
            posTuiles.add(t.getPosition());
            nomsTuiles.add(t.getNom());
        }
        
        vueGrille = new VueGrille(posTuiles, nomsTuiles);
        for (Aventurier j : joueurs) {
            vueGrille.actualiserPositionJoueur(j.getPosition(), j);
        }
        
        getGrille().getTuile(new Position(3,0)).setEtat(EtatTuile.INONDEE);
        getGrille().getTuile(new Position(3,1)).setEtat(EtatTuile.COULEE);
        
        getVueGrille().actualiserEtatTuile(new Position(3,0),EtatTuile.INONDEE);
        getVueGrille().actualiserEtatTuile(new Position(3,1),EtatTuile.COULEE);
        
        // Création de la vue aventurier
        vueAventurier = new VueAventurier(getVueGrille());
        vueAventurier.addObserver(this);
        getVueAventurier().actualiserVue(getAventurierCourant().getNomJoueur(), getAventurierCourant().getClasse(), getAventurierCourant().getPion().getCouleur(), getAventurierCourant().getNbAction());

    }
    
    
    /**
    * Fonction globale qui gère le déplacement
    */
    public void gererDeplacement(){

    proposerTuiles(getAventurierCourant().calculDeplacement(getGrille()),Action.DEPLACER);
    }

    /**
    * Fonction globale qui gère l'asséchement
    */
    public void gererAssechement(){
 
    proposerTuiles(getAventurierCourant().calculAssechement(getGrille()),Action.ASSECHER);
    }
    
    /**
    * Affiche les cases possibles en les rendant cliquables avec
    * une liste de tuiles et une action
     * @param ct
     * @param act
    */
    public void proposerTuiles(ArrayList<Tuile> ct,Action act){
        ArrayList<Position> posTuiles = new ArrayList<Position>();
        for (Tuile t : ct){
            posTuiles.add(t.getPosition());
        }
        getVueGrille().actualiserBoutonsCliquables(posTuiles,act);
    }   
    
    /**
     * Passe au prochain joueur
     */
    public void aventurierSuivant(){
       
       setAventurierCourant(getJoueurs().get((getJoueurs().indexOf(getAventurierCourant()) + 1) % 4));
    }
    
    /**
     * Change de tour : remet les points d'action a 3, remet le pouvoir en utilisable 
     * et actualise la vueAventurier avec les paramètres du nouvel aventurier
     */
    public void nextTurn(){
        getAventurierCourant().setPouvoir(true);
        getAventurierCourant().resetPA();
        aventurierSuivant();
        getVueAventurier().actualiserVue(getAventurierCourant().getNomJoueur(), getAventurierCourant().getClasse(), getAventurierCourant().getPion().getCouleur(), getAventurierCourant().getNbAction());
    }
    
    /**
     * S'occupe de toute les opérations(logique applicative)
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        Message message = (Message) arg;
        //Si le message contient une Action
        if (null!= message.getAction()) 
        switch (message.getAction()) {
            //Si le message possède l'action ASSECHER
            case ASSECHER:
                gererAssechement();
                break;
            //Si le message possède l'action DEPLACER
            case DEPLACER:
                gererDeplacement();
                break;
            //Si le message possède l'action TERMINER
            case TERMINER:
                nextTurn();
                break;
            default:
                break;
        }
       //Si arg est de type MessagePos
        if (arg instanceof MessagePos){
            getVueGrille().tousBoutonsInertes();
            MessagePos messagepos = (MessagePos) arg;
            //Si le messagePos possède l'action DEPLACER
            if (messagepos.getAction()==Action.DEPLACER){
                //Si l'aventurier en train de jouer est un pilote
                if (getAventurierCourant() instanceof Pilote) {
                    Pilote p = (Pilote) getAventurierCourant();
                    p.setPositionPilote(getGrille(),getGrille().getTuile(messagepos.getPos()));    
                } else {
                    getAventurierCourant().setTuile(getGrille().getTuile(messagepos.getPos()));
                }
                getVueGrille().actualiserPositionJoueur(messagepos.getPos(),getAventurierCourant().getClasse());
            //Si le messagePos possède l'action ASSECHER
            } else if (messagepos.getAction()==Action.ASSECHER){
                getGrille().getTuile(messagepos.getPos()).setEtat(EtatTuile.SECHE);
                //Si l'aventurier en train de jouer est un ingénieur
                if (getAventurierCourant() instanceof Ingenieur){
                    //Si le pouvoir de l'ingénieur est utilisable
                    if(getAventurierCourant().getPouvoir()) {
                        getAventurierCourant().setPouvoir(false);
                        gererAssechement();
                    } else {
                        getAventurierCourant().decremente();
                    }
                } else { 
                    getAventurierCourant().decremente();
                    
                }
                getVueGrille().actualiserEtatTuile(messagepos.getPos(),EtatTuile.SECHE);         
            }
         
         }
        
    }
    
    
    
    
    
    
    
    
    
    //Getters et Setters :
    

    
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
