/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author genevoic
 */
public class VueIHMinit extends Observable{
     private JFrame window ;
     private boolean apres;
    /**
     * @param args the command line arguments
     */
    public VueIHMinit () {
        window = new JFrame();
        window.setSize(500, 350);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setTitle("Selectionner vos joueurs");
        apres = false;
        
        JPanel NJ = new JPanel();
        window.add(NJ, BorderLayout.NORTH);
        JLabel label0 = new JLabel("Nombre de joueurs");
        NJ.add(label0);
        JSlider liste = new JSlider(JSlider.HORIZONTAL,2,4,3);
        NJ.add(liste);
        liste.setMajorTickSpacing(1);
        liste.setPaintTicks(true);
        liste.setPaintLabels(true);
        liste.setLabelTable(liste.createStandardLabels(1));
        JPanel mainPanel4 = new JPanel(new GridLayout(9, 5));
        JPanel mainPanel3 = new JPanel(new GridLayout(7, 5));
        JPanel mainPanel2 = new JPanel(new GridLayout(5, 5));
        
        liste.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent event) {
                if (liste.getValue()==4){
                    mainPanel2.removeAll();
                    mainPanel3.removeAll();
                    mainPanel4.removeAll();
                    getWindow().add(mainPanel4);
                    window.setSize(500, 350);
                    
                    mainPanel4.add(new JLabel(""));
                    mainPanel4.add(new JLabel(""));
                    mainPanel4.add(new JLabel(""));
                    mainPanel4.add(new JLabel(""));
                    mainPanel4.add(new JLabel(""));
                    
                    mainPanel4.add(new JLabel(""));
                    mainPanel4.add(new JLabel("1",SwingConstants.CENTER));
                    JLabel label1 = new JLabel("Pseudo",SwingConstants.CENTER);
                    mainPanel4.add(label1);
                    JTextField N1 = new JTextField(20);
                    mainPanel4.add(N1);
                    mainPanel4.add(new JLabel(""));
                    
                    mainPanel4.add(new JLabel(""));
                    mainPanel4.add(new JLabel(""));
                    mainPanel4.add(new JLabel(""));
                    mainPanel4.add(new JLabel(""));
                    mainPanel4.add(new JLabel(""));
                    
                    mainPanel4.add(new JLabel(""));
                    mainPanel4.add(new JLabel("2",SwingConstants.CENTER));
                    JLabel label3 = new JLabel("Pseudo",SwingConstants.CENTER);
                    mainPanel4.add(label3);
                    JTextField N2 = new JTextField(20);
                    mainPanel4.add(N2);
                    mainPanel4.add(new JLabel(""));
                    
                    mainPanel4.add(new JLabel(""));
                    mainPanel4.add(new JLabel(""));
                    mainPanel4.add(new JLabel(""));
                    mainPanel4.add(new JLabel(""));
                    mainPanel4.add(new JLabel(""));
                    
                    mainPanel4.add(new JLabel(""));
                    mainPanel4.add(new JLabel("3",SwingConstants.CENTER));
                    JLabel label5 = new JLabel("Pseudo",SwingConstants.CENTER);
                    mainPanel4.add(label5);
                    JTextField N3 = new JTextField(20);
                    mainPanel4.add(N3);
                    mainPanel4.add(new JLabel(""));

                    mainPanel4.add(new JLabel(""));
                    mainPanel4.add(new JLabel(""));
                    mainPanel4.add(new JLabel(""));
                    mainPanel4.add(new JLabel(""));
                    mainPanel4.add(new JLabel(""));
                    
                    mainPanel4.add(new JLabel(""));
                    mainPanel4.add(new JLabel("4",SwingConstants.CENTER));
                    JLabel label7 = new JLabel("Pseudo",SwingConstants.CENTER);
                    mainPanel4.add(label7);
                    JTextField N4 = new JTextField(20);
                    mainPanel4.add(N4);
                    mainPanel4.add(new JLabel(""));
                    
                    mainPanel4.add(new JLabel(""));
                    mainPanel4.add(new JLabel(""));
                    mainPanel4.add(new JLabel(""));
                    mainPanel4.add(new JLabel(""));
                    mainPanel4.add(new JLabel(""));

                    } else if (liste.getValue()==3){
                    mainPanel2.removeAll();
                    mainPanel3.removeAll();
                    mainPanel4.removeAll();
                    getWindow().add(mainPanel3);
                    window.setSize(500, 250);
                    
                    mainPanel3.add(new JLabel(""));
                    mainPanel3.add(new JLabel(""));
                    mainPanel3.add(new JLabel(""));
                    mainPanel3.add(new JLabel(""));
                    mainPanel3.add(new JLabel(""));
                    
                    mainPanel3.add(new JLabel(""));
                    mainPanel3.add(new JLabel("1",SwingConstants.CENTER));
                    JLabel label1 = new JLabel("Pseudo",SwingConstants.CENTER);
                    mainPanel3.add(label1);
                    JTextField N1 = new JTextField(20);
                    mainPanel3.add(N1);
                    mainPanel3.add(new JLabel(""));
                    
                    mainPanel3.add(new JLabel(""));
                    mainPanel3.add(new JLabel(""));
                    mainPanel3.add(new JLabel(""));
                    mainPanel3.add(new JLabel(""));
                    mainPanel3.add(new JLabel(""));
                    
                    mainPanel3.add(new JLabel(""));
                    mainPanel3.add(new JLabel("2",SwingConstants.CENTER));
                    JLabel label3 = new JLabel("Pseudo",SwingConstants.CENTER);
                    mainPanel3.add(label3);
                    JTextField N2 = new JTextField(20);
                    mainPanel3.add(N2);
                    mainPanel3.add(new JLabel(""));
                    
                    mainPanel3.add(new JLabel(""));
                    mainPanel3.add(new JLabel(""));
                    mainPanel3.add(new JLabel(""));
                    mainPanel3.add(new JLabel(""));
                    mainPanel3.add(new JLabel(""));
                    
                    mainPanel3.add(new JLabel(""));
                    mainPanel3.add(new JLabel("3",SwingConstants.CENTER));
                    JLabel label5 = new JLabel("Pseudo",SwingConstants.CENTER);
                    mainPanel3.add(label5);
                    JTextField N3 = new JTextField(20);
                    mainPanel3.add(N3);
                    mainPanel3.add(new JLabel(""));
                    
                    mainPanel3.add(new JLabel(""));
                    mainPanel3.add(new JLabel(""));
                    mainPanel3.add(new JLabel(""));
                    mainPanel3.add(new JLabel(""));
                    mainPanel3.add(new JLabel(""));


                    }else if (liste.getValue()==2){
                    mainPanel2.removeAll();
                    mainPanel3.removeAll();
                    mainPanel4.removeAll();
                    getWindow().add(mainPanel2);
                    window.setSize(500, 200);
                        
                    mainPanel2.add(new JLabel(""));
                    mainPanel2.add(new JLabel(""));
                    mainPanel2.add(new JLabel(""));
                    mainPanel2.add(new JLabel(""));
                    mainPanel2.add(new JLabel(""));
                    
                    mainPanel2.add(new JLabel(""));
                    mainPanel2.add(new JLabel("1",SwingConstants.CENTER));
                    JLabel label1 = new JLabel("Pseudo",SwingConstants.CENTER);
                    mainPanel2.add(label1);
                    JTextField N1 = new JTextField(20);
                    mainPanel2.add(N1);
                    mainPanel2.add(new JLabel(""));
                    
                    mainPanel2.add(new JLabel(""));
                    mainPanel2.add(new JLabel(""));
                    mainPanel2.add(new JLabel(""));
                    mainPanel2.add(new JLabel(""));
                    mainPanel2.add(new JLabel(""));
                    
                    mainPanel2.add(new JLabel(""));
                    mainPanel2.add(new JLabel("2",SwingConstants.CENTER));
                    JLabel label3 = new JLabel("Pseudo",SwingConstants.CENTER);
                    mainPanel2.add(label3);
                    JTextField N2 = new JTextField(20);
                    mainPanel2.add(N2);
                    mainPanel2.add(new JLabel(""));
                    
                    mainPanel2.add(new JLabel(""));
                    mainPanel2.add(new JLabel(""));
                    mainPanel2.add(new JLabel(""));
                    mainPanel2.add(new JLabel(""));
                    mainPanel2.add(new JLabel(""));

                }
                    setChanged();
                    notifyObservers("changement de style");
                    clearChanged();
            }  
        });
                NJ.add(liste);
        JPanel bas = new JPanel();
        window.add(bas, BorderLayout.SOUTH);
        JButton Next = new JButton("Suivant");
        bas.add(Next);
        Next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setApres(true);
                    setChanged();
                    notifyObservers("on passe à la suite");
                    clearChanged();
                }  
        });
                bas.add(Next);
    }
    
        public void suivant() {
            if (apres==true){
                System.exit(0);
            }
        }

        public void afficher() {
        this.getWindow().setVisible(true);
    }
     /**
     * @return the window
     */ 
        public JFrame getWindow() {
        return window;
    }

    /**
     * @param window the window to set
     */
    public void setWindow(JFrame window) {
        this.window = window;
    }

    /**
     * @return the apres
     */
    public boolean isApres() {
        return apres;
    }

    /**
     * @param apres the apres to set
     */
    public void setApres(boolean apres) {
        this.apres = apres;
    }
}
