package com.example.auditoryexercises.Example_3.JUnit;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;

class PrimeNumberCheckerTest {

    private PrimeNumberChecker primeNumberChecker;

    @BeforeEach
    public void init() {
        primeNumberChecker = new PrimeNumberChecker();
    }

    public static Collection<Object[]> primeNumbers() {
        return Arrays.asList(new Object[][]{
                {2, true},
                {6, false},
                {19, true},
                {19, false},
                {22, false},
                {23, true},
                {28, false},
        });
    }

    @ParameterizedTest
    @MethodSource("primeNumbers")
    public void testPrimeNumberChecker(int inputNumber, boolean expectedResult) {
        System.out.println("Parameterized numbers is: " + inputNumber + "! " + "Expected result is: " + expectedResult + "!");
        Assert.assertEquals(expectedResult, primeNumberChecker.validate(inputNumber));
    }
}