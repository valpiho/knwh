package com.pibox.knwh.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pibox.knwh.enumeration.Gender;
import com.pibox.knwh.entity.Person;
import com.pibox.knwh.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-it.properties")
@AutoConfigureMockMvc
public class PersonIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PersonRepository personRepository;

    @Test
    void canRegisterNewPerson() throws Exception {
        Person person = new Person("Val", "lol@test.com", Gender.MALE);

        ResultActions resultActions = mockMvc
                .perform(post("/api/v1/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(person)));

        resultActions.andExpect(status().isOk());
        List<Person> people = personRepository.findAll();
        assertThat(people)
                .usingElementComparatorIgnoringFields("id")
                .contains(person);
    }
}
