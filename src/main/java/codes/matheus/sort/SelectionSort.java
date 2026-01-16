package codes.matheus.sort;

import org.jetbrains.annotations.NotNull;

/**
 * A simple Selection Sort algorithm
 *
 * <p>Time Complexity:
 * - Best case: O(n^2)
 * - Average case: O(n^2)
 * - Worst case: O(n^2)</p>
 *
 * <p>Space Complexity: O(1) – in-place sorting.</p>
 *
 * @author Matheus Sousa (https://github.com/omatheus-edev)
 */
public final class SelectionSort {
    /**
     * Sorts a generic array.
     *
     * @param array Array that will be sorted
     * @param <T> the type of elements in the array extends comparable
     */
    public static <T extends Comparable<T>> void sort(@NotNull T[] array) {
        if (array == null || array.length < 2) return;

        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < array.length; j++) {
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                SortUtils.swap(array, i, minIndex);
            }
        }
    }
}
