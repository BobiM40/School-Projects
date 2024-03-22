import java.util.Scanner;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
public class Wordle {
    ArrayList<String> words = new ArrayList<>();
    boolean[][][][][] exists = new boolean[26][26][26][26][26];
    String word;
    String guess;
    Scanner input = new Scanner(System.in);
    public static final String ANSI_BLACK = "\033[0;30m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public Wordle(){
        String url = "https://raw.githubusercontent.com/charlesreid1/five-letter-words/master/sgb-words.txt";
        try {
            Scanner scanner = new Scanner(new URL(url).openStream());
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                words.add(line);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        words.add("vozhd");
        words.add("glent");
        words.add("waqfs");
        for(String i : words) {
            exists[i.charAt(0) - 'a'][i.charAt(1) - 'a'][i.charAt(2) - 'a'][i.charAt(3) - 'a'][i.charAt(4) - 'a'] = true;
        }
    }
    public void chooseWord(){
        word = words.get((int)(Math.random() * words.size())).toLowerCase();
    }
    private boolean isWord(String guess){
        return exists[guess.charAt(0) - 'a'][guess.charAt(1) - 'a'][guess.charAt(2) - 'a'][guess.charAt(3) - 'a'][guess.charAt(4) - 'a'];
    }
    public void play(){
        byte attempts = 6;
        do{
            System.out.println("Enter your guess: ");
            boolean skip = false;
            while (true) {
                guess = input.next().toLowerCase();
                if(guess.length() != 5) {
                    System.out.println("Invalid guess! Try again: ");
                    continue;
                }
                skip = false;
                for(int i = 0; i < guess.length(); i++){
                    if(guess.charAt(i) < 'a' || guess.charAt(i) > 'z'){
                        System.out.println("Invalid guess! Try again: ");
                        skip = true;
                        break;
                    }
                }
                if(skip){
                    continue;
                }
                if(!isWord(guess)){
                    System.out.println("Guess must be a real word! Try again: ");
                    continue;
                }
                break;
            }
            provideFeedback(guess, word);
            attempts--;
            //soFar = updateWord(guess, word, soFar);
            if(guess.equals(word)){
                System.out.println("Congratulations! You guessed the correct word! ");
                break;
            }else if(attempts < 1){
                System.out.println("Uh, oh! You ran out of attempts! ");
                System.out.println("The correct word was " + word + ". ");
                break;
            }
            //System.out.println("So far, you have guessed: " + soFar.toString());
            System.out.println("You have " + attempts + " attempts left. ");
        }while(true);
    }
    public static void provideFeedback(String guess, String word){
        String letter = "";
        StringBuilder response = new StringBuilder(guess);
        byte guessed[] = {0, 0, 0, 0, 0};
        byte[] letters = new byte[(int)'z' + 1];
        for(int i = 0; i < word.length(); i++){
            if(guess.charAt(i) == word.charAt(i)){
                guessed[i] = 1;
            }else{
                letters[(int)word.charAt(i)]++;
            }
        }
        for(int i = 0; i < word.length(); i++){
            letter = "";
            letter += guess.charAt(i);
            if(word.contains(letter) && guessed[i] != 1 && letters[(int)guess.charAt(i)] > 0){
                guessed[i] = 2;
                letters[(int)guess.charAt(i)]--;
            }
        }

        for(int i = 0; i < word.length(); i++){
            if(guessed[i] == 2){
                System.out.print(ANSI_BLACK + ANSI_YELLOW_BACKGROUND + guess.charAt(i) + ANSI_RESET);
                System.out.print(" ");
            }else if(guessed[i] == 1){
                System.out.print(ANSI_BLACK + ANSI_GREEN_BACKGROUND + guess.charAt(i) + ANSI_RESET);
                System.out.print(" ");
            }else{
                System.out.print(ANSI_BLACK + ANSI_WHITE_BACKGROUND + guess.charAt(i) + ANSI_RESET);
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
