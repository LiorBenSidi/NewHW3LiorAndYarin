import java.util.Objects;

public class Song implements Cloneable {
    private String name;
    private String artist;
    private Genre genre;
    private int seconds;
    private String duration;
    private Object[] song;

    public Song(String name, String artist, Genre genre, int seconds) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.seconds = seconds;
        int mm = seconds / 60;
        int ss = seconds - (60 * mm);
        if (ss / 10 == 0) {
            this.duration = mm + ":0" + ss;
        } else {
            this.duration = mm + ":" + ss;
        }
        song = new Object[]{name, artist, genre, duration};
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getDuration() {
        return duration;
    }

    public Object[] getSong() {
        return song;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setDuration(int seconds) {
        int mm = seconds / 60;
        int ss = seconds - (60 * mm);
        this.seconds = seconds;
        this.duration = mm + ":" + ss;
    }

    public void setSong(Object[] song) {
        this.song = song;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public enum Genre {
        POP,
        ROCK,
        HIP_HOP,
        COUNTRY,
        JAZZ,
        DISCO
    }

    @Override
    public String toString() {
        return name + ", " + artist + ", " + genre + ", " + duration;
    }

    @Override
    public Song clone() {
        try {
            Song copy = (Song) super.clone();
            copy.name = new String(name);
            copy.artist = new String(artist);
            copy.genre = genre;
            copy.duration = new String(duration);
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }


    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if(!(this.hashCode() == other.hashCode()) || !(other instanceof Song)) {
            return false;
        }

        Song otherSong = (Song) other;
        return (Objects.equals(name, otherSong.name)) && (Objects.equals(artist, otherSong.artist));
    }

    @Override
    public int hashCode() {
        /*
        String[] nameSplit = name.split("");
        String[] artistSplit = artist.split("");
        String[] genreSplit = String.valueOf(genre).split("");
        String[] durationSplit = duration.split(":");
         */
        int nameAsciiValue = 0;
        for (int i = 0; i < name.length(); i++) {
            nameAsciiValue += (int) name.charAt(i);
        }
        int artistAsciiValue = 0;
        for (int i = 0; i < artist.length(); i++) {
            artistAsciiValue += (int) artist.charAt(i);
        }
        /*
        String genreStr = String.valueOf(genre);
        int genreAsciiValue = 0;
        for (int i = 0; i < genreStr.length(); i++) {
            genreAsciiValue += (int) genreStr.charAt(i);
        }
        int durationAsciiValue = 0;
        for (int i = 0; i < duration.length(); i++) {
            durationAsciiValue += (int) duration.charAt(i);
        }
         */
        return 3 * (nameAsciiValue + artistAsciiValue /*+ genreAsciiValue + durationAsciiValue*/);
    }
}
