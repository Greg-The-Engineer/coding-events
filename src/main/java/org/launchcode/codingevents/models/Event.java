package org.launchcode.codingevents.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Event {

    private static int nextId = 1;

    private final int id;

    @NotBlank(message="Name is required")
    @Size(min = 3, max = 50, message="Name must be between 3 and 50 characters")
    private String name;

    @Size(max = 500, message="Description too long (maximum length is 500)")
    private String description;

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    private String contactEmail;

    public Event(String name, String description, String contactEmail) {
        this();
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
    }

    public Event() {
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

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
