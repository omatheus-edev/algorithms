package codes.matheus.datastructures.queue;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Linked Queue is an implementation using Linked List logic.
 * <p>This structure follows the FIFO (First-In-First-Out) principle.
 * By maintaining both head and tail pointers, it ensures that both
 * insertion (enqueue) and removal (dequeue) are highly efficient.</p>
 *
 * @author Matheus Sousa (https://github.com/omatheus-edev)
 * @param <T> The type of elements in the queue
 */
public final class LinkedQueue<T> implements Queue<T>{
    private @Nullable Node<T> head;
    private @Nullable Node<T> tail;
    private int size = 0;

    /**
     * Constructor
     */
    public LinkedQueue() {
        this.head = null;
        this.tail = null;
    }

    /**
     * Constructor parameterized
     *
     * @param value Value to be inserted
     */
    public LinkedQueue(@NotNull T value) {
        this.head = new Node<>(value);
        this.tail = head;
        size++;
    }

    @Override
    public boolean push(@NotNull T value) {
        @NotNull Node<T> node = new Node<>(value);
        if (isEmpty()) {
            this.head = node;
        } else {
            tail.next = node;
        }
        this.tail = node;
        size++;
        return true;
    }

    @Override
    public @Nullable T pop() {
        if (isEmpty()) {
            return null;
        }

        @NotNull T value = head.value;
        this.head = head.next;
        size--;

        if (size == 0) {
            tail = null;
        }
        return value;
    }

    @Override
    public @Nullable T peek() {
        return size == 0 ? null : head.value;
    }

    @Override
    public @Nullable T peekLast() {
        return size == 0 ? null : tail.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";

        @NotNull StringBuilder result = new StringBuilder("[");
        @Nullable Node<T> current = head;

        while (current != null) {
            result.append(current.value);
            if (current.next != null) {
                result.append(", ");
            }
            current = current.next;
        }
        result.append("]");
        return result.toString();
    }

    /**
     * This class represents a node in the Linked Queue
     * Each node has a reference to the next node.
     *
     * @author Matheus Sousa (https://github.com/omatheus-edev)
     * @param <T> The type of element in this node
     */
    private final static class Node<T> {
        @NotNull T value;
        private @Nullable Node<T> next;

        public Node(@NotNull T value) {
            this.value = value;
        }
    }
}