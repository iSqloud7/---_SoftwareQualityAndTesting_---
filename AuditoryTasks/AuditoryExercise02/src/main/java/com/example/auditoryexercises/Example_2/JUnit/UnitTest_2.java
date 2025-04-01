package com.example.auditoryexercises.Example_2.JUnit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTest_2 {

    String message = "Marija";

    MessageUtil messageUtil_obj = new MessageUtil(message);

    @Test
    public void testSalutationMesage() {
        System.out.println("Inside testSalutationMessage()");
        message = "Hi!" + "Marija";

        assertEquals(message, messageUtil_obj.salutationMessage());
    }
}
