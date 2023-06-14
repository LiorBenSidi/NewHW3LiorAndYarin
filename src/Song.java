/**
 * A class representing a song.
 */
public class Song implements Cloneable {
    private final String name;
    private final String artist;
    private Genre genre;
    private int seconds;
    private String duration;

    /**
     * Constructs a Song object with the specified name, artist, genre, and duration in seconds.
     *
     * @param name     the name of the song
     * @param artist   the artist of the song
     * @param genre    the genre of the song
     * @param seconds  the duration of the song in seconds
     */
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


    /**
     * Returns the name of the song.
     *
     * @return the name of the song
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the artist of the song.
     *
     * @return the artist of the song
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Returns the genre of the song.
     *
     * @return the genre of the song
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * Sets the genre of the song.
     *
     * @param genre the genre of the song
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    /**
     * Sets the duration of the song in seconds.
     *
     * @param seconds the duration of the song in seconds
     */
    public void setDuration(int seconds) {
        int mm = seconds / 60;
        int ss = seconds - (60 * mm);
        this.seconds = seconds;
        this.duration = mm + ":" + ss;
    }

    /**
     * Returns the duration of the song in seconds.
     *
     * @return the duration of the song in seconds
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * An enumeration representing the genre of a song.
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
     * Returns a string representation of the song.
     *
     * @return a string representation of the song
     */
    @Override
    public String toString() {
        return name + ", " + artist + ", " + genre + ", " + duration;
    }

    /**
     * Creates and returns a copy of this Song object.
     *
     * @return a copy of this Song object
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
     * Checks if this Song object is equal to the specified object.
     *
     * @param other the object to compare with this Song object
     * @return true if the objects are equal, false otherwise
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
     * Returns the hash code value for this Song object.
     *
     * @return the hash code value for this Song object
     */
    @Override
    public int hashCode() {
        int nameAsciiValue = 0;
        for (int i = 0; i < name.length(); i++) {
            nameAsciiValue += name.charAt(i);
        }
        int artistAsciiValue = 0;
        for (int i = 0; i < artist.length(); i++) {
            artistAsciiValue += artist.charAt(i);
        }

        return 3 * (nameAsciiValue + artistAsciiValue);
    }
}
