package Java.Prog_2.U01.A02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Java.Prog_2.U00.StapelMitArray;


public class StapelMitArrayTest {
    StapelMitArray stp = null;
    int[] row = { 1, 2, 3, 4, 5 };

    @Before
    public void setup() {
        stp = new StapelMitArray(5);
    }

    @After
    public void teardown() {
        stp = null;
    }

    @Test

    public void testPop() {
        for (int i = 0; i < 5; i++) {
            stp.insert(i + 1);
            assertEquals("Pop war falsch", i + 1, stp.top());
        }
    }

    @Test
    public void emptyRemove() {
        assertThrows(NoSuchElementException.class, () -> { // Lambdaklasse, erst die Exception.class, danach
            stp.remove(); // der Befehl der ausgeführt werden soll
        });
    }

    @Test
    public void fullStack() {
        assertThrows(IllegalStateException.class, () -> { // erneut eine Lambdaklasse
            for (int i = 0; i < 10; i++) {
                stp.insert(i);
            }
        });
    }
}