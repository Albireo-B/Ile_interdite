/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.controleur;

import utilitaires.Role;
import ileInterdite.model.aventurier.*;
import utilitaires.*;
import ileInterdite.model.*;
import ileInterdite.message.*;
import ileInterdite.model.cartes.*;
import ileInterdite.vues.*;
import java.util.ArrayList;
import ileInterdite.vues.VuePrincipale;
import ileInterdite.vues.VuePrincipale.Bouton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author vinetg
 */
public class Controleur implements Observer {

    private VuePrincipale vuePrincipale;
    private VueGrille vueGrille;
    private HashMap<Role, Aventurier> joueurs = new HashMap<>();
    private Grille grille;
    private Aventurier aventurierCourant;
    private ArrayList<CarteInondation> piocheInondation = new ArrayList<>();
    private ArrayList<CarteInondation> defausseInondation = new ArrayList<>();
    private ArrayList<CarteTirage> piocheTirage = new ArrayList<>();
    private ArrayList<CarteTirage> defausseTirage = new ArrayList<>();
    private int niveauEau;
    private ArrayList<Role> listeRoles;

    /**
     * On définit le constructeur du controleur avec une liste d'aventuriers
     * joueurs et une Grille grille
     *
     * @param nomsjoueurs
     * @param roles
     * @param nomTuiles
     */
    public Controleur(ArrayList<String> nomsjoueurs, ArrayList<Role> roles, ArrayList<String> nomTuiles, HashMap<String, Tresor> tuilesTresor, ArrayList<CarteTirage> pioche, int niveauEau) {
        //Initialisation du niveau d'eau
        this.niveauEau = niveauEau;

        //Initialisation de la Grille
        grille = new Grille(nomTuiles, tuilesTresor);

        ArrayList<Position> posTuiles = new ArrayList();
        ArrayList<String> nomsTuiles = new ArrayList();
        for (Tuile t : grille.getToutesTuiles()) {
            posTuiles.add(t.getPosition());
            nomsTuiles.add(t.getNom());
        }

        for (String nom : nomsTuiles) {
            piocheInondation.add(new CarteInondation(nom));
        }

        piocheTirage = pioche;

        vueGrille = new VueGrille(posTuiles, nomsTuiles);
        vueGrille.addObserver(this);

        //Initialisation des joueurs et du joueur courant
        setRoles(nomsjoueurs, roles);
        aventurierCourant = joueurs.get(listeRoles.get(0));

        HashMap<Role, VueAventurier> vuesAventuriers = new HashMap();
        int cptr = 0;
        for (Role role : joueurs.keySet()) {
            vueGrille.actualiserPositionJoueur(joueurs.get(role).getPosition(), null, joueurs.get(role).getPion());
            VueAventurier newVueAv = new VueAventurier(role, cptr == 0 || cptr == 3);
            newVueAv.addObserver(this);
            vuesAventuriers.put(role, newVueAv);
            cptr++;
        }

        // Création des vues
        vuePrincipale = new VuePrincipale(vueGrille, vuesAventuriers);
        vuePrincipale.addObserver(this);

        //Ecoute des IAventurier
        for (VueAventurier av : vuePrincipale.getPanelAventuriers().values()) {
            av.getCarteJoueur().addObserver(this);
        }

        //Ecoute des VueDefausse
        for (Role role : joueurs.keySet()) {
            joueurs.get(role).getVueDefausse().addObserver(this);
        }

        vuePrincipale.actualiserVue(aventurierCourant.getNomJoueur(),
                aventurierCourant.getRole(),
                aventurierCourant.getPion().getCouleur(),
                aventurierCourant.getNbAction()
        );
    }

    public void initInondation() {
        for (int i = 0; i < 6; i++) {
            getPiocheInondation().get(getPiocheInondation().size() - 1);
        }
    }

    /**
     *
     * @param nomsJoueurs
     * @param Rôles
     */
    public void setRoles(ArrayList<String> nomsJoueurs, ArrayList<Role> Rôles) {
        for (Tuile t : getGrille().getTuiles().values()) {
            for (int i = 0; i < nomsJoueurs.size(); i++) {
                if (t.getNom().equals(Rôles.get(i).getCaseDepart())) {
                    joueurs.put(Rôles.get(i), créerAventurier(t, nomsJoueurs.get(i), Rôles.get(i)));
                }
            }
        }
        listeRoles = new ArrayList<>(joueurs.keySet());
    }

