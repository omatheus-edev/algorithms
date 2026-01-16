package sort;

import codes.matheus.sort.SelectionSort;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public final class SelectionSortTest {
    @Test
    void testSortingInteger() {
        @NotNull Integer[] array = {8, 11, 9, 3, 10};
        @NotNull Integer[] expected = {3, 8, 9, 10, 11};

        SelectionSort.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void testSortingString() {
        @NotNull String[] array = {"Driver", "Bus", "Moto", "Car"};
        @NotNull String[] expected = {"Bus", "Car", "Driver", "Moto"};

        SelectionSort.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void testEmptyAndSingle() {
        @NotNull Integer[] empty = {};
        @NotNull Integer[] single = {1};

        SelectionSort.sort(empty);
        SelectionSort.sort(single);

        assertEquals(0, empty.length);
        assertEquals(1, single[0]);
    }

    @Test
    void testDuplicates() {
        @NotNull Integer[] array = {3, 1, 2, 1, 3};
        @NotNull Integer[] expected = {1, 1, 2, 3, 3};

        SelectionSort.sort(array);

        assertArrayEquals(expected, array);
    }
}
