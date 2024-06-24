package Java.Prog_2.U04.A02;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DynArrayTest {
    DynArray<Integer> dynArray;
    int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };

    @Before
    public void build() {
        dynArray = new DynArray<>();
    }

    @After
    public void teardown() {
        dynArray = null;
    }

    @Test
    public void increaseDecrease() {
        // Methode ist bisschen witzlos geworden. Hab in der Klasse die beiden Methoden
        // anders implementiert
        dynArray.addLast(1);
        dynArray.addLast(2);
        assertSame(dynArray.capacity(), 6);
        for (Integer i : array) {
            dynArray.addLast(i);
        }
        // Es wird in fünferschritten erhöht
        assertSame(dynArray.capacity(), 21);
        for (int i = 0; i < 4; i++) {
            dynArray.removeFirst();
        }
        // Neue size ist die aktuelle size + 2
        assertSame(dynArray.capacity(), 17);
    }

    @Test
    public void remove() {
        assertThrows(NullPointerException.class, () -> {
            dynArray.removeFirst();
        });
        assertThrows(NullPointerException.class, () -> {
            dynArray.removeLast();
        });
        for (Integer i : array) {
            dynArray.addLast(i);
        }
        assertSame(dynArray.removeLast(), 16);
        assertSame(dynArray.removeFirst(), 1);
    }

    @Test
    public void remove2() {
        dynArray.addFirst(1);
        dynArray.removeFirst();
        assertThrows(NullPointerException.class, () -> {
            dynArray.removeFirst();
        });
        assertThrows(NullPointerException.class, () -> {
            dynArray.removeLast();
        });
    }

    @Test
    public void pos() {
        for (Integer i : array) {
            dynArray.addLast(i);
        }
        assertSame(dynArray.get(4), 5);
    }

    @Test
    public void pos2() {
        for (Integer i : array) {
            dynArray.addLast(i);
        }
        dynArray.set(3, 17);
        assertSame(dynArray.get(3), 17);
        assertSame(dynArray.get(2), 3);
        assertSame(dynArray.get(4), 5);
    }

    @Test
    public void addFirst() {
        for (Integer i : array) {
            dynArray.addLast(i);
        }
        dynArray.addFirst(24);
        assertSame(dynArray.removeFirst(), 24);
        assertSame(dynArray.removeFirst(), 1);
    }

    @Test
    public void iterieren() {
        int n = 0;
        for (Integer i : array) {
            dynArray.addLast(i);
        }
        for (Integer i : dynArray) {
            assertSame(array[n++], i);
        }
    }
}
