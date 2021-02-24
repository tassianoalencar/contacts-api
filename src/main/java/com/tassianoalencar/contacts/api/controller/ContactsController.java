package com.tassianoalencar.contacts.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/contacts")
public class ContactsController {

    @GetMapping
    public String getAllContacts() {
        return "Hello Contacts";
    }

}
