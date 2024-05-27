public class Archer extends Character{
    private double headshotChance;
    private int power;
    private boolean charged = false;
    public Archer(String name){
        super(name);
        headshotChance = 0.1;
        power = 6;
    }
    @Override
    public int attack(){
        if(super.getMana() < 10){
            return -1;
        }
        super.changeMana(-10);
        System.out.println("-10 mana");
        int dmg;
        if(Math.random() < headshotChance){
            if(!charged){
                dmg = (int)(10 * 1.25);
            }else {
                dmg = (int) ((10 + power) * 1.25);
                charged = false;
            }
        }else {
            if(!charged){
                dmg = 10;
            }else {
                dmg = 10 + power;
                charged = false;
            }
        }
        System.out.println(super.getName() + "'s bow dealt " + dmg + " damage!");
        return dmg;
    }
    @Override
    public int defend(){ //next attack does more dmg
        if(super.getMana() < 5){
            return -1;
        }
        super.changeMana(-5);
        System.out.println("-5 mana");
        charged = true;
        System.out.println(super.getName() + "'s next basic attack will deal extra damage!");
        return 0;
    }
    @Override
    public int castSpell(){
        if(super.getMana() < 25){
            return -1;
        }
        super.changeMana(-25);
        System.out.println("-25 mana");
        int dmg = 20;
        System.out.println(super.getName() + "'s Arrow Storm dealt " + dmg + " damage!");
        return dmg;
    }
    @Override
    public void levelUp(){
        super.levelUp();
        System.out.println("You are now level " + super.getLevel());
        System.out.println("     + 4% headshot chance");
        System.out.println("     + 1 power");
        headshotChance += 0.04;
        power += 1;
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
        System.out.println("1. shoot: (-10) mana");
        System.out.println("2. charge next shot: (-5) mana");
        System.out.println("3. arrow storm: (-25) mana");
    }
}
