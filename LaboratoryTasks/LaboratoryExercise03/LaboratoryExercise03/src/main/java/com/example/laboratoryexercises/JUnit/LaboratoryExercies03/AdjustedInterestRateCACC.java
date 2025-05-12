package com.example.laboratoryexercises.JUnit.LaboratoryExercies03;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AdjustedInterestRateCACC {

    // Method under test
    public static double calculateAdjustedInterestRate(int creditScore,
                                                       boolean isFirstTimeBorrower,
                                                       double loanAmount,
                                                       int yearsEmployed) {
        if ((yearsEmployed > 3 && loanAmount <= 10000 && !isFirstTimeBorrower) ||
                (loanAmount > 10000 && creditScore > 700)) {
            return 3.5 + (loanAmount / 10000);
        } else {
            return 6.5 + (loanAmount / 5000);
        }
    }

    private static Stream<Arguments> testData() {
        return Stream.of(
                // CACC for A (yearsEmployed > 3)
                Arguments.of(650, false, 10000, 4, 3.5 + 10000 / 10000.0),  // true path
                Arguments.of(650, false, 10000, 3, 6.5 + 10000 / 5000.0),  // false path

                // CACC for B (loanAmount <= 10000)
                Arguments.of(650, false, 10000, 4, 3.5 + 10000 / 10000.0),
                Arguments.of(650, false, 11000, 4, 6.5 + 11000 / 5000.0),

                // CACC for C (!isFirstTimeBorrower)
                Arguments.of(650, false, 10000, 4, 3.5 + 10000 / 10000.0),
                Arguments.of(650, true, 10000, 4, 6.5 + 10000 / 5000.0),

                // CACC for D (loanAmount > 10000)
                Arguments.of(750, true, 11000, 2, 3.5 + 11000 / 10000.0),
                Arguments.of(750, true, 10000, 2, 6.5 + 10000 / 5000.0),

                // CACC for E (creditScore > 700)
                Arguments.of(750, true, 11000, 2, 3.5 + 11000 / 10000.0),
                Arguments.of(650, true, 11000, 2, 6.5 + 11000 / 5000.0)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testCalculateAdjustedInterestRate(int creditScore, boolean isFirstTimeBorrower, double loanAmount, int yearsEmployed, double expected) {
        double result = calculateAdjustedInterestRate(creditScore, isFirstTimeBorrower, loanAmount, yearsEmployed);
        assertEquals(expected, result, 0.01,
                String.format("Failed for CS=%d FTB=%b LA=%.2f YRS=%d", creditScore, isFirstTimeBorrower, loanAmount, yearsEmployed));
    }
}
