import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * A class that represents a playlist of songs.
 */
public class Playlist implements Cloneable, FilteredSongIterable, OrderedSongIterable {
    private ArrayList<Song> playlist;
    private ArrayList<Song> filteredPlaylist;

    public Playlist() {
        playlist = new ArrayList<>();
        filteredPlaylist = new ArrayList<>();

    }

    /**
     * Adds a song to the playlist.
     *
     * @param song the song we want to add
     * @throws SongAlreadyExistsException  if the song we want to add is already exists in the playlist
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
     * @param song the song we want to remove
     * @return true if the song was able to remove successfully, false otherwise
     */
    public boolean removeSong(Song song) {
        boolean isRemoved = playlist.remove(song);
        filteredPlaylist.remove(song);
        return isRemoved;
    }


    /**
     * Returns a string that represents the playlist.
     *
     * @return a string that represents the playlist
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
     * Creates and returns a deep copy of the object playlist.
     *
     * @return a deep copy of the object playlist
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
     * Checks if the current playlist object is equal to other provided object.
     * In according to the provided instructions.
     *
     * @param other the object provided to compare to
     * @return true if the current playlist object is equal to the other provided object, false otherwise
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
     * Returns the hash code value of the current playlist object.
     * In according to the provided instructions.
     *
     * @return the hash code of the current playlist object
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
     * Filters the playlist by the attribute artist of the songs.
     *
     * @param artist the artist provided to filter the playlist by
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
     * Filters the playlist by the attribute genre of the songs.
     *
     * @param genre the genre provided to filter the playlist by
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
     * Filters the playlist by the maximum duration(by seconds).
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
     * Sets the scanning order for the playlist according to the provided requested order.
     * Also, in according to the provided instructions.
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
     * Returns an iterator to use for iterating over the songs in the playlist.
     *
     * @return an iterator to use for iterating over the songs in the playlist
     */
    @Override
    public Iterator<Song> iterator() {
        return new PlaylistIterator();
    }

    /**
     * An inner class, that represents an iterator for iterating over the songs in the playlist.
     */
    public class PlaylistIterator implements Iterator<Song> {
        private int counter;

        public PlaylistIterator() {
            counter = 0;
        }

        /**
         * Checks if there are remaining songs to iterate over.
         *
         * @return true if there are remaining songs to iterate over, false otherwise
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
         * If there are remaining songs to iterate over, returns the next song in the iteration.
         *
         * @return the next song in the iteration
         */
        @Override
        public Song next() {
            counter++;
            return filteredPlaylist.get(counter - 1);
        }
    }
}
