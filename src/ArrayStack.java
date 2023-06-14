import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * A stack implementation using an array.
 *
 * @param <E> the type of elements in the stack
 */
public class ArrayStack<E extends Cloneable> implements Stack<E> {
    private int counterOfItems;
    private Cloneable[] array;
    private final int maxCapacity;

    /**
     * Constructs an ArrayStack with the specified maximum capacity.
     *
     * @param maxCapacity the maximum capacity of the stack
     * @throws NegativeCapacityException if the provided capacity is negative
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
     * Pushes an element onto the top of the stack.
     *
     * @param element the element to be pushed
     * @throws StackOverflowException if the stack is already full
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
     * Removes and returns the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public E pop() {
        if (!this.isEmpty()) {
            Cloneable temp = array[this.size() - 1];
            array[this.size() - 1] = null;
            counterOfItems--;
            return (E) temp;
        } else {
            throw new EmptyStackException();
        }
    }

    /**
     * Returns the element at the top of the stack without removing it.
     *
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
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
     * Returns an iterator over the elements in the stack.
     *
     * @return an iterator over the elements in the stack
     */
    @Override
    public Iterator<E> iterator() {
        return new StackIterator();
    }

    /**
     * Iterator implementation for ArrayStack.
     */
    public class StackIterator implements Iterator<E> {
        private int itemsLeft;

        public StackIterator() {
            this.itemsLeft = size();
        }

        /**
         * Checks if there are more elements in the stack to iterate over.
         *
         * @return true if there are more elements, false otherwise
         */
        @Override
        public boolean hasNext() {
            return itemsLeft > 0;
        }

        /**
         * Returns the next element in the stack.
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
