package edu.scholar_app.domain;

public abstract class User {

    protected int id;
    protected String firstName;
    protected String lastName;
    protected String name;

    public User() {
        firstName = "Leeroy";
        lastName = "Jenkins";
    }

    public String toString() {
        return String.format("[%d] - %s", id, name);
    }

    public void print() {
        System.out.printf("[%d] %s",
                id,
                name
        );
    }

    public abstract String getStatus();

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
