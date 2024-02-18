import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Author: BenDowty
 * Version: See MyWorld version for game version
 */
public class Turtle extends Cats
{
    static int HP = 300;
    static boolean lockInPlace = false;
    /**
     * Checks to see if its location needs to change.
     */
    private void checkForLock() {
        if (Turtle.lockInPlace) {
            setLocation(900, 400);
        }
    }
    /**
     * Updates its health on the screen.
     */
    private void updateTextRC() {
        if (Turtle.HP > 0) {
            ((MyWorld)getWorld()).myShowText(Integer.toString(Turtle.HP) + " HP", 601, 100);
        } else {
            ((MyWorld)getWorld()).myShowText(null, 601, 100);
        }
    }
    /**
     * Act - do whatever the RandCat wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
       updateTextRC();
       checkForLock();
    }
}
