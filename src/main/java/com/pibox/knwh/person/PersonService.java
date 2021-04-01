package com.pibox.knwh.person;

import com.pibox.knwh.person.exception.BadRequestException;
import com.pibox.knwh.person.exception.PersonNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;

    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    public void addPerson(Person person) {
        Boolean existsEmail = personRepository.selectExistsEmail(person.getEmail());
        if (existsEmail) {
            throw new BadRequestException(
                    "Email " + person.getEmail() + " taken"
            );
        }
        personRepository.save(person);
    }

    public void deletePerson(Long personId) {
        if (!personRepository.existsById(personId)) {
            throw new PersonNotFoundException(
                    "Person with id " + personId + " does not exists"
            );
        }
        personRepository.deleteById(personId);
    }
}
