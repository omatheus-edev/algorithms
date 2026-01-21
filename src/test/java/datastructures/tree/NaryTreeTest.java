package datastructures.tree;

import codes.matheus.datastructures.tree.NaryTree;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public final class NaryTreeTest {
    private final @NotNull NaryTree<String> tree = new NaryTree<>("Root");

    @BeforeEach
    void setUp() {
        tree.insert("Root", "FolderA");
        tree.insert("Root", "FolderB");

        @NotNull NaryTree.Node<String> nodeA = Objects.requireNonNull(tree.search("FolderA"));
        assertNotNull(nodeA);
        tree.insert(nodeA, "File1");
    }

    @Test
    void testInsert() {
        assertTrue(tree.contains("File1"));
        assertTrue(tree.contains("FolderB"));

        @Nullable NaryTree.Node<String> nodeA = tree.search("FolderA");
        assertNotNull(nodeA);
        assertEquals(1, nodeA.getChildren().size());
        assertEquals("File1", nodeA.getChildren().get(0).getValue());
    }

    @Test
    void testInsertReturnsNode() {
        @NotNull NaryTree.Node<String> newNode = tree.insert("FolderB", "File2");
        assertNotNull(newNode);
        assertEquals("File2", newNode.getValue());
    }

    @Test
    void testSearchNotFound() {
        assertFalse(tree.contains("non-existent"));
        assertNull(tree.search("non-existent"));
    }

    @Test
    void testRemoveLeaf() {
        tree.remove("File1");
        assertFalse(tree.contains("File1"));
        assertTrue(tree.contains("FolderA"));
    }

    @Test
    void testRemoveSubtree() {
        tree.remove("FolderA");
        assertFalse(tree.contains("FolderA"));
        assertFalse(tree.contains("File1"));
        assertTrue(tree.contains("Root"));
    }

    @Test
    void testRemoveRoot() {
        tree.remove("Root");
        assertFalse(tree.contains("Root"));
        assertFalse(tree.contains("FolderB"));
    }

    @Test
    void testPreOrder() {
        @NotNull ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        tree.preOrder();

        @NotNull String output = outContent.toString().trim();
        assertTrue(output.startsWith("Root FolderA File1 FolderB"));

        System.setOut(System.out);
    }

    @Test
    void testWithIntegers() {
        @NotNull NaryTree<Integer> intTree = new NaryTree<>(1);
        intTree.insert(1, 10);
        intTree.insert(1, 20);
        intTree.insert(10, 100);

        assertTrue(intTree.contains(100));
        intTree.remove(10);
        assertFalse(intTree.contains(100));
    }

}
