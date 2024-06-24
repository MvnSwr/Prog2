package Java.Prog_2.U02;

import java.util.*;

public class Fifo<T> implements Buffer<T> {
    private T[] stapel;
    private int pointer = 0;

    @SuppressWarnings("unchecked") // damit der cast von Object zu T keine Warnung spuckt
    public Fifo(int maxGroesse) {
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
    public void insert(T p) throws java.lang.IllegalStateException {
        if (pointer >= stapel.length) {
            throw new IllegalStateException();
        }
        stapel[pointer++] = (T) p;
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

    // public T front() throws java.util.NoSuchElementException {
    // if (pointer == 0) {
    // throw new NoSuchElementException();
    // }
    // return stapel[0];
    // }
}