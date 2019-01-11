/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.controleur;


import ileInterdite.model.aventurier.*;
import utilitaires.*;
import ileInterdite.model.*;
import ileInterdite.message.*;
import ileInterdite.model.cartes.*;
import ileInterdite.vues.*;
import java.util.ArrayList;
import ileInterdite.vues.VuePrincipale;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author vinetg
 */
public class Controleur implements Observer {

    private VuePrincipale vueAventurier;
    private VueGrille vueGrille;
    private ArrayList<Aventurier> joueurs = new ArrayList<>();//à modifier en [4]
    private Grille grille;
    private Aventurier aventurierCourant;
    private ArrayList<CarteInondation> piocheInondation=new ArrayList<>();
    private ArrayList<CarteInondation> defausseInondation=new ArrayList<>();
    private ArrayList<CarteTirage> piocheTirage=new ArrayList<>();
    private ArrayList<CarteTirage> defausseTirage=new ArrayList<>();
    private int niveauEau;

    /**
     * On définit le constructeur du controleur avec une liste d'aventuriers
     * joueurs et une Grille grille
     *
     * @param nomsjoueurs
     * @param roles
     * @param nomTuiles
     */
    public Controleur(ArrayList<String> nomsjoueurs,ArrayList<Role> roles, ArrayList<String> nomTuiles, ArrayList<CarteTirage> pioche, int niveauEau) {
        //Initialisation du niveau d'eau
        this.niveauEau=niveauEau;
        
        
        //Initialisation de la Grille
        grille = new Grille(nomTuiles);

        ArrayList<Position> posTuiles = new ArrayList();
        ArrayList<String> nomsTuiles = new ArrayList();
        for (Tuile t : grille.getToutesTuiles()) {
            posTuiles.add(t.getPosition());
            nomsTuiles.add(t.getNom());
        }

        for (String nom : nomsTuiles){
            piocheInondation.add(new CarteInondation(nom));
        }
        
        piocheTirage = pioche;
        
        //initialiser main des joueurs
        initCartes();
        
        vueGrille = new VueGrille(posTuiles, nomsTuiles);
        vueGrille.addObserver(this);
        
        //Initialisation des joueurs et du joueur courant
        setRoles(nomsjoueurs,roles);
        aventurierCourant = joueurs.get(0);

        
        for (Aventurier j : joueurs) {
            vueGrille.actualiserPositionJoueur(j.getPosition(), null, j.getPion());
        }
        

        // Création des vues aventurier
        vueAventurier = new VuePrincipale(getVueGrille());
        vueAventurier.addObserver(this);
        vueAventurier.actualiserVue(aventurierCourant.getNomJoueur(),
                                    aventurierCourant.getRole(),
                                    aventurierCourant.getPion().getCouleur(),
                                    aventurierCourant.getNbAction()
                                    );
    }
    
    public void initCoule() {
        for (int i = 0 ; i < 6 ; i ++){
            piocheInondation.get(piocheInondation.size()-1);
        }
    }
    
