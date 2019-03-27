package com.jbrary.model;

import java.time.LocalDate;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String otherNames;
    private LocalDate dateOfBirth;
    private String gender;
    private String level;
    private String program;
    private String residence;
    private String image;

    public User(int id, String firstName, String lastName, String otherNames, LocalDate dateOfBirth, String gender, String level, String program, String residence, String image) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.otherNames = otherNames;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.level = level;
        this.program = program;
        this.residence = residence;
        this.image = image;
    }

    public User(String firstName, String lastName, String otherNames, LocalDate dateOfBirth, String gender, String level, String program, String residence, String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.otherNames = otherNames;
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getOtherNames() {
        return otherNames;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getLevel() {
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setOtherNames(String otherNames) {
        this.otherNames = otherNames;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLevel(String level) {
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
