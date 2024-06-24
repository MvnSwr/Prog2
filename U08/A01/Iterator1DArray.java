package Java.Prog_2.U08.A01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Iterator1DArray<T> implements Iterable<T> {
    T[] data;
    int beginn, end;

    /*
     * Konstruktoren
     */

    // standard Konstruktor

    public Iterator1DArray(T[] arr, int bgn, int end) throws IndexOutOfBoundsException {
        if (end > arr.length) {
            throw new IndexOutOfBoundsException("Ungültiger end Wert");
        }
        if (bgn >= end) {
            throw new IndexOutOfBoundsException("Ungültiger beginn Wert");
        }
        data = arr;
        beginn = bgn;
        this.end = end;
        // Es wird exklusiv bis zu end iteriert
    }

    // weitere Konstruktoren

    public Iterator1DArray(T[] arr, int bgn) {
        this(arr, bgn, arr.length);
    }

    public Iterator1DArray(T[] arr) {
        this(arr, 0, arr.length);
    }

    // Spezialfall mit comparator, das ganze Array wird einmal sortiert
    @SuppressWarnings("unchecked")
    public Iterator1DArray(T[] arr, Comparator<T> comparator) throws IndexOutOfBoundsException {
        ArrayList<T> al = new ArrayList<>();
        for (T e : arr) {
            al.add(e);
        }
        Collections.sort(al, comparator);
        data = (T[]) new Object[al.size()];
        int i = 0;
        for (T e : al) {
            data[i++] = e;
        }
        beginn = 0;
        end = data.length;
    }

    /*
     * Iteratorklasse
     */

    private class MyIterator implements Iterator<T> {
        int i;

        // Konstruktor
        private MyIterator() {
            this.i = beginn;
        }

        // Die bekannten Methoden
        @Override
        public boolean hasNext() {
            return i < end;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                return data[i++];
            }
        }
    }

    // Einfach eine notwendige Methode um hier zu iterieren
    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }
}

@SuppressWarnings("unused")
class testing {
    public static void main(String[] args) {
        Integer[] y = { 1, 2, 3, 4, 5, 7, 6 };
        int[] x = { 1, 2, 3, 4, 5, 7, 6 }; // Geht nicht, da wir einen richtigen "Typ" definieren müssen. Problem ist
                                           // das autoboxing bei int...

        // Comparator
        Comparator<Integer> intsort = new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                if (x < y) {
                    return -1;
                } else if (x > y) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };

        Iterator1DArray<Integer> it = new Iterator1DArray<>(y, intsort);
        for (Integer i : it) {
            System.out.println(i);
        }
    }
}