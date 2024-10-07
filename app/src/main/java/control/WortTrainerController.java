package control;

import model.SpeichernLaden;
import model.WortEintrag;
import model.WortTrainer;
import view.WortTrainerPanel;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


/**
 * Controller
 *
 * @author ZhenYu Zhan
 * @version 05.10.2024
 */
public class WortTrainerController extends KeyAdapter implements ActionListener {
    private ArrayList<WortEintrag> liste = new ArrayList<>();
    private WortTrainer model;
    private SpeichernLaden file;
    private WortTrainerPanel panel;
    private final String filePath = "WortTrainer.txt";

    public WortTrainerController() throws MalformedURLException {
    }

    public void initialisierung() throws MalformedURLException, IOException {
        this.model = new WortTrainer(this.liste);
        this.file = new SpeichernLaden(this.model);
        if (Files.exists(Paths.get(filePath))) {
            System.out.println("Speicherdatei gefunden, lade Daten...");
            load();
            this.panel.update(this.model.getRichtig(), this.model.getAbgefragt());
        }
        else {
        System.out.println("Keine Speicherdatei gefunden, starte von null...");
        }
        this.panel = new WortTrainerPanel(this, this, this.model.getAktuelleURL());
        this.model.initialization();

        this.panel.setButtonsEnabed(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        String s = b.getText();
        switch (s) {
            case "Zurücksetzen" -> {
                this.panel.reset();
                this.model.resetStatistik();
            }
            case "Speichern" -> {
                this.save();
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int t = e.getKeyCode();
        if (t == KeyEvent.VK_ENTER) {
            String textField = this.panel.getTextField();
            if (textField != null) {
                if (this.model.checkIgnoreCase(textField)) {
                    this.panel.setAntwort("Richtig!");
                    this.panel.update(this.model.getRichtig(), this.model.getAbgefragt());
                    this.panel.leereTextField();
                    this.model.zufall();
                    this.panel.update(this.model.getRichtig(), this.model.getAbgefragt());
                    try {
                        this.panel.imageUpdate(this.model.getAktuelleURL());
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else {
                    this.panel.setAntwort("Falsch! Versuche es nochmal");
                    this.model.setStatistik(this.model.getRichtig(), this.model.getAbgefragt()+1);
                    this.panel.update(this.model.getRichtig(), this.model.getAbgefragt());
                }
            }
        }
    }
    public void save() {
        try {
            this.file = new SpeichernLaden(this.model);
            this.file.speichern("WortTrainer.txt");
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }

    public void load() throws IOException {
        this.model = this.file.laden("WortTrainer.txt") ;
    }

    public static void main(String[] args) throws IOException {
        WortTrainerController controller = new WortTrainerController();
        controller.initialisierung();
    }
}