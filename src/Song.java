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
            return (Song) super.clone();
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
        return name.equals(otherSong.name) && artist.equals(otherSong.artist);
    }

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
