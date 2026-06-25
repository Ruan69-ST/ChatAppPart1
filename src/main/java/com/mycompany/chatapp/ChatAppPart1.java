/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.chatapp;

import java.util.Scanner;

/**
 *
 * @author Ruan
 */
public class ChatAppPart1 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter last name: ");
        String lastName = input.nextLine();

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        System.out.print("Enter cell phone number: ");
        String cellPhone = input.nextLine();

        Login user = new Login(firstName, lastName, username, password, cellPhone);

        System.out.println(user.registerUser());

        System.out.print("Enter username to login: ");
        String enteredUsername = input.nextLine();

        System.out.print("Enter password to login: ");
        String enteredPassword = input.nextLine();

        boolean loginSuccess = user.loginUser(enteredUsername, enteredPassword);

        System.out.println(user.returnLoginStatus(loginSuccess));

        if (loginSuccess) {

            System.out.println("Welcome to QuickChat.");

            System.out.print("How many messages would you like to enter? ");
            int totalMessages = input.nextInt();
            input.nextLine();

            int menuChoice = 0;
            int messagesEntered = 0;

            while (menuChoice != 6) {

                System.out.println("\nPlease select an option:");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Search stored messages");
                System.out.println("4) Stored Messages");
                System.out.println("5) Show disregarded messages report");
                System.out.println("6) Quit");
                System.out.print("Enter your choice: ");

                menuChoice = input.nextInt();
                input.nextLine();

                switch (menuChoice) {

                    case 1:

                        if (messagesEntered >= totalMessages) {
                            System.out.println("You have already entered the maximum number of messages.");
                            break;
                        }

                        while (messagesEntered < totalMessages) {

                            System.out.println("\nMessage " + (messagesEntered + 1) + " of " + totalMessages);

                            System.out.print("Enter recipient cell phone number: ");
                            String recipient = input.nextLine();

                            System.out.print("Enter message: ");
                            String messageText = input.nextLine();

                            Message message = new Message(recipient, messageText, messagesEntered);

                            message.generateMessageID();
                            message.createMessageHash();

                            System.out.println(message.checkRecipientCell());
                            System.out.println(message.checkMessageLength());

                            System.out.println("\nChoose what you want to do with this message:");
                            System.out.println("1) Send Message");
                            System.out.println("2) Disregard Message");
                            System.out.println("3) Store Message to send later");
                            System.out.print("Enter your choice: ");

                            int messageChoice = input.nextInt();
                            input.nextLine();

                            System.out.println(message.sentMessage(messageChoice));

                            if (messageChoice == 1) {

                                message.addSentMessage();

                                System.out.println("\nMessage Details:");
                                System.out.println(message.getMessageDetails());
                            } else if (messageChoice == 3) {
                                System.out.println("\nStored Message Details:");
                                System.out.println(message.getMessageDetails());
                            }

                            messagesEntered++;
                        }

                        Message messageReport = new Message("", "", 0);
                        System.out.println("\nTotal messages sent: " + messageReport.returnTotalMessages());

                        break;

                    case 2:
                        Message recentReport = new Message("", "", 0);
                        System.out.println("\n--- RECENTLY SENT MESSAGES ---");
                        System.out.println(recentReport.printMessages());
                        break;

                    case 3:
                        System.out.print("\nEnter Message ID or Message Hash to search: ");
                        String searchKey = input.nextLine();
                        System.out.println(Message.searchStoredMessages(searchKey));
                        break;

                    case 4:
                        System.out.println("\n--- STORED MESSAGES MENU ---");
                        System.out.println("1. Display senders and recipients");
                        System.out.println("2. Display longest stored message");
                        System.out.println("3. Search by Recipient");
                        System.out.println("4. Delete message by Hash");
                        System.out.println("5. Display full system report");
                        System.out.print("Enter choice: ");
                        int sub = input.nextInt();
                        input.nextLine();

                        switch (sub) {
                            case 1:
                                System.out.println(Message.displaySendersAndRecipients(username));
                                break;
                            case 2:
                                System.out.println(Message.getLongestStoredMessage());
                                break;
                            case 3:
                                System.out.print("Enter recipient number: ");
                                System.out.println(Message.searchByRecipient(input.nextLine()));
                                break;
                            case 4:
                                System.out.print("Enter hash to delete: ");
                                System.out.println(Message.deleteMessageByHash(input.nextLine()));
                                break;
                            case 5:
                                System.out.println(Message.displayFullReport(username));
                                break;
                            default:
                                System.out.println("Invalid.");
                        }
                        break;

                    case 5:
                        System.out.println(Message.printDisregardedMessages());
                        break;

                    case 6:
                        System.out.println("Goodbye.");
                        break;

                    default:
                        System.out.println("Invalid option selected.");
                        break;
                }
            }

        } else {

            System.out.println("You must be logged in to use QuickChat.");
        }

        input.close();
    }
}
