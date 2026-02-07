package codes.matheus.datastructures.list;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public final class MidLinkedListTest {
    private final @NotNull MidLinkedList<String> list = new MidLinkedList<>();

    @Nested
    class InsertionTests {

        @Test
        void testAddLast() {
            list.add("A");
            list.add("B");
            list.add("C");

            assertEquals(3, list.size());
            assertEquals("[A, B, C]", list.toString());
        }

        @Test
        void testAddFirst() {
            list.addFirst("C");
            list.addFirst("B");
            list.addFirst("A");

            assertEquals(3, list.size());
            assertEquals("A", list.get(0));
            assertEquals("[A, B, C]", list.toString());
        }

        @Test
        void testAddAtIndex() {
            list.add("A"); // Index 0
            list.add("C"); // Index 1
            list.add("B", 1); // Insert B at Index 1

            assertEquals(3, list.size());
            assertEquals("B", list.get(1));
            assertEquals("[A, B, C]", list.toString());
        }
    }

    @Nested
    class SearchTests {

        @Test
        void testGet() {
            list.add("0"); // Head
            list.add("1");
            list.add("2"); // Mid
            list.add("3");
            list.add("4"); // Tail

            assertAll(
                    () -> assertEquals("0", list.get(0), "Head access failed"),
                    () -> assertEquals("2", list.get(2), "Mid access failed"),
                    () -> assertEquals("4", list.get(4), "Tail access failed"),
                    () -> assertEquals("1", list.get(1), "Near-head access failed")
            );
        }

        @Test
        void testIndexOf() {
            list.add("First");
            list.add("Second");
            list.add("Third");

            assertEquals(0, list.indexOf("First"));
            assertEquals(1, list.indexOf("Second"));
            assertEquals(2, list.indexOf("Third"));
            assertEquals(-1, list.indexOf("Non-existent"));
        }

        @Test
        void testOutOfBounds() {
            list.add("Value");
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(5));
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        }
    }

    @Nested
    class DeletionTests {

        @Test
        void testRemoveByIndex() {
            list.add("A");
            list.add("B");
            list.add("C");

            assertTrue(list.remove(1)); // Remove "B"
            assertEquals(2, list.size());
            assertEquals("[A, C]", list.toString());
        }

        @Test
        void testRemoveByValue() {
            list.add("X");
            list.add("Y");
            list.add("Z");

            assertTrue(list.remove("Y"));
            assertFalse(list.contains("Y"));
            assertEquals(2, list.size());
        }

        @Test
        void testRemoveLastElement() {
            list.add("Only One");
            assertTrue(list.remove(0));
            assertTrue(list.isEmpty());
            assertEquals(0, list.size());
        }
    }

    @Nested
    class UtilityTests {

        @Test
        void testMidPointerStability() {
            // 1 element: mid = 0
            list.add("0");
            assertEquals("0", list.get(list.size()/2));

            // 2 elements: mid = 2/2 = 1
            list.add("1");

            // 3 elements: mid = 3/2 = 1
            list.add("2");
            assertEquals("1", list.get(1));

            // 4 elements: mid = 4/2 = 2
            list.add("3");
            assertEquals("2", list.get(2));
        }

        @Test
        void testIsEmpty() {
            assertTrue(list.isEmpty());
            list.add("Data");
            assertFalse(list.isEmpty());
        }
    }

}