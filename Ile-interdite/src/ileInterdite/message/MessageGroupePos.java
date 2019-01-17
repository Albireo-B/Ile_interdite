/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.message;

import utilitaires.Action;
import ileInterdite.model.Position;
import java.util.ArrayList;
import utilitaires.Role;

/**
 *
 * @author vinetg
 */
public class MessageGroupePos extends MessageGroupe {

    private Position pos;

    /**
     * On définit le constructeur de MessageGroupePos avec une action Action et une
     * position Position
     *
     * @param action
     * @param pos
     * @param role
     * @param roles
     */
    public MessageGroupePos(Action action, Position pos,Role role, ArrayList<Role> roles) {
        super(action,role, roles);
        this.pos = pos;
    }

    //Getters et Setters :
    /**
     * @return the pos
     */
    public Position getPos() {
        return pos;
    }

}
