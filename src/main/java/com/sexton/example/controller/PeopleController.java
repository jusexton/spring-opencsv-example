package com.sexton.example.controller;

import com.sexton.example.dto.PeopleDTO;
import com.sexton.example.exception.CsvSerializationException;
import com.sexton.example.model.Person;
import com.sexton.example.service.CsvBeanWriterService;
import com.sexton.example.utility.ColumnPositionAndNameMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@RestController
public class PeopleController {
    private final CsvBeanWriterService csvBeanWriterService;

    @Autowired
    public PeopleController(final CsvBeanWriterService csvBeanWriterService) {
        this.csvBeanWriterService = csvBeanWriterService;
    }

    @RequestMapping("/people")
    public ResponseEntity<PeopleDTO> getPeopleJson() {
        final PeopleDTO peopleDTO = new PeopleDTO();
        peopleDTO.setPeople(getPeople());
        return new ResponseEntity<>(peopleDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/people", produces = "text/csv")
    public void getPeopleCsv(final HttpServletResponse httpServletResponse) throws CsvSerializationException {
        final List<Person> people = getPeople();
        final ColumnPositionAndNameMappingStrategy<Person> mappingStrategy = new ColumnPositionAndNameMappingStrategy<>();
        mappingStrategy.setType(Person.class);
        this.csvBeanWriterService.writeBeansToResponse(httpServletResponse, mappingStrategy, people);
    }

    // Acting as a repository collecting data.
    private List<Person> getPeople() {
        return Arrays.asList(
                new Person("Bob", "Smith"),
                new Person("Jim", "Pat")
        );
    }
}
