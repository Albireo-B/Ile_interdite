/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.aventurier;

/**
 *
 * @author deniaul
 */
public enum Role {
    Explorateur("La Porte de Cuivre"),
    Ingénieur("La Porte de Bronze"),
    Messager("La Porte d'Argent"),
    Pilote("Heliport"),
    Navigateur("La Porte d'Or"),
    Plongeur("La Porte de Fer");
    
    private String caseDépart;
    
    Role (String caseDépart){
    this.caseDépart = caseDépart;
}

    /**
     * @return the caseDépart
     */
    public String getCaseDépart() {
        return caseDépart;
    }
    
}
