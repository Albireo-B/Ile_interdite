/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import utilitaires.Action;
import utilitaires.Role;

/**
 *
 * @author PC
 */
public class VueDefausse extends Observable {

    private JFrame fenetre;
    private JPanel panelCarte;
    private JPanel panelPrincipal;

    public VueDefausse() {

        fenetre = new JFrame();
        fenetre.setTitle("cartes");

        fenetre.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
        fenetre.setSize(600, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        fenetre.setLocation(dim.width / 2 - fenetre.getSize().width / 2, dim.height / 2 - fenetre.getSize().height / 2);
        fenetre.setResizable(true);

        panelPrincipal = new JPanel(new BorderLayout());
        JLabel explication = new JLabel("Vous avez plus de cinq cartes veuillez en défausser une :");

        panelCarte = new JPanel(new GridLayout(1, 1));

        panelPrincipal.add(explication, BorderLayout.NORTH);
        panelPrincipal.add(panelCarte, BorderLayout.CENTER);

        fenetre.add(panelPrincipal);
    }

    public void actualiser(ArrayList<String> listeCartes, Role role) {
        panelPrincipal.remove(panelCarte);

        panelCarte = new JPanel(new GridLayout(1, listeCartes.size()));
        for (String nomCarte : listeCartes) {
            ICarte buttonCarte = new ICarte(nomCarte, Action.DEFAUSSER);
            buttonCarte.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    setChanged();
                    notifyObservers(buttonCarte.getMessage(role));
                    clearChanged();
                }
            });
            panelCarte.add(buttonCarte);
        }
        JButton b= new JButton();
        panelPrincipal.add(panelCarte, BorderLayout.CENTER);
        afficher();
    }

    private void afficher() {
        getFenetre().setVisible(true);
    }

    public void close() {
        getFenetre().dispose();
    }

    //Getters et Setters : 



    /**
     * @return the fenetre
     */
    public JFrame getFenetre() {
        return fenetre;
    }

}
