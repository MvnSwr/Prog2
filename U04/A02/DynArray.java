package Java.Prog_2.U04.A02;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class DynArray<T> implements Iterable<T> {
    private int size = 0;
    private int capacity = 1;
    private T[] data = (T[]) new Object[capacity];

    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }

    private void increase() { // kann immer ausgeführt werden, bedingungen werden in Methode geprüft
        if (size == capacity) {
            T[] tmp = (T[]) new Object[capacity += 5];
            for (int i = 0; i < size; i++) {
                tmp[i] = data[i];
            }
            data = tmp;
        }
    }

    private void decrease() { // kann immer ausgeführt werden, bedingungen werden in Methode geprüft
        if (capacity - size > 5) {
            T[] tmp = (T[]) new Object[capacity = size + 2];
            for (int i = 0; i < size; i++) {
                tmp[i] = data[i];
            }
            data = tmp;
        }
    }

    public T get(int pos) throws NullPointerException {
        if (pos >= size) {
            throw new NullPointerException();
        }
        return data[pos];
    }

    // ersetzen und return
    public T set(int pos, T e) throws NullPointerException {
        if (pos >= size) { // pos ist die Position im Arr //size die Menge an Objekten im Array
            throw new NullPointerException();
        }
        increase();
        T tmp = data[pos];
        data[pos] = e;
        return tmp;
    }

    public void addFirst(T e) {
        increase();
        for (int i = size; i > 0; i--) {
            data[i] = data[i - 1];
        }
        data[0] = e;
        size++;
    }

    public void addLast(T e) {
        increase();
        data[size] = e;
        size++;
    }

    public T removeFirst() throws NullPointerException {
        if (size == 0) {
            throw new NullPointerException();
        }
        T tmp = data[0];
        for (int i = 0; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        decrease();
        return tmp;
    }

    public T removeLast() throws NullPointerException {
        if (size == 0) {
            throw new NullPointerException();
        }
        T tmp = data[--size];
        data[size] = null;
        decrease();
        return tmp;
    }

    public void insert(int pos, T e) throws IndexOutOfBoundsException {
        increase();
        if (pos >= capacity) {
            throw new IndexOutOfBoundsException(pos);
        }
        for (int i = size; i > pos; i--) {
            data[i] = data[i - 1];
        }
        data[pos] = e;
        size++;
    }

    public T remove(int pos) {
        T tmp = data[pos];
        for (int i = pos; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        decrease();
        return tmp;
    }

    /*
     * Iterator
     */

    // Iterator Subklasse
    private class DynArrayIterator implements Iterator<T> {
        int i = 0;

        @Override
        public boolean hasNext() {
            return i < size;
        }

        @Override
        public T next() {
            return data[i++];
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new DynArrayIterator();
    }
}