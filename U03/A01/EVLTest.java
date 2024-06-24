package Java.Prog_2.U03.A01;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EVLTest {
    EVL<String> Stringlist;
    EVL<Integer> Intlist;
    String[] Stringarr = { "Hamburg", "Berlin", "München", "Bremen", "Duisburg", "Frankfurt", "Nürnberg" };
    int[] Intarr = { 1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1 };

    @Before
    public void build() {
        Stringlist = new EVL<>();
        Intlist = new EVL<>();
    }

    @After
    public void teardown() {
        Stringlist = null;
        Intlist = null;
    }

    @Test
    public void Remove() {
        Stringlist.addLast("Hallo");
        Stringlist.removeLast();
        assertEquals(Stringlist.first, null);
    }

    @Test
    public void size() {
        for (String s : Stringarr) {
            Stringlist.addLast(s);
        }
        for (int i : Intarr) {
            Intlist.addLast(i);
        }
        assertEquals(Stringlist.size, 7);
        assertEquals(Intlist.size, 11);
    }

    @Test
    public void sizeAfterRemove() {
        for (String s : Stringarr) {
            Stringlist.addLast(s);
        }
        for (int i : Intarr) {
            Intlist.addLast(i);
        }
        Stringlist.removeLast();
        Intlist.removeLast();
        assertEquals(Stringlist.size, 6);
        assertEquals(Intlist.size, 10);
    }

    @Test
    public void emptyList() {
        assertEquals(Stringlist.size, 0);
        assertEquals(Intlist.size, 0);
        assertEquals(Stringlist.first, null);
        assertEquals(Intlist.first, null);
    }

    @Test
    public void emptyListAfterRemove() {
        Stringlist.addLast("Hallo");
        Stringlist.removeLast();
        Intlist.addLast(1);
        Intlist.removeLast();
        assertSame(Stringlist.size, 0);
        assertSame(Intlist.size, 0);
    }

    @Test
    public void firstElement() {
        for (String s : Stringarr) {
            Stringlist.addLast(s);
        }
        for (int i : Intarr) {
            Intlist.addLast(i);
        }
        assertEquals(Stringlist.getFirst(), "Hamburg");
        assertSame(Intlist.getFirst(), 1);
    }

    @Test
    public void lastElement() {
        for (String s : Stringarr) {
            Stringlist.addLast(s);
        }
        for (int i : Intarr) {
            Intlist.addLast(i);
        }
        assertEquals(Stringlist.getLast(), "Nürnberg");
        assertSame(Intlist.getLast(), 1);
    }

    @Test
    public void lastElementAfterRemove() {
        for (String s : Stringarr) {
            Stringlist.addLast(s);
        }
        for (int i : Intarr) {
            Intlist.addLast(i);
        }
        Stringlist.removeLast();
        Intlist.removeLast();
        assertEquals(Stringlist.getLast(), "Frankfurt");
        assertSame(Intlist.getLast(), 2);
    }

    @Test
    public void contains() {
        for (String s : Stringarr) {
            Stringlist.addLast(s);
        }
        for (int i : Intarr) {
            Intlist.addLast(i);
        }
        assertTrue(Stringlist.contains("Frankfurt"));
        assertFalse(Stringlist.contains("Geldern"));
    }

    @Test
    public void exceptionhandling1() {
        assertThrows(NullPointerException.class, () -> {
            Stringlist.contains("Test");
        });
        assertThrows(NullPointerException.class, () -> {
            Intlist.contains(1);
        });
    }

    @Test
    public void exceptionhandling2() {
        assertThrows(NullPointerException.class, () -> {
            Stringlist.removeLast();
        });
        assertThrows(NullPointerException.class, () -> {
            Intlist.removeLast();
        });
    }

    @Test
    public void exceptionhandling3() {
        assertThrows(NullPointerException.class, () -> {
            Stringlist.getFirst();
        });
        assertThrows(NullPointerException.class, () -> {
            Intlist.getFirst();
        });
    }

    @Test
    public void exceptionhandling4() {
        assertThrows(NullPointerException.class, () -> {
            Stringlist.getLast();
        });
        assertThrows(NullPointerException.class, () -> {
            Intlist.getLast();
        });
    }

    @Test
    public void exceptionhandling5() {
        Stringlist.addLast("Test");
        Stringlist.removeLast();
        assertThrows(NullPointerException.class, () -> {
            Stringlist.contains("Test");
        });
        Intlist.addLast(1);
        Intlist.removeLast();
        assertThrows(NullPointerException.class, () -> {
            Intlist.contains(1);
        });
    }

    @Test
    public void exceptionhandling6() {
        Stringlist.addLast("Test");
        Stringlist.removeLast();
        assertThrows(NullPointerException.class, () -> {
            Stringlist.removeLast();
        });
        Intlist.addLast(1);
        Intlist.removeLast();
        assertThrows(NullPointerException.class, () -> {
            Intlist.removeLast();
        });
    }

    @Test
    public void exceptionhandling7() {
        Stringlist.addLast("Test");
        Stringlist.removeLast();
        assertThrows(NullPointerException.class, () -> {
            Stringlist.getFirst();
        });
        Intlist.addLast(1);
        Intlist.removeLast();
        assertThrows(NullPointerException.class, () -> {
            Intlist.getFirst();
        });
    }

    @Test
    public void exceptionhandling8() {
        Stringlist.addLast("Test");
        Stringlist.removeLast();
        assertThrows(NullPointerException.class, () -> {
            Stringlist.getLast();
        });
        Intlist.addLast(1);
        Intlist.removeLast();
        assertThrows(NullPointerException.class, () -> {
            Intlist.getLast();
        });
    }

    @Test
    public void zipTest() {
        EVL<Integer> testen = new EVL<>();
        int[] zipen = { 1, 2, 3, 4, 5 };
        int[] pruefen = { 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 5, 4, 3, 2, 1 };
        for (Integer i : Intarr) {
            Intlist.addLast(i);
        }
        for (Integer i : zipen) {
            testen.addLast(i);
        }
        Intlist.zip(testen);
        for (int i = 0; i < 16; i++) {
            assertSame(Intlist.removeFirst(), pruefen[i]);
        }
    }

    @Test
    public void iterieren() {
        EVL<Integer> evl = new EVL<>();
        int[] arr = { 1, 2, 3, 4, 5 };
        int n = 0;
        for (Integer i : arr) {
            evl.addLast(i);
        }
        for (Integer i : evl) {
            assertSame(arr[n++], i);
        }
    }
}