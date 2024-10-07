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

    public WortTrainer(ArrayList<WortEintrag> liste) {
        this.liste = Objects.requireNonNull(liste, "Die WortListe darf nicht null sein");
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
        String wort2 = (this.speicherEintrag == null) ? "Hund" : this.speicherEintrag.getWort();
        if (wort2.equalsIgnoreCase(wort)) {
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
