/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.message;

import utilitaires.Action;
import utilitaires.Role;

/**
 *
 * @author vinetg
 */
public class Message{

    
    private Role role;
    private Action action;

    
        
    /**
     * On définit le constructeur de message avec une action Action
     *
     * @param action
     * @param role
     */
    public Message(Action action, Role role) {

        this.role = role;
        this.action = action;
    }

    //Getters et Setters :
    /**
     * @return the action
     */
    public Action getAction() {
        return action;
    }
    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }


}
