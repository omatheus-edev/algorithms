package codes.matheus.datastructures.stack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A simple interface with the characteristics of a LIFO stack
 *
 * @author Matheus Sousa (https://github.com/omatheus-edev)
 * @param <T> The type of elements in the stack
 */
public interface Stack<T> {

    /**
     * Insert a value at the top of the stack.
     *
     * @param value Value to be inserted on stack
     * @return Returns true or false depending on whether the item is added to the stack
     */
    boolean push(@NotNull T value);

    /**
     * Remove the item from the top of the stack.
     *
     * @return Returns the first element that will be removed or null if the stack is empty
     */
    @Nullable T pop();

    /**
     * Returns the element from the top of the stack.
     *
     * @return Returns the first element of stack or null if the stack is empty
     */
    @Nullable T peek();

    /**
     * Returns the element from the bottom of the stack.
     *
     * @return Returns the last element of stack or null if the stack is empty
     */
    @Nullable T peekLast();

    /**
     * Checks if the stack is empty
     *
     * @return Returns true or false if the stack is empty
     */
    boolean isEmpty();

    /**
     * Calculates the number of elements in the stack.
     *
     * @return Returns size of stack
     */
    int size();
}
