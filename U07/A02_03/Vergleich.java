package Java.Prog_2.U07.A02_03;

//util brauchen wir als Import für die Comparator/Comparable
import java.util.*;

//allgemeine Person-Klasse
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

// Sortierungsklasse für Nachname
class nameSort implements Comparator<Boxer> {

    // compare methode um unsere eigene Sort-Reihenfolge zu machen
    @Override
    public int compare(Boxer bo1, Boxer bo2) {
        return bo1.name.compareTo(bo2.name); // so um Strings zu sortieren
    }

    // folgend falls wir unsere eigene Reihenfolge für Int oder double z.B. machen
    /*
     * if(bo1.gewicht < bo2.gewicht){
     * return -1;
     * }else if(bo1.gewicht > bo2.gewicht){
     * return 1;
     * }else{
     * return 0;
     * }
     */

}

// Sortierungsklasse für Vorname
class vornameSort implements Comparator<Person> {

    @Override
    public int compare(Person bo1, Person bo2) {
        return bo1.vorname.compareTo(bo2.vorname);
    }
}
// Wir vergleichen zwar Boxer, jedoch auf Basis von Attributen, die eine Person
// hat. Boxer wird in compare zu Person aufgeleitet und so können wir
// theoretisch auch noch Personen/Studenten mit vergleichen

// Sollte es Probleme geben, 'Person' wieder durch 'Boxer' ersetzen

/*
 * 
 * 
 * A03
 * 
 * 
 */

// Comparator für die Namen

class ComparatorPersonVornameName implements Comparator<Person> {
    // Erst eine Sortierung nach Vornamen, dann nach Nachnamen => eig. Sortierung
    // nach Nachnamen, gibt es einen Nachnamen häufiger kommt noch die Sortierung
    // nach Vornamen
    @Override
    public int compare(Person p1, Person p2) {
        int x = p1.name.compareTo(p2.name);
        // compare return 0 wenn beide Namen gleich sind
        if (x != 0) {
            return x;
        }
        // Fall das die namen gleich sind
        return p1.vorname.compareTo(p2.vorname);
    }
}

// Comparator für das Gewicht der Boxer

class ComparatorBoxerGewicht implements Comparator<Boxer> {
    // Code habe ich oben bereits kommentiert
    @Override
    public int compare(Boxer bo1, Boxer bo2) {
        if (bo1.gewicht < bo2.gewicht) {
            return -1;
        } else if (bo1.gewicht > bo2.gewicht) {
            return 1;
        } else {
            return 0;
        }
    }
}

/*
 * 
 * 
 * MAIN METHODE
 * 
 * 
 */

public class Vergleich {

    public static void main(String[] args) {

        /*
         * Studentensortierung
         */

        ArrayList<Student> stdlist = new ArrayList<>();
        stdlist.add(new Student("Marvin", "Schönwälder", 123458));
        stdlist.add(new Student("Lara", "Wieloch", 123457));
        stdlist.add(new Student("Marcello", "Gehrmann", 123458));
        stdlist.add(new Student("Max", "Hoyer", 123456));
        stdlist.add(new Student("Sömki", "Hoyer", 12345));

        // Sortierung nach Matrikelnummer
        Collections.sort(stdlist);

        System.out.println("\nStudenten geordnet\n");
        for (Student std : stdlist) {
            System.out.println(std.toString());
        }

        /*
         * Boxersortierung
         */

        ArrayList<Boxer> bolist = new ArrayList<>();
        bolist.add(new Boxer("Michael", "Jackson", 75));
        bolist.add(new Boxer("Mike", "Tyson", 87));
        bolist.add(new Boxer("Muhammet", "Ali", 68));
        bolist.add(new Boxer("Logan", "Paul", 98));

        // Sortierung nach Gewicht (Analog zu oben)

        Collections.sort(bolist);

        System.out.println("\n\nBoxer sortiert nach Gewicht\n");
        for (Boxer bo : bolist) {
            System.out.println(bo.toString());
        }

        // Sortierung nach Vorname

        vornameSort vns = new vornameSort();
        Collections.sort(bolist, vns); // andere Methode mit unserer eigenen Reihenfolge

        System.out.println("\n\nBoxer sortiert nach Vornamen\n");
        for (Boxer bo : bolist) {
            System.out.println(bo.toString());
        }

        // Sortierung nach name

        nameSort ns = new nameSort();
        Collections.sort(bolist, ns); // andere Methode mit unserer eigenen Reihenfolge

        System.out.println("\n\nBoxer sortiert nach Namen\n");
        for (Boxer bo : bolist) {
            System.out.println(bo.toString());
        }

        /*
         * Personensortierung
         */

        ArrayList<Person> psnlist = new ArrayList<>();
        psnlist.add(new Person("Marvin", "Schönwälder"));
        psnlist.add(new Person("Martin", "Schönwälder"));
        psnlist.add(new Person("Harald", "Schönwälder"));
        psnlist.add(new Person("Sönke", "Hoyer"));
        psnlist.add(new Person("Max", "Hoyer"));
        psnlist.add(new Person("Max", "Meier"));

        // Sortierung nach Namen
        ComparatorPersonVornameName cpvn = new ComparatorPersonVornameName();
        Collections.sort(psnlist, cpvn);

        System.out.println("\n\nPersonen geordnet\n");
        for (Person psn : psnlist) {
            System.out.println(psn.toString());
        }

        /*
         * Boxersortierung A03
         */

        // Liste haben wir oben bereits definiert
        ComparatorBoxerGewicht cbg = new ComparatorBoxerGewicht();
        Collections.sort(bolist, cbg);

        System.out.println("\n\nBoxer sortiert nach Gewicht\n");
        for (Boxer bo : bolist) {
            System.out.println(bo.toString());
        }
    }
}