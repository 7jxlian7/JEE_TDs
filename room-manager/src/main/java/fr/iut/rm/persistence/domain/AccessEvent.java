package fr.iut.rm.persistence.domain;

import javax.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

/**
 * An access event
 */
@Entity
@Table(name = "access_event")
public class AccessEvent {
    /**
     * sequence generated id
     */
    @Id
    @GeneratedValue
    private long id;

    /**
     * Person's name
     */
    @Column(nullable = false)
    private String personName;

    /**
     * True only if the person is in the room, else, false.
     */
    @Column(nullable = false)
    private boolean isOut;

    /**
     * Date/Time of the event
     */
    @Column(nullable = false)
    private Date date;

    /**
     * The room
     */
    @ManyToOne
    @JoinColumn(name="room", nullable = false)
    private Room room;

    /**
     * Default constructor (do nothing)
     */
    public AccessEvent() {
        // do nothing
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public boolean isOut() {
        return isOut;
    }

    public void setIsOut(boolean status) {
        this.isOut = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
