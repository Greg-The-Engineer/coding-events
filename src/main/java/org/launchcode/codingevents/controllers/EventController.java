package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("events")
public class EventController {

    // handles GET requests at /events
    // the root controller method for this class
    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("events", EventData.getAll());
        model.addAttribute("title", "All Events");
        return "events/index";
    }

    // responds to GET requests at /events/create
    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create New Event");
        return "events/create";
    }

    // responds to POST requests at /events/create
    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute Event event) {

        // "store" the new event
        EventData.add(event);

        // send them back to the main event listing
        // equivalent of sending 302 HTTP response code with Location=/events
        return "redirect:";
    }

}
