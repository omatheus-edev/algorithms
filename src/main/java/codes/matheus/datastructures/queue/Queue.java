package codes.matheus.datastructures.queue;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A simple interface with the characteristics of a FIFO queue
 *
 * @author Matheus Sousa (https://github.com/omatheus-edev)
 * @param <T> The type of elements in the queue
 */
public interface Queue<T> {

    /**
     * Insert a value at the end of the queue
     *
     * @param value Value to be inserted on queue
     * @return Returns true or false depending on whether the item is added to the queue
     */
    boolean push(@NotNull T value);

    /**
     * Remove the first element of queue
     *
     * @return Returns the first element that will be removed or null if the queue is empty
     */
    @Nullable T pop();

    /**
     * Returns the first element of queue
     *
     * @return Returns the first element of queue or null if the queue is empty
     */
    @Nullable T peek();

    /**
     * Returns the last element of queue
     *
     * @return Returns the last element of queue or null if the queue is empty
     */
    @Nullable T peekLast();

    /**
     * Checks if the queue is empty
     *
     * @return Returns true or false if the queue is empty
     */
    boolean isEmpty();

    /**
     * Calculates the number of elements in the queue.
     *
     * @return Returns size of queue
     */
    int size();
}
