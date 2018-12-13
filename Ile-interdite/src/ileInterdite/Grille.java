/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author grosa
 */
public class Grille {
    private final int longueurTerrain = 6;
    private HashMap<Position, Tuile> tuiles = new HashMap();
    
    /**
    * Voici l'organisation de la grille (x = tuile):
    *      x ----->
    *        0  1  2  3  4  5
    *    y 0       x  x
    *    | 1    x  x  x  x
    *    | 2 x  x  x  x  x  x
    *    | 3 x  x  x  x  x  x
    *    \/4    x  x  x  x
    *      5       x  x
     * @param nomTuiles
    */
    public Grille(ArrayList<String> nomTuiles) {
        ArrayList<Position> positionTuiles = getAllTilesPositions();
        for (String nomTuile : nomTuiles) {
            tuiles.put(positionTuiles.get(0), new Tuile(nomTuile, positionTuiles.get(0)));
            positionTuiles.remove(0);
        }
        
    }
    
    /**
     * Retourne la position de tous les emplacements valides pour des tuiles
     * @return 
    */
    public static ArrayList<Position> getAllTilesPositions() {
        ArrayList<Position> positions = new ArrayList();
        int demi_longueur = 1; // nombre de tuile dans la ligne actuelle / 2
        int increment = 1; // Vaut +1 pour la première moitié des lignes, -1 ensuite
        int i = 0; // numero de la tuile de la ligne actuelle
        int x = 0, y = 5; // position de la tuile actuelle
        
        /**
         * On parcours toutes les tuiles:
        */       
        for (int indexTuile = 0; indexTuile < 24; indexTuile++) {
            if (i == demi_longueur * 2) {
                i = 0;
                y--;
                demi_longueur += increment;
                if (demi_longueur == 4) {
                    demi_longueur = 3;
                    increment *= -1;
                }
            }
            x = 3 - demi_longueur + i;

            positions.add(new Position(x,y));
            
            i++;
        }
        
        return positions;
    }
    
    public Tuile getTuile(Position p) {
        return tuiles.get(p);
    }
    
    /** Retourne un tableau des 4 tuiles adjascentes à la position pos selon ce
    *pattern:
    *           tuile[2]
    *    tuile[3] pos tuile[1]
    *           tuile[0]
     * @param tuile
     * @return 
    */
    public ArrayList<Tuile> tuilesAdjacentesCroix(Tuile tuile) {
        ArrayList<Tuile> tuilesAdjacentes = new ArrayList();
        
        Position posTuile = tuile.getPosition();
        
        Position pos[] = {
            new Position(posTuile.x, posTuile.y+1),
            new Position(posTuile.x+1, posTuile.y),
            new Position(posTuile.x, posTuile.y-1),
            new Position(posTuile.x-1, posTuile.y)
        };
        
        if (tuiles.containsKey(pos[0]))
            tuilesAdjacentes.add(tuiles.get(pos[0]));
        if (tuiles.containsKey(pos[1]))
            tuilesAdjacentes.add(tuiles.get(pos[1]));
        if (tuiles.containsKey(pos[2]))
            tuilesAdjacentes.add(tuiles.get(pos[2]));
        if (tuiles.containsKey(pos[3]))
            tuilesAdjacentes.add(tuiles.get(pos[3]));
        
        return tuilesAdjacentes;
    }
    
    /** 
    * Retourne une liste de 8 tuiles adjacentes à la position pos selon ce pattern:
    *    tuile[0] tuile[1] tuile[2]
    *    tuile[3]   pos    tuile[4]
    *    tuile[5] tuile[6] tuile[7]
     * @param tuile
     * @return 
    */
    public ArrayList<Tuile> tuilesAdjacentesCarre(Tuile tuile) {
        ArrayList<Tuile> tuilesAdjacentes = new ArrayList();
        
        Position posTuile = tuile.getPosition();
        
        for (int y = -1; y <= 1; y++) {
            for (int x = -1; x <= 1; x++) {
                if (x == 0 && y == 0) {
                } else {
                    Position pos = new Position(posTuile.x+x, posTuile.y+y);
                    if (tuiles.containsKey(pos))
                        tuilesAdjacentes.add(tuiles.get(pos));
                }
            }
        }
        
        return tuilesAdjacentes;
    }
    
    
    /** 
     * Retourne une liste de toutes les tuiles non coulées
     * @return 
    */
    public ArrayList<Tuile> tuilesNonCoulees() {
        ArrayList<Tuile> tuilesSeches = new ArrayList();
        
        for (int x = 0; x < longueurTerrain; x++) {
            for (int y = 0; y < longueurTerrain; y++) {
                if (tuiles.get(new Position(x, y)).getEtat() != EtatTuile.COULEE) {
                    tuilesSeches.add(tuiles.get(new Position(x, y)));
                }
            }
        }
        
        return tuilesSeches;
    }
    
    
    /** 
    * Retourne une liste de tuiles adjacentes à la position pos par rapport au plongeur et à son pouvoir
     * @param tuile
     * @return 
    */
    public ArrayList<Tuile> tuilesAccessiblesPlongeur(Tuile tuile) {
        ArrayList<Tuile> tuileAccessibles = new ArrayList();
        ArrayList<Tuile> tuileNonVerifiees = new ArrayList();
        tuileNonVerifiees.add(tuile);
        
        while(!tuileNonVerifiees.isEmpty())
        {
            Tuile tuileVerifiee = tuileNonVerifiees.get(0);
            ArrayList<Tuile> tuilesAVerifier = new ArrayList();
            
            tuilesAVerifier = this.tuilesAdjacentesCroix(tuileVerifiee);
            
            for (Tuile tNew : tuilesAVerifier) {
                if (tNew.getEtat() == EtatTuile.SECHE) {
                    tuileAccessibles.add(tNew);
                    tuilesAVerifier.remove(tNew);
                }
                else if (tuileAccessibles.contains(tNew)){
                    tuilesAVerifier.remove(tNew);
                }
            }
            
            tuileNonVerifiees.addAll(tuilesAVerifier);
            
            tuileAccessibles.add(tuileVerifiee);
            tuileNonVerifiees.remove(0);
        }
        
        return tuileAccessibles;
    }
    
    public ArrayList<Tuile> getToutesTuiles() {
        return new ArrayList<Tuile>(tuiles.values());
    }
}
