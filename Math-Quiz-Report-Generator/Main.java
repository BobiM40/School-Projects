import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int a, b;
        String ans = "", usrAns, name, problem = "";
        boolean correct;
        System.out.println("Time to solve some math problems! What's your name: ");
        name = scan.next();
        for(int i = 0; i < 5; i++){
            b = (int)(Math.random() * 50 + 1);
            a = b + (int)(Math.random() * 50);
            byte op = (byte)(Math.random() * 4);
            switch(op){
                case 0: // +
                    ans = Integer.toString(a + b);
                    problem = a + " + " + b + " = ";
                    System.out.println(problem);
                    break;
                case 1: // -
                    ans = Integer.toString(a - b);
                    problem = a + " - " + b + " = ";
                    System.out.println(problem);
                    break;
                case 2: // x
                    ans = Integer.toString(a * b);
                    problem = a + " x " + b + " = ";
                    System.out.println(problem);
                    break;
                case 3: // /
                    ans = Integer.toString(a / b);
                    problem = a + " / " + b + " = ";
                    System.out.println(problem);
                    break;
            }
            usrAns = scan.next();
            fileWriter(problem + usrAns + " " + usrAns.equalsIgnoreCase(ans), name);
        }
    }
    private static void fileWriter(String str, String user){
        try(FileWriter outStream = new FileWriter(user + "-answers.txt", true)){
            outStream.write(str + "\n");
        }catch(IOException e){
            System.out.print("missclick!" + e);
        }
    }
}
