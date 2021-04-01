package com.pibox.knwh.person;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/people")
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @PostMapping
    public void addPerson(@Valid @RequestBody Person person) {
        personService.addPerson(person);
    }

    @DeleteMapping(path = "{personId}")
    public void deletePerson(@PathVariable ("personId") Long personId) {
        personService.deletePerson(personId);
    }
}
