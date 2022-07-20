package telran.citizens.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.citizens.interfaces.Citizens;
import telran.citizens.model.Person;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CitizensImplTest {
    Citizens citizens;
    List<Person> plist = Arrays.asList(
            new Person(1, "Peter", "Jackson", 23),
            new Person(1, "WAWA", "Os", 25),
            new Person(2, "John", "Smith", 20),
            new Person(3, "Mary", "Jackson", 20),
            new Person(4, "Tigran", "Petrosian", 25)
    );
    @BeforeEach
    void setUp() throws Exception {
        citizens = new CitizensImpl();
        for(Person person: plist){
            citizens.add(person);
        }
    }

    @Test
    void testCitizensImplListOfPerson() {
        citizens = new CitizensImpl(plist);
        List<Person> actual = (List<Person>) citizens.getAllPersonSortedById();
        List<Person> expected = Arrays.asList(
                new Person(1, "Peter", "Jackson", 23),
                new Person(2, "John", "Smith", 20),
                new Person(3, "Mary", "Jackson", 20),
                new Person(4, "Tigran", "Petrosian", 25)
        );
        assertArrayEquals(expected.toArray(),actual.toArray());
    }

    @Test
    void testAdd() {
        List<Person> actual = (List<Person>) citizens.getAllPersonSortedById();
        List<Person> expected = Arrays.asList(
                new Person(1, "Peter", "Jackson", 23),
                new Person(2, "John", "Smith", 20),
                new Person(3, "Mary", "Jackson", 20),
                new Person(4, "Tigran", "Petrosian", 25)
        );
        assertArrayEquals(expected.toArray(),actual.toArray());
    }

    @Test
    void testRemove() {
        citizens.remove(1);
        List<Person> actual = (List<Person>) citizens.getAllPersonSortedById();
        List<Person> expected = Arrays.asList(
                new Person(2, "John", "Smith", 20),
                new Person(3, "Mary", "Jackson", 20),
                new Person(4, "Tigran", "Petrosian", 25)
        );
        assertArrayEquals(expected.toArray(),actual.toArray());
    }

    @Test
    void testFindId() {
       Person actual = citizens.find(1);
       assertEquals(plist.get(0),actual);
    }

    @Test
    void testFindAge() {
        List<Person> expected = Arrays.asList(
                new Person(1, "Peter", "Jackson", 23),
                new Person(4, "Tigran", "Petrosian", 25)
        );
        List<Person> actual = (List<Person>) citizens.find(22,26);
        assertArrayEquals(expected.toArray(),actual.toArray());

    }

    @Test
    void testFindLastName() {
        List<Person> expected = Arrays.asList(
                new Person(3, "Mary", "Jackson", 20),
                new Person(1, "Peter", "Jackson", 23)

        );
        List<Person> actual = (List<Person>) citizens.find("Jackson");
        assertArrayEquals(expected.toArray(),actual.toArray());
    }

    @Test
    void testGetAllPersonSortedById() {
        List<Person> actual = (List<Person>) citizens.getAllPersonSortedById();
        List<Person> expected = Arrays.asList(
                new Person(1, "Peter", "Jackson", 23),
                new Person(2, "John", "Smith", 20),
                new Person(3, "Mary", "Jackson", 20),
                new Person(4, "Tigran", "Petrosian", 25)
        );
        assertArrayEquals(expected.toArray(),actual.toArray());
    }

    @Test
    void testGetAllPersonSortedByLastName() {
        List<Person> actual = (List<Person>) citizens.getAllPersonSortedByLastName();
        List<Person> expected = Arrays.asList(
                new Person(3, "Mary", "Jackson", 20),
                new Person(1, "Peter", "Jackson", 23),
                new Person(4, "Tigran", "Petrosian", 25),
                new Person(2, "John", "Smith", 20)
        );
        assertArrayEquals(expected.toArray(),actual.toArray());
    }

    @Test
    void testGetAllPersonSortedByAge() {
        List<Person> actual = (List<Person>) citizens.getAllPersonSortedByAge();
        List<Person> expected = Arrays.asList(
                new Person(3, "Mary", "Jackson", 20),
                new Person(2, "John", "Smith", 20),
                new Person(1, "Peter", "Jackson", 23),
                new Person(4, "Tigran", "Petrosian", 25)
        );
        assertArrayEquals(expected.toArray(),actual.toArray());
    }

    @Test
    void testSize() {
        assertEquals(4,citizens.size());
    }
}