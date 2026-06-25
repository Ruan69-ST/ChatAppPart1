/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.Random;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

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

    // Stores all messages that are saved to the JSON file while the program is running.
    private static ArrayList<Message> storedMessages = new ArrayList<>();

    // Part 3: New dynamic tracking arrays
    // Tracks all messages that were flagged as "disregarded"
    private static ArrayList<String> disregardedMessagesArray = new ArrayList<>();

    // Parrallel arrays for part 3 searching & features
    private static ArrayList<String> messageIDArray = new ArrayList<>();
    private static ArrayList<String> messageHashArray = new ArrayList<>();
    private static ArrayList<String> recipientArray = new ArrayList<>();
    private static ArrayList<String> storedMessagesTextArray = new ArrayList<>();

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
     * Validates the recipient cellphone number. The number must start with +27 and contain 12 characters.
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
                //Ensure ID and hash are generated before tracking the disregarded message
                if (messageID == null) {
                    generateMessageID();
                }
                if (messageHash == null) {
                    createMessageHash();
                }

                //Track this message as disregarded
                disregardedMessagesArray.add(getMessageDetails());

                return "Press 0 to delete the message.";

            case 3:
                return storeMessage();

            default:
                return "Invalid option selected.";
        }
    }

    /**
     * Adds the current message details to the sent messages list. This is only
     * used when the user chooses to send the message.
     */
    public void addSentMessage() {

        sentMessages.add(getMessageDetails());

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

    /**
     * Stores the current message in JSON file. The JSON file is rewritten each
     * time so that it remains a valid JSON.
     *
     * Code attribution: FileWriter and IOException were used based on the
     * official Oracle Java documentation for writing text to files and handling
     * file errors.
     *
     * @return Message confirming if the message was stored successfully.
     */
    public String storeMessage() {

        if (messageID == null) {
            generateMessageID();
        }

        if (messageHash == null) {
            createMessageHash();
        }

        storedMessages.add(this);

        // Populate parallel arrays for part 3 tracking
        messageIDArray.add(this.messageID);
        messageHashArray.add(this.messageHash);
        recipientArray.add(this.recipient);
        storedMessagesTextArray.add(this.messageText);

        try (FileWriter writer = new FileWriter("stored_messages.json")) {

            writer.write("[\n");

            for (int i = 0; i < storedMessages.size(); i++) {

                Message storedMessage = storedMessages.get(i);

                writer.write("  {\n");
                writer.write("    \"messageID\": \"" + escapeJson(storedMessage.getMessageID()) + "\",\n");
                writer.write("    \"messageHash\": \"" + escapeJson(storedMessage.getMessageHash()) + "\",\n");
                writer.write("    \"recipient\": \"" + escapeJson(storedMessage.getRecipient()) + "\",\n");
                writer.write("    \"message\": \"" + escapeJson(storedMessage.getMessageText()) + "\"\n");
                writer.write("  }");

                if (i < storedMessages.size() - 1) {
                    writer.write(",");
                }

                writer.write("\n");
            }

            writer.write("]\n");

            return "Message successfully stored.";

        } catch (IOException e) {

            return "Error storing message.";
        }
    }

    /**
     * Escapes special characters so that message text can be safely written to
     * JSON.
     *
     * @param value The text value that must be written to JSON.
     * @return The escaped JSON text.
     */
    private static String escapeJson(String value) {
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    /**
     * Returns the message ID.
     *
     * @return The message ID.
     */
    public String getMessageID() {

        return messageID;

    }

    /**
     * Returns the message hash.
     *
     * @return The message hash.
     */
    public String getMessageHash() {

        return messageHash;

    }

    /**
     * Returns the recipient cellphone number.
     *
     * @return The recipient cellphone number.
     */
    public String getRecipient() {

        return recipient;

    }

    /**
     * Returns the message text.
     *
     * @return The message text.
     */
    public String getMessageText() {

        return messageText;

    }

    /**
     * Allows the message ID to be set manually for testing.
     *
     * @param messageID The message ID to use.
     */
    public void setMessageID(String messageID) {

        this.messageID = messageID;

    }

    /**
     * Returns the full message details in the required display order.
     *
     * @return Message ID, message hash, recipient, and message text.
     */
    public String getMessageDetails() {

        if (messageID == null) {
            generateMessageID();
        }

        if (messageHash == null) {
            createMessageHash();
        }

        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText;
    }

    // Part 3 search and reporting methods
    /**
     * Requirement (c): Search for a message ID and display the corresponding
     * recipient and message.
     *
     *  * @param searchKey The message ID or message hash to look for.
     * @return The formatted details of the message if found, otherwise a not
     * found error.
     */
    public static String searchStoredMessages(String searchKey) {
        for (int i = 0; i < messageIDArray.size(); i++) {
            // Check if the search key matches either the ID or the Hash at the current index
            if (messageIDArray.get(i).equalsIgnoreCase(searchKey) || messageHashArray.get(i).equalsIgnoreCase(searchKey)) {

                return "--- MESSAGE RETRIEVED (INDEX " + i + ") ---\n"
                        + "Message ID: " + messageIDArray.get(i) + "\n"
                        + "Message Hash: " + messageHashArray.get(i) + "\n"
                        + "Recipient: " + recipientArray.get(i) + "\n"
                        + "Message: " + storedMessagesTextArray.get(i);
            }
        }
        return "Search complete. Message with ID/Hash '" + searchKey + "' could not be found.";
    }

    /**
     * Compiles and returns a report of all messages flagged as disregarded.
     *
     * * @return A formatted list of disregarded messages.
     */
    public static String printDisregardedMessages() {
        if (disregardedMessagesArray.isEmpty()) {
            return "No messages have been disregarded during this session.";
        }

        String report = "--- DISREGARDED MESSAGES REPORT ---\n\n";
        for (String messageDetails : disregardedMessagesArray) {
            report += messageDetails + "\n-----------------------------------\n";
        }
        return report;
    }

    /**
     * Requirement (a): Displays the sender and recipient of all stored
     * messages.
     */
    public static String displaySendersAndRecipients(String currentUserName) {
        if (recipientArray.isEmpty()) {
            return "No stored messages found.";
        }

        StringBuilder output = new StringBuilder("--- STORED CONTACTS ---\n");
        for (int i = 0; i < recipientArray.size(); i++) {
            output.append("From: ").append(currentUserName)
                    .append(" -> To: ").append(recipientArray.get(i)).append("\n");
        }
        return output.toString();
    }

    /**
     * Requirement (b): Locates and returns the longest stored message text.
     */
    public static String getLongestStoredMessage() {
        if (storedMessagesTextArray.isEmpty()) {
            return "No messages stored.";
        }

        int longestIndex = 0;
        for (int i = 1; i < storedMessagesTextArray.size(); i++) {
            if (storedMessagesTextArray.get(i).length() > storedMessagesTextArray.get(longestIndex).length()) {
                longestIndex = i;
            }
        }
        return storedMessagesTextArray.get(longestIndex);
    }

    /**
     * Requirement (d): Gathers all messages bound for a specific recipient.
     */
    public static String searchByRecipient(String recipientNum) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < recipientArray.size(); i++) {
            if (recipientArray.get(i).equals(recipientNum)) {
                output.append(storedMessagesTextArray.get(i)).append(" ");
            }
        }
        return output.toString().trim();
    }

    /**
     * Requirement (e): Delete message using message hash.
     */
    public static String deleteMessageByHash(String hash) {
        for (int i = 0; i < messageHashArray.size(); i++) {
            if (messageHashArray.get(i).equalsIgnoreCase(hash)) {
                String deletedText = storedMessagesTextArray.get(i);
                messageIDArray.remove(i);
                messageHashArray.remove(i);
                recipientArray.remove(i);
                storedMessagesTextArray.remove(i);
                storedMessages.remove(i);

                saveToJSON();

                return "Message: \"" + deletedText + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

    /**
     * Requirement (f): Displays a full system report.
     *
     * * @param currentUserName The name of the user currently logged in.
     * @return A formatted report of all stored messages.
     */
    public static String displayFullReport(String currentUserName) {
        if (messageIDArray.isEmpty()) {
            return "No messages stored in the system.";
        }

        StringBuilder output = new StringBuilder("--- FULL STORAGE REPORT ---\n");
        for (int i = 0; i < messageIDArray.size(); i++) {
            output.append("Hash: ").append(messageHashArray.get(i))
                    .append(" | Recipient: ").append(recipientArray.get(i))
                    .append("\nMessage: ").append(storedMessagesTextArray.get(i)).append("\n\n");
        }
        return output.toString();
    }

    private static void saveToJSON() {
        try (FileWriter writer = new FileWriter("stored_messages.json")) {
            writer.write("[\n");
            for (int i = 0; i < storedMessages.size(); i++) {
                Message m = storedMessages.get(i);
                writer.write("  {\n");
                writer.write("    \"messageID\": \"" + escapeJson(m.getMessageID()) + "\",\n");
                writer.write("    \"messageHash\": \"" + escapeJson(m.getMessageHash()) + "\",\n");
                writer.write("    \"recipient\": \"" + escapeJson(m.getRecipient()) + "\",\n");
                writer.write("    \"message\": \"" + escapeJson(m.getMessageText()) + "\"\n");
                writer.write("  }" + (i < storedMessages.size() - 1 ? "," : "") + "\n");
            }
            writer.write("]");
        } catch (IOException e) {
            System.out.println("Error saving to JSON.");
        }
    }
}
