package Java.Prog_2.U00;

public interface Puffer<T> extends Iterable<T> {
    public boolean isEmpty();

    public int capacity();

    public int size();

    public void insert(T obj);

    public T remove();
}
