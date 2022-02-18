package fr.iut.rm.persistence.dao.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import fr.iut.rm.persistence.dao.AccessEventDao;
import fr.iut.rm.persistence.domain.AccessEvent;
import fr.iut.rm.persistence.domain.AccessEvent_;
import fr.iut.rm.persistence.domain.Room;
import fr.iut.rm.persistence.domain.Room_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Provider;
import javax.persistence.EntityManager;
import java.util.List;


/**
 * Implementation of {@link fr.iut.rm.persistence.dao.AccessEventDao}
 */
@Singleton
public class AccessEventDaoImpl implements AccessEventDao {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(AccessEventDaoImpl.class);

    /**
     * Entity Manager used to perform database operations
     */
    @Inject
    private Provider<EntityManager> em;

    /**
     *
     * @param accessEvent access event to persist
     */
    @Override
    @Transactional
    public void saveOrUpdate(final AccessEvent accessEvent) {
        this.em.get().persist(accessEvent);
        logger.debug("Access Event with person '{}' on room '{}' saved", accessEvent.getPersonName(), accessEvent.getRoom().getName());
    }

    /**
     * Find an accessEvent
     *
     * @param username name of the user
     * @param room room
     * @return the corresponding access event or null if nothing found
     */
    @Override
    @Transactional
    public AccessEvent findLastBy(final String username) {
        StringBuilder query = new StringBuilder("from ");
        query.append(AccessEvent.class.getName()).append(" as accessEvent");
        query.append(" where accessEvent.").append(AccessEvent_.personName.getName()).append(" = :username");
        query.append(" order by date desc");

        List<AccessEvent> resultList = em.get().createQuery(query.toString()).setParameter("username", username).getResultList();

        if (resultList.size() > 0) {
            logger.debug("Access event with user '{}' on room '{}' found", username, resultList.get(0).getRoom().getName());
            return resultList.get(0);
        }
        logger.debug("No access event with user '{}' found", username);
        return null;
    }

    /**
     *
     * Find all room's access event
     *
     * @param room the searched room, must be case sensitive and exact match.
     *             Null name returns null result
     * @return all the access events or null if nothing found
     */
    public List<AccessEvent> findAllByRoom(final Room room){
        StringBuilder query = new StringBuilder("from ");
        query.append(AccessEvent.class.getName()).append(" as accessEvent");
        query.append(" where accessEvent.").append(AccessEvent_.room.getName()).append(" = :room");
        query.append(" order by date desc");

        List<AccessEvent> resultList = em.get().createQuery(query.toString()).setParameter("room", room).getResultList();
        logger.debug("Access events on room '{}' found", room.getName());
        return resultList;
    }

    /**
     *
     * Find all room's access event for one person
     *
     * @param person the searched person, must be case sensitive and exact match.
     *             Null name returns null result
     * @return all the access events or null if nothing found
     */
    public List<AccessEvent> findAllByPerson(final String person){
        StringBuilder query = new StringBuilder("from ");
        query.append(AccessEvent.class.getName()).append(" as accessEvent");
        query.append(" where accessEvent.").append(AccessEvent_.personName.getName()).append(" = :person");
        query.append(" order by date desc");

        List<AccessEvent> resultList = em.get().createQuery(query.toString()).setParameter("person", person).getResultList();
        logger.debug("Access events for person %s found", person);
        return resultList;
    }
}
