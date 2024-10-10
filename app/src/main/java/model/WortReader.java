package model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * WortReader
 *
 * @author ZhenYu Zhan
 * @version 05.10.2024
 */
public class WortReader {

    private ArrayList<WortEintrag> wortEintraege = new ArrayList<>();

    public WortReader(String path) throws FileNotFoundException {
        readQuestionsFile(path);
    }

    public void readQuestionsFile(String path) {
        try (FileReader reader = new FileReader(path);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.Builder.create()
                     .build())) {

            for (CSVRecord record : csvParser) {
                String wort = record.get(0).trim();
                String url = record.get(1).trim();
                WortEintrag eintrag = new WortEintrag(wort, url);
                wortEintraege.add(eintrag);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<WortEintrag> getWortEintraege() {
        return wortEintraege;
    }
}
