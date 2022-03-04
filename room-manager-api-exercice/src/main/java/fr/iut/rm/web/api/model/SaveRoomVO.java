package fr.iut.rm.web.api.model;

/**
 * Defines a room to create for the API layer (Room Visual Object)
 */
public class SaveRoomVO {
    /**
     * Room's name.
     */
    public String name;
    /**
     * Room's description.
     */
    public String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
