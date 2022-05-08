/*
 * Kyra Samuel
 * Project 2
 * CMSC405
 * Main.java
 */
package project2.ks;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("JOGL");
        JoglStarter panel = new JoglStarter();
        window.setContentPane(panel);
        /* TODO: If you want to have a menu, comment out the following line. */
        //window.setJMenuBar(panel.createMenuBar());
        window.pack();
        window.setLocation(50,50);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}

