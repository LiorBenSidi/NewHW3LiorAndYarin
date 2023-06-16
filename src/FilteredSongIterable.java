/**
 * An interface that represents an iterable collection of songs that can be filtered.
 */
public interface FilteredSongIterable extends Iterable<Song>{

    /**
     * Filters the playlist by the attribute artist of the songs.
     *
     * @param artist the artist provided to filter the playlist by
     */
    void filterArtist(String artist);

    /**
     * Filters the playlist by the attribute genre of the songs.
     *
     * @param genre the genre provided to filter the playlist by
     */
    void filterGenre(Song.Genre genre);

    /**
     * Filters the playlist by the maximum duration(by seconds).
     *
     * @param maxSeconds the maximum duration in seconds
     */
    void filterDuration(int maxSeconds);
}
