package com.tassianoalencar.contacts.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ContactsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnContacts() throws Exception {
        mockMvc.perform(get("/api/v1/contacts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("phoneNumber")));
    }

    @Test
    public void onlyContactValidShouldBeCreated() throws Exception {
        String mockJsonContactInvalid = "{\"name\": \"Person Name\", \"phoneNumber\": \"\"}";

        mockMvc.perform(
                post("/api/v1/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mockJsonContactInvalid))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }
}