package com.ContactBookApp.Contact.Book.App.controller;

import com.ContactBookApp.Contact.Book.App.model.Contact;
import com.ContactBookApp.Contact.Book.App.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    // Home Page
    @GetMapping("/")
    public String home() {
        return "index"; // Loads index.html
    }

    // Display all contacts (sorted) in table
    @GetMapping("/view")
    public String viewContacts(Model model) {
        List<Contact> sortedContacts = contactService.getAllContacts();
        model.addAttribute("contacts", sortedContacts);
        return "view-contacts";
    }

    // Add contact form
    @GetMapping("/add-form")
    public String addContactForm() {
        return "add-contact";
    }

    // Add contact (with success message)
    @PostMapping("/add")
    public String addContactWeb(@RequestParam String name,
                                @RequestParam String phone,
                                @RequestParam String email,
                                Model model) {
        Contact contact = new Contact(name, phone, email);
        contactService.addContact(contact);
        model.addAttribute("successMessage", "Contact added successfully!");
        return "add-contact";
    }

    // Delete contact
    @GetMapping("/delete/{name}")
    public String deleteContactWeb(@PathVariable String name) {
        contactService.deleteContact(name);
        return "redirect:/contacts/view";
    }

    /* ------------------------ REST API ENDPOINTS ------------------------ */
    @RestController
    @RequestMapping("/api/contacts")
    static class ContactRestController {

        @Autowired
        private ContactService contactService;

        // Add contact (JSON)
        @PostMapping("/add")
        public String addContact(@RequestBody Contact contact) {
            return contactService.addContact(contact);
        }

        // Delete contact (JSON)
        @DeleteMapping("/delete/{name}")
        public String deleteContact(@PathVariable String name) {
            return contactService.deleteContact(name);
        }

        // Search contact by name
        @GetMapping("/search/{name}")
        public Contact searchContact(@PathVariable String name) {
            return contactService.searchContact(name);
        }

        // Get all contacts (sorted)
        @GetMapping("/all")
        public List<Contact> getAllContacts() {
            return contactService.getAllContacts(); // Already sorted
        }
    }
}
