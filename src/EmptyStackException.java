/**
 * An exception thrown when attempting to perform an operation on an empty stack.
 */
public class EmptyStackException extends StackException{

    /**
     * Constructs a new EmptyStackException with no detail message.
     */
    public EmptyStackException() {
    }

    /**
     * Constructs a new EmptyStackException with the specified detail message.
     *
     * @param message the detail message
     */
    public EmptyStackException(String message) {
        super(message);
    }

    /**
     * Constructs a new EmptyStackException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of this exception
     */
    public EmptyStackException(String message, Throwable cause) {
        super(message, cause);
    }
}
