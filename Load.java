import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Author: BenDowty
 * Version: See MyWorld version for game version
 */
public class Load extends Controls
{
    static String a = "y";
    static String b = "u";
    static String c = "i";
    /**
     * Loads the save created by class Save.
     */
    public void loadSave() {
        if (!tutorial && !Mover.askingForBattle && Greenfoot.mouseClicked(this) && Save.saveAvailable) {
            FatCat.overhealProtect = Save.overhealProtect;
            FatCat.fishes = Save.fishes;
            FatCat.HP = Save.HP;
            Cats.fatCatTurn = true;
            FatCat.x = Save.x;
            FatCat.y = Save.y;
            RandCat.HP = Save.RandCatHP;
            DrSteve.HP = Save.drsteveHP;
            FatCat.imageLeft = Save.imageLeft;
            FatCat.imageRight = Save.imageRight;
            FatCat.s = 0;
            Cats.phase = Save.phase;
            getWorld().removeObjects(getWorld().getObjects(DrSteve.class));
            getWorld().removeObjects(getWorld().getObjects(Penguin.class));
            getWorld().removeObjects(getWorld().getObjects(RandCat.class));
            getWorld().removeObjects(getWorld().getObjects(Turtle.class));
            getWorld().removeObjects(getWorld().getObjects(Barrier.class));
            Turtle.HP = Save.turtleHP;
            Cats.battleNumber = Save.battleNumber;
            FatCat.setImage = true;
            for (int i = 0; i<=2; i++) {
                FatCat.barrierHere[i] = Save.barrierHere[i];
            }
            say("Load successful.");
            FatCat.isDead = false;
            Save.saveAvailable = false;
        } else if (!Save.saveAvailable && Greenfoot.mouseClicked(this)) {
            say("No save available. Create a save file first.");
        }
    }
    /**
     * Act - do whatever the Load wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        loadSave();
    }
}
