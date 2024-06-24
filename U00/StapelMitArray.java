package Java.Prog_2.U00;

import Java.Prog_2.U01.A03.Funktion;
import java.util.NoSuchElementException;


public class StapelMitArray implements Stapel {
    private int[] stapel;
    private int pointer = 0;

    public StapelMitArray(int maxGroesse) {
        stapel = new int[maxGroesse];
    }

    public boolean isEmpty() {
        return pointer == 0;
    }

    public int capacity() {
        return stapel.length;
    }

    public int size() {
        return pointer;
    }

    public void insert(int zahl) throws java.lang.IllegalStateException {
        if (pointer >= stapel.length) {
            throw new IllegalStateException();
        }
        stapel[pointer++] = zahl;
    }

    public int remove() throws java.util.NoSuchElementException {
        if (pointer <= 0) {
            throw new NoSuchElementException();
        }
        return stapel[--pointer];
    }

    @Override
    public int top() throws java.util.NoSuchElementException {
        if (pointer <= 0) {
            throw new NoSuchElementException();
        }
        return stapel[pointer - 1];
    }

    public void applyToAll(Funktion fkt) { // wir übergeben ein Objekt der Schnittstelle Funktion
        for (int i = 0; i < pointer; i++) {
            stapel[i] = fkt.auswerten(stapel[i]); // Hier wenden wir von der Schnittstelle Funktion die Methode
                                                  // auswerten an
            // auswerten müsste jedoch noch per Lambdafunktion definitert werde
        }
    }

    public static void main(String[] args) {
        try {
            StapelMitArray Arr = new StapelMitArray(3);
            Arr.insert(1);
            Arr.insert(2);
            Arr.insert(3);
            // Arr.remove();
            // Arr.top();
            // Arr.insert(15);
            // Arr.insert(6);
            // Arr.insert(17);
            // System.out.println(Arr.top());
            // System.out.println(Arr.capacity());
            // System.out.println(Arr.isEmpty());
            // System.out.println(Arr.size());
            // Arr.remove();
            // System.out.println(Arr.top());
            // System.out.println(Arr.capacity());
            // System.out.println(Arr.isEmpty());
            // System.out.println(Arr.size());
            // Arr.applyToAll
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}