package com.example.demo.dao;

import com.example.demo.model.File;
import com.example.demo.model.Student;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("hsql")
public class StudentDataAccessService implements StudentDao {

    private final JdbcTemplate jdbcTemplate;

    public StudentDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UUID insertStudent(UUID id, Student student) {
        final String sql = "INSERT INTO students VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                id,
                student.getName(),
                student.getSurname(),
                student.getPhone(),
                student.getCity(),
                student.getDistrict(),
                student.getDescription());
        return id;
    }

    @Override
    public List<Student> selectAllStudents() {
        final String sql = "SELECT id, name, surname, phone, city, district, description FROM students";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String phone = resultSet.getString("phone");
            String city = resultSet.getString("city");
            String district = resultSet.getString("district");
            String description = resultSet.getString("description");
            return new Student(id, name, surname, phone, city, district, description);
        });
    }

    @Override
    public Optional<Student> selectStudentById(UUID id) {
        final String sql = "SELECT id,name, surname, phone, city, district, description FROM students WHERE id = ?";
        Student student = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
            UUID studentId = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String phone = resultSet.getString("phone");
            String city = resultSet.getString("city");
            String district = resultSet.getString("district");
            String description = resultSet.getString("description");
            return new Student(studentId, name, surname, phone, city, district, description);
        });
        return Optional.ofNullable(student);
    }

    @Override
    public int deleteStudentById(UUID id) {
        final String sql = "DELETE FROM students WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int updateStudentById(UUID id, Student student) {
        final String sql = "UPDATE students " +
                "SET name = ?, " +
                "surname = ?, " +
                "phone = ?, " +
                "city = ?, " +
                "district = ?, " +
                "description = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                student.getName(),
                student.getSurname(),
                student.getPhone(),
                student.getCity(),
                student.getDistrict(),
                student.getDescription(),
                id);
    }

    @Override
    public int uploadFile(UUID id, UUID studentId, String file) {
        final String sql = "INSERT INTO files VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, id, studentId, file);
    }

    @Override
    public int deleteFile(UUID id) {
        final String sql = "DELETE FROM files WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<File> selectFileById(UUID id) {
        try {
            final String sql = "SELECT id, studentId, file FROM files WHERE studentId = ?";
            return jdbcTemplate.query(sql, new Object[]{id}, (resultSet, i) -> {
                UUID fileId = UUID.fromString(resultSet.getString("id"));
                UUID studentId = UUID.fromString(resultSet.getString("studentId"));
                String fileSource = resultSet.getString("file");
                return new File(fileId, studentId, fileSource);
            });
        }
        catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
}
