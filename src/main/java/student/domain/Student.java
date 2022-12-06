package student.domain;

import java.util.Objects;

public record Student(String schoolClass,
                      int number,
                      String firstName,
                      String secondName,
                      Gender gender) {

    public static Student of(String csv) {

        var splitted = csv.split(",");

        if (splitted.length!=5) {
            throw new IllegalArgumentException();
        }
        var firstName = splitted[1];
        var secondName = splitted[0];
        Gender gender = null;
        if (Objects.equals(splitted[2], "M")) {
             gender = Gender.MALE;
        } else if (Objects.equals(splitted[2], "W")) {
             gender = Gender.FEMALE;
        } else if (Objects.equals(splitted[2], "D")) {
             gender = Gender.DIVERSE;
        }
        int number = Integer.parseInt(splitted[3]);
        var schoolClass = splitted[4];
        return new Student(schoolClass, number, firstName, secondName, gender);
    }

    @Override
    public String toString() {
        return String.format("%s/%02d %s %s %s", schoolClass, number, secondName, firstName, gender);
    }
}
