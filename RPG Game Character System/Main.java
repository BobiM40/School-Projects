import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your character: ");
        System.out.println("1. Warrior");
        System.out.println("2. Mage");
        System.out.println("3. Rouge");
        System.out.println("4. Archer");
        String input = scanner.next();
        boolean valid = false;
        int turn = 0;
        int dmg;
        while(!valid){
            valid = true;
            if(input.charAt(0) < '1' || input.charAt(0) > '4' || input.length() > 1){
                valid = false;
            }
            if(!valid) {
                System.out.println("Invalid input, try again: ");
                input = scanner.next();
            }
        }
        String name = "";
        Character player;
        switch(Integer.parseInt(input)){
            case 1:
                System.out.println("You have chosen to be a Warrior! ");
                System.out.println("What will your name be? ");
                name = scanner.next();
                player = new Warrior(name);
                break;
            case 2:
                System.out.println("You have chosen to be a Mage! ");
                System.out.println("What will your name be? ");
                name = scanner.next();
                player = new Mage(name);
                break;
            case 3:
                System.out.println("You have chosen to be a Rouge! ");
                System.out.println("What will your name be? ");
                name = scanner.next();
                player = new Rouge(name);
                break;
            case 4:
                System.out.println("You have chosen to be an Archer! ");
                System.out.println("What will your name be? ");
                name = scanner.next();
                player = new Archer(name);
                break;
            default:
                System.out.println("You shouldn't be able to see that. What?");
                return;
        }
        while(true) {
            System.out.println("Who do you wish to challenge to a battle? ");
            System.out.println("1. Warrior");
            System.out.println("2. Mage");
            System.out.println("3. Rouge");
            System.out.println("4. Archer");
            System.out.println("5. Random");
            input = scanner.next();
            valid = false;
            while (!valid) {
                valid = true;
                if (input.charAt(0) < '1' || input.charAt(0) > '5' || input.length() > 1) {
                    valid = false;
                }
                if (!valid) {
                    System.out.println("Invalid input, try again: ");
                    input = scanner.next();
                }
            }
            Character enemy;
            int inp = Integer.parseInt(input);
            if (inp == 5) {
                inp = (int) (Math.random() * 4) + 1; //math.random does not return 1
            }
            switch (inp) {
                case 1:
                    System.out.println("You have chosen to fight a Warrior! ");
                    enemy = new Warrior("Warrior");
                    break;
                case 2:
                    System.out.println("You have chosen to fight a Mage! ");
                    enemy = new Mage("Mage");
                    break;
                case 3:
                    System.out.println("You have chosen to fight a Rouge! ");
                    enemy = new Rouge("Rouge");
                    break;
                case 4:
                    System.out.println("You have chosen to fight an Archer! ");
                    enemy = new Archer("Archer");
                    break;
                default:
                    System.out.println("You shouldn't be able to see that. What?");
                    return;
            }
            System.out.println("May the battle begin! ");
            turn = (int) (Math.random() * 2);
            while (true) {
                if(player.getMana() > 100){
                    player.setMana(100);
                }
                if(enemy.getMana() > 100){
                    enemy.setMana(100);
                }
                if (turn % 2 == 0) {
                    System.out.println();
                    System.out.println("It's " + name + "'s turn!");
                    System.out.println("   health: " + player.getHealth());
                    System.out.println("   mana: " + player.getMana());
                    System.out.println();
                    player.possibleActions();
                    input = scanner.next();
                    valid = false;
                    while (!valid) {
                        valid = true;
                        if (input.charAt(0) < '1' || input.charAt(0) > '3' || input.length() > 1) {
                            valid = false;
                        }
                        if (!valid) {
                            System.out.println("Invalid input, try again: ");
                            input = scanner.next();
                        }
                    }
                    inp = Integer.parseInt(input);
                    switch (inp) {
                        case 1:
                            dmg = player.attack();
                            if (dmg == -1) {
                                System.out.println("You don't have enough mana to do that! You lost your turn. ");
                            } else {
                                enemy.takeDmg(dmg);
                            }
                            break;
                        case 2:
                            dmg = player.defend();
                            if (dmg == -1) {
                                System.out.println("You don't have enough mana to do that! You lost your turn. ");
                            }
                            break;
                        case 3:
                            dmg = player.castSpell();
                            if (dmg == -1) {
                                System.out.println("You don't have enough mana to do that! You lost your turn. ");
                            } else {
                                enemy.takeDmg(dmg);
                            }
                            break;
                        default:
                            System.out.println("You shouldn't be able to see that. What?");
                            return;
                    }
                } else {
                    System.out.println();
                    System.out.println("It's " + enemy.getName() + "'s turn!");
                    System.out.println("   health: " + enemy.getHealth());
                    System.out.println("   mana: " + enemy.getMana());
                    System.out.println();
                    switch (enemy.chooseAction()) {
                        case 0:
                            System.out.println(enemy.getName() + " does not have enough mana to do anything! Now's your chance...");
                            break;
                        case 1:
                            player.takeDmg(enemy.attack());
                            break;
                        case 2:
                            enemy.defend();
                            break;
                        case 3:
                            player.takeDmg(enemy.castSpell());
                            break;
                        default:
                            System.out.println("wtf");
                            return;
                    }
                }
                if (player.getHealth() <= 0) {
                    System.out.println(name + " has fallen to " + enemy.getName() + ". ");
                    System.out.println();
                    System.out.println("G A M E   O V E R");
                    return;
                }
                if (enemy.getHealth() <= 0) {
                    System.out.println(enemy.getName() + " has fallen to " + name + ". ");
                    player.levelUp();
                    break;
                }
                turn++;
                System.out.println("Both players get (+5) mana!");
                player.changeMana(5);
                enemy.changeMana(5);
            }
            System.out.println("Do you wish to continue playing? (yes/no)");
            input = scanner.next();
            valid = false;
            while (!valid) {
                valid = true;
                if (!(input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("no"))) {
                    valid = false;
                }
                if (!valid) {
                    System.out.println("Invalid input, try again: ");
                    input = scanner.next();
                }
            }
            if(input.equalsIgnoreCase("no")){
                System.out.println("Thanks for playing!");
                return;
            }else{
                System.out.println("In that case, you'll get sent back to max health and mana! How generous...");
                System.out.println();
                player.setHealth(100);
                player.setMana(100);
            }
        }
    }
}
