/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitaires;

/**
 *
 * @author deniaul
 */
public enum Role {
    Explorateur("LaPorteDeCuivre"),
    Ingénieur("LaPorteDeBronze"),
    Messager("LaPortedArgent"),
    Pilote("Heliport"),
    Navigateur("LaPortedOr"),
    Plongeur("LaPorteDeFer");

    private String caseDepart;

    Role(String caseDepart) {
        this.caseDepart = caseDepart;
    }

    //Getters et Setter :
    
    
    /**
     * @return the caseDépart
     */
    public String getCaseDepart() {
        return caseDepart;
    }
}
