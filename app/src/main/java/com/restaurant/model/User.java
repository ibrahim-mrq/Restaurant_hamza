package com.restaurant.model;

import org.jetbrains.annotations.NotNull;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String phone2;
    private String password;
    private String governorate;
    private String neighborhood;
    private String houseNumber;
    private String navigational;

    public User() {
    }

    public User(int id, String firstName, String lastName, String email, String phone,
                String phone2, String password, String governorate, String neighborhood,
                String houseNumber, String navigational) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.phone2 = phone2;
        this.password = password;
        this.governorate = governorate;
        this.neighborhood = neighborhood;
        this.houseNumber = houseNumber;
        this.navigational = navigational;
    }

    @NotNull
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", phone2='" + phone2 + '\'' +
                ", password='" + password + '\'' +
                ", governorate='" + governorate + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", navigational='" + navigational + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getGovernorate() {
        return governorate;
    }

    public void setGovernorate(String governorate) {
        this.governorate = governorate;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getNavigational() {
        return navigational;
    }

    public void setNavigational(String navigational) {
        this.navigational = navigational;
    }
}
