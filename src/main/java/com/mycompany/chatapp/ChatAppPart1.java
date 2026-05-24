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

            while (menuChoice != 3) {

                System.out.println("\nPlease select an option:");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Enter your choice: ");

                menuChoice = input.nextInt();
                input.nextLine();

                switch (menuChoice) {

                    case 1:
                        System.out.println("Send Messages selected.");
                        System.out.println("You may enter up to " + totalMessages + " messages.");
                        break;

                    case 2:
                        System.out.println("Coming Soon.");
                        break;

                    case 3:
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
