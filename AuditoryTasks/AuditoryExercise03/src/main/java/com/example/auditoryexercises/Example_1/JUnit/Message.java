package com.example.auditoryexercises.Example_1.JUnit;

public class Message {

    private String message;

    public Message(String message) {
        this.message = message;
    }

    public String printMessage() {
        System.out.println(message);

        return message;
    }

    public String salutationMessage() {
        message = "Hi " + message;
        System.out.println(message);

        return message;
    }

    public String goodbyeMessage() {
        message = "Goodbye ";
        System.out.println(message);

        return message;
    }
}
