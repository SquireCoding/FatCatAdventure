import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Author: BenDowty
 * Version: See MyWorld version for game version
 */
public class Text extends ForLooks
{
    public static final Color red = Color.RED;
    public static final Color green = Color.GREEN;
    /**
     * Adds an object that says "Sample Text".
     */
    public Text() {
        this("Sample Text");
    }
    /**
     * Adds a text that says whatever s is.
     */
    public Text(String s) {
        if (s != null) {
            setImage(new GreenfootImage(s, 25, Color.BLACK, new Color(0, 0, 0, 0)));
        }
    }
    /**
     * Adds a text in the color c that says whatever s is.
     */
    public Text(String s, Color c) {
        if (s != null) {
            setImage(new GreenfootImage(s, 25, c, new Color(0, 0, 0, 0)));
        }
    }
    /**
     * Adds a text in the color c with the background color background and the text saying whatever s is.
     */
    public Text(String s, Color c, Color background) {
        if (s != null) {
            setImage(new GreenfootImage(s, 25, c, background));
        }
    }
    /**
     * Adds a text that says s, in the size size, with the color c, and the background color background.
     */
    public Text(String s, int size, Color c, Color background) {
        if (s != null) {
            setImage(new GreenfootImage(s, size, c, background));
        }
    }
    /**
     * Act - do whatever the Text wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        //nada
    }
}
