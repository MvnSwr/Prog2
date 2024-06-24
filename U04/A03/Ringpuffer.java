package Java.Prog_2.U04.A03;

import java.util.Iterator;

public class Ringpuffer<T> implements Iterable<T> {
    private int capacity;
    private int front = -1;
    private int end = -1;
    private T[] dataArray;

    @SuppressWarnings("unchecked")
    public Ringpuffer(int capacity) {
        this.capacity = capacity;
        dataArray = (T[]) new Object[capacity];
    }

    public int size() {
        if (front == -1) {// array nicht initialisiert
            return 0;
        }
        if (front <= end) {
            return end - front + 1; // da front der Punkt des ersten Eintrages ist End aber auch der Punkt des
                                    // letzten Eintrages
        } else {
            return capacity - front + end + 1;
        }
    }

    public T get(int pos) throws NullPointerException {
        if (front == -1 || end < pos && pos < front) {
            throw new NullPointerException();
        }
        pos += front;
        while (pos > capacity - 1) {
            pos -= capacity;
        }
        return dataArray[pos];
    }

    public T set(int pos, T e) { // Überschreibung
        T tmp = get(pos);
        while (pos > capacity - 1) {
            pos -= capacity;
        }
        dataArray[pos] = e;
        return tmp;
    }

    public void addFirst(T e) {
        if (front == -1) { // nicht initialisiert
            front = end = 0;
            dataArray[front] = e;
        } else { // initialisiert
            if (--front < 0) { // front geht vorne aus dem Array raus
                front = capacity - 1;
            }
            dataArray[front] = e;
            if (front == end) { // Fall das das komplette Array voll ist
                if (--end < 0) { // End muss um eins dekrementiert werden
                    end = capacity - 1;
                }
            }
        }
    }

    public void addLast(T e) {
        if (end == -1) { // nicht initialisiert
            front = end = 0;
            dataArray[end] = e;
        } else { // initialisiert
            if (++end == capacity) { // läuft hinten aus dem Array raus
                end = 0;
            }
            dataArray[end] = e;
            if (front == end) {
                if (++front == capacity) {
                    front = 0;
                }
            }
        }
    }

    public T removeFirst() throws NullPointerException {
        if (front == -1) {
            throw new NullPointerException();
        }
        T tmp = dataArray[front];
        if (front == end) { // Fall das nur ein Elemtent im Array ist. Array wird deinitialisiert
            front = end = -1;
        } else {
            if (++front == capacity) { // front wird einfach verschoben
                front = 0;
            }
        }
        return tmp;
    }

    public T removeLast() throws NullPointerException {
        if (front == -1) {
            throw new NullPointerException();
        }
        T tmp = dataArray[end];
        if (front == end) { // Fall das nur ein Elemtent im Array ist. Array wird deinitialisiert
            front = end = -1;
        } else {
            if (--end < 0) { // end wird einfach verschoben
                end = capacity - 1;
            }
        }
        return tmp;
    }

    public T remove(int pos) throws IndexOutOfBoundsException { // alle Elemente werden aufgerückt
        if ((front <= end && pos < front && end < pos)
                || (front > end && end < pos && pos < front)) {
            throw new IndexOutOfBoundsException();
        }
        T tmp = get(pos);
        if (front == end) { // es gab nur einen Eintrag. Array wird deinitialisiert
            front = end = -1;
        } else if (front < end) { // fall für array geht von vorne bis hinten
            for (int i = pos; i < end; i++) {
                dataArray[i] = dataArray[i + 1];
            }
            end--;
        } else { // Fall front ist größer als end
            for (int i = pos; i < capacity - 1; i++) {
                dataArray[i] = dataArray[i + 1];
            }
            dataArray[capacity - 1] = dataArray[0]; // im array von pos 0 auf pos ganz hinten
            for (int i = 0; i < end; i++) {
                dataArray[i] = dataArray[i + 1];
            }
            end--;
        }
        return tmp;
    }

    public void insert(int pos, T e) { // Wird an der Stelle eingefügt und alle Elemente werden nach hinten verschoben
        if ((front <= end && pos < front && end < pos)
                || (front > end && end < pos && pos < front)) {
            throw new IndexOutOfBoundsException();
        }
        if (front <= end) { // fall für array geht von vorne bis hinten
            T tmp = dataArray[end];
            for (int i = end; i > pos; i--) { // alles verschieben
                dataArray[i] = dataArray[i - 1];
            }
            if (end == capacity - 1) { // end ist ganz hinten im Array
                dataArray[0] = tmp;
                end = 0;
            } else {
                dataArray[++end] = tmp;
            }
        } else { // Fall front ist größer als end
            end++;
            if (pos < end) {// pos ist noch vor end
                for (int i = end; i > pos; i--) {
                    dataArray[i] = dataArray[i - 1];
                }
            } else {// pos ist hinter end, also vor front
                for (int i = end; i > 0; i--) {// vorne alles einmal verschieben
                    dataArray[i] = dataArray[i - 1];
                }
                dataArray[0] = dataArray[capacity - 1];// ganz hinten im array muss ein Element sein, sonst wären wir
                                                       // nicht beim Fall front ist größer als end
                for (int i = capacity - 1; i > pos; i--) {// hinten alles einmal verschieben
                    dataArray[i] = dataArray[i - 1];
                }
            }
        }
        if (end == front) { // front muss um eins verschoben werden wenn das Array voll ist
            front++;
        }
        dataArray[pos] = e;// Element in Array speichern
    }

    // Iterator
    private class RingIterator implements Iterator<T> {
        int i = front;

        @Override
        public boolean hasNext() {
            return i < size();
        }

        @Override
        public T next() {
            T data = dataArray[i++];
            if (i > end) {
                i = 0;
            }
            return data;
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new RingIterator();
    }
}