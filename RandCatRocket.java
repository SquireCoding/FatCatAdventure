import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RandCatRocket here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RandCatRocket extends FatCat
{
    boolean direction;
    int speed;
    boolean hit;
    int damage;
    boolean player;
    boolean exploding;
    public RandCatRocket(boolean direct, int spe, int dam, boolean playe) {
        if (!direct) {
            GreenfootImage img = getImage();
            img.mirrorHorizontally();
            setImage(img);
            direction = false;
        } else {
            direction = true;
        }
        exploding = false;
        speed = spe;
        damage = dam;
        player = playe;
    }
    public int getDamage() {
        if (hit) {
            return 0;
        } else {
            return damage;
        }
    }
    public void setDamage(int dam) {
        damage = dam;
    }
    public boolean getPlayer() {
        return player;
    }
    public void setPlayer(boolean playe) {
        player = playe;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int s) {
        speed = s;
    }
    public void startExplosion() {
        exploding = true;
    }
    public boolean getExploding() {
        return exploding;
    }
    public void flipDirection() {
        if (direction) {
            direction = false;
        } else if (!direction) {
            direction = true;
        }
        GreenfootImage img = getImage();
        img.mirrorHorizontally();
        setImage(img);
    }
    public void explode() {
        if (exploding) {
            exploding = true;
        GreenfootImage img = getImage();
        img.scale(img.getWidth()+1, img.getHeight()+1);
        setImage(img);
        if (img.getWidth() >= 150 || img.getHeight() >= 150) {
            getWorld().removeObject(this);
        }
        }
    }
    private void shoot() {
        boolean dissapear = false;
        if (direction) {
            if (speed>1) {
                move(speed);
            } else {
                move(2);
            }
            if (getX() >= 1199) {
                dissapear = true;
            }
        }
        if (!direction) {
            if (speed > 1) {
                move(0-speed);
            } else {
                move(-2);
            }
            if (getX() <= 1) {
                dissapear = true;
            }
        }
        if (dissapear) {
            getWorld().removeObject(this);
        }
    }
    /**
     * Act - do whatever the RandCatRocket wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        explode();
        if (!exploding) {
            shoot();
        }
    }
}
