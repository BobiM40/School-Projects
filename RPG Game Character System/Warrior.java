public class Warrior extends Character {
    private int strength;
    private double armor;
    private boolean defence = false;

    public Warrior(String name) {
        super(name);
        strength = 4; // do more damage based on strength
        armor = 0.1; //take less damage based on armor
    }
    @Override
    public int attack(){
        if(super.getMana() < 10){
            return -1;
        }
        super.changeMana(-10);
        System.out.println("-10 mana");
        int dmg = 10 + strength;
        System.out.println(super.getName() + "'s swing dealt " + dmg + " damage!");
        return dmg;
    }
    @Override
    public int defend(){
        if(super.getMana() < 5){
            return -1;
        }
        super.changeMana(-5);
        System.out.println("-5 mana");
        System.out.println(super.getName() + "'s armor will block some of the damage from the next attack! ");
        defence = true;
        return 0;
    }
    @Override
    public int castSpell(){
        if(super.getMana() < 25){
            return -1;
        }
        super.changeMana(-25);
        System.out.println("-25 mana");
        int dmg = 18 + strength;
        System.out.println(super.getName() + "'s Earthquake dealt " + dmg + " damage!");
        return dmg;
    }
    @Override
    public void levelUp(){
        super.levelUp();
        System.out.println("You are now level " + super.getLevel());
        System.out.println("     + 2 strength");
        System.out.println("     + 2% armor");
        strength += 2;
        armor += 0.02;
    }
    @Override
    public void takeDmg(int dmg){
        if(defence) {
            System.out.println(super.getName() + "'s shield managed to block " + (int)(armor * dmg) + " damage!");
            defence = false;
            super.takeDmg((int) (dmg * (1 - armor)));
        }else{
            super.takeDmg((dmg));
        }
    }
    @Override
    public byte chooseAction(){
        while(true) {
            double random = Math.random() * 10;
            if (random < 3) {
                return 2;
            } else if (random < 8 && super.getMana() > 10) {
                return 1;
            } else if (super.getMana() > 25) {
                return 3;
            }
            if(super.getMana() < 10){
                return 0;
            }
        }
    }
    @Override
    public void possibleActions(){
        System.out.println("1. swing: (-10) mana");
        System.out.println("2. shield up: (-5) mana");
        System.out.println("3. earthquake: (-25) mana");
    }
}
