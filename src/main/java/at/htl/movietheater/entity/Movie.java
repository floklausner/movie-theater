package at.htl.movietheater.entity;

public class Movie {
    private Long id;

    private String title;

    private double duration;

    private Genre genre;

    private int ageLimit;

    public Movie() {
    }

    public Movie(String title, double duration, Genre genre, int ageLimit) {
        this.title = title;
        this.duration = duration;
        this.genre = genre;
        this.ageLimit = ageLimit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", title, genre);
    }
}