    public void initCartes() {
        for (Aventurier a : joueurs){
                ArrayList<CarteTirage> cartes = new ArrayList<>();
                for (int i = 0; i<2;i++){
                    while (piocheTirage.get(piocheTirage.size()-1) instanceof CarteMonteeDesEaux){
                        Collections.shuffle(piocheTirage);
                    }
                    cartes.add(piocheTirage.get(piocheTirage.size()-1));
                    piocheTirage.remove(piocheTirage.size()-1);
                }
                try{
                a.addCartes(cartes);
                }
                catch (ExceptionAventurier ex){};
        }
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
     * Fonction globale qui gère l'inondation de fin de tour
     */
    public void gererInondation(){
        //Pour toutes les cartes à inonder
        for (CarteInondation carteAInonder : tirerCartesInondation()){
            //Pour toutes les tuiles
            for (Tuile tuile : grille.getToutesTuiles()){
                //Si La tuile a le même nom que la carte à inonder
                if (tuile.getNom().equals(carteAInonder.getNom())){

                    try {
                        monteeDesEaux(tuile.getPosition());
                    } 
                    catch (ExceptionAventurier ex) {
                        System.out.println("L'aventurier " + ex.getAventurier().getNomJoueur() +" ne peut plus se déplacer !");
                        
                        
                        //à compléter
                        
                        
                    }
                    //Si la tuile est coulée
                    if (tuile.getEtat()!=EtatTuile.COULEE){
                        defausseInondation.add(carteAInonder);
                    }
                }
            }
        }
    }


    /**
     * Augmente de le niveau d'eau d'une tuile, et vérifie qu'aucun aventurier n'est présent sur l'île,
     * et si oui lui propose de se déplacer
     * @param p 
     */
    public void monteeDesEaux(Position p) throws ExceptionAventurier {
        //Si le tuile est inondée
        if (getGrille().getTuile(p).getEtat()==EtatTuile.INONDEE){
            getGrille().getTuile(p).setEtat(EtatTuile.COULEE);
            getVueGrille().actualiserEtatTuile(p, EtatTuile.COULEE);
            
            for (Aventurier aventurier : joueurs){
                if (aventurier.getTuile().getEtat()==EtatTuile.COULEE){
                    System.out.println("2");
                    if (!aventurier.calculDeplacement(grille).isEmpty()){
                        System.out.println("3");
                        proposerTuiles(aventurier.calculDeplacement(grille), Action.DEPLACER);
                    } else {
                        throw new ExceptionAventurier(aventurier);
                    }
                }
            }
                  
            
        }
        else{
            getGrille().getTuile(p).setEtat(EtatTuile.INONDEE);
            getVueGrille().actualiserEtatTuile(p, EtatTuile.INONDEE);
        }
    }
    
        //OBLIGERDEPLAEMENT A FAIRE AVEC MESSAGE URGENCE (AZventurier, pos) et TOUS BOUTONS NOIRS SEULEMENT ANNULER
        
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
        tirerCartes();
        gererInondation();
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
    
    /**
    * On remet les cartes de la défausse sur le haut de la pioche des cartes d'inondation
    * puis on pioches dans cette pioche un nombre de cartes dépendant du niveau d'eau
    * @return une liste de CarteInondation
    */
    public ArrayList<CarteInondation> tirerCartesInondation(){
        ArrayList<CarteInondation> cartesTirees = new ArrayList<>();
        //1,2 -> 2 cartes
        //3,4,5 -> 3 cartes
        //6,7 -> 4 cartes
        //8,9 -> 5 cartes
        int j = 2;
        System.out.println("WTF");
        if (niveauEau<8){
        j += niveauEau / 3;}
        else{
            j = 5;
        }
        
        //Pour chauqe niveau d'eau
        for (int i=0;i<j;i++){
            //Si la pioche inondation n'est pas vide
            if (!piocheInondation.isEmpty()){
            cartesTirees.add(piocheInondation.get(piocheInondation.size()-1));
            piocheInondation.remove(piocheInondation.size()-1);
            //Si la pioche inondation est vide
            } else {
                Collections.shuffle(defausseInondation);
                piocheInondation.addAll(defausseInondation);
                defausseInondation.clear();
            }
        }
        return cartesTirees;       
    }
        
    /**
     * On tire 2 cartes qu'on donne a l'aventurier courant
    */
    public void tirerCartes(){
        ArrayList<CarteTirage> cartes = new ArrayList<>();
        ArrayList<CarteInondation> cartesARemettreEnPioche= new ArrayList<>();
        Boolean trigger = false;
        //Pour le nombre de cartes qu'on veut donner
        for (int i=0;i<2;i++){
            //Si la pioche n'est pas vide
            if (!piocheInondation.isEmpty()) {
                //Si la prochaine carte est une carte montée des eaux
                if (piocheTirage.get(piocheTirage.size()-1) instanceof CarteMonteeDesEaux){
                    trigger=true;
                    niveauEau+=1;
                    System.out.println("TRIGGGGGGGGGGERD");
                    defausseTirage.add(piocheTirage.get(piocheTirage.size()-1));
                }
                //Si la prochaine carte n'est pas une carte montée des eaux
                else {
                    cartes.add(piocheTirage.get(piocheTirage.size()-1));
                }
                piocheTirage.remove(piocheTirage.get(piocheTirage.size()-1));    
            // Si la pioche est vide    
            } else { 
                Collections.shuffle(defausseInondation);
                piocheInondation.addAll(defausseInondation);
                defausseInondation.clear();
            }
        }
        if (trigger){
                
            cartesARemettreEnPioche.addAll(defausseInondation);
            defausseInondation.clear();
                
            Collections.shuffle(cartesARemettreEnPioche);
            piocheInondation.addAll(cartesARemettreEnPioche); 
          
            gererInondation();
        }
        try{
        aventurierCourant.addCartes(cartes);
        } catch (ExceptionAventurier e) { 
            defausseCartes(e.getAventurier());
        }
    }
    

    public void defausseCartes(Aventurier aventurier){
        //à compléter        
    }
   
    public void donnerCartes(Aventurier aventurier){
        //à compléter
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
    public VuePrincipale getVueAventurier() {
        return vueAventurier;
    }

    /**
     * @param vueAventurier the vueAventurier to set
     */
    public void setVueAventurier(VuePrincipale vueAventurier) {
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
     * @return the niveauEau
     */
    public int getNiveauEau() {
        return niveauEau;
    }

    /**
     * @param niveauEau the niveauEau to set
     */
    public void setNiveauEau(int niveauEau) {
        this.niveauEau = niveauEau;
    }

    /**
     * @param piocheInondation the piocheInondation to set
     */
    public void setPiocheInondation(ArrayList<CarteInondation> piocheInondation) {
        this.piocheInondation = piocheInondation;
    }

    /**
     * @param defausseInondation the defausseInondation to set
     */
    public void setDefausseInondation(ArrayList<CarteInondation> defausseInondation) {
        this.defausseInondation = defausseInondation;
    }

    /**
     * @param piocheTirage the piocheTirage to set
     */
    public void setPiocheTirage(ArrayList<CarteTirage> piocheTirage) {
        this.piocheTirage = piocheTirage;
    }

    /**
     * @param defausseTirage the defausseTirage to set
     */
    public void setDefausseTirage(ArrayList<CarteTirage> defausseTirage) {
        this.defausseTirage = defausseTirage;
    }

    /**
     * @return the piocheInondation
     */
    public ArrayList<CarteInondation> getPiocheInondation() {
        return piocheInondation;
    }

    /**
     * @return the defausseInondation
     */
    public ArrayList<CarteInondation> getDefausseInondation() {
        return defausseInondation;
    }

    /**
     * @return the piocheTirage
     */
    public ArrayList<CarteTirage> getPiocheTirage() {
        return piocheTirage;
    }

    /**
     * @return the defausseTirage
     */
    public ArrayList<CarteTirage> getDefausseTirage() {
        return defausseTirage;
    }
    

}
