package com.example.auditoryexercises.Example_1.JUnit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageTest_2 {

    String message = "Marija";

    Message message_obj = new Message(message);

    @Test
    public void testSalutationMessage() {
        assertEquals("Hi " + message, message_obj.salutationMessage());
    }
}
