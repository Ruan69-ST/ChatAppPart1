/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author Ruan
 */
public class Login {
    
    private String username;
    private String password;
    private String cellPhone;
    private String firstName;
    private String lastName;
    
public Login(String firstName, String lastName, String username, String password, String cellPhone) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
    this.cellPhone = cellPhone;
}
public boolean checkUserName() {
    return username.contains("_") && username.length() <=5;
}
//Validates password with regex: must be more than 8 characters, include a capital letter, number, and a special character.
public boolean checkPasswordComplexity() {
    String regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,}$";
    return java.util.regex.Pattern.matches(regex, password);
}
}
