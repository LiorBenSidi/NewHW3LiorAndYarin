/**
 * An interface for an iterable collection of songs that can be ordered.
 */
public interface OrderedSongIterable extends Iterable<Song> {

    /**
     * Sets the scanning order for the songs.
     *
     * @param scanningOrder the scanning order to be set
     */
    public void setScanningOrder(ScanningOrder scanningOrder);

}
