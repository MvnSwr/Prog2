package Java.Prog_2.U02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PaarTest {
    Paar<Integer, String> a = null; // vorne muss definiert werden, welche Art von Object eingegeben wird
    Paar<String, Integer> b = null;
    Paar<Integer, Integer> c = null;
    Paar<Integer, Integer> d = null;

    @Before
    public void build() {
        a = new Paar<>(7, "15");
        b = new Paar<>("7", 15);
        c = new Paar<>(7, 15);
        d = new Paar<>(17, 13);
    }

    @After
    public void teardown() {
        a = null;
        b = null;
        c = null;
        d = null;
    }

    @Test
    public void valueTest() {
        assertFalse(a.equals(b));
        assertFalse(a.equals(c));
        assertFalse(a.equals(d));
    }

    @Test
    public void firstTest() {
        int x = 7;
        c.setFirst(x);
        int y = c.getFirst();
        assertEquals(x, y);
    }

    @Test
    public void secondTest() {
        int x = 7;
        c.setSecond(x);
        int y = c.getSecond();
        assertEquals(x, y);
    }

    @Test
    public void bothTest() {
        int v = 7;
        int w = 12;
        c.setBoth(v, w);
        int x = c.getFirst();
        int y = c.getSecond();
        assertEquals(v, x);
        assertEquals(w, y);
    }

    @Test
    public void equalsTest1() {
        Paar<Integer, Integer> test = new Paar<>(7, 15);
        assertTrue(test.equals(c));
    }

    @Test
    public void equalsTest2() {
        Paar<Integer, String> test = new Paar<>(7, "15");
        assertTrue(test.equals(a));
    }

    @Test
    public void equalsTest3() {
        Paar<Integer, String> test = new Paar<>(8, "15");
        assertFalse(test.equals(a));
    }

    @Test
    public void stringTest() {
        assertEquals(c.toString(), "(7,15)");
    }
}