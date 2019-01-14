/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;


import ileInterdite.message.*;
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
public class VueDefausse extends Observable{
    private JFrame fenetre;
    private ArrayList<ICartes> buttonCartes;
    private JPanel panelCarte;
    private JPanel panelPrincipal;

    public VueDefausse(){

        
        fenetre=new JFrame();
        fenetre.setTitle("cartes");
        
        fenetre.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
        fenetre.setSize(600, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        fenetre.setLocation(dim.width/2-fenetre.getSize().width/2, dim.height/2-fenetre.getSize().height/2);
        fenetre.setResizable(true);
        
        
        panelPrincipal=new JPanel(new BorderLayout());
        JLabel explication=new JLabel("on met les explications ici");
        
        

        
        panelCarte=new JPanel(new GridLayout(1,1)); 
        
        panelPrincipal.add(explication,BorderLayout.NORTH);
        panelPrincipal.add(panelCarte,BorderLayout.CENTER);
        
        
        actualiser(new ArrayList<>(),null);
        
        
        
        close();
        fenetre.add(panelPrincipal);
    }
    
      public void actualiser(ArrayList<String> listeCartes,Role role){ 
        panelPrincipal.remove(panelCarte);
          
        buttonCartes=new ArrayList<>();
        
        panelCarte=new JPanel(new GridLayout(1,listeCartes.size()));
        for(String nomCarte: listeCartes){
            ICartes carte = new ICartes(nomCarte, role, Action.DEFAUSSER);
            carte.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    setChanged();
                    notifyObservers(carte.getMessage());
                    clearChanged();
                }
            });
            buttonCartes.add(carte);
            panelCarte.add(carte);
        }
        panelPrincipal.add(panelCarte,BorderLayout.CENTER);
        afficher();
    }
      
    
    
      
      public void afficher(){
        this.fenetre.setVisible(true);
    }
      
      public void close(){
        this.fenetre.dispose();
    }

}
