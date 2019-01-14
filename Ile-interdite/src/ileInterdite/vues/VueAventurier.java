/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import ileInterdite.model.cartes.ICartes;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import utilitaires.Role;

        
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

    private ArrayList<ICartes> buttonCartes;
  
    private ArrayList<JLabel> lampes;
    private String nomAventurier;
    private JPanel pannelBouttons=new JPanel(new GridLayout(2,2));
    
    
    
    public VueAventurier(Role roleAventurier) {
        super(new BorderLayout());
        this.nomAventurier = nomAventurier;
        
        
        //====================== principal========================
        
        JPanel pannelPrincipal = new JPanel(new GridLayout(2, 1));
        
        
        //===================pannel en haut avec les button et la classe====
        
        JPanel pannelHaut=new JPanel(new GridLayout(1,2));
        
        bouger=new JButton("bouger");
        assecher=new JButton("assecher");
        recuperer=new JButton("recuperer");
        donner=new JButton("donner");
        
        pannelBouttons.add(new JPanel());
        pannelBouttons.add(new JPanel());
        pannelBouttons.add(donner);
        pannelBouttons.add(new JPanel());

        paneClass=new JPanel(new BorderLayout());
        JButton carteJoueur=new JButton("icone");
        paneTresor=new JPanel(new GridLayout(1,4));
        
        lampes=new ArrayList<>();
       
        for (int i=0;i<4;i++){
            lampes.add(new JLabel("AA"));
            paneTresor.add(lampes.get(i));
    
        }
        paneClass.add(carteJoueur, BorderLayout.CENTER);
        paneClass.add(paneTresor,BorderLayout.SOUTH);
        
        pannelHaut.add(pannelBouttons);
        pannelHaut.add(paneClass);
                
        
        
        //=======================pannel en bas pour afficher les cartes=========
        
        JPanel pannelBas=new JPanel(new GridLayout(1,5));
        
        
        buttonCartes=new ArrayList<>();
        for(int i=0;i<5;i++){
            buttonCartes.add(new ICartes(new JButton("Carte"),null,roleAventurier));
            pannelBas.add(buttonCartes.get(i).getBoutonCarte());
        }

        
        pannelPrincipal.add(pannelHaut);
        pannelPrincipal.add(pannelBas);
        this.add(pannelPrincipal);
    }
        
    public void actualiserVueAventurier(ArrayList<String> listeCarte){
         for (int i = 0 ;i<5 && i<listeCarte.size();i++){
            getButtonCartes().get(i).getBoutonCarte().setText(listeCarte.get(i));
         }
     
    } 

    public void rendreCartesCliquables(ArrayList<Integer> listePos){
        for (Integer carteCliquable : listePos){
            buttonCartes.get(carteCliquable).rendreCarteCliquable();
        }
    }
            
    public void rendreAventurierCliquable(){
        
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

    public ArrayList<ICartes> getButtonCartes() {
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

    public void setButtonCartes(ArrayList<ICartes> buttonCartes) {
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
