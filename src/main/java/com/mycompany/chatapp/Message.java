/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.Random;
import java.util.ArrayList;

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

    // Stores all messages that are successfully sent while the program is running.
    private static ArrayList<String> sentMessages = new ArrayList<>();

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
     * @return Validation message for the recipient cellphone number.
     */
    public String checkRecipientCell() {

        if (recipient.matches("^\\+27\\d{9}$")) {

            return "Cell phone number successfully captured.";

        } else {

            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }

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
                    + excessCharacters + "; please reduce the size.";
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

    /**
     * Creates the required message hash using the first two digits of the
     * message ID, the message number, and the first and last words of the
     * message text.
     *
     * @return The generated message hash in uppercase.
     */
    public String createMessageHash() {

        if (messageID == null) {

            generateMessageID();

        }

        // Splits the message into separate words.
        String[] words = messageText.trim().split("\\s+");

        // Stores the first word of the message and removes punctuation.
        String firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "");

        // Stores the last word of the message and removes punctuation.
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "");

        // Creates the message hash in the required format.
        messageHash = messageID.substring(0, 2)
                + ":" + messageNumber
                + ":" + firstWord + lastWord;

        return messageHash.toUpperCase();
    }

    /**
     * Returns the correct message action based on the user's choice.
     *
     * @param choice The option selected by the user.
     * @return The result of the selected message action.
     */
    public String sentMessage(int choice) {

        switch (choice) {
            case 1:
                return "Message successfully sent.";

            case 2:
                return "Press 0 to delete the message.";

            case 3:
                return "Message successfully stored.";

            default:
                return "Invalid option selected.";
        }
    }

    /**
     * Adds the current message details to the sent messages list. This is only
     * used when the user chooses to send the message.
     */
    public void addSentMessage() {

        if (messageID == null) {
            generateMessageID();
        }

        if (messageHash == null) {
            createMessageHash();
        }

        String messageDetails = "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText;

        sentMessages.add(messageDetails);

    }

    /**
     * Returns all messages that were sent while the program is running.
     *
     * @return A formatted list of sent messages
     */
    public String printMessages() {

        if (sentMessages.isEmpty()) {

            return "No messages sent.";

        }

        String allMessages = "";

        for (String message : sentMessages) {

            allMessages += message + "\n\n";

        }

        return allMessages;

    }

    /**
     * Returns the total number of messages sent while the program is running.
     *
     * @return The number of sent messages.
     */
    public int returnTotalMessages() {

        return sentMessages.size();
    }
}
