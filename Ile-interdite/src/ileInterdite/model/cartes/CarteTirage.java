/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.model.cartes;

import utilitaires.Action;

/**
 *
 * @author vinetg
 */
public class CarteTirage {
    private String nom;
    private Action action;
    private Boolean utilisable;
    
    public CarteTirage(Boolean utilisable){
        this.utilisable=utilisable;
    }
    
    public CarteTirage(CarteTirage old) {
        this.nom = old.nom;
        this.action = old.action;
        this.utilisable = old.utilisable;
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
     * @return the utilisable
     */
    public Boolean getUtilisable() {
        return utilisable;
    }

    /**
     * @param utilisable the utilisable to set
     */
    public void setUtilisable(Boolean utilisable) {
        this.utilisable = utilisable;
    }
    
    public Action getAction() {
        return action;
    }
}
