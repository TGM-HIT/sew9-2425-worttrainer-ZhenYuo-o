package view;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class WortTrainerGrafik extends JPanel{
    private int richtig;
    private int anzahl;
    JLabel imgLabel;
    private String url = "https://sdad.com";

    public void paint(String link) throws MalformedURLException {
        this.url = link;
        ImageIcon icon = new ImageIcon(new URL(link));
        Image image = icon.getImage();
        this.setBackground(Color.WHITE);
        image = image.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        imgLabel = new JLabel(new ImageIcon(image));
        this.repaint();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }
}
