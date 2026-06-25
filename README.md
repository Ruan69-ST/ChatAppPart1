# ChatAppPart1

## References

GitHub Docs. (2024) Building and testing Java with Maven. Available at: https://docs.github.com/en/actions/tutorials/build-and-test-code/java-with-maven

Goyvaerts, J. (2024) Lookahead and Lookbehind Zero-Length Assertions. Available at: https://www.regular-expressions.info/lookaround.html

Oracle. (2024) FileWriter (Java Platform SE Documentation). Available at: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/io/FileWriter.html

Oracle. (2024) IOException (Java Platform SE Documentation). Available at: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/io/IOException.html

Oracle. (2024) Pattern (Java Platform SE 8 Documentation). Available at: https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html

Oracle. (2024) Scanner (Java Platform SE 8 Documentation). Available at: https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html


## Part 2 Features

The functionality of part 2 includes:

- Login required before using QuickChat.
- Welcome message displayed after successful login.
- Numeric menu with the following options:
  - 1) Send messages
  - 2) Show recently sent messages
  - 3) Quit
- Message limit set by the user.
- Message ID generation.
- Message hash generation.
- Recipient cellphone number validation.
- Message length validation.
- Sent message details displayed in the required order.
- Stored messages written to a JSON file.
- Unit tests for the Message class.
- GitHub Actions workflow for automated Maven tests.

## How to Run the Application

Open the project in NetBeans and run `ChatAppPart1.java`.

The program will ask the user to register, log in, and then use the QuickChat menu.

## How to Run the Tests

The project uses JUnit 5 tests.

In NetBeans, right-click the project and select **Test**.

The tests can also be run with Maven by using the following command:

mvn test

## GitHub Actions

A GitHub Actions workflow has been added in .github/workflows/TestJava.yml.

The workflow runs Maven tests automatically when new code is pushed to GitHub.

#Part 3

- Implemented message tracking arrays.
- Added functionality to search and delete messages.
- finalized the logic for saving message history to JSON file.
- Unit tests using specified data
