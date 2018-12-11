/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import ileInterdite.actions.*;
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
     
    private  JPanel panelBoutons ;
    private  JPanel panelCentre ;
    private  JFrame window;
    private  JPanel panelAventurier;
    private  JPanel mainPanel;
    private  JButton btnBouger  ;
    private  JButton btnAssecher;
    private  JLabel nbPA;
    private  JButton btnTerminerTour;
    private VueGrille vueGrille;

   
   
    /**
     * On définit un constructeur de VueAventurier 
     */
    public VueAventurier(){

        this.window = new JFrame();
        window.setSize(650, 650);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE); 
        window.setTitle("Ile interdite");
    }
    
        public void actualiserVue(String nomJoueur, String nomAventurier, Color couleur, int nombrePA){
        

        setMainPanel(new JPanel(new BorderLayout()));
        this.getWindow().add(getMainPanel());

        getMainPanel().setBackground(new Color(230, 230, 230));
        getMainPanel().setBorder(BorderFactory.createLineBorder(couleur, 2)) ;

        // =================================================================================
        // NORD : le titre = nom de l'aventurier sur la couleurActive du pion

        this.setPanelAventurier(new JPanel());
        getPanelAventurier().setBackground(couleur);
        getPanelAventurier().add(new JLabel(nomAventurier + " ( "+nomJoueur+" ) ",SwingConstants.CENTER ));
        getMainPanel().add(getPanelAventurier(), BorderLayout.NORTH);
   
           
        // =================================================================================
        // CENTRE : 1 ligne pour position courante
        this.setPanelCentre(new JPanel(new BorderLayout()));
        this.getPanelCentre().setOpaque(false);
        this.getPanelCentre().setBorder(new MatteBorder(0, 0, 2, 0, couleur));
        getMainPanel().add(this.getPanelCentre(), BorderLayout.CENTER);
        
        setVueGrille(new VueGrille());
        getPanelCentre().add(getVueGrille().getPanelGrille(),  BorderLayout.CENTER);


        // =================================================================================
        // SUD : les boutons
        this.setPanelBoutons(new JPanel(new GridLayout(2,2)));
        this.getPanelBoutons().setOpaque(false);
        getMainPanel().add(this.getPanelBoutons(), BorderLayout.SOUTH);

        this.setBtnBouger(new JButton("Bouger")) ;
        this.setBtnAssecher(new JButton( "Assecher"));
        this.setNbPA(new JLabel("Nombre d'actions restantes : " + nombrePA));
        this.setBtnTerminerTour(new JButton("Terminer Tour")) ;
        
        this.getPanelBoutons().add(getBtnBouger());
        this.getPanelBoutons().add(getBtnAssecher());
        this.getPanelBoutons().add(getNbPA());
        this.getPanelBoutons().add(getBtnTerminerTour());

        this.getWindow().setVisible(true);
        
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
    
    /**
     * Ferme la fenêtre
     */
    public void close(){
        this.getWindow().dispose();
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

 



