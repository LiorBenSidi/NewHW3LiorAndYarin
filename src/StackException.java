/**
 * Exception thrown to indicate an error or exceptional condition in a stack operation.
 */
public class StackException extends RuntimeException{

    /**
     * Constructs a new StackException with no detail message.
     */
    public StackException() {
    }

    /**
     * Constructs a new StackException with the specified detail message.
     *
     * @param message the detail message
     */
    public StackException(String message) {
        super(message);
    }

    /**
     * Constructs a new StackException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public StackException(String message, Throwable cause) {
        super(message, cause);
    }
}
