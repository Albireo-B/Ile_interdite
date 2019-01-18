/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.model.aventurier;

import utilitaires.*;
import ileInterdite.model.Grille;
import ileInterdite.model.Position;
import ileInterdite.model.Tuile;
import ileInterdite.model.cartes.*;
import ileInterdite.vues.*;
import java.awt.Cursor;
import java.util.ArrayList;
import javax.swing.JFrame;


/**
 *
 * @author vinetg
 */
public abstract class Aventurier {

    private int nbAction;
    private Tuile tuile;
    private Boolean pouvoir = true;
    private Role role;
    private String nomJoueur;
    private Pion pion;
    private ArrayList<CarteTirage> cartes = new ArrayList<>();
    private VueDefausse vueDefausse = new VueDefausse();

    protected Aventurier(String nomJoueur, Tuile tuile) {
        this.tuile = tuile;
        this.nomJoueur = nomJoueur;
        tuile.addAventurier(this);
        reset();
    }

    /**
     * On renvoie la liste des tuiles adjacentes en croix
     *
     * @param g
     * @return
     */
    public ArrayList<Tuile> calculDeplacement(Grille g) {
        return enleverTuilesCoulees(g.tuilesAdjacentesCroix(getTuile()));
    }
    
    protected ArrayList<Tuile> enleverTuilesCoulees(ArrayList<Tuile> array) {
        ArrayList<Tuile> result = new ArrayList();
        for (Tuile t : array) {
            if(t.getEtat() != EtatTuile.COULEE)
                result.add(t);
        }
        return result;
    }

    public ArrayList<Tuile> calculGuide(Grille g) {
        ArrayList<Tuile> tuiles = new ArrayList<>();
        for (Tuile t : g.tuilesAdjacentesCroix(getTuile())) {
            if (t.getEtat() != EtatTuile.COULEE) {
                if (!(tuiles.contains(t))){
                tuiles.add(t);}
                for (Tuile tt : g.tuilesAdjacentesCroix(t)) {
                    if (tt != tuile && !tuiles.contains(tt)) {
                        tuiles.add(tt);
                    }
                }
            }
        }
        return enleverTuilesCoulees(tuiles);
    }

    /**
     * On renvoie la liste des tuiles adjacentes en croix
     *
     * @param g
     * @return CarteTirage>
     */
    public ArrayList<Tuile> calculAssechement(Grille g) {
        ArrayList<Tuile> liste = new ArrayList();
        if (tuile.getEtat() == EtatTuile.INONDEE) {
            liste.add(tuile);
        }
        for (Tuile t : g.tuilesAdjacentesCroix(tuile)) {
            if (t.getEtat() == EtatTuile.INONDEE) {
                liste.add(t);
            }
        }
        return liste;
    }

    public void defausseCartes() {
        vueDefausse.actualiser(cartesToString(), getRole());
        JFrame fene = vueDefausse.getFenetre();
        fene.setVisible(true);
        fene.setAlwaysOnTop(true);
        vueDefausse.getFenetre().setCursor(Cursor.HAND_CURSOR);
    }
    
    

    /**
     * On remet le nombre d'actions à 3
     */
    public void reset() {
        nbAction = 3;
        setPouvoir(true);
    }

    public ArrayList<Integer> cartesTresor() {
        ArrayList<Integer> cartesTresor = new ArrayList<>();
        for (CarteTirage carte : cartes) {
            if (!carte.getUtilisable()) {
                cartesTresor.add(getCartes().indexOf(carte));
            }
        }
        return cartesTresor;
    }

    /**
     * Fais diminuer le nombre d'actions de 1
     */
    public void decrementeNbActions() {
        nbAction--;
    }

    /**
     * Permet de déplacer un aventurier sur une nouvelle Tuile en supprimant sa
     * position précédente
     *
     * @param grille
     * @param tuile
     */
    public void setTuile(Grille grille, Tuile tuile) {
        this.tuile.removeAventurier(this);
        tuile.addAventurier(this);
        this.tuile = tuile;
    }

    public void addCartes(ArrayList<CarteTirage> listeCartes) throws ExceptionAventurier {
        for (CarteTirage c : listeCartes) {
            if (c != null){
                getCartes().add(c);
            }
            else{
                System.out.println("Problème à gérer ultérieurement, on ne dois pas pouvoir ajouter une carte vide");
            }
        }
        if (cartes.size() > 5) {
            throw new ExceptionAventurier(this);
        }
    }

    public ArrayList<String> cartesToString() {
        ArrayList<String> listeCarte = new ArrayList<>();

        for (CarteTirage carte : cartes) {
            listeCarte.add(carte.getNom());
        }
        return listeCarte;

    }
    
    public void removeCarte(CarteTirage carte) {
        cartes.remove(carte);
    }

    public Tresor tresorRecuperable() {
        Tresor tresor = tuile.getTresor();
        if (tresor != null) {
            if (!tresor.isRecupere()) {
                int nbTresor = 0;
                for (CarteTirage carte : getCartes()) {
                    if (carte.getNom().equals(tresor.getName())) {
                        nbTresor += 1;
                    }
                }
                if (nbTresor >= 4)
                    return tresor;
            }
        }
        
        return null;
    }
    
    public void removeCartesTresor(Tresor tresor) {
        ArrayList<CarteTirage> nouveauDeck = new ArrayList();
        for (CarteTirage carte : getCartes()) {
            if (carte instanceof CarteTresor ) {
                if (((CarteTresor)carte).getTresor() != tresor)
                    nouveauDeck.add(carte);
            }
            else
                nouveauDeck.add(carte);
        }
        cartes = new ArrayList(nouveauDeck);
    }
    
   //Getters et Setters :
    
    /**
     * @return position de la tuile
     */
    public Position getPosition() {
        return tuile.getPosition();
    }

    /**
     * @return the tuile
     */
    public Tuile getTuile() {
        return tuile;
    }

    /**
     * @return the cartes
     */
    public ArrayList<CarteTirage> getCartes() {
        return cartes;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @return the vueDefausse
     */
    public VueDefausse getVueDefausse() {
        return vueDefausse;
    }

    /**
     * @return the nbAction
     */
    public int getNbAction() {
        return nbAction;
    }

    /**
     * @return the nomJoueur
     */
    public String getNomJoueur() {
        return nomJoueur;
    }

    /**
     * @return the pion
     */
    public Pion getPion() {
        return pion;
    }

    /**
     * @return the pouvoir
     */
    public Boolean getPouvoir() {
        return pouvoir;
    }

    /**
     * @param pouvoir the pouvoir to set
     */
    public void setPouvoir(Boolean pouvoir) {
        this.pouvoir = pouvoir;
    }

    /**
     * @param role the role to set
     */
    protected void setRole(Role role) {
        this.role = role;
    }

    /**
     * @param pion the pion to set
     */
    protected void setPion(Pion pion) {
        this.pion = pion;
    }

}
