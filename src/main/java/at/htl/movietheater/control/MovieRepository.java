package at.htl.movietheater.control;

import at.htl.movietheater.entity.Movie;

public class MovieRepository {

    public Movie save(Movie movie) {
        return null;
    }

    /**
     * use a NamedQuery "Movie.findByTitle" to retrieve the Movie by title
     * when the NoResultException is thrown, return null
     *
     * @param title
     * @return the movie (with the given title) or null, when the title is not in the db
     */
    public Movie findByTitle(String title) {
        return null;
    }
}
