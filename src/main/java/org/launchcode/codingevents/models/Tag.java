package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag extends AbstractEntity {

    @ManyToMany(mappedBy = "tags")
    private List<Event> events = new ArrayList<>();

    @NotBlank
    @Size(min = 2, max = 25)
    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public Tag () {}

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
