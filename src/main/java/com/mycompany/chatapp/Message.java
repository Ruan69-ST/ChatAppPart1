/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

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
}
