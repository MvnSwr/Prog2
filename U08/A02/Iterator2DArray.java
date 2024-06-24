package Java.Prog_2.U08.A02;

import java.util.ArrayList;
import java.util.Iterator;

public class Iterator2DArray<T> implements Iterable<T> {
    T[] data;
    int beginn, end;

    @SuppressWarnings("unchecked")
    // Wir schreiben alles in eine Liste um es nacher in ein 1d Array
    // umzuschreiben(einfachere iteration)
    public Iterator2DArray(T[][] arr) {
        ArrayList<T> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 != 0) {
                // i ist ungerade, es wird von hinten nach vorne iteriert
                for (int j = arr[i].length - 1; j >= 0; j--) {
                    list.add(arr[i][j]);
                }
            } else {
                // i ist gerade, es wird von vorne nach hinten iteriert
                for (T e : arr[i]) {
                    list.add(e);
                }
            }
        }

        data = (T[]) new Object[list.size()];
        int i = 0;
        while (!list.isEmpty()) {
            data[i++] = list.remove(0);
        }
    }

    private class array2dIterator implements Iterator<T> {
        int i = 0;

        @Override
        public boolean hasNext() {
            return i < data.length;
        }

        @Override
        public T next() {
            return data[i++];
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new array2dIterator();
    }
}

class testclass {
    public static void main(String[] args) {
        Integer[][] intArr = { { 1, 2, 3, 4 }, { 5, 6, 7 }, { 8, 9, 0, 11, 12, 13 }, { 14, 15, 16 } };
        Iterator2DArray<Integer> iteratorArr = new Iterator2DArray<>(intArr);
        for (Integer num : iteratorArr) {
            System.out.println(num);
        }
    }
}
