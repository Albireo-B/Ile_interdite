 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import java.util.ArrayList;
import ileInterdite.controleur.Controleur;

/**
 *
 * @author daiha
 */
public class testTour {
    
    public static void main(String[] args) {
        
        ArrayList<String> nomTuiles = nomsDesTuiles();
       
        ArrayList <String> avens=new ArrayList<>();
        // initializing  4 players
        avens.add("clement");
        avens.add("Guillaume");
        avens.add("Alexis");
        avens.add("Lois");
        

        Controleur ctrl = new Controleur(avens, nomTuiles);
                
        
        
        
    }
        
    static public ArrayList<String> nomsDesTuiles() {
        
        ArrayList<String> ndt=new ArrayList<>();    
        ndt.add("Le Pont des Abimes");
        ndt.add("La Porte de Bronze");
        ndt.add("La Caverne des Ombres");
        ndt.add("La Porte de Fer");
        ndt.add("La Porte d’Or");
        ndt.add("Les Falaises de l’Oubli");
        ndt.add("Le Palais de Corail");
        ndt.add("La Porte d’Argent");
        ndt.add("Les Dunes de l’Illusion");
        ndt.add("Heliport");
        ndt.add("La Porte de Cuivre");
        ndt.add("Le Jardin des Hurlements");
        ndt.add("La Foret Pourpre");
        ndt.add("Le Lagon Perdu");
        ndt.add("Le Marais Brumeux");
        ndt.add("Observatoire");
        ndt.add("Le Rocher Fantome");
        ndt.add("La Caverne du Brasier");
        ndt.add("Le Temple du Soleil");
        ndt.add("Le Temple de La Lune");
        ndt.add("Le Palais des Marees");
        ndt.add("Le Val du Crepuscule");
        ndt.add("La Tour du Guet");
        ndt.add("Le Jardin des Murmures");
        return (ndt);
       
    }
}
