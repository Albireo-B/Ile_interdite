/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitaires;

import ileInterdite.model.aventurier.Aventurier;

/**
 *
 * @author vinetg
 */
public class ExceptionAventurier extends Exception{
    
    private Aventurier aventurier;
    
    public ExceptionAventurier(Aventurier aventurier){
        this.aventurier=aventurier;
    }

    
    //Getters et Setter :
    
    /**
     * @return the aventurier
     */
    public Aventurier getAventurier() {
        return aventurier;
    }
    
}
