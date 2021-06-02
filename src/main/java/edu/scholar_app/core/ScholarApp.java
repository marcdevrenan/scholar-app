package edu.scholar_app.core;

import java.util.Scanner;

public class ScholarApp {

    private static String[] names;
    private static float[] av1Grades;
    private static float[] av2Grades;
    private static int index;
    private static final int LEN = 100;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        names = new String[LEN];
        av1Grades = new float[LEN];
        av2Grades = new float[LEN];

        String option;

        do {
            System.out.println();
            System.out.println("[1] Register a student report card");
            System.out.println("[2] Consult a student report card");
            System.out.println("[3] View all student's report cards");
            System.out.println("[4] Exit");

            System.out.print("\nEnter the desired option: ");
            option = in.next();

            switch (option) {
                case "1":
                    if (index < LEN) {
                        System.out.print("Enter the student's name: ");
                        names[index] = in.next();

                        System.out.print("Enter the AV1 grade: ");
                        av1Grades[index] = in.nextFloat();

                        System.out.print("Enter the AV2 grade: ");
                        av2Grades[index] = in.nextFloat();

                        System.out.println("\nStudent successfully registered in the position " + index);

                        index++;
                    } else {
                        System.out.println("There is no more vacancy for registration!");
                    }
                    break;

                case "2":
                    System.out.print("Enter the position: ");
                    int pos = in.nextInt();

                    if (pos >= 0 && pos < index) {
                        print(pos);
                    } else {
                        System.out.println("Invalid position!");
                    }
                    break;

                case "3":
                    if (index != 0) {
                        print();
                    } else {
                        System.out.println("No registered students!");
                    }
                    break;

                case "4":
                    System.out.println("Shutting down...");
                    break;

                default:
                    System.out.println("Invalid option!");
                    break;
            }

        } while (!option.equals("4"));

        sendReport();
        in.close();
    }

    private static void print() {
        System.out.println("Student's list:\n");
        for (int i = 0; i < index; i++) {
            print(i);
        }
    }

    private static void print(int idx) {
        float averageGrade = getAverage(idx);
        System.out.printf("[%d] %s - AV1(%.2f) + AV2(%.2f) = Average(%.2f) - Status (%s)\n",
                idx + 1,
                names[idx],
                av1Grades[idx],
                av2Grades[idx],
                averageGrade,
                getStatus(averageGrade)
        );
    }

    private static float getAverage(int idx) {
        return (av1Grades[idx] + av2Grades[idx]) / 2;
    }

    private static String getStatus(float finalGrade) {
        if (finalGrade >= 7) {
            return "Approved";
        } else if (finalGrade < 4) {
            return "Disapproved";
        } else {
            return "Final Test";
        }
    }

    private static float getAverageGrades() {
        float sum = 0;

        for (int i = 0; i < index; i++) {
            sum = sum + getAverage(i);
        }

        return sum / index;
    }

    private static void sendReport() {
        int amount = index;

        System.out.println("Reporting status...");
        System.out.println("Number of students: " + amount);
        System.out.printf("Average student grades: %.2f", getAverageGrades());
    }
}
