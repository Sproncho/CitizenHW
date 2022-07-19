package telran.citizens.dao;

import telran.citizens.interfaces.Citizens;
import telran.citizens.model.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CitizensImpl implements Citizens {
    private List<Person> idList = new ArrayList<>();
    private List<Person> lastNameList = new ArrayList<>();
    private List<Person> ageList = new ArrayList<>();

    public CitizensImpl(List<Person> list) {
        //TODO
    }
    public CitizensImpl(){}
    @Override
    public boolean add(Person person) {
        int ind = Collections.binarySearch(idList,person);
        if(ind > 0)
            return false;
        ind = -ind -1;
        idList.add(ind,person);
        ind = Collections.binarySearch(lastNameList,person,new LastNameComparator());
        ind = -ind -1;
        lastNameList.add(ind,person);
        ind = Collections.binarySearch(ageList,person,new AgeComparator());
        ind = -ind  -1;
        ageList.add(ind,person);
        return true;
    }

    @Override
    public boolean remove(int id) {
        idList.removeIf((Person p) -> p.getId() == id);
        lastNameList.removeIf((Person p) -> p.getId() == id);
        return ageList.removeIf((Person p) -> p.getId() == id);
    }

    @Override
    public Person find(int id) {
        int ind = Collections.binarySearch(idList, new Person(id,null,null,0));
        if(id < 0 )
            return null;
        return idList.get(ind);
    }

    @Override
    public Iterable<Person> find(int minAge, int maxAge) {
//        ArrayList<Person> res = Collections.
        return null;
    }

    @Override
    public Iterable<Person> find(String lastName) {
        return null;
    }

    @Override
    public Iterable<Person> getAllPersonSortedById() {
        return null;
    }

    @Override
    public Iterable<Person> getAllPersonSortedByAge() {
        return null;
    }

    @Override
    public Iterable<Person> getAllPersonSortedByLastName() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
    private static class LastNameComparator implements Comparator<Person>{

        @Override
        public int compare(Person o1, Person o2) {
            return o1.getLastName().compareTo(o2.getLastName());
        }
    }

    private static class AgeComparator implements Comparator<Person>{

        @Override
        public int compare(Person o1, Person o2) {
            return o1.getAge() - o2.getAge();
        }
    }
}
