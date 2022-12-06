package student;

import student.domain.Gender;
import student.domain.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentStatistics {

    private final Collection<Student> students = new ArrayList<>();

    /**
     * Reads all students from given csv-File.
     * @param path the file
     * @throws IOException
     */
    public StudentStatistics(Path path) throws IOException {
        try(Stream<String> stringStream = Files.lines(path)) {
                stringStream
                        .skip(1)
                        .map(Student::of)
                        .forEach(students::add);
        }
    }

    /**
     * Counts all students of given gender
     * @param gender the gender
     * @return number of students of the given gender
     */
    public long countGender(Gender gender) {
            return students
                    .stream()
                    .filter(s -> s.gender().equals(gender))
                    .count();
    }

    /**
     * Returns all students classes sorted alphabetically.
     * @return all students classes sorted alphabetically
     */
    public SortedSet<String> getClasses() {
        return students
                .stream()
                .map(Student::schoolClass)
                .sorted()
                .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Returns a map containing the number students of each gender in a given class.
     * @param schoolClass the class
     * @return count of students of each gender
     */
    public Map<Gender, Long> getGenderCountForClass(String schoolClass) {
            return students
                    .stream()
                    .filter(s -> s.schoolClass().equals(schoolClass))
                    .collect(Collectors.groupingBy(Student::gender , TreeMap::new, Collectors.counting()));

    }

    /**
     * Returns all students whose second name contains the given sequence.
     * @param sequence the sequence to search for. Case sensitive
     * @return students named like %sequence%
     */
    public List<Student> getAllWithSecondNameLike(String sequence) {
        return students
                .stream()
                .filter(s -> s.secondName().contains(sequence))
                .collect(Collectors.toList());
    }

    /**
     * Finds a student by number and class
     * @param number students number
     * @param schoolClass students class
     * @return the student or Optional.empty if no student matches criteria
     */
    public Optional<Student> findByNumberAndClass(int number, String schoolClass) {
        return students
                .stream()
                .filter(s -> s.schoolClass().equals(schoolClass) && s.number() == number).findFirst();
    }

    /**
     * Returns the student with the longest name.
     * @return any student whose name contains the highest number of letters in all names
     */
    public Student getStudentWithLongestName() {
        Optional<Student> optional = students
                .stream()
                .max(Comparator.comparing(s -> s.firstName().length() + s.secondName().length()));
        if (optional.isPresent()) {
            return optional.get();
        }
        else throw new IllegalArgumentException("Es wurden keine Schüler mit dem längsten Namen gefunden!");
    }


    /**
     * Returns the most frequently found first names
     * @param count how many should be returned
     * @return the topX most frequent first names
     */
    public Set<String> getMostFrequentFirstNames(int count) {
        if(count < 1) {
            throw new IllegalArgumentException();
        }
        return students
                .stream()
                .collect(Collectors.groupingBy(Student::firstName, TreeMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue((l1, l2) -> (int) (l2 - l1)))
                .limit(count)
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * Returns the number of students in each year.
     * @return count of students of each year
     */
    public Map<Integer, Long> countStudentsByYear() {
        return students
                .stream()
                .collect(Collectors.groupingBy(s -> Integer.parseInt(String.valueOf(s.schoolClass().charAt(0))) , TreeMap::new, Collectors.counting()));
    }
}
