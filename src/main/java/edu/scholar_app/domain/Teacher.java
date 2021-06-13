package edu.scholar_app.domain;

import edu.scholar_app.exception.NegativeValueException;

public class Teacher extends User {

    private String subject;
    private int experience;
    private double salary;

    public Teacher() {
        super();
    }

    @Override
    public String getStatus() {
        if (getExperience() == 0 || getSalary() <= 1200) {
            return "Novice";
        } else if (getExperience() >= 6 || getSalary() >= 4800) {
            return "Master";
        } else {
            return "Apprentice";
        }
    }

    @Override
    public void print() {
        super.print();
        System.out.printf(" - %d years of teaching experience, earning %.2f - %s in %s\n",
                experience,
                salary,
                getStatus(),
                subject
        );
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) throws NegativeValueException {
        if (salary < 0) {
            throw new NegativeValueException("Invalid salary amount");
        }
        this.salary = salary;
    }
}
