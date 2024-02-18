import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Author: BenDowty
 * Version: See MyWorld version for game version
 */
public class RandCat extends Cats
{
    static int HP = 100;
    static boolean lockInPlace = false;
    /**
     * Checks to see if its location needs to change.
     */
    private void checkForLock() {
        if (lockInPlace) {
            setLocation(900, 400);
        }
    }
    /**
     * Updates its health on the screen.
     */
    private void updateTextRC() {
        if (RandCat.HP > 0) {
            ((MyWorld)getWorld()).myShowText(Integer.toString(RandCat.HP) + " HP", 600, 100);
        } else {
            ((MyWorld)getWorld()).myShowText(null, 600, 100);
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
