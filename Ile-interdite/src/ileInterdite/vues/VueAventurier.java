/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import ileInterdite.actions.*;
import ileInterdite.aventurier.Role;
import ileInterdite.message.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
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
 * @author vinetg
 */

 
public class VueAventurier extends Observable {
     
    private  JPanel panelBoutons = new JPanel(new GridLayout(2,2));
    private  JPanel panelCentre =  new JPanel(new BorderLayout());
    private  JFrame window;
    private  JPanel panelAventurier = new JPanel();
    private  JPanel mainPanel = new JPanel(new BorderLayout());
    private  JButton btnBouger = new JButton("Bouger");
    private  JButton btnAssecher= new JButton( "Assecher");
    private  JLabel nbPA   = new JLabel();
    private  JButton btnTerminerTour = new JButton("Terminer Tour") ;
    private VueGrille vueGrille;
    

   
    /**
     * On définit un constructeur de VueAventurier  avec une VueGrille v
     */
    public VueAventurier(VueGrille v){
        vueGrille = v;
        setWindow(new JFrame());
        getWindow().setSize(1080, 720);
        getWindow().setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE); 
        getWindow().setTitle("Ile interdite");
        getWindow().add(getMainPanel());
        

        // =================================================================================
        // NORD : le titre = nom de l'aventurier sur la couleurActive du pion

        
        getMainPanel().add(getPanelAventurier(), BorderLayout.NORTH);
   
           
        // =================================================================================
        // CENTRE : 1 ligne pour position courante
        
        getPanelCentre().setOpaque(false);
        getMainPanel().add(getPanelCentre(), BorderLayout.CENTER);
        getPanelCentre().add(getVueGrille().getPanelGrille(),  BorderLayout.CENTER);
        getMainPanel().add(this.getPanelBoutons(), BorderLayout.SOUTH);
        
        // =================================================================================
        // SUD
        
        getPanelBoutons().setOpaque(false);
        getPanelBoutons().add(getBtnBouger());
        getPanelBoutons().add(getBtnAssecher());
        getPanelBoutons().add(getBtnTerminerTour());
        getPanelBoutons().add(getNbPA());
        
        
        
        
        
        
                      
        getBtnBouger().addActionListener((ActionEvent e) -> {
            setChanged();
            notifyObservers(new Message(Action.DEPLACER));
            clearChanged();
        });

        getBtnAssecher().addActionListener((ActionEvent e) -> {
            setChanged();
            notifyObservers(new Message(Action.ASSECHER));
            clearChanged();
        });

        getBtnTerminerTour().addActionListener((ActionEvent e) -> {
            setChanged();
            notifyObservers(new Message(Action.TERMINER));
            clearChanged();
        });
        
        
    }
    
        public void actualiserVue(String nomJoueur, Role classe, Color couleur, int nombrePA){
            
            
        if (nombrePA==0) {
            getBtnBouger().disable();
            
            
        }
        getMainPanel().setBorder(BorderFactory.createLineBorder(couleur, 2)) ;
        
        getPanelAventurier().setBackground(couleur);
        
        getPanelAventurier().removeAll();
        getPanelAventurier().add(new JLabel(classe + " ( "+nomJoueur+" ) ",SwingConstants.CENTER ));
        
        getPanelCentre().setBorder(new MatteBorder(0, 0, 2, 0, couleur));
        
        getPanelBoutons().remove(getNbPA());
        setNbPA(new JLabel("Nombre d'actions restantes : " + nombrePA));
        getPanelBoutons().add(getNbPA());
        
        getWindow().setVisible(true);
        
    }
    
    /**
     * Ferme la fenêtre
     */
    public void close(){
        getWindow().dispose();
    }
    
    
    //Getters et Setters :
    
    
    /**
     *
     * @return the btnBouger
     */
    public JButton getBtnBouger() {
        return btnBouger;
    }
    
     /**
     *
     * @return the btnAssecher
     */
    public JButton getBtnAssecher() {
        return btnAssecher;
    }

     /**
     * 
     * @return the btnTerminerTour
     */
    public JButton getBtnTerminerTour() {
        return btnTerminerTour;
    }

    /**
     * @return the panelBoutons
     */
    public JPanel getPanelBoutons() {
        return panelBoutons;
    }

    /**
     * @return the panelCentre
     */
    public JPanel getPanelCentre() {
        return panelCentre;
    }

    /**
     * @return the window
     */
    public JFrame getWindow() {
        return window;
    }

    /**
     * @return the panelAventurier
     */
    public JPanel getPanelAventurier() {
        return panelAventurier;
    }

    /**
     * @return the mainPanel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * @return the nbPA
     */
    public JLabel getNbPA() {
        return nbPA;
    }

    /**
     * @return the vueGrille
     */
    public VueGrille getVueGrille() {
        return vueGrille;
    }

    /**
     * @param vueGrille the vueGrille to set
     */
    public void setVueGrille(VueGrille vueGrille) {
        this.vueGrille = vueGrille;
    }

    /**
     * @param panelBoutons the panelBoutons to set
     */
    public void setPanelBoutons(JPanel panelBoutons) {
        this.panelBoutons = panelBoutons;
    }

    /**
     * @param panelCentre the panelCentre to set
     */
    public void setPanelCentre(JPanel panelCentre) {
        this.panelCentre = panelCentre;
    }

    /**
     * @param window the window to set
     */
    public void setWindow(JFrame window) {
        this.window = window;
    }

    /**
     * @param panelAventurier the panelAventurier to set
     */
    public void setPanelAventurier(JPanel panelAventurier) {
        this.panelAventurier = panelAventurier;
    }

    /**
     * @param mainPanel the mainPanel to set
     */
    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    /**
     * @param btnBouger the btnBouger to set
     */
    public void setBtnBouger(JButton btnBouger) {
        this.btnBouger = btnBouger;
    }

    /**
     * @param btnAssecher the btnAssecher to set
     */
    public void setBtnAssecher(JButton btnAssecher) {
        this.btnAssecher = btnAssecher;
    }

    /**
     * @param nbPA the nbPA to set
     */
    public void setNbPA(JLabel nbPA) {
        this.nbPA = nbPA;
    }

    /**
     * @param btnTerminerTour the btnTerminerTour to set
     */
    public void setBtnTerminerTour(JButton btnTerminerTour) {
        this.btnTerminerTour = btnTerminerTour;
    }
 
}

 



