package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.models.*;
import org.launchcode.codingevents.models.data.EventCategoryRepository;
import org.launchcode.codingevents.models.data.EventRepository;
import org.launchcode.codingevents.models.data.TagRepository;
import org.launchcode.codingevents.models.dto.EventTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @Autowired
    private TagRepository tagRepository;

    // handles GET requests at /events
    // the root controller method for this class
    @GetMapping
    public String displayAllEvents(@RequestParam(required = false) Integer categoryId, Model model) {

        if (categoryId == null) {
            model.addAttribute("events", eventRepository.findAll());
            model.addAttribute("title", "All Events");
        } else {
            Optional<EventCategory> optionalEventCategory = eventCategoryRepository.findById(categoryId);
            if (optionalEventCategory.isPresent()) {
                EventCategory eventCategory = optionalEventCategory.get();
                model.addAttribute("events", eventCategory.getEvents());
                model.addAttribute("title", "Events in Category: " + eventCategory.getName());
            } else {
                model.addAttribute("title", "Invalid Category ID");
            }
        }

        return "events/index";
    }

    // responds to GET requests at /events/create
    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create New Event");
        model.addAttribute("event", new Event());
        model.addAttribute("categories", eventCategoryRepository.findAll());
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
        eventRepository.save(event);

        // send them back to the main event listing
        // equivalent of sending 302 HTTP response code with Location=/events
        return "redirect:";
    }

    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId) {

        Optional<Event> optEvent = eventRepository.findById(eventId);
        if (optEvent.isPresent()) {
            Event event = optEvent.get();
            model.addAttribute("event", event);
            model.addAttribute("title", "Edit Event " + event.getName() + " (id=" + event.getId() + ")");
        } else {
            // do something to handle the error situation
        }

        return "events/edit";
    }

    @PostMapping("edit")
    public String processEditForm(@RequestParam int eventId,
                                  @RequestParam String name,
                                  @RequestParam String description) {

        Optional<Event> optEvent = eventRepository.findById(eventId);
        if (optEvent.isPresent()) {
            Event event = optEvent.get();
            event.setName(name);
            event.setDescription(description);
            eventRepository.save(event);
        } else {
            // do something to handle the error situation
        }

        return "redirect:";
    }

    @GetMapping("delete")
    public String renderDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Event");
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {

        if (eventIds != null) {
            for (int id : eventIds) {
                eventRepository.deleteById(id);
            }
        }

        return "redirect:";
    }

    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam int eventId, Model model) {

        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isEmpty()) {
            return "redirect:";
        }

        Event event = optionalEvent.get();

        // put necessary data in the model
        EventTagDTO dto = new EventTagDTO();
        dto.setEvent(event);
        model.addAttribute("dto", dto);
        model.addAttribute("title", "Add Tag to " + event.getName() + " (" + event.getId() + ")");
        model.addAttribute("tags", tagRepository.findAll());

        return "events/add-tag";
    }

    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid EventTagDTO eventTagDTO, Errors errors) {

        if (errors.hasErrors()) {
            return "redirect:";
        }

        Event event = eventTagDTO.getEvent();
        Tag tag = eventTagDTO.getTag();

        if (!event.getTags().contains(tag)) {
            event.addTag(tag);
            eventRepository.save(event);
        }

        return "redirect:";
    }

}
