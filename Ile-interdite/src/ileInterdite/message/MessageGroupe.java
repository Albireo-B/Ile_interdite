/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.message;

import java.util.ArrayList;
import utilitaires.Action;
import utilitaires.Role;

/**
 *
 * @author vinetg
 */
public class MessageGroupe extends Message {

    private ArrayList<Role> roles = new ArrayList<>();

    /**
     * On définit le constructeur de message avec une action Action
     *
     * @param action
     * @param role
     * @param roles
     */
    public MessageGroupe(Action action,Role role, ArrayList<Role> roles) {
        super(action,role);
        this.roles = roles;
    }

    //Getters et Setters :
    
    /**
     * @return the role
     */
    public ArrayList<Role> getRoles() {
        return roles;
    }


}
