package com.example.demo.dao;

import com.example.demo.model.File;
import com.example.demo.model.Student;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentDao {

    UUID insertStudent(UUID id, Student student);

    default UUID insertStudent(Student student) {
        UUID id = UUID.randomUUID();
        return insertStudent(id, student);
    }

    List<Student> selectAllStudents();

    Optional<Student> selectStudentById(UUID id);

    int deleteStudentById(UUID id);

    int updateStudentById(UUID id, Student student);

    int uploadFile(UUID id, UUID studentId, String file);

    default int uploadFile(UUID studentId, String file) {
        UUID id = UUID.randomUUID();
        return uploadFile(id, studentId, file);
    }

    int deleteFile(UUID id);

    List<File> selectFileById(UUID id);
}
