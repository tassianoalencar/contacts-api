package com.tassianoalencar.contacts.api.controller;

import com.tassianoalencar.contacts.api.entity.Contact;
import com.tassianoalencar.contacts.api.repository.ContactRepository;
import com.tassianoalencar.contacts.api.request.ContactRequest;
import com.tassianoalencar.contacts.api.response.ContactResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping
    public Page<ContactResponse> getContactsPaginated(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Contact> pageResult = contactRepository.findAll(pageRequest);
        List<ContactResponse> contacts = ContactResponse.convert(pageResult);

        return new PageImpl<>(contacts, pageRequest, pageResult.getTotalElements());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ContactResponse> createContact(@RequestBody @Valid ContactRequest contactRequest, UriComponentsBuilder uriComponentsBuilder) {
        Contact contact = ContactRequest.convert(contactRequest);
        contactRepository.save(contact);

        URI uri = uriComponentsBuilder.path("/api/v1/contacts/{id}").buildAndExpand(contact.getId()).toUri();
        return ResponseEntity.created(uri).body(new ContactResponse(contact));
    }
}
