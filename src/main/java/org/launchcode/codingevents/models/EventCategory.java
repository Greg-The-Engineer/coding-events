package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EventCategory extends AbstractEntity {

    @OneToMany(mappedBy = "eventCategory")
    private List<Event> events = new ArrayList<>();

    @NotBlank(message="Name is required")
    @Size(min = 3, max = 50, message="Name must be between 3 and 50 characters")
    private String name;

    public EventCategory(String name) {
        this.name = name;
    }

    public EventCategory () {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }
}
