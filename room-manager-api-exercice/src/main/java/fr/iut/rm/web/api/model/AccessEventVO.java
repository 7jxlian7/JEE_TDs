package fr.iut.rm.web.api.model;

import fr.iut.rm.persistence.domain.EventType;

import javax.persistence.*;

public class AccessEventVO {
    /**
     * AccessEvent's identifier.
     */
    private long id;

    /**
     * Event target user name.
     */
    @Column
    private String userName;

    /**
     * Event type in / out
     */
    @Column
    @Enumerated(EnumType.STRING)
    private EventType type;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
