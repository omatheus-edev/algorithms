package codes.matheus.search;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * A graph traversal algorithm known as Breadth First Search (BFS). It goes through each level until it finds the target.
 *
 * @author Matheus Sousa (https://github.com/omatheus-edev)
 */
public final class BreadthFirstSearch {
    private BreadthFirstSearch() {
        throw new UnsupportedOperationException("this class cannot be instantiated");
    }

    /**
     * Performs a breadth-first search to find a node with the given value.
     *
     * @param graph adjacency list representation of the graph
     * @param first the starting node for the BFS traversal
     * @param target the target of graph
     * @param <T> the type of nodes in the graph (must correctly implement {@code equals()} and {@code hashCode())}
     * @return the target node if reachable, {@code null} otherwise
     */
    public static <T> @Nullable T search(@NotNull Map<T, List<T>> graph, @NotNull T first, @NotNull T target) {
        if (!graph.containsKey(first)) return null;
        if (first.equals(target)) return target;

        @NotNull Set<T> visited = new HashSet<>();
        @NotNull Queue<T> queue = new LinkedList<>();
        queue.offer(first);
        queue.add(first);

        while (!queue.isEmpty()) {
            @NotNull T current = queue.poll();
            @NotNull List<T> neighbors = graph.getOrDefault(current, Collections.emptyList());

            for (@NotNull T neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    if (neighbor.equals(target)) {
                        return target;
                    }
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return null;
    }
}
