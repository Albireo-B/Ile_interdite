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
public class MessageAventurier extends Message{
    
    private Role role;
    
    public MessageAventurier(Action action,Role role){
        super(action);
        this.role=role;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }
}
