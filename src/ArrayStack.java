import java.util.Iterator;

public class ArrayStack<T extends Cloneable> implements Stack<T> {
    private int counterOfItems;
    private Cloneable[] array;
    private int maxCapacity;

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
                array[this.size() + 1] = element;
                counterOfItems++;
            } else {
                throw new StackOverflowException("The size of the array-stack reached it's maximum capacity");
            }
        } catch (StackOverflowException stackOverflowException) {
            throw stackOverflowException;
        }
    }

    @Override
    public Cloneable pop() throws EmptyStackException {
        try {
            if (!this.isEmpty()) {
                Cloneable temp = array[this.size() - 1];
                array[this.size() - 1] = null;
                counterOfItems--;
                return temp;
            } else {
                throw new EmptyStackException("The array-stack is empty");
            }
        } catch (EmptyStackException emptyStackException) {
            throw emptyStackException;
            }
    }

    @Override
    public Cloneable peek() throws EmptyStackException {
        try {
            if (!this.isEmpty()) {
                return array[this.size() - 1];
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
    public ArrayStack clone() {
        try {
            ArrayStack copy = (ArrayStack) super.clone();
            copy.array = array.clone();
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public Iterator iterator() {
        return new StackIterator<>(this);
    }
    public class StackIterator<T> implements Iterator<T> {
        private ArrayStack arrayStack;
        private int itemLeft;

        public StackIterator(ArrayStack arrayStack) {
            this.arrayStack = arrayStack;
            this.itemLeft = arrayStack.size();
        }

        @Override
        public boolean hasNext() {
            return itemLeft > 0;
        }

        @Override
        public T next() {
            Cloneable temp = arrayStack.array[itemLeft - 1];
            itemLeft--;
            return (T) temp;
        }
    }
}
