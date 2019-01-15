/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import ileInterdite.model.cartes.ICartes;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
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
    private JPanel paneClass;
    private JPanel paneTresor;
    private JButton bouger=new JButton("bouger");;
    private JButton assecher=new JButton("assecher");;
    private JButton recuperer=new JButton("recuperer");;
    private JButton donner=new JButton("donner");;

    private JButton carteJoueur;

    private JPanel panelPrincipal;


    private ArrayList<ICartes> buttonCartes;
  
    private ArrayList<JLabel> lampes;
    private Role roleAventurier;

    
    
    

    public VueAventurier(Role roleAventurier,boolean gauche) {

        super(new BorderLayout());
        this.roleAventurier = roleAventurier;
        
        bouger.setVisible(true);
        assecher.setVisible(true);
        
        donner.setVisible(false);
        recuperer.setVisible(false);
        
        
        //====================== principal========================
        
        panelPrincipal = new JPanel(new GridLayout(2,3));
        
        
        //===================pannel en haut avec les button et la classe====
 

        paneClass=new JPanel(new BorderLayout());
        carteJoueur=new JButton("icone");

        paneTresor=new JPanel(new GridLayout(1,4));
        
        lampes=new ArrayList<>();
       
        for (int i=0;i<4;i++){
            lampes.add(new JLabel("AA"));
            paneTresor.add(lampes.get(i));
    
        }
        buttonCartes=new ArrayList<>();
        
       
        //=============================================
        paneClass.add(carteJoueur);

        for(int i=0;i<5;i++){
                if((i==0 && !gauche) || (i==2 && gauche)){
                    panelPrincipal.add(paneClass);
                }
                buttonCartes.add(new ICartes(new JButton("Carte"),null,roleAventurier));
                panelPrincipal.add(buttonCartes.get(i).getBoutonCarte());

            }


        
        

            buttonCartes.add(new ICartes(new JButton("Carte"),null,roleAventurier));
            this.add(panelPrincipal,BorderLayout.CENTER);
            this.add(paneTresor,BorderLayout.SOUTH);

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
            
    public void rendreAventuriersCliquables(ArrayList<Integer> aventuriers){
        for (Integer aventurier : aventuriers){
            
        }
        //carteJoueur.addActionListener(new ActionListener);
    }
            
    //Getters et Setters :
     
    /**
     * @return the nomAventurier
     */
    public Role getRoleAventurier() {
        return roleAventurier;
    }

    /**
     * @param nomAventurier the nomAventurier to set
     */
    public void setRoleAventurier(Role roleAventurier) {
        this.roleAventurier = roleAventurier;
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


     public void afficherDonner(){
         donner.setVisible(true);
     }
     public void afficherRecuper(){
         recuperer.setVisible(true);
     }
     
     public void cacherBouger(){
         bouger.setVisible(false);
     }
     public void cacherAssecher(){
         assecher.setVisible(false);
     
     }
}
