public class Rouge extends Character{
    private double dexterity; //higher dexterity = higher dodge chance
    boolean dodge = false;
    public Rouge(String name){
        super(name);
        dexterity = 0.50;
    }
    @Override
    public int attack(){
        if(super.getMana() < 5){
            return -1;
        }
        super.changeMana(-5);
        System.out.println("-5 mana");
        int dmg = 8;
        System.out.println(super.getName() + "'s swing dealt " + dmg + " damage!");
        return dmg;
    }
    @Override
    public int defend(){
        if(super.getMana() < 10){
            return -1;
        }
        super.changeMana(-10);
        dodge = true;
        System.out.println("-10 mana");
        System.out.println(super.getName() + " has a chance to dodge the next attack!");
        return 0;
    }
    @Override
    public int castSpell(){
        if(super.getMana() < 30){
            return -1;
        }
        super.changeMana(-30);
        System.out.println("-30 mana");
        int dmg = 20;
        System.out.println(super.getName() + "'s Tornado dealt " + dmg + " damage!");
        return dmg;
    }
    @Override
    public void takeDmg(int dmg){
        if(dodge){
            if(Math.random() < dexterity){
                System.out.println(super.getName() + " managed to dodge the attack!");
                super.takeDmg(0);
            }else{
                System.out.println(super.getName() + " didn't manage to dodge the attack!");
                super.takeDmg(dmg);
            }
            dodge = false;
        }else{
            super.takeDmg(dmg);
        }
    }
    @Override
    public void levelUp(){
        super.levelUp();
        System.out.println("You are now level " + super.getLevel());
        if(dexterity < 0.9) {
            dexterity += 0.05;
            System.out.println("     + 5% dexterity");
        }else{
            System.out.println("current dexterity: " + (dexterity * 100) + "% - M A X");
        }
    }
    @Override
    public byte chooseAction(){
        while(true) {
            double random = Math.random() * 10;
            if (random < 3) {
                return 2;
            } else if (random < 8 && super.getMana() > 5) {
                return 1;
            } else if (super.getMana() > 30) {
                return 3;
            }
            if(super.getMana() < 5){
                return 0;
            }
        }
    }
    @Override
    public void possibleActions(){
        System.out.println("1. swing: (-5) mana");
        System.out.println("2. dodge: (-10) mana");
        System.out.println("3. tornado: (-30) mana");
    }
}
