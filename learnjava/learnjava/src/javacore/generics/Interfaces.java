package javacore.generics;

public class Interfaces {

    public static void main(String[] args) {

        Interfaces obj = new Interfaces();

        Integer[] intArr = new Integer[] { 1, 2, 3, 4, 5 };
        Character[] charArr = new Character[] { 'a', 'b', 'c', 'd', 'e', 'f' };

        MinMaxClass<Integer> iObj = obj.new MinMaxClass<>(intArr);
        MinMaxClass<Character> cObj = obj.new MinMaxClass<>(charArr);
        iObj.minVal();
        iObj.maxVal();

        cObj.minVal();
        cObj.maxVal();

        MinMaxIntClass intClassObj = obj.new MinMaxIntClass();
        intClassObj.minVal();
        intClassObj.maxVal();
    }

    interface MinMax<T extends Comparable<T>> {
        T minVal();

        T maxVal();
    }

    class MinMaxClass<T extends Comparable<T>> implements MinMax<T> {
        T[] _arr;

        public MinMaxClass(T[] arr) {
            _arr = arr;
        }

        @Override
        public T minVal() {
            T minVal = _arr[0];

            for (int indx = 1; indx < _arr.length; indx++) {
                if (_arr[indx].compareTo(minVal) < 0) {
                    minVal = _arr[indx];
                }
            }

            return minVal;
        }

        @Override
        public T maxVal() {
            T maxVal = _arr[0];

            for (int indx = 1; indx < _arr.length; indx++) {
                if (_arr[indx].compareTo(maxVal) > 0) {
                    maxVal = _arr[indx];
                }
            }

            return maxVal;
        }
    }

    /*
     * If we implement a specific type of generic interface then the class doesn't
     * need to be generic.
     */
    class MinMaxIntClass implements MinMax<Integer> {

        @Override
        public Integer minVal() {
            return Integer.MIN_VALUE;
        }

        @Override
        public Integer maxVal() {
            return Integer.MAX_VALUE;
        }

    }

}
