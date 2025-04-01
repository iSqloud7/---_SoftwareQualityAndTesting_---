package com.example.auditoryexercises.Example_1.JUnit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest_3 {

    Message message_obj = new Message("Test Message");

    @Test
    public void testGoodbyeMessageTrue() {
        assertEquals(message_obj.goodbyeMessage(), "Goodbye ");
    }

    @Test
    public void testGoodbyeMessageFalse() {
        assertEquals(message_obj.goodbyeMessage(), "Goodbye!!!");
    }
}