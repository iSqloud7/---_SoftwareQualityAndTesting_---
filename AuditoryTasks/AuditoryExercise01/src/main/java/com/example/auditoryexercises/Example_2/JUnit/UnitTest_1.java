package com.example.auditoryexercises.Example_2.JUnit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTest_1 {

    String message = "Ivan";

    MessageUtil messageUtil_obj = new MessageUtil(message);

    @Test
    public void testSalutationMessageTrue() {
        System.out.println("Inside testSalutationMessage()");
        message = "Hi!" + "Ivan";

        assertEquals(message, messageUtil_obj.salutationMessage());
    }

    @Test
    public void testSalutationMessageFalse() {
        message = "Pupinoski";

        assertEquals(message, messageUtil_obj.salutationMessage());
    }
}
