package com.pibox.knwh.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock private PersonRepository personRepository;
    private PersonService underTest;

    @BeforeEach
    void setUp() {
        underTest = new PersonService(personRepository);
    }


    @Test
    void getAllPeople() {
        underTest.getAllPeople();
        verify(personRepository).findAll();
    }

    @Test
    @Disabled
    void addPerson() {
    }

    @Test
    @Disabled
    void deletePerson() {
    }
}
