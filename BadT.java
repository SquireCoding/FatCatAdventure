import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class BadT here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BadT extends FatCat
{
    int HP = 20;
    int iFrames = 0;
    static int killCount = 0;
    public BadT() {
        HP = 20;
    }
    private void checkForHit() {
        boolean d = false;
        Hitbox h = (Hitbox) getOneIntersectingObject(Hitbox.class);
        if (h!=null && iFrames == 0) {
            HP -= h.getDamage();
            iFrames = h.getDuration()*4;
        }
        if (iFrames > 0) {
            iFrames--;
        }
        if (HP <= 0) {
            addTurtle();
            killCount++;
            d = true;
        }
        RandCatRocket r = (RandCatRocket) getOneIntersectingObject(RandCatRocket.class);
        if (r != null) {
            iFrames = r.getSpeed()*4;
            HP -= r.getDamage();
            r.hit = true;
        }
        if (d) {
            getWorld().removeObject(this);
        }
    }
    private void addTurtle() {
        int fo = Greenfoot.getRandomNumber(4) + 1;
        int walln = 0;
        if (fo == 1 || fo == 3) {
            walln = Greenfoot.getRandomNumber(1200) + 1;
        } else {
            walln = Greenfoot.getRandomNumber(800) + 1;
        }
        int own = 0;
        if (fo == 1) {
            own = 0;
        }
        if (fo==2) {
            own = 1200;
        }
        if (fo==3) {
            own = 800;
        }
        if (fo == 4) {
            own = 0;
        }
        if (fo == 1 || fo == 3) {
            getWorld().addObject(new BadT(), walln, own);
        } else {
            getWorld().addObject(new BadT(), own, walln);
        }
    }
    private void chasePlayer() {
        if (iFrames == 0) {
            int px = 0;
            int py = 0;
            List<Player> l = getWorld().getObjects(Player.class);
            for (Player p: l) {
                if (p.getPlayer()) {
                    px = p.getX();
                    py = p.getY();
                }
            }
            turnTowards(px,py);
            moveTowards(px,py,1);
        }
    }
    /**
     * Act - do whatever the BadT wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        chasePlayer();
        checkForHit();
    }
}
