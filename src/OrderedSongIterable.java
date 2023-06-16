/**
 * An interface that represents an iterable collection of songs that can be ordered.
 */
public interface OrderedSongIterable extends Iterable<Song> {

    /**
     * Sets the scanning order for the playlist according to the provided requested order.
     * Also, in according to the provided instructions.
     *
     * @param scanningOrder the scanning order to be set
     */
    void setScanningOrder(ScanningOrder scanningOrder);

}
