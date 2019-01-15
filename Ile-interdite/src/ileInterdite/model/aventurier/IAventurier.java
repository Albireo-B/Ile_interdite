/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.model.aventurier;

import ileInterdite.message.Message;
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
public class IAventurier extends Observable{
    private JButton boutonAventurier;
    private Role role;
    
    public IAventurier(JButton boutonAventurier,Role role){
        this.boutonAventurier=boutonAventurier;
        this.role=role;
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

    
    public void devenirSuiveur(){         
           ActionListener l = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setChanged();
                notifyObservers(new Message(Action.SUIVRE, getRole()));
                clearChanged();
                boutonAventurier.removeActionListener(this);
                boutonAventurier.setBackground(null);
            }
        };
        boutonAventurier.addActionListener(l);
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
