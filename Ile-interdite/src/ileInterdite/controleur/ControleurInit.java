/*
*Tochangethislicenseheader,chooseLicenseHeadersinProjectProperties.
*Tochangethistemplatefile,chooseTools|Templates
*andopenthetemplateintheeditor.
*/
package ileInterdite.controleur;

import utilitaires.Role;
import ileInterdite.model.cartes.*;
import java.util.ArrayList;
import static java.util.Collections.reverse;
import java.util.HashMap;
import utilitaires.Tresor;
        

/*
*@authorvinetg
*/
public class ControleurInit{

    public ControleurInit(){

    //initialisationjoueurs
    ArrayList<String> avens=new ArrayList<>();
    avens.add("Lois");
    avens.add("Alexis");
    avens.add("Guillaume");
    avens.add("Haozhou");

    ArrayList<Role> roles=new ArrayList<>();
    roles.add(Role.Navigateur);
    roles.add(Role.Pilote);
    roles.add(Role.Messager);
    roles.add(Role.Ingenieur);
    
    HashMap<String, Tresor> tuilesTresor = new HashMap();
    tuilesTresor.put("LeTempleDeLaLune", Tresor.PIERRE);
    tuilesTresor.put("LeTempleDuSoleil", Tresor.PIERRE);
    tuilesTresor.put("LePalaisDesMarees", Tresor.CALICE);
    tuilesTresor.put("LePalaisDeCorail", Tresor.CALICE);
    tuilesTresor.put("LaCaverneDesOmbres", Tresor.CRISTAL);
    tuilesTresor.put("LaCaverneDuBrasier", Tresor.CRISTAL);
    tuilesTresor.put("LeJardinDesHurlements", Tresor.ZEPHYR);
    tuilesTresor.put("LeJardinDesMurmures", Tresor.ZEPHYR);


    
    Controleur ctrl = new Controleur(avens, roles, nomsDesTuiles(), tuilesTresor, cartesPioche(), 2);
    ctrl.initInondation();
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
