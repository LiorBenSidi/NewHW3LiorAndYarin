/**
 * An interface that represents an iterable stack.
 */
public interface Stack<E extends Cloneable> extends Iterable<E>, Cloneable {

    /**
     * Pushes a provided element to the top of the stack.
     *
     * @param element the provided element
     */
    void push(E element);

    /**
    * Removes and returns(if the stack is not empty) the element at the top of the stack.
    *
    * @return the element that is at the top of the stack
    */
    E pop();

    /**
     * Returns the element(if the stack is not empty) at the top of the stack, but without removing it.
     *
     * @return the element that is at the top of the stack
     */
    E peek();

    /**
     * Returns the number of elements in the stack.
     *
     * @return the number of elements in the stack
     */
    int size();

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Creates and returns a deep copy of the ArrayStack.
     *
     * @return a deep copy of the ArrayStack
     */
    Stack<E> clone();
}


