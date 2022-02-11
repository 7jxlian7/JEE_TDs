package fr.iut.bean;

import fr.iut.validation.Name;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public class Project {
    private String description;
    @NotNull
    @Name
    private String name;
    @NotNull
    private String urlRepository;
    protected ArrayList<Person> teachers;
    protected ArrayList<Person> students;
    protected ArrayList<Person> customers;
    protected ArrayList<Document> mesDocuments;

    public Project(String description, String name, String urlRepository, ArrayList<Person> teachers, ArrayList<Person> students, ArrayList<Person> customers, ArrayList<Document> mesDocuments) {
        this.description = description;
        this.name = name;
        this.urlRepository = urlRepository;
        this.teachers = teachers;
        this.students = students;
        this.customers = customers;
        this.mesDocuments = mesDocuments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlRepository() {
        return urlRepository;
    }

    public void setUrlRepository(String urlRepository) {
        this.urlRepository = urlRepository;
    }

    public ArrayList<Person> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<Person> teachers) {
        this.teachers = teachers;
    }

    public ArrayList<Person> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Person> students) {
        this.students = students;
    }

    public ArrayList<Person> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Person> customers) {
        this.customers = customers;
    }

    public ArrayList<Document> getMesDocuments() {
        return mesDocuments;
    }

    public void setMesDocuments(ArrayList<Document> mesDocuments) {
        this.mesDocuments = mesDocuments;
    }
}
