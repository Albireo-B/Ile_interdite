/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import ileInterdite.message.Message;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import utilitaires.Role;

/**
 *
 * @author genevoic
 */
public class VueInit extends Observable{
     private JFrame window ;
     private boolean apres;
     private JTextField nom1=new JTextField(10);
     private JTextField nom2=new JTextField(10);
     private JTextField nom3=new JTextField(10);
     private JTextField nom4=new JTextField(10);
     private JLabel label0;
     private JLabel label1;
     private JLabel label2;
     private JLabel label3;
     
     private JSlider liste = new JSlider(JSlider.HORIZONTAL,2,4,3);
    /**
     */
    public VueInit () {
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
                    window.add(mainPanel4);
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
                   
                    mainPanel4.add(nom1);
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
                    
                    mainPanel4.add(nom2);
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
                    
                    mainPanel4.add(nom3);
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
                    
                    mainPanel4.add(nom4);
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
                    window.add(mainPanel3);
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
                    window.add(mainPanel2);
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
                    notifyObservers(null);
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
                    ArrayList<String> noms=new ArrayList<>();
                if (!(nom1.getText().equals(""))){
                    noms.add(nom1.getText());
                }else{
                    noms.add("joueur1");
                }
                if (!(nom2.getText().equals(""))){
                    noms.add(nom2.getText());
                }else{
                    noms.add("joueur2");
                }
                if (!(nom3.getText().equals("")) && liste.getValue()>2){
                    noms.add(nom3.getText());
                }else if (liste.getValue()>2){
                    noms.add("joueur3");
                }
                if (!(nom4.getText().equals("")) && liste.getValue()>3){
                    noms.add(nom4.getText());
                }else if (liste.getValue()>3){
                    noms.add("joueur4");
                }
                    notifyObservers(noms);
                    clearChanged();
                }  
        });
                bas.add(Next);
    }

        public void afficher() {
        window.setVisible(true);
    }
        public void close() {
        window.setVisible(false);
    }
        
    //Getters et Setters :

  
    /**
     * @param apres the apres to set
     */
    public void setApres(boolean apres) {
        this.apres = apres;
    }
}
