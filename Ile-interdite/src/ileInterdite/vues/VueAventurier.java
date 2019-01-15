/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import ileInterdite.message.MessagePos;
import ileInterdite.model.aventurier.IAventurier;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JPanel;
import utilitaires.Action;
import utilitaires.Role;

        
/**
 *
 * @author grosa
 */
public class VueAventurier extends Observable {
    private JPanel paneClass;

    private IAventurier carteJoueur;
    
    private JPanel panelGeneral;


    private ArrayList<ICarte> buttonCartes;
  
    private Role roleAventurier;


    public VueAventurier(Role roleAventurier,boolean gauche) {

        panelGeneral = new JPanel(new BorderLayout());
        this.roleAventurier = roleAventurier;
        
    
        
        //====================== principal========================
        
        JPanel panelPrincipal = new JPanel(new GridLayout(2,3));
        
        
        //===================pannel en haut avec les button et la classe====

 

        paneClass=new JPanel(new BorderLayout());
        carteJoueur=new IAventurier(new JButton(roleAventurier.toString()),roleAventurier);
       
        buttonCartes=new ArrayList<>();
        
       
        //=============================================
        paneClass.add(carteJoueur.getBoutonAventurier());

        for(int i=0;i<5;i++){
                if((i==0 && !gauche) || (i==2 && gauche)){
                    panelPrincipal.add(paneClass);
                }
                buttonCartes.add(new ICarte("_", Action.DONNER, roleAventurier));
                panelPrincipal.add(buttonCartes.get(i));

            }

            buttonCartes.add(new ICarte("_", Action.DONNER, roleAventurier));
            panelGeneral.add(panelPrincipal,BorderLayout.CENTER);

        }
    
        
    public void actualiserVueAventurier(ArrayList<String> listeCarte){
        int j= 0;
         for (int i=0 ;i<5 && i<listeCarte.size();i++){
            getButtonCartes().get(i).setNom(listeCarte.get(i));
            j=i;
         }
         for (int i=j+1;i<5;i++){
             getButtonCartes().get(i).setNom("_");
         }
     
    } 

    public void rendreCartesCliquables(ArrayList<Integer> listePos){
        for (Integer carteCliquable : listePos){
            
            ICarte carte = buttonCartes.get(carteCliquable);
            carte.addActionListener((ActionEvent e) -> {
                    setChanged();
                    notifyObservers(carte.getMessage());     
                    clearChanged();
                });
            buttonCartes.get(carteCliquable).setBackground(Color.red);
        }
    }
            

    public void devenirReceveur(String carte,Boolean suivre){
            getCarteJoueur().devenirReceveur(carte,suivre);
    }
    
    public void devenirSuiveur(boolean suivre){
            getCarteJoueur().devenirSuiveur(suivre);
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

    
    public ArrayList<ICarte> getButtonCartes() {
        return buttonCartes;
    }




    /**
     * @param paneClass the paneClass to set
     */
    public void setPaneClass(JPanel paneClass) {
        this.paneClass = paneClass;
    }



    public void setButtonCartes(ArrayList<ICarte> buttonCartes) {
        this.buttonCartes = buttonCartes;
    }
    
    
    /**
     * @return the paneClass
     */
    public JPanel getPaneClass() {
        return paneClass;
    }

    /**
     * @return the carteJoueur
     */
    public IAventurier getCarteJoueur() {
        return carteJoueur;
    }

    /**
     * @return the panelGeneral
     */
    public JPanel getPanelGeneral() {
        return panelGeneral;
    }


}
