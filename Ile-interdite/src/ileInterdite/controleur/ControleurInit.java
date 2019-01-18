/*
*Tochangethislicenseheader,chooseLicenseHeadersinProjectProperties.
*Tochangethistemplatefile,chooseTools|Templates
*andopenthetemplateintheeditor.
 */
package ileInterdite.controleur;

import utilitaires.Role;
import ileInterdite.model.cartes.*;
import ileInterdite.vues.VueInit;
import java.util.ArrayList;
import static java.util.Collections.reverse;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import utilitaires.Tresor;
import java.util.Collections;


/*
*@authorvinetg
 */
public class ControleurInit implements Observer {

    private final VueInit vueInit;
    private ArrayList<String> avens;
    private ArrayList<Role> roles;
    private HashMap<String, Tresor> tuilesTresor;

    public ControleurInit() {

        vueInit = new VueInit();
        vueInit.afficher();
        vueInit.addObserver(this);

        //initialisationjoueurs
        roles = new ArrayList<>();
        roles.add(Role.Navigateur);
        roles.add(Role.Pilote);
        roles.add(Role.Messager);
        roles.add(Role.Ingenieur);
        roles.add(Role.Explorateur);
        roles.add(Role.Plongeur);
        //========ajouter que 4 joueurs aléatoire======
        Collections.shuffle(roles);
        roles.remove(0);
        roles.remove(0);

        tuilesTresor = new HashMap();
        tuilesTresor.put("LeTempleDeLaLune", Tresor.PIERRE);
        tuilesTresor.put("LeTempleDuSoleil", Tresor.PIERRE);
        tuilesTresor.put("LePalaisDesMarees", Tresor.CALICE);
        tuilesTresor.put("LePalaisDeCorail", Tresor.CALICE);
        tuilesTresor.put("LaCaverneDesOmbres", Tresor.CRISTAL);
        tuilesTresor.put("LaCaverneDuBrasier", Tresor.CRISTAL);
        tuilesTresor.put("LeJardinDesHurlements", Tresor.ZEPHYR);
        tuilesTresor.put("LeJardinDesMurmures", Tresor.ZEPHYR);

    }

    public ArrayList<String> nomsDesTuiles() {
        ArrayList<String> ndt = new ArrayList<>();
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

    @Override
    public void update(Observable arg0, Object arg1) {

        if (arg1 != null) {
            vueInit.close();
            avens = (ArrayList<String>) (arg1);
            Controleur ctrl = new Controleur(avens, roles, nomsDesTuiles(), tuilesTresor, cartesPioche(), 2);
            ctrl.initInondation();
            ctrl.initCartes();
        }
    }

    public ArrayList<CarteTirage> cartesPioche() {

        ArrayList<CarteTirage> pioche = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                pioche.add(new CarteTresor(Tresor.values()[i]));
            }
        }

        for (int i = 0; i < 3; i++) {
            pioche.add(new CarteHelicoptere());
        }

        for (int i = 0; i < 2; i++) {
            pioche.add(new CarteSacDeSable());
        }

        for (int i = 0; i < 3; i++) {
            pioche.add(new CarteMonteeDesEaux());
        }

        return pioche;

    }
}
