import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Author: BenDowty
 * Version: See MyWorld version for game version
 */
public class MocBook extends Cats
{
    public static boolean[] allowedToHack = new boolean[2];
    public static boolean cutScened = false;
    public static String a = "j";
    boolean dissappear = false;
    int counter = 0;
    /**
     * Intializes certain variables.
     */
    public MocBook() {
        dissappear = false;
        counter = 0;
        allowedToHack[1] = false;
        allowedToHack[0] = false;
    }
    /**
     * This is called whenever the timer has run out.
     */
    private void timeIsUp() {
            allowedToHack[0] = true;
            allowedToHack[1] = true;
            getWorld().removeObject(this);
    }
    /**
     * Dissapears or waits to be clicked at different times based on difficulty.
     */
    public void checkForHack() {
        counter++;
        Greenfoot.setSpeed(50);
        if (Mover.hard) {
            if (counter == 100) {
                timeIsUp();
            }
        } else if (Mover.easy) {
            if (counter == 600) {
                timeIsUp();
            }
        } else {
            if (counter == 300) {
                timeIsUp();
            }
        }
        if (Greenfoot.mouseClicked(null) && !Greenfoot.mouseClicked(this) && Mover.hard) {
            timeIsUp();
        }
        if (Greenfoot.mouseClicked(this) && !allowedToHack[0]) {
            allowedToHack[0] = false;
            allowedToHack[1] = true;
            dissappear = true;
        }
    }
    /**
     * Dissapears.
     */
    private void dissappear() {
        if (dissappear) {
            shrinkOutOfExistince();
        }
    }
    /**
     * Act - do whatever the MocBook wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (phase != 4) {
            if (!Cats.cutScening) {
            checkForHack();
        }
        dissappear();
        if (Greenfoot.mouseClicked(this) && Cats.cutScening) {
            allowedToHack[0] = false;
            cutScened = true;
            dissappear = true;
        }
        }
    }
}
