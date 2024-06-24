package Java.Prog_2.U09.Aall;

import Java.Prog_2.U00.Puffer;
import Java.Prog_2.U03.A02.SchlangeMitEVL;
import Java.Prog_2.U05.A03.FolgeMitDynarr;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;


public abstract class GenericUtil<T> {

    public static <T> T gambling(T a1, T a2) {
        Random r = new Random();
        if (r.nextInt(2) == 0) {
            return a1;
        } else {
            return a2;
        }
    }

    public static <T> SchlangeMitEVL<T> arrayToSchlange(T[] arr) {
        SchlangeMitEVL<T> schlange = new SchlangeMitEVL<>();
        for (T data : arr) {
            schlange.insert(data);
        }
        return schlange;
    }

    // Überladung von gambling
    public static <T> Puffer<T> gambling(Puffer<T> puf1, Puffer<T> puf2) {
        Random r = new Random();
        if (r.nextInt(2) == 0) {
            return puf1;
        } else {
            return puf2;
        }
    }

    // Typeinschränkung von obj da es iterierbar sein soll, daher geht auch ForEach
    public static <T extends Iterable<T>> void printall(T obj) {
        for (T data : obj) {
            System.out.println(data);
        }
    }

    // U muss abgeleitet von T sein, damit der Cast immer ohne Probleme
    // funktionieren kann. Sonst müssen wir einen unchecked cast unterdrücken
    public static <T, U extends T> void insertInto(Puffer<T> puf1, U[] arr) {
        for (U data : arr) {
            puf1.insert((T) data);
        }
    }

    // insertInto überladen
    public static <U> void insertInto(Puffer<U> puf1, Puffer<U> puf2) {
        for (int i = 0; i < puf2.size(); i++) {
            puf1.insert(puf2.remove());
        }
    }

    public static <U extends Comparable<U>> FolgeMitDynarr<U> getMinima(Puffer<U> puf1, Puffer<U> puf2) {
        FolgeMitDynarr<U> folge = new FolgeMitDynarr<>();
        Iterator<U> it1 = puf1.iterator();
        Iterator<U> it2 = puf2.iterator();

        while (it1.hasNext() && it2.hasNext()) {
            U data1 = it1.next();
            U data2 = it2.next();
            if (data1.compareTo(data2) <= 0) {
                folge.insert(data1);
            } else {
                folge.insert(data2);
            }
        }

        while (it1.hasNext()) {
            folge.insert(it1.next());
        }

        while (it2.hasNext()) {
            folge.insert(it2.next());
        }

        return folge;
    }

    // getMinima überladen mit einem Comparator
    public static <U extends Comparable<U>> FolgeMitDynarr<U> getMinima(Puffer<U> puf1, Puffer<U> puf2,
            Comparator<U> coparator) {
        FolgeMitDynarr<U> folge = new FolgeMitDynarr<>();
        Iterator<U> it1 = puf1.iterator();
        Iterator<U> it2 = puf2.iterator();

        while (it1.hasNext() && it2.hasNext()) {
            U data1 = it1.next();
            U data2 = it2.next();
            if (coparator.compare(data1, data2) <= 0) {
                folge.insert(data1);
            } else {
                folge.insert(data2);
            }
        }

        while (it1.hasNext()) {
            folge.insert(it1.next());
        }

        while (it2.hasNext()) {
            folge.insert(it2.next());
        }

        return folge;
    }

    public static <T extends Comparable<T>> FolgeMitDynarr<?> getMinima2(Puffer<? extends T> puf1,
            Puffer<? extends T> puf2) {
        FolgeMitDynarr<T> folge = new FolgeMitDynarr<>();
        Iterator<? extends T> it1 = puf1.iterator();
        Iterator<? extends T> it2 = puf2.iterator();

        while (it1.hasNext() && it2.hasNext()) {
            T data1 = it1.next();
            T data2 = it2.next();
            if (data1.compareTo(data2) <= 0) {
                folge.insert(data1);
            } else {
                folge.insert(data2);
            }
        }

        while (it1.hasNext()) {
            folge.insert(it1.next());
        }

        while (it2.hasNext()) {
            folge.insert(it2.next());
        }

        return folge;
    }

    public static <U> FolgeMitDynarr<U> getMinima2(Puffer<? extends U> puf1,
            Puffer<? extends U> puf2, Comparator<? super U> coparator) {
        FolgeMitDynarr<U> folge = new FolgeMitDynarr<>();
        Iterator<? extends U> it1 = puf1.iterator();
        Iterator<? extends U> it2 = puf2.iterator();

        while (it1.hasNext() && it2.hasNext()) {
            U data1 = it1.next();
            U data2 = it2.next();
            if (coparator.compare(data1, data2) <= 0) {
                folge.insert(data1);
            } else {
                folge.insert(data2);
            }
        }

        while (it1.hasNext()) {
            folge.insert(it1.next());
        }

        while (it2.hasNext()) {
            folge.insert(it2.next());
        }

        return folge;
    }
}