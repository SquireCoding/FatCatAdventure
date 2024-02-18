import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Author: BenDowty
 * Version: See MyWorld version for game version
 */
public class Save extends Controls
{
    static int fishes = 10;
    static int HP = 150;
    static boolean canSave = true;
    static int x = 600;
    static int y = 400;
    static boolean imageLeft = false;
    static boolean imageRight = true;
    final static int s = 0;
    static boolean saveAvailable = false;
    static int RandCatHP;
    static final String a = "k";
    static boolean shouldRun = false;
    static boolean overhealProtect = true;
    static boolean[] barrierHere = new boolean[3];
    static final boolean barrierHere3 = false;
    static int battleNumber = 0;
    static int turtleHP = 0;
    static int phase = 0;
    static int drsteveHP = 0;
    static int savesLeft = 4;
    static final GreenfootImage aliveIMG = new GreenfootImage("IMG_1958rt.png");
    /**
     * Creates a save for class Load to use.
     */
    public void save() {
        if (Greenfoot.mouseClicked(this)) {
            if (canSave && !tutorial && !Mover.askingForBattle && !FatCat.isDead) {
            if (Mover.hard && savesLeft > 0) {
                savesLeft--;
                fishes = FatCat.fishes;
            HP = FatCat.HP;
            x = FatCat.x;
            drsteveHP = DrSteve.HP;
            y = FatCat.y;
            RandCatHP = RandCat.HP;
            imageLeft = FatCat.imageLeft;
            imageRight = FatCat.imageRight;
            overhealProtect = FatCat.overhealProtect;
            turtleHP = Turtle.HP;
            phase = Cats.phase;
            battleNumber = Cats.battleNumber;
            for (int i = 0; i<=2; i++) {
                barrierHere[i] = FatCat.barrierHere[i];
            }
            saveAvailable = true;
            say("Save successful. " + Integer.toString(savesLeft) + "/8 saves left.");
            } else if (savesLeft == 0 && Mover.hard) {
                say("No saves left.");
            } else if (!Mover.hard) {
                fishes = FatCat.fishes;
            HP = FatCat.HP;
            x = FatCat.x;
            drsteveHP = DrSteve.HP;
            y = FatCat.y;
            RandCatHP = RandCat.HP;
            imageLeft = FatCat.imageLeft;
            imageRight = FatCat.imageRight;
            overhealProtect = FatCat.overhealProtect;
            turtleHP = Turtle.HP;
            battleNumber = Cats.battleNumber;
            for (int i = 0; i<=2; i++) {
                barrierHere[i] = FatCat.barrierHere[i];
            }
            saveAvailable = true;
            say("Save successful.");
        }            
        } else {
            say("You can't save right now.");
        }
        }
    }
    
    /**
     * Act - do whatever the Save wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        save();
    }
}
