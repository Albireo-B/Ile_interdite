/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.message;

import utilitaires.Action;
import ileInterdite.model.Position;
import utilitaires.Role;

/**
 *
 * @author vinetg
 */
public class MessagePos extends Message{
    private Position pos;
    

    /**
     * On définit le constructeur de MessagePos avec une action Action
     * et une position Position
     * @param action
     * @param pos 
     */
    public MessagePos(Action action, Position pos,Role role){
       super(action,role);
       this.pos = pos;
    }
    
    //Getters et Setters :
    
    /**
     * @return the pos
     */
    public Position getPos() {
        return pos;
    }

    /**
     * @param pos the pos to set
     */
    public void setPos(Position pos) {
        this.pos = pos;
    }
}
