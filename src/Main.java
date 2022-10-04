import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long personsAgeTo18 = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Количество людей допризывного возраста в городе: " + personsAgeTo18 + " человек");

        List<String> personsAgeFrom18to27 = persons.stream()
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .filter(person -> person.getSex() == Sex.MAN)
                .map(Person::getFamily).toList();
        System.out.println("Количество людей призывного возраста в городе: " + personsAgeFrom18to27.size() + " человек");
        //System.out.println("Список людей призывного возраста в городе: " + personsAgeFrom18to27);

        List<Person> humanResources = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> {
                    if (person.getSex() == Sex.MAN) {
                        return person.getAge() >= 18 && person.getAge() <= 65;
                    } else {
                        return person.getAge() >= 18 && person.getAge() <= 60;
                    }
                })
                .sorted(Comparator.comparing(Person::getFamily)).toList();
        System.out.println("Количество потенциально работоспособных людей в городе: " + humanResources.size() + " человек");
        //System.out.println("Список потенциально работоспособных людей в городе:  " + humanResources);
    }
}