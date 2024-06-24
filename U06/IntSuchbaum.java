package Java.Prog_2.U06;

import Java.Prog_2.U05.A03.*;

public class IntSuchbaum {
    private Node root;

    private class Node {
        private int data = -1;
        private Node left, right;

        public Node(int i) {
            data = i;
        }
    }

    public IntSuchbaum() {
        root = new Node(-1);
    }

    public boolean isEmpty() {
        return root.data == -1;
    }

    public void insert(int i) {
        if (isEmpty()) {
            root = new Node(i);
        } else {
            if (!contains(i)) { // wir wollen keine Zahlen doppelt haben
                Node tmp = root;
                Node parent = null; // nutzen wir um später zu wissen welches das Verzeichnis vor dem leeren
                                    // Verzeichnis ist
                while (tmp != null) {
                    parent = tmp;
                    if (tmp.data > i) {
                        tmp = tmp.left;
                    } else if (tmp.data < i) {
                        tmp = tmp.right;
                    } else {
                        return;
                    }
                }
                // hier nutzen wir es. Es wird in der Verzweigung ein neuer "Ast" erstellt
                if (parent.data > i) {
                    parent.left = new Node(i);
                } else {
                    parent.right = new Node(i);
                }
            }
        }
    }

    public boolean contains(int i) {
        Node tmp = root;
        while (tmp != null) {
            if (tmp.data == i) {
                return true;
            } else if (i < tmp.data) {
                tmp = tmp.left;
            } else {
                tmp = tmp.right;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return toString(root);
    }

    private String toString(Node n) {
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

    // gibt die Tiefe des Baumes an. Die Wurzel zählt nicht mit: Ist nur ein Element
    // im Baum => höhe = 0
    public int hoehe() {
        return hoehe(root);
    }

    // höhe ist bei einem Eintrage 0, erst bei einem Zweig ist die höhe 1
    private int hoehe(Node e) {
        if (e == null || e.data == -1) {
            return -1;
        }
        int leftheight = hoehe(e.left);
        int rightheight = hoehe(e.right);
        return Math.max(leftheight, rightheight) + 1;
    }

    // gibt die Anzahl der Elemente in dem Baum an
    public int size() {
        return 1 + size(root);
    }

    private int size(Node n) {
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

    // geht von oben nach unten und von links nach rechts
    public FolgeMitDynarr<Integer> preorder() {
        FolgeMitDynarr<Integer> dinArr = new FolgeMitDynarr<>();
        preorder(root, dinArr);
        return dinArr;
    }

    private void preorder(Node n, FolgeMitDynarr<Integer> arr) {
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

    // geht wortwörtlich von links nach rechts
    public FolgeMitDynarr<Integer> inorder() {
        FolgeMitDynarr<Integer> dinArr = new FolgeMitDynarr<>();
        inorder(root, dinArr);
        return dinArr;
    }

    private void inorder(Node n, FolgeMitDynarr<Integer> arr) {
        for (int i = 0; i == 0; i++) { // analog zu preorder
            if (n == null) {
                break;
            }
            inorder(n.left, arr);
            arr.insert(n.data);
            inorder(n.right, arr);
        }
    }

    // geht links von unten nach oben, dann rechts von unten nach obern und ganz zum
    // Schluss die Wurzel
    public FolgeMitDynarr<Integer> postorder() {
        FolgeMitDynarr<Integer> dinArr = new FolgeMitDynarr<>();
        postorder(root, dinArr);
        return dinArr;
    }

    private void postorder(Node n, FolgeMitDynarr<Integer> arr) {
        for (int i = 0; i == 0; i++) { // ebenfalls analog zu preorder
            if (n == null) {
                break;
            }
            postorder(n.left, arr);
            postorder(n.right, arr);
            arr.insert(n.data);
        }
    }

    // ich nutze eine queue um die überigen daten aus den Zweigen drunter zu
    // speichern und nach abzuarbeiten
    public FolgeMitDynarr<Integer> breitensuche() {
        FolgeMitDynarr<Integer> dinArr = new FolgeMitDynarr<>();
        FolgeMitDynarr<Node> queue = new FolgeMitDynarr<>();
        if (isEmpty()) {
            System.out.println("Leerer Baum");
            return dinArr;
        }
        queue.insert(root);
        while (!queue.isEmpty()) {
            Node tmp = queue.remove();
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

    // es ist nicht optimiert. In den meisten Fällen kommt nach dem Remove eine
    // folgende EVL
    public void remove(int x) {
        if (contains(x)) {
            Node tmp = root;
            FolgeMitDynarr<Integer> numbers = new FolgeMitDynarr<>();
            // fall das der Wert in der Root drin steht
            if (root.data == x) {
                // funktion analog zu unten
                if (root.left != null && root.right != null) {
                    preorder(root.right, numbers);
                    root = root.left;

                    while (!numbers.isEmpty()) {
                        insert(numbers.remove());
                    }
                } else if (root.left != null) { // es gibt nur links einen Zweig
                    root = root.left;
                } else if (root.right != null) { // es gibt nur rechts einen Zweig
                    root = root.right;
                } else {
                    root = null;
                }
                // Fall das der Wert tiefer in dem Baum steht
            } else {
                while (tmp != null) {
                    // if in den zweigen der Wert drin steht
                    if (tmp.left.data == x) {
                        // falls es danach noch eine Zweig nach links und nach rechts gibt
                        if (tmp.left.left != null && tmp.left.right != null) {
                            // alle werten vom linken Zweig nach dem Eintrag
                            preorder(tmp.left.right, numbers);
                            tmp.left = tmp.left.left;

                            // alle Werte in der Liste werden neu eingefügt
                            while (!numbers.isEmpty()) {
                                insert(numbers.remove());
                            }
                        } else if (tmp.left.left != null) { // es gibt nur links einen Zweig
                            tmp.left = tmp.left.left;
                        } else if (tmp.left.right != null) { // es gibt nur rechts einen Zweig
                            tmp.left = tmp.left.right;
                        } else {
                            tmp.left = null;
                        }
                        break;
                    } else if (tmp.right.data == x) {
                        // falls es danach noch eine Zweig nach links und nach rechts gibt
                        if (tmp.right.left != null && tmp.right.right != null) {
                            // alle werten vom linken Zweig nach dem Eintrag
                            preorder(tmp.right.right, numbers);
                            tmp.right = tmp.right.left;

                            // alle Werte in der Liste werden neu eingefügt
                            while (!numbers.isEmpty()) {
                                insert(numbers.remove());
                            }
                        } else if (tmp.right.left != null) { // es gibt nur links einen Zweig
                            tmp.right = tmp.right.left;
                        } else if (tmp.right.right != null) { // es gibt nur rechts einen Zweig
                            tmp.right = tmp.right.right;
                        } else {
                            tmp.right = null;
                        }
                        break;
                    } else if (x < tmp.data) {
                        tmp = tmp.left;
                    } else {
                        tmp = tmp.right;
                    }
                }
            }
        }
    }
}