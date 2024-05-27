public class Character {
    private String name;
    private int health;
    private int mana;
    private int level;
    public Character(String name){
        this.name = name;
        health = 100;
        mana = 100;
        level = 1;
    }
    public int attack(){
        if(mana < 15){
            return -1;
        }
        changeMana(-15);
        System.out.println("-15 mana");
        int dmg = 10;
        System.out.println(name + "'s attack dealt " + dmg + " damage!");
        return dmg;
    }
    public int defend(){
        changeMana(10);
        //pseudo code
        return 0;
    }
    public int castSpell(){
        if(mana < 25){
            return -1;
        }
        changeMana(-25);
        System.out.println("-25 mana");
        int dmg = 20;
        System.out.println(name + "'s Spell dealt " + dmg + " damage!");
        return dmg;
    }
    public void levelUp(){
        level++;
    }
    public void changeMana(int amount){
        mana += amount;
    }
    public void takeDmg(int dmg){
        health -= dmg;
    }
    public byte chooseAction(){
        while(true) {
            double random = Math.random() * 10;
            if (random < 3) {
                return 2;
            } else if (random < 8 && mana > 15) {
                return 1;
            } else if (mana > 25) {
                return 3;
            }
            if(mana < 10){
                return 0;
            }
        }
    }

    public int getMana() {
        return mana;
    }
    public int getHealth(){
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }
    public void possibleActions(){
        System.out.println("1. attack: (-15) mana");
        System.out.println("2. defend: (+10) mana");
        System.out.println("3. cast spell: (-25) mana");
    }
}
