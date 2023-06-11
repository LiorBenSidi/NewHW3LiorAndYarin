import java.lang.reflect.Method;
import java.util.Iterator;

public class Playlist implements Cloneable, FilteredSongIterable, OrderedSongIterable {
    private Song[] playlist;

    public Playlist(Song[] playlist) {
        this.playlist = playlist;
    }

    public Playlist() {
    }

    public void addSong(Song song) throws SongAlreadyExistsException {
        try {
            boolean isSongExist = false;
            for (int i = 0; (i < playlist.length) && !isSongExist; i++) {
                if (song.equals(playlist[i])) {
                    isSongExist = true;
                }
            }
            if (!isSongExist) {

            } else {
                throw new SongAlreadyExistsException("The song is already in playlist");
            }
        } catch (SongAlreadyExistsException songAlreadyExistsException) {
            throw songAlreadyExistsException;
        }
    }

    public boolean removeSong(Song song) {
        boolean isSongExist = false;
        for (int i = 0; (i < playlist.length) && !isSongExist; i++) {
            if (song.equals(playlist[i])) {
                isSongExist = true;
                playlist[i] = null;
                for (int j = i; j < playlist.length; j++) {
                    playlist[j] = playlist[j + 1];
                }
            }
        }
        return isSongExist;
    }

    @Override
    public String toString() {
        StringBuilder builder = null;
        for (int i = 0; i < playlist.length; i++) {
            builder.append(playlist[i].toString());
            if (i < playlist.length -1) {
                builder.append(", ");
            }
        }
        return "[" + builder + "]";

    }

    @Override
    public Playlist clone() {
        Playlist copy;
        try {
            copy = (Playlist) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }

        copy.playlist = new Song[playlist.length];

        for (int i = 0; i < this.playlist.length; i++) {
            try {
                Method method = this.playlist[i].getClass().getMethod("clone", null);
                copy.playlist[i] = (Song) method.invoke(this.playlist[i]);
            } catch (Exception e) {
                return null;
            }
        }
        return copy;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if(!(this.hashCode() == other.hashCode()) || !(other instanceof Playlist)) {
            return false;
        }

        Song[] otherPlaylist = (Song[]) other;
        boolean isSamePlaylistSong = false;
        boolean nextCheck = true;
        for (int i = 0; i < playlist.length; i++) {
            for (int j = 0; (j < playlist.length) && nextCheck; j++) {
                if (otherPlaylist[i].equals(playlist[j])) {
                    isSamePlaylistSong = true;
                    nextCheck = false;
                }
                if(isSamePlaylistSong) {
                    nextCheck = true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int sum = 0;
        for (int i = 0; i < playlist.length; i++) {
            sum += playlist[i].hashCode();
        }
        return sum;
    }

    @Override
    public void filterArtist(String artist) {

    }

    @Override
    public void filterGenre(Song.Genre genre) {

    }

    @Override
    public void filterDuration(String duration) {

    }

    @Override
    public Iterator<Song> iterator() {
        return null;
    }

    @Override
    public void setScanningOrder(ScanningOrder scanningOrder) {

    }

    public class PlaylistIterator implements Iterator<Song> {

        @Override
        public boolean hasNext() {

        }

        @Override
        public Song next() {

        }
    }
}
