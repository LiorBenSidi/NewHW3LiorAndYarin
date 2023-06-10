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
