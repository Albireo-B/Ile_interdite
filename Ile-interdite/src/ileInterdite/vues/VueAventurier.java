/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import ileInterdite.message.MessageCarte;
import ileInterdite.message.MessagePos;
import ileInterdite.model.aventurier.IAventurier;
import ileInterdite.model.cartes.CarteTirage;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import utilitaires.Action;
import utilitaires.Role;

        
/**
 *
 * @author grosa
 */
public class VueAventurier extends Observable {
    private JPanel panelPrincipal;
    
    private JButton bouger;
    private JPanel paneClass;
    private JPanel paneTresor;
    private JButton assecher;
    private JButton recuperer;
    private JButton donner;

    private IAventurier carteJoueur;



    private ArrayList<ICartes> buttonCartes;
  
    private ArrayList<JLabel> lampes;
    private Role roleAventurier;

    
    
    

    public VueAventurier(Role roleAventurier,boolean gauche) {
        this.roleAventurier = roleAventurier;
        
        
        //====================== principal========================
        
        panelPrincipal = new JPanel(new GridLayout(2,3));
        
        
        //===================pannel en haut avec les button et la classe====
        
       
        
        bouger=new JButton("bouger");
        assecher=new JButton("assecher");
        recuperer=new JButton("recuperer");
        donner=new JButton("donner");
        

        paneClass=new JPanel(new BorderLayout());
        carteJoueur=new IAventurier(new JButton("icone"),null,roleAventurier);

        paneTresor=new JPanel(new GridLayout(1,4));
        
        lampes=new ArrayList<>();
       
        buttonCartes=new ArrayList<>();
        
       
        //=============================================
        paneClass.add(carteJoueur.getBoutonAventurier());

        for(int i=0;i<5;i++){
                if((i==0 && !gauche) || (i==2 && gauche)){
                    panelPrincipal.add(paneClass);
                }
                buttonCartes.add(new ICartes("Carte",roleAventurier, null));
                panelPrincipal.add(buttonCartes.get(i));
            }

            buttonCartes.add(new ICartes("Carte",roleAventurier, null));
            panelPrincipal.add(paneTresor,BorderLayout.SOUTH);

        }
    
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
        
    public void actualiserVueAventurier(ArrayList<CarteTirage> listeCarte){
        for (int i = 0 ;i<5 && i<listeCarte.size();i++){
            ICartes icarte = getButtonCartes().get(i);
            CarteTirage carte = listeCarte.get(i);
            icarte.setText(carte.getNom());
            icarte.setActionCarte(carte.getAction());
        }
    } 
    
    public void rendreCartesCliquables() {
        ArrayList<Integer> listePos = new ArrayList();
        
        for (int i = 0; i < buttonCartes.size(); i++) {
            listePos.add(i);
        }
        
        rendreCartesCliquables(listePos);
    }
    
    public void rendreCartesCliquables(ArrayList<Integer> listePos) {
        for (Integer carteCliquable : listePos){
            ICartes carte = buttonCartes.get(carteCliquable);
            carte.addActionListener((ActionEvent e) -> {
                    setChanged();
                    notifyObservers(carte.getMessage());
                    clearChanged();
                });
        }
    }
            
    public void rendreAventurierCliquable(){
            carteJoueur.rendreAventurierCliquable();
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


     
     
}
