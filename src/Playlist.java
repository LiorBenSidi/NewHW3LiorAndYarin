import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Playlist implements Cloneable, FilteredSongIterable, OrderedSongIterable {
    private ArrayList<Song> playlist;
    private ArrayList<Song> filteredPlaylist;

    public Playlist() {
        playlist = new ArrayList<>();
        filteredPlaylist = new ArrayList<>();

    }

    public void addSong(Song song) throws SongAlreadyExistsException {
        if (playlist.contains(song)) {
            throw new SongAlreadyExistsException("The song is already in the playlist");
        }
        playlist.add(song);
        filteredPlaylist.add(song);
        /*
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
                    filteredPlaylist.add(song);

                } else {
                    throw new SongAlreadyExistsException("The song is already in playlist");
                }
            } else {
                playlist.add(song);
                filteredPlaylist.add(song);
            }
        } catch (SongAlreadyExistsException songAlreadyExistsException) {
            throw songAlreadyExistsException;
        }
         */
    }

    public boolean removeSong(Song song) {
        boolean isRemoved = playlist.remove(song);
        filteredPlaylist.remove(song);
        return isRemoved;
        /*
        boolean isSongExist = false;
        for (int i = 0; (i < playlist.size()) && !isSongExist; i++) {
            if (song.equals(playlist.get(i))) {
                isSongExist = true;
                playlist.remove(i);
                filteredPlaylist.remove(i);
            }
        }
        return isSongExist;
         */
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
        try {
            Playlist copy = (Playlist) super.clone();
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

    @Override
    public Iterator<Song> iterator() {
        return new PlaylistIterator();
    }

    public class PlaylistIterator implements Iterator<Song> {

        private int counter;

        public PlaylistIterator() {
            counter = 0;
        }

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

        @Override
        public Song next() {
            counter++;
            return filteredPlaylist.get(counter - 1);
        }
    }
}
