/**
 * An exception thrown when a negative capacity is provided for a stack.
 */
public class NegativeCapacityException extends StackException{


    /**
     * Constructs a NegativeCapacityException with no specified detail message.
     */
    public NegativeCapacityException() {
    }

    /**
     * Constructs a NegativeCapacityException with the specified detail message.
     *
     * @param message the detail message
     */
    public NegativeCapacityException(String message) {
        super(message);
    }

    /**
     * Constructs a NegativeCapacityException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public NegativeCapacityException(String message, Throwable cause) {
        super(message, cause);
    }
}
