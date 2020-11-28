package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.Person;
import com.acme.meetyourroommate.domain.repository.PersonRepository;
import com.acme.meetyourroommate.domain.service.PersonService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person confirmData(String password, String mail) {
        Person person = personRepository.findByMail(mail)
                .orElseThrow(()-> new ResourceNotFoundException("Person","mail",mail));

        if(!person.getPassword().equals(password)) {
            throw new ResourceNotFoundException("Password incorrect");
        }

        return person;
    }
}
