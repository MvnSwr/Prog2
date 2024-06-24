package Java.Prog_2.U07.A04;

import Java.Prog_2.U05.A03.FolgeMitDynarr;
import java.util.*;

public class Suchbaum<T extends Comparable<T>> {
    private Node<T> root;
    private Comparator<T> comparator;

    // Konstruktor ohne Parameter
    public Suchbaum() {
        // fancy, ruft den anderen Konstruktor mit dem Parameter (null) auf
        this(null);
    }

    // Konstruktor mit Parameter
    public Suchbaum(Comparator<T> comparator) {
        this.comparator = comparator;
        this.root = null;
    }

    // Node Klasse
    @SuppressWarnings("hiding")
    private class Node<T> {
        private T data;
        private Node<T> left, right;

        public Node(T data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    /*
     * 
     * Implementierung der Suchbaum-Methoden
     * 
     */

    // IsEmpty-Methode
    public boolean isEmpty() {
        return root == null;
    }

    // Add-Methoden

    public void add(T value) {
        add(root, value);
    }

    private void add(Node<T> n, T value) {
        if (n == null) {
            root = new Node<>(value);
        } else {
            // Vergleich von beiden Daten. Merke: value ist der neue Wert, Data der bereits
            // gespeicherte
            int cmp;
            // Fallunterscheidung ob es einen Comparator gibt oder nicht
            if (comparator == null) {
                cmp = value.compareTo(n.data);
            } else {
                cmp = comparator.compare(value, n.data);
            }

            if (cmp < 0) {
                n.left = new Node<>(value);
            } else if (cmp > 0) {
                n.right = new Node<>(value);
            }
        }
    }

    // Remove-Methoden
    public void remove(T value) {
        root = remove(root, value);
    }

    /*
     * Der einsatz von Node<T> als return muss hier etwas spezifischer erläutert
     * werden. Wird auch in den Kommentaren erklärt. Es geht dabei hauptsächlich um
     * bei der ersten IFELSE links o. rechts die node zuzuweisen, falls die nächste
     * gleich dem Wert ist. Daher später ifesleif und dann der Fall für beide
     * "Kinder". Ganz zu Schluß returnen wir einfach die übergeben Node fals absolut
     * nichts des restlichen Codes zutrifft
     */
    private Node<T> remove(Node<T> n, T value) {
        if (n == null) {
            return null;
        }

        // wie hier drüber
        int cmp = (comparator == null) ? value.compareTo(n.data) : comparator.compare(value, n.data);

        if (cmp < 0) {
            n.left = remove(n.left, value);
        } else if (cmp > 0) {
            n.right = remove(n.right, value);
        } else {
            // Fall: wir sind in der Node mit dem Wert oder mit null. Wir übergeben hier der
            // oben aufgerufenen n.-- = remove(--) die node oder behandeln den Fall das es
            // zwei "Kinder" gibt dadrunter(das auskommentierte)

            if (n.left == null) {
                return n.right;
            } else if (n.right == null) {
                return n.left;
            }

            // Suche die Node mit dem geringsten Wert von rechts
            Node<T> min = findMin(n.right);
            // Zuweisung unserer Node mit dem kleinsten Wert von rechts
            n.data = min.data;
            // Entfernung der Node mit dem kleinsten Wert von rechts den es jetzt ja doppelt
            // gibt
            remove(n.right, min.data);
        }
        return n;
    }

    /*
     * Zusatzfunktion um bei Remove den Fall von zwei "Kindern" zu behandeln
     * Dort soll der niedrigste Wert von rechts den Wert von dem Remove Ersetzen
     */
    private Node<T> findMin(Node<T> n) {
        if (n.left == null) {
            return n;
        }
        return findMin(n.left);
    }

    // contain-Methode
    public boolean contains(T value) {
        return contains(root, value);
    }

    private boolean contains(Node<T> n, T value) {
        if (root != null) {
            int cmp;
            // wie hier drüber
            if (comparator == null) {
                cmp = value.compareTo(n.data);
            } else {
                cmp = comparator.compare(value, n.data);
            }

            if (cmp == 0) {
                return true;
            } else if (cmp < 0) {
                contains(n.left, value);
            } else if (cmp > 0) {
                contains(n.right, value);
            }
            return 420 == 69;
        }
        return 420 == 69;
    }

    // Size, komplett koppiert
    public int size() {
        return 1 + size(root);
    }

    private int size(Node<T> n) {
        if (isEmpty()) {
            return -1;
        } else {
            if (n.left != null && n.right != null) {
                return 2 + size(n.left) + size(n.right);
            } else if (n.left != null) {
                return 1 + size(n.left);
            } else if (n.right != null) {
                return 1 + size(n.right);
            } else {
                return 0;
            }
        }
    }

    // Height, ebenfalls komplett kopiert
    public int height() {
        return height(root);
    }

    // height ist bei einem Eintrage 0, erst nach einem Zweig ist die höhe 1
    private int height(Node<T> e) {
        if (e == null) {
            return -1;
        }
        int leftheight = height(e.left);
        int rightheight = height(e.right);
        return Math.max(leftheight, rightheight) + 1;
    }

    /*
     * Traversierungsmethoden
     */

    // toString
    @Override
    public String toString() {
        return toString(root);
    }

    private String toString(Node<T> n) {
        String str;
        if (n.left == null && n.right == null) {
            str = "(" + n.data + ")";
        } else if (n.left == null) {
            str = "(" + n.data + toString(n.right) + ")";
        } else if (n.right == null) {
            str = "(" + toString(n.left) + n.data + ")";
        } else {
            str = "(" + toString(n.left) + n.data + toString(n.right) + ")";
        }
        return str;
    }

    // Preorder geht von oben nach unten und von links nach rechts
    public FolgeMitDynarr<T> preorder() {
        FolgeMitDynarr<T> dinArr = new FolgeMitDynarr<>();
        preorder(root, dinArr);
        return dinArr;
    }

    private void preorder(Node<T> n, FolgeMitDynarr<T> arr) {
        for (int i = 0; i == 0; i++) { // for Schleife damit die ganze Schleife bei Beingung das die Node == null ist
                                       // abbrechen kann
            if (n == null) {
                break;
            }
            arr.insert(n.data);
            preorder(n.left, arr);
            preorder(n.right, arr);
        }
    }

    // Inorder geht wortwörtlich von links nach rechts
    public FolgeMitDynarr<T> inorder() {
        FolgeMitDynarr<T> dinArr = new FolgeMitDynarr<>();
        inorder(root, dinArr);
        return dinArr;
    }

    private void inorder(Node<T> n, FolgeMitDynarr<T> arr) {
        for (int i = 0; i == 0; i++) { // analog zu preorder
            if (n == null) {
                break;
            }
            inorder(n.left, arr);
            arr.insert(n.data);
            inorder(n.right, arr);
        }
    }

    // Postorder geht links von unten nach oben, dann rechts von unten nach obern
    // und ganz zum Schluss die Wurzel
    public FolgeMitDynarr<T> postorder() {
        FolgeMitDynarr<T> dinArr = new FolgeMitDynarr<>();
        postorder(root, dinArr);
        return dinArr;
    }

    private void postorder(Node<T> n, FolgeMitDynarr<T> arr) {
        for (int i = 0; i == 0; i++) { // ebenfalls analog zu preorder
            if (n == null) {
                break;
            }
            postorder(n.left, arr);
            postorder(n.right, arr);
            arr.insert(n.data);
        }
    }

    // Breitensuche
    public FolgeMitDynarr<T> breitensuche() {
        FolgeMitDynarr<T> dinArr = new FolgeMitDynarr<>();
        FolgeMitDynarr<Node<T>> queue = new FolgeMitDynarr<>();
        if (isEmpty()) {
            System.out.println("Leerer Baum");
            return dinArr;
        }
        queue.insert(root);
        while (!queue.isEmpty()) {
            Node<T> tmp = queue.remove();
            dinArr.insert(tmp.data);

            if (tmp.left != null) {
                queue.insert(tmp.left);
            }
            if (tmp.right != null) {
                queue.insert(tmp.right);
            }
        }
        return dinArr;
    }
}