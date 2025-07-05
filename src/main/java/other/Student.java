package other;


import java.util.Objects;

// student class with custom equals and hashcode methods
public class Student {
    public String name;
    public int age;
    public String studentId;
    public double gpa;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) {
            return false;
        }
        final Student s = (Student) o;
        if (this.name != s.name) return false;
        if (this.age != s.age) return false;
        if (this.studentId != s.studentId) return false;
        if (this.gpa != s.gpa) return false;
        return true;
    }

    // very simple hashcode method using java's built-in hash method
    @Override
    public int hashCode() {
        return Objects.hash(name, age, studentId, gpa);
    }
}
