package student;

import student.domain.Gender;
import student.domain.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Schueler {
    private static final Collection<Student> student = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        new Schueler(Path.of("src/main/resources/schueler.csv"));

        System.out.println("-------------------------------- \n a)");

        System.out.println(student.stream()
                .filter(s -> s.gender().equals(Gender.FEMALE))
                .count());

        System.out.println("-------------------------------- \n b)");

        System.out.println(student.stream()
                .filter(s -> s.gender().equals(Gender.FEMALE))
                .map(Student::firstName)
                .reduce((s1, s2) -> s1 + "; " + s2).get());

        System.out.println("-------------------------------- \n c)");

        student.stream()
                .filter(s -> s.gender().equals(Gender.FEMALE))
                .sorted(Comparator.comparing(Student::schoolClass).thenComparing(Student::number))
                .forEach(System.out::println);

        System.out.println("-------------------------------- \n d)");

        System.out.println(Arrays.toString(student.stream()
                .filter(s -> s.schoolClass().contains("4") && s.firstName()
                        .equals("Lukas"))
                .toArray()));

        System.out.println("-------------------------------- \n e)");

        System.out.println(student.stream()
                .max(Comparator.comparing(s -> s.firstName().length() + s.secondName().length()))
                .get());

        System.out.println("-------------------------------- \n f)");

        Optional<Student> optional = student.stream()
                .filter(s -> s.firstName().equals("Julia"))
                .findFirst();
        if (optional.isPresent()) {
            System.out.println(optional.get());
        } else {
            System.out.println("Keine Julia gefunden");
        }

        System.out.println("-------------------------------- \n g)");

        System.out.println(student.stream()
                .map(Student::schoolClass)
                .sorted()
                .reduce((s1, s2) -> s1 + ", " + s2).get());

        System.out.println("-------------------------------- \n h)");

        System.out.println(student.stream()
                .collect(Collectors.groupingBy(Student::schoolClass, Collectors.counting())));


    }



    public Schueler(Path path) throws IOException {
        try(Stream<String> stringStream = Files.lines(path)) {
            stringStream
                    .skip(1)
                    .map(Student::of)
                    .forEach(student::add);
        }
    }


}
