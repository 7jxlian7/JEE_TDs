package fr.iut.bean;

import fr.iut.validation.Login;
import fr.iut.validation.Name;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

public class Person {
    @Email
    @NotNull
    private String email;
    @NotNull
    @Name
    private String firstName;
    @NotNull
    @Name
    private String lastName;
    @Login
    @NotNull
    private String login;
    private boolean isStudent;

    public Person(){

    }

    public Person(String email, String firstName, String lastName, String login, boolean isStudent) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.isStudent = isStudent;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }
}
