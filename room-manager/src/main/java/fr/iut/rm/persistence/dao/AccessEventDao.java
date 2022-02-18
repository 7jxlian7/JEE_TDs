package fr.iut.rm.persistence.dao;

import fr.iut.rm.persistence.domain.AccessEvent;
import fr.iut.rm.persistence.domain.Room;

import java.util.List;

/**
 * Data Access Object of {@link fr.iut.rm.persistence.domain.AccessEvent}
 */
public interface AccessEventDao {

    /**
     * Persists access event.
     *
     * @param accessEvent object to persist
     */
    void saveOrUpdate(AccessEvent accessEvent);

    /**
     *
     * Find an access event
     *
     * @param username the searched person's name, must be case sensitive and exact match.
     *             Null name returns null result
     * @return the access event or null if nothing found
     */
    AccessEvent findLastBy(final String username);

    /**
     *
     * Find all room's access event
     *
     * @param room the searched room, must be case sensitive and exact match.
     *             Null name returns null result
     * @return all the access events or null if nothing found
     */
    List<AccessEvent> findAllByRoom(final Room room);

    /**
     *
     * Find all room's access event for one person
     *
     * @param person the searched person, must be case sensitive and exact match.
     *             Null name returns null result
     * @return all the access events or null if nothing found
     */
    List<AccessEvent> findAllByPerson(final String person);
}
