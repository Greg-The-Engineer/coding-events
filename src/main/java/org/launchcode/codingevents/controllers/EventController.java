package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        model.addAttribute("event", new Event());
        return "events/create";
    }

    // responds to POST requests at /events/create
    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event event,
                                         Errors errors,
                                         Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create New Event");
            return "events/create";
        }

        // "store" the new event
        EventData.add(event);

        // send them back to the main event listing
        // equivalent of sending 302 HTTP response code with Location=/events
        return "redirect:";
    }

    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId) {
        Event event = EventData.getById(eventId);
        model.addAttribute("event", event);
        model.addAttribute("title", "Edit Event " + event.getName() + " (id=" + event.getId() + ")");
        return "events/edit";
    }

    @PostMapping("edit")
    public String processEditForm(@RequestParam int eventId,
                                  @RequestParam String name,
                                  @RequestParam String description) {
        Event event = EventData.getById(eventId);
        event.setName(name);
        event.setDescription(description);
        return "redirect:";
    }

    @GetMapping("delete")
    public String renderDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Event");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {

        if (eventIds != null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }
        }

        return "redirect:";
    }

}
