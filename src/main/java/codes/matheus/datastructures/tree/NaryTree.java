package codes.matheus.datastructures.tree;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * A generic tree (N-ary tree) is a data structure different from a common binary tree.
 * The n-ary tree has one node, which can have more than two children.
 * This makes them very flexible for use in file systems, DOM structures, etc.
 *
 * <p>Properties of an N-ary Tree:
 * <ul>
 * <li>Each node contains a value and a collection of references to its children.</li>
 * <li>There is exactly one root node from which all other nodes are reachable.</li>
 * <li>There are no cycles; each node (except the root) has exactly one parent.</li>
 * </ul></p>
 *
 * <p>Common operation's complexity:
 * <ul>
 * <li>Insertion: O(1) if the parent node is already known, or O(n) to find the parent.</li>
 * <li>Deletion: O(n) to find the node and its parent. Removing a node typically removes its entire subtree (recursive deletion)
 * <li>Search: O(n) in the worst case, as the tree is not necessarily ordered.</li>
 * <li>Space Complexity: O(n) to store 'n' nodes and their respective child references.</li>
 * </ul></p>
 *
 * @author Matheus Sousa (https://github.com/omatheus-edev)
 * @param <T> The type of elements in this tree
 */
public final class NaryTree<T> {

    private @Nullable Node<T> root;

    /**
     * Parameterized constructor
     *
     * @param root Value to be inserted on root
     */
    public NaryTree(@NotNull T root) {
        this.root = new Node<>(root);
    }

    /**
     * Constructor
     */
    public NaryTree() {
    }

    public @Nullable Node<T> getRoot() {
        return root;
    }

    /**
     * Insert a vale into a node of the N-ary tree
     *
     * @param parentValue Value of the parent who will receive the new value
     * @param value Value to be inserted
     * @return Returns the node that was just added.
     */
    public @NotNull Node<T> insert(@NotNull T parentValue, @NotNull T value) {
        @NotNull Node<T> node = new Node<>(value);
        if (root == null) {
            this.root = node;
            return node;
        }

        @Nullable Node<T> parent = search(parentValue);

        if (parent == null) {
            throw new NoSuchElementException("Parent not found on tree: " + parentValue);
        }

        parent.addChild(node);
        return node;
    }

    /**
     * Insert a vale into a node of the N-ary tree. Using the parent node to optimize the search
     *
     * @param parent Node of the parent who will receive the new value
     * @param value value Value to be inserted
     * @return Returns the node that was just added.
     */
    public @NotNull Node<T> insert(@NotNull Node<T> parent, @NotNull T value) {
        @NotNull Node<T> node = new Node<>(value);
        parent.addChild(node);
        return node;
    }

    /**
     * Removes a node and all its subtrees from the tree.
     * @param value The value of the node to be removed.
     */
    public void remove(@NotNull T value) {
        if (root == null) {
            return;
        }

        if (root.getValue().equals(value)) {
            root = null;
            return;
        }

        @NotNull Queue<Node<T>> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            @NotNull Node<T> parent = queue.poll();
            @Nullable List<Node<T>> childrenList = parent.children;

            if (childrenList != null) {
                @NotNull Iterator<Node<T>> iterator = childrenList.iterator();

                while (iterator.hasNext()) {
                    @NotNull Node<T> currentChild = iterator.next();

                    if (currentChild.getValue().equals(value)) {
                        iterator.remove();
                        return;
                    }
                    queue.add(currentChild);
                }
            }
        }
    }

    /**
     * Recursive search (DFS) to find a node by its value.
     *
     * @param value value of the node to be searched
     * @return Returns the value of the searched node based on the current value.
     */
    public @Nullable Node<T> search(@NotNull T value) {
        if (root == null) return null;

        @NotNull Queue<Node<T>> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            @NotNull Node<T> current = queue.poll();

            if (current.getValue().equals(value)) {
                return current;
            }

            @Nullable List<Node<T>> currentChildren = current.children;
            if (currentChildren != null) {
                for (Node<T> child : currentChildren) {
                    queue.add(child);
                }
            }
        }
        return null;
    }

    /**
     * Method to check if the tree contains a value
     *
     * @param value Value to look for
     * @return Returns true or false depending on whether the tree contains the value.
     */
    public boolean contains(@NotNull T value) {
        return search(value) != null;
    }

    /**
     * Traverses the tree in Pre-order (Root -> Children) recursively.
     */
    public void preOrder() {
        if (root != null) {
            preOrderRecursive(root);
            System.out.println();
        }
    }

    /**
     * Helper method for recursive pre-order traversal.
     * * @param current The current node being visited.
     */
    private void preOrderRecursive(@NotNull Node<T> current) {
        System.out.print(current.getValue() + " ");
        @Nullable List<Node<T>> children = current.children;

        if (children != null) {
            for (@NotNull Node<T> child : children) {
                preOrderRecursive(child);
            }
        }
    }

    /**
     * This class represents a node in the N-ary tree
     * Each node has a list of children, without the need for them to be ordered.
     *
     * @author Matheus Sousa (https://github.com/omatheus-edev)
     * @param <T> The type of element in this node
     */
    public final static class Node<T> {
        @NotNull T value;
        private @Nullable List<Node<T>> children;
        private @Nullable Node<T> parent;

        /**
         * Constructor of node
         *
         * @param value Value of the node
         */
        public Node(@NotNull T value) {
            this.value = value;
            this.parent = null;
        }

        public @NotNull T getValue() {
            return value;
        }

        public @NotNull List<Node<T>> getChildren() {
            if (children == null) {
                return Collections.emptyList();
            }
            return Collections.unmodifiableList(children);
        }

        public @Nullable Node<T> getParent() {
            return parent;
        }

        public void clear() {
            if (children != null) {
                children.clear();
            }
        }

        /**
         * Adds a child to the node
         *
         * @param child Child that will be added to the parent
         */
        private void addChild(@NotNull Node<T> child) {
            if (children == null) {
                this.children = new ArrayList<>();
            }
            children.add(child);
            child.parent = this;
        }

        @Override
        public @NotNull String toString() {
            return "Node {" +
                    " value=" + value +
                    " length=" + (children != null ? children : "null") +
                    " children=" + children +
                    "}";
        }
    }
}
