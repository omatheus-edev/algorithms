package codes.matheus.datastructures.stack;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

final class LinkedStackTest {
    private final @NotNull LinkedStack<String> stack = new LinkedStack<>();

    @Test
    void testPush() {
        assertTrue(stack.isEmpty());
        stack.push("C");
        stack.push("B");
        stack.push("A");

        assertEquals("C", stack.peekLast());
        assertEquals("A", stack.peek());
        assertEquals(3, stack.size());
    }

    @Test
    void testPoll() {
        assertNull(stack.pop());
        stack.push("D");
        stack.push("C");
        stack.push("B");
        stack.push("A");

        assertNotNull(stack.pop());
        assertEquals(3, stack.size());
        assertEquals("B", stack.peek());
        assertEquals("D", stack.peekLast());
    }
}