package datastructures.tree;

import codes.matheus.datastructures.tree.BinarySearchTree;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public final class BinarySearchTreeTest {
    private final @NotNull BinarySearchTree<Integer> bst = new BinarySearchTree<>();

    @Test
    void testInsert() {
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(30);

        assertEquals(3, bst.size());
        assertTrue(bst.contains(50));
        assertTrue(bst.contains(30));
        assertTrue(bst.contains(70));
    }

    @Test
    void testRemoveLeaf() {
        bst.insert(50);
        bst.insert(30);
        bst.remove(30);

        assertFalse(bst.contains(30));
        assertEquals(1, bst.size());
    }

    @Test
    void testRemoveOneChild() {
        bst.insert(50);
        bst.insert(30);
        bst.insert(20);

        bst.remove(30);

        assertFalse(bst.contains(30));
        assertTrue(bst.contains(20));
        assertEquals(2, bst.size());
    }

    @Test
    void testRemoveTwoChildren() {
        bst.insert(50);
        bst.insert(30);
        bst.insert(20);
        bst.insert(40);
        bst.insert(70);

        bst.remove(30);

        assertFalse(bst.contains(30));
        assertTrue(bst.contains(20));
        assertTrue(bst.contains(40));
        assertEquals(4, bst.size());
    }

    @Test
    void testRemoveRoot() {
        bst.insert(50);
        bst.remove(50);

        assertNull(bst.search(50));
        assertEquals(0, bst.size());
    }

    @Test
    void testOrder() {
        bst.insert(10);
        bst.insert(15);
        bst.insert(20);
        bst.insert(12);
        bst.insert(17);
        bst.insert(8);
        bst.insert(21);

        assertEquals(7, bst.size());

        assertNotNull(bst.search(10));
        assertNotNull(bst.search(21));

        bst.inOrder();
        bst.preOrder();
        bst.postOrder();
    }
}
