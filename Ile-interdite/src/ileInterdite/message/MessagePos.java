/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.message;

import ileInterdite.Tuile;
import ileInterdite.actions.*;

/**
 *
 * @author vinetg
 */
public class MessagePos extends Message{
    private Tuile tuile;

    public MessagePos(Action action,Tuile tuile){
       super(action);  
       setTuile(tuile);
    }
    
    /**
     * @return the case
     */
    public Tuile getTuile() {
        return tuile;
    }

    /**
     * @param tuile the case to set
     */
    public void setTuile(Tuile tuile) {
        this.tuile = tuile;
    }
}
