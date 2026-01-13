package datastructures.tree;

import codes.matheus.datastructures.tree.BinaryTree;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public final class BinaryTreeTest {
    private final @NotNull BinaryTree<@NotNull Integer> tree = new BinaryTree<>();

    @Test
    void testInsert() {
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);

        assertTrue(tree.contains(10));
        assertTrue(tree.contains(20));
        assertTrue(tree.contains(30));
    }

    @Test
    void testRemoveLeaf() {
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);

        tree.remove(30);

        assertFalse(tree.contains(30), "Tree should not contain 30 anymore");
    }

    @Test
    void testRemoveRoot() {
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);

        tree.remove(10);

        assertFalse(tree.contains(10), "Root 10 should be removed");
        assertTrue(tree.contains(30), "Deepest node 30 should still exist");
    }

    @Test
    void testRemoveOnlyNode() {
        tree.insert(100);
        tree.remove(100);

        assertFalse(tree.contains(100));
    }

    @Test
    void testRemoveNonExistent() {
        tree.insert(10);
        tree.insert(20);

        tree.remove(99);

        assertTrue(tree.contains(10));
        assertTrue(tree.contains(20));
    }

    @Test
    void testRemoveTargetIsLastNode() {
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);

        tree.remove(30);

        assertFalse(tree.contains(30));
    }

    @Test
    void testOrder() {
        tree.insert(10);
        tree.insert(15);
        tree.insert(20);
        tree.insert(12);
        tree.insert(17);
        tree.insert(8);
        tree.insert(21);

        tree.inOrder();
        tree.preOrder();
        tree.postOrder();
    }
}
