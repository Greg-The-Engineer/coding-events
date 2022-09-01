package org.launchcode.codingevents.models;

public class Event {

    private static int nextId = 1;

    private final int id;
    private String name;
    private String description;

    public Event(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }

    private Event() {
        this.id = nextId;
        nextId++;
    }

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

    public int getId() {
        return id;
    }
}
