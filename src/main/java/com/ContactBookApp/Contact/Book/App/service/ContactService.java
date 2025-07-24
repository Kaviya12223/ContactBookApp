package com.ContactBookApp.Contact.Book.App.service;

import com.ContactBookApp.Contact.Book.App.model.Contact;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ContactService {

    private List<Contact> contacts = new ArrayList<>();

    // Add a new contact
    public String addContact(Contact contact) {
        contacts.add(contact);
        sortContacts(); // Sort after adding
        return "Contact added successfully!";
    }

    // Delete a contact by name
    public String deleteContact(String name) {
        boolean removed = contacts.removeIf(c -> c.getName().equalsIgnoreCase(name));
        return removed ? "Contact deleted successfully!" : "Contact not found!";
    }

    // Search a contact by name
    public Contact searchContact(String name) {
        return contacts.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    // Get all contacts (sorted)
    public List<Contact> getAllContacts() {
        sortContacts(); // Ensure sorting before returning
        return new ArrayList<>(contacts);
    }

    // Helper method to sort contacts by name
    private void sortContacts() {
        Collections.sort(contacts, Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER));
    }
}
