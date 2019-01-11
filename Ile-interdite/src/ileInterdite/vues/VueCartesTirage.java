/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import ileInterdite.model.cartes.CarteTirage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author PC
 */
public class VueCartesTirage {
    private JFrame fenetre;
    private ArrayList<JButton> buttonCartes;
    private ArrayList<CarteTirage> cartes;
    private CarteTirage carte;
    private JPanel paneCarte;
    
    
    public VueCartesTirage(CarteTirage carte){
        this.carte=carte;
        
        
        fenetre=new JFrame();
        fenetre.setTitle("cartes");
        
        fenetre.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(600, 500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        fenetre.setLocation(dim.width/2-fenetre.getSize().width/2, dim.height/2-fenetre.getSize().height/2);
        fenetre.setResizable(true);
        
        
        JPanel pannelPrincipale=new JPanel(new BorderLayout());
        JLabel explication=new JLabel("on met les explications ici");
        paneCarte=new JPanel(new GridLayout(1,buttonCartes.size()));
        

        
      //============ajouter des button des cartes dans la vue============================= 
        cartes=new ArrayList<>();
        
        for(JButton c:buttonCartes){
               paneCarte.add(c);
        
        }
        
        pannelPrincipale.add(explication,BorderLayout.NORTH);
        pannelPrincipale.add(paneCarte,BorderLayout.CENTER);
        fenetre.add(pannelPrincipale);
    }
      public void affiche(){
        this.fenetre.setVisible(true);
    }
   
        public static void main(String[] args) {
        CarteTirage carte=null;
            
        VueCartesTirage ct=new VueCartesTirage(carte);
        
        ct.affiche();

    }
        
    
}
