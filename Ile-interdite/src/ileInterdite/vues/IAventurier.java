/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import ileInterdite.message.Message;
import ileInterdite.message.MessageCarte;
import ileInterdite.message.MessageGroupe;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;
import utilitaires.Action;
import utilitaires.Role;

/**
 *
 * @author vinetg
 */
public class IAventurier extends Observable {

    private JButton boutonAventurier;
    private Role role;
    private ActionListener actionListener;
    private Border bordureNoire;
    private Border bordureRouge;
    
    
    

    public IAventurier(JButton boutonAventurier, Role role) {
        bordureNoire = BorderFactory.createLineBorder(Color.black,3);
        bordureRouge = BorderFactory.createLineBorder(Color.red,3);
        this.boutonAventurier = boutonAventurier;
        this.role = role;
    }

    public void devenirReceveur(String carte) {
        if (carte != null) {
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    setChanged();
                    notifyObservers(new MessageCarte(carte, Action.RECEVOIR, role));
                    clearChanged();
                }
            });
        } else {
            removeActionListener();
        }
    }

    public void devenirSuiveur(Boolean suivre) {
        if (suivre){
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setChanged();
                notifyObservers(new Message(Action.SUIVRE, role));
                clearChanged();
            }
        });} else {
            removeActionListener();
        }
    }
    
    public void proposerHelico(Role possesseurCarte, ArrayList<Role> roles,Boolean suivre){
        ArrayList<Role> newRoles = new ArrayList(roles);
        if (suivre){
        newRoles.add(role);
        removeActionListener();
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setChanged();
                notifyObservers(new MessageGroupe(Action.GROUPEHELICO,possesseurCarte, newRoles));
                clearChanged();
            }
        });
        boutonAventurier.setBackground(null);
        }else{
            newRoles.remove(role);
            removeActionListener();
             addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setChanged();
                notifyObservers(new MessageGroupe(Action.GROUPEHELICO,possesseurCarte, newRoles));
                clearChanged();
            }
        });
            boutonAventurier.setBackground(Color.red);
        }
    }
    
    
    public void removeActionListener() {
        boutonAventurier.removeActionListener(actionListener);
        actionListener = null;
        boutonAventurier.setBorder(null);
    }


    public void addActionListener(ActionListener act) {
        if (actionListener == null) {
            actionListener = act;
            boutonAventurier.addActionListener(act);
            boutonAventurier.setBorder(bordureRouge);
        } else {
            System.out.println("ERREUR IAventurier: N'essayez pas d'ajouter deux actionlisteners");
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
