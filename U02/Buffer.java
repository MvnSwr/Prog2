package Java.Prog_2.U02;

public interface Buffer<T> {
    boolean isEmpty();

    int capacity();

    int size();

    void insert(T p);

    T remove();
}
