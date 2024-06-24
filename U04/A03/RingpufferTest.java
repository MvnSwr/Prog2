package Java.Prog_2.U04.A03;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RingpufferTest {
    Ringpuffer<Integer> rp;
    int[] intArr = { 1, 2, 3, 4 };

    @Before
    public void build() {
        rp = new Ringpuffer<>(4);
    }

    @After
    public void teardown() {
        rp = null;
    }

    @Test
    public void getException() {
        assertThrows(NullPointerException.class, () -> {
            rp.get(0);
        });
    }

    @Test
    public void size() {
        assertSame(rp.size(), 0);
        rp.addFirst(4);
        assertSame(rp.size(), 1);
        rp.removeFirst();
        assertSame(rp.size(), 0);
    }

    @Test
    public void removeExceptions() {
        assertThrows(NullPointerException.class, () -> {
            rp.removeFirst();
        });
        assertThrows(NullPointerException.class, () -> {
            rp.removeLast();
        });
        rp.addFirst(1);
        rp.removeFirst();
        assertThrows(NullPointerException.class, () -> {
            rp.removeFirst();
        });
        assertThrows(NullPointerException.class, () -> {
            rp.removeLast();
        });
    }

    @Test
    public void inserts() {
        for (Integer i : intArr) {
            rp.addLast(i);
        }
        assertSame(rp.removeFirst(), 1);
        assertSame(rp.removeLast(), 4);
        assertSame(rp.removeFirst(), 2);
        assertSame(rp.removeFirst(), 3);
        // noch Exceptions
        assertThrows(NullPointerException.class, () -> {
            rp.removeFirst();
        });
        assertThrows(NullPointerException.class, () -> {
            rp.removeLast();
        });
        rp.addLast(7);
        assertSame(rp.removeLast(), 7);
    }
}