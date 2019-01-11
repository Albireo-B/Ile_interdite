/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.message;

import utilitaires.Role;
import utilitaires.Action;

/**
 *
 * @author vinetg
 */
public class MessageCarte extends Message {
    
    private String nomCarte;
    
    public MessageCarte(String nomcarte,Action a,Role role){
        super(a,role);
        this.nomCarte=nomCarte;
    }

    /**
     * @return the nomCarte
     */
    public String getNomCarte() {
        return nomCarte;
    }
}
