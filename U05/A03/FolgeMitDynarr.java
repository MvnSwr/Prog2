package Java.Prog_2.U05.A03;

import Java.Prog_2.U04.A02.DynArray;
import Java.Prog_2.U05.A02.Folge;
import java.util.Iterator;


public class FolgeMitDynarr<T> implements Folge<T> {
    private DynArray<T> data = new DynArray<>();

    @Override
    public boolean isEmpty() {
        return data.size() == 0;
    }

    @Override
    public int capacity() {
        return data.capacity();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public void insert(T obj) {
        data.addLast(obj);
    }

    @Override
    public T remove() {
        return data.removeFirst();
    }

    @Override
    public T get(int pos) {
        return data.get(pos);
    }

    @Override
    public T set(int pos, T e) {
        return data.set(pos, e);
    }

    @Override
    public T remove(int pos) {
        return data.remove(pos);
    }

    @Override
    public void insert(int pos, T e) throws IndexOutOfBoundsException {
        data.insert(pos, e);
    }

    @Override
    public Iterator<T> iterator() {
        return data.iterator();
    }

}

// Test um den Iterator zu probieren
class test {
    public static void main(String[] args) {
        FolgeMitDynarr<Integer> folge = new FolgeMitDynarr<>();
        int[] ia = { 1, 2, 3, 4, 5, 6, 7 };
        for (Integer i : ia) {
            folge.insert(i);
        }
        for (Integer i : folge) {
            System.out.println(i);
        }
    }
}