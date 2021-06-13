package edu.scholar_app.core;

import edu.scholar_app.domain.Student;
import edu.scholar_app.domain.Teacher;
import edu.scholar_app.domain.User;
import edu.scholar_app.exception.IncompleteNameException;
import edu.scholar_app.exception.NegativeValueException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScholarApp {

    private static User[] users;
    private static int index;
    private static final int LEN = 100;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        users = new User[LEN];

        String option;

        do {
            System.out.println();
            System.out.println("[1] Register a student report card");
            System.out.println("[2] Register a teacher report card");
            System.out.println("[3] Consult the status of a student/teacher");
            System.out.println("[4] View all report cards");
            System.out.println("[5] Exit");

            System.out.print("\nEnter the desired option: ");
            option = in.next();

            switch (option) {
                case "1":
                    try {
                        if (index < LEN) {
                            Student student = new Student();
                            student.setId(index);

                            try {
                                inputName(student, in);
                            } catch (IncompleteNameException e) {
                                System.out.println(e.getMessage());
                                break;
                            }

                            System.out.print("Enter the AV1 grade: ");
                            student.setAv1Grade(in.nextFloat());

                            System.out.print("Enter the AV2 grade: ");
                            student.setAv2Grade(in.nextFloat());

                            users[index] = student;

                            System.out.println("\nStudent successfully registered in the position " + users[index]);

                            index++;
                        } else {
                            System.out.println("There is no more vacancy for registration!");
                        }
                    } finally {
                        System.out.println("Returning to the menu...");
                    }
                    break;

                case "2":
                    try {
                        if (index < LEN) {
                            Teacher teacher = new Teacher();
                            teacher.setId(index);

                            try {
                                inputName(teacher, in);
                            } catch (IncompleteNameException e) {
                                System.out.println(e.getMessage());
                                break;
                            }

                            System.out.print("Enter the teacher's subject: ");
                            teacher.setSubject(in.next());

                            System.out.print("Enter the teaching experience: ");
                            teacher.setExperience(in.nextInt());

                            try {
                                System.out.print("Enter the salary amount: ");
                                teacher.setSalary(in.nextDouble());
                            } catch (NegativeValueException e) {
                                System.out.println(e.getMessage());
                                break;
                            }

                            users[index] = teacher;

                            System.out.println("\nTeacher successfully registered in the position " + users[index]);

                            index++;
                        } else {
                            System.out.println("There is no more vacancy for registration!");
                        }
                    } finally {
                        System.out.println("Returning to the menu...");
                    }
                    break;

                case "3":
                    try {
                        System.out.print("Enter the position: ");
                        int pos = in.nextInt();

                        if (pos >= 0 && pos < index) {
                            users[pos].print();
                        } else {
                            System.out.println("Invalid position!");
                        }
                    } finally {
                        System.out.println("Returning to the menu...");
                    }
                    break;

                case "4":
                    try {
                        if (index != 0) {
                            print();
                        } else {
                            System.out.println("No registered users!");
                        }
                    } finally {
                        System.out.println("Returning to the menu...");
                    }
                    break;

                case "5":
                    System.out.println("Shutting down...");
                    break;

                default:
                    try {
                        System.out.println("Invalid option!");
                    } finally {
                        System.out.println("Returning to the menu...");
                    }
                    break;
            }

        } while (!option.equals("5"));

        in.close();
    }

    private static void inputName(User user, Scanner in) throws IncompleteNameException {

        System.out.print("Enter the first name: ");
        user.setFirstName(in.next().trim());

        System.out.print("Enter the last name: ");
        user.setLastName(in.next().trim());

        String regex = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcherFirstName = pattern.matcher(user.getFirstName());
        Matcher matcherLastName = pattern.matcher(user.getLastName());

        if (!matcherFirstName.matches() || !matcherLastName.matches()) {
            throw new IncompleteNameException("Invalid name");
        }

        StringBuilder sb = new StringBuilder();

        sb.append(user.getFirstName().toUpperCase().charAt(0)).append(user.getFirstName().substring(1));
        sb.append(" ");
        sb.append(user.getLastName().toUpperCase().charAt(0)).append(user.getLastName().substring(1));

        user.setName(sb.toString());
    }

    private static void print() {

        List<Student> studentList = new ArrayList<>();
        List<Teacher> teacherList = new ArrayList<>();

        for (int i = 0; i < index; i++) {
            if (users[i] instanceof Student) {
                studentList.add((Student) users[i]);
            } else if (users[i] instanceof Teacher) {
                teacherList.add((Teacher) users[i]);
            }
        }

        System.out.println("Student's list:");
        studentList.forEach(Student::print);

        System.out.println(" ");

        System.out.println("Teacher's list:");
        teacherList.forEach(Teacher::print);
    }
}
