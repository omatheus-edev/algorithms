package codes.matheus.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * A simple QuickSort algorithm
 *
 *  <p>Time Complexity:
 *  Best case: O(n log n)
 *  Average case: O(n log n)
 *  Worst case: O(n^2)</p>
 *
 *  <p>Space Complexity: O(log n) – in-place sorting with recursive stack space.</p>
 *
 *  @author Matheus Sousa
 */
public final class QuickSort {
    private static final @NotNull Random random = new Random();

    /**
     * Sorts a generic array.
     *
     * @param array Array that will be sorted
     * @param <T> the type of elements in the array extends comparable
     */
    public static <T extends Comparable<T>> void sort(@NotNull T[] array) {
        if (array == null || array.length < 2) return;
        quickSort(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(@NotNull T[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private static <T extends Comparable<T>> int partition(@NotNull T[] array, int low, int high) {
        int randomIndex = random.nextInt(high - low + 1) + low;
        SortUtils.swap(array, randomIndex, high);
        @NotNull T pivot = array[high];

        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j].compareTo(pivot) < 0) {
                i++;
                SortUtils.swap(array, i, j);
            }
        }
        SortUtils.swap(array, i + 1, high);
        return i + 1;
    }
}
