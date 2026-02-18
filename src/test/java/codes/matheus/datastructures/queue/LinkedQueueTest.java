package codes.matheus.datastructures.queue;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

final class LinkedQueueTest {
    private final @NotNull LinkedQueue<Integer> queue = new LinkedQueue<>();

    @Test
    void testPush() {
        assertTrue(queue.isEmpty());
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);

        assertEquals(4, queue.size());
        assertEquals(1, queue.peek());
        assertEquals(4, queue.peekLast());
    }

    @Test
    void testPoll() {
        queue.push(1);
        queue.push(2);
        queue.pop();
        queue.push(3);
        queue.push(4);

        assertEquals(3, queue.size());
        assertEquals(2, queue.peek());
        System.out.println(queue);
    }
}