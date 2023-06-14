/**
 * An iterable collection of songs that can be filtered based on various criteria.
 */
public interface FilteredSongIterable extends Iterable<Song>{

    /**
     * Filters the songs by the specified artist.
     *
     * @param artist the artist name to filter by
     */
    public void filterArtist(String artist);

    /**
     * Filters the songs by the specified genre.
     *
     * @param genre the genre to filter by
     */
    public void filterGenre(Song.Genre genre);

    /**
     * Filters the songs by the maximum duration in seconds.
     *
     * @param maxSeconds the maximum duration in seconds to filter by
     */
    public void filterDuration(int maxSeconds);
}
