package org.examples.streamapi._4;

import org.examples.streamapi.model.Person;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.examples.streamapi.TestUtil.PEOPLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PredicateTest {
/*
    We used Predicates with the Stream's filter method.


    In Java, each method must belong to a Type - there are no methods on their own.
    Every lambda we pass is an implementation of a functional interface - (a functional interface is an interface with one method only).

    We can assign a lambda to a reference which sometimes may be helpful write more reusable code.

 */

    @Test
    void filterUsingAPredicate() {
        // Use lambda expression and assign it to a variable
        Predicate<Person> ageMoreThan30 = (person) -> {
            return person.getAge() > 30;
        };

        Predicate<Person> ageLessThan20 = (person) -> {
            return person.getAge() < 20;
        };

        List<Person> peopleOlderThan30AndYoungerThen20 = PEOPLE
                .stream()
                // Time to use the predicates ?
                .filter(ageLessThan20.or(ageMoreThan30))
                .toList();

        assertEquals(11, peopleOlderThan30AndYoungerThen20.size());
    }

        /*
            Time for some practice:
             - create the same test but this time use IntelliJ to help you get rid of unnecessary code in the lambdas
             (e.g. return keyword)
             - use FilterTest exercises again, but this time with the help of predefined predicates

             - extras: define some predicates in Person class (public static) and use them in the test
         */
//  Rewritten method by JDP
    @Test
    void filterUsingAPredicate2(){
        Predicate<Person> ageMoreThan30 = (person) -> person.getAge() > 30;

        Predicate<Person> ageLessThan20 = (person) -> person.getAge() < 20;

        List<Person> peopleOlderThan30AndYoungerThan20 = PEOPLE
                .stream()
                .filter(ageLessThan20.or(ageMoreThan30))
                .toList();
        assertEquals(11, peopleOlderThan30AndYoungerThan20.size());
    }

    @Test
    void predicateFilterNameStartsWithZAndYoungerThan20(){
        Predicate<Person> startsWithZ = (person) -> person.getName().startsWith("Z");

        Predicate<Person> ageLessThan20 = (person) -> person.getAge() < 20;

        List<Person> peopleFirstNameStartWithZAndYoungerThan20 = PEOPLE
                .stream()
                .filter(startsWithZ.and(ageLessThan20))
                .toList();
        assertEquals(1, peopleFirstNameStartWithZAndYoungerThan20.size());
    }

    @Test
    void predicateFilterPeopleNonBinaryAndOlderThan30(){
        Predicate<Person> nonBinary = (person) -> person.getGender().equals(Person.Gender.NON_BINARY);

        Predicate<Person> ageOlderThan30 = (person) -> person.getAge() > 30;

        List<Person> peopleNonBinaryAndOlderThan30 = PEOPLE
                .stream()
                .filter(nonBinary.and(ageOlderThan30))
                .toList();
        assertEquals(2,peopleNonBinaryAndOlderThan30.size());
    }

    @Test
    void predicateFilterLastNameStartsWithBAndOlderThan50(){
        Predicate<Person> lastNameStartsB = (person) -> person.getLastName().startsWith("B");

        Predicate<Person> olderThan50 = (person) -> person.getAge() > 50;

        List<Person> peopleLastNameStartsBAndOlderThan50 = PEOPLE
                .stream()
                .filter(lastNameStartsB.and(olderThan50))
                .toList();
        assertEquals(1,peopleLastNameStartsBAndOlderThan50.size());
    }

}