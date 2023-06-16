/**
 * A class that represents an unchecked exception thrown when attempting to perform an operation on an empty stack.
 * Extends 'StackException' class.
 */
public class EmptyStackException extends StackException{

    public EmptyStackException() {
    }

    public EmptyStackException(String message) {
        super(message);
    }

    public EmptyStackException(String message, Throwable cause) {
        super(message, cause);
    }
}
