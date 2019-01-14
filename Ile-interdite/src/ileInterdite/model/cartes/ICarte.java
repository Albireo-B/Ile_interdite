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
    private ActionListener actions;
    private Role role; 
    
    public ICarte(JButton boutonCarte,ActionListener actions,Role role){
        this.actions=actions;
        this.boutonCarte=boutonCarte;
        this.role=role;
    }
    
    
    public void rendreCarteCliquable(){
        System.out.println("cartes Cliquables");
        setActions((ActionEvent arg0) -> {
            setChanged();
            notifyObservers(new MessageCarte(boutonCarte.getText(),Action.DONNER,getRole()));
            clearChanged();
        });
        
        
        //rendre les joueurs cliquables
    }
    
    

    /**
     * @return the boutonCarte
     */
    public JButton getBoutonCarte() {
        return boutonCarte;
    }

    /**
     * @return the actions
     */
    public ActionListener getActions() {
        return actions;
    }

    /**
     * @param actions the actions to set
     */
    public void setActions(ActionListener actions) {
        this.actions = actions;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }
    
}
