/**
 * A class that represents an unchecked exception thrown when attempting to add a song that already exists in a playlist.
 */
public class SongAlreadyExistsException extends RuntimeException {

    public SongAlreadyExistsException() {
    }

    public SongAlreadyExistsException(String message) {
        super(message);
    }

    public SongAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
