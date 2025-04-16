package com.example.auditoryexercises.Example_3.JUnit;

public class PrimeNumberChecker {

    public Boolean validate(final Integer primeNumber) {
        for (int i = 2; i < Math.sqrt(primeNumber * 1.0); i++) {
            if (primeNumber % 2 == 0) {
                return false;
            }
        }

        return true;
    }
}
