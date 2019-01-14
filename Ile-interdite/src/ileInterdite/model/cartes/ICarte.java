/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.model.cartes;

import ileInterdite.message.MessageCarte;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JButton;
import utilitaires.Action;
import utilitaires.Role;

/**
 *
 * @author vinetg
 */
public class ICarte extends Observable {
    
    private JButton boutonCarte;
    private Role role;
    
    public ICarte(JButton boutonCarte,Role role){
        this.boutonCarte=boutonCarte;
        this.role=role;
    }
    
    
    public void rendreCarteCliquable(){
        ActionListener l = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setChanged();
                notifyObservers(new MessageCarte(boutonCarte.getText(),Action.DONNER,getRole()));
                clearChanged();
                boutonCarte.removeActionListener(this);
            }
        };
        
        boutonCarte.addActionListener(l);
    
    }
    
    

    /**
     * @return the boutonCarte
     */
    public JButton getBoutonCarte() {
        return boutonCarte;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }
    
}
