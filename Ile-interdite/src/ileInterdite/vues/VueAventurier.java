/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import utilitaires.Action;
import ileInterdite.model.aventurier.Role;
import ileInterdite.message.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.Observable;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

        
/**
 *
 * @author grosa
 */
public class VueAventurier {
    private JFrame fenetre;
    private JButton bouger;
    private JPanel paneClass;
    private JPanel paneTresor;
    private JButton assecher;
    private JButton recuperer;
    private JButton donner;
    private JButton carte1;
    private JButton carte2;
    private JButton carte3;
    private JButton carte4;
    private JButton carte5;
    private JLabel lampe1;
    private JLabel lampe2;
    private JLabel lampe3;
    private JLabel lampe4;
    
    
    public VueAventurier() {
        
        fenetre=new JFrame();
        fenetre.setTitle("aventurier");
        
        fenetre.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(600, 500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        fenetre.setLocation(dim.width/2-fenetre.getSize().width/2, dim.height/2-fenetre.getSize().height/2);
        fenetre.setResizable(true);
        
        //====================== principal========================
        
        JPanel pannelPrincipal = new JPanel(new GridLayout(2, 1));
        
        
        //===================pannel en haut avec les button et la class====
        
        JPanel pannelHaut=new JPanel(new GridLayout(1,2));
        
        bouger=new JButton("bouger");
        assecher=new JButton("assecher");
        recuperer=new JButton("recuperer");
        donner=new JButton("donner");
        
        JPanel pannelBouttons=new JPanel(new GridLayout(2,2));
        
        pannelBouttons.add(bouger);
        pannelBouttons.add(assecher);
        pannelBouttons.add(recuperer);
        pannelBouttons.add(donner);

        paneClass=new JPanel(new BorderLayout());
        JPanel paneImage=new JPanel();
        paneTresor=new JPanel(new GridLayout(1,4));
        
        
        lampe1=new JLabel("aa");
        lampe2=new JLabel("aa");
        lampe3=new JLabel("aa");
        lampe4=new JLabel("aa");
        
        paneTresor.add(lampe1);
        paneTresor.add(lampe2);
        paneTresor.add(lampe3);
        paneTresor.add(lampe4);
        
        paneClass.add(paneImage,BorderLayout.CENTER);
        paneClass.add(paneTresor,BorderLayout.SOUTH);
        
        pannelHaut.add(pannelBouttons);
        pannelHaut.add(paneClass);
                
        
        
        //=======================pannel en bas pour afficher les cartes=========
        
        JPanel pannelBas=new JPanel(new GridLayout(1,5));
                
        carte1=new JButton("Carte1");
        carte2=new JButton("Carte2");
        carte3=new JButton("Carte3");
        carte4=new JButton("Carte4");
        carte5=new JButton("Carte5");
        
        pannelBas.add(carte1);
        pannelBas.add(carte2);
        pannelBas.add(carte3);
        pannelBas.add(carte4);
        pannelBas.add(carte5);
        
        pannelPrincipal.add(pannelHaut);
        pannelPrincipal.add(pannelBas);
        fenetre.add(pannelPrincipal);
    }
    
      public void affiche(){
        this.fenetre.setVisible(true);
    }
   
        public static void main(String[] args) {
            
            VueAventurier ihm=new VueAventurier();
            ihm.affiche();

    }
    
}
