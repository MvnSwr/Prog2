package Java.Prog_2.U03.A05;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("unused")

public class RDVLTest {
    RDVL<Integer> Liste = null;
    int[] arr = { 1, 2, 3, 4, 5, 6 };

    @Before
    public void build() {
        Liste = new RDVL<>();
        for (Integer i : arr) {
            Liste.add(i);
        }
    }

    @After
    public void teardown() {
        Liste = null;
    }

    @Test
    public void insert() {
        String str = Liste.toString();
        assertTrue(str.equals("1-2-3-4-5-6"));
    }

    @Test
    public void remove() {
        for (Integer i : arr) {
            Liste.remove();
        }
        assertSame(Liste.size, 0);
    }
}