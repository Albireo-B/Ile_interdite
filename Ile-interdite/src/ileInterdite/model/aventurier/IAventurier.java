/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.model.aventurier;

import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author vinetg
 */
public class IAventurier {
    private JButton boutonAventurier;
    private ActionListener actions;
    
    public IAventurier(JButton boutonAventurier,ActionListener actions){
        this.actions=actions;
        this.boutonAventurier=boutonAventurier;
    }

//    public rendreAventurierCliquable(){
//        
//    }
    
   
    /**
     * @return the boutonAventurier
     */
    public JButton getBoutonAventurier() {
        return boutonAventurier;
    }

    /**
     * @return the actions
     */
    public ActionListener getActions() {
        return actions;
    }

    /**
     * @param actions the actions to set
     */
    public void setActions(ActionListener actions) {
        this.actions = actions;
    }
}
