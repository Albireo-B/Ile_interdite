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
    PIERRE(false, "Pierre"),
    ZEPHYR(false, "Cristal"),
    CRISTAL(false, "Cristal"),
    CALICE(false, "Calice");
    
    private String nomTresor;
    private Boolean recuperé;

    Tresor(Boolean recuperé, String nom) {
        this.recuperé = recuperé;
        nomTresor = nom;
    }
    
    public String getName() {
        return nomTresor;
    }

    public Boolean getRecuperé() {
        return this.recuperé;
    }
    
    /**
     * @param recuperé the recuperé to set
     */
    public void setRecuperé(Boolean recuperé) {
        this.recuperé = recuperé;
    }

    
}
