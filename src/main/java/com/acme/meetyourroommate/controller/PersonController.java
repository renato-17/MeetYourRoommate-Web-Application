package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.Lessor;
import com.acme.meetyourroommate.domain.model.Person;
import com.acme.meetyourroommate.domain.service.PersonService;
import com.acme.meetyourroommate.resource.ConfirmDataRequest;
import com.acme.meetyourroommate.resource.ConfirmDataResponse;
import com.acme.meetyourroommate.resource.LessorResource;
import com.acme.meetyourroommate.resource.SaveLessorResource;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class PersonController {
    @Autowired
    private PersonService personService;

    @Operation(summary = "Confirm data", description = "Confirm data", tags = {"person"})
    @PostMapping("/sign-in")
    public ConfirmDataResponse createLessor(@Valid @RequestBody ConfirmDataRequest request){
        Person person = personService.confirmData(request.getPassword(),request.getMail());
        ConfirmDataResponse confirmDataResponse = new ConfirmDataResponse();
        confirmDataResponse.setId(person.getId());
        confirmDataResponse.setMail(person.getMail());
        confirmDataResponse.setType(person.getType());

        return confirmDataResponse;
    }
}
