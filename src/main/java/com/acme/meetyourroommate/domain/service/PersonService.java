package com.acme.meetyourroommate.domain.service;

import com.acme.meetyourroommate.domain.model.Person;

public interface PersonService {
    Person confirmData(String password, String mail);
}
