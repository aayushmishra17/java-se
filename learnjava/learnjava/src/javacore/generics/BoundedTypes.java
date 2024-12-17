package javacore.generics;

public class BoundedTypes {

    public static void main(String[] args) {
        BoundedTypes obj = new BoundedTypes();

        Integer[] intArr = new Integer[] { 1, 2, 3, 4, 5 };
        MyGenericArray<Integer> genIntArr = obj.new MyGenericArray<>(intArr);
        genIntArr.printArrElements();
        genIntArr.printAverage();

        Double[] doubleArr = new Double[] { 1.0, 2.0, 3.0, 4.0, 5.0 };
        MyGenericArray<Double> genDoubleArr = obj.new MyGenericArray<>(doubleArr);
        genDoubleArr.printArrElements();
        genDoubleArr.printAverage();

        // Below code would throw a bound compile error.

        // String[] strArr = new String[] {"a", "b", "c"};
        // MyGenericArray<String> genStrArr = obj.new MyGenericArray<>(strArr);
        // genStrArr.printArrElements();
        // genStrArr.printAverage();
    }

    /*
     * Upper bounds to a Number type.
     */
    private class MyGenericArray<T extends Number> {

        T[] _arr;

        public MyGenericArray(T[] inp) {
            _arr = inp;
        }

        public void printArrElements() {
            for (int indx = 0; indx < _arr.length; indx++) {
                System.out.println(_arr[indx]);
            }
        }

        public double printAverage() {
            double sum = 0.0;

            for (int indx = 0; indx < _arr.length; indx++) {
                sum += _arr[indx].doubleValue();
            }

            return sum / _arr.length;
        }

    }

}
