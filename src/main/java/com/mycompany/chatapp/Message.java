/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.Random;

/**
 *
 * @author Ruan
 */
public class Message {

    // Stores the recipient cellphone number.
    private String recipient;

    // Stores the actual text of the message.
    private String messageText;

    // Stores the automatically generated message ID.
    private String messageID;

    // Stores the message hash.
    private String messageHash;

    // Stores the message number.
    private int messageNumber;

    /**
     * Constructor used to create new Message object.
     *
     * @param recipient The recipient cellphone number.
     * @param messageText The message content.
     * @param messageNumber The message number.
     */
    public Message(String recipient, String messageText, int messageNumber) {

        this.recipient = recipient;
        this.messageText = messageText;
        this.messageNumber = messageNumber;
    }

    /**
     * Validates the recipient cellphone number. The number must start with +27
     * and contain 12 characters.
     *
     * @return True if the number is valid, otherwise false.
     */
    public boolean checkRecipientCell() {

        return recipient.matches("^\\+27\\d{9}$");

    }

    /**
     * Checks whether the message contains less than 250 characters.
     *
     * @return Validation message for the message length.
     */
    public String checkMessageLength() {

        if (messageText.length() <= 250) {
            return "Message ready to send.";

        } else {

            int excessCharacters = messageText.length() - 250;

            return "Message exceeds 250 characters by "
                    + excessCharacters + ", please reduce size.";
        }
    }

    /**
     * Automatically generates a random 10-digit message ID.
     *
     * @return The generated message ID.
     */
    public String generateMessageID() {

        Random random = new Random();

        StringBuilder idBuilder = new StringBuilder();

        for (int i = 0; i < 10; i++) {

            idBuilder.append(random.nextInt(10));

        }
        messageID = idBuilder.toString();

        return messageID;
    }

    /**
     * Checks whether the generated message ID contains 10 digits.
     *
     * @return True if the message ID is valid, otherwise false.
     */
    public boolean checkMessageID() {

        return messageID != null && messageID.length() == 10;

    }
}
