/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.model;

import utilitaires.EtatTuile;
import java.util.ArrayList;
import java.util.HashMap;
import utilitaires.Tresor;

/**
 *
 * @author grosa
 */
public class Grille {

    private final int longueurTerrain = 6;
    private HashMap<Position, Tuile> tuiles = new HashMap();
    protected Tuile tuileHeliport = null;

    /**
     * Voici l'organisation de la grille (x = tuile): x -----> 0 1 2 3 4 5 y 0 x
     * x | 1 x x x x | 2 x x x x x x | 3 x x x x x x \/4 x x x x 5 x x
     *
     * @param nomTuiles
     */
    public Grille(ArrayList<String> nomTuiles, HashMap<String, Tresor> tuilesTresor) {
        ArrayList<Position> positionTuiles = getAllTilesPositions();
        ArrayList<String> listeTresors = new ArrayList(tuilesTresor.keySet());
        for (String nomTuile : nomTuiles) {
            Tuile t = new Tuile(nomTuile, positionTuiles.get(0));
            if (nomTuile.equals("Heliport"))
                tuileHeliport = t;
            
            if (listeTresors.contains(nomTuile))
                t.setTresor(tuilesTresor.get(nomTuile));
            
            tuiles.put(positionTuiles.get(0), t);
            positionTuiles.remove(0);
        }
    }

    /**
     * Retourne la position de tous les emplacements valides pour des tuiles
     *
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

            positions.add(new Position(x, y));

            i++;
        }

        return positions;
    }

    public Tuile getTuile(Position p) {
        return getTuiles().get(p);
    }

    /**
     * Retourne un tableau des 4 tuiles adjascentes à la position pos selon ce
     * pattern: tuile[2] tuile[3] pos tuile[1] tuile[0]
     *
     * @param tuile
     * @return
     */
    public ArrayList<Tuile> tuilesAdjacentesCroix(Tuile tuile) {
        ArrayList<Tuile> tuilesAdjacentes = new ArrayList();

        Position posTuile = tuile.getPosition();

        Position pos[] = {
            new Position(posTuile.getX(), posTuile.getY() + 1),
            new Position(posTuile.getX() + 1, posTuile.getY()),
            new Position(posTuile.getX(), posTuile.getY() - 1),
            new Position(posTuile.getX() - 1, posTuile.getY())
        };

        if (getTuiles().containsKey(pos[0])) {
            tuilesAdjacentes.add(getTuiles().get(pos[0]));
        }
        if (getTuiles().containsKey(pos[1])) {
            tuilesAdjacentes.add(getTuiles().get(pos[1]));
        }
        if (getTuiles().containsKey(pos[2])) {
            tuilesAdjacentes.add(getTuiles().get(pos[2]));
        }
        if (getTuiles().containsKey(pos[3])) {
            tuilesAdjacentes.add(getTuiles().get(pos[3]));
        }

        return tuilesAdjacentes;
    }

    /**
     * Retourne une liste de 8 tuiles adjacentes à la position pos selon ce
     * pattern: tuile[0] tuile[1] tuile[2] tuile[3] pos tuile[4] tuile[5]
     * tuile[6] tuile[7]
     *
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
                    Position pos = new Position(posTuile.getX() + x, posTuile.getY() + y);
                    if (getTuiles().containsKey(pos)) {
                        tuilesAdjacentes.add(getTuiles().get(pos));
                    }
                }
            }
        }

        return tuilesAdjacentes;
    }

    /**
     * Retourne une liste de toutes les tuiles non coulées
     *
     * @param tuile
     * @return
     */
    public ArrayList<Tuile> tuilesNonCoulees(Tuile tuile) {
        ArrayList<Tuile> tuilesSeches = new ArrayList();

        for (int x = 0; x < longueurTerrain; x++) {
            for (int y = 0; y < longueurTerrain; y++) {
                if (getTuiles().get(new Position(x, y)) != null && getTuiles().get(new Position(x, y)).getEtat() != EtatTuile.COULEE) {
                    tuilesSeches.add(getTuiles().get(new Position(x, y)));
                }
            }
        }
        tuilesSeches.remove(tuile);
        return tuilesSeches;
    }

    /**
     * Retourne une liste de tuiles adjacentes à la position pos par rapport au
     * plongeur et à son pouvoir
     *
     * @param tuileJoueur
     * @return
     */
    public ArrayList<Tuile> tuilesAccessiblesPlongeur(Tuile tuileJoueur) {
        ArrayList<Tuile> tuilesAccessibles = new ArrayList();
        ArrayList<Tuile> tuilesNonVerifiees = tuilesAdjacentesCroix(tuileJoueur);

        while (!tuilesNonVerifiees.isEmpty()) {
            Tuile tuileCourante = tuilesNonVerifiees.get(0);

            //Si la tuile est inondée, on augmente le nombre de tuiles à vérifier:
            if (tuileCourante.getEtat() != EtatTuile.SECHE) {
                ArrayList<Tuile> nouvellesTuiles = tuilesAdjacentesCroix(tuileCourante);
                // On évite d'ajouter la tuile du joueur
                if (nouvellesTuiles.contains(tuileJoueur)) {
                    nouvellesTuiles.remove(tuileJoueur);
                }

                ArrayList<Tuile> tuilesAjoutees = new ArrayList(nouvellesTuiles);
                // On supprime les tuiles redondantes par rapport à tuilesAccessibles et tuilesNonVerifiees
                for (Tuile t : nouvellesTuiles) {
                    if (tuilesAccessibles.contains(t)) {
                        tuilesAjoutees.remove(t);
                    } else if (tuilesNonVerifiees.contains(t)) {
                        tuilesAjoutees.remove(t);
                    }
                }

                tuilesNonVerifiees.addAll(tuilesAjoutees);
            }
            // C'est bon, la tuile est traitée:
            tuilesAccessibles.add(tuileCourante);
            tuilesNonVerifiees.remove(tuileCourante);
        }

        return tuilesAccessibles;
    }

    public ArrayList<Tuile> getToutesTuiles() {
        return new ArrayList<>(getTuiles().values());
    }

    /**
     * @return the tuiles
     */
    public HashMap<Position, Tuile> getTuiles() {
        return tuiles;
    }

    /**
     * @return the tuileHeliport
     */
    public Tuile getTuileHeliport() {
        return tuileHeliport;
    }
}
