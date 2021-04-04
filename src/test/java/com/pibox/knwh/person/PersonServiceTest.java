package com.pibox.knwh.person;

import com.pibox.knwh.person.exception.BadRequestException;
import com.pibox.knwh.person.exception.PersonNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
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
    void addPerson() {
        Person person = new Person("Val", "val@test.com", Gender.MALE);
        underTest.addPerson(person);

        ArgumentCaptor<Person> personArgumentCaptor = ArgumentCaptor.forClass(Person.class);
        verify(personRepository).save(personArgumentCaptor.capture());

        Person capturedPerson = personArgumentCaptor.getValue();

        assertThat(capturedPerson).isEqualTo(person);
    }

    @Test
    void addPersonWithTakenEmail() {
        Person person = new Person("Val", "val@test.com", Gender.MALE);
        underTest.addPerson(person);

        given(personRepository.selectExistsEmail(person.getEmail()))
                .willReturn(true);

        assertThatThrownBy(() -> underTest.addPerson(person))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email " + person.getEmail() + " taken");
    }

    @Test
    void canDeleteStudent() {
        // given
        long id = 10;
        given(personRepository.existsById(id))
                .willReturn(true);
        // when
        underTest.deletePerson(id);

        // then
        verify(personRepository).deleteById(id);
    }

    @Test
    void willThrowWhenDeleteStudentNotFound() {
        // given
        long id = 10;
        given(personRepository.existsById(id))
                .willReturn(false);
        // when
        // then
        assertThatThrownBy(() -> underTest.deletePerson(id))
                .isInstanceOf(PersonNotFoundException.class)
                .hasMessageContaining("Person with id " + id + " does not exists");
    }
}
