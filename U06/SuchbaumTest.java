package Java.Prog_2.U06;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Java.Prog_2.U05.A03.FolgeMitDynarr;




public class SuchbaumTest {

    IntSuchbaum tree;
    int[] arr = { 4, 5, 2, 6, 1, 3, 7 };

    private void inserting() {
        for (Integer i : arr) {
            tree.insert(i);
        }
    }

    @Before
    public void build() {
        tree = new IntSuchbaum();
    }

    @After
    public void teardown() {
        tree = null;
    }

    @Test
    public void contains() {
        inserting();
        assertTrue(tree.contains(5));
        assertTrue(tree.contains(7));
        assertFalse(tree.contains(11));
    }

    @Test
    public void string() {
        inserting();
        assertEquals(tree.toString(), "(((1)2(3))4(5(6(7))))");
    }

    @Test
    public void hoehe() {
        inserting();
        assertSame(tree.hoehe(), 3);
        tree.insert(11);
        assertSame(tree.hoehe(), 4);
        tree.insert(9);
        assertSame(tree.hoehe(), 5);
        tree.insert(12);
        assertSame(tree.hoehe(), 5);
    }

    @Test
    public void size() {
        inserting();
        assertSame(tree.size(), 7);
        tree.insert(9);
        assertSame(tree.size(), 8);
    }

    @Test
    public void preorder() {
        inserting();
        FolgeMitDynarr<Integer> flg = tree.preorder();
        int[] outputArr = { 4, 2, 1, 3, 5, 6, 7 };
        for (int i = 0; i < tree.size(); i++) {
            assertSame(flg.remove(), outputArr[i]);
        }
    }

    @Test
    public void inorder() {
        inserting();
        FolgeMitDynarr<Integer> flg = tree.inorder();
        int[] outputArr = { 1, 2, 3, 4, 5, 6, 7 };
        for (int i = 0; i < tree.size(); i++) {
            assertSame(flg.remove(), outputArr[i]);
        }
    }

    @Test
    public void postorder() {
        inserting();
        FolgeMitDynarr<Integer> flg = tree.postorder();
        int[] outputArr = { 1, 3, 2, 7, 6, 5, 4 };
        for (int i = 0; i < tree.size(); i++) {
            assertSame(flg.remove(), outputArr[i]);
        }
    }

    @Test
    public void breitensuche() {
        inserting();
        FolgeMitDynarr<Integer> flg = tree.breitensuche();
        int[] outputArr = { 4, 2, 5, 1, 3, 6, 7 };
        for (int i = 0; i < tree.size(); i++) {
            assertSame(flg.remove(), outputArr[i]);
        }
    }

    @Test
    public void remove() {
        inserting();
        assertTrue(tree.contains(2));
        assertTrue(tree.contains(4));
        tree.remove(2);
        assertFalse(tree.contains(2));
        tree.remove(4);
        assertFalse(tree.contains(4));
    }
}