package codes.matheus.search;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

final class BreadthFirstSearchTest {
    private @NotNull Map<String, List<String>> graph;

    @BeforeEach
    void setUp() {
        graph = new HashMap<>();

        graph.put("Fortaleza", Arrays.asList("Caucaia", "Maracanaú", "Eusébio"));
        graph.put("Caucaia", Arrays.asList("Fortaleza", "São Gonçalo do Amarante"));
        graph.put("Maracanaú", Arrays.asList("Fortaleza", "Eusébio", "Aquiraz"));
        graph.put("Eusébio", Arrays.asList("Fortaleza", "Maracanaú"));
        graph.put("São Gonçalo do Amarante", Arrays.asList("Caucaia"));
        graph.put("Aquiraz", Arrays.asList("Maracanaú"));
        graph.put("Juazeiro do Norte", Collections.emptyList());
    }

    @Test
    void testSearch() {
        assertEquals("Fortaleza", BreadthFirstSearch.search(graph, "Aquiraz", "Fortaleza"));
        assertEquals("Fortaleza", BreadthFirstSearch.search(graph, "Fortaleza", "Fortaleza"));
        assertNull(BreadthFirstSearch.search(graph, "Fortaleza", "Juazeiro do Norte"));
        assertNull(BreadthFirstSearch.search(graph, "Juazeiro do Norte", "Caucaia"));
    }

}