import java.util.Scanner;
public class SimpleWordle {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String ans;
        System.out.println("Welcome to Wordle! ");
        System.out.println("The rules are simple: ");
        System.out.println("1. You will have 6 attempts to guess the correct word. ");
        System.out.println("2. Each guess must be a valid 5-letter word. ");
        System.out.println("3. If a letter is coloured green, that means you guessed it right. ");
        System.out.println("4. If a letter is coloured yellow, that means it is in the word, but at another spot. ");
        System.out.println("5. If a letter is not coloured, that means you guessed it wrong. ");
        Wordle wordle = new Wordle();
        do{
            wordle.chooseWord();
            wordle.play();
            System.out.println("Do you want to play again? (yes/no) ");
            ans = input.next();
            while(!(ans.equalsIgnoreCase("no") || ans.equalsIgnoreCase("yes"))){
                System.out.println("Invalid answer! Try again: ");
                ans = input.next();
            }
            if(ans.equalsIgnoreCase("no")){
                System.out.println("Thanks for playing! ");
                break;
            }
        }while(true);
    }
}