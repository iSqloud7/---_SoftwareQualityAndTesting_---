package com.example.laboratoryexercises.JUnit.LaboratoryExercies02;

public class GradeAnalyzer {

    public static String analyzeGrades(int[] grades) {
        boolean hasInvalid = false;
        int passCount = 0;

        for (int i = 0; i < grades.length; i++) {
            int grade = grades[i];

            if (grade < 0 || grade > 100) {
                hasInvalid = true;
            } else if (grade >= 50) {
                passCount++;
            }
        }

        if (hasInvalid) {
            return "Invalid grades detected.";
        } else if (passCount == grades.length && grades.length > 0) {
            return "All students passed.";
        } else if (passCount == 0) {
            return "No students passed.";
        } else {
            return "Some students passed.";
        }
    }
}
