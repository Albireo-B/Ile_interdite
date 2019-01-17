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
    PIERRE("Pierre"),
    ZEPHYR("Zephyr"),
    CRISTAL("Cristal"),
    CALICE("Calice");
    
    private String nomTresor;
    private Boolean recuperé;

    Tresor(String nom) {
        this.recuperé = false;
        nomTresor = nom;
    }
    
    
    //Getters et Setter :
    
    public String getName() {
        return nomTresor;
    }

    public Boolean isRecupere() {
        return this.recuperé;
    }
    
    /**
     * @param recuperé the recuperé to set
     */
    public void setRecuperé(Boolean recuperé) {
        this.recuperé = recuperé;
    }

    
}
