package fr.iut.rm.persistence.domain;

import javax.persistence.*;
import jakarta.validation.constraints.*;

/**
 * A classic room
 */
@Entity
@Table(name = "room")
public class Room {
    /**
     * sequence generated id
     */
    @Id
    @GeneratedValue
    private long id;

    /**
     * Room's name
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Room's description
     */
    @Column()
    @Size(max = 10)
    private String description;

    /**
     * Default constructor (do nothing)
     */
    public Room() {
        // do nothing
    }

    /**
     * anemic getter
     *
     * @return the room's id
     */
    public long getId() {
        return id;
    }

    /**
     * anemic setter
     *
     * @param id the new id
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * anemic getter
     *
     * @return the calling number
     */
    public String getName() {
        return name;
    }

    /**
     * anemic setter
     *
     * @param name the new calling number to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * anemic getter
     *
     * @return the calling number
     */
    public String getDescription() {
        return description;
    }

    /**
     * anemic setter
     *
     * @param description the new calling number to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

}