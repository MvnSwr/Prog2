package Java.Prog_2.U05.A03;

import static org.junit.Assert.assertSame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FolgeMitDynarrTest {
    FolgeMitDynarr<Integer> dynArray;
    int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };

    @Before
    public void build() {
        dynArray = new FolgeMitDynarr<>();
        for (Integer i : array) {
            dynArray.insert(i);
        }
    }

    @After
    public void teardown() {
        dynArray = null;
    }

    @Test
    public void getpos() {
        assertSame(dynArray.get(4), 5);
        assertSame(dynArray.get(0), 1);
        assertSame(dynArray.get(6), 7);
    }

    @Test
    public void setpos() { // wird überschrieben, kein verschieben der Daten
        assertSame(dynArray.get(4), 5);
        dynArray.set(4, 20);
        assertSame(dynArray.get(5), 6);
        assertSame(dynArray.get(4), 20);
    }

    @Test
    public void removepos() {
        assertSame(dynArray.remove(0), 1);
        assertSame(dynArray.get(0), 2);
        assertSame(dynArray.get(2), 4);
        assertSame(dynArray.get(14), 16);
    }

    @Test
    public void insertpos() {
        assertSame(dynArray.get(4), 5);
        dynArray.insert(4, 20);
        assertSame(dynArray.get(5), 5);
        assertSame(dynArray.get(4), 20);
    }
}
