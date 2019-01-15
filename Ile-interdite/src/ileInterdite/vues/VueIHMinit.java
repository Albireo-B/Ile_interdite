/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileInterdite.vues;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author genevoic
 */
public class VueIHMinit {

    /**
     * @param args the command line arguments
     */
    public static void VueIHMinit(String[] args) {
        JFrame window = new JFrame();
        window.setTitle("Selectionner vos joueurs");
        JPanel NJ = new JPanel();
        window.add(NJ, BorderLayout.NORTH);
        JLabel label0 = new JLabel("Nombre de joueurs");
        NJ.add(label0);
        String[] items = {"2", "3", "4"};
        JComboBox liste = new JComboBox(items);
        NJ.add(liste);
        JPanel mainPanel = new JPanel(new GridLayout(4, 5));

        JLabel label1 = new JLabel("Nom");
        mainPanel.add(label1);
        JTextField N1 = new JTextField(20);
        mainPanel.add(N1);
        JLabel label2 = new JLabel("Prénom");
        mainPanel.add(label2);
        JTextField P1 = new JTextField(20);
        mainPanel.add(P1);

        JLabel label3 = new JLabel("Nom");
        mainPanel.add(label3);
        JTextField N2 = new JTextField(20);
        mainPanel.add(N2);
        JLabel label4 = new JLabel("Prénom");
        mainPanel.add(label4);
        JTextField P2 = new JTextField(20);
        mainPanel.add(P2);

        JLabel label5 = new JLabel("Nom");
        mainPanel.add(label5);
        JTextField N3 = new JTextField(20);
        mainPanel.add(N3);
        JLabel label6 = new JLabel("Prénom");
        mainPanel.add(label6);
        JTextField P3 = new JTextField(20);
        mainPanel.add(P3);

        JLabel label7 = new JLabel("Nom");
        mainPanel.add(label7);
        JTextField N4 = new JTextField(20);
        mainPanel.add(N4);
        JLabel label8 = new JLabel("Prénom");
        mainPanel.add(label8);
        JTextField P4 = new JTextField(20);
        mainPanel.add(P4);

        window.add(mainPanel);
        window.setSize(900, 700);
        window.setVisible(true);
    }

}
