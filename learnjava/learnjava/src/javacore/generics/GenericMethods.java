package javacore.generics;

public class GenericMethods {

    public static void main(String[] args) {

        Integer[] nums = new Integer[] { 1, 2, 3, 4, 5 };
        if (isIn(3, nums)) {
            System.out.println("3 is present in nums.");
        }

        String[] strArr = new String[] { "a", "b", "c" };
        if (isIn("a", strArr)) {
            System.out.println("a is present in strArr");
        }

        // Below incompatible arguements won't compile.
        // isIn(7, strArr);
    }

    /*
     * Generic method to check if an object exists in an array.
     */
    private static <T extends Comparable<T>, V extends T> boolean isIn(T val, V[] arr) {
        for (int indx = 0; indx < arr.length; indx++) {
            if (val.equals(arr[indx])) {
                return true;
            }
        }

        return false;
    }
}
