import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Playlist implements Cloneable, FilteredSongIterable, OrderedSongIterable {
    private ArrayList<Song> playlist;
    private ArrayList<Song> filterPlaylist;


    public Playlist(ArrayList<Song> playlist) {
        this.playlist = playlist;
        this.filterPlaylist = (ArrayList<Song>) this.playlist.clone();
    }

    public Playlist() {
        playlist = new ArrayList<>();
        filterPlaylist = (ArrayList<Song>) playlist.clone();

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
                    filterPlaylist.add(song);

                } else {
                    throw new SongAlreadyExistsException("The song is already in playlist");
                }
            } else {
                playlist.add(song);
                filterPlaylist.add(song);
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
                filterPlaylist.remove(i);
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
             int i = 0;
             while (i < filterPlaylist.size()){
                 if (!(filterPlaylist.get(i).getArtist().equals(artist))) {
                     filterPlaylist.remove(i);
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
            while (i < filterPlaylist.size()) {
                if (!(filterPlaylist.get(i).getGenre() == genre)) {
                    filterPlaylist.remove(i);
                } else {
                    i++;
                }
            }
        }
    }

    @Override
    public void filterDuration(int maxSeconds) {
        int i = 0;
        while (i < filterPlaylist.size()) {
            if (!(filterPlaylist.get(i).getSeconds() <= maxSeconds)) {
                filterPlaylist.remove(i);
            } else {
                i++;
            }
        }
    }

    @Override
    public void setScanningOrder(ScanningOrder scanningOrder) {
        if (filterPlaylist.size() > 0) {
            if (scanningOrder == ScanningOrder.ADDING) {
                // No need to modify the filterPlaylist since it is already in the order of addition.
                return;
            }

            if (scanningOrder == ScanningOrder.NAME) {
                filterPlaylist.sort(Comparator.comparing(Song::getName)
                        .thenComparing(Song::getArtist));
            } else if (scanningOrder == ScanningOrder.DURATION) {
                filterPlaylist.sort(Comparator.comparingInt(Song::getSeconds)
                        .thenComparing(Song::getName)
                        .thenComparing(Song::getArtist));
            }
        }
        /*
        if (filterPlaylist.size() > 0) {
            if (!(scanningOrder == ScanningOrder.ADDING)) {
                if (scanningOrder == ScanningOrder.NAME) {
                    ArrayList<ArrayList<Integer>> letters = new ArrayList<>();
                    for (int i = 0; i < filterPlaylist.size(); i++) {
                        for (int j = 0; j < filterPlaylist.get(i).getName().length(); j++) {
                            int lower = 0;
                            letters.add(new ArrayList<>());
                            if ((int) filterPlaylist.get(i).getName().charAt(j) != 0) {
                                if ((int) filterPlaylist.get(i).getName().charAt(j) < 97) {
                                    lower = (int) filterPlaylist.get(i).getName().charAt(j) + 32;
                                } else {
                                    lower = (int) filterPlaylist.get(i).getName().charAt(j);
                                }
                            }
                            letters.get(i).add(lower);
                        }
                    }
                    ArrayList<Song> playlistName = new ArrayList<>();
                    for (int i = 0; i < filterPlaylist.size(); i++) {
                        for (int j = 0; j < letters.size(); j++) {
                            for (int k = 0; )
                        }
                    }

                } else {
                    ArrayList<Song> playlistDuration = new ArrayList<>();
                    int temp = 0;
                    ArrayList<Song> song = new ArrayList<>();
                    for (int i = 0; i < filterPlaylist.size(); i++) {
                        temp = 0;
                        song = song = new new ArrayList<>();
                        for (int j = filterPlaylist.size() - 1; j > 0; j--) {
                            if (filterPlaylist.get(j).getSeconds() >= temp) {
                                temp = filterPlaylist.get(j).getSeconds();
                                song.add(filterPlaylist.get(i));
                            }
                            if (song.size() > 1) {

                            }
                            playlistDuration.set(i, song);
                        }
                    }
                    //...
                }
            }
        }
         */
    }

    @Override
    public Iterator<Song> iterator() {
        return new PlaylistIterator();
    }

    public class PlaylistIterator implements Iterator<Song> {

        private int isLast;
        private int isFirst;
        private String filterAs;
        public PlaylistIterator(String filterAs) {
            this.filterAs = filterAs;
        }

        public PlaylistIterator() {
            isLast = filterPlaylist.size();
        }

        public PlaylistIterator(String filterAs, String str) {

        }

        @Override
        public boolean hasNext() {
            if (filterPlaylist != null) {
                if (isFirst < filterPlaylist.size()) {
                    return true;
                } else {
                    filterPlaylist = (ArrayList<Song>) playlist.clone();
                    return false;
                }
            } else {
                return false;
            }
        }

        @Override
        public Song next() {
            Song temp = filterPlaylist.get(isFirst);
            isFirst++;
            return temp;
        }
    }
}
