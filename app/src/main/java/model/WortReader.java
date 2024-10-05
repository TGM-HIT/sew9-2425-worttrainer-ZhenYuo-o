package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * WortReader
 *
 * @author ZhenYu Zhan
 * @version 05.10.2024
 */

public class WortReader {

    private final List<WortEintrag> WORTEINTRAEGE = new ArrayList<>();

    public WortReader(String path) {
        readQuestionsFile(path);
    }

    public void readQuestionsFile(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String wort = parts[0].trim();
                    String url = parts[1].trim();
                    WortEintrag eintrag = new WortEintrag(wort, url);
                    WORTEINTRAEGE.add(eintrag);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<WortEintrag> getWORTEINTRAEGE() {
        return WORTEINTRAEGE;
    }
}
