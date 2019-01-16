/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.message;

import ileInterdite.model.Position;
import java.util.ArrayList;
import utilitaires.Action;
import utilitaires.Role;

/**
 *
 * @author vinetg
 */
public class MessageGroupe extends MessagePos {
        
    
    
    public MessageGroupe(Action action,Position pos,ArrayList<Role> roles){
        super(action,pos,roles.get(0));
        
    }
}
