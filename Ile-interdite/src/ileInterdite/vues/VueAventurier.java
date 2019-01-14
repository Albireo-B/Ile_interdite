/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import ileInterdite.model.cartes.CarteTirage;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

        
/**
 *
 * @author grosa
 */
public class VueAventurier extends JPanel{
   
    private JButton bouger;
    private JPanel paneClass;
    private JPanel paneTresor;
    private JButton assecher;
    private JButton recuperer;
    private JButton donner;

    private ArrayList<JButton> buttonCartes;
  
    private ArrayList<JLabel> lampes;
    private String nomAventurier;

    
    
    
    public VueAventurier(String nomAventurier,boolean gauche) {
        super(new BorderLayout());
        this.nomAventurier = nomAventurier;
        
        
        //====================== principal========================
        
        JPanel pannelPrincipal = new JPanel(new GridLayout(2,3));
        
        
        //===================pannel en haut avec les button et la classe====
        
       
        
        bouger=new JButton("bouger");
        assecher=new JButton("assecher");
        recuperer=new JButton("recuperer");
        donner=new JButton("donner");
        


        paneClass=new JPanel();
        JButton carteJoueur=new JButton("icone");
        paneTresor=new JPanel(new GridLayout(1,4));
        
        lampes=new ArrayList<>();
       
        for (int i=0;i<4;i++){
            lampes.add(new JLabel("AA"));
            paneTresor.add(lampes.get(i));
    
        }
        buttonCartes=new ArrayList<>();
        
       
        //=============================================
        paneClass.add(carteJoueur);
        if(!gauche){
        
        
        
        
        pannelPrincipal.add(paneClass);
        for(int i=0;i<5;i++){
            
                buttonCartes.add(new JButton("Carte"));
                pannelPrincipal.add(buttonCartes.get(i));
        
            }
        
        
        }
        else{
        for(int i=0;i<5;i++){
                if(i==2)
                    pannelPrincipal.add(paneClass);
                
                    buttonCartes.add(new JButton("Carte"));
                    pannelPrincipal.add(buttonCartes.get(i));
                
                }
        
        
        }
        
        
        //===============================================
        this.add(pannelPrincipal,BorderLayout.CENTER);
        this.add(paneTresor,BorderLayout.SOUTH);
    }
        
     public void actualiserVueAventurier(ArrayList<String> listeCarte){
         for (int i = 0 ;i<listeCarte.size();i++){
            getButtonCartes().get(i).setText(listeCarte.get(i));
         }
     
    } 
     
     
       

    public void rendreCartesCliquables(ArrayList<CarteTirage> cartesCliquables){
       
    }
    
    //Getters et Setters :
     
    /**
     * @return the nomAventurier
     */
    public String getNomAventurier() {
        return nomAventurier;
    }

    /**
     * @param nomAventurier the nomAventurier to set
     */
    public void setNomAventurier(String nomAventurier) {
        this.nomAventurier = nomAventurier;
    }

    /**
     * @return the bouger
     */
    public JButton getBouger() {
        return bouger;
    }

    public JButton getAssecher() {
        return assecher;
    }

    public JButton getRecuperer() {
        return recuperer;
    }

    public JButton getDonner() {
        return donner;
    }

    public ArrayList<JButton> getButtonCartes() {
        return buttonCartes;
    }

    public ArrayList<JLabel> getLampes() {
        return lampes;
    }

    public void setBouger(JButton bouger) {
        this.bouger = bouger;
    }

    /**
     * @param paneClass the paneClass to set
     */
    public void setPaneClass(JPanel paneClass) {
        this.paneClass = paneClass;
    }


    public void setAssecher(JButton assecher) {
        this.assecher = assecher;
    }

    public void setRecuperer(JButton recuperer) {
        this.recuperer = recuperer;
    }

    public void setDonner(JButton donner) {
        this.donner = donner;
    }

    public void setButtonCartes(ArrayList<JButton> buttonCartes) {
        this.buttonCartes = buttonCartes;
    }


    public void setLampes(ArrayList<JLabel> lampes) {
        this.lampes = lampes;
    }


     
     
}
