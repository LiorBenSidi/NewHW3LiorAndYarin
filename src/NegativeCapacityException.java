/**
 * A class that represents an unchecked exception thrown when a negative capacity is provided for a stack.
 * Extends 'StackException' class.
 */
public class NegativeCapacityException extends StackException{

    public NegativeCapacityException() {
    }

    public NegativeCapacityException(String message) {
        super(message);
    }

    public NegativeCapacityException(String message, Throwable cause) {
        super(message, cause);
    }
}
