import model.WortEintrag;
import model.WortTrainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WortTrainerTest {

    private WortTrainer trainer;

    @BeforeEach
    void setUp() {
        ArrayList<WortEintrag> liste = new ArrayList<>();
        liste.add(new WortEintrag("Hund", "https://example.com"));
        liste.add(new WortEintrag("Katze", "https://example.com"));
        trainer = new WortTrainer(liste);
    }

    @Test
    void testZufall() {
        trainer.zufall();
        assertNotNull(trainer.aktuellerEintrag());
    }

    @Test
    void testCheckIgnoreCaseCorrect() {
        trainer.zufall();
        WortEintrag entry = trainer.aktuellerEintrag();
        assertTrue(trainer.checkIgnoreCase(entry.getWort()));
        assertEquals(1, trainer.getRichtig());
    }

    @Test
    void testCheckIgnoreCaseIncorrect() {
        trainer.zufall();
        assertFalse(trainer.checkIgnoreCase("FalschesWort"));
        assertEquals(0, trainer.getRichtig());
    }

    @Test
    void testStatistikReset() {
        trainer.zufall();
        trainer.checkIgnoreCase(trainer.aktuellerEintrag().getWort());
        trainer.resetStatistik();
        assertEquals(0, trainer.getRichtig());
        assertEquals(0, trainer.getAbgefragt());
    }
}
