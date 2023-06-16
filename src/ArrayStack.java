import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * A generic class that implements a stack using an array.
 *
 * @param <E> the generic type of elements in our implementation of stack
 */
public class ArrayStack<E extends Cloneable> implements Stack<E> {
    private int counterOfItems;
    private Cloneable[] array;
    private final int maxCapacity;

    /**
     * Constructs an ArrayStack according to the provided maximum capacity(if valid).
     *
     * @param maxCapacity the provided maximum capacity of the stack
     * @throws NegativeCapacityException if the provided maximum capacity is negative
     */
    public ArrayStack(int maxCapacity) {
        if (maxCapacity >= 0) {
            this.maxCapacity = maxCapacity;
            array = new Cloneable[maxCapacity];
        } else {
            throw new NegativeCapacityException();
        }
    }

    /**
     * Pushes a provided element to the top of the stack.
     *
     * @param element the provided element
     * @throws StackOverflowException if the stack is already full(We reached the maximum capacity)
     */
    @Override
    public void push(Cloneable element) {
        if (this.size() < maxCapacity) {
            array[this.size()] = element;
            counterOfItems++;
        } else {
            throw new StackOverflowException();
        }
    }

    /**
     * Removes and returns(if the stack is not empty) the element at the top of the stack.
     *
     * @return the element that is at the top of the stack
     * @throws EmptyStackException if the stack is empty(there are no element we can remove)
     */
    @Override
    public E pop() {
        if (!this.isEmpty()) {
            Cloneable topElement = array[this.size() - 1];
            array[this.size() - 1] = null;
            counterOfItems--;
            return (E) topElement;
        } else {
            throw new EmptyStackException();
        }
    }

    /**
     * Returns the element(if the stack is not empty) at the top of the stack, but without removing it.
     *
     * @return the element that is at the top of the stack
     * @throws EmptyStackException if the stack is empty(there are no element we can look at)
     */
    @Override
    public E peek() {
        if (!isEmpty()) {
            return (E) array[size() - 1];
        } else {
            throw new EmptyStackException();
        }
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return the number of elements in the stack
     */
    @Override
    public int size() {
        return counterOfItems;
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Creates and returns a deep copy of the ArrayStack.
     *
     * @return a deep copy of the ArrayStack
     */
    @Override
    public ArrayStack<E> clone() {
        try {
            ArrayStack<E> copy = (ArrayStack<E>) super.clone();
            copy.array = copy.array.clone();
            for (int i = 0; i < counterOfItems; i++) {
                try {
                    Method method = array[i].getClass().getMethod("clone");
                    copy.array[i] = (E) method.invoke(array[i]);
                } catch (Exception e) {
                    return null;
                }
            }
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }

    }

    /**
     * Returns an iterator to use for iterating over the elements in the stack.
     *
     * @return an iterator to use for iterating over the elements in the stack
     */
    @Override
    public Iterator<E> iterator() {
        return new StackIterator();
    }

    /**
     * An inner class, that represents an iterator for iterating over the elements in the ArrayStack.
     */
    public class StackIterator implements Iterator<E> {
        private int itemsLeft;

        public StackIterator() {
            this.itemsLeft = ArrayStack.this.size();
        }

        /**
         * Checks if there are remaining elements in the stack to iterate over.
         *
         * @return true if there are remaining elements in the stack to iterate over, false otherwise
         */
        @Override
        public boolean hasNext() {
            return itemsLeft > 0;
        }

        /**
         * If there are remaining elements in the stack to iterate over, returns the next element in the stack.
         *
         * @return the next element in the stack
         */
        @Override
        public E next() {
            itemsLeft--;
            return (E) array[itemsLeft];
        }
    }
}
