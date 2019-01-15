/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import ileInterdite.message.MessageCarte;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import utilitaires.Action;
import utilitaires.Role;

/**
 *
 * @author vinetg
 */
public class ICarte extends JButton {

    private String nom;
    private Action action;

    private ActionListener actionListener = null;

    public ICarte(String nom, Action action) {
        this.nom = nom;
        this.action = action;
    }

    public void removeActionListener() {
        removeActionListener(actionListener);
        actionListener = null;
        setForeground(null);
    }

    @Override
    public void addActionListener(ActionListener act) {
        if (actionListener == null) {
            actionListener = act;
            super.addActionListener(act);
            setForeground(Color.red);
        } else {
            System.out.println("ERREUR: N'essayez pas d'ajouter deux actionlisteners");
        }
    }

    public MessageCarte getMessage(Role role) {
        return new MessageCarte(nom, action, role);
    }


    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
        setText(nom);
    }

    /**
     * @param action the action to set
     */
    public void setAction(Action action) {
        this.action = action;
    }

}
