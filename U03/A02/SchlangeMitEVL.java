package Java.Prog_2.U03.A02;

import Java.Prog_2.U00.Schlange;
import Java.Prog_2.U03.A01.EVL;
import java.util.Iterator;


public class SchlangeMitEVL<T> implements Schlange<T> {
    EVL<T> Liste;

    public SchlangeMitEVL() {
        Liste = new EVL<>();
    }

    public boolean isEmpty() {
        return Liste.size() == 0;
    }

    @Override
    public int capacity() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int size() {
        return Liste.size();
    }

    @Override
    public void insert(T e) {
        Liste.addLast(e);
    }

    @Override
    public T remove() {
        return Liste.removeFirst();
    }

    @Override
    public T front() {
        return Liste.getFirst();
    }

    @Override
    public Iterator<T> iterator() {
        return Liste.iterator();
    }
}

// Test um den Iterator zu probieren
class test {
    public static void main(String[] args) {
        SchlangeMitEVL<Integer> folge = new SchlangeMitEVL<>();
        int[] ia = { 1, 2, 3, 4, 5, 6, 7 };
        for (Integer i : ia) {
            folge.insert(i);
        }
        for (Integer i : folge) {
            System.out.println(i);
        }
    }
}