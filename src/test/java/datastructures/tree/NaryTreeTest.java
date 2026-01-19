package datastructures.tree;

import codes.matheus.datastructures.tree.NaryTree;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public final class NaryTreeTest {
    private final @NotNull NaryTree<String> tree = new NaryTree<>("Raiz");

    @BeforeEach
    void setUp() {
        tree.insert("Raiz", "PastaA");
        tree.insert("Raiz", "PastaB");
        tree.insert("PastaA", "Arq1");
    }

    @Test
    void testInsert() {
        assertTrue(tree.contains("Arq1"));
        assertTrue(tree.contains("PastaB"));

        @Nullable NaryTree.Node<String> nodeA = tree.search("PastaA");
        assertNotNull(nodeA);
        assertEquals(1, nodeA.getChildren().size());
        assertEquals("Arq1", nodeA.getChildren().get(0).getValue());
    }

    @Test
    void testSearchNotFound() {
        assertFalse(tree.contains("Inexistente"));
        assertNull(tree.search("Inexistente"));
    }

    @Test
    void testRemoveLeaf() {
        tree.remove("Arq1");
        assertFalse(tree.contains("Arq1"));
        assertTrue(tree.contains("PastaA"), "PastaA deve continuar existindo");
    }

    @Test
    void testRemoveSubtree() {
        tree.remove("PastaA");
        assertFalse(tree.contains("PastaA"));
        assertFalse(tree.contains("Arq1"), "A subárvore de PastaA deveria ter sido removida");
        assertTrue(tree.contains("Raiz"));
    }

    @Test
    void testRemoveRoot() {
        tree.remove("Raiz");
        assertFalse(tree.contains("Raiz"));
        assertFalse(tree.contains("PastaB"));
    }

    @Test
    void testPreOrder() {
        @NotNull ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        tree.preOrder();

        @NotNull String output = outContent.toString().trim();
        assertTrue(output.startsWith("Raiz PastaA Arq1 PastaB"));

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
        assertFalse(intTree.contains(100), "Ao remover o 10, o 100 deve sumir");
    }

}
