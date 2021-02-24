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

    private final String BASE_URL = "/api/v1/contacts";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnContacts() throws Exception {
        mockMvc.perform(get(BASE_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("phoneNumber")));
    }

    @Test
    public void onlyContactValidShouldBeCreated() throws Exception {
        String mockJsonContactInvalid = "{\"name\": \"Person Name\", \"phoneNumber\": \"\"}";

        mockMvc.perform(
                post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mockJsonContactInvalid))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnTheLastContactWhenCreated() throws Exception {
        String mockJsonContact = "{\"name\": \"Person Name\", \"phoneNumber\": \"99099887766\"}";

        mockMvc.perform(
                post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mockJsonContact))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("99099887766")));
    }

    @Test
    public void shouldReturnTheContactById() throws Exception {
        String id = "1";

        mockMvc.perform(get(BASE_URL + "/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("phoneNumber")));
    }
}