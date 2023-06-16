/**
 * A class that represents an unchecked exception thrown to indicate an error in a stack operation.
 */
public class StackException extends RuntimeException{

    public StackException() {
    }

    public StackException(String message) {
        super(message);
    }

    public StackException(String message, Throwable cause) {
        super(message, cause);
    }
}
