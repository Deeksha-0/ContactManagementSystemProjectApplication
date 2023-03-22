package com.example.ContactManagementSystemProject.Service;

import com.example.ContactManagementSystemProject.Model.Contact;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {
    private final List<Contact> contacts = new ArrayList<>(Arrays.asList(
            new Contact(1L,"Vikrant","Tomar","xyz@gmail.com","7986208759"),
            new Contact(2L,"Anmol","Tomar","xyz123@gmail.com","798624321"),
            new Contact(3L,"Abhishek","Singh","xyz1234@gmail.com","5684308759")
    ));
    private Long nextId = 1L;

    public Contact createContact(Contact contact) {
        contact.setId(nextId++);
        contacts.add(contact);
        return contact;
    }

    public Contact getContactById(Long id) throws ChangeSetPersister.NotFoundException {
        return contacts.stream()
                .filter(contact -> contact.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    public Contact updateContact(Long id, Contact contact) throws ChangeSetPersister.NotFoundException {
        Contact existingContact = getContactById(id);
        existingContact.setFirstName(contact.getFirstName());
        existingContact.setLastName(contact.getLastName());
        existingContact.setEmail(contact.getEmail());
        existingContact.setPhoneNumber(contact.getPhoneNumber());
        return existingContact;
    }

    public void deleteContact(Long id) {
        contacts.removeIf(contact -> contact.getId().equals(id));
    }

    public List<Contact> searchContacts(String firstName, String lastName, String email) {
        return contacts.stream()
                .filter(contact -> firstName == null || contact.getFirstName().contains(firstName))
                .filter(contact -> lastName == null || contact.getLastName().contains(lastName))
                .filter(contact -> email == null || contact.getEmail().contains(email))
                .collect(Collectors.toList());
    }
}

