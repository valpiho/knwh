package com.pibox.knwh.service.serviceImpl;

import com.pibox.knwh.entity.User;
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

    public List<User> getAllPeople() {
        return personRepository.findAll();
    }

    public void addPerson(User user) {
        Boolean existsEmail = personRepository.selectExistsEmail(user.getEmail());
        if (existsEmail) {
            throw new BadRequestException(
                    "Email " + user.getEmail() + " taken"
            );
        }
        personRepository.save(user);
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
