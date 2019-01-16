/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import ileInterdite.message.MessageCarte;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import utilitaires.Action;
import utilitaires.Role;

/**
 *
 * @author vinetg
 */
public class ICarte extends JButton {

    private Action action;
    
    private String nomCarte;

    private ActionListener actionListener = null;
    private String path = "src/images/cartes/";
    private int imgWidth = 120;
    private int imgHeigh = 200;

    private ImageIcon image = null;

    public ICarte(String nomImage, Action action) {
        setImage(nomImage);
        this.action = action;
    }

    public void removeActionListener() {
        removeActionListener(actionListener);
        actionListener = null;
        setForeground(null);
    }

    @Override
    public void addActionListener(ActionListener act) {
        if (actionListener == null) {
            actionListener = act;
            super.addActionListener(act);
            setForeground(Color.red);
        } else {
            System.out.println("ERREUR: N'essayez pas d'ajouter deux actionlisteners");
        }
    }

    public MessageCarte getMessage(Role role) {
        return new MessageCarte(getNom(), action, role);
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nomCarte;
    }

    
        
    public void rescale(ImageIcon image,int resizedWidth,int resizedHeight){
        image.getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_DEFAULT);    
    }
    
    
    /**
     * @param nom the nom to set
     */
    public void setImage(String nomImage) {
        if (nomImage != null) {
            nomCarte = nomImage;
            image = new ImageIcon(new ImageIcon(path + nomImage + ".png").getImage().getScaledInstance(imgWidth, imgHeigh, Image.SCALE_DEFAULT));
            setIcon(image);
        }
        else {
            setIcon(null);
            nomCarte = "";
        }
    }

    /**
     * @param action the action to set
     */
    public void setAction(Action action) {
        this.action = action;
    }

}
