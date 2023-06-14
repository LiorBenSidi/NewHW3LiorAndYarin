import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * A class representing a playlist of songs.
 */
public class Playlist implements Cloneable, FilteredSongIterable, OrderedSongIterable {
    private ArrayList<Song> playlist;
    private ArrayList<Song> filteredPlaylist;

    /**
     * Constructs an empty Playlist.
     */
    public Playlist() {
        playlist = new ArrayList<>();
        filteredPlaylist = new ArrayList<>();

    }

    /**
     * Adds a song to the playlist.
     *
     * @param song the song to be added
     * @throws SongAlreadyExistsException if the song already exists in the playlist
     */
    public void addSong(Song song) {
        if (playlist.contains(song)) {
            throw new SongAlreadyExistsException();
        }
        playlist.add(song);
        filteredPlaylist.add(song);
    }

    /**
     * Removes a song from the playlist.
     *
     * @param song the song to be removed
     * @return true if the song was removed successfully, false otherwise
     */
    public boolean removeSong(Song song) {
        boolean isRemoved = playlist.remove(song);
        filteredPlaylist.remove(song);
        return isRemoved;
    }


    /**
     * Returns a string representation of the playlist.
     *
     * @return a string representation of the playlist
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < playlist.size(); i++) {
            builder.append("(" ).append(playlist.get(i).toString()).append(")");
            if (i < playlist.size() -1) {
                builder.append(", ");
            }
        }
        return "[" + builder + "]";

    }

    /**
     * Creates a clone of the playlist.
     *
     * @return a clone of the playlist
     */
    @Override
    public Playlist clone() {
        try {
            Playlist copy = (Playlist) super.clone();
            copy.playlist = (ArrayList<Song>) copy.playlist.clone();
            for (int i = 0; i < playlist.size(); i++) {
                copy.playlist.set(i, copy.playlist.get(i).clone());
            }
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * Checks if the playlist is equal to another object.
     *
     * @param other the object to compare
     * @return true if the playlist is equal to the other object, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if(!(this.hashCode() == other.hashCode()) || !(other instanceof Playlist)) {
            return false;
        }

        ArrayList<Song> otherPlaylist = ((Playlist) other).playlist;
        boolean isSamePlaylistSong = false;
        boolean nextCheck = true;
        for (int i = 0; i < playlist.size(); i++) {
            for (int j = 0; (j < playlist.size()) && nextCheck; j++) {
                if (otherPlaylist.get(i).equals(playlist.get(j))) {
                    isSamePlaylistSong = true;
                    nextCheck = false;
                }
            }
            if(isSamePlaylistSong) {
                nextCheck = true;
            } else {
                return false;
            }

        }
        return true;
    }

    /**
     * Returns the hash code of the playlist.
     *
     * @return the hash code of the playlist
     */
    @Override
    public int hashCode() {
        int sum = 0;
        for (Song song : playlist) {
            sum += song.hashCode();
        }
        return sum;
    }

    /**
     * Filters the playlist by artist.
     *
     * @param artist the artist to filter by
     */
    @Override
    public void filterArtist(String artist) {
         if (artist != null) {
             int i = 0;
             while (i < filteredPlaylist.size()){
                 if (!(filteredPlaylist.get(i).getArtist().equals(artist))) {
                     filteredPlaylist.remove(i);
                 } else {
                     i++;
                 }
             }
         }
    }

    /**
     * Filters the playlist by genre.
     *
     * @param genre the genre to filter by
     */
    @Override
    public void filterGenre(Song.Genre genre) {
        if (genre != null){
            int i = 0;
            while (i < filteredPlaylist.size()) {
                if (!(filteredPlaylist.get(i).getGenre() == genre)) {
                    filteredPlaylist.remove(i);
                } else {
                    i++;
                }
            }
        }
    }

    /**
     * Filters the playlist by maximum duration.
     *
     * @param maxSeconds the maximum duration in seconds
     */
    @Override
    public void filterDuration(int maxSeconds) {
        int i = 0;
        while (i < filteredPlaylist.size()) {
            if (!(filteredPlaylist.get(i).getSeconds() <= maxSeconds)) {
                filteredPlaylist.remove(i);
            } else {
                i++;
            }
        }
    }


    /**
     * Sets the scanning order for the playlist.
     *
     * @param scanningOrder the scanning order to be set
     */
    @Override
    public void setScanningOrder(ScanningOrder scanningOrder) {
        if (filteredPlaylist.size() > 0) {
            if (scanningOrder == ScanningOrder.ADDING) {
                return;
            }

            if (scanningOrder == ScanningOrder.NAME) {
                filteredPlaylist.sort(Comparator.comparing(Song::getName)
                        .thenComparing(Song::getArtist));
            } else if (scanningOrder == ScanningOrder.DURATION) {
                filteredPlaylist.sort(Comparator.comparingInt(Song::getSeconds)
                        .thenComparing(Song::getName)
                        .thenComparing(Song::getArtist));
            }
        }
    }

    /**
     * Returns an iterator over the songs in the playlist.
     *
     * @return an iterator over the songs in the playlist
     */
    @Override
    public Iterator<Song> iterator() {
        return new PlaylistIterator();
    }

    /**
     * An iterator for iterating over the songs in the playlist.
     */
    public class PlaylistIterator implements Iterator<Song> {
        private int counter;

        /**
         * Constructs a PlaylistIterator.
         */
        public PlaylistIterator() {
            counter = 0;
        }

        /**
         * Checks if there are more songs to iterate over.
         *
         * @return true if there are more songs, false otherwise
         */
        @Override
        public boolean hasNext() {
            if (filteredPlaylist != null) {
                if (counter < filteredPlaylist.size()) {
                    return true;
                } else {
                    filteredPlaylist = new ArrayList<>(playlist);
                    return false;
                }
            } else {
                return false;
            }
        }

        /**
         * Returns the next song in the iteration.
         *
         * @return the next song
         */
        @Override
        public Song next() {
            counter++;
            return filteredPlaylist.get(counter - 1);
        }
    }
}
