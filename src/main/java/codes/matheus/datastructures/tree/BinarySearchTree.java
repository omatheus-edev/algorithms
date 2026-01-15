package codes.matheus.datastructures.tree;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

/**
 * This class represents a Binary Search Tree (BST) data structure
 *
 *<p>A Binary Search Tree is a node-based binary tree data structure which has the
 * following properties:
 * <ul>
 * <li>The left subtree of a node contains only nodes with keys lesser than the node's key.</li>
 * <li>The right subtree of a node contains only nodes with keys greater than the node's key.</li>
 * <li>The left and right subtree each must also be a binary search tree.</li>
 * </ul>
 * This ordering allows for fast lookup, addition, and removal of items.</p>
 *
 * <p>Common operations (e.g., search, insert, delete) have O(log n) time complexity
 * in the average case, where n is the number of nodes. In the worst case (skewed tree),
 * these operations have O(n) time complexity. Worst-case space complexity is O(n)
 * to store the nodes.</p>
 *
 * @author Matheus Sousa (https://github.com/omatheus-edev)
 * @param <T> The type of elements in this tree, must extend Comparable
 */
public final class BinarySearchTree<T extends Comparable<@NotNull T>> {

    private @Nullable Node<T> root;
    @Range(from = 0, to = Integer.MAX_VALUE)
    private int size;

    /**
     * Parameterized constructor
     *
     * @param root Value to be inserted in the root
     */
    public BinarySearchTree(@NotNull T root) {
        this.root = new Node<>(root);
    }

    /**
     * Constructor
     */
    public BinarySearchTree() {
    }

    /**
     * Insert a vale into a node of a BST
     *
     * @param value value Value to be inserted
     */
    public void insert(@NotNull T value) {
        @NotNull Node<T> node = new Node<>(value);
        if (root == null) {
            this.root = node;
        } else {
            @Nullable Node<T> current = root;
            @Nullable Node<T> parent = null;
            int compute = 0;

            while (current != null) {
                parent = current;
                compute = value.compareTo(current.value);

                if (compute == 0) {
                    return;
                } else if (compute < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            if (compute < 0) {
                parent.left = node;
            } else {
                parent.right = node;
            }
        }
        size ++;
    }

    /**
     * Deletes a given value from the BST
     *
     * @param value Value to be deleted
     */
    public void remove(@NotNull T value) {
        @Nullable Node<T> current = root;
        @Nullable Node<T> parent = null;

        while (current != null && !current.value.equals(value)) {
            parent = current;
            int compute = value.compareTo(current.value);

            if (compute < 0) {
                current = parent.left;
            } else {
                current = parent.right;
            }
        }

        if (current == null) return;

        if (current.left != null && current.right != null) {
            @NotNull Node<T> successor = current.right;
            @NotNull Node<T> successorParent = current;

            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            current.value = successor.value;

            current = successor;
            parent = successorParent;
        }

        @Nullable Node<T> replacement = (current.left != null) ? current.left : current.right;

        if (parent == null) {
            root = replacement;
        } else {
            if (current == parent.left) {
                parent.left = replacement;
            } else {
                parent.right = replacement;
            }
        }
        size--;
    }

    /**
     * Get a node into a BST with binary search
     *
     * @param value Value to be searched for in the BST
     * @return Returns the found node or returns null
     */
    public @Nullable Node<T> search(@NotNull T value) {
        if (root == null) {
            return null;
        }

        @Nullable Node<T> current = root;
        while (current != null) {
            int compute = value.compareTo(current.value);

            if (compute == 0) {
                return current;
            } else if (compute < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    /***
     * Compute the number of nodes in BST.
     *
     * @return Returns the size of nodes of BST
     */
    @Range(from = 0, to = Integer.MAX_VALUE)
    public int size() {
        return size;
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
     * Method for printing the tree in order
     * */
    public void inOrder() {
        inOrder(root);
        System.out.println();
    }

    /**
     * Prints leftChild - root - rightChild
     *
     * @param node The local root of the binary tree
     */
    private void inOrder(@Nullable Node<T> node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.value + " ");
        inOrder(node.right);
    }

    /**
     * Method for printing the tree in pre-order
     * */
    public void preOrder() {
        preOrder(root);
        System.out.println();
    }

    /**
     * Prints root - leftChild - rightChild
     *
     * @param node The local root of the binary tree
     */
    private void preOrder(@Nullable Node<T> node) {
        if (node == null) return;
        System.out.print(node.value + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * Method for printing the tree in post-order
     * */
    public void postOrder() {
        postOrder(root);
        System.out.println();
    }

    /**
     * Prints leftChild - rightChild - root
     *
     * @param node The local root of the binary tree
     */
    private void postOrder(@Nullable Node<T> node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.value + " ");
    }

    /**
     * This class represents a node in a BST.
     * Where each node has a left and right child with a defined pattern.
     *
     * @author Matheus Sousa (https://github.com/omatheus-edev)
     * @param <T> The type of element in this node, must extend Comparable
     */
    public final static class Node<T extends Comparable<@NotNull T>> {
        @NotNull T value;
        @Nullable Node<T> left;
        @Nullable Node<T> right;

        /**
         * Constructor of Node
         *
         * @param value Value of the node
         * */
        public Node(@NotNull T value) {
            this.value = value;
        }

        public @NotNull T getValue() {
            return value;
        }

        public @Nullable Node<T> getLeft() {
            return left;
        }

        public @Nullable Node<T> getRight() {
            return right;
        }

        @Override
        public @NotNull String toString() {
            return "Node{" +
                    "value=" + value +
                    ", left=" + (left != null ? left.value : "null") +
                    ", right=" + (right != null ? right.value : "null") +
                    '}';
        }
    }
}
