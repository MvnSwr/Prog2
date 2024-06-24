package Java.Prog_2.U03.A03;

import Java.Prog_2.U00.SchlangeMitArray;
import Java.Prog_2.U03.A02.SchlangeMitEVL;

public class TimeTestSchlange {
    public static void main(String[] args) {
        SchlangeMitArray<Integer> Arr = null;
        SchlangeMitEVL<Integer> EVL = null;
        long start, finish, elapsed;

        start = System.currentTimeMillis();
        Arr = new SchlangeMitArray<>(100000);
        for (int i = 0; i < 100000; i++) {
            Arr.insert(i);
        }
        for (int i = 0; i < 100000; i++) {
            Arr.remove();
        }
        finish = System.currentTimeMillis();
        elapsed = finish - start;
        System.out.println("Array: \n" + elapsed + "\nListe:");
        start = System.currentTimeMillis();
        EVL = new SchlangeMitEVL<>();
        for (int i = 0; i < 100000; i++) {
            EVL.insert(i);
        }
        for (int i = 0; i < 100000; i++) {
            EVL.remove();
        }
        finish = System.currentTimeMillis();
        elapsed = finish - start;
        System.out.println(elapsed);
    }
}