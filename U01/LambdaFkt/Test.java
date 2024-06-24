package Java.Prog_2.U01.LambdaFkt;

public class Test {

    public static void main(String[] args) { // Lambda-Funktionen
        doSomething machMal = () -> { // machMal als Name um es später aufzurufen
            System.out.println("Klappts?"); // Definition der Methode aus dem Interface doSomething
        };
        machMal.say(); // name von oben + name der Methode aus dem Interface
    }
}