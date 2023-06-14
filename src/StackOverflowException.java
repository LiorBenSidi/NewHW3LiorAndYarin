/**
 * Exception thrown to indicate that a stack has reached its maximum capacity and a push operation cannot be performed.
 */
public class StackOverflowException extends StackException {

    /**
     * Constructs a new StackOverflowException with no detail message.
     */
    public StackOverflowException() {
    }

    /**
     * Constructs a new StackOverflowException with the specified detail message.
     *
     * @param message the detail message
     */
    public StackOverflowException(String message) {
        super(message);
    }

    /**
     * Constructs a new StackOverflowException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public StackOverflowException(String message, Throwable cause) {
        super(message, cause);
    }
}
