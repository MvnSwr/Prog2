package Java.Prog_2.U03.A01;

public class EVLMain {
    public static void main(String[] args) {
        EVL<String> Liste = new EVL<>();
        EVL<Integer> Zahlenliste = new EVL<>();
        String[] Stadte = { "Hamburg", "Berlin", "München", "Bremen", "Duisburg", "Frankfurt", "Nürnberg" };
        int[] zahlen = { 3, 2, 6, 5, 1, 2, 4, 2, 3, 4, 5, 6, 5, 1, 2, 4, 3 };
        for (String s : Stadte) { // wilde Iteration durch das Stringarray
            Liste.addLast(s);
            System.out.println(Liste);
        }
        for (int i : zahlen) {
            Zahlenliste.addLast(i);
        }
        try {
            System.out.println();
            System.out.println(Liste.getFirst());
            System.out.println(Liste.getLast());
            Liste.removeLast();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}