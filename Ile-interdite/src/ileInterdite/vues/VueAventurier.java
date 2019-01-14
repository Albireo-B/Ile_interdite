/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import ileInterdite.model.aventurier.IAventurier;
import ileInterdite.model.cartes.ICarte;
import java.awt.BorderLayout;
import java.awt.Color;
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

    private IAventurier carteJoueur;

    private JPanel panelPrincipal;


    private ArrayList<ICarte> buttonCartes;
  
    private ArrayList<JLabel> lampes;
    private Role roleAventurier;

    
    
    

    public VueAventurier(Role roleAventurier,boolean gauche) {

        super(new BorderLayout());
        this.roleAventurier = roleAventurier;
        
        
        //====================== principal========================
        
        panelPrincipal = new JPanel(new GridLayout(2,3));
        
        
        //===================pannel en haut avec les button et la classe====
        
       
        
        bouger=new JButton("bouger");
        assecher=new JButton("assecher");
        recuperer=new JButton("recuperer");
        donner=new JButton("donner");
        

        paneClass=new JPanel(new BorderLayout());
        carteJoueur=new IAventurier(new JButton("icone"),roleAventurier);

        paneTresor=new JPanel(new GridLayout(1,4));
        
        lampes=new ArrayList<>();
       
        for (int i=0;i<4;i++){
            lampes.add(new JLabel("AA"));
            paneTresor.add(lampes.get(i));
    
        }
        buttonCartes=new ArrayList<>();
        
       
        //=============================================
        paneClass.add(carteJoueur.getBoutonAventurier());

        for(int i=0;i<5;i++){
                if((i==0 && !gauche) || (i==2 && gauche)){
                    panelPrincipal.add(paneClass);
                }
                buttonCartes.add(new ICarte(new JButton("Carte"),roleAventurier));
                panelPrincipal.add(buttonCartes.get(i).getBoutonCarte());

            }


        
        

            buttonCartes.add(new ICarte(new JButton("Carte"),roleAventurier));
            this.add(panelPrincipal,BorderLayout.CENTER);
            this.add(paneTresor,BorderLayout.SOUTH);

        }
    
        
    public void actualiserVueAventurier(ArrayList<String> listeCarte){
        int j= 0;
        System.out.println(listeCarte);
         for (int i=0 ;i<5 && i<listeCarte.size();i++){
            getButtonCartes().get(i).getBoutonCarte().setText(listeCarte.get(i));
            j=i;
         }
         for (int i=j+1;i<5;i++){
             getButtonCartes().get(i).getBoutonCarte().setText("carte");
         }
     
    } 

    public void rendreCartesCliquables(ArrayList<Integer> listePos){
        for (Integer carteCliquable : listePos){
            buttonCartes.get(carteCliquable).rendreCarteCliquable();
            buttonCartes.get(carteCliquable).getBoutonCarte().setBackground(Color.red);
        }
    }
            

    public void devenirReceveur(String carte){
            getCarteJoueur().devenirReceveur(carte);
            getCarteJoueur().getBoutonAventurier().setBackground(Color.red);
    }
    
    public void devenirSuiveur(){
            getCarteJoueur().devenirSuiveur();
            getCarteJoueur().getBoutonAventurier().setBackground(Color.red);
    }
    
    //Getters et Setters :
     
    /**
     * @return the nomAventurier
     */
    public Role getRoleAventurier() {
        return roleAventurier;
    }

    /**
     * @param roleAventurier
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

    public ArrayList<ICarte> getButtonCartes() {
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

    public void setButtonCartes(ArrayList<ICarte> buttonCartes) {
        this.buttonCartes = buttonCartes;
    }


    public void setLampes(ArrayList<JLabel> lampes) {
        this.lampes = lampes;
    }

    /**
     * @return the paneClass
     */
    public JPanel getPaneClass() {
        return paneClass;
    }

    /**
     * @return the paneTresor
     */
    public JPanel getPaneTresor() {
        return paneTresor;
    }

    /**
     * @return the carteJoueur
     */
    public IAventurier getCarteJoueur() {
        return carteJoueur;
    }

    /**
     * @return the panelPrincipal
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }


     
     
}
