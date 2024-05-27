public class Mage extends Character{
    private int intelligence; //more intelligence = more mana
    private int spellPower; //do more dmg based on spellPower
    public Mage(String name){
        super(name);
        intelligence = 10;
        spellPower = 8;
    }
    @Override
    public int attack(){
        if(super.getMana() < 15){
            return -1;
        }
        super.changeMana(-15);
        System.out.println("-15 mana");
        int dmg = 10 + spellPower;
        System.out.println(super.getName() + "'s fireball dealt " + dmg + " damage!");
        return dmg;
    }
    @Override
    public int defend(){
        super.changeMana(intelligence);
        System.out.println(getName() + " is meditating, gaining " + intelligence + " mana. ");
        return 0;
    }
    @Override
    public int castSpell(){
        if(super.getMana() < 35){
            return -1;
        }
        super.changeMana(-35);
        System.out.println("-35 mana");
        int dmg = 20 + spellPower;
        System.out.println(super.getName() + "'s Thunderstorm dealt " + dmg + " damage!");
        return dmg;
    }
    @Override
    public void levelUp(){
        super.levelUp();
        System.out.println("You are now level " + super.getLevel());
        System.out.println("     + 3 intelligence");
        System.out.println("     + 2 spell power");
        intelligence += 3;
        spellPower += 2;
    }
    @Override
    public byte chooseAction(){
        while(true) {
            double random = Math.random() * 10;
            if (random < 3) {
                return 2;
            } else if (random < 8 && super.getMana() > 15) {
                return 1;
            } else if (super.getMana() > 35) {
                return 3;
            }
            if(super.getMana() < 15){
                return 0;
            }
        }
    }
    @Override
    public void possibleActions(){
        System.out.println("1. fireball: (-15) mana");
        System.out.println("2. meditate: (+" + intelligence + ") mana");
        System.out.println("3. thunderstorm: (-35) mana");
    }
}
