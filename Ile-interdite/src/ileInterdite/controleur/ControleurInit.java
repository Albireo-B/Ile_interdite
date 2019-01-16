/*
*Tochangethislicenseheader,chooseLicenseHeadersinProjectProperties.
*Tochangethistemplatefile,chooseTools|Templates
*andopenthetemplateintheeditor.
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
*@authorvinetg
*/
public class ControleurInit{

    public ControleurInit(){

    //initialisationjoueurs
    ArrayList<String> avens=new ArrayList<>();
    avens.add("Lois");
    avens.add("Alexis");
    avens.add("Guillaume");
    avens.add("Clement");

    ArrayList<Role> roles=new ArrayList<>();
    roles.add(Role.Navigateur);
    roles.add(Role.Pilote);
    roles.add(Role.Messager);
    roles.add(Role.Ingénieur);

    Controleur ctrl=new Controleur(avens,roles,nomsDesTuiles(),cartesPioche(),2);

    //initialisation inondation debut de partie
    try{
        ctrl.monteeDesEaux(new Position(2,0));
        ctrl.monteeDesEaux(new Position(4,3));
        ctrl.monteeDesEaux(new Position(2,5));
        ctrl.monteeDesEaux(new Position(2,3));
        ctrl.monteeDesEaux(new Position(0,3));

        ctrl.monteeDesEaux(new Position(3,2));
        ctrl.monteeDesEaux(new Position(3,2));
        ctrl.getPiocheInondation().remove(ctrl.getGrille().getTuile(new Position(3,2)));

        ctrl.monteeDesEaux(new Position(3,3));
        ctrl.monteeDesEaux(new Position(3,3));
        ctrl.getPiocheInondation().remove(ctrl.getGrille().getTuile(new Position(3,3)));

        ctrl.monteeDesEaux(new Position(3,4));
        ctrl.monteeDesEaux(new Position(3,4));
        ctrl.getPiocheInondation().remove(ctrl.getGrille().getTuile(new Position(3,4)));

        ctrl.monteeDesEaux(new Position(1,3));
        ctrl.monteeDesEaux(new Position(1,3));
        ctrl.getPiocheInondation().remove(ctrl.getGrille().getTuile(new Position(1,3)));
    }catch(ExceptionAventurier ex){
    }

    ctrl.initCartes();
    }

    public ArrayList<String> nomsDesTuiles(){
    ArrayList<String> ndt=new ArrayList<>();
    ndt.add("LePontDesAbimes");
    ndt.add("LaPorteDeBronze");
    ndt.add("LaCaverneDesOmbres");
    ndt.add("LaPorteDeFer");
    ndt.add("LaPortedOr");
    ndt.add("LesFalaisesDeLOubli");
    ndt.add("LePalaisDeCorail");
    ndt.add("LaPortedArgent");
    ndt.add("LesDunesDeLIllusion");
    ndt.add("Heliport");
    ndt.add("LaPorteDeCuivre");
    ndt.add("LeJardinDesHurlements");
    ndt.add("LaForetPourpre");
    ndt.add("LeLagonPerdu");
    ndt.add("LeMaraisBrumeux");
    ndt.add("Observatoire");
    ndt.add("LeRocherFantome");
    ndt.add("LaCaverneDuBrasier");
    ndt.add("LeTempleDuSoleil");
    ndt.add("LeTempleDeLaLune");
    ndt.add("LePalaisDesMarees");
    ndt.add("LeValDuCrepuscule");
    ndt.add("LaTourDuGuet");
    ndt.add("LeJardinDesMurmures");
    reverse(ndt);
    return ndt;

    }

    public ArrayList<CarteTirage> cartesPioche(){
        
    ArrayList<CarteTirage> pioche=new ArrayList<>();

    for(int i=0;i<4;i++){
        for(int j=0;j<5;j++){
            pioche.add(new CarteTresor(Tresor.values()[i]));
        }
    }
    
    for(int i=0;i<3;i++){
        pioche.add(new CarteHelicoptere());
    }

    for(int i=0;i<2;i++){
        pioche.add(new CarteSacDeSable());
    } 
    
    for(int i=0;i<3;i++){
        pioche.add(new CarteMonteeDesEaux());
    }
    
    return pioche;
    
    }
}
