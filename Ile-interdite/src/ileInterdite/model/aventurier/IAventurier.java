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
        l = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setChanged();
                notifyObservers(new Message(Action.SUIVRE, getRole()));
                clearChanged();
            }
        };
    }

    public void devenirReceveur(String carte){
        ActionListener l = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setChanged();
                notifyObservers(new MessageCarte(carte,Action.RECEVOIR, getRole()));
                clearChanged();
                boutonAventurier.removeActionListener(this);
                boutonAventurier.setBackground(null);
            }
        };
        
        boutonAventurier.addActionListener(l);
    }

    public void devenirSuiveur(Boolean suivre){
        if (suivre){
           boutonAventurier.addActionListener(l);
           boutonAventurier.setForeground(Color.RED);
        }
        else{
           boutonAventurier.removeActionListener(l);
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
