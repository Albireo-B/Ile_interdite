/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.model.cartes;

import utilitaires.*;

/**
 *
 * @author vinetg
 */
public class CarteTresor extends CarteTirage {
    
    private Tresor tresor;
    
    public CarteTresor(Tresor tresor){
        super(false);
        this.tresor = tresor;
    }
}
