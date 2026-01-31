package codes.matheus.datastructures.list;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

/**
 * This class represents a Singly Linked List data structure.
 *
 * <p>A Singly Linked List is a linear data structure consisting of nodes where each node
 * contains a value and a reference (link) to the next node in the sequence. The list
 * starts from a head node and ends at a node that points to null. This structure allows
 * for efficient insertion and deletion at the beginning, but operations at the end or
 * in the middle require traversal.</p>
 *
 * <p>Properties of a Singly Linked List:
 * <ul>
 * <li>Each node has exactly one pointer to the next node (or null for the last node).</li>
 * <li>The list is unidirectional; you can traverse from head to tail but not backward.</li>
 * <li>There are no cycles unless explicitly created (assuming an acyclic list).</li>
 * </ul>
 * This makes it suitable for applications like implementing stacks, queues, or managing dynamic memory.</p>
 *
 * <p>Common operations' complexity (where n is the number of nodes):
 * <ul>
 * <li>Insertion at the beginning: O(1).</li>
 * <li>Insertion at the end: O(n) without a tail pointer, O(1) with a tail pointer.</li>
 * <li>Deletion: O(1) at the beginning, O(n) for arbitrary positions.</li>
 * <li>Search: O(n) in the worst case.</li>
 * <li>Space complexity: O(n) to store the nodes and their references.</li>
 * </ul></p>
 *
 * @author Matheus Sousa (https://github.com/omatheus-edev)
 * @param <T> The type of elements in this list
 */
public final class SinglyLinkedList<T> {

    private @Nullable Node<T> head;
    private @Nullable Node<T> tail;
    @Range(from = 0, to = Integer.MAX_VALUE)
    private int size = 0;

    /**
     * Parameterized constructor
     *
     * @param head Value to be inserted in first element
     */
    public SinglyLinkedList(@NotNull T head) {
        this.head = new Node<>(head);
        this.tail = this.head;
        size++;
    }

    /**
     * Constructor
     */
    public SinglyLinkedList() {
        this.tail = null;
    }

    /**
     * Adding a value to first node of list
     *
     * @param value Value to be inserted
     */
    public void addFirst(@NotNull T value) {
        @NotNull Node<T> node = new Node<>(value);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head = node;
        }
        size++;
    }

    /**
     * Adding a value to final node of list
     *
     * @param value Value to be inserted
     */
    public void add(@NotNull T value) {
        @NotNull Node<T> node = new Node<>(value);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
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
    public void add(int index, @NotNull T value) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Enter a positive and valid index");

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
            node.next = current.next;
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
     * Calculates the number of elements in the list.
     *
     * @return Returns size of list
     */
    public int size() {
        return size;
    }

    /**
     * Method to check if the list contains a value
     *
     * @param value Value to look for
     * @return Returns true or false depending on whether the tree contains the value.
     */
    public boolean contains(@NotNull T value) {
        @NotNull Node<T> current = head;
        for (int i = 0; i < size - 1; i++) {
            if (current.getValue().equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Removes a value from the list
     *
     * @param value Value to be removed
     * @return Returns true or false depending on whether the item is removed.
     */
    public boolean remove(@NotNull T value) {
        if (head == null) return false;

        if (head.getValue().equals(value)) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
            size--;
            return true;
        }

        @NotNull Node<T> current = head;
        while (current.next != null && !current.next.getValue().equals(value)) {
            current = current.next;
        }

        if (current.next != null) {
            if (current.next == tail) {
                tail = current;
            }

            current.next = current.next.next;
            size--;
            return true;
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
        if (head == null) return false;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index invalid");
        }

        if (index == 0) {
            head = head.next;
            if (head == null) tail = null;
        } else {
            @NotNull Node<T> previous = head;
            for (int i = 0; i < index - 1; i++) {
                previous = previous.next;
            }

            @NotNull Node<T> target = previous.next;
            if (target == tail) {
                previous.next = null;
                tail = previous;
            } else {
                previous.next = target.next;
            }
        }
        size--;
        return true;
    }

    /**
     * This class represents a node in the SinglyLinkedList
     * Each node has a reference to the next node.
     *
     * @author Matheus Sousa (https://github.com/omatheus-edev)
     * @param <T> The type of element in this node
     */
    public final static class Node<T> {
        @NotNull T value;
        private @Nullable Node<T> next;

        /**
         * Constructor of Node
         *
         * @param value Value of node
         */
        public Node(@NotNull T value) {
            this.value = value;
        }

        /**
         * Constructor of Node
         *
         * @param value Value of node
         * @param next Next node of current
         */
        public Node(@NotNull T value, @Nullable Node<T> next) {
            this.value = value;
            this.next = next;
        }

        public @NotNull T getValue() {
            return value;
        }

        public @Nullable Node<T> getNext() {
            return next;
        }

        public void setNext(@Nullable Node<T> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "value=" + value +
                    " next=" + (next != null ? next.value : "null");
        }
    }
}
