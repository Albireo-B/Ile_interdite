/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitaires;

/**
 *
 * @author vinetg
 */
public enum Tresor {
    
    
    
    PIERRE(false),
    ZEPHYR(false),
    CRISTAL(false),
    CALICE(false);
        
    private Boolean recuperé;
       
    Tresor (Boolean recuperé) {
        this.recuperé = recuperé ;
    }


    public Boolean  getRecuperé() {
        return this.recuperé ;
    }
}
