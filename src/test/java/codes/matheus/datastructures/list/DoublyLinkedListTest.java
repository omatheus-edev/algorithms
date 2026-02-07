package codes.matheus.datastructures.list;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public final class DoublyLinkedListTest {
    private final @NotNull DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

    @Test
    void testAddFirst() {
        list.addFirst(1);
        assertEquals(1, list.size());
        assertEquals(1, list.get(0));

        list.addFirst(2);
        assertEquals(2, list.size());
        assertEquals(2, list.get(0));
        assertEquals(1, list.get(1));
    }

    @Test
    void testAddLast() {
        list.add(1);
        assertEquals(1, list.size());
        assertEquals(1, list.get(0));

        list.add(2);
        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    void testAddAtIndex() {
        list.add(1);
        list.add(3);
        list.add(2, 1); // Insert 2 at index 1
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));

        // Add at beginning
        list.add(0, 0);
        assertEquals(4, list.size());
        assertEquals(0, list.get(0));
        assertEquals(1, list.get(1));

        // Add at end
        list.add(4, 4);
        assertEquals(5, list.size());
        assertEquals(4, list.get(4));

        // Invalid indices
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(5, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(5, 6));
    }

    @Test
    void testGet() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));

        // Invalid indices
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
    }

    @Test
    void testGetEmptyList() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    void testRemoveByValue() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.remove(2));
        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));

        assertTrue(list.remove(1)); // Remove head
        assertEquals(1, list.size());
        assertEquals(1, list.get(0));

        assertFalse(list.remove(3)); // Remove tail (and last element)
        assertEquals(1, list.size());

        assertFalse(list.remove(4)); // Non-existent
    }

    @Test
    void testRemoveByIndex() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.remove(1)); // Remove middle
        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));

        assertTrue(list.remove(0)); // Remove head
        assertEquals(1, list.size());
        assertEquals(3, list.get(0));

        assertTrue(list.remove(0)); // Remove tail (and last)
        assertEquals(0, list.size());

        assertFalse(list.remove(0)); // Invalid index on empty
        assertFalse(list.remove(-1));
        assertFalse(list.remove(1));
    }

    @Test
    void testSize() {
        assertEquals(0, list.size());
        list.add(1);
        assertEquals(1, list.size());
        list.add(2);
        assertEquals(2, list.size());
        list.remove(1);
        assertEquals(1, list.size());
    }

    @Test
    void testContains() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.contains(2));
        assertFalse(list.contains(4));

        list.remove(2);
        assertFalse(list.contains(3));

        // Empty list
        DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<>();
        assertFalse(emptyList.contains(1));
    }

    @Test
    void testContainsEdgeCases() {
        // Single element
        list.add(1);
        assertTrue(list.contains(1));

        // After removal
        list.remove(0);
        assertFalse(list.contains(1));
    }
}