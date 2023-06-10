public class Song implements Cloneable {
    private String name;
    private String artist;
    private Genre genre;
    private String duration;
    private Object[] song;

    public Song(String name, String artist, Genre genre, int seconds) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        int mm = seconds / 60;
        int ss = seconds - (60 * mm);
        this.duration = mm + ":" + ss;
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
        this.duration = mm + ":" + ss;
    }

    public void setSong(Object[] song) {
        this.song = song;
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
        return "(" + name + ", " + artist + ", " + genre + ", " + duration + ")";
    }

    @Override
    public Song clone() {
        Song copy;
        try {
            copy = (Song) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }

        copy.name = new String(name);
        copy.artist = new String(artist);
        copy.genre = genre;
        copy.duration = new String(duration);

        return copy;
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
        return (name == otherSong.name) && (artist == otherSong.artist);
    }

    @Override
    public int hashCode() {

    }
}
