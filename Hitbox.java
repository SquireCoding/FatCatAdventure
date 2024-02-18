import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hitbox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hitbox extends FatCat
{
    int duration;
    int damage;
    boolean playerOrigin;
    boolean hitSomething = false;
    int shieldDamage;
    public Hitbox(int dur, int dam, boolean player, boolean visible) {
        duration = dur/2;
        damage = dam;
        shieldDamage = dam/10;
        playerOrigin = player;
        setImage(new GreenfootImage(28,28));
        if (visible) {
            GreenfootImage img = new GreenfootImage(28,28);
            img.setColor(Color.RED);
            img.fill();
            setImage(img);
        }
    }
    public Hitbox(int dur, int dam, boolean player, int size, boolean visible) {
        duration = dur/2;
        damage = dam;
        shieldDamage = dam/10;
        playerOrigin = player;
        setImage(new GreenfootImage(size,size));
        if (visible) {
            GreenfootImage img = new GreenfootImage(size,size);
            img.setColor(Color.BLUE);
            img.fill();
            setImage(img);
        }
    }
    public Hitbox(int dur, int dam, int shieldDam, boolean player, boolean visible) {
        duration = dur/2;
        damage = dam;
        shieldDamage = shieldDam;
        playerOrigin = player;
        setImage(new GreenfootImage(28,28));
        if (visible) {
            GreenfootImage img = new GreenfootImage(28,28);
            img.setColor(Color.PINK);
            img.fill();
            setImage(img);
        }
    }
    public Hitbox() {
        this(100, 10, true, 50, true);
    }
    public void setHitted(boolean b) {
        hitSomething = b;
    }
    public int getDuration() {
        return duration;
    }
    public boolean getHitted() {
        return hitSomething;
    }
    public int getShieldDamage() {
        return shieldDamage;
    }
    public boolean getPlayer() {
        return playerOrigin;
    }
    public int getDamage() {
        return damage;
    }
    private void hitboxStuff() {
        duration--;
        boolean dissapear = false;
        if (duration<=0) {
            dissapear = true;
        }
        if (dissapear) {
            getWorld().removeObject(this);
        }
    }
    /**
     * Act - do whatever the Hitbox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        hitboxStuff();
    }
}
