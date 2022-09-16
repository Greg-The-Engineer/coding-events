package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Tag extends AbstractEntity {

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
}
