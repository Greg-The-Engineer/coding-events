package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event extends AbstractEntity {

    @ManyToOne
    @NotNull
    private EventCategory eventCategory;

    @ManyToMany
    private List<Tag> tags = new ArrayList<>();

    @NotBlank(message="Name is required")
    @Size(min = 3, max = 50, message="Name must be between 3 and 50 characters")
    private String name;

    @Size(max = 500, message="Description too long (maximum length is 500)")
    private String description;

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    private String contactEmail;

    public Event(String name, String description, String contactEmail, EventCategory eventCategory) {
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
        this.eventCategory = eventCategory;
    }

    // Hibernate needs no-arg constructors
    public Event() {}

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tag> getTags() {
        return tags;
    }
}

// Chapter 17