/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Ruan
 */
public class MessageTest {

    private Message validMessage;

    @BeforeEach
    public void setUp() {

        validMessage = new Message("+27718693002",
                "Hi Mike, can you join us for dinner tonight?", 0);
    }

    @Test
    public void testMessageLengthSuccess() {

        assertEquals("Message ready to send.",
                validMessage.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {

        String longMessage = "A".repeat(260);

        Message message = new Message("+27718693002", longMessage, 0);

        assertEquals("Message exceeds 250 characters by 10; please reduce the size.",
                message.checkMessageLength());
    }

    @Test
    public void testRecipientCellSuccess() {

        assertEquals("Cell phone number successfully captured.",
                validMessage.checkRecipientCell());
    }

    @Test
    public void testRecipientCellFailure() {

        Message message = new Message("08575975889",
                "Hi Keegan, did you receive the payment?", 1);

        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                message.checkRecipientCell());
    }

    @Test
    public void testMessageHash() {

        validMessage.setMessageID("0012345678");

        assertEquals("00:0:HITONIGHT",
                validMessage.createMessageHash());
    }

    @Test
    public void testMessageIDGenerated() {

        String messageID = validMessage.generateMessageID();

        assertEquals(10, messageID.length());
        assertTrue(validMessage.checkMessageID());
    }

    @Test
    public void testSendMessageOption() {

        assertEquals("Message successfully sent.",
                validMessage.sentMessage(1));
    }

    @Test
    public void testDisregardMessageOption() {

        assertEquals("Press 0 to delete the message.",
                validMessage.sentMessage(2));
    }

    @Test
    public void testStoreMessageOption() {

        assertEquals("Message successfully stored.",
                validMessage.sentMessage(3));
    }
}
