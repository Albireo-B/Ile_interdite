
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import ileInterdite.message.MessageGroupePos;
import utilitaires.EtatTuile;
import ileInterdite.model.Grille;
import ileInterdite.model.Position;
import utilitaires.Action;
import utilitaires.Pion;
import ileInterdite.message.MessagePos;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import utilitaires.Role;
import utilitaires.Tresor;

/**
 *
 * @author vinetg
 */
public class VueGrille extends Observable {

    private Box panelGeneral;
    private JPanel panelGrille;
    private HashMap<Position, BoutonTuile> bTuiles = new HashMap();
    private Color myBackgroundColor = new Color(12, 143, 181);
    private Role joueurSelectionné;
    private String path = "src/images/tuiles/";
    
    private int grilleWidth = 790;
    private int grilleHeight = 800;

    private HashMap<Tresor, ITresor> tresors = new HashMap();

    /**
     * On définit le constructeur de VueGrille
     *
     * @param positions
     * @param noms
     */
    public VueGrille(ArrayList<Position> positions, ArrayList<String> noms) {
        panelGeneral = new Box(BoxLayout.Y_AXIS);
        panelGeneral.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        
        panelGrille = new JPanel(new GridLayout(6, 6));
        panelGrille.setPreferredSize(new Dimension(grilleWidth, grilleHeight));
        panelGeneral.add(panelGrille);
        panelGrille.setBackground(myBackgroundColor);
        ArrayList<Position> positionTuiles = Grille.getAllTilesPositions();

        for (int y = 0; y < 6; y++) {
            for (int x = 5; x > -1; x--) {
                Position pos = new Position(x, y);
                if (positionTuiles.contains(pos)) {
                    if (positions.contains(pos)) {
                        String nom = noms.get(positions.indexOf(pos));
                        BoutonTuile bouton = new BoutonTuile(nom);
                        bTuiles.put(pos, bouton);
                        panelGrille.add(bouton);
                    } else {
                        System.out.println("Il vous manque une case ou quoi?");
                        JPanel panel = new JPanel();
                        panel.setBackground(Color.red);
                        panelGrille.add(panel);
                    }
                } else {
                    if (pos.getX() == 0 && pos.getY() == 0) {
                        ITresor tresor = new ITresor(Tresor.ZEPHYR);
                        panelGrille.add(tresor);
                        tresors.put(Tresor.ZEPHYR, tresor);
                    } else if (pos.getX() == 5 && pos.getY() == 0) {
                        ITresor tresor = new ITresor(Tresor.PIERRE);
                        panelGrille.add(tresor);
                        tresors.put(Tresor.PIERRE, tresor);
                    } else if (pos.getX() == 0 && pos.getY() == 5) {
                        ITresor tresor = new ITresor(Tresor.CALICE);
                        panelGrille.add(tresor);
                        tresors.put(Tresor.CALICE, tresor);
                    } else if (pos.getX() == 5 && pos.getY() == 5) {
                        ITresor tresor = new ITresor(Tresor.CRISTAL);
                        panelGrille.add(tresor);
                        tresors.put(Tresor.CRISTAL, tresor);
                    } else {
                        JPanel panel = new JPanel();
                        panel.setBackground(myBackgroundColor);
                        panelGrille.add(panel);
                    }
                }
            }
        }
    
    }

    /**
     *
     */
    public void tousBoutonsInertes() {
        for (BoutonTuile bouton : bTuiles.values()) {
            for (ActionListener ac : bouton.getBouton().getActionListeners()) {
                bouton.removeActionListener(ac);
                bouton.setButtonBorder(null);
                bouton.getBouton().setBorderPainted(false);
            }
        }
    }

    /**
     * Rend toutes les positions de la liste cliquables
     *
     * @param posBoutons
     * @param act
     */
    public void actualiserBoutonsCliquables(ArrayList<Position> posBoutons, Action act, Role role) {
      
        setJoueurSelectionné(role);
        for (Position pos : posBoutons) {
            if (bTuiles.keySet().contains(pos)) {
                BoutonTuile bouton = bTuiles.get(pos);
                bouton.setButtonBorder(Color.red);
                
                bouton.getBouton().setBorderPainted(true);
                bouton.addActionListener((ActionEvent e) -> {
                setChanged();
                notifyObservers(new MessagePos(act, pos, getJoueurSelectionné()));
                clearChanged();
                });
            }
        }
    }
    public void actualiserBoutonsCliquables(ArrayList<Position> posBoutons, Action act, Role role,ArrayList<Role> roles) {
        joueurSelectionné = role;
        for (Position pos : posBoutons) {
            if (bTuiles.keySet().contains(pos)) {
                BoutonTuile bouton = bTuiles.get(pos);
                bouton.setButtonBorder(Color.red);
                bouton.getBouton().setBorderPainted(true);
                bouton.addActionListener((ActionEvent e) -> {
                    setChanged();
                    notifyObservers(new MessageGroupePos(act, pos, joueurSelectionné,roles));
                    clearChanged();
                });
            }
        }
    }

    /**
     * On définit l'état d'une Position (Tuile)
     *
     * @param etat
     * @param pos
     */
    public void actualiserEtatTuile(Position pos, EtatTuile etat) {
        BoutonTuile bouton = bTuiles.get(pos);
        switch (etat) {
            case COULEE:
                bouton.setButtonEnabled(false);
                break;
            case SECHE:
                
                ImageIcon tuileSeche = new ImageIcon(new ImageIcon(path+bouton.getNom()+".png").getImage().getScaledInstance(bouton.getWidth(),bouton.getHeight() , Image.SCALE_DEFAULT));
                bouton.getBouton().setIcon(tuileSeche);
                bouton.setButtonEnabled(true);
                break;
            case INONDEE:
                ImageIcon tuileSInonde = new ImageIcon(new ImageIcon(path+bouton.getNom()+"_Inonde.png").getImage().getScaledInstance(bouton.getWidth(),bouton.getHeight() , Image.SCALE_DEFAULT));

                bouton.getBouton().setIcon(tuileSInonde);
                bouton.setButtonEnabled(true);
                break;
        }
    }

    /**
     * @param position
     * @param posAv
     * @param p
     */
    public void actualiserPositionJoueur(Position position, Position posAv, Pion p) {
        if (bTuiles.keySet().contains(posAv)) {
            bTuiles.get(posAv).removeAventurier(p.getCouleur());
        }
        bTuiles.get(position).addAventurier(p.getCouleur());
    }

    //Getters et Setters :
    /**
     *
     * @return the panelGrille
     */
    public JPanel getPanelGrille() {
        return panelGrille;
    }

    /**
     * @param panelGrille the panelGrille to set
     */
    public void setPanelGrille(JPanel panelGrille) {
        this.panelGrille = panelGrille;
    }

    /**
     * @return the joueurSelectionné
     */
    public Role getJoueurSelectionné() {
        return joueurSelectionné;
    }

    /**
     * @param joueurSelectionné the joueurSelectionné to set
     */
    public void setJoueurSelectionné(Role joueurSelectionné) {
        this.joueurSelectionné = joueurSelectionné;
    }

    /**
     * @return the tresors
     */
    public HashMap<Tresor, ITresor> getTresors() {
        return tresors;
    }
}
