package other;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {

    // need getter methods in student class to run
    @Override
    public int compare(Student s1, Student s2) {
        int compareGpa = s2.getGpa().compareTo(s1.getGpa());
        if (compareGpa != 0) return compareGpa;
        return s1.getName().compareTo(s2.getName());
    }
}