    public Aventurier créerAventurier(Tuile t, String n, Role r) {
        Aventurier a = null;
        switch (r) {
            case Explorateur:
                a = new Explorateur(n, t);
                break;
            case Messager:
                a = new Messager(n, t);
                break;
            case Plongeur:
                a = new Plongeur(n, t);
                break;
            case Navigateur:
                a = new Navigateur(n, t);
                break;
            case Pilote:
                a = new Pilote(n, t);
                break;
            case Ingénieur:
                a = new Ingenieur(n, t);
                break;
        }
        return a;
    }

    public void initCartes() {
        for (Role role : joueurs.keySet()) {
            ArrayList<CarteTirage> cartes = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                while (piocheTirage.get(piocheTirage.size() - 1) instanceof CarteMonteeDesEaux) {
                    Collections.shuffle(piocheTirage);
                }
                cartes.add(piocheTirage.get(piocheTirage.size() - 1));
                piocheTirage.remove(piocheTirage.size() - 1);
            }
            try {
                joueurs.get(role).addCartes(cartes);
            } catch (ExceptionAventurier ex) {
            };
            vuePrincipale.getPanelAventuriers().get(role).actualiserVueAventurier(joueurs.get(role).cartesToString());
        }
        updateBoutons();
    }

    /**
     * Fonction globale qui gère le déplacement
     */
    public void gererDeplacement() {
        if (aventurierCourant.getNbAction() > 0) {
            if (aventurierCourant.getRole() == Role.Navigateur) {
                for (Role r : listeRoles) {
                    if (r != Role.Navigateur) {
                        vuePrincipale.getPanelAventuriers().get(r).getCarteJoueur().removeActionListener();
                        vuePrincipale.getPanelAventuriers().get(r).devenirSuiveur(true);
                    }
                }
            }
            proposerTuiles(aventurierCourant.calculDeplacement(getGrille()), Action.DEPLACER, aventurierCourant.getRole());
        }
    }

    public void appliquerDeplacement(MessagePos messagepos) {
        vueGrille.actualiserPositionJoueur(messagepos.getPos(), aventurierCourant.getPosition(), aventurierCourant.getPion());
        //Si l'aventurier en train de jouer est un pilote
        aventurierCourant.setTuile(getGrille(), getGrille().getTuile(messagepos.getPos()));
        aventurierCourant.decrementeNbActions();

    }

    public void gererNaviguation(Role r) {
        proposerTuiles(joueurs.get(r).calculGuide(getGrille()), Action.SUIVRE, r);
    }

    public void appliquerNavigation(MessagePos messagepos) {
        vueGrille.actualiserPositionJoueur(messagepos.getPos(), joueurs.get(messagepos.getRole()).getPosition(), joueurs.get(messagepos.getRole()).getPion());
        joueurs.get(messagepos.getRole()).setTuile(getGrille(), getGrille().getTuile(messagepos.getPos()));
        aventurierCourant.decrementeNbActions();
    }

    /**
     * Fonction globale qui gère l'asséchement
     */
    public void gererAssechement() {
        proposerTuiles(aventurierCourant.calculAssechement(getGrille()), Action.ASSECHER, aventurierCourant.getRole());
    }

    public void appliquerAssechement(MessagePos messagepos) {
        getGrille().getTuile(messagepos.getPos()).setEtat(EtatTuile.SECHE);
        vueGrille.actualiserEtatTuile(messagepos.getPos(), EtatTuile.SECHE);

        //Si l'aventurier n'est pas un ingénieur qui as pas son pouvoir
        if (aventurierCourant instanceof Ingenieur && !aventurierCourant.getPouvoir()) {
            aventurierCourant.setPouvoir(true);
        } else {
            if (aventurierCourant instanceof Ingenieur) {
                aventurierCourant.setPouvoir(false);
                gererAssechement();
            }
            aventurierCourant.decrementeNbActions();

        }

    }

    /**
     * Fonction globale qui gère l'inondation de fin de tour
     */
    public void gererInondation() {
        //Pour toutes les cartes à inonder
        for (CarteInondation carteAInonder : tirerCartesInondation()) {
            //Pour toutes les tuiles
            for (Tuile tuile : getGrille().getToutesTuiles()) {
                //Si La tuile a le même nom que la carte à inonder
                if (tuile.getNom().equals(carteAInonder.getNom())) {

                    try {
                        monteeDesEaux(tuile.getPosition());
                    } catch (ExceptionAventurier ex) {
                        System.out.println("L'aventurier " + ex.getAventurier().getNomJoueur() + " ne peut plus se déplacer !");

                        //à compléter
                    }
                    //Si la tuile est coulée
                    if (tuile.getEtat() != EtatTuile.COULEE) {
                        defausseInondation.add(carteAInonder);
                    }
                }
            }
        }
    }

    /**
     * Augmente du niveau d'eau d'une tuile, et vérifie qu'aucun aventurier
     * n'est présent sur l'île, et si oui lui propose de se déplacer
     *
     * @param p
     * @throws utilitaires.ExceptionAventurier
     */
    public void monteeDesEaux(Position p) throws ExceptionAventurier {
        //Si le tuile est inondée
        if (getGrille().getTuile(p).getEtat() == EtatTuile.INONDEE) {
            getGrille().getTuile(p).setEtat(EtatTuile.COULEE);
            vueGrille.actualiserEtatTuile(p, EtatTuile.COULEE);

            for (Role role : joueurs.keySet()) {
                if (joueurs.get(role).getTuile().getEtat() == EtatTuile.COULEE) {
                    if (!joueurs.get(role).calculDeplacement(grille).isEmpty()) {
                        proposerTuiles(joueurs.get(role).calculDeplacement(getGrille()), Action.DEPLACER, role);
                    } else {
                        throw new ExceptionAventurier(joueurs.get(role));
                    }
                }
            }

        } else {
            getGrille().getTuile(p).setEtat(EtatTuile.INONDEE);
            vueGrille.actualiserEtatTuile(p, EtatTuile.INONDEE);
        }
    }

    /**
     * Affiche les cases possibles en les rendant cliquables avec une liste de
     * tuiles et une action
     *
     * @param ct
     * @param act
     */
    public void proposerTuiles(ArrayList<Tuile> ct, Action act, Role role) {
        ArrayList<Position> posTuiles = new ArrayList();
        for (Tuile t : ct) {
            posTuiles.add(t.getPosition());
        }
        vueGrille.actualiserBoutonsCliquables(posTuiles, act, role);
    }
    public void proposerTuilesHelicopthère(Action act, Role role,ArrayList<Role> roles) {
        ArrayList<Position> posTuiles = new ArrayList();
        for (Tuile t : getGrille().tuilesNonCoulees(joueurs.get(roles.get(0)).getTuile())) {
            posTuiles.add(t.getPosition());
        }
        vueGrille.actualiserBoutonsCliquables(posTuiles, act, role,roles);
    }
    /**
     * Passe au prochain joueur
     */
    public void aventurierSuivant() {
        aventurierCourant = joueurs.get(listeRoles.get((listeRoles.indexOf(aventurierCourant.getRole()) + 1) % joueurs.size()));
    }

    public void actualiserVue(Object arg) {
        //Si l'ingénieur fait une autre action au lieu d'assecher une seconde fois
        if (arg instanceof MessagePos && ((MessagePos) arg).getAction() != Action.ASSECHER && ((MessagePos) arg).getRole() == Role.Ingénieur) {
            aventurierCourant.setPouvoir(true);
        }
        //regarde si les carte des aventuriers sont encore utiles
        if (   //l'aventurier déplace des joueurs
                !(aventurierCourant.getRole() == Role.Navigateur && (((Message) arg).getAction()== Action.DEPLACER
                ||(((Message) arg).getAction()== Action.SUIVRE && !(arg instanceof MessagePos)))
                )
                //Une carte à donner est séléctionée
                && !(arg instanceof MessageCarte && ((Message) arg).getAction() == Action.DONNER)
                //L'action de la carte hélicoptère est en cours 
                && !(((Message) arg).getAction() == Action.CARTESPECIALE && ( arg instanceof MessageCarte &&((MessageCarte)arg).getNomCarte().equals("Helicoptere")))
                && !(((Message) arg).getAction() == Action.GROUPEHELICO && !(arg instanceof MessageGroupePos))) {
            for (Role r : listeRoles) {
                vuePrincipale.getPanelAventuriers().get(r).devenirSuiveur(false);
                vuePrincipale.getPanelAventuriers().get(r).getCarteJoueur().getBoutonAventurier().setBackground(null);
            }
        }
        if (   //si l'aventurier déplace des joueurs
                aventurierCourant.getRole() == Role.Navigateur && (((Message) arg).getAction()== Action.DEPLACER
                ||(((Message) arg).getAction()== Action.SUIVRE && !(arg instanceof MessagePos)))
                ){
                vuePrincipale.getPanelAventuriers().get(Role.Navigateur).getCarteJoueur().removeActionListener();
            for (Role r : listeRoles) {
                vuePrincipale.getPanelAventuriers().get(r).getCarteJoueur().getBoutonAventurier().setBackground(null);
            }   
        }
        vuePrincipale.actualiserVue(aventurierCourant.getNomJoueur(),
                aventurierCourant.getRole(),
                aventurierCourant.getPion().getCouleur(),
                aventurierCourant.getNbAction()
        );
        updateBoutons();
    }

    public void actualiserModele(Object arg) {
        ArrayList<Tuile> casesAssechables = aventurierCourant.calculAssechement(getGrille());

        // Si l'aventurier a moins de 1 action et qu'il n'est pas un ingénieur qui utilise son pouvoir et qui a encore des cases possibles a assécher
        if (aventurierCourant.getNbAction() < 1
                && !(aventurierCourant instanceof Ingenieur
                && !(aventurierCourant.getPouvoir())
                && !casesAssechables.isEmpty())) {
            nextTurn();
        }
    }

    /**
     * Change de tour : remet les points d'action a 3, remet le pouvoir en
     * utilisable et actualise la vuePrincipale avec les paramètres du nouvel
     * aventurier
     */
    public void nextTurn() {
        aventurierCourant.reset();
        tirerCartes();
        gererInondation();
        aventurierSuivant();
        vuePrincipale.actualiserVue(aventurierCourant.getNomJoueur(),
                aventurierCourant.getRole(),
                aventurierCourant.getPion().getCouleur(),
                aventurierCourant.getNbAction()
        );
    }

    public void appliquerDefausse(MessageCarte messageCarte) {
        CarteTirage carteSelection = null;
        for (CarteTirage carte : joueurs.get(messageCarte.getRole()).getCartes()) {
            if (carte.getNom().equals(messageCarte.getNomCarte())) {
                carteSelection = carte;
            }

        }
        if (carteSelection.getUtilisable()) {
            appliquerCartesSpeciales(messageCarte);
        }

        defausseTirage.add(carteSelection);
        joueurs.get(messageCarte.getRole()).removeCarte(carteSelection);
        joueurs.get(messageCarte.getRole()).getVueDefausse().close();
        if (joueurs.get(messageCarte.getRole()).getCartes().size() > 5) {
            joueurs.get(messageCarte.getRole()).defausseCartes();
        }
        vuePrincipale.getPanelAventuriers().get(messageCarte.getRole()).actualiserVueAventurier(joueurs.get(messageCarte.getRole()).cartesToString());

    }

    public void appliquerRecevoir(MessageCarte messageCarte) {
        CarteTirage carte = stringToCarte(messageCarte.getNomCarte(), aventurierCourant.getRole());
        ArrayList<CarteTirage> cartes = new ArrayList<>();

        cartes.add(carte);
        aventurierCourant.removeCarte(carte);
        try {
            joueurs.get(messageCarte.getRole()).addCartes(cartes);
        } catch (ExceptionAventurier ex) {
            joueurs.get(messageCarte.getRole()).defausseCartes();
        }
        vuePrincipale.getPanelAventuriers().get(messageCarte.getRole()).actualiserVueAventurier(joueurs.get(messageCarte.getRole()).cartesToString());
        vuePrincipale.getPanelAventuriers().get(aventurierCourant.getRole()).actualiserVueAventurier(aventurierCourant.cartesToString());

        aventurierCourant.decrementeNbActions();
    }

    public void appliquerCartesSpeciales(MessageCarte messageCarte) {
        if (messageCarte.getNomCarte().equals("SacDeSable")) {
            gererSacDeSable(messageCarte.getRole());
        } else {
            gererGroupeHelicoptere(messageCarte.getRole());

        }
    }

    public void gererSacDeSable(Role role) {
        ArrayList<Tuile> liste = new ArrayList();
        for (Tuile t : getGrille().tuilesNonCoulees(null)) {
            if (t.getEtat() == EtatTuile.INONDEE) {
                liste.add(t);
            }
        }
        proposerTuiles(liste, Action.CARTESPECIALE, role);
    }

    private void appliquerAssechementSacDeSable(MessagePos messagepos) {
        getGrille().getTuile(messagepos.getPos()).setEtat(EtatTuile.SECHE);
        vueGrille.actualiserEtatTuile(messagepos.getPos(), EtatTuile.SECHE);
        
        //retire la carte
        joueurs.get(messagepos.getRole()).removeCarte(stringToCarte("SacDeSable", messagepos.getRole()));
        defausseTirage.add(stringToCarte("SacDeSable", messagepos.getRole()));
        vuePrincipale.getPanelAventuriers().get(messagepos.getRole()).actualiserVueAventurier(joueurs.get(messagepos.getRole()).cartesToString());
    }

    public void gererGroupeHelicoptere(Role possesseurCarte) {
        for (Role roleAventurier : joueurs.keySet()) {
            vuePrincipale.getPanelAventuriers().get(roleAventurier).getCarteJoueur().proposerHelico(possesseurCarte, new ArrayList<>(), true);
        }


    }

    public void gererGroupeHelicoptere(Role possesseurCarte, ArrayList<Role> roles) {
        for (Role r : listeRoles) {
            if (roles.contains(r)) {
                vuePrincipale.getPanelAventuriers().get(r).getCarteJoueur().proposerHelico(possesseurCarte, roles, false);
            } else {
                if (!roles.isEmpty()){
                    if (joueurs.get(roles.get(0)).getTuile().getRoleAventuriers().contains(r)){
                        vuePrincipale.getPanelAventuriers().get(r).getCarteJoueur().proposerHelico(possesseurCarte, roles, true);}
                    else{
                        vuePrincipale.getPanelAventuriers().get(r).getCarteJoueur().removeActionListener();
                    }
                    
                } else {
                    vuePrincipale.getPanelAventuriers().get(r).getCarteJoueur().proposerHelico(possesseurCarte, roles, true);
                }
            }
        }
        if (!roles.isEmpty()){
            proposerTuilesHelicopthère(Action.GROUPEHELICO,possesseurCarte,roles);
        }
        


    }


    public void appliquerDeplacementhelicoptere(MessageGroupePos messageGroupePos) {
        
        for(Role r:messageGroupePos.getRoles()){
            vueGrille.actualiserPositionJoueur(messageGroupePos.getPos(), joueurs.get(r).getPosition(), joueurs.get(r).getPion());
            joueurs.get(r).setTuile(getGrille(), getGrille().getTuile(messageGroupePos.getPos()));
        }
        
        //retire la carte
        joueurs.get(messageGroupePos.getRole()).removeCarte(stringToCarte("Helicoptere", messageGroupePos.getRole()));
        defausseTirage.add(stringToCarte("Helicoptere", messageGroupePos.getRole()));
        vuePrincipale.getPanelAventuriers().get(messageGroupePos.getRole()).actualiserVueAventurier(joueurs.get(messageGroupePos.getRole()).cartesToString());
    }

    /**
     * S'occupe de toute les opérations(logique applicative)
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {

        //Si arg est de type messageCarte        
        if (arg instanceof MessageCarte) {
            MessageCarte messageCarte = (MessageCarte) arg;
            switch (messageCarte.getAction()) {
                //Si le message possède l'action DEFAUSSER
                case DEFAUSSER:
                    appliquerDefausse(messageCarte);
                    break;
                //Si le message possède l'action DONNER
                case DONNER:
                    appliquerDon(messageCarte);
                    break;
                //Si le message possède l'action RECEVOIR
                case RECEVOIR:
                    appliquerRecevoir(messageCarte);
                    break;
                //Si le message possède l'action CARTESPECIALE
                case CARTESPECIALE:
                    appliquerCartesSpeciales(messageCarte);
                    break;
            }
        } else //Si arg est de type MessagePos
        if (arg instanceof MessagePos) {
            MessagePos messagepos = (MessagePos) arg;
            vueGrille.tousBoutonsInertes();
            switch (messagepos.getAction()) {
                //Si le message possède l'action DONNER
                case DEPLACER:
                    appliquerDeplacement(messagepos);
                    break;
                //Si le message possède l'action SUIVRE
                case SUIVRE:
                    appliquerNavigation(messagepos);
                    break;
                //Si le message possède l'action ASSECHER
                case ASSECHER:
                    appliquerAssechement(messagepos);
                    break;
                //Si le message possède l'action ASSECHERSACDESABLE
                case CARTESPECIALE:
                    appliquerAssechementSacDeSable(messagepos);
                    break;

            }
        } else if (arg instanceof MessageGroupePos) {
            MessageGroupePos messageGroupePos = (MessageGroupePos) arg;
            vueGrille.tousBoutonsInertes();
            switch (messageGroupePos.getAction()) {
                case GROUPEHELICO:
                        appliquerDeplacementhelicoptere(messageGroupePos);
                    break;
            }    
        } else if (arg instanceof MessageGroupe) {
            MessageGroupe messageGroupe = (MessageGroupe) arg;
            vueGrille.tousBoutonsInertes();
            switch (messageGroupe.getAction()) {
                case GROUPEHELICO:
                    gererGroupeHelicoptere(messageGroupe.getRole(), messageGroupe.getRoles());
                    break;
            }
        } else if (arg instanceof Message) {
            Message message = (Message) arg;
            //Si le message contient une Action
            if (null != message.getAction()) {
                vueGrille.tousBoutonsInertes();
                switch (message.getAction()) {
                    //Si le message possède l'action DEPLACER
                    case DEPLACER:
                        gererDeplacement();
                        break;
                    //Si le message possède l'action ASSECHER
                    case ASSECHER:
                        gererAssechement();
                        break;
                    //Si le message possède l'action TERMINER
                    case TERMINER:
                        nextTurn();
                        break;
                    //Si le message possède l'action DONNER
                    case DONNER:
                        gererDon();
                        break;
                    //Si le message possède l'action SUIVRE
                    case SUIVRE:
                        gererNaviguation(message.getRole());
                        break;
                    //Si le message possède l'action RECUPERER
                    case RECUPERER_TRESOR:
                        gererRecupTresor();
                        break;
                }
            }
        } else {
            System.out.println("Message non traité");
        }
        actualiserVue(arg);
        actualiserModele(arg);
    }

    public CarteTirage stringToCarte(String nomCarte, Role role) {
        CarteTirage carteSelection = null;
        for (CarteTirage carte : joueurs.get(role).getCartes()) {
            if (carte.getNom().equals(nomCarte)) {
                carteSelection = carte;
            }
        }
        return carteSelection;
    }

    /**
     * On remet les cartes de la défausse sur le haut de la pioche des cartes
     * d'inondation puis on pioches dans cette pioche un nombre de cartes
     * dépendant du niveau d'eau
     *
     * @return une liste de CarteInondation
     */
    public ArrayList<CarteInondation> tirerCartesInondation() {
        ArrayList<CarteInondation> cartesTirees = new ArrayList<>();
        //1,2 -> 2 cartes
        //3,4,5 -> 3 cartes
        //6,7 -> 4 cartes
        //8,9 -> 5 cartes
        int j;
        if (niveauEau < 8) {
            j = 2 + niveauEau / 3;
        } else {
            j = 5;
        }

        //Pour chauqe niveau d'eau
        for (int i = 0; i < j; i++) {
            //Si la pioche inondation n'est pas vide
            if (!piocheInondation.isEmpty()) {
                cartesTirees.add(getPiocheInondation().get(getPiocheInondation().size() - 1));
                getPiocheInondation().remove(getPiocheInondation().size() - 1);
                //Si la pioche inondation est vide
            } else {
                Collections.shuffle(defausseInondation);
                getPiocheInondation().addAll(defausseInondation);
                defausseInondation.clear();
            }
        }
        return cartesTirees;
    }

    /**
     * On tire 2 cartes qu'on donne a l'aventurier courant
     */
    public void tirerCartes() {
        ArrayList<CarteTirage> cartes = new ArrayList<>();
        Boolean trigger = false;
        //Pour le nombre de cartes qu'on veut donner
        for (int i = 0; i < 2; i++) {
            //Si la pioche n'est pas vide
            if (!piocheTirage.isEmpty()) {
                //Si la prochaine carte est une carte montée des eaux
                if (piocheTirage.get(piocheTirage.size() - 1) instanceof CarteMonteeDesEaux) {
                    trigger = true;
                    niveauEau += 1;
                    defausseTirage.add(piocheTirage.get(piocheTirage.size() - 1));
                    //Si la prochaine carte n'est pas une carte montée des eaux    
                } else {
                    cartes.add(piocheTirage.get(piocheTirage.size() - 1));
                }
                piocheTirage.remove(piocheTirage.get(piocheTirage.size() - 1));
                // Si la pioche est vide    
            } else {
                Collections.shuffle(defausseTirage);
                piocheTirage.addAll(defausseTirage);
                defausseTirage.clear();
            }

        }
        if (trigger) {
            Collections.shuffle(defausseInondation);
            getPiocheInondation().addAll(defausseInondation);
            defausseInondation.clear();
        }
        try {
            aventurierCourant.addCartes(cartes);
        } catch (ExceptionAventurier e) {
            aventurierCourant.defausseCartes();

        }

        vuePrincipale.getPanelAventuriers().get(aventurierCourant.getRole()).actualiserVueAventurier(joueurs.get(aventurierCourant.getRole()).cartesToString());
    }

    public void gererDon() {
        Boolean yes = false;
        if (aventurierCourant.getRole() == Role.Messager) {
            yes = true;
        }
        for (Role role : joueurs.keySet()) {
            if (joueurs.get(role).getTuile().equals(aventurierCourant.getTuile()) && !joueurs.get(role).equals(aventurierCourant)) {
                yes = true;
            }
        }
        if (yes) {
            vuePrincipale.getPanelAventuriers().get(aventurierCourant.getRole()).rendreCartesCliquables(aventurierCourant.cartesTresor());
        }
    }

    public void appliquerDon(MessageCarte messageCarte) {
        for (Role aventurier : joueurs.keySet()) {
            if (aventurier != aventurierCourant.getRole() && (aventurierCourant.getTuile() == joueurs.get(aventurier).getTuile() || aventurierCourant.getRole() == Role.Messager)) {
                vuePrincipale.getPanelAventuriers().get(aventurier).devenirReceveur(messageCarte.getNomCarte());
            }
        }
    }

    private void gererRecupTresor() {
        if (aventurierCourant.tresorRecuperable() != null) {
            recupererTresor();
        }

    }

    public void recupererTresor() {
        Tresor tresor = aventurierCourant.tresorRecuperable();
        if (tresor != null) {
            switch (tresor) {
                case CALICE:
                    System.out.println("Calice Récupéré");
                    break;
                case CRISTAL:
                    System.out.println("Cristal Récupéré");
                    break;
                case PIERRE:
                    System.out.println("Pierre Récupérée");
                    break;
                case ZEPHYR:
                    System.out.println("Zephyr Récupéré");
                    break;
            }
            aventurierCourant.removeCartesTresor(tresor);

            vuePrincipale.getPanelAventuriers().get(aventurierCourant.getRole()).actualiserVueAventurier(aventurierCourant.cartesToString());
            aventurierCourant.decrementeNbActions();
        }
    }

    private void checkImage(Tresor tresor) {
        //à compléter

    }

    private void resetButtons() {
        for (Bouton b : Bouton.values()) {
            vuePrincipale.activerBouton(b, false);
        }
    }

    private void updateBoutons() {
        vuePrincipale.activerBouton(Bouton.DEPLACER, !aventurierCourant.calculDeplacement(grille).isEmpty());

        vuePrincipale.activerBouton(Bouton.ASSECHER, !aventurierCourant.calculAssechement(grille).isEmpty());

        Boolean peutDonner = true;
        ArrayList<Aventurier> avSurCase = aventurierCourant.getTuile().getAventuriers();
        if ((avSurCase.size() <= 1 || aventurierCourant.getCartes().isEmpty()) && aventurierCourant.getRole() != Role.Messager) {
            peutDonner = false;
        }
        vuePrincipale.activerBouton(Bouton.DONNER, peutDonner);

        vuePrincipale.activerBouton(Bouton.RECUPERER, aventurierCourant.tresorRecuperable() != null);
    }

    /**
     * @return the piocheInondation
     */
    public ArrayList<CarteInondation> getPiocheInondation() {
        return piocheInondation;
    }

    /**
     * @return the grille
     */
    public Grille getGrille() {
        return grille;
    }
}
