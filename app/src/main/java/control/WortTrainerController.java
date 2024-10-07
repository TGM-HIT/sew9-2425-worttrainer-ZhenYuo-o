package control;

import model.SpeichernLaden;
import model.WortEintrag;
import model.WortReader;
import model.WortTrainer;
import view.WortTrainerFrame;
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
 * @version 07.10.2024
 */
public class WortTrainerController extends KeyAdapter implements ActionListener {
    private static final String WORT_DATEI = "app/src/main/java/resources/woerter.csv";
    private WortReader wortReader = new WortReader(WORT_DATEI);
    private ArrayList<WortEintrag> liste  = wortReader.getWortEintraege();
    private WortTrainer model = new WortTrainer(this.liste);;
    private SpeichernLaden file;
    private WortTrainerPanel panel = new WortTrainerPanel(model.getAktuelleURL(),this, this);
    private WortTrainerFrame frame = new WortTrainerFrame("WortTainer", panel);
    private final String FILEPATH = "WortTrainer.txt";



    public WortTrainerController() throws MalformedURLException {
    }

    public void initialisierung() throws MalformedURLException, IOException {
        this.file = new SpeichernLaden(this.model);
        if (Files.exists(Paths.get(FILEPATH))) {
            System.out.println("Speicherdatei gefunden, lade Daten...");
            load();
            this.panel.update(this.model.getRichtig(), this.model.getAbgefragt());
        }
        else {
        System.out.println("Keine Speicherdatei gefunden, starte von null...");
        }

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