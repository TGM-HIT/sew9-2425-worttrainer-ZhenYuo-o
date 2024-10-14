import model.WortEintrag;
import model.WortReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

class WortReaderTest {

    @Test
    void testReadQuestionsFile() throws FileNotFoundException {
        WortReader reader = new WortReader("src/test/resources/woerter_test.csv");
        ArrayList<WortEintrag> entries = reader.getWortEintraege();
        assertEquals(2, entries.size());
        assertEquals("Hund", entries.get(0).getWort());
        assertEquals("https://example.com", entries.get(0).getUrl());
    }

    @Test
    void testInvalidFile() throws FileNotFoundException {
        Exception exception = assertThrows(FileNotFoundException.class, () -> {
            WortReader reader = new WortReader("invalid/path/to/file.csv");
            reader.getWortEintraege();
        });
    }
}
