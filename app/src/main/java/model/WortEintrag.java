package model;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Objects;
/**
 * WortEintrag
 *
 * @author ZhenYu Zhan
 * @version 05.10.2024
 */
public class WortEintrag implements Serializable {
    static final long serialVersionUID = 1L;
    private String wort;
    private String url;

    public WortEintrag(String wort, String url) {
        this.wort = validateString(wort, "Wort");
        this.url = validateString(url, "URL");

        if (wort.trim().length() < 2) {
            throw new IllegalArgumentException("Das Wort muss mindestens zwei Zeichen lang sein und darf nicht nur aus Leerzeichen bestehen.");
        }

        if (!checkURL(url)) {
            throw new IllegalArgumentException("Die URL ist nicht gültig!");
        }
    }

    public static boolean checkURL(String url) {
        try {
            URI uri = new URI(url);
            return uri.getScheme() != null && (uri.getScheme().equals("http") || uri.getScheme().equals("https"));
        } catch (Exception e) {
            return false;
        }
    }

    public void setUrl(String url) {
        if (checkURL(url)) {
            this.url = url;
        } else {
            throw new IllegalArgumentException("Die URL ist nicht gültig!");
        }
    }
    public String getWort() {return this.wort;}
    public String getUrl() {return this.url;}

    private String validateString(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " darf nicht null sein");
        value = value.trim();
        return value;
    }

    @Override
    public String toString() {
        String text = "";
        return text + (this.wort + "\n" + this.url);
    }
}
