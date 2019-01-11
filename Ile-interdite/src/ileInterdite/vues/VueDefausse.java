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
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author PC
 */
public class VueDefausse extends Observable{
    private JFrame fenetre;
    private ArrayList<JButton> buttonCartes;
    private JPanel paneCarte;
    private JPanel pannelPrincipale;

    public VueDefausse(){

        
        fenetre=new JFrame();
        fenetre.setTitle("cartes");
        
        fenetre.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(600, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        fenetre.setLocation(dim.width/2-fenetre.getSize().width/2, dim.height/2-fenetre.getSize().height/2);
        fenetre.setResizable(true);
        
        
        pannelPrincipale=new JPanel(new BorderLayout());
        JLabel explication=new JLabel("on met les explications ici");
        
        

        
        paneCarte=new JPanel(new GridLayout(1,1));
       //===================================================================================== 
        
        pannelPrincipale.add(explication,BorderLayout.NORTH);
        pannelPrincipale.add(paneCarte,BorderLayout.CENTER);
        actualiser(new ArrayList<>());
        close();
        fenetre.add(pannelPrincipale);
    }
    
      public void actualiser(ArrayList<String> listeCartes){ 
          pannelPrincipale.remove(paneCarte);
          //============ajouter des button des cartes dans la vue============================= 
        buttonCartes=new ArrayList<>();
        
        paneCarte=new JPanel(new GridLayout(1,listeCartes.size()));
        for(String c:listeCartes){
            JButton buttonCarte=new JButton();
             buttonCarte.setText(c);
             buttonCartes.add(buttonCarte);
             paneCarte.add(buttonCarte);
             System.out.println("bob");
        }
        pannelPrincipale.add(paneCarte,BorderLayout.CENTER);
        afficher();
      }
    
      public void afficher(){
        this.fenetre.setVisible(true);
    }
      
      public void close(){
        this.fenetre.dispose();
    }

}
