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
        int input = 0;  // Initialize integer input value.
        while (input != 1 && input != 2 && input != 3) { // User must choose these options to progress.
            Scanner obj = new Scanner(System.in); // Creates object for user input.
            try { // Try-catch block to catch incorrect input value...i.e. not an integer.
                input = obj.nextInt(); // 1 - PIN // 2 - Password // 3 - EXIT.
                if (input == 1 || input == 2 || input == 3) {
                    inputMethod(input); // Successful input
                }
                else {
                    invalidValuePrompt(); // Remind user to select correct value.
                    menuMethod(); // Recursive call gives user option to create PIN, Password or EXIT.
                }
            } catch (InputMismatchException e) { // If input is not an integer.
                // Skip to finally block
            } finally {
                invalidValuePrompt(); // Remind user to select correct value.
                menuMethod(); // Recursive call gives user option to create PIN, Password or EXIT.
            }
        }
    }

    // Method for menuMethod()
    static void invalidValuePrompt() {
        System.out.printf("Please enter: %n1 - Create PIN%n2 - Create Password%n3 - EXIT%n");
    }

    // Method takes users input and starts pinGenerator(), passwordGenerator() or EXITS program.
    static void inputMethod(int input) { // If statement selects PIN, Password or EXIT option according to input.
        Scanner obj = new Scanner(System.in); // Creates object for user input.
        if (input == 1) { // PIN generator
            int pinLength = 0;
            System.out.printf("Enter pin length: (4 - 32)%n");
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
                    invalidValuePrompt();
                    inputMethod(input); // Recursive call to method.
                }
            }
            pinGenerator(pinLength); // PIN generates and is printed.
            System.exit(0); // Program is finished.
        }
        else if (input == 2) { // Password generator
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
            System.exit(0); // Program exits.
        }
        else if (input == 3) { // EXIT program.
            System.exit(0);
        }
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
