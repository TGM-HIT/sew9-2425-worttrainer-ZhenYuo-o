package model;

import java.io.*;
import java.util.ArrayList;

/**
 * Speichern und Laden für die WortTrainer Objekte
 * @author ZhenYu Zhan
 * @version 05.10.2024
 */
public class SpeichernLaden {
    private WortTrainer trainer;

    public SpeichernLaden(WortTrainer trainer) {
        this.trainer = trainer;
    }

    public void speichern(String filename) throws IOException {
        File file = new File(filename);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(trainer.toString());
        }
    }

    public void speichern() throws IOException {
        speichern("WortTrainer.txt");
    }

    public WortTrainer laden(String filename) throws IOException {

        int abgefragt = 0;
        int richtig = 0;
        int counter = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                switch (counter) {
                    case 0 -> abgefragt = Integer.parseInt(line);
                    case 1 -> richtig = Integer.parseInt(line);
                    default -> {
                    }
                }
                counter++;
            }
            this.trainer.setStatistik(richtig, abgefragt);
        }

        return this.trainer;
    }


    public WortTrainer laden() throws IOException {
        return laden("WortTrainer.txt");
    }

    public void speichernSerial() throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Liste.obj"))) {
            outputStream.writeObject(trainer);
        }
    }

    public WortTrainer ladenSerial() throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("Liste.obj"))) {
            return (WortTrainer) inputStream.readObject();
        }
    }
}
