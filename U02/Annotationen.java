package Java.Prog_2.U02;

public class Annotationen<T> {
    T[] feld;

    @SuppressWarnings("unchecked")

    public Annotationen(int capacity) {
        feld = (T[]) new Object[capacity];
    }

    public void insert(int pos, T e) {
        if (pos < 0 || pos >= feld.length)
            throw new UnsupportedOperationException();
        feld[pos] = e;
    }

    public T remove(int pos) {
        if (pos < 0 || pos >= feld.length)
            throw new UnsupportedOperationException();
        if (feld[pos] == null)
            throw new NullPointerException();
        return feld[pos];
    }
}