package com.example.auditoryexercises.Example_3.JUnit.DividingTwoIntegers;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DivisionTest {

    private Division division;

    @BeforeEach
    public void init() {
        division = new Division();
    }

    @Test
    public void testDivision() { // shouldThrowAnException
        Assert.assertThrows(ArithmeticException.class, () -> division.divide(12, 0));
    }
}
