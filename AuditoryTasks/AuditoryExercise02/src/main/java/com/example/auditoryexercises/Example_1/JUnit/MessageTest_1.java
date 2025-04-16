package com.example.auditoryexercises.Example_1.JUnit;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;

public class MessageTest_1 {

    String message = "Marija";

    Message message_obj = new Message(message);

    @Test
    public void testPrintMessageTrue() {
        assertEquals(message, message_obj.printMessage());
    }

    @Test
    public void testPrintMessageFalse() {
        assertEquals("Ivan", message_obj.printMessage());
    }

    @Test
    public void testPrintMessageWithDuration() {
        assertTimeout(Duration.ofMillis(5000), () -> {
            System.out.println("Inside testPrintMessage()");
            message_obj.printMessage();

            Thread.sleep(1000);
        });
    }
}
