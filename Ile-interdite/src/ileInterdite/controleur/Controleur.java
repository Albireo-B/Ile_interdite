/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.controleur;

import ileInterdite.model.aventurier.Navigateur;
import ileInterdite.model.aventurier.Messager;
import ileInterdite.model.aventurier.Pilote;
import ileInterdite.model.aventurier.Role;
import ileInterdite.model.aventurier.Explorateur;
import ileInterdite.model.aventurier.Plongeur;
import ileInterdite.model.aventurier.Ingenieur;
import ileInterdite.model.aventurier.Aventurier;
import ileInterdite.message.Action;
import ileInterdite.model.EtatTuile;
import ileInterdite.model.Grille;
import ileInterdite.model.Position;
import ileInterdite.model.Tuile;
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
    private ArrayList<Aventurier> joueurs = new ArrayList();
    private Grille grille;
    private Aventurier aventurierCourant;

    /**
     * On définit le constructeur du controleur avec une liste d'aventuriers
     * joueurs et une Grille grille
     *
     * @param nomsjoueurs
     * @param nomTuiles
     */
    public Controleur(ArrayList<String> nomsjoueurs, ArrayList<String> nomTuiles) {
        //Initialisation des joueurs et du joueur courant

        grille = new Grille(nomTuiles);

        //Initialisation de la Grille
        ArrayList<Position> posTuiles = new ArrayList();
        ArrayList<String> nomsTuiles = new ArrayList();
        for (Tuile t : grille.getToutesTuiles()) {
            posTuiles.add(t.getPosition());
            nomsTuiles.add(t.getNom());
        }

        vueGrille = new VueGrille(posTuiles, nomsTuiles);
        vueGrille.addObserver(this);

        ArrayList<Role> roles = new ArrayList();
        roles.add(Role.Navigateur);
        roles.add(Role.Explorateur);
        roles.add(Role.Messager);
        roles.add(Role.Plongeur);
        roles.add(Role.Pilote);
        roles.add(Role.Ingénieur);
        setRoles(nomsjoueurs,roles);
        aventurierCourant = joueurs.get(0);

        
        for (Aventurier j : joueurs) {
            vueGrille.actualiserPositionJoueur(j.getPosition(), null, j.getPion());
        }

        
        grille.getTuile(new Position(3, 2)).setEtat(EtatTuile.COULEE);
        grille.getTuile(new Position(3, 3)).setEtat(EtatTuile.COULEE);
        grille.getTuile(new Position(3, 4)).setEtat(EtatTuile.COULEE);
        grille.getTuile(new Position(1, 3)).setEtat(EtatTuile.COULEE);
        grille.getTuile(new Position(0, 3)).setEtat(EtatTuile.INONDEE);
        grille.getTuile(new Position(2, 3)).setEtat(EtatTuile.INONDEE);
        grille.getTuile(new Position(2, 5)).setEtat(EtatTuile.INONDEE);
        grille.getTuile(new Position(4, 3)).setEtat(EtatTuile.INONDEE);
        grille.getTuile(new Position(2, 0)).setEtat(EtatTuile.INONDEE);
        
        
        vueGrille.actualiserEtatTuile(new Position(3, 2), EtatTuile.COULEE);
        vueGrille.actualiserEtatTuile(new Position(3, 3), EtatTuile.COULEE);
        vueGrille.actualiserEtatTuile(new Position(3, 4), EtatTuile.COULEE);
        vueGrille.actualiserEtatTuile(new Position(1, 3), EtatTuile.COULEE);
        vueGrille.actualiserEtatTuile(new Position(0, 3), EtatTuile.INONDEE);
        vueGrille.actualiserEtatTuile(new Position(2, 3), EtatTuile.INONDEE);
        vueGrille.actualiserEtatTuile(new Position(2, 5), EtatTuile.INONDEE);
        vueGrille.actualiserEtatTuile(new Position(4, 3), EtatTuile.INONDEE);
        vueGrille.actualiserEtatTuile(new Position(2, 0), EtatTuile.INONDEE);
        

        // Création de la vue aventurier
        vueAventurier = new VueAventurier(vueGrille);
        vueAventurier.addObserver(this);
        vueAventurier.actualiserVue(aventurierCourant.getNomJoueur(),
                                    aventurierCourant.getRole(),
                                    aventurierCourant.getPion().getCouleur(),
                                    aventurierCourant.getNbAction()
                                    );
    }

    /**
     * Fonction globale qui gère le déplacement
     */
    public void gererDeplacement() {
        if (aventurierCourant.getNbAction()>0){
            proposerTuiles(aventurierCourant.calculDeplacement(grille), Action.DEPLACER);
        }
    }

    /**
     * Fonction globale qui gère l'asséchement
     */
    public void gererAssechement() {
        proposerTuiles(aventurierCourant.calculAssechement(grille), Action.ASSECHER);
    }

    /**
     * Affiche les cases possibles en les rendant cliquables avec une liste de
     * tuiles et une action
     *
     * @param ct
     * @param act
     */
    public void proposerTuiles(ArrayList<Tuile> ct, Action act) {
        ArrayList<Position> posTuiles = new ArrayList();
        
        for (Tuile t : ct) {
            posTuiles.add(t.getPosition());
        }
        
        vueGrille.actualiserBoutonsCliquables(posTuiles, act);
    }

    /**
     * Passe au prochain joueur
     */
    public void aventurierSuivant() {
        aventurierCourant = joueurs.get((joueurs.indexOf(aventurierCourant) + 1) % joueurs.size());
    }

    /**
     * Change de tour : remet les points d'action a 3, remet le pouvoir en
     * utilisable et actualise la vueAventurier avec les paramètres du nouvel
     * aventurier
     */
    public void nextTurn() {
        aventurierCourant.reset();
        aventurierSuivant();
        vueAventurier.actualiserVue(aventurierCourant.getNomJoueur(),
                                    aventurierCourant.getRole(),
                                    aventurierCourant.getPion().getCouleur(),
                                    aventurierCourant.getNbAction()
                                    );
    }

    /**
     * S'occupe de toute les opérations(logique applicative)
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {

        if (arg instanceof Message) {
            Message message = (Message) arg;

            //Si le message contient une Action
            if (null != message.getAction()) {
                vueGrille.tousBoutonsInertes();
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
            }
        }
        //Si arg est de type MessagePos
        if (arg instanceof MessagePos) {
            vueGrille.tousBoutonsInertes();
            MessagePos messagepos = (MessagePos) arg;
            
            //Si le messagePos possède l'action DEPLACER
            if (messagepos.getAction() == Action.DEPLACER) {
                
                vueGrille.actualiserPositionJoueur(messagepos.getPos(), aventurierCourant.getPosition(), aventurierCourant.getPion());
                //Si l'aventurier en train de jouer est un pilote
                if (aventurierCourant instanceof Pilote && aventurierCourant.getPouvoir()) {
                    Pilote p = (Pilote) aventurierCourant;
                    p.setPositionPilote(grille, grille.getTuile(messagepos.getPos()));

                } else {
                    aventurierCourant.setTuile(grille.getTuile(messagepos.getPos()));
                }
                aventurierCourant.decremente();

                //Si le messagePos possède l'action ASSECHER
            }
            else if (messagepos.getAction() == Action.ASSECHER) {
                grille.getTuile(messagepos.getPos()).setEtat(EtatTuile.SECHE);
                vueGrille.actualiserEtatTuile(messagepos.getPos(), EtatTuile.SECHE);

                //Si l'aventurier en train de jouer est un ingénieur
                if (aventurierCourant instanceof Ingenieur) {
                    //Si le pouvoir de l'ingénieur est utilisable
                    if (aventurierCourant.getPouvoir()) {
                        aventurierCourant.decremente();
                        aventurierCourant.setPouvoir(false);
                        
                        gererAssechement();
                    } else {
                        aventurierCourant.setPouvoir(true);
                    }
                }
                else {
                    aventurierCourant.decremente();
                }
            }
        }
        vueAventurier.actualiserVue(aventurierCourant.getNomJoueur(),
                                    aventurierCourant.getRole(),
                                    aventurierCourant.getPion().getCouleur(),
                                    aventurierCourant.getNbAction()
                                    );
    
        ArrayList<Tuile> casesAssechables = aventurierCourant.calculAssechement(grille);
        // Si l'aventurier a moins de 1 action ou qu'il n'est pas un ingénieur qui utilise son pouvoir et qui a encore des cases possibles a assécher
        if (aventurierCourant.getNbAction() < 1 && 
                !( aventurierCourant instanceof Ingenieur &&
                 !(aventurierCourant.getPouvoir()) &&
                 !casesAssechables.isEmpty() )
                )
        {
            nextTurn();
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
    
    public void setRoles(ArrayList<String> nomsJoueurs, ArrayList<Role> Rôles){
        for (Tuile t : grille.getTuiles().values()) {
            for (int i = 0; i < nomsJoueurs.size(); i++) {
                if (t.getNom().equals(Rôles.get(i).getCaseDepart())) {
                    joueurs.add(créerAventurier(t, nomsJoueurs.get(i), Rôles.get(i)));
                }
            }
        }
    }
    
    public Aventurier créerAventurier(Tuile t, String n, Role r){
        Aventurier a = null;
        switch (r) {
            case Explorateur :
                a = new Explorateur(n,t);
                break;
            case Messager :
                a = new Messager(n,t);
                break;
            case Plongeur :
                a = new Plongeur(n,t);
                break;
             case Navigateur :
                a = new Navigateur(n,t);
                break;   
             case Pilote :
                a = new Pilote(n,t);
                break;
             case Ingénieur :
                a = new Ingenieur(n,t);
                break; 
        }
        return a;
    }
}
