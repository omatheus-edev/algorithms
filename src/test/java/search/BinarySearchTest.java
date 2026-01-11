package search;

import codes.matheus.search.BinarySearch;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the static BinarySearch implementation.
 */
public final class BinarySearchTest {
    @Test
    void testFindIntegerInMiddle() {
        @NotNull Integer[] numbers = {10, 20, 30, 40, 50};
        int index = BinarySearch.find(numbers, 30);
        assertEquals(2, index, "The index of 30 should be 2");
    }

    @Test
    void testFindStringAtBeginning() {
        @NotNull String[] fruits = {"Apple", "Banana", "Cherry", "Date"};
        int index = BinarySearch.find(fruits, "Apple");
        assertEquals(0, index, "The index of 'Apple' should be 0");
    }

    @Test
    void testFindElementAtEnd() {
        @NotNull Integer[] numbers = {1, 3, 5, 7, 9, 11};
        int index = BinarySearch.find(numbers, 11);
        assertEquals(5, index, "The index of 11 should be 5");
    }

    @Test
    void testElementNotFound() {
        @NotNull Integer[] numbers = {2, 4, 6, 8, 10};
        int index = BinarySearch.find(numbers, 5);
        assertEquals(-1, index, "Should return -1 for a missing element");
    }

    @Test
    void testSingleElementArray() {
        @NotNull Integer[] numbers = {42};
        assertEquals(0, BinarySearch.find(numbers, 42));
        assertEquals(-1, BinarySearch.find(numbers, 7));
    }

    @Test
    void testFindWithCustomRange() {
        @NotNull Integer[] numbers = {10, 20, 30, 40, 50, 60, 70, 80};

        // Search for 70 only between indices 4 and 7 (should find it)
        int indexFound = BinarySearch.find(numbers, 70, 4, 7);
        assertEquals(6, indexFound);

        // Search for 70 only between indices 0 and 3 (should NOT find it)
        int indexNotFound = BinarySearch.find(numbers, 70, 0, 3);
        assertEquals(-1, indexNotFound);
    }

    @Test
    void testGenericTypeSupport() {
        @NotNull Double[] decimals = {1.1, 2.2, 3.3, 4.4};
        assertEquals(1, BinarySearch.find(decimals, 2.2));
    }

    @Test
    void testPrimitiveInt() {
        int[] numbers = {100, 200, 300, 400};
        assertEquals(1, BinarySearch.find(numbers, 200));
        assertEquals(-1, BinarySearch.find(numbers, 999));
    }

    @Test
    void testPrimitiveLong() {
        long[] numbers = {1000L, 2000L, 3000L, 4000L};
        assertEquals(3, BinarySearch.find(numbers, 4000L));
    }

    @Test
    void testPrimitiveFloat() {
        float[] numbers = {1.5f, 2.5f, Float.POSITIVE_INFINITY};
        assertEquals(1, BinarySearch.find(numbers, 2.5f));
        assertEquals(2, BinarySearch.find(numbers, Float.POSITIVE_INFINITY));
    }

    @Test
    void testFloatNaN() {
        float[] numbers = {1.1f, 2.2f, Float.NaN};
        assertEquals(2, BinarySearch.find(numbers, Float.NaN));
    }

    @Test
    void testPrimitiveDouble() {
        double[] numbers = {0.1, 0.2, 0.5, 0.9};
        assertEquals(2, BinarySearch.find(numbers, 0.5));
    }
}
