package com.tassianoalencar.contacts.api.repository;

import com.tassianoalencar.contacts.api.entity.Contact;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {
}
