package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Student {

    private final UUID id;
    @NotBlank
    private final String name;
    @NotBlank
    private final String surname;
    private final String phone;
    @NotBlank
    private final String city;
    @NotBlank
    private final String district;
    private final String description;

    public Student(@JsonProperty("id") UUID id,
                   @JsonProperty("name") String name,
                   @JsonProperty("surname") String surname,
                   @JsonProperty("phone") String phone,
                   @JsonProperty("city") String city,
                   @JsonProperty("district") String district,
                   @JsonProperty("description") String description) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.city = city;
        this.district = district;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getDescription() {
        return description;
    }
}
