package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Die Panel-Klasse im View von MVC.
 * Diese bringt das Aussehen des Programms
 * @author ZhenYu Zhan
 * @version 05.10.2024
 */
public class WortTrainerPanel extends JPanel {
    private JTextField textField = new JTextField();
    private WortTrainerGrafik grafik;
    private int richtig, anzahl;
    private JLabel text, richtigWort, anzahlWort, richtigZahl, anzahlZahl, imgLabel;
    private JButton reset, speichern;
    private String url;
    private final Color color = new Color(235, 235, 235);

    public WortTrainerPanel(ActionListener ah, KeyListener kh) throws MalformedURLException {
        grafik = new WortTrainerGrafik();
        this.setLayout(new BorderLayout(3, 3));
        this.setBackground(color);
        Border bd = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        this.setBorder(bd);
        JPanel top = new JPanel((new GridLayout(2, 1, 3, 3)));
        this.text = new JLabel("Welches Wort wird unten dargestellt (Eingabe zum Überprüfen)?");
        top.add(this.text);
        top.add(this.textField);
        top.setBackground(color);
        this.add(top, BorderLayout.PAGE_START);
        JPanel rechts = new JPanel(new BorderLayout(20, 3));
        this.add(rechts, BorderLayout.LINE_START);
        JPanel links = new JPanel(new BorderLayout(20, 3));
        this.add(links, BorderLayout.LINE_END);
        JPanel unten = new JPanel(new GridLayout(2, 3, 15, 15));
        this.reset = new JButton("Zurücksetzen");
        this.speichern = new JButton("Speichern");
        this.richtigWort = new JLabel("Richtige Wörter:");
        this.richtigZahl = new JLabel("0");
        this.anzahlWort = new JLabel("Anzahl Wörter:");
        this.anzahlZahl = new JLabel("0");
        unten.add(richtigWort);
        unten.add(richtigZahl);
        unten.add(reset);
        unten.add(anzahlWort);
        unten.add(anzahlZahl);
        unten.add(speichern);
        this.add(unten, BorderLayout.PAGE_END);
        ImageIcon icon = new ImageIcon(new URL("https://www.radioessen.de/externalimages/?source=jpg140/www.vieler-photography.com1.jpg&dt=201908261605150&resize=1000x750"));
        Image image = icon.getImage();
        image = image.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        this.imgLabel = new JLabel(new ImageIcon(image));
        this.add(this.imgLabel, BorderLayout.CENTER);
        this.reset.addActionListener(ah);
        this.speichern.addActionListener(ah);
        this.textField.addKeyListener(kh);
    }

    public void imageUpdate(String link) throws Exception {
        URI uri = new URI(link);
        URL url = uri.toURL();

        ImageIcon icon = new ImageIcon(url);
        Image image = icon.getImage();
        image = image.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        this.imgLabel.setIcon(new ImageIcon(image));
        this.imgLabel.updateUI();
    }

    public void update(int correct, int number) {
        this.richtig = correct;
        this.anzahl = number;
        this.richtigZahl.setText(String.valueOf(this.richtig));
        this.anzahlZahl.setText(String.valueOf(this.anzahl));
        this.richtigZahl.updateUI();
        this.anzahlZahl.updateUI();
    }

    public void reset() {
        this.richtig = 0;
        this.anzahl = 0;
        this.richtigZahl.setText(String.valueOf(this.richtig));
        this.anzahlZahl.setText(String.valueOf(this.anzahl));
        this.richtigZahl.updateUI();
        this.anzahlZahl.updateUI();
    }

    public String getTextField() {
        return this.textField.getText();
    }

    public void leereTextField() {
        this.textField.setText("");
        this.textField.updateUI();
    }

    public void setButtonsEnabed(boolean status) {
        this.reset.setEnabled(status);
        this.textField.setEnabled(status);
    }

}
