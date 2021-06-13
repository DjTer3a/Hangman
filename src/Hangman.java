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


public class Hangman
{
    // Properties
    StringBuffer secretWord;
    StringBuffer allLetters;
    StringBuffer usedLetters;
    StringBuffer knownSoFar;
    int numberOfIncorrectTries;
    int maxAllowedIncorrectTries;
	final int MaxTries = 6;

    
    // Constructors
    public Hangman() {
        //we assumed that chooseSecretWord() method return String
        secretWord = chooseSecretWord();
        allLetters = new StringBuffer("abcdefghijklmnopqrstuvwxyz");
        usedLetters = new StringBuffer("");
        numberOfIncorrectTries = 0;
        maxAllowedIncorrectTries = MaxTries;
        knownSoFar = new StringBuffer("");  //insantiate KnownSoFar
        //we replaced the secretWord's characters and added the "*"
        for (int count = 0; count < secretWord.length(); count++) {
			knownSoFar.append("*");
		}


   }
    
    // Methods
    
    /**
     * @return correct letters inputted so far
     */
    public String getKnownSoFar() {
		return knownSoFar.toString();
	}
    
    /**
     * @return allLetters in alphabet
     */
    public String getAllLetters()
    {
        return allLetters.toString();   
    }
    
    /**
     * @return letters which are used by user 
     */
    public String getUsedLetters()
    {
        return usedLetters.toString();   
    }
    
    /**
     * @return the value of incorrect tries
     */
    public int getNumberOfIncorrectTries()
    {
        return numberOfIncorrectTries;
    }
    
    /**
     * @return the value of max incorrect tries
     */
    public int getMaxAllowedIncorrectTries()
    {
        return maxAllowedIncorrectTries;   
    }
    
    /**
     * @return Secret which determine whether is game over or not
     */
    public boolean isGameOver() {

		boolean SECRET = false;

		if (hasLost() || (knownSoFar.toString().equals(secretWord.toString()))) {

			SECRET = true;
		}
		return SECRET;
    }
    
    /** determine whether user lost game or not
     * @return true if number of incorrect tries is bigger or equal to the max allowed tries
     */
    public boolean hasLost() {

		return (numberOfIncorrectTries >= maxAllowedIncorrectTries);
	}

    public int tryThis(char letter) {
        int GameState = 0;
		int counter = 0;

		if ((letter > 96 && letter < 123)) {

			if (!hasLost()) {

				//Creating UsedLetters and SecretChar arrays
				String used = getUsedLetters();
				char[] UsedLetters = used.toLowerCase().toCharArray();
				String all = getAllLetters();
				char[] AllLetters = all.toLowerCase().toCharArray();
				String secret = secretWord.toString();
				char[] SecretChar = secret.toLowerCase().toCharArray();

				//Intialization
				int UsedLength = UsedLetters.length;
				int AllLength = AllLetters.length;
				int CharLength = SecretChar.length;
				int knownCount = 0;

				for (int i = 0; i < UsedLength; i++)
					if (letter == UsedLetters[i])
						counter++;
                
                
                // Letter is used before
				if (counter > 0)
					GameState = -2; 

				else {
					for (int i = 0; i < CharLength; i++) {

						//if loop to update knownSoFar and update the allLetters StringBuffer to exclude usedLetters
						if (SecretChar[i] == letter) {
							knownCount++;
							knownSoFar.replace(i, i + 1, String.valueOf(letter));
							usedLetters.append(letter);
							
							for(int count=0; count<AllLength;count++){
								if(letter==AllLetters[count]){
									allLetters.deleteCharAt(count);
								}
							}
							
						}
						GameState = knownCount;
					}

					//if loop to update usedLetters and update the allLetters StringBuffer to exclude usedLetters
					if (knownCount == 0) {
						numberOfIncorrectTries++;
						usedLetters.append(letter);
						GameState = knownCount;

                        for(int count=0; count<AllLength;count++){
                            if(letter==AllLetters[count]){
                                allLetters.deleteCharAt(count);
                            }
                        }
                        
					}

				}
            }
            // The Game is OVER!
			else
            GameState = -3; 

        }
    
        // The input is invalid!!
        else
            GameState = -1; 

        return GameState;
        }

        private StringBuffer chooseSecretWord() {
            final int wordcount;
            int indexOfWord;

            wordcount = 9;

            StringBuffer[] words = new StringBuffer[wordcount];

            words[0] = new StringBuffer("apple");
            words[1] = new StringBuffer("pencil");
            words[2] = new StringBuffer("computer");
            words[3] = new StringBuffer("people");
            words[4] = new StringBuffer("book");
            words[5] = new StringBuffer("laptop");
            words[6] = new StringBuffer("violin");
            words[7] = new StringBuffer("tiger");
            words[8] = new StringBuffer("lion");
		
            indexOfWord = ( int ) (Math.random() * words.length);
            return words[ indexOfWord ];
        }
    
    }