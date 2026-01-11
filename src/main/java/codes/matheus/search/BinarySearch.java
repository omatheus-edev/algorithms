package codes.matheus.search;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

/**
 * A search algorithm known as binary search, which looks for the position of a value within an array.
 *
 * <p>Worst-case performance O(log n) Best-case performance O(1) Average performance O(log n) Worst-case space complexity O(1)</p>
 *
 * @author Matheus Sousa (https://github.com/omatheus-edev)
 */
public final class BinarySearch {
    private BinarySearch() {
        throw new UnsupportedOperationException("this class cannot be instantiated");
    }


    /**
     * This method implements a binary search algorithm
     *
     * @param array a sorted array
     * @param key the key to search in array
     * @return the index of key in the array or -1 if not found
     *
     * */
    public static  <T extends Comparable<@NotNull T>> int find(@NotNull T[] array, @NotNull T key) {
        return find(array, key, 0, array.length - 1);
    }

    /**
     * This method implements a binary search algorithm with a range in array
     *
     * @param array a sorted array
     * @param key the key to search in array
     * @param left it's the start of the range.
     * @param right it's the end of the range
     * @return the index of key in the array or -1 if not found
     *
     * */
    public static  <T extends Comparable<@NotNull T>> int find(@NotNull T[] array, @NotNull T key, @Range(from = 0, to = Integer.MAX_VALUE) int left, @Range(from = 0, to = Integer.MAX_VALUE) int right) {
        while (left <= right) {
            int median = (left + right) >>> 1;
            int compute = key.compareTo(array[median]);

            if (compute == 0) {
                return median;
            } else if (compute > 0) {
                left = median + 1;
            } else {
                right = median - 1;
            }
        }
        return -1;
    }

    /**
     * This method implements a binary search algorithm for primitive int types, in order to optimize the search.
     * This method uses a primitive array of type int for better performance.
     *
     * @param array a sorted int array
     * @param key the key to search in array
     * @return the index of key in the array or -1 if not found
     *
     * */
    public static int find(int[] array, @Range(from = 0, to = Integer.MAX_VALUE) int key) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int median = (left + right) >>> 1;
            if (array[median] == key) {
                return median;
            } else if (array[median] < key) {
                left = median + 1;
            } else {
                right = median - 1;
            }
        }
        return -1;
    }


    /**
     * This method implements a binary search algorithm for primitive long types, in order to optimize the search.
     * This method uses a primitive array of type long for better performance.
     *
     * @param array a sorted long array
     * @param key the key to search in array
     * @return the index of key in the array or -1 if not found
     *
     * */
    public static int find(long[] array, @Range(from = 0, to = Long.MAX_VALUE) long key) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int median = (left + right) >>> 1;
            if (array[median] == key) {
                return median;
            } else if (array[median] < key) {
                left = median + 1;
            } else {
                right = median - 1;
            }
        }
        return -1;
    }


    /**
     * This method implements a binary search algorithm for primitive double types, in order to optimize the search.
     * This method uses a primitive array of type double for better performance.
     *
     *
     * @param array a sorted double array
     * @param key the key to search in array
     * @return the index of key in the array or -1 if not found
     *
     * */
    public static int find(double[] array, @Range(from = 0, to = Long.MAX_VALUE) double key) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int median = (left + right) >>> 1;
            int compute = Double.compare(key, array[median]);

            if (compute == 0) {
                return median;
            }
            else if (compute > 0) {
                left = median + 1;
            } else {
                right = median - 1;
            }
        }
        return -1;
    }

    /**
     * This method implements a binary search algorithm for primitive float types, in order to optimize the search.
     * This method uses a primitive array of type float for better performance.
     *
     *
     * @param array a sorted float array
     * @param key the key to search in array
     * @return the index of key in the array or -1 if not found
     *
     * */
    public static int find(float[] array, @Range(from = 0, to = Long.MAX_VALUE) float key) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int median = (left + right) >>> 1;
            int compute = Float.compare(key, array[median]);

            if (compute == 0) {
                return median;
            }
            else if (compute > 0) {
                left = median + 1;
            } else {
                right = median - 1;
            }
        }
        return -1;
    }
}