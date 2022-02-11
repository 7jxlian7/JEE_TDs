package fr.iut.rm.control;

import com.google.inject.Inject;
import com.google.inject.persist.UnitOfWork;
import fr.iut.rm.persistence.dao.RoomDao;
import fr.iut.rm.persistence.domain.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by cjohnen on 02/02/17.
 */
public class ControlRoom {
    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(ControlRoom.class);

    /**
     * Unit of work is used to drive DB Connection
     */
    @Inject
    UnitOfWork unitOfWork;

    /**
     * Data Access Object for rooms
     */
    @Inject
    RoomDao roomDao;

     /*
     * * Displays all the rooms content in DB
     */
    public void showRooms() {
        unitOfWork.begin();

        List<Room> rooms = roomDao.findAll();
        if (rooms.isEmpty()) {
            System.out.println("No room");
        } else {
            System.out.println("Rooms :");
            System.out.println("--------");
            for (Room room : rooms) {
                System.out.println(String.format("   [%d], name '%s' | description '%s'", room.getId(), room.getName(), room.getDescription()));
            }
        }

        unitOfWork.end();
    }

    /**
     * Creates a room in DB
     *
     * @param name the name of the room
     */
    public void createRoom(final String name, final String description) {
        unitOfWork.begin();

        // TODO check unicity
        if(roomDao.findByName(name) == null) {
            Room room = new Room();
            room.setName(name);
            room.setDescription(description);
            roomDao.saveOrUpdate(room);
        } else {
            System.out.println(String.format("Room '%s' already exists", name));
        }

        unitOfWork.end();
    }

    /**
     * Removes a room in DB
     *
     * @param name the name of the room
     */
    public void removeRoom(final String name) {
        unitOfWork.begin();
        Room r = roomDao.findByName(name);
        if(r != null) // if room exists
            roomDao.removeRoom(name);
        unitOfWork.end();
    }

}