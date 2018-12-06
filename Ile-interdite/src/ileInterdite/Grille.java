/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author grosa
 */
public class Grille {
    private static int longueurTerrain = 6;
    private HashMap<Position, Tuile> tuiles = new HashMap();
    
    public Grille() {
        
    }
    
    public Tuile getTuile(Position p) {
        return tuiles.get(new Position(p.x, p.y));
    }
    
    // Retourne un tableau des 4 tuiles adjascentes à la position pos selon ce
    // pattern:
    //           tuile[0]
    //    tuile[3] pos tuile[1]
    //           tuile[2]
    public ArrayList<Tuile> tuilesAdjacentesCroix(Position posTuile) {
        ArrayList<Tuile> tuilesAdjacentes = new ArrayList();
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
    
    // Retourne un tableau des 4 tuiles adjacentes à la position pos selon ce
    // pattern:
    //    tuile[0] tuile[1] tuile[2]
    //    tuile[3]   pos    tuile[4]
    //    tuile[5] tuile[6] tuile[7]
    public ArrayList<Tuile> tuilesAdjacentesCarre(Position posTuile) {
        ArrayList<Tuile> tuilesAdjacentes = new ArrayList();
        
        for (int y = 1; y <= -1; y--) {
            for (int x = -1; x <= 1; x++) {
                if (x == 0 && y == 0)
                    continue;
                else {
                    Position pos = new Position(posTuile.x+x, posTuile.y+y);
                    if (tuiles.containsKey(pos))
                        tuilesAdjacentes.add(tuiles.get(pos));
                }
            }
        }
        
        return tuilesAdjacentes;
    }
    
    public ArrayList<Tuile> tuilesSeches() {
        ArrayList<Tuile> tuilesSeches = new ArrayList();
        
        for (int x = 0; x < longueurTerrain; x++) {
            for (int y = 0; y < longueurTerrain; y++) {
                if (tuiles.get(new Position(x, y)).getEtat() == EtatTuile.SECHE) {
                    tuilesSeches.add(tuiles.get(new Position(x, y)));
                }
            }
        }
        
        return tuilesSeches;
    }
    
    public ArrayList<Tuile> tuilesAccessiblesPlongeur(Tuile tuile) {
        ArrayList<Tuile> tuileAccessibles = new ArrayList();
        ArrayList<Tuile> tuileNonVerifiees = new ArrayList();
        tuileNonVerifiees.add(tuile);
        
        while(!tuileNonVerifiees.isEmpty())
        {
            Tuile tuileVerifiee = tuileNonVerifiees.get(0);
            ArrayList<Tuile> tuilesAdjacentes = new ArrayList();
            
            
            tuilesAdjacentes = this.tuilesAdjacentesCroix(tuileVerifiee.getPosition());
            
            for (Tuile tNew : tuilesAdjacentes) {
                if (tuileAccessibles.contains(tNew)){
                    tuilesAdjacentes.remove(tNew);
                }
            }
            
            tuileNonVerifiees.addAll(tuilesAdjacentes);
            
            tuileAccessibles.add(tuileVerifiee);
            tuileNonVerifiees.remove(0);
        }
        
        return tuileAccessibles;
    }
}
