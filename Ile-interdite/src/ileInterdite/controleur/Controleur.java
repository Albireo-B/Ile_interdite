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
import utilitaires.Action;
import utilitaires.EtatTuile;
import ileInterdite.model.Grille;
import ileInterdite.model.Position;
import ileInterdite.model.Tuile;
import ileInterdite.message.*;
import ileInterdite.model.cartes.*;
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
    private ArrayList<Aventurier> joueurs = new ArrayList<>();//à modifier en [4]
    private Grille grille;
    private Aventurier aventurierCourant;
    private CarteInondation[] piocheInondation=new CarteInondation[24];
    private CarteInondation[] defausseInondation=new CarteInondation[24];
    private CarteTirage[] piocheTirage=new CarteTirage[27];
    private CarteTirage[] defausseTirage=new CarteTirage[27];

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
        roles.add(Role.Explorateur);
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
        vueAventurier = new VueAventurier(getVueGrille());
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
        if (getAventurierCourant().getNbAction()>0){
            proposerTuiles(getAventurierCourant().calculDeplacement(getGrille()), Action.DEPLACER);
        }
    }

    /**
     * Fonction globale qui gère l'asséchement
     */
    public void gererAssechement() {
        proposerTuiles(getAventurierCourant().calculAssechement(getGrille()), Action.ASSECHER);
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
        getVueGrille().actualiserBoutonsCliquables(posTuiles, act);
    }

    /**
     * Passe au prochain joueur
     */
    public void aventurierSuivant() {

        setAventurierCourant(getJoueurs().get((getJoueurs().indexOf(getAventurierCourant()) + 1) % getJoueurs().size()));
    }

    /**
     * Change de tour : remet les points d'action a 3, remet le pouvoir en
     * utilisable et actualise la vueAventurier avec les paramètres du nouvel
     * aventurier
     */
    public void nextTurn() {
        getAventurierCourant().reset();
        aventurierSuivant();
        getVueAventurier().actualiserVue(getAventurierCourant().getNomJoueur(),
                                    getAventurierCourant().getRole(),
                                    getAventurierCourant().getPion().getCouleur(),
                                    getAventurierCourant().getNbAction()
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
                getVueGrille().tousBoutonsInertes();
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
            getVueGrille().tousBoutonsInertes();
            MessagePos messagepos = (MessagePos) arg;
            
            //Si le messagePos possède l'action DEPLACER
            if (messagepos.getAction() == Action.DEPLACER) {
                
                getVueGrille().actualiserPositionJoueur(messagepos.getPos(), getAventurierCourant().getPosition(), getAventurierCourant().getPion());
                //Si l'aventurier en train de jouer est un pilote
                if (getAventurierCourant() instanceof Pilote && getAventurierCourant().getPouvoir()) {
                    Pilote p = (Pilote) getAventurierCourant();
                    p.setPositionPilote(getGrille(), getGrille().getTuile(messagepos.getPos()));

                } else {
                    getAventurierCourant().setTuile(getGrille().getTuile(messagepos.getPos()));
                }
                getAventurierCourant().decremente();

                //Si le messagePos possède l'action ASSECHER
            }
            else if (messagepos.getAction() == Action.ASSECHER) {
                getGrille().getTuile(messagepos.getPos()).setEtat(EtatTuile.SECHE);
                getVueGrille().actualiserEtatTuile(messagepos.getPos(), EtatTuile.SECHE);

                //Si l'aventurier en train de jouer est un ingénieur
                if (getAventurierCourant() instanceof Ingenieur) {
                    //Si le pouvoir de l'ingénieur est utilisable
                    if (getAventurierCourant().getPouvoir()) {
                        getAventurierCourant().decremente();
                        getAventurierCourant().setPouvoir(false);
                        
                        gererAssechement();
                    } else {
                        getAventurierCourant().setPouvoir(true);
                    }
                }
                else {
                    getAventurierCourant().decremente();
                }
            }
        }
        getVueAventurier().actualiserVue(getAventurierCourant().getNomJoueur(),
                                    getAventurierCourant().getRole(),
                                    getAventurierCourant().getPion().getCouleur(),
                                    getAventurierCourant().getNbAction()
                                    );
    
        ArrayList<Tuile> casesAssechables = getAventurierCourant().calculAssechement(getGrille());
        // Si l'aventurier a moins de 1 action ou qu'il n'est pas un ingénieur qui utilise son pouvoir et qui a encore des cases possibles a assécher
        if (getAventurierCourant().getNbAction() < 1 && 
                !( aventurierCourant instanceof Ingenieur &&
                 !(aventurierCourant.getPouvoir()) &&
                 !casesAssechables.isEmpty() )
                )
        {
            nextTurn();
        }
    }
    
        public void setRoles(ArrayList<String> nomsJoueurs, ArrayList<Role> Rôles){
        for (Tuile t : getGrille().getTuiles().values()) {
            for (int i = 0; i < nomsJoueurs.size(); i++) {
                if (t.getNom().equals(Rôles.get(i).getCaseDepart())) {
                    getJoueurs().add(créerAventurier(t, nomsJoueurs.get(i), Rôles.get(i)));
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
    public void setJoueurs(ArrayList<Aventurier>joueurs) {
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

    /**
     * @return the piocheInondation
     */
    public CarteInondation[] getPiocheInondation() {
        return piocheInondation;
    }

    /**
     * @param piocheInondation the piocheInondation to set
     */
    public void setPiocheInondation(CarteInondation[] piocheInondation) {
        this.piocheInondation = piocheInondation;
    }

    /**
     * @return the defausseInondation
     */
    public CarteInondation[] getDefausseInondation() {
        return defausseInondation;
    }

    /**
     * @param defausseInondation the defausseInondation to set
     */
    public void setDefausseInondation(CarteInondation[] defausseInondation) {
        this.defausseInondation = defausseInondation;
    }

    /**
     * @return the piocheTirage
     */
    public CarteTirage[] getPiocheTirage() {
        return piocheTirage;
    }

    /**
     * @param piocheTirage the piocheTirage to set
     */
    public void setPiocheTirage(CarteTirage[] piocheTirage) {
        this.piocheTirage = piocheTirage;
    }

    /**
     * @return the defausseTirage
     */
    public CarteTirage[] getDefausseTirage() {
        return defausseTirage;
    }

    /**
     * @param defausseTirage the defausseTirage to set
     */
    public void setDefausseTirage(CarteTirage[] defausseTirage) {
        this.defausseTirage = defausseTirage;
    }
    

}
