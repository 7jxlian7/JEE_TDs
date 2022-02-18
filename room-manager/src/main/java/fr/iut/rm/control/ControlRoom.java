package fr.iut.rm.control;

import com.google.inject.Inject;
import com.google.inject.persist.UnitOfWork;
import fr.iut.rm.persistence.dao.AccessEventDao;
import fr.iut.rm.persistence.dao.RoomDao;
import fr.iut.rm.persistence.domain.AccessEvent;
import fr.iut.rm.persistence.domain.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
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

    /**
     * Data Access Object for access event
     */
    @Inject
    AccessEventDao accessEventDao;

    /**
     * Displays all the rooms content in DB
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
        // check unicity
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

    /**
     * Enter in a room.
     *
     * @param username name of the user to enter in the room
     * @param roomName name of the room to enter in
     */
    public void enterRoom(final String username, final String roomName) {
        unitOfWork.begin();
        // check if the room exist

        Room room = roomDao.findByName(roomName);
        if(room == null){
            System.out.println(String.format("Room '%s' does not exists", roomName));
            unitOfWork.end();
            return;
        }

        // getting the last activity of the person
        AccessEvent accessEvent = accessEventDao.findLastBy(username);
        if(accessEvent != null){
            // is he in a room?
            if (!accessEvent.isOut()) {
                // if he wants to enter the room that he is in (he can't)
                if(accessEvent.getRoom().getName().equals(roomName)){
                    System.out.println(String.format("User '%s' is already in the room '%s'", username, roomName));
                    unitOfWork.end();
                    return;
                }
                // if he wants to enter anither the room (he must leave one before)
                unitOfWork.end();
                leaveRoom(username, accessEvent.getRoom().getName());
                unitOfWork.begin();
            }
        }

        AccessEvent ae = new AccessEvent();
        Date date = new Date();

        ae.setRoom(room);
        ae.setIsOut(false);
        ae.setPersonName(username);
        ae.setDate(date);

        System.out.println(String.format("User %s has entered the room %s", username, roomName));

        accessEventDao.saveOrUpdate(ae);
        unitOfWork.end();
    }

    /**
     * Leave a room.
     *
     * @param username name of the user
     * @param roomName name of the room to leave
     */
    public void leaveRoom(final String username, final String roomName) {
        unitOfWork.begin();

        // check if the room exist
        Room room = roomDao.findByName(roomName);
        if(room == null){
            System.out.println(String.format("Room '%s' does not exists", roomName));
            unitOfWork.end();
            return;
        }

        // check if the user has entered at least one room
        AccessEvent accessEvent = accessEventDao.findLastBy(username);
        if(accessEvent == null) {
            System.out.println(String.format("User '%s' has not entered any room", username));
            unitOfWork.end();
            return;
        }

        // check if the user is out
        if(accessEvent.isOut()){
            System.out.println(String.format("User '%s' is not in the room '%s'", username, roomName));
            unitOfWork.end();
            return;
        }

        // check if the user is not in another room
        if(!accessEvent.getRoom().getName().equals(roomName)){
            System.out.println(String.format("User '%s' is in the room '%s'", username, accessEvent.getRoom().getName()));
            unitOfWork.end();
            return;
        }

        // making the user leave the room
        AccessEvent ae = new AccessEvent();
        Date date = new Date();

        ae.setRoom(room);
        ae.setIsOut(true);
        ae.setPersonName(username);
        ae.setDate(date);

        System.out.println(String.format("User %s has left the room %s", username, roomName));

        accessEventDao.saveOrUpdate(ae);
        unitOfWork.end();
    }

    /**
     * Show the activity of a room
     *
     * @param room the room
     */
    public void showRoomActivity(final String room) {
        unitOfWork.begin();

        Room r = roomDao.findByName(room);

        if(r == null){
            System.out.println(String.format("Room '%s' does not exists", room));
            unitOfWork.end();
            return;
        }

        // getting all the events in a room
        List<AccessEvent> accessEvents = accessEventDao.findAllByRoom(r);

        if (accessEvents.isEmpty()) {
            System.out.println(String.format("No access event on the room '%s'", r.getName()));
        } else {
            System.out.println(String.format("Access events on room '%s' :", r.getName()));
            System.out.println("--------");
            for (AccessEvent accessEvent : accessEvents) {
                String action = accessEvent.isOut() ? "left" : "entered";
                System.out.println(String.format("   %s has %s the room [%s] at %s", accessEvent.getPersonName(), action, r.getName(), accessEvent.getDate()));
            }
        }

        unitOfWork.end();
    }

    /**
     * Show the activity of a person
     *
     * @param person the person
     */
    public void showPersonActivity(final String person) {
        unitOfWork.begin();

        // getting of the person's activities
        List<AccessEvent> accessEvents = accessEventDao.findAllByPerson(person);

        if (accessEvents.isEmpty()) {
            System.out.println(String.format("No access event for this person '%s'", person));
        } else {
            System.out.println(String.format("Access events for the person '%s' :", person));
            System.out.println("--------");
            for (AccessEvent accessEvent : accessEvents) {
                String action = accessEvent.isOut() ? "left" : "entered";
                System.out.println(String.format("   %s has %s the room [%s] at %s", person, action, accessEvent.getRoom().getName(), accessEvent.getDate()));
            }
        }

        unitOfWork.end();
    }
}
