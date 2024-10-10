import model.WortEintrag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WortEintragTest {

    @Test
    void testValidWortEintrag() {
        WortEintrag entry = new WortEintrag("Hund", "https://example.com");
        assertEquals("Hund", entry.getWort());
        assertEquals("https://example.com", entry.getUrl());
    }

    @Test
    void testInvalidUrl() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new WortEintrag("Hund", "invalid-url");
        });
        assertEquals("Die URL ist nicht gültig!", exception.getMessage());
    }

    @Test
    void testInvalidWort() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new WortEintrag(" ", "https://example.com");
        });
        assertEquals("Das Wort muss mindestens zwei Zeichen lang sein und darf nicht nur aus Leerzeichen bestehen.", exception.getMessage());
    }
}
