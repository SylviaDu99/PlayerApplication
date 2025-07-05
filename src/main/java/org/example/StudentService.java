package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(this::convertToDto).collect(Collectors.toList());
    }

    public Optional<StudentDto> getStudentById(long id) {
        Optional<Student> student = studentRepository.findById((int) id);
        return student.map(this::convertToDto);
    }

    public StudentDto convertToDto(Student student) {
        return new StudentDto(student.getId(), student.getName());
    }
}
