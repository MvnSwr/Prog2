package Java.Prog_2.U05.A04;

import Java.Prog_2.U03.A02.SchlangeMitEVL;
import Java.Prog_2.U05.A03.FolgeMitDynarr;

public class timetest {
    public static void main(String[] args) {
        FolgeMitDynarr<Integer> Arr = null;
        SchlangeMitEVL<Integer> EVL = null;
        long start, finish, elapsed;

        start = System.currentTimeMillis();
        Arr = new FolgeMitDynarr<>();
        for (int i = 0; i < 100000; i++) {
            Arr.insert(i);
        }
        for (int i = 0; i < 100000; i++) {
            Arr.remove();
        }
        finish = System.currentTimeMillis();
        elapsed = finish - start;
        System.out.println("Folge mit DynArray: \n" + elapsed + "\nSchlange mit EVL:");
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