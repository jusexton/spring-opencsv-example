package com.sexton.example.dto;

import com.sexton.example.model.Person;

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
