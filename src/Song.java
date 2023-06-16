/**
 * A class that represents a songs(with its requested attributes).
 */
public class Song implements Cloneable {
    private final String name;
    private final String artist;
    private Genre genre;
    private int seconds;
    private String duration;

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

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setDuration(int seconds) {
        int mm = seconds / 60;
        int ss = seconds - (60 * mm);
        this.seconds = seconds;
        this.duration = mm + ":" + ss;
    }

    public int getSeconds() {
        return seconds;
    }

    /**
     * An inner enum that represents the genre of a song.
     */
    public enum Genre {
        POP,
        ROCK,
        HIP_HOP,
        COUNTRY,
        JAZZ,
        DISCO
    }

    /**
     * Returns a string that represents the song.
     *
     * @return a string that represents the song
     */
    @Override
    public String toString() {
        return name + ", " + artist + ", " + genre + ", " + duration;
    }

    /**
     * Creates and returns a deep copy of the object song.
     *
     * @return a deep copy of the object song
     */
    @Override
    public Song clone() {
        try {
            return (Song) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * Checks if the current song object is equal to other provided object.
     * In according to the provided instructions.
     *
     * @param other the object provided to compare to
     * @return true if the current song object is equal to the other provided object, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if(!(this.hashCode() == other.hashCode()) || !(other instanceof Song)) {
            return false;
        }

        Song otherSong = (Song) other;
        return name.equals(otherSong.name) && artist.equals(otherSong.artist);
    }

    /**
     * Returns the hash code value for the current Song object.
     * In according to the provided instructions.
     *
     * @return the hash code value for the current Song object
     */
    @Override
    public int hashCode() {
        /* Sum up the ascii value of each letter in the name. */
        int nameAsciiValue = 0;
        for (int i = 0; i < name.length(); i++) {
            nameAsciiValue += name.charAt(i);
        }

        /* Sum up the ascii value of each letter in the artist. */
        int artistAsciiValue = 0;
        for (int i = 0; i < artist.length(); i++) {
            artistAsciiValue += artist.charAt(i);
        }

        /* Multiply by 3 (prime number) to ensure difference. */
        return 3 * (nameAsciiValue + artistAsciiValue);
    }
}
