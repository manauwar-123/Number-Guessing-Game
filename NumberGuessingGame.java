import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
    int min = 1;
    int max = 100;
    int attempts = 5; // the maximum number of attempts allowed
    int score = 0; // the initial score
    int hintsRemaining = 1; // the number of hints the user has remaining
    Random random = new Random();
    int number = random.nextInt(max - min + 1) + min;
    Scanner scanner = new Scanner(System.in);

    System.out.println("Guess the number between " + min + " and " + max);
    for (int round = 1; round <= 3; round++) { // play 3 rounds
        System.out.printf("Round %d\n", round);
        // repeat until the user guesses the number or runs out of attempts
        while (attempts > 0) {
            System.out.printf("Attempts left: %d\nScore: %d\nHints remaining: %d\n", attempts, score, hintsRemaining);
            System.out.print("Enter your guess: ");
            int guess;
            try {
                guess = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // consume the invalid input
                continue;
            }
            if (guess == number) {
                score += attempts; // add the remaining attempts to the score
                System.out.printf("Congratulations! You guessed the number.\nScore: %d\n", score);
                break;
            } else if (guess < number) {
                System.out.println("Too low. Try again.");
            } else {
                System.out.println("Too high. Try again.");
            }
            attempts--;
            if (attempts == 2 && hintsRemaining > 0) { // allow hints only in first 2 rounds
                System.out.println("You have a hint available. Would you like to use it? (y/n)");
                String answer = scanner.next().toLowerCase();
                if (answer.equals("y")) {
                    System.out.println("Solve the following equation to get the number: 2x + 5 = " + (2 * number + 5));
                    System.out.print("Enter your guess: ");
                    try {
                        int hintGuess = scanner.nextInt();
                        if (hintGuess == number) {
                            score += attempts / 2; // add half of the remaining attempts to the score
                            System.out.printf("Congratulations! You guessed the number using a hint.\nScore: %d\n", score);
                            hintsRemaining--;
                            break;
                        } else {
                            System.out.println("Incorrect. The hint did not help you.");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input. The hint did not help you.");
                        scanner.nextLine(); // consume the invalid input
                    }
                }
            }
        }

        if (round < 3) { // prepare for the next round
            attempts = 5;
            number = random.nextInt(max - min + 1) + min;
        }
    }

    System.out.println("Game over.");
    System.out.printf("Your final score is %d\n", score);
}
}                                                   