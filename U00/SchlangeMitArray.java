package Java.Prog_2.U00;

import java.util.*;

@SuppressWarnings("unchecked")
public class SchlangeMitArray<T> implements Schlange<T> {
    private T[] stapel;
    private int pointer = 0;

    public SchlangeMitArray(int maxGroesse) {
        stapel = (T[]) new Object[maxGroesse];
    }

    @Override
    public boolean isEmpty() {
        return pointer == 0;
    }

    @Override
    public int capacity() {
        return stapel.length;
    }

    @Override
    public int size() {
        return pointer;
    }

    @Override
    public void insert(T obj) throws java.lang.IllegalStateException {
        if (pointer >= stapel.length) {
            throw new IllegalStateException();
        }
        stapel[pointer++] = obj;
    }

    @Override
    public T remove() throws java.util.NoSuchElementException {
        if (pointer <= 0) {
            throw new NoSuchElementException();
        }
        T out = stapel[0];
        for (int i = 0; i < pointer - 1; i++) {
            stapel[i] = stapel[i + 1];
        }
        return out;
    }

    @Override
    public T front() throws java.util.NoSuchElementException {
        if (pointer == 0) {
            throw new NoSuchElementException();
        }
        return stapel[0];
    }

    private class SchlangeIterator implements Iterator<T> {
        int i = 0;

        @Override
        public boolean hasNext() {
            return i < stapel.length;
        }

        @Override
        public T next() {
            return stapel[i++];
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new SchlangeIterator();
    }

}

class FifoG<T> {
    @SuppressWarnings("unchecked")
    T[] stapel = (T[]) new Object[5];
    int size = 0;

    public void push(T p) {
        stapel[size++] = p;
    }
}