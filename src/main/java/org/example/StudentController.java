package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/v1/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

//    @GetMapping
//    public ResponseEntity<List<StudentDto>> getAllStudents() {
//        return ResponseEntity.ok(studentService.getAllStudents());
//    }

    @GetMapping
    public ResponseEntity<?> getStudent(@RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            return ResponseEntity.ok(studentService.getStudentById(id));
        }
        return ResponseEntity.ok(studentService.getAllStudents());
    }
}
