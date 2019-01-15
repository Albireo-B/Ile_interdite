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
public class Message {

    private Action action;
    Role role;

    /**
     * On définit le constructeur de message avec une action Action
     *
     * @param action
     */
    public Message(Action action, Role role) {
        this.action = action;
        this.role = role;
    }

    //Getters et Setters :
    /**
     * @return the action
     */
    public Action getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(Action action) {
        this.action = action;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }
}
