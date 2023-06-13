import java.lang.reflect.Method;
import java.util.Iterator;

public class ArrayStack<T extends Cloneable> implements Stack<T> {
    private int counterOfItems;
    private Cloneable[] array;
    private final int maxCapacity;

    public ArrayStack(int maxCapacity) throws NegativeCapacityException {
        try {
            if (maxCapacity >= 0) {
                this.maxCapacity = maxCapacity;
                array = new Cloneable[maxCapacity];
            } else {
                throw new NegativeCapacityException("The provided capacity is negative");
            }
        } catch (NegativeCapacityException negativeCapacityException) {
            throw negativeCapacityException;
        }
    }

    @Override
    public void push(Cloneable element) throws StackOverflowException{
        try {
            if (this.size() < maxCapacity) {
                array[this.size()] = element;
                counterOfItems++;
            } else {
                throw new StackOverflowException("The size of the array-stack reached it's maximum capacity");
            }
        } catch (StackOverflowException stackOverflowException) {
            throw stackOverflowException;
        }
    }

    @Override
    public T pop() throws EmptyStackException {
        try {
            if (!this.isEmpty()) {
                Cloneable temp = array[this.size() - 1];
                array[this.size() - 1] = null;
                counterOfItems--;
                return (T) temp;
            } else {
                throw new EmptyStackException("The array-stack is empty");
            }
        } catch (EmptyStackException emptyStackException) {
            throw emptyStackException;
            }
    }

    @Override
    public T peek() throws EmptyStackException {
        try {
            if (!isEmpty()) {
                return (T) array[size() - 1];
            } else {
                throw new EmptyStackException("The array-stack is empty");
            }
        } catch (EmptyStackException emptyStackException) {
            throw emptyStackException;
        }
    }

    @Override
    public int size() {
        return counterOfItems;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public ArrayStack<T> clone() {
        ArrayStack<T> copy;
        try {
            copy = (ArrayStack<T>) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }

        copy.array = new Cloneable[maxCapacity];

        for (int i = 0; i < counterOfItems; i++) {
            try {
                Method method = array[i].getClass().getMethod("clone");
                copy.array[i] = (Cloneable) method.invoke(array[i]);
            } catch (Exception e) {
                return null;
            }
        }
        return copy;
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }
    public class StackIterator implements Iterator<T> {
        private int itemLeft;

        public StackIterator() {
            this.itemLeft = size();
        }

        @Override
        public boolean hasNext() {
            return itemLeft > 0;
        }

        @Override
        public T next() {
            itemLeft--;
            return (T) array[itemLeft];
        }
    }
}
