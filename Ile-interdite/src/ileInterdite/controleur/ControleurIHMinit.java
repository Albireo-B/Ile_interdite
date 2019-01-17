/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.controleur;
import ileInterdite.vues.VueIHMinit;
import java.util.Observable;
import java.util.Observer;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author genevoic
 */
public class ControleurIHMinit implements Observer {
        /*creation d'un nouveau MyUI*/
        private final VueIHMinit vueInit;
    
        /*creation du controleur et affichage de la fenetre*/
    public ControleurIHMinit (){
        vueInit = new VueIHMinit();
        vueInit.afficher();
        vueInit.addObserver(this);
        }

    @Override
    public void update(Observable arg0, Object arg1) {
        vueInit.afficher();
        vueInit.suivant();
    }
    
}