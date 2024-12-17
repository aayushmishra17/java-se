package javacore.generics;

public class Generics {

    public static void main(String[] args) {
        Generics obj = new Generics();

        Integer[] arr = new Integer[] { 1, 2, 3, 4, 5 };
        MyGenericArray<Integer> genericArr = obj.new MyGenericArray<>(arr);
        genericArr.printArrElements();

        String[] strArr = new String[] { "a", "b", "c" };
        MyGenericArray<String> genericStrArr = obj.new MyGenericArray<>(strArr);
        genericStrArr.printArrElements();
    }

    private class MyGenericArray<T> {

        T[] _arr;

        public MyGenericArray(T[] inp) {
            _arr = inp;
        }

        public void printArrElements() {
            for (int indx = 0; indx < _arr.length; indx++) {
                System.out.println(_arr[indx]);
            }
        }

    }
}
