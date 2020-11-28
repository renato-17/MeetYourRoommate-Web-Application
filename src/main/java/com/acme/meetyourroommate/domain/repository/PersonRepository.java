package com.acme.meetyourroommate.domain.repository;

import com.acme.meetyourroommate.domain.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository< Person ,Long> {
    Optional<Person> findByMail(String mail);
}
