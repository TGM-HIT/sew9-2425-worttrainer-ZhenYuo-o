package control;

import model.SpeichernLaden;
import model.WortEintrag;
import model.WortReader;
import model.WortTrainer;
import view.WortTrainerFrame;
import view.WortTrainerPanel;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
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
    private static final String WORT_DATEI = "app/src/main/resources/woerter.csv";
    private WortReader wortReader = new WortReader(WORT_DATEI);
    private ArrayList<WortEintrag> liste = wortReader.getWortEintraege();
    private WortTrainer model = new WortTrainer(this.liste);;
    private SpeichernLaden file;
    private WortTrainerPanel panel;
    private final String filePath = "app/src/main/resources/WortTrainer.txt";

    public WortTrainerController() throws FileNotFoundException {
    }

    public void initialisierung() throws IOException, URISyntaxException {
        this.model.zufall();
        this.panel = new WortTrainerPanel(this, this, this.model.getAktuelleURL());
        WortTrainerFrame frame = new WortTrainerFrame("WortTainer", panel);
        this.file = new SpeichernLaden(this.model);
        if (Files.exists(Paths.get(filePath))) {
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
                save();
            }
        }
    }
    public void save() {
        try {
            this.file = new SpeichernLaden(this.model);
            this.file.speichern(this.filePath);
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }

    public void load() throws IOException {
        this.model = this.file.laden(this.filePath) ;
    }

    public static void main(String[] args) throws Exception {
        WortTrainerController controller = new WortTrainerController();
        controller.initialisierung();
    }
}