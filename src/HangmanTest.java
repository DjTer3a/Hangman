/**
 * HW 01(Hangman) Instructions found on moodle
 * 
 * Style guidlines URL:-
 * http://www.cs.bilkent.edu.tr/~adayanik/cs101/practicalwork/styleguidelines.htm
 * 
 * 
 * @author Mostafa Higazy
 * @version 13/06/2021
 */

import java.util.Scanner;
public class HangmanTest {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		// variables
		Hangman HangmanGame = new Hangman();
		String letter;
		int numberOfOccurences;
        
        System.out.println();
        System.out.println("Welcome to hangman game! Good luck!");
        System.out.println();
        System.out.println("You have " + HangmanGame.getMaxAllowedIncorrectTries() + " tries left.");
        System.out.println();

		// displaying the secret word
		while (!HangmanGame.isGameOver()) {
			
			System.out.println("You are going to guess this " + HangmanGame.getKnownSoFar().length() 
                                + " letter word: " + HangmanGame.getKnownSoFar());
            System.out.println();
			System.out.println("Unused letters are :" + HangmanGame.getAllLetters());
            System.out.println();
			System.out.println("Used letters so far:" + HangmanGame.getUsedLetters());
			

			// to take the input from the user
            System.out.println();
			System.out.print("Guess a letter: ");
			System.out.println();
            letter = scan.next();
			
            if (letter.length() != 1) {
                System.out.println();
				System.out.println("You have to enter a single letter!");
                System.out.println();
			} 
            else {
				numberOfOccurences = HangmanGame.tryThis(letter.charAt(0));

				// to check the correction of inputs
				if (numberOfOccurences == -1){
					System.out.println();
					System.out.println("Invalid input: " + letter);
					System.out.println();}
				
				//When invalid input entered
				else if (numberOfOccurences == -2) {
                    System.out.println();
					System.out.println("This letter :- " + letter + " was already used before");
                    System.out.println();
					System.out.println("Please try another letter");
                    System.out.println();
				}

				//When game is lost
				else if (numberOfOccurences == -3){
                    System.out.println();
                    System.out.println("Game Over...");
                    System.out.println();
                }
					

				// If the given letter does not exist in the string print an error message accoordingly.
				else if (numberOfOccurences == 0) {
                    System.out.println();
					System.out.println("This letter :- " + letter + "  does not exist in the word! Try again.");
                    System.out.println();
                    System.out.println("You have " + (HangmanGame.getMaxAllowedIncorrectTries() - HangmanGame.getNumberOfIncorrectTries()) + " tries left.");
				}

				// If it exists, print a success message accoordingly
				else if (numberOfOccurences > 0) {
					HangmanGame.getKnownSoFar();
                    System.out.println();
					System.out.println("You guessed it right! Well done.");
				}

			}
		} 
        scan.close();
		
		// After a game ends, print whether the player won or lost
        if (HangmanGame.hasLost()) {
            System.out.println();
            System.out.println("You have lost. Better luck next time :)");
			System.out.println();
			System.out.println("The word was:- " + HangmanGame.secretWord.toString());
			System.out.println();
        }
        else {
            System.out.println();
            System.out.println("You have won. Well played.");
			System.out.println();
        }
	}

}
