public interface FilteredSongIterable extends Iterable<Song>{
    public void filterArtist(String artist);
    public void filterGenre(Song.Genre genre);
    public void filterDuration(String duration);
    public  enum ScanningOrder {
        ADDING,
        NAME,
        DURATION
    }
}
