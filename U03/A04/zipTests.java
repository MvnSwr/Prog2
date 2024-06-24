package Java.Prog_2.U03.A04;

// import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Java.Prog_2.U03.A01.EVL;

public class zipTests {
    int[] one = { 1, 3, 5, 7 };
    int[] two = { 2, 4, 6, 8, 9, 10, 11 };
    EVL<Integer> first;
    EVL<Integer> second;

    @Before
    public void build() {
        first = new EVL<>();
        second = new EVL<>();
        for (int i : one) {
            first.addLast(i);
        }
        for (int i : two) {
            second.addLast(i);
        }
    }

    @After
    public void teardown() {
        first = null;
        second = null;
    }

    @Test
    public void zip() {
        first.zip(second);
        String out = first.toString();
        assertTrue(out.equals("1-2-3-4-5-6-7-8-9-10-11"));
    }
}