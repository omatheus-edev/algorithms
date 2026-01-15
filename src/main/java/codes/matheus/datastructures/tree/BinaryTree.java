package codes.matheus.datastructures.tree;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This class represents a general Binary Tree data structure.
 *
 * <p>A binary tree is a tree data structure in which each node has at most two children,
 * referred to as the left child and the right child. Unlike a Binary Search Tree (BST),
 * there is no ordering constraint between parent and children nodes.</p>
 *
 * <p>Common operations (e.g., traversals) have O(n) time complexity in the average and worst cases.
 * Worst-case space complexity is O(n) for n nodes. Height-related operations may be O(h),
 * where h is the height of the tree (h can be up to n in a skewed tree).</p>
 *
 * @author Matheus Sousa (https://github.com/omatheus-edev)
 * @param <T> The type of elements in this tree
 */
public final class BinaryTree<T> {

    private @Nullable Node<T> root;
    @Range(from = 0, to = Integer.MAX_VALUE)
    private int size = 0;

    /**
     * Constructor
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * Parameterized constructor
     *
     * @param root Value to be inserted in the root
     */
    public BinaryTree(@NotNull T root) {
        this.root = new Node<>(root);
    }

    /**
     * Insert a value into a node of Binary Tree
     *
     * @param value Value to be inserted
     * */
    public void insert(@NotNull T value) {
        @NotNull Node<T> node = new Node<>(value);
        if (root == null) {
            this.root = node;
        } else {
            @NotNull Queue<Node<T>> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                @NotNull Node<T> temp = queue.poll();

                if (temp.left == null) {
                    temp.left = node;
                    break;
                } else {
                    queue.add(temp.left);
                }

                if (temp.right == null) {
                    temp.right = node;
                    break;
                } else {
                    queue.add(temp.right);
                }
            }
        }
        size++;
    }

    /**
     * Deletes a given value from the Binary Tree
     *
     * @param value Value to be deleted
     */
    public void remove(@NotNull T value) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            if (root.value.equals(value)) {
                root = null;
                size--;
            }
            return;
        }

        @NotNull Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);

        @Nullable Node<T> target = null;
        @Nullable Node<T> last = null;

        while (!queue.isEmpty()) {
            last = queue.poll();

            if (last.value.equals(value)) {
                target = last;
            }

            if (last.left != null) {
                queue.add(last.left);
            }
            if (last.right != null) {
                queue.add(last.right);
            }
        }

        if (target != null) {
            @NotNull T deepest = last.value;
            deleteDeepestNode(last);
            size--;

            if (target != last) {
                target.value = deepest;
            }
        }
    }

    /**
     * Locate the parent of the given node and remove the reference to it.
     *
     * @param last The node that should be disconnected from the tree.
     */
    private void deleteDeepestNode(@NotNull Node<T> last) {
        @NotNull Queue<Node<T>> deepestQueue = new LinkedList<>();
        deepestQueue.add(root);

        while (!deepestQueue.isEmpty()) {
            @NotNull Node<T> temp = deepestQueue.poll();

            if (temp.left == last) {
                temp.left = null;
                return;
            } else if (temp.left != null) {
                deepestQueue.add(temp.left);
            }

            if (temp.right == last) {
                temp.right = null;
                return;
            } else if (temp.right != null) {
                deepestQueue.add(temp.right);
            }
        }
    }

    /**
     * Get a node into a node of Binary Tree
     *
     * @param value Value to be searched for in the Binary Tree
     * @return Returns the found node or returns null
     * */
    public @Nullable Node<T> search(@NotNull T value) {
        if (root == null) {
            return null;
        }

        @NotNull Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            @NotNull Node<T> node = queue.poll();

            if (node.value.equals(value)) {
                return node;
            }
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return null;
    }

    @Range(from = 0, to = Integer.MAX_VALUE)
    public int size() {
        return size;
    }

    /**
     * Method to check if the tree contains a value
     *
     * @param value Value to look for
     * @return Returns true or false depending on whether the tree contains the value.
     * */
    public boolean contains(@NotNull T value) {
        if (root == null) {
            return false;
        }

        @NotNull Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            @NotNull Node<T> node = queue.poll();

            if (node.value.equals(value)) {
                return true;
            }
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return false;
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
     * This class represents a node in a Binary Tree.
     * Where each node has a left and right child with a defined pattern.
     *
     * @author Matheus Sousa (https://github.com/omatheus-edev)
     * @param <T> The type of element in this node
     * */
    public final static class Node<T> {
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
