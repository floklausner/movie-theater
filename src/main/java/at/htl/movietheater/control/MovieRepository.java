package at.htl.movietheater.control;

import at.htl.movietheater.entity.Movie;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@ApplicationScoped
public class MovieRepository {

    @Inject
    EntityManager entityManager;

    @Transactional
    public Movie save(Movie movie) {
       return entityManager.merge(movie);
    }

    /**
     * use a NamedQuery "Movie.findByTitle" to retrieve the Movie by title
     * when the NoResultException is thrown, return null
     *
     * @param title
     * @return the movie (with the given title) or null, when the title is not in the db
     */
    public Movie findByTitle(String title) {

        try {
            TypedQuery<Movie> movieTypedQuery = entityManager
                    .createNamedQuery("Movie.findByTitle", Movie.class)
                    .setParameter("TITLE", title);
            return movieTypedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
