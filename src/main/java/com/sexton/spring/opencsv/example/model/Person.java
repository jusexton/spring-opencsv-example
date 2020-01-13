package com.sexton.spring.opencsv.example.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class Person {
    @CsvBindByName(column = "First Name")
    @CsvBindByPosition(position = 0)
    private final String firstName;

    @CsvBindByName(column = "Last Name")
    @CsvBindByPosition(position = 1)
    private final String lastName;

    @CsvBindByName(column = "Full Name")
    @CsvBindByPosition(position = 2)
    private final String fullName;

    public Person(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = String.format("%s %s", firstName, lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return fullName;
    }
}
