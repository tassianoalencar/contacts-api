package com.tassianoalencar.contacts.api.response;

import com.tassianoalencar.contacts.api.entity.Contact;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ContactResponse {
    private final Long id;
    private final String name;
    private final String phoneNumber;

    public ContactResponse(Contact contact) {
        this.id = contact.getId();
        this.name = contact.getName();
        this.phoneNumber = contact.getPhoneNumber();
    }

    public static List<ContactResponse> convert(Page<Contact> contacts) {
        return contacts.stream().map(ContactResponse::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
