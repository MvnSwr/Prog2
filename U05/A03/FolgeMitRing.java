package Java.Prog_2.U05.A03;

import Java.Prog_2.U04.A03.Ringpuffer;
import Java.Prog_2.U05.A02.Folge;
import java.util.Iterator;


public class FolgeMitRing<T> implements Folge<T> {

    private Ringpuffer<T> data;
    private int capacity;

    public FolgeMitRing(int capacity) {
        data = new Ringpuffer<>(capacity);
        this.capacity = capacity;
    }

    @Override
    public boolean isEmpty() {
        return data.size() == 0;
    }

    @Override
    public int capacity() {
        return capacity;
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
    public T remove(int pos) throws IndexOutOfBoundsException {
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