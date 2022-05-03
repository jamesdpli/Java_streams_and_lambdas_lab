package org.examples.streamapi._2_filter;

import org.examples.streamapi.model.Person;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.examples.streamapi.TestUtil.PEOPLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterTest {
    @Test
    void filterPeopleYoungerThan20_comments(){
        List<Person> filteredPeople = PEOPLE
                // We need to change a list into a stream to have access to Stream
                .stream()
                .filter(
                        // Let's pass lambda (again, a more extended version to be more similar to a method
                        (person) -> {
                            return person.getAge() < 20;
                        }
                )
                // Now it's time to go back to a List
                .toList();
//      Changed assertEquals to 5 from 4
        assertEquals(5, filteredPeople.size());
    }

    @Test
    void filterPeopleYoungerThan20(){
        List<Person> filteredPeople = PEOPLE
                .stream()
                .filter(person -> person.getAge() < 20)
                .toList();
//      Changed assertEquals to 5 from 4
        assertEquals(5, filteredPeople.size());
    }
/*
    Time for some practice:
     - filter by: age < 20 || age > 30
     - filter by: firstName starts with "Z" and age < 20
     - filter by: Gender: non-binary and  age > 30
     - filter by lastName starts with "B" and age > 50
 */
    @Test
    void filterPeopleOlderThan30OrYoungerThan20(){
        List<Person> filteredPeople = PEOPLE
                .stream()
                .filter(person -> person.getAge() < 20 || person.getAge() > 30)
                .toList();
        assertEquals(11,filteredPeople.size());
    }
    @Test
    void filterByFirstNameStartsWithZandOlderThan20(){
        List<Person> filteredPeople = PEOPLE
                .stream()
                .filter(person -> person.getName().startsWith("Z") && person.getAge() < 20)
                .toList();
        assertEquals(1,filteredPeople.size());
    }
    @Test
    void filterByNonBinaryAndOlderThan30(){
        List<Person> filteredPeople = PEOPLE
                .stream()
                .filter(person -> person.getGender() == Person.Gender.NON_BINARY && person.getAge() > 30)
                .toList();
        assertEquals(2,filteredPeople.size());
    }
    @Test
    void filterByLastNameStartsWithBAndOlderThan50(){
        List<Person> filteredPeople = PEOPLE
                .stream()
                .filter(person -> person.getName().startsWith("B") && person.getAge() > 50)
                .toList();
        assertEquals(0,filteredPeople.size());
    }
}
