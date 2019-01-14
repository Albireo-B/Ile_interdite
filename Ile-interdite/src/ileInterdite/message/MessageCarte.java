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
    private Role role;
    
    public MessageCarte(String nomCarte,Action a,Role role){
        super(a);
        this.nomCarte=nomCarte;
        this.role=role;
    }

    /**
     * @return the nomCarte
     */
    public String getNomCarte() {
        return nomCarte;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }
}
