package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * WortListe
 *
 * @author ZhenYu Zhan
 * @version 05.10.2024
 */

public class WortListe {
    static final long serialVersionUID = 1L;
    List<WortEintrag> wortListe = new ArrayList<WortEintrag>();

    public WortListe(List<WortEintrag> wortListe) {
        this.wortListe = Objects.requireNonNull(wortListe, "Das Wort darf nicht null sein");
    }
    public void addWort(WortEintrag wort) {
        this.wortListe.add(wort);
    }
    public void addWort(String wort, String url) {
        WortEintrag eintrag = new WortEintrag(wort, url);
        this.addWort(eintrag);
    }
    public WortEintrag wortAuswahl(int index) {
        if (index < 0 || index >= this.wortListe.size()) {
            throw new IllegalArgumentException("Ungültiger Index. Maximaler Index ist " + (this.wortListe.size() - 1));
        }

        WortEintrag eintrag = this.wortListe.get(index);
        if (eintrag == null) {
            throw new IllegalArgumentException("Der Eintrag an diesem Index ist null.");
        }

        return eintrag;
    }
    public boolean deleteWort(String wort) {
        return this.wortListe.removeIf(eintrag -> eintrag.getWort().equals(wort));
    }

}
