package codes.matheus.datastructures.list;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * This class represents an optimized Doubly Linked List data structure, named MidLinkedList.
 *
 * <p>A MidLinkedList is an enhanced version of a Doubly Linked List that maintains three
 * strategic pointers: <b>head</b>, <b>mid</b>, and <b>tail</b>. While a standard Doubly
 * Linked List allows bidirectional traversal, this implementation optimizes access time
 * by calculating the shortest path from the nearest anchor point (Head, Mid, or Tail).</p>
 *
 * <p>The central innovation lies in the <b>Shortest Path Search</b> logic:
 * <ul>
 * <li><b>Head:</b> Used for indices close to the beginning of the list.</li>
 * <li><b>Mid:</b> A pointer that always tracks the element at {@code size / 2}, used for indices near the center.</li>
 * <li><b>Tail:</b> Used for indices close to the end of the list.</li>
 * </ul>
 * For any given index <i>i</i>, the list calculates the minimum distance among
 * {@code i} (from head), {@code |i - (size/2)|} (from mid), and {@code (size-1) - i} (from tail),
 * ensuring the most efficient traversal possible.</p>
 *
 * <p>Properties of MidLinkedList:
 * <ul>
 * <li>Bidirectional navigation with an additional O(1) entry point at the center.</li>
 * <li>Automatic rebalancing of the <b>mid</b> pointer during insertions and deletions.</li>
 * <li>Drastically reduces the constant factor of linear operations compared to standard lists.</li>
 * </ul></p>
 *
 * <p>Common operations' complexity (where n is the number of nodes):
 * <ul>
 * <li>Insertion at the beginning/end: O(1).</li>
 * <li>Deletion at the beginning/end: O(1).</li>
 * <li>Search (get/contains): O(n/4) on average due to the tri-directional search (worst case O(n/2) at the quarter points).</li>
 * <li>Insertion/Deletion at arbitrary index: O(n/4) average search time + O(1) pointer reconfiguration.</li>
 * <li>Space complexity: O(n) to store nodes with dual references and the additional mid pointer.</li>
 * </ul></p>
 *
 * @param <T> The type of elements in this list
 * @author Matheus Sousa (https://github.com/omatheus-edev)
 */
public final class MidLinkedList<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> tail;
    private Node<T> mid;
    @Range(from = 0, to = Integer.MAX_VALUE)
    private int size = 0;
    @Range(from = 0, to = Integer.MAX_VALUE)
    private int midIndex = 0;

    /**
     * Constructor
     *
     */
    public MidLinkedList() {
        this.head = null;
        this.mid = null;
        this.tail = null;
    }

    /**
     * Parameterized constructor
     *
     * @param head Value to be inserted in first element
     */
    public MidLinkedList(@NotNull T head) {
        this.head = new Node<>(head);
        this.mid = this.head;
        this.tail = this.head;
        this.size++;
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
            this.mid = node;
            this.tail = node;
            size = 1;
            midIndex = 0;
            return;
        }

        node.next = head;
        head.previous = node;
        head = node;
        size++;
        midIndex++;
    }

    /**
     * Adding a value of last node of list
     *
     * @param value Value to be inserted on first node
     */
    public void add(@NotNull T value) {
        @NotNull Node<T> node = new Node<>(value);
        if (head == null) {
            this.head = node;
            this.mid = node;
            this.tail = node;
            size = 1;
            midIndex = 0;
            return;
        }

        tail.next = node;
        node.previous = tail;
        tail = node;
        size++;

        if (size % 2 == 1) {
            mid = mid.next;
            midIndex++;
        }
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
            @NotNull Node<T> current = getNode(index);

            node.previous = current.previous;
            node.next = current;
            current.previous.next = node;
            current.previous = node;
            size++;

            int newMidIndex = size / 2;
            if (index <= midIndex) {
                midIndex++;
            }

            while (midIndex < newMidIndex) {
                mid = mid.next;
                midIndex++;
            }

            while (midIndex > newMidIndex) {
                mid = mid.previous;
                midIndex--;
            }
        }
    }

    /**
     * Removes a value from the list
     *
     * @param value Value to be removed
     * @return Returns true or false depending on whether the item is removed.
     */
    public boolean remove(@NotNull T value) {
        if (isEmpty()) {
            return false;
        }

        @Nullable Node<T> start = head;
        @Nullable Node<T> end = tail;

        for (int i = 0; i <= size / 2; i++) {
            int endIndex = size - 1 - i;

            if (start != null) {
                if (value.equals(start.value)) {
                    return remove(i);
                }
            }

            if (end != null && i != endIndex) {
                if (value.equals(end.value)) {
                    return remove(endIndex);
                }
            }

            if (start != null) {
                start = start.next;
            }
            if (end != null) {
                end = end.previous;
            }
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
        if (index < 0 || index > size) return false;
        if (isEmpty()) {
            return false;
        }

        if (size == 1) {
            head = null;
            tail = null;
            mid = null;
            size = 0;
            midIndex = 0;
            return true;
        }

        if (index == 0) {
            head = head.next;
            head.previous = null;
            size--;
            midIndex--;

            if (midIndex < 0) {
                midIndex = 0;
                mid = head;
            }

            if (size % 2 == 1 && midIndex > size / 2) {
                midIndex--;
                mid = mid.previous;
            }
            return true;
        }

        if (index == size - 1) {
            tail = tail.previous;
            tail.next = null;
            size--;

            if (size % 2 == 0 && midIndex >= (size + 1) / 2) {
                mid = mid.previous;
                midIndex--;
            }
            return true;
        }

        @NotNull Node<T> target = getNode(index);
        target.previous.next = target.next;
        target.next.previous = target.previous;
        size--;

        int newMidIndex = size / 2;
        if (index < midIndex) {
            midIndex--;
        } else if (index == midIndex) {
            if (mid.next != null) {
                mid = mid.next;
            } else if (mid.previous != null) {
                mid = mid.previous;
                midIndex--;
            }
        }

        while (midIndex < newMidIndex && mid.next != null) {
            mid = mid.next;
            midIndex++;
        }

        while (midIndex > newMidIndex && mid.previous != null) {
            mid = mid.previous;
            midIndex--;
        }
        return true;
    }

    /**
     * Search and retrieve a value from the list based on its index.
     *
     * @param index Index that will be searched in the list
     * @return Value that was in the index
     */
    public @NotNull T get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return getNode(index).value;
    }

    private @NotNull Node<T> getNode(int index) {
        int distFromHead = index;
        int distFromMid = Math.abs(index - midIndex);
        int distFromTail = size - 1 - index;

        if (distFromHead <= distFromMid && distFromHead <= distFromTail) {
            @NotNull Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        } else if (distFromMid <= distFromTail) {
            @NotNull Node<T> current = mid;
            if (index > midIndex) {
                for (int i = midIndex; i < index; i++) {
                    current = current.next;
                }
            } else {
                for (int i = midIndex; i > index; i--) {
                    current = current.previous;
                }
            }
            return current;
        } else {
            @NotNull Node<T> current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.previous;
            }
            return current;
        }
    }

    /**
     * Calculates the index of element in the list
     *
     * @param value Index of the value to be searched
     * @return Returns index of value or -1 if not found
     */
    public int indexOf(@NotNull T value) {
        @Nullable Node<T> start = head;
        @Nullable Node<T> end = tail;

        for (int i = 0; i <= size / 2; i++) {
            if (start != null && value.equals(start.value)) {
                return i;
            }

            int endIndex = size - 1 - i;
            if (end != null && i != endIndex && value.equals(end.value)) {
                return endIndex;
            }

            if (start != null) start = start.next;
            if (end != null) end = end.previous;
        }
        return -1;
    }

    /**
     * Calculates the number of elements in the list
     *
     * @return Returns size of list
     */
    public int size() {
        return size;
    }

    /**
     * Check if the list is empty.
     *
     * @return Return true or false if the list is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Method to check if the list contains a value
     *
     * @param value Value to look for
     * @return Returns true or false depending on whether the list contains the value.
     */
    public boolean contains(@NotNull T value) {
        return indexOf(value) != -1;
    }

    @Override
    public @NotNull Iterator<T> iterator() {
        return new Iterator<T>() {
            private @Nullable Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                @NotNull T value = current.value;
                current = current.next;
                return value;
            }
        };
    }

    public @NotNull Stream<T> stream() {
        @NotNull Spliterator<T> spliterator = Spliterators.spliteratorUnknownSize(iterator(), Spliterator.ORDERED);
        return StreamSupport.stream(spliterator, false);
    }

    @Override
    public @NotNull String toString() {
        if (isEmpty()) {
            return "[]";
        }

        @NotNull StringBuilder builder = new StringBuilder("[");
        @Nullable Node<T> current = head;


        while (current != null) {
            builder.append(current.value);
            if (current.next != null) {
                builder.append(", ");
            }
            current = current.next;
        }
        builder.append("]");
        return builder.toString();
    }

    /**
     * This class represents a node in the MidLinkedList
     * Each node has a reference to the next and previous node.
     *
     * @param <T> The type of element in this node
     * @author Matheus Sousa (https://github.com/omatheus-edev)
     */
    public final static class Node<T> {
        @NotNull T value;
        private Node<T> previous;
        private Node<T> next;

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
        public @NotNull String toString() {
            return "value=" + value +
                    " previous=" + (previous != null ? previous.value : "null") +
                    " next=" + (next != null ? next.value : "null");
        }
    }
}