package telran.citizens.dao;

import telran.citizens.interfaces.Citizens;
import telran.citizens.model.Person;

import java.util.*;

public class CitizensImpl implements Citizens {
    private List<Person> idList = new ArrayList<>();
    private List<Person> lastNameList = new ArrayList<>();
    private List<Person> ageList = new ArrayList<>();

    public CitizensImpl(List<Person> list) {
        for(Person person: list){
            add(person);
        }
    }
    public CitizensImpl(){}
    @Override
    public boolean add(Person person) {
        if(idList.contains(person))
            return false;
        int ind = Collections.binarySearch(idList,person);
        ind = -ind -1;
        idList.add(ind,person);
        ind = Collections.binarySearch(lastNameList,person,new LastNameComparator());
        ind = ind < 0? -ind -1 :ind;
        lastNameList.add(ind,person);
        ind = Collections.binarySearch(ageList,person,new AgeComparator());
        ind = ind < 0? -ind -1 :ind;
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
        AgeComparator ageComp = new AgeComparator();
        int from  = Collections.binarySearch(ageList,new Person(0,null,null,minAge), ageComp);
        from  = from < 0 ? - from - 1 : from;
        while(from > 0 && ageList.get(from).getAge() > minAge)
            from--;
        from++;
        int to  = Collections.binarySearch(ageList,new Person(0,null,null,maxAge),ageComp);
        to =  to < 0 ? - to - 1 : to;
        while (to < size() && ageList.get(to).getAge() < maxAge)
            to++;
        List<Person> res = new ArrayList<>();
        for (int i = from; i < to; i++) {
            res.add(ageList.get(i));
        }
        return res;
    }

    @Override
    public Iterable<Person> find(String lastName) {
        LastNameComparator lastNameComparator = new LastNameComparator();
        int ind = Collections.binarySearch(lastNameList,new Person(0,null,lastName,0),lastNameComparator);
        if(ind < 0) return new LinkedList<Person>();
        int from = ind,  to = ind;
        while (from > 0 && lastNameList.get(from).getLastName().equals(lastName))
            from--;
        while (to < size() && lastNameList.get(to).getLastName().equals(lastName))
            to++;
        List<Person> res = new ArrayList<>();
        for (int i = from; i < to; i++) {
            res.add(lastNameList.get(i));
        }
        return res;
    }

    @Override
    public Iterable<Person> getAllPersonSortedById() {
        return  idList;
    }

    @Override
    public Iterable<Person> getAllPersonSortedByAge() {
        return ageList;
    }

    @Override
    public Iterable<Person> getAllPersonSortedByLastName() {
        return lastNameList;
    }

    @Override
    public int size() {
        return ageList.size();
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
