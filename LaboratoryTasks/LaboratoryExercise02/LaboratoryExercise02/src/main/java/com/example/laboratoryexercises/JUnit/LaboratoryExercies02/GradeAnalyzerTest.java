package com.example.laboratoryexercises.JUnit.LaboratoryExercies02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GradeAnalyzerTest {

    // ---- "Invalid grades detected." paths ----

    @Test
    public void testSingleInvalidGrade() {
        // Path: [1, 2, 3, 4, 5, 6, 10, 2, 3, 11, 12]
        // A path where an invalid grade is found during iteration
        int[] grades = {-5};
        String result = GradeAnalyzer.analyzeGrades(grades);
        assertEquals("Invalid grades detected.", result);
    }

    @Test
    public void testPassingThenInvalid() {
        // Path: [1, 2, 3, 4, 5, 7, 8, 10, 2, 3, 11, 12]
        // Path where we have valid passing grades before finding an invalid one
        int[] grades = {75, 101};
        String result = GradeAnalyzer.analyzeGrades(grades);
        assertEquals("Invalid grades detected.", result);
    }

    @Test
    public void testFailingThenInvalid() {
        // Path: [1, 2, 3, 4, 5, 7, 9, 10, 2, 3, 11, 12]
        // Path where we have valid failing grades before finding an invalid one
        int[] grades = {45, 101};
        String result = GradeAnalyzer.analyzeGrades(grades);
        assertEquals("Invalid grades detected.", result);
    }

    // Note: Path [1, 2, 3, 11, 12] with empty array actually leads to "No students passed"

    // ---- "All students passed." paths ----

    @Test
    public void testAllStudentsPassed() {
        // Path: [1, 2, 3, 4, 5, 7, 8, 10, 2, 3, 11, 13, 14]
        // Path where all grades are passing (≥50)
        int[] grades = {50, 75, 90};
        String result = GradeAnalyzer.analyzeGrades(grades);
        assertEquals("All students passed.", result);
    }

    @Test
    public void testSingleStudentPassed() {
        // Similar to Path: [1, 2, 3, 4, 5, 7, 8, 10, 2, 3, 11, 13, 14] but with one iteration
        // Single passing grade
        int[] grades = {75};
        String result = GradeAnalyzer.analyzeGrades(grades);
        assertEquals("All students passed.", result);
    }

    // Note: Paths [1, 2, 3, 4, 5, 6, 10, 2, 3, 11, 13, 14],
    // [1, 2, 3, 4, 5, 7, 9, 10, 2, 3, 11, 13, 14], and [1, 2, 3, 11, 13, 14]
    // are logically impossible for "All students passed"

    // ---- "No students passed." paths ----

    @Test
    public void testAllStudentsFailed() {
        // Path: [1, 2, 3, 4, 5, 7, 9, 10, 2, 3, 11, 13, 15, 16]
        // Path where all grades are failing (<50)
        int[] grades = {30, 45, 49};
        String result = GradeAnalyzer.analyzeGrades(grades);
        assertEquals("No students passed.", result);
    }

    @Test
    public void testEmptyArray() {
        // Path: [1, 2, 3, 11, 13, 15, 16]
        // Path for an empty array where no students passed
        int[] grades = {};
        String result = GradeAnalyzer.analyzeGrades(grades);
        assertEquals("No students passed.", result);
    }

    @Test
    public void testSingleStudentFailed() {
        // Similar to Path: [1, 2, 3, 4, 5, 7, 9, 10, 2, 3, 11, 13, 15, 16] but with one iteration
        // Single failing grade
        int[] grades = {45};
        String result = GradeAnalyzer.analyzeGrades(grades);
        assertEquals("No students passed.", result);
    }

    // Note: Paths [1, 2, 3, 4, 5, 6, 10, 2, 3, 11, 13, 15, 16] and
    // [1, 2, 3, 4, 5, 7, 8, 10, 2, 3, 11, 13, 15, 16] are logically impossible for "No students passed"

    // ---- "Some students passed." paths ----

    @Test
    public void testSomeStudentsPassedPassingFirst() {
        // Path: [1, 2, 3, 4, 5, 7, 8, 10, 2, 3, 11, 13, 15, 17]
        // Path with mix of passing and failing grades (some passing)
        int[] grades = {75, 45};
        String result = GradeAnalyzer.analyzeGrades(grades);
        assertEquals("Some students passed.", result);
    }

    @Test
    public void testSomeStudentsPassedFailingFirst() {
        // Path: [1, 2, 3, 4, 5, 7, 9, 10, 2, 3, 11, 13, 15, 17]
        // Path with mix of passing and failing grades (first failing)
        int[] grades = {45, 75};
        String result = GradeAnalyzer.analyzeGrades(grades);
        assertEquals("Some students passed.", result);
    }

    @Test
    public void testMultipleMixedGrades() {
        // Combined path with multiple iterations through both passing and failing branches
        int[] grades = {45, 75, 30, 80, 49};
        String result = GradeAnalyzer.analyzeGrades(grades);
        assertEquals("Some students passed.", result);
    }

    // Note: Paths [1, 2, 3, 4, 5, 6, 10, 2, 3, 11, 13, 15, 17] and
    // [1, 2, 3, 11, 13, 15, 17] are logically impossible for "Some students passed"
}
