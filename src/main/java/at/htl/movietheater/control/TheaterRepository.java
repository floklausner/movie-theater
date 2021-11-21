package at.htl.movietheater.control;

import at.htl.movietheater.entity.Theater;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@ApplicationScoped
public class TheaterRepository {

    @Inject
    EntityManager entityManager;

    /**
     * search the theater in the database.
     * - when it is already persisted, return the existing theater
     * - when the theater.name is not found, persist the theater object
     *
     * @param theater
     * @return the existing theater, when theater.name already exists in db
     * or return the persisted theater, when not found in database
     */

   @Transactional
    public Theater save(Theater theater) {

        Theater theater1 = findByName(theater.getName());

        if (theater1 != null) {
            return theater1;
        } else {
            return entityManager.merge(theater);
        }

    }

    /**
     * use a NamedQuery "Theater.findByName" to retrieve the Theater by name
     * when the NoResultException is thrown, return null
     *
     * @param name
     * @return the theater (with the given name) or null, when the name is not in the db
     */
    public Theater findByName(String name) {
        try {
            TypedQuery<Theater> theaterTypedQuery = entityManager
                    .createNamedQuery("Theater.findByName", Theater.class)
                    .setParameter("NAME", name);
            return theaterTypedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
