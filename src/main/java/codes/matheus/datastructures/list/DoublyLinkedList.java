package codes.matheus.datastructures.list;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

/**
 * This class represents a Doubly Linked List data structure.
 *
 * <p>A Doubly Linked List is a linear data structure consisting of nodes where each node
 * contains a value and two references (links): one to the next node and one to the
 * previous node in the sequence. The list tracks both a head and a tail node, allowing
 * for efficient traversal in both directions (forward and backward).</p>
 *
 * <p>Properties of a Doubly Linked List:
 * <ul>
 * <li>Each node holds references to both the next and the previous node.</li>
 * <li>The list is bidirectional; you can traverse from head to tail and tail to head.</li>
 * <li>The previous pointer of the head is null, and the next pointer of the tail is null.</li>
 * <li>Requires slightly more memory per node compared to a Singly Linked List due to the extra pointer.</li>
 * </ul>
 * This makes it ideal for applications requiring bidirectional navigation, such as browser history,
 * music playlists, or implementing Deques (Double Ended Queues).</p>
 *
 * <p>Common operations' complexity (where n is the number of nodes):
 * <ul>
 * <li>Insertion at the beginning: O(1).</li>
 * <li>Insertion at the end: O(1) (maintaining a tail pointer).</li>
 * <li>Deletion at the beginning: O(1).</li>
 * <li>Deletion at the end: O(1) (unlike Singly Linked List, which is O(n) for tail removal).</li>
 * <li>Search: O(n) in the worst case (though technically O(n/2) on average if optimizing direction).</li>
 * <li>Space complexity: O(n) to store the nodes and their dual references.</li>
 * </ul></p>
 *
 * @author Matheus Sousa (https://github.com/omatheus-edev)
 * @param <T> The type of elements in this list
 */
public final class DoublyLinkedList<T> {
    private @Nullable Node<T> head;
    private @Nullable Node<T> tail;
    @Range(from = 0, to = Integer.MAX_VALUE)
    private int size = 0;

    /**
     * Constructor
     *
     */
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    /**
     * Parameterized constructor
     *
     * @param head Value to be inserted in first element
     */
    public DoublyLinkedList(@NotNull T head) {
        this.head = new Node<>(head);
        this.tail = this.head;
    }

    /**
     * Adding a value of first node of list
     *
     * @param value Value to be inserted on first node
     */
    public void addFirst(@NotNull T value) {
        @NotNull Node<T> node = new Node<>(value);
        if (head == null) {
            this.head = node;
            this.tail = node;
        } else {
            node.next = head;
            head.previous = node;
            head = node;
        }
        size++;
    }

    /**
     * Adding a value of last node of list
     *
     * @param value Value to be inserted on first node
     */
    public void add(@NotNull T value) {
        @NotNull Node<T> node = new Node<>(value);
        if (tail == null) {
            head = node;
        } else {
            tail.next = node;
            node.previous = tail;
        }
        tail = node;
        size++;
    }

    /**
     * Adding a value to specific index on list
     *
     * @param index Index where the value will be inserted.
     * @param value Value to be inserted
     */
    public void add(@NotNull T value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        if (index == 0) {
            addFirst(value);
        } else if (index == size) {
            add(value);
        } else {
            @NotNull Node<T> node = new Node<>(value);
            @NotNull Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            node.previous = current;
            node.next = current.next;
            current.next.previous = node;
            current.next = node;
            size++;
        }
    }

    /**
     * Search and retrieve a value from the list based on its index.
     *
     * @param index Index that will be searched in the list
     * @return Value that was in the index
     */
    public @NotNull T get(int index) {
        if (head == null) {
            throw new IndexOutOfBoundsException("List is empty");
        } else if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index invalid");
        }

        if (index == size - 1) {
            return tail.getValue();
        }
        @NotNull Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    /**
     * Removes a value from the list
     *
     * @param value Value to be removed
     * @return Returns true or false depending on whether the item is removed.
     */
    public boolean remove(@NotNull T value) {
        if (head == null) return false;

        @Nullable Node<T> current = head;

        while (current != null) {
            if (current.getValue().equals(value)) {
                removeNode(current);
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Removes a value from the list using the index
     *
     * @param index Position where the element is located
     * @return Returns true or false depending on whether the item is removed.
     */
    public boolean remove(int index) {
        if (index < 0 || index >= size) {
            return false;
        }

        @NotNull Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        removeNode(current);
        return true;
    }

    private void removeNode(@NotNull Node<T> node) {
        if (node.previous != null) {
            node.previous.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {
            node.next.previous = node.previous;
        } else {
            tail = node.previous;
        }
        size--;
    }

    /**
     * Calculates the number of elements in the list.
     *
     * @return Returns size of list
     */
    public int size() {
        return size;
    }

    public boolean contains(@NotNull T value) {
        @NotNull Node<T> current = head;

        while (current != null) {
            if (current.getValue().equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * This class represents a node in the DoublyLinkedList
     * Each node has a reference to the next and previous node.
     *
     * @author Matheus Sousa (https://github.com/omatheus-edev)
     * @param <T> The type of element in this node
     */
    public final static class Node<T> {
        @NotNull T value;
        private @Nullable Node<T> previous;
        private @Nullable Node<T> next;

        /**
         * Constructor of node
         *
         * @param value Value of node
         */
        public Node(@NotNull T value) {
            this.value = value;
            this.previous = null;
            this.next = null;
        }

        public @NotNull T getValue() {
            return value;
        }

        public @Nullable Node<T> getPrevious() {
            return previous;
        }

        public @Nullable Node<T> getNext() {
            return next;
        }

        @Override
        public String toString() {
            return "value=" + value +
                    " previous=" + (previous != null ? previous.value : "null") +
                    " next=" + (next != null ? next.value : "null");
        }
    }
}
