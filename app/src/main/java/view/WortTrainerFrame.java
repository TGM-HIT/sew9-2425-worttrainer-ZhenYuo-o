package view;

import javax.swing.*;

/**
 * Frame-Klasse in View für MVC.
 * Erstellt den Frame des Programms
 * @author ZhenYu Zhan
 * @version 13.11.2022
 */
public class WortTrainerFrame extends JFrame{
    public WortTrainerFrame(String name, JPanel panel) {
        super(name);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
