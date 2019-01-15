/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.model.aventurier;

import ileInterdite.message.Message;
import ileInterdite.message.MessageCarte;
import java.awt.Color;
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
    private Role role;
    private ActionListener l;
    
    public IAventurier(JButton boutonAventurier,Role role){
        this.boutonAventurier=boutonAventurier;
        this.role=role;
        
    }

    public void devenirReceveur(String carte,Boolean suivre){
        l = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setChanged();
                notifyObservers(new MessageCarte(carte,Action.RECEVOIR, getRole()));
                clearChanged();
            }
        };       
        devenirCliquable(l,suivre);
    }

    public void devenirSuiveur(Boolean suivre){
        l = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setChanged();
                notifyObservers(new Message(Action.SUIVRE, getRole()));
                clearChanged();
            }
        };
        devenirCliquable(l,suivre);
    }
    
    public void devenirCliquable(ActionListener al,Boolean b){
        if (b){
           boutonAventurier.addActionListener(al);
           boutonAventurier.setForeground(Color.RED);
        }
        else{
           boutonAventurier.removeActionListener(al);
           boutonAventurier.setForeground(null);
        }
    }
    
    /**
     * @return the boutonAventurier
     */
    public JButton getBoutonAventurier() {
        return boutonAventurier;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }


}
