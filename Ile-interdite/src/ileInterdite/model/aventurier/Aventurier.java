/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.model.aventurier;


import utilitaires.*;
import ileInterdite.model.Grille;
import ileInterdite.model.Position;
import ileInterdite.model.Tuile;
import ileInterdite.model.cartes.*;
import ileInterdite.vues.*;
import java.util.ArrayList;


/**
 *
 * @author vinetg
 */
public abstract class Aventurier {
    private int nbAction;
    private Tuile tuile;
    private Boolean pouvoir = true;
    private Role role;
    private String nomJoueur;
    private Pion pion;
    private ArrayList<CarteTirage> cartes=new ArrayList<>();
    private VueDefausse vueDefausse = new VueDefausse();
    
    
    public Aventurier(String nomJoueur,Tuile tuile) {
        this.tuile=tuile;
        this.nomJoueur=nomJoueur;
        this.tuile = tuile;
        reset();
    }
    
    
    /**
     * On renvoie la liste des tuiles adjacentes en croix
     * @param g
     * @return 
     */
    public ArrayList<Tuile> calculDeplacement(Grille g){
        return g.tuilesAdjacentesCroix(getTuile()); 
    }
    public ArrayList<Tuile> calculGuide(Grille g){
        ArrayList<Tuile> tuiles = new ArrayList<>();
        for (Tuile t : g.tuilesAdjacentesCroix(getTuile())) {
            if (t.getEtat()!=EtatTuile.COULEE){
                tuiles.add(t);
                for (Tuile tt : g.tuilesAdjacentesCroix(t)) {
                    if (tt !=getTuile()){
                        tuiles.add(tt);
                    }
                }
            }
        }
        return tuiles;
    }
    
    /**
     * On renvoie la liste des tuiles adjacentes en croix
     * @param g
     * @return CarteTirage> 
     */
    public ArrayList<Tuile> calculAssechement(Grille g){
        ArrayList<Tuile> liste = new ArrayList();
        if (getTuile().getEtat() == EtatTuile.INONDEE){
            liste.add(getTuile());
        }
        for (Tuile t : g.tuilesAdjacentesCroix(getTuile())){
            if (t.getEtat() == EtatTuile.INONDEE){
                liste.add(t);
            }
        }
        return liste;
    }
    
    public void defausseCartes(){
        vueDefausse.actualiser(cartesToString(),role);
    }
 
    
    /**
     * On remet le nombre d'actions à 3
     */
    public void reset(){
        setNbAction(3);
        setPouvoir(true);
    }
    public ArrayList<Integer> cartesTresor(){
        ArrayList<Integer> cartesTresor = new ArrayList<>();
        for (CarteTirage carte : cartes){
            if(!carte.getUtilisable()){
                cartesTresor.add(cartes.indexOf(carte));
            }
        }
        return cartesTresor;
    }
    /**
     * Fais diminuer le nombre d'actions de 1
     */
    public void decremente(){
        setNbAction(getNbAction() - 1);
    }

    /**
    * Permet de déplacer un aventurier sur une nouvelle Tuile
    * en supprimant sa position précédente
     * @param tuile
    */
    public void setTuile(Tuile tuile) {
        tuile.removeAventurier(this);
        tuile.addAventurier(this);
        this.tuile = tuile;
    }

    
    public void addCartes(ArrayList<CarteTirage> cartes) throws ExceptionAventurier{
        for (CarteTirage c : cartes){
        this.cartes.add(c);
        }
        if (this.cartes.size()>5){
            throw new ExceptionAventurier(this);
        }
    }
        
        
    public ArrayList<String> cartesToString(){
        ArrayList<String> cartes = new ArrayList<>();
        if (!cartes.isEmpty()){
            for(CarteTirage carte : this.cartes){
                cartes.add(carte.getNom());
            }
        } else { cartes =null;}
        return cartes;
    }
    
    public void recupererTresor(){
       
    }
    
    
    public void removeCarte(CarteTirage carte){
        cartes.remove(carte);
        System.out.println(cartes);
    }
    
    
    public Boolean peutRecupererTresor(){

       return true;
       
    }
   
    //Getters et Setters :
    
    
    public Position getPosition() {
        return getTuile().getPosition();
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
     * @return the Role
     */
    public Role getRole() {
        return role;
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
     * @param Role the Role to set
     */
    public void setRole(Role role) {
        this.role = role;
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
     * @param nbAction the nbAction to set
     */
    public void setNbAction(int nbAction) {
        this.nbAction = nbAction;
    }

    /**
     * @return the cartes
     */
    public ArrayList<CarteTirage> getCartes() {
        return cartes;
    }

    /**
     * @param cartes the cartes to set
     */
    public void setCartes(ArrayList<CarteTirage> cartes) {
        this.cartes = cartes;
    }

    /**
     * @return the vueDefausse
     */
    public VueDefausse getVueDefausse() {
        return vueDefausse;
    }
}
