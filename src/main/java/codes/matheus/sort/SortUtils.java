package codes.matheus.sort;

import org.jetbrains.annotations.NotNull;

public final class SortUtils {
    private SortUtils() {
        throw new UnsupportedOperationException("this class cannot be instantiated");
    }

    /**
     * Swaps two elements at the given positions in an array.
     *
     * @param array the array in which to swap elements
     * @param i     the index of the first element to swap
     * @param j     the index of the second element to swap
     * @param <T>   the type of elements in the array
     */
    public static <T> void swap(@NotNull T[] array, int i, int j) {
        if (i != j && i >= 0 && j >= 0) {
            @NotNull T temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}
