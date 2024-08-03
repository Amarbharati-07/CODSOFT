import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    private static final int MAX_ATTEMPTS = 5; 
    private static final int MIN_NUMBER = 1; 
    private static final int MAX_NUMBER = 100; 
    private static final int CLOSE_RANGE = 10; 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        Random random = new Random();
        boolean playAgain = true;
        int totalRounds = 0;
        int totalWins = 0;

        while (playAgain) {
            totalRounds++;
            int numberToGuess = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER; 
            boolean guessedCorrectly = false;
            int attemptsLeft = MAX_ATTEMPTS;

            System.out.println("Guess the number between " + MIN_NUMBER + " and " + MAX_NUMBER + ". You have " + MAX_ATTEMPTS + " attempts.");

            while (attemptsLeft > 0 && !guessedCorrectly) {
                int userGuess = getValidGuess(scanner);

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You've guessed the number correctly.");
                    guessedCorrectly = true;
                } else {
                    provideHint(userGuess, numberToGuess);
                }

                attemptsLeft--;
                if (attemptsLeft > 0 && !guessedCorrectly) {
                    System.out.println("Attempts left: " + attemptsLeft);
                } else if (!guessedCorrectly) {
                    System.out.println("Sorry, you've used all your attempts. The number was " + numberToGuess + ".");
                }
            }

            if (guessedCorrectly) {
                totalWins++;
            }

            System.out.println("Your score: " + totalWins + " win(s) out of " + totalRounds + " round(s).");

            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        }

        System.out.println("Thank you for playing! Final score: " + totalWins + " win(s) out of " + totalRounds + " round(s).");
        scanner.close();
    }

    private static int getValidGuess(Scanner scanner) {
        int guess;
        while (true) {
            System.out.print("Enter your guess: ");
            if (scanner.hasNextInt()) {
                guess = scanner.nextInt();
                if (guess >= MIN_NUMBER && guess <= MAX_NUMBER) {
                    return guess;
                } else {
                    System.out.println("Please enter a number between " + MIN_NUMBER + " and " + MAX_NUMBER + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); 
            }
        }
    }

    private static void provideHint(int userGuess, int numberToGuess) {
        int difference = Math.abs(userGuess - numberToGuess);
        
        if (difference <= CLOSE_RANGE) {
            System.out.println("You're very close! " + (userGuess < numberToGuess ? "Try guessing a little higher." : "Try guessing a little lower."));
        } else if (userGuess < numberToGuess) {
            System.out.println("Your guess is too low. Try guessing a higher number.");
        } else {
            System.out.println("Your guess is too high. Try guessing a lower number.");
        }

        // Provide additional guidance based on how close the guess is
        if (difference > CLOSE_RANGE && difference <= CLOSE_RANGE * 2) {
            System.out.println("You're somewhat close. " + (userGuess < numberToGuess ? "Try guessing a bit higher." : "Try guessing a bit lower."));
        }
    }
}
