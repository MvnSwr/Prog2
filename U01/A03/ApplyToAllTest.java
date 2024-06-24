package Java.Prog_2.U01.A03;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Java.Prog_2.U00.StapelMitArray;


public class ApplyToAllTest {
    StapelMitArray stp;
    int[] row = { 1, 2, 3, 4, 5 };

    @Before
    public void build() {
        stp = new StapelMitArray(6);
    }

    @After
    public void teardown() {
        stp = null;
    }

    @Test
    public void verdoppelnTest() {
        for (int i = 0; i < stp.size(); i++) {
            stp.insert(i); // stp Array wird gefüllt von 0 - 5
        }
        stp.applyToAll((i) -> 2 * i); // auf alle Werte werden applyToAll angewendet mit der Lambdaklasse 2*Wert der
                                      // drinnen ist
        for (int pointer = stp.size() - 1; pointer >= 0; pointer--) {
            assertEquals(2 * row[pointer], stp.remove()); // Die Ergebnisse werden von hinten nach vorne geprüft, da ein
                                                          // Stack immer FILO
        }
    }
}