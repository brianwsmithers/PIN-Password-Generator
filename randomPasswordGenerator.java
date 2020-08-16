import java.security.SecureRandom;
import java.util.InputMismatchException;
import java.util.Scanner;

class randomPasswordGenerator{

    public static void main(String []args){

        choicePrompt(); // Prompt to choose pin or password.
        menuMethod(); // Method to catch user input if not (1) PIN, (2) Password or (3) Exit.

    }

    static void choicePrompt() {
        System.out.printf("Password Generator:%n");
        System.out.printf("1 - Create PIN%n");
        System.out.printf("2 - Create Password%n");
        System.out.printf("3 - EXIT%n");
    }

    // Method accepts (1), (2), (3) or handles any input exception.
    static void menuMethod() {
        Scanner obj = new Scanner(System.in); // Creates object for user input.
        int input = obj.nextInt();  // Initialize integer input value.
        switch (input) {
            case 1:
            case 2:
            case 3:
                inputMethod(input);
                break;
            default: defaultCase();
        }
    }

    // Method for default case returns user to main method after printing incorrect input message.
    static void defaultCase() {
        invalidValue();
        menuMethod();
    }

    // Method for menuMethod()
    static void invalidValuePrompt() {
        System.out.printf("Please enter: %n1 - Create PIN%n2 - Create Password%n3 - EXIT%n");
    }

    // Method to instruct input for pin.
    static void pinInputInstructions() {
        System.out.println("Enter pin length: (4-32)");
    }
    
    // Method takes users input and starts pinGenerator(), passwordGenerator() or EXITS program.
    static void inputMethod(int input) { // If statement selects PIN, Password or EXIT option according to input.
        Scanner obj = new Scanner(System.in); // Creates object for user input.
        switch (input) {
            case 1: // PIN Generator
                pinSwitchCase(obj, input); // Takes pin length and sends to pinGenerator method.
                break;
            case 2: // Password Generator
                passwordSwitchCase(obj, input);
                break;
            default: System.out.println("Program closing");
        }
    }

    // Method accepts obj and input to create pin length.
    static void pinSwitchCase(Scanner obj, int input) {
        int pinLength = 0;
        pinInputInstructions();
        try {
            pinLength = obj.nextInt();
            while (pinLength < 4 || pinLength > 32) {
                System.out.printf("Please enter: (4-32)%n");
                pinLength = obj.nextInt();
            }
        }  catch (InputMismatchException e) { // If input is not an integer.
            // Skip to finally block
        } finally { // Executes if user does not enter valid password length.
            if (pinLength < 4 || pinLength > 32) {
                inputMethod(input); // Recursive call to method.
            }
        }
        pinGenerator(pinLength); // PIN generates and is printed.
    }

    // Method accepts obj and input to create password length.
    static void passwordSwitchCase(Scanner obj, int input) {
        int passwordLength = 0;
        System.out.printf("Enter password length: (6-64)%n");
        try {
            passwordLength = obj.nextInt();
            while (passwordLength < 4 || passwordLength > 64) {
                invalidValuePrompt();
                System.out.printf("Please enter: (4-64)%n");
                passwordLength = obj.nextInt();
            }
        } catch (InputMismatchException e) {
            // Skip to finally block
        } finally {
            if (passwordLength < 4 || passwordLength > 64) {
                invalidValuePrompt();
                inputMethod(input); // Recursive call to method.
            }
        }
        passwordGenerator(passwordLength); // Password generates and is printed.
    }

    // Method used during try-catch blocks when user inputs a non-integer value.
    static void invalidValue() {
        System.out.printf("Invalid value.%n");
    }

    // Method generates pin from an array. passwordLength is determined in previous
    // method by user.
    static void pinGenerator(int passwordLength) {
        SecureRandom random = new SecureRandom();
        int[] numericalArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};

        // Array prints random index with SecureRandom Class.
        for (int i = 0; i < passwordLength; i++) {
            int randomNum = random.nextInt(numericalArray.length);
            System.out.print(numericalArray[randomNum]);
        }
    }

    // Method generates a random password using SecureRandom class by utilizing four arrays with alphabetical
    // characters, numerical values and special characters.
    static void passwordGenerator(int passwordLength) {

        SecureRandom random = new SecureRandom();

        String[] lowerCaseLetterArray = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n","o",
                "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        int[] numericalArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        String[] upperCaseLetterArray = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
                "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String[] specialCharacterArray = {"!", "@", "#", "%", "&", "=", "?", "-"};

        // For loop generates password from array using switch class.
        for (int i = 0; i < passwordLength; i++) { // passwordLength equals users input.
            int valueChoice = 1 + random.nextInt(4); // Bounds account for all arrays in switch case.
            switch(valueChoice) {
                case 1: // Random lowercase letter is chosen from array.
                    int randomLowerCaseLetter = random.nextInt(lowerCaseLetterArray.length);
                    System.out.print(lowerCaseLetterArray[randomLowerCaseLetter]);
                    break; // Restarts loop and generates next value.
                case 2: // Random numerical value (0-9) is chosen from array.
                    int randomNum = random.nextInt(numericalArray.length);
                    System.out.print(numericalArray[randomNum]);
                    break;
                case 3: // Random uppercase letter is chosen from array.
                    int randomUpperCaseLetter = random.nextInt(upperCaseLetterArray.length);
                    System.out.print(upperCaseLetterArray[randomUpperCaseLetter]);
                    break;
                case 4: // Random special character is chosen from array.
                    int randomSymbols = random.nextInt(specialCharacterArray.length);
                    System.out.print(specialCharacterArray[randomSymbols]);
                    break;
            }
        }
    }
}
