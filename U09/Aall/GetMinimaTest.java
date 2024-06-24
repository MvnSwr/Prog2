package Java.Prog_2.U09.Aall;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.Comparator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Java.Prog_2.U00.Puffer;
import Java.Prog_2.U05.A03.FolgeMitDynarr;

public class GetMinimaTest {
    Puffer<Integer> pf1, pf2;
    Puffer<String> spf1, spf2;
    Puffer<Integer> p1, p2;
    Puffer<Student> s1;
    Puffer<Boxer> s2;
    Comparator<Person> c3;

    @Before
    public void build() {
        pf1 = new FolgeMitDynarr<>();
        pf2 = new FolgeMitDynarr<>();
        pf1.insert(1);
        pf1.insert(3);
        pf1.insert(5);
        pf1.insert(4);
        pf2.insert(2);
        pf2.insert(2);

        spf1 = new FolgeMitDynarr<>();
        spf2 = new FolgeMitDynarr<>();
        spf1.insert("Aber");
        spf2.insert("Bald");
        spf2.insert("Zum schluß");

        p1 = new FolgeMitDynarr<>();
        p2 = new FolgeMitDynarr<>();
        p1.insert(2);
        p2.insert(1);
        p1.insert(5);

        s1 = new FolgeMitDynarr<>();
        s2 = new FolgeMitDynarr<>();
        c3 = new Comparator<Person>() {

            @Override
            public int compare(Person arg0, Person arg1) {
                return arg0.toString().compareTo(arg1.toString());
            }

        };
    }

    @After
    public void teardown() {
        pf1 = null;
        pf2 = null;
    }

    @Test
    public void test() {
        FolgeMitDynarr<Integer> folgeMitDyn = GenericUtil.<Integer>getMinima(pf1, pf2);
        assertSame(folgeMitDyn.remove(), 1);
        assertSame(folgeMitDyn.remove(), 2);
        assertSame(folgeMitDyn.remove(), 5);
        assertSame(folgeMitDyn.remove(), 4);
    }

    @Test
    public void test2() {
        FolgeMitDynarr<?> stringFolge = GenericUtil.getMinima(spf1, spf2);
        assertEquals(stringFolge.remove(), "Aber");
        assertEquals(stringFolge.remove(), "Zum schluß");
    }

    @Test
    public void testWildcard() {
        FolgeMitDynarr<?> folgeWild = GenericUtil.getMinima2(p1, p2);
        assertSame(folgeWild.remove(), 1);
        assertSame(folgeWild.remove(), 5);
    }

    @Test
    public void testWildcard2() {
        // FolgeMitDynarr<?> folgeWild = GenericUtil.getMinima2(s1, s2, c3);
    }

}

class Person {
    protected String name, vorname;

    // Konstruktor
    public Person(String vorname, String name) {
        this.name = name;
        this.vorname = vorname;
    }

    // toString
    @Override
    public String toString() {
        return vorname + " " + name;
    }
}

// erste relevante Klasse Student
class Student extends Person implements Comparable<Student> {
    protected int matrikelnummer;

    // compare Methode zum vergleichen
    @Override
    public int compareTo(Student o) {
        return this.matrikelnummer - o.matrikelnummer;
    }

    // Konstruktor
    public Student(String vorname, String name, int matrikelnummer) {
        super(vorname, name);
        this.matrikelnummer = matrikelnummer;
    }

    // toString
    @Override
    public String toString() {
        return vorname + " " + name + " " + matrikelnummer;
    }
}

// zweite relevante Klasse Boxer
class Boxer extends Person implements Comparable<Boxer> {
    protected int gewicht;

    // compareTo analog zu oben
    @Override
    public int compareTo(Boxer bo) {
        return this.gewicht - bo.gewicht;
    }

    // Konstruktor
    public Boxer(String vorname, String name, int gewicht) {
        super(vorname, name);
        this.gewicht = gewicht;
    }

    // toString
    @Override
    public String toString() {
        return vorname + " " + name + " " + gewicht;
    }
}
