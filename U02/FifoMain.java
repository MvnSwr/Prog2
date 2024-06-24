package Java.Prog_2.U02;

public class FifoMain {
    public static void main(String[] args) {
        Fifo<Integer> stapel = new Fifo<>(5);
        System.out.println(stapel.capacity());
    }
}
