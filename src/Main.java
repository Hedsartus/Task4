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

        long count = persons.stream().filter(v -> v.getAge() < 18).count();
        System.out.println("Количество несовершеннолетних : " + count);

        List<String> conscripts = persons.stream()
                .filter(p -> p.getSex() == Sex.MAN)
                .filter(p -> p.getAge() < 27 && p.getAge() >= 18)
                .map(Person::getFamily).collect(Collectors.toList());
        System.out.println("Призывников : " + conscripts.size());

        List<Person> employables = persons.stream()
                .filter(p -> p.getEducation() == Education.HIGHER)
                .filter(p -> {
                    if (p.getAge() >= 18 && p.getAge() < 65) {
                        return p.getSex() != Sex.WOMAN || p.getAge() < 60;
                    } else {
                        return false;
                    }
                })
                .sorted(Comparator.comparing(Person::getFamily)).toList();
        System.out.println("Employables : " + employables.size());
    }


}
