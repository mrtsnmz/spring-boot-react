package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Base64;
import java.util.UUID;

public class File {

    private final UUID id;
    private final UUID studentId;
    private final String file;

    public File(@JsonProperty("id") UUID id,
                   @JsonProperty("studentId") UUID studentId,
                   @JsonProperty("file") String file) {
        this.id = id;
        this.studentId = studentId;
        this.file = file;
    }

    public UUID getId() {
        return id;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public String getFile() {
        return file;
    }

}
