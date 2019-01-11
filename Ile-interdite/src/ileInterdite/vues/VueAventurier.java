/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

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
    private JPanel pannelBouttons=new JPanel(new GridLayout(2,2));
    
    
    
    public VueAventurier(String nomAventurier) {
        this.nomAventurier=nomAventurier;
        
        
        //====================== principal========================
        
        JPanel pannelPrincipal = new JPanel(new GridLayout(2, 1));
        
        
        //===================pannel en haut avec les button et la class====
        
        JPanel pannelHaut=new JPanel(new GridLayout(1,2));
        
        bouger=new JButton("bouger");
        assecher=new JButton("assecher");
        recuperer=new JButton("recuperer");
        donner=new JButton("donner");
        
        
        
        pannelBouttons.add(bouger);
        pannelBouttons.add(assecher);
        pannelBouttons.add(recuperer);
        pannelBouttons.add(donner);

        paneClass=new JPanel(new BorderLayout());
        JPanel paneImage=new JPanel();
        paneTresor=new JPanel(new GridLayout(1,4));
        
        lampes=new ArrayList<>();
       
        for (int i=0;i<4;i++){
        paneTresor.add(lampes.get(i));
    
        }
        paneClass.add(paneImage,BorderLayout.CENTER);
        paneClass.add(paneTresor,BorderLayout.SOUTH);
        
        pannelHaut.add(pannelBouttons);
        pannelHaut.add(paneClass);
                
        
        
        //=======================pannel en bas pour afficher les cartes=========
        
        JPanel pannelBas=new JPanel(new GridLayout(1,5));
        
        
        buttonCartes=new ArrayList<>();
        for(int i=0;i<4;i++){
        pannelBas.add(buttonCartes.get(i));
        
        }

        
        pannelPrincipal.add(pannelHaut);
        pannelPrincipal.add(pannelBas);

    }
    

   
     public void ActualiserAventurier(ArrayList<String> listeCarte){
         int i=0;
         for (String c : listeCarte){
            getButtonCartes().get(i).setText(c);
         }
     
    } 
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


    /**
     * @param pannelBouttons the pannelBouttons to set
     */
    public void setPannelBouttons(JPanel pannelBouttons) {
        this.pannelBouttons = pannelBouttons;
    }
     
     
}
