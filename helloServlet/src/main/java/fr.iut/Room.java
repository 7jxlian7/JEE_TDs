package fr.iut;

public class Room {

    private String name;
    private int occupation;
    private int capacity;

    public Room(String name, int occupation, int capacity) {
        this.name = name;
        this.occupation = occupation;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOccupation() {
        return occupation;
    }

    public void setOccupation(int occupation) {
        this.occupation = occupation;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
