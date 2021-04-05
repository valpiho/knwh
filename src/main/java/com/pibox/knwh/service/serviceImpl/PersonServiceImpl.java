package com.pibox.knwh.service.serviceImpl;

import com.pibox.knwh.entity.Person;
import com.pibox.knwh.exception.BadRequestException;
import com.pibox.knwh.exception.PersonNotFoundException;
import com.pibox.knwh.repository.PersonRepository;
import com.pibox.knwh.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

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
