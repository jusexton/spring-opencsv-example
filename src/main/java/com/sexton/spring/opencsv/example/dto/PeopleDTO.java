package com.sexton.spring.opencsv.example.dto;

import com.sexton.spring.opencsv.example.model.Person;

import java.util.List;

public class PeopleDTO {
    private List<Person> people;

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }
}
