package codes.matheus.datastructures.stack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Linked Stack is an implementation using Linked List logic.
 * <p>This structure follows the LIFO (Last-In-First-Out) principle.
 * By managing elements from the head of the internal list, it ensures that
 * both insertion (push) and removal (pop) operations are performed in
 * constant time O(1).</p>
 *
 * @author Matheus Sousa (https://github.com/omatheus-edev)
 * @param <T> The type of elements in the stack
 */
public final class LinkedStack<T> implements Stack<T> {
    private @Nullable Node<T> head;
    private @Nullable Node<T> tail;
    private int size = 0;

    /**
     * Constructor parameterized
     *
     * @param value Value to be inserted
     */
    public LinkedStack(@NotNull T value) {
        this.head = new Node<>(value);
        this.tail = head;
        size++;
    }

    /**
     * Constructor
     */
    public LinkedStack() {
        this.head = null;
        this.tail = null;
    }

    @Override
    public boolean push(@NotNull T value) {
        @NotNull Node<T> node = new Node<>(value);
        if (isEmpty()) {
            this.head = node;
            this.tail = node;
        } else {
            node.next = head;
            this.head = node;
        }
        size++;
        return true;
    }

    @Override
    public @Nullable T pop() {
        if (isEmpty()) return null;
        @NotNull T value = head.value;
        head = head.next;
        size--;

        if (size == 0) {
            tail = null;
        }
        return value;
    }

    @Override
    public @Nullable T peek() {
        return head != null ? head.value : null;
    }

    @Override
    public @Nullable T peekLast() {
        return tail != null ? tail.value : null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
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
        return result.append("]").toString();
    }

    /**
     * This class represents a node in the Linked Stack
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
