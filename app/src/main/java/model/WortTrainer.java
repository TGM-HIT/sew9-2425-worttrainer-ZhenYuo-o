package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Objects;

/**
 * Trainer
 *
 * @author ZhenYu Zhan
 * @version 05.10.2024
 */
public class WortTrainer implements Serializable {
    static final long serialVersionUID = 1L;
    private ArrayList<WortEintrag> liste;
    private Random zufall = new Random();
    private WortEintrag speicherEintrag;
    private int abgefragt;
    private int richtig;
    private static final String WORT_DATEI = "app/src/main/java/resources/woerter.csv";

    public void initialization() {
        WortReader wortReader = new WortReader(WORT_DATEI);
        this.liste = wortReader.getWortEintraege();

    }

    public WortTrainer(ArrayList<WortEintrag> liste) {
        this.liste = Objects.requireNonNull(liste, "Die WortListe darf nicht null sein");
    }

    public WortTrainer(ArrayList<WortEintrag> liste, int abgefragt, int richtig) {
        this(liste);
        this.abgefragt = abgefragt;
        this.richtig = richtig;
    }

    public void zufall() {
        int zahl = zufall.nextInt(this.liste.size());
        this.speicherEintrag = this.liste.get(zahl);
        this.abgefragt++;
    }

    public WortEintrag aktuellerEintrag() {
        return this.speicherEintrag;
    }

    public boolean checkIgnoreCase(String wort) {
        if (this.speicherEintrag.getWort().equalsIgnoreCase(wort)) {
            this.richtig++;
            return true;
        }
        return false;
    }

    public int getRichtig() {
        return this.richtig;
    }

    public int getAbgefragt() {
        return this.abgefragt;
    }

    public void resetStatistik() {
        this.richtig = 0;
        this.abgefragt = 0;
    }

    public void setStatistik(int richtig, int abgefragt) {
        this.richtig = richtig;
        this.abgefragt = abgefragt;
    }

    public String getAktuellesWort() {
        return this.speicherEintrag.getWort();
    }

    public String getAktuelleURL() {
        return this.speicherEintrag.getUrl();
    }

    @Override
    public String toString() {
        return this.abgefragt + "\n" + this.richtig;
    }
}
