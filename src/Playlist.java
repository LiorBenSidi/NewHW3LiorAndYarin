import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Playlist implements Cloneable, FilteredSongIterable, OrderedSongIterable {
    private ArrayList<Song> playlist;

    public Playlist(ArrayList<Song> playlist) {
        this.playlist = playlist;
    }

    public Playlist() {
        playlist = new ArrayList<>();
    }

    public void addSong(Song song) throws SongAlreadyExistsException {
        try {
            boolean isSongExist = false;
            if (playlist != null) {
                for (int i = 0; (i < playlist.size()) && !isSongExist; i++) {
                    if (song.equals(playlist.get(i))) {
                        isSongExist = true;
                    }
                }
                if (!isSongExist) {
                    playlist.add(song);
                } else {
                    throw new SongAlreadyExistsException("The song is already in playlist");
                }
            } else {
                playlist.add(song);
            }
        } catch (SongAlreadyExistsException songAlreadyExistsException) {
            throw songAlreadyExistsException;
        }
    }

    public boolean removeSong(Song song) {
        boolean isSongExist = false;
        for (int i = 0; (i < playlist.size()) && !isSongExist; i++) {
            if (song.equals(playlist.get(i))) {
                isSongExist = true;
                playlist.remove(i);
            }
        }
        return isSongExist;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < playlist.size(); i++) {
            builder.append("(" + playlist.get(i).toString() + ")");
            if (i < playlist.size() -1) {
                builder.append(", ");
            }
        }
        return "[" + builder + "]";

    }

    @Override
    public Playlist clone() {
        Playlist pTemp = this;
        Playlist copy;
        try {
            copy = (Playlist) super.clone();
            copy.playlist = new ArrayList<>();
            for (Song song : this.playlist) {
                copy.playlist.add(song.clone());
            }
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }

        /*
        copy.playlist = new ArrayList<>();

        for (int i = 0; i < this.playlist.size(); i++) {
            Song temp = this.playlist.get(i);
            try {
                copy.playlist.set(i, playlist.get(i).clone());

                Method method = this.playlist.get(i).getClass().getMethod("clone",null );
                copy.playlist.set(i, (Song) method.invoke(this.playlist.get(i)));

            } catch (Exception e) {
                return null;
            }

        }
        return copy;
         */
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if(!(this.hashCode() == other.hashCode()) || !(other instanceof Playlist)) {
            return false;
        }

        ArrayList<Song> otherPlaylist = (ArrayList<Song>) ((Playlist) other).playlist;
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

    @Override
    public int hashCode() {
        int sum = 0;
        for (int i = 0; i < playlist.size(); i++) {
            sum += playlist.get(i).hashCode();
        }
        return sum;
    }

    @Override
    public void filterArtist(String artist) {
         if (artist != null) {
             for (int i = 0; i < playlist.size(); i++) {
                 if (!(playlist.get(i).getArtist() == artist)) {
                     playlist.remove(i);
                 }
             }
         }
    }

    @Override
    public void filterGenre(Song.Genre genre) {
        if (genre != null){
            for (int i = 0; i < playlist.size(); i++) {
                if (!(playlist.get(i).getGenre() == genre)) {
                    playlist.remove(i);
                }
            }
        }
    }

    @Override
    public void filterDuration(int maxSeconds) {
        for (int i = 0; i < playlist.size(); i++) {
            if (!(playlist.get(i).getSeconds() <= maxSeconds)) {
                playlist.remove(i);
            }
        }
    }

    @Override
    public void setScanningOrder(ScanningOrder scanningOrder) {
        if (!(scanningOrder == ScanningOrder.ADDING)) {
            if (scanningOrder == ScanningOrder.NAME) {
                ArrayList<Song> playlistName = new ArrayList<>();
                //...
            } else {
                ArrayList<Song> playlistDuration = new ArrayList<>();
                //...
            }
        }

    }

    @Override
    public Iterator<Song> iterator() {
        return new PlaylistIterator();
    }

    public class PlaylistIterator implements Iterator<Song> {

        private String filterAs;
        public PlaylistIterator(String filterAs) {
            this.filterAs = filterAs;
        }

        public PlaylistIterator() {
        }

        public PlaylistIterator(String filterAs, String str) {

        }

        @Override
        public boolean hasNext() {
            if (playlist != null) {
                return playlist.size() > 0;
            } else {
                return false;
            }
        }

        @Override
        public Song next() {
            return playlist.get(playlist.size() - 1);
        }
    }
}
