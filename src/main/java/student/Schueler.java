package student;

import student.domain.Gender;
import student.domain.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Stream;

public class Schueler implements Comparable<Schueler> {
    private static final Collection<Student> student = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        new Schueler(Path.of("src/main/resources/schueler.csv"));

        System.out.println(student.stream().filter(s -> s.gender().equals(Gender.FEMALE)).count());
        System.out.println("--------------------------------");
        System.out.println(student.stream().filter(s -> s.gender().equals(Gender.FEMALE)).map(Student::firstName).reduce((s1, s2) -> s1 + "; " + s2).get());
        System.out.println("--------------------------------");
     //   student.stream().filter(s -> s.gender().equals(Gender.FEMALE)).sorted().forEach(System.out::println);
        System.out.println("--------------------------------");
        System.out.println(Arrays.toString(student.stream().filter(s -> s.schoolClass().contains("4")).filter(s1 -> s1.firstName().equals("Lukas")).toArray()));
        System.out.println("--------------------------------");
        System.out.println(student.stream().max(Comparator.comparing(s -> s.firstName().length() + s.secondName().length())).get());
        System.out.println("--------------------------------");

    }



    public Schueler(Path path) throws IOException {
        try(Stream<String> stringStream = Files.lines(path)) {
            stringStream
                    .skip(1)
                    .map(Student::of)
                    .forEach(student::add);
        }
    }


    @Override
    public int compareTo(Schueler o) {
        return 0;
    }
}
