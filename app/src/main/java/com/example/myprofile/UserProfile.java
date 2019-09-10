package com.example.myprofile;

import java.io.Serializable;
import java.util.Objects;

public class UserProfile implements Serializable {

    String firstName ;

    public UserProfile(String firstName, String lastName, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                gender.equals(that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, gender);
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    String lastName;
    String gender ;






}
