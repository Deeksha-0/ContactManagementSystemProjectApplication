package com.example.ContactManagementSystemProject.Controller;

import com.example.ContactManagementSystemProject.Model.Contact;
import com.example.ContactManagementSystemProject.Service.ContactService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @ApiOperation(value = "create a conatct")
    @PostMapping("/createConatct")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        Contact createdContact = contactService.createContact(contact);
        return ResponseEntity.created(URI.create("/api/contacts/" + createdContact.getId()))
                .body(createdContact);
    }

    @ApiOperation(value = "Get contact By id")
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        Contact contact = contactService.getContactById(id);
        return ResponseEntity.ok(contact);
    }

    @ApiOperation(value = "Update Conatct by id")
    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact contact) throws ChangeSetPersister.NotFoundException {
        Contact updatedContact = contactService.updateContact(id, contact);
        return ResponseEntity.ok(updatedContact);
    }
    @ApiOperation(value = "delete conatct by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
    @ApiOperation(value = "Search contact by First Name, Last Name and emailId")
    @GetMapping("/search")
    public ResponseEntity<List<Contact>> searchContacts(@RequestBody Contact contact) {
        String firstName=contact.getFirstName();
        String lastName=contact.getLastName();
        String email=contact.getEmail();
        List<Contact> contacts = contactService.searchContacts(firstName, lastName, email);
        return ResponseEntity.ok(contacts);
    }


}

