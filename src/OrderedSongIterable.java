public interface OrderedSongIterable extends Iterable<Song> {
    public void setScanningOrder(FilteredSongIterable.ScanningOrder scanningOrder);

}
