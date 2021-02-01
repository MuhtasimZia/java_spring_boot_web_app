package com.javatest.javawebapp.model;



import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import javax.validation.constraints.*;

@Entity
@Table
public class Prescription {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "firstName can not be null!!")
    @NotEmpty(message = "firstName can not be empty!!")
    private String firstName;

    @NotNull(message = "LastName can not be null!!")
    @NotEmpty(message = "LastName can not be empty!!")
    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "prescription date should be less than current date!!")
    private LocalDate prescription_date;

    @NotNull(message = "age can not be null!!")
    @Min(18)
    private Integer age;

    private String gender;

    @NotNull(message = "diagnosis can not be null!!")
    @NotEmpty(message = "diagnosis can not be empty!!")
    private String diagnosis;

    @NotNull(message = "medicine can not be null!!")
    @NotEmpty(message = "medicine can not be empty!!")
    private String medicine;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "next date must be after current date")
    private LocalDate next_date;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getPrescription_date() {
        return prescription_date;
    }

    public void setPrescription_date(LocalDate prescription_date) {
        this.prescription_date = prescription_date;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public LocalDate getNext_date() {
        return next_date;
    }

    public void setNext_date(LocalDate next_date) {
        this.next_date = next_date;
    }
}
