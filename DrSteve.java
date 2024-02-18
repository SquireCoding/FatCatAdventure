import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Author: BenDowty
 * Version: See MyWorld version for game version
 */
public class DrSteve extends Cats
{
    public static int HP = 400;
    public static boolean hacking = false;
    public static boolean needToGo = false;
    /**
     * Act - do whatever the DrSteve wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (HP > 0) {
            ((MyWorld)getWorld()).myShowText(Integer.toString(DrSteve.HP) + " HP", 850, 100);
        } else {
            ((MyWorld)getWorld()).myShowText(null, 850, 100);
        }
        if (needToGo) {
            realShrinkOutOfExistince();
        }
    }
}
