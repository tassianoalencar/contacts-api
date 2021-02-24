package com.tassianoalencar.contacts.api.request;

import com.tassianoalencar.contacts.api.entity.Contact;

import javax.validation.constraints.NotBlank;

public class ContactRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String phoneNumber;

    public static Contact convert(ContactRequest contactRequest) {
        return new Contact(contactRequest.getName(), contactRequest.getPhoneNumber());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
