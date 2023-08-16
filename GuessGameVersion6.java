/* 
   Programmer: Elijah Yakimyuk
   Class: CS& 145 with Jeremiah Ramsey
   Date: 6/29/2023
   Assignment: Lab 1: Guessing Game
   Purpose: Create a game for the user to guess a random number between 1 and 100
   Resources: "CS141 Lab 4: Guessing game"
*/

import java.util.Random;
import java.util.Scanner;

public class GuessGameVersion6 {

    // Main method: Entry point of the program
    public static void main(String[] args) {
        giveIntro(); // Display the game introduction
        Scanner console = new Scanner(System.in); // Create a scanner for user input
        boolean playAgain = true;

        while (playAgain) {
            int gamesPlayed = 0, totalGuesses = 0, bestGame = Integer.MAX_VALUE;

            while (true) {
                gamesPlayed++;

                Random rand = new Random(); // Generate a random number from 0 to 99
                int number = rand.nextInt(100);

                int guess = getGuess(console); // Get the user's first guess
                int numGuesses = 1;

                while (guess != number) { // Provide hints until the correct guess is reached
                    int numMatches = matches(number, guess);

                    if (guess > number) {
                        System.out.println("| Incorrect:    The number is lower                           |");
                    } else if (guess < number) {
                        System.out.println("|                                                             |");
                        System.out.println("| Incorrect:    The number is higher                          |");
                    }
                    System.out.println("|                                       (hint: " + numMatches + " digits match)|");
                    System.out.println("|_____________________________________________________________|");
                    guess = getGuess(console);
                    numGuesses++;
                }

                displayCorrectGuess(numGuesses);

                totalGuesses += numGuesses;
                if (numGuesses < bestGame) {
                    bestGame = numGuesses;
                }
                displayGameStats(gamesPlayed, totalGuesses, bestGame);

                System.out.print("| Do you want to play again? (y/n): ");
                String playAgainInput = console.next();
                if (!playAgainInput.equalsIgnoreCase("y")) {
                    break;
                }
            }
            System.out.print("| Do you want to start a new game? (y/n): ");
            String newGameInput = console.next();
            playAgain = newGameInput.equalsIgnoreCase("y");
        }
        System.out.println("| Thank you for playing!                                      |");
        System.out.println("|_____________________________________________________________|");
    }

    // Display the game introduction
    public static void giveIntro() {
        System.out.println("|=============================================================|");
        System.out.println("|                 Welcome to the Guessing Game                |");
        System.out.println("|-------------------------------------------------------------|");
        System.out.println("| I will think of a number between 1 and 100 and will allow   |\n" +
                           "| you to guess until you get it. For each guess, I will tell  |\n" +
                           "| you whether the right answer is higher or lower than yours  |");
        System.out.println("|_____________________________________________________________|");
    }

    // Get the user's guess, ensuring it's within the valid range
    public static int getGuess(Scanner console) {
        int guess = getInt(console, "| Your guess? ");
        while (guess < 0 || guess >= 100) {
            System.out.println("| Out of range; try again.");
            guess = getInt(console, "| Your guess? ");
        }
        return guess;
    }

    // Get an integer input from the user, handling non-integer inputs
    public static int getInt(Scanner console, String prompt) {
        System.out.print(prompt);
        while (!console.hasNextInt()) {
            console.next();
            System.out.println("| Not an integer; try again.");
            System.out.print(prompt);
        }
        return console.nextInt();
    }

    // Calculate the number of matching digits between the guess and the actual number
    public static int matches(int number, int guess) {
        int numMatches = 0;
        if (guess / 10 == number / 10 || guess / 10 == number % 10) {
            numMatches++;
        }
        if (guess / 10 != guess % 10 && (guess % 10 == number / 10 || guess % 10 == number % 10)) {
            numMatches++;
        }
        return numMatches;
    }

    // Display hints to the user about the comparison between their guess and the actual number
    public static void displayHint(int numMatches, int number, int guess) {
        if (guess > number) {
            System.out.println("| Incorrect:    The number is lower                           |");
        } else if (guess < number) {
            System.out.println("|                                                             |");
            System.out.println("| Incorrect:    The number is higher                          |");
        }
        System.out.println("|                                       (hint: " + numMatches + " digits match)|");
        System.out.println("|_____________________________________________________________|");
    }

    // Display a message indicating the correct guess and the number of tries
    public static void displayCorrectGuess(int numGuesses) {
        System.out.println("|                                                             |");
        System.out.println("| Correct!!                                                   |");
        System.out.println("|                 You got it right in " + numGuesses + " tries                 |");
        System.out.println("|_____________________________________________________________|");
    }

    // Display statistics about the game's progress
    public static void displayGameStats(int gamesPlayed, int totalGuesses, int bestGame) {
        System.out.println("|=============================================================|");
        System.out.println("|                         Game Stats                          |");
        System.out.println("|_____________________________________________________________|");
        System.out.println("| Games played: " + gamesPlayed);
        System.out.println("| Total guesses: " + totalGuesses);
        System.out.println("|_____________________________________________________________|");
        System.out.println("| Guesses per game range: " + (double) totalGuesses / gamesPlayed);
        System.out.println("| Average guesses per game: " + (double) totalGuesses / gamesPlayed);
        System.out.println("| Best game: " + bestGame);
        System.out.println("|_____________________________________________________________|");
    }
}
