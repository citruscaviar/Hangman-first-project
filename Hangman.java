import java.util.Arrays;
import java.util.Scanner;

public class Hangman {

    public static String misses = "";
    public static Scanner scan = new Scanner(System.in);

    public static String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
    "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
    "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
    "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
    "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon", 
    "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal",
    "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
    "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
    "wombat", "zebra"};

    public static String[] gallows = {
    "+---+\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|   |\n" +
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + //if you were wondering, the only way to print '\' is with a trailing escape character, which also happens to be '\'
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" +
    "/    |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + 
    "/ \\  |\n" +
    "     |\n" +
    " =========\n"};



    public static String randomWord(String[] words){
        double randNum = Math.random() * words.length;
        int randInt = (int) randNum;
        String randWord = words[randInt];
        return randWord;
    }

    public static String printPlaceholders(String word){ 
        char[] wordToChar = word.toCharArray(); 
        char[] maskedArray = new char[wordToChar.length]; 
        for(int i = 0; i < wordToChar.length;i++){
            maskedArray[i] = '_'; 
        }
        return String.join(" ", new String(maskedArray)); 
    }


    public static boolean checkGuess(char guessLetter, String hiddenWord){
        return hiddenWord.indexOf(guessLetter) != -1;   
    }

    public static String updatePlaceholders(String word,String hiddenWord, char guess){
        StringBuilder newHiddenWord = new StringBuilder(hiddenWord);
        for (int i = 0; i< hiddenWord.length();i++){
            if (word.charAt(i) == guess) {
                newHiddenWord.setCharAt(i, guess);
            }
        }
        return newHiddenWord.toString();
    }

    private static void printMissedGuesses() {
    System.out.println("Misses: " + misses);
    }


    public static void main(String[] args) {
        
        
        String newWord = randomWord(words); // swan
        String hiddenWord = printPlaceholders(newWord);
     
        System.out.println(gallows[0]);
        System.out.println("Word: " + hiddenWord);
        System.out.println();
        System.out.println("Misses: ");
        System.out.println();
        System.out.print("Guess: ");
        char guess = scan.next().charAt(0);
        System.out.println();
        
        for (int i = 0; i < newWord.length(); i++) {
            if (checkGuess(guess, newWord)) {
                hiddenWord = updatePlaceholders(newWord, hiddenWord, guess);
                System.out.println(gallows[0]);
                System.out.println("Word: " + hiddenWord);
                System.out.println();
                System.out.print("Guess: "); 
                guess = scan.next().charAt(0);
                System.out.println();
                
            } else {
                if (newWord.charAt(i) != guess) {
                    misses+=guess;
                    switch (misses.length()) {
                        case 1: System.out.println(gallows[1]); break;
                        case 2: System.out.println(gallows[2]); break;
                        case 3: System.out.println(gallows[3]); break;
                        case 4: System.out.println(gallows[4]); break;
                        case 5: System.out.println(gallows[5]); break;
                        case 6: System.out.println(gallows[6]); break;
                    }
                    printMissedGuesses();
                    System.out.println();
                    System.out.println("Word: " + hiddenWord);
                    System.out.println();
                    System.out.print("Guess: "); 
                    guess = scan.next().charAt(0);  
                    System.out.println();
                } 
            }
        }
        char[] arrayNewWord = newWord.toCharArray();
        char[] arrayHiddenWord = hiddenWord.toCharArray();
        if (Arrays.equals(arrayNewWord, arrayHiddenWord)) {
            System.out.println("GOOD WORK!");
        } else{
            System.out.println("RIP! The word was: " + newWord);
        }
    }
}





