package TEST;

import org.junit.jupiter.api.DisplayName;
import util.Case;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaseTest {

    @Test
    @DisplayName("Test du getteur state")
    void getState() {
        var c1 = new Case(0);
        assertEquals(0, c1.getState());
        var c2 = new Case(1);
        assertEquals(1, c2.getState());

    }
}