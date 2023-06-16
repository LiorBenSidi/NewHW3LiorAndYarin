/**
 * A class that represents an unchecked exception thrown to indicate that a stack has reached its maximum capacity,
 * and a push operation cannot be performed.
 * Extends 'StackException' class.
 */
public class StackOverflowException extends StackException {

    public StackOverflowException() {
    }

    public StackOverflowException(String message) {
        super(message);
    }

    public StackOverflowException(String message, Throwable cause) {
        super(message, cause);
    }
}
