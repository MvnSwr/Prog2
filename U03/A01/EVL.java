package Java.Prog_2.U03.A01;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EVL<T> implements Iterable<T> {
    Listenelement first = null; // referenz zu dem ersten Element in der Liste
    int size = 0; // Anzahl an Knoten in der Liste

    class Listenelement { // innere Klasse für die einezelnen Knoten
        T data; // Speicherplatz für die Daten
        Listenelement next; // Referenz zu dem nächsten Object

        public Listenelement(T obj) { // Konstruktor um die neue Datei zu speichern
            data = obj;
        }
    }

    public void addLast(T e) {
        Listenelement le = new Listenelement(e); // neues Listenelement anlegens
        if (size == 0) { // sollte es noch keins geben, dann wird der neue Knoten in der first Referenz
            first = le; // gespeichert
        } else {
            Listenelement tmp = first; // falls es schon Knoten gibt, dann wird solange itteriert,
            while (tmp.next != null) { // bis der nächste Knoten den next-pointer auf null hat
                tmp = tmp.next;
            }
            tmp.next = le; // die next Referenz ist dann das neue Listenelement
        }
        size++;
    }

    public T getFirst() throws NullPointerException {
        if (first == null) {
            throw new NullPointerException("Empty List");
        }
        return first.data; // hab den cast nach (T) entfernt
    }

    public T getLast() throws NullPointerException {
        if (first == null) {
            throw new NullPointerException("Empty List");
        }
        Listenelement tmp = first;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        return tmp.data;
    }

    public T removeFirst() throws NullPointerException {
        if (first == null) {
            throw new NullPointerException();
        }
        Listenelement tmp = first;
        T out = tmp.data;
        first = tmp.next;
        return out;
    }

    public void removeLast() throws NullPointerException {
        if (first == null) {
            throw new NullPointerException("Empty List");
        }
        Listenelement tmp = first;
        if (tmp.next == null) {// es gibt nur ein Element
            first = null;
        } else {
            while (tmp.next != null) {
                Listenelement tmp2 = tmp.next;
                if (tmp2.next == null) {
                    tmp.next = null;
                } else {
                    tmp = tmp.next;
                }
            }
        }
        size--;
    }

    public int size() {
        return size;
    }

    public boolean contains(T e) throws NullPointerException {
        if (first == null) {
            throw new NullPointerException("Empty List");
        }
        Listenelement tmp = first;
        String in = e.toString();
        for (int i = 0; i < size; i++) {
            if (in.equals(tmp.data.toString())) {
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }

    public void zip(EVL<T> other) throws NullPointerException {
        if (size == 0 || other.size == 0) {
            throw new NullPointerException("Leere Listen");
        }
        EVL<T> out = new EVL<>();
        boolean a, b;
        a = b = true;
        while (first != null || other.first != null) {
            if (a) {
                out.addLast(removeFirst());
                a = first != null;
            }
            if (b) {
                out.addLast(other.removeFirst());
                b = other.first != null;
            }
        }
        first = out.first;
    }

    public String toString() {
        String out = "";
        Listenelement tmp = first;
        if (tmp != null)
            out += tmp.data;
        tmp = tmp.next;
        while (tmp != null) {
            out += "-" + tmp.data;
            tmp = tmp.next;
        }
        return out;
    }

    /*
     * Iterator
     */

    // Iterator Subklasse

    private class EVLIterator implements Iterator<T> {
        Listenelement tmp = first;

        @Override
        public boolean hasNext() {
            return tmp != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T out = tmp.data;
            tmp = tmp.next;
            return out;
        }
    }

    // Iterarotmethode

    @Override
    public Iterator<T> iterator() {
        return new EVLIterator();
    }
}