package telran.citizens.interfaces;

import telran.citizens.model.Person;

public interface Citizens {
    public boolean add(Person person);
    public boolean remove(int id);
    public Person find(int id);
    public Iterable<Person> find(int minAge, int maxAge);
    public Iterable<Person> find(String lastName);
    public Iterable<Person> getAllPersonSortedById();
    public Iterable<Person> getAllPersonSortedByAge();
    public Iterable<Person> getAllPersonSortedByLastName();
    public int size();
}
