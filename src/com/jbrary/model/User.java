package com.jbrary.model;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class User {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private int id;
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private int level;
    private String program;
    private String residence;
    private String image;

    public User(int id, String name, LocalDate dateOfBirth, String gender, int level, String program, String residence, String image) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.level = level;
        this.program = program;
        this.residence = residence;
        this.image = image;
    }

    public User(int id, String name, String dateOfBirth, String gender, int level, String program, String residence, String image) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = LocalDate.parse(dateOfBirth, dateTimeFormatter);
        this.gender = gender;
        this.level = level;
        this.program = program;
        this.residence = residence;
        this.image = image;
    }

    public User(String name, LocalDate dateOfBirth, String gender, int level, String program, String residence, String image) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.level = level;
        this.program = program;
        this.residence = residence;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDateOfBirthString() {
        return dateTimeFormatter.format(dateOfBirth);
    }

    public String getGender() {
        return gender;
    }

    public int getLevel() {
        return level;
    }

    public String getProgram() {
        return program;
    }

    public String getResidence() {
        return residence;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
