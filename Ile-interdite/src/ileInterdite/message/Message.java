/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.message;

/**
 *
 * @author vinetg
 */
public class Message {
    private Action action;
    
    /**
     * On définit le constructeur de message avec une action Action
     * @param action 
     */
    public Message(Action action){
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
     * @param action the action to set
     */
    public void setAction(Action action) {
        this.action = action;
    }
}
