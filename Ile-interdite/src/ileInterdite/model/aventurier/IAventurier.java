/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.model.aventurier;

import ileInterdite.message.MessageCarte;
import ileInterdite.model.cartes.CarteTirage;
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
public class IAventurier extends Observable{
    private JButton boutonAventurier;
    private ActionListener actions;
    private Role role;
    
    public IAventurier(JButton boutonAventurier,ActionListener actions,Role role){
        this.actions=actions;
        this.boutonAventurier=boutonAventurier;
        this.role=role;
    }

    public void rendreAventurierCliquable(String carte){
         
        setActions(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent arg0) {
               setChanged();
               notifyObservers(new MessageCarte(carte,Action.RECEVOIR,role));
               clearChanged();
           }
       });
    }
    
   
    /**
     * @return the boutonAventurier
     */
    public JButton getBoutonAventurier() {
        return boutonAventurier;
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
}
