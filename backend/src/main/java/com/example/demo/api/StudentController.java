package com.example.demo.api;

import com.example.demo.model.File;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/student")
@RestController
@CrossOrigin("*")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public UUID addStudent(@Valid @NonNull @RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(path = "{id}")
    public Student getStudentById(@PathVariable("id") @Valid @NonNull UUID id) {
        return studentService.getStudentById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public List<Student> deleteStudentById(@PathVariable("id") @Valid @NonNull UUID id) {
        studentService.deleteStudent(id);
        return studentService.getAllStudents();
    }

    @PutMapping(path = "{id}")
    public void updateStudent(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Student studentToUpdate) {
        studentService.updateStudent(id, studentToUpdate);
    }

    @PostMapping(path="upload/{id}")
    public void uploadFile(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody String file) {
        studentService.uploadFile(id, file);
    }

    @DeleteMapping(path="file-delete/{id}")
    public void deleteFile(@PathVariable("id") @Valid @NonNull UUID id) {
        studentService.deleteFile(id);
    }

    @GetMapping(path="file/{id}")
    public List<File> getFileById(@PathVariable("id") @Valid @NonNull UUID id) {
        return studentService.getFileById(id);
    }
}
