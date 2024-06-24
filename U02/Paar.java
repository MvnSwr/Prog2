package Java.Prog_2.U02;

public class Paar<E, Z> {
    private E first;
    private Z second;

    public Paar(E a, Z b) {
        first = a;
        second = b;
    }

    public E getFirst() {
        return first;
    }

    public Z getSecond() {
        return second;
    }

    public void setFirst(E first) {
        this.first = first;
    }

    public void setSecond(Z second) {
        this.second = second;
    }

    public void setBoth(E e, Z z) {
        first = e;
        second = z;
    }

    public boolean equals(Paar<E, Z> p) {

        return toString().equals(p.toString()); // ich vergleiche die Strings der einzelnen Einträge

    }

    @Override
    public String toString() {
        return "(" + first.toString() + "," + second.toString() + ")";
    }
}
