package datastructures.tree;

import codes.matheus.datastructures.tree.BinaryTree;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public final class BinaryTreeTest {
    private final @NotNull BinaryTree<@NotNull Integer> tree = new BinaryTree<>();

    @Test
    void testInsertAndSize() {
        assertEquals(0, tree.size(), "Initial size should be 0");

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);

        assertEquals(3, tree.size(), "Size should be 3 after 3 insertions");
        assertTrue(tree.contains(10));
    }

    @Test
    void testSearch() {
        tree.insert(10);
        tree.insert(20);

        @Nullable BinaryTree.Node<@NotNull Integer> node = tree.search(20);
        assertNotNull(node, "Search should find the node with value 20");

        assertNull(tree.search(99), "Search should return null for non-existent value");
    }

    @Test
    void testRemoveAndSize() {
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        assertEquals(3, tree.size());

        tree.remove(20);
        assertEquals(2, tree.size(), "Size should decrease after removal");
        assertNull(tree.search(20), "Removed node should not be findable via search");
    }

    @Test
    void testRemoveOnlyNodeSize() {
        tree.insert(100);
        assertEquals(1, tree.size());

        tree.remove(100);
        assertEquals(0, tree.size(), "Size should be 0 after removing the only node");
        assertNull(tree.search(100));
    }

    @Test
    void testRemoveNonExistentSize() {
        tree.insert(10);
        tree.insert(20);
        assertEquals(2, tree.size());

        tree.remove(99);
        assertEquals(2, tree.size(), "Size should not change if value was not found");
    }

    @Test
    void testRemoveTargetIsLastNode() {
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);

        tree.remove(30);

        assertFalse(tree.contains(30));
        assertEquals(2, tree.size());
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

        assertEquals(7, tree.size());

        assertNotNull(tree.search(10));
        assertNotNull(tree.search(21));

        tree.inOrder();
        tree.preOrder();
        tree.postOrder();
    }
}