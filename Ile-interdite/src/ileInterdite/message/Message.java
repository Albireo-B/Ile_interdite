/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.message;

import ileInterdite.actions.*;

/**
 *
 * @author vinetg
 */
public class Message {
    private Action action;
        
    public Message(Action action){
        setAction(action);
    }

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
