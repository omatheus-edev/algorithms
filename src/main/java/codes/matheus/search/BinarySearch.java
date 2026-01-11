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
}
