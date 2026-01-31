package codes.matheus.datastructures.list;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public final class SinglyLinkedListTest {
    private final @NotNull SinglyLinkedList<String> list = new SinglyLinkedList<>();

    @Test
    void testAddFirstAndLast() {
        list.add("Mid");
        list.addFirst("Begin");
        list.add("Last");

        assertEquals("Begin", list.get(0));
        assertEquals("Mid", list.get(1));
        assertEquals("Last", list.get(2));
    }

    @Test
    void testAddAtIndex() {
        list.add("A");
        list.add("C");
        list.add(1, "B");

        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
    }

    @Test
    void testIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        list.add("Test");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(5));
    }

    @Test
    void testContains() {
        list.add("Java");
        list.add("C#");

        assertTrue(list.contains("Java"));
        assertFalse(list.contains("Python"));
    }

    @Test
    void testRemoveByValue() {
        list.add("First");
        list.add("Second");
        list.add("Third");

        assertTrue(list.remove("Second"));
        assertEquals(2, list.get(1).equals("Third") ? 2 : -1);

        assertTrue(list.remove("Third"));
        list.add("new final");
        assertEquals("new final", list.get(1));
    }

    @Test
    void testRemoveByIndex() {
        list.add("A");
        list.add("B");
        list.add("C");

        assertTrue(list.remove(1));
        assertEquals("C", list.get(1));
        assertEquals("A", list.get(0));
    }

    @Test
    void testClearListBehavior() {
        list.add("unique");
        assertTrue(list.remove(0));

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        list.add("new");
        assertEquals("new", list.get(0));
    }
}