/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author Ruan
 */
public class LoginTest {

    @Test
    public void testUsernameCorrectlyFormatted() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean success = user.loginUser("kyl_1", "Ch&&sec@ke99!");
        assertEquals("Welcome Kyle ,Smith it is great to see you.", user.returnLoginStatus(success));
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        Login user = new Login("Kyle", "Smith", "kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", user.registerUser());
    }

    @Test
    public void testPasswordMeetsComplexityRequirements() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("Username successfully captured.\nPassword successfully captured.\nCell number successfully captured.", user.registerUser());
    }

    @Test
    public void testPasswordDoesNotMeetComplexityRequirements() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "password", "+27838968976");
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", user.registerUser());
    }

    @Test
    public void testCellPhoneCorrectlyFormatted() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("Username successfully captured.\nPassword successfully captured.\nCell number successfully captured.", user.registerUser());
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "08966553");
        assertEquals("Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.", user.registerUser());
    }

    @Test
    public void testLoginSuccessful() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(user.loginUser("wrong", "wrong"));
    }

    @Test
    public void testUsernameCorrectlyFormattedBoolean() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.checkUserName());
    }

    @Test
    public void testUsernameIncorrectlyFormattedBoolean() {
        Login user = new Login("Kyle", "Smith", "kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(user.checkUserName());
    }

    @Test
    public void testPasswordMeetsComplexityBoolean() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.checkPasswordComplexity());
    }

    @Test
    public void testPasswordDoesNotMeetComplexityBoolean() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "password", "+27838968976");
        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void testCellPhoneCorrectlyFormattedBoolean() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.checkCellPhoneNumber());
    }

    @Test
    public void testCellPhoneIncorrectlyFormattedBoolean() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "08966553");
        assertFalse(user.checkCellPhoneNumber());
    }
}
