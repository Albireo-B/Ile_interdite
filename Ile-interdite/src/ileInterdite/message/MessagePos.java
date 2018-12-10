/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.message;

import ileInterdite.Position;
import ileInterdite.Tuile;
import ileInterdite.actions.*;

/**
 *
 * @author vinetg
 */
public class MessagePos extends Message{
    private Position pos;

    public MessagePos(Action action, Position pos){
       super(action);
       this.pos = pos;
    }
    
    /**
     * @return the case
     */
    public Position getPosition() {
        return pos;
    }
}
