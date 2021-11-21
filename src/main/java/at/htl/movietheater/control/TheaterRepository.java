package at.htl.movietheater.control;

import at.htl.movietheater.entity.Theater;

public class TheaterRepository {

    /**
     * search the theater in the database.
     * - when it is already persisted, return the existing theater
     * - when the theater.name is not found, persist the theater object
     *
     * @param theater
     * @return the existing theater, when theater.name already exists in db
     * or return the persisted theater, when not found in database
     */
    public Theater save(Theater theater) {
        return null;
    }

    /**
     * use a NamedQuery "Theater.findByName" to retrieve the Theater by name
     * when the NoResultException is thrown, return null
     *
     * @param name
     * @return the theater (with the given name) or null, when the name is not in the db
     */
    public Theater findByName(String name) {
        return null;
    }
}
