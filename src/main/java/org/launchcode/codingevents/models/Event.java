package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Event extends AbstractEntity {

    private EventType type;

    @Size(max = 500, message="Description too long (maximum length is 500)")
    private String description;

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    private String contactEmail;

    public Event(String name, String description, String contactEmail, EventType type) {
        super(name);
        this.description = description;
        this.contactEmail = contactEmail;
        this.type = type;
    }

    // Hibernate needs no-arg constructors
    public Event() {}

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

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }
}
