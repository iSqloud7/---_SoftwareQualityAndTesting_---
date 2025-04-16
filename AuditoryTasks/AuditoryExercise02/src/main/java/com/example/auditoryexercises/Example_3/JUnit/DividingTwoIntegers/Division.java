package com.example.auditoryexercises.Example_3.JUnit.DividingTwoIntegers;

public class Division {

    public int divide(int x, int y) {
        if (y == 0) {
            throw new ArithmeticException("Division by zero!");
        }

        return x / y;
    }
}
