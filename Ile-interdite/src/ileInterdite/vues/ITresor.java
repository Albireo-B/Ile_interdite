/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import utilitaires.Tresor;

/**
 *
 * @author grosa
 */
public class ITresor extends JLabel {

    private Tresor tresor;

    private String path = "src/images/tresors/";
    private int imgWidth = 50;
    private int imgHeight = 70;

    private ImageIcon pierreGrisee = new ImageIcon(new ImageIcon(path + "pierreGrisee.png").getImage().getScaledInstance(imgWidth, imgHeight, Image.SCALE_DEFAULT));
    private ImageIcon pierre = new ImageIcon(new ImageIcon(path + "pierre.png").getImage().getScaledInstance(imgWidth, imgHeight, Image.SCALE_DEFAULT));
    private ImageIcon caliceGrisee = new ImageIcon(new ImageIcon(path + "caliceGrisee.png").getImage().getScaledInstance(imgWidth, imgHeight, Image.SCALE_DEFAULT));
    private ImageIcon calice = new ImageIcon(new ImageIcon(path + "calice.png").getImage().getScaledInstance(imgWidth, imgHeight, Image.SCALE_DEFAULT));
    private ImageIcon zephyr = new ImageIcon(new ImageIcon(path + "zephyr.png").getImage().getScaledInstance(imgWidth, imgHeight, Image.SCALE_DEFAULT));
    private ImageIcon zephyrGrisee = new ImageIcon(new ImageIcon(path + "zephyrGrisee.png").getImage().getScaledInstance(imgWidth, imgHeight, Image.SCALE_DEFAULT));
    private ImageIcon cristal = new ImageIcon(new ImageIcon(path + "cristal.png").getImage().getScaledInstance(imgWidth, imgHeight, Image.SCALE_DEFAULT));
    private ImageIcon cristalGrisee = new ImageIcon(new ImageIcon(path + "cristalGrisee.png").getImage().getScaledInstance(imgWidth, imgHeight, Image.SCALE_DEFAULT));

    public ITresor(Tresor tresor) {
        super("", JLabel.CENTER);
        this.tresor = tresor;
        setTrouve(false);
    }

    public void setTrouve(boolean trouve) {
        if (trouve) {
            switch (tresor) {
                case PIERRE:
                    setIcon(pierre);
                    break;
                case CALICE:
                    setIcon(calice);
                    break;
                case CRISTAL:
                    setIcon(cristal);
                    break;
                case ZEPHYR:
                    setIcon(zephyr);
                    break;
            }
        } else {
            switch (tresor) {
                case PIERRE:
                    setIcon(pierreGrisee);
                    break;
                case CALICE:
                    setIcon(caliceGrisee);
                    break;
                case CRISTAL:
                    setIcon(cristalGrisee);
                    break;
                case ZEPHYR:
                    setIcon(zephyrGrisee);
                    break;
            }
        }
    }
}
