/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.controleur;

import ileInterdite.model.Position;
import utilitaires.Role;
import ileInterdite.model.cartes.*;
import java.util.ArrayList;
import static java.util.Collections.reverse;
import utilitaires.ExceptionAventurier;
import utilitaires.Tresor;

/**
 *
 * @author vinetg
 */
public class ControleurInit {
    
    public ControleurInit(){
        
 
            // initialisation joueurs
            ArrayList <String> avens=new ArrayList<>();
            avens.add("Lois");
            avens.add("Alexis");
            avens.add("Guillaume");
            avens.add("Clement");
            
            ArrayList<Role> roles = new ArrayList();
            roles.add(Role.Explorateur);
            roles.add(Role.Plongeur);
            roles.add(Role.Pilote);
            roles.add(Role.Ingénieur);
            
            
            Controleur ctrl = new Controleur(avens,roles, nomsDesTuiles(), cartesPioche(), 2);
            
            
            
            //initialisation inondation debut de partie
            try{
            ctrl.monteeDesEaux(new Position(2, 0));
            ctrl.monteeDesEaux(new Position(4, 3));
            ctrl.monteeDesEaux(new Position(2, 5));
            ctrl.monteeDesEaux(new Position(2, 3));
            ctrl.monteeDesEaux(new Position(0, 3));
            
            ctrl.monteeDesEaux(new Position(3, 2));
            ctrl.monteeDesEaux(new Position(3, 2));
            ctrl.getPiocheInondation().remove(ctrl.getGrille().getTuile(new Position(3, 2)));
            
            ctrl.monteeDesEaux(new Position(3, 3));
            ctrl.monteeDesEaux(new Position(3, 3));
            ctrl.getPiocheInondation().remove(ctrl.getGrille().getTuile(new Position(3, 3)));
            
            ctrl.monteeDesEaux(new Position(3, 4));
            ctrl.monteeDesEaux(new Position(3, 4));
            ctrl.getPiocheInondation().remove(ctrl.getGrille().getTuile(new Position(3, 4)));
            
            ctrl.monteeDesEaux(new Position(1, 3));
            ctrl.monteeDesEaux(new Position(1, 3));
            ctrl.getPiocheInondation().remove(ctrl.getGrille().getTuile(new Position(1, 3)));
        } catch (ExceptionAventurier ex) {}
        
            ctrl.initCartes();
    }
    
    
    public ArrayList<String> nomsDesTuiles() {
        ArrayList<String> ndt=new ArrayList<>();    
        ndt.add("Le Pont des Abimes");
        ndt.add("La Porte de Bronze");
        ndt.add("La Caverne des Ombres");
        ndt.add("La Porte de Fer");
        ndt.add("La Porte d'Or");
        ndt.add("Les Falaises de l'Oubli");
        ndt.add("Le Palais de Corail");
        ndt.add("La Porte d'Argent");
        ndt.add("Les Dunes de l'Illusion");
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
        reverse(ndt);
        return ndt;
       
    }
    
    public ArrayList<CarteTirage> cartesPioche() {
        ArrayList<CarteTirage> pioche=new ArrayList<>();
        
        
        
        for (int i = 0; i<4;i++){
            for (int j = 0; j<5;j++){
                pioche.add(new CarteTresor(Tresor.values()[i]));
            }
        }
        for (int i = 0; i<3;i++){
            pioche.add(new CarteHelicoptere());
        }
        
        for (int i = 0; i<2;i++){
            pioche.add(new CarteSacDeSable());
        }
        for (int i = 0; i<3;i++){
            pioche.add(new  CarteMonteeDesEaux());
        }
        return pioche;
    }
}
