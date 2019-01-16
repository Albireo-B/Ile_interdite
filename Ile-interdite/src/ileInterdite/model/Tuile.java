/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.model;

import utilitaires.EtatTuile;
import java.util.ArrayList;
import ileInterdite.model.aventurier.Aventurier;
import utilitaires.Role;
import utilitaires.Tresor;

/**
 *
 * @author grosa
 */
public class Tuile {

    private EtatTuile etat = EtatTuile.SECHE;
    private ArrayList<Aventurier> aventuriers = new ArrayList();
    private Position position;
    private String nom;
    private Tresor tresor = null;

    /**
     * On définit le constructeur de Tuile avec un string nom et une Position
     * pos
     *
     * @param nom
     * @param pos
     */
    public Tuile(String nom, Position pos) {
        this.nom = nom;
        this.position = pos;
        etat = EtatTuile.SECHE;
    }
    
    /**
     * On définit le constructeur de Tuile avec un string nom et une Position
     * pos
     *
     * @param nom
     * @param pos
     */
    public Tuile(String nom, Position pos, Tresor tresor) {
        this.nom = nom;
        this.position = pos;
        etat = EtatTuile.SECHE;
        this.tresor = tresor;
    }

    /**
     * Ajoute un aventurier a la liste des aventuriers
     *
     * @param av
     */
    public void addAventurier(Aventurier av) {
        aventuriers.add(av);
    }

    /**
     * Supprime un aventurier de la liste des aventuriers
     *
     * @param av
     */
    public void removeAventurier(Aventurier av) {
        aventuriers.remove(av);
    }

    //Getters et Setters :
    /**
     * @return the aventuriers
     */
    public ArrayList<Aventurier> getAventuriers() {
        return aventuriers;
    }
    public ArrayList<Role> getRoleAventuriers() {
        ArrayList<Role> roles = new ArrayList();
        for (Aventurier a:aventuriers){
            roles.add(a.getRole());
        }
                return roles;
    }

    /**
     * @param aventuriers the aventuriers to set
     */
    public void setAventuriers(ArrayList<Aventurier> aventuriers) {
        this.aventuriers = aventuriers;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the etat
     */
    public EtatTuile getEtat() {
        return etat;
    }

    /**
     * @param etat the etat to set
     */
    public void setEtat(EtatTuile etat) {
        this.etat = etat;
    }

    /**
     * @return the position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * @return the tresor
     */
    public Tresor getTresor() {
        return tresor;
    }

    /**
     * @param tresor the tresor to set
     */
    public void setTresor(Tresor tresor) {
        this.tresor = tresor;
    }
}
