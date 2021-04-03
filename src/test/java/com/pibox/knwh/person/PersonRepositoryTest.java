package com.pibox.knwh.person;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void selectExistsEmail() {
        String email = "val@test.com";
        Person person = new Person("Val", email, Gender.MALE);

        underTest.save(person);
        boolean expected = underTest.selectExistsEmail(email);

        assertThat(expected).isTrue();
    }

    @Test
    void selectNotExistsEmail() {
        String email = "val@test.com";

        boolean expected = underTest.selectExistsEmail(email);

        assertThat(expected).isFalse();
    }
}
