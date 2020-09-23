import java.util.ArrayList;
import java.util.List;

/**
 * Class to modify
 */
class ListMultiplicator {

    /**
     * Repeats original list content provided number of times
     *
     * @param list list to repeat
     * @param n    times to repeat, should be zero or greater
     */
    public static void multiply(List<?> list, int n) {


        if (n == 0) {
            list.clear();
        } else if (n > 1) {
            multiplyHelper(list, n);
        }

        // System.out.println(list);
    }

    private static <T> void multiplyHelper(List<T> list, int n) {
        List<T> list1 = new ArrayList<>(list);
        for (int i = 1; i < n; i++) {
            list.addAll(list1);
        }
    }

//    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<Integer>(List.of(1, 2, 3));
//
//        multiply(list,3);
//
//    }
}
