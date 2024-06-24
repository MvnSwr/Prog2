package Java.Prog_2.U03.A05;

public class RDVL<T> { // Ring-doppelt-verkettete-Liste
    Listenelement entry = null;
    int size = 0;

    class Listenelement {
        T data;
        Listenelement prev;
        Listenelement next;

        public Listenelement(T obj) {
            data = obj;
            prev = next = null;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void add(T e) {
        Listenelement le = new Listenelement(e);
        if (size == 0) {
            entry = le;
        } else if (size == 1) { // Sonderfall für nur ein Listenelement
            Listenelement tmp = entry;
            le.next = tmp;
            le.prev = tmp;
            entry.prev = le;
            entry.next = le;
        } else {
            Listenelement tmp = entry; // tmp ist das Einstiegselement
            Listenelement before = tmp.prev; // before ist das Element vor dem entry
            le.prev = before; // referenz vom neuen Element zu vorher
            le.next = tmp; // referenz vom neuen Element zu entry
            before.next = le; // referenz von zuvor.next auf das neue Element
            entry.prev = le; // referenz von entry zurück auf das neue Element
        }
        size++;
    }

    public T remove() throws NullPointerException {
        if (size == 0) {
            throw new NullPointerException("Kein Element vorhanden");
        }
        T out = entry.data; // Ausgabendate speichern
        Listenelement before = entry.prev; // Element nach entry
        Listenelement after = entry.next; // Element vor entry
        before.next = after; // Referenz zu entry werden gelöscht
        after.prev = before;
        entry = after;
        size--;
        return out;
    }

    public T element() throws NullPointerException {
        if (size == 0) {
            throw new NullPointerException("Kein Element vorhanden");
        }
        return entry.data;
    }

    public void next(int s) throws NullPointerException {
        if (size == 0) {
            throw new NullPointerException("Kein Element vorhanden");
        }
        Listenelement tmp = entry;
        for (int i = 0; i < s; i++) {
            tmp = tmp.next;
        }
        entry = tmp;
    }

    public void prev(int s) throws NullPointerException {
        if (size == 0) {
            throw new NullPointerException("Kein Element vorhanden");
        }
        Listenelement tmp = entry;
        for (int i = 0; i < s; i++) {
            tmp = tmp.prev;
        }
        entry = tmp;
    }

    @Override
    public String toString() {
        String out = "";
        Listenelement tmp = entry;
        if (tmp != null)
            out += tmp.data;
        tmp = tmp.next;
        for (int i = 1; i < size; i++) {
            out += "-" + tmp.data;
            tmp = tmp.next;
        }
        return out;
    }
}