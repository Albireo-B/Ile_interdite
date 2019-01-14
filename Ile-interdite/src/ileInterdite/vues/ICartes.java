/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import ileInterdite.message.MessageCarte;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JPanel;
import utilitaires.Action;
import utilitaires.Role;

/**
 *
 * @author vinetg
 */
public class ICartes extends JButton {
    private Role role;
    private Action action;
    private String texte;
    
    public ICartes(String texte, Role role, Action action){
        super(texte);
        this.role=role;
        this.texte = texte;
    }
    
    public MessageCarte getMessage() {
        return new MessageCarte(texte, action, role);
    }

    /**
     * @return the actions
     */
    public Action getActionCarte() {
        return action;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * @param action the action to set
     */
    public void setActionCarte(Action action) {
        this.action = action;
    }
}
