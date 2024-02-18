import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends FatCat
{
    boolean player;
    int[] x = new int[2];
    int[] y = new int[2];
    boolean[] imageLeft = new boolean[2];
    boolean[] imageRight = new boolean[2];
    static String[] player1ASDF = new String[4];
    static String[] player2ASDF = new String[4];
    static int[] HP = new int[2];
    static int[] htFrames = new int[2];
    static int[] iFrames = new int[2];
    static int[] rocketCooldown = new int[2];
    static int[] shPoints = new int[2];
    static Color[] shColor = new Color[2];
    boolean there = true;
    static final GreenfootImage blankIMG = new GreenfootImage(28,28);
    static boolean[] shielding = new boolean[2];
    int counter = 0;
    String forwardMove[] = new String[2];
    static int[] counterReset = new int[2];
    static int[] specialCounter = new int[2];
    int chargeCounter[] = new int[2];
    public Player(boolean playerNumber) {
        player = playerNumber;
        there = true;
        if (player) {
            HP[0] = 150;
            imageLeft[0] = true;
            imageRight[0] = false;
            player1ASDF[0] = "f";
            player1ASDF[1] = "g";
            player1ASDF[2] = "h";
            player1ASDF[3] = "j";

            iFrames[0] = 1;
            x[0] = 300;
            y[0] = 400;
            htFrames[0] = 0;
            shPoints[0] = 250;
            shColor[0] = Color.BLUE;
        }
        if (!player) {
            HP[1] = 150;
            imageLeft[1] = true;
            imageRight[1] = false;
            player2ASDF[0] = "m";
            player2ASDF[1] = ",";
            player2ASDF[2] = ".";
            player2ASDF[3] = "/";
            iFrames[1] = 1;
            htFrames[1] = 0;
            x[1] = 900;
            y[1] = 400;
            shPoints[1] = 250;
            shColor[1] = Color.RED;
        }
    }
    public boolean getShielding(boolean playe) {
        if (playe) {
            return shielding[0];
        }
        if (!playe) {
            return shielding[1];
        }
        return true;
    }
    public int getHitstunFrames(boolean playe) {
        if (playe) {
            return htFrames[0];
        }
        if (!playe) {
            return htFrames[1];
        }
        return 10000;
    }
    public int getShieldPoints(boolean playe) {
        if (playe) {
            return shPoints[0];
        }
        if (!playe) {
            return shPoints[1];
        }
        return 3;
    }
    public boolean getThere() {
        return there;
    }
    public int getIFrames(boolean playe) {
        if (playe) {
            return iFrames[0];
        }
        if (!playe) {
            return iFrames[1];
        }
        return 5;
    }
    private boolean checkForHT() {
        if (player &&  htFrames[0] != 0) {
            return false;
        }
        if (!player && htFrames[1] != 0) {
            return false;
        }
        return true;
    }
    private void manageHT() {
        if (player && htFrames[0] > 0) {
            htFrames[0]--;
        }
        if (player && htFrames[1] > 0) {
            htFrames[1]--;
        }
        if (!player && rocketCooldown[1]>0) {
            rocketCooldown[1]--;
        }
        if (player && rocketCooldown[0]>0) {
            rocketCooldown[0]--;
        }
    }
    public void setIFrames(int iframes, boolean playe) {
        if (playe) {
            iFrames[0] = iframes;
        }
        if (!playe) {
            iFrames[1] = iframes;
        }
    }
    private void shieldPoker(boolean hitboxesVis) {
        if (player && Greenfoot.isKeyDown(player1ASDF[2])) {
            if (!imageLeft[0] || imageRight[0]) {
                object(Hitbox.class, new Hitbox(75, 2, 100, true, hitboxesVis), getX()+104,getY()-18);
            object(Hitbox.class, new Hitbox(75, 2, 100, true, hitboxesVis), getX()+128,getY()-6);
            object(Hitbox.class, new Hitbox(75, 2, 100, true, hitboxesVis), getX()+104,getY()+5);
            } else if (imageLeft[0] || !imageRight[0]) {
                object(Hitbox.class, new Hitbox(75, 2, 100, true, hitboxesVis), getX()-104,getY()-18);
            object(Hitbox.class, new Hitbox(75, 2, 100, true, hitboxesVis), getX()-128,getY()-6);
            object(Hitbox.class, new Hitbox(75, 2, 100, true, hitboxesVis), getX()-104,getY()+5);
            }
            htFrames[0] = 75;
        }
        if (!player && Greenfoot.isKeyDown(player2ASDF[2])) {
            if (!imageLeft[1] || imageRight[1]) {
                object(Hitbox.class, new Hitbox(75, 2, 100, false, hitboxesVis), getX()+104,getY()-18);
            object(Hitbox.class, new Hitbox(75, 2, 100, false, hitboxesVis), getX()+128,getY()-6);
            object(Hitbox.class, new Hitbox(75, 2, 100, false, hitboxesVis), getX()+104,getY()+5);
            } else if (imageLeft[1] || !imageRight[1]) {
                object(Hitbox.class, new Hitbox(75, 2, 100, false, hitboxesVis), getX()-104,getY()-18);
            object(Hitbox.class, new Hitbox(75, 2, 100, false, hitboxesVis), getX()-128,getY()-6);
            object(Hitbox.class, new Hitbox(75, 2, 100, false, hitboxesVis), getX()-104,getY()+5);}
            htFrames[1] = 75;
        }
    }
    private void checkForInvin() {
        if (player && iFrames[0] > 1) {
            GreenfootImage img;
            img = new GreenfootImage("IMG_1958y.png");
            if (imageRight[0] || !imageLeft[0]) {
                img.mirrorHorizontally();
            }
            iFrames[0]--;
            if (iFrames[0]%5 == 0) {
                if (there) {
                    setImage(new GreenfootImage(20,20));
                    there = false;
                } else if (!there) {
                    setImage(img);
                    there = true;
                }
            }
        } else if (player && iFrames[0] <= 1 && !there) {
            GreenfootImage img = new GreenfootImage("IMG_1958y.png");
            if (imageRight[0] || !imageLeft[0]) {
                img.mirrorHorizontally();
            }
            setImage(img);
            there = true;
        }
        if (!player && iFrames[1] > 1) {
            GreenfootImage img;
            img = new GreenfootImage("IMG_1958y.png");
            if (!imageLeft[1] || imageRight[1]) {
                img.mirrorHorizontally();
            } 
            iFrames[1]--;
            if (iFrames[1]%5 == 0) {
                if (there) {
                    setImage(new GreenfootImage(1,1));
                    there = false;
                } else if (!there) {
                    setImage(img);
                    there = true;
                }
            }
        } else if (!player && iFrames[1] <= 1 && !there) {
            GreenfootImage img = new GreenfootImage("IMG_1958y.png");
            if (!imageLeft[1] || imageRight[1]) {
                img.mirrorHorizontally();
            } 
            setImage(img);
            there = true;
        }
    }
    public void object(Class crs, Actor a, int x, int y) {
        if (getWorld().getObjectsAt(x, y, crs).size() == 0) {
            getWorld().addObject(a, x, y);
        }
    }
    public boolean getPlayer() {
        return player;
    }
    private void fireRocket() {
        if (Greenfoot.isKeyDown(player1ASDF[3]) && player && rocketCooldown[0] == 0 && !shielding[0]) {
            chargeCounter[0]++;
            if (chargeCounter[0] >= 500) {
                if (!imageLeft[0]||imageRight[0]) {
                    object(RandCatRocket.class, new RandCatRocket(true, 50, 50, true), getX()+128, getY()+1);
                } else if (imageLeft[0] || !imageRight[0]) {
                    object(RandCatRocket.class, new RandCatRocket(false, 50, 50, true), getX()-128, getY()+1);
                }
                htFrames[0] = chargeCounter[0];
                chargeCounter[0] = 0;
                rocketCooldown[0] = 200;
            }
        }
        if (shielding[0]) {
            chargeCounter[0] = 0;
        }
        if (shielding[1]) {
            chargeCounter[1] = 0;
        }
        if (chargeCounter[0] > 0 && !Greenfoot.isKeyDown(player1ASDF[3]) && player  && rocketCooldown[0] ==0 && !shielding[0]) {
            int dam = 0;
            if (chargeCounter[0]/10 <= 0) {
                dam = 1;
            } else {
                dam = chargeCounter[0]/10;
            }
            if (!imageLeft[0]||imageRight[0]) {
                    object(RandCatRocket.class, new RandCatRocket(true, chargeCounter[0]/10, dam, true), getX()+128, getY()+1);
                } else if (imageLeft[0] || !imageRight[0]) {
                    object(RandCatRocket.class, new RandCatRocket(false, chargeCounter[0]/10, dam, true), getX()-128, getY()+1);
                }
                htFrames[0] = chargeCounter[0];
                chargeCounter[0] = 0;
                rocketCooldown[0] = 200;
        }
        if (Greenfoot.isKeyDown(player2ASDF[3]) && !player && rocketCooldown[1] == 0 && !shielding[1]) {
            chargeCounter[1]++;
            if (chargeCounter[1] >= 500) {
                if (!imageLeft[1]||imageRight[1]) {
                    object(RandCatRocket.class, new RandCatRocket(true, 50, 50, false), getX()+128, getY()+1);
                } else if (imageLeft[1] || !imageRight[1]) {
                    object(RandCatRocket.class, new RandCatRocket(false, 50, 50, false), getX()-128, getY()+1);
                }
                htFrames[1] = chargeCounter[1];
                chargeCounter[1] = 0;
                rocketCooldown[1] = 200;
            }
        }
        if (chargeCounter[1] > 0 && !Greenfoot.isKeyDown(player2ASDF[3]) && !player && rocketCooldown[1] ==0  && !shielding[1]) {
            int dam = 0;
            if (chargeCounter[1]/10 <= 0) {
                dam = 1;
            } else {
                dam = chargeCounter[1]/10;
            }
            if (!imageLeft[1]||imageRight[1]) {
                    object(RandCatRocket.class, new RandCatRocket(true, chargeCounter[1]/10, dam, false), getX()+128, getY()+1);
                } else if (imageLeft[1] || !imageRight[1]) {
                    object(RandCatRocket.class, new RandCatRocket(false, chargeCounter[1]/10, dam, false), getX()-128, getY()+1);
                }
                htFrames[1] = chargeCounter[1];
                chargeCounter[1] = 0;
                rocketCooldown[1] = 200;
        }
    }
    private void checkForWin() {
        boolean dissapear = false;
        if (Mover.gameMode==1) {
            if (HP[0] <= 0 && player) {
            dissapear = true;
            ((MyWorld)getWorld()).myShowText("P2 Wins!", 600, 400);
        } else if (!player && HP[1] <= 0) {
            dissapear = true;
            ((MyWorld)getWorld()).myShowText("P1 Wins!", 600, 400);
        }
        } else if(Mover.gameMode == 3) {
            if (HP[0] <=0) {
                dissapear = true;
            ((MyWorld)getWorld()).myShowText(Integer.toString(BadT.killCount) + " turtles defeated!", 600, 400);
            }
        }
        if (dissapear) {
            getWorld().removeObject(this);
        }
    }
    private void attack(boolean hitboxesVis) {
        checkForSpecialMoves(hitboxesVis);
        if (Greenfoot.isKeyDown(player1ASDF[0]) && htFrames[0] == 0) {
            slash(true, hitboxesVis);
            htFrames[0] = 100;
        }
        if (Greenfoot.isKeyDown(player2ASDF[0]) && htFrames[1] == 0) {
            slash(false, hitboxesVis);
        }
        shieldPoker(hitboxesVis);
        fireRocket();
    }
    private void displayFreeze() {
        if (player && htFrames[0] > 1) {
            GreenfootImage img = getImage();
            img.setColor(new Color(0,255,0,10));
                    img.fillOval(100, 75, 20, 20);
                    setImage(img);
        } else if (player && (htFrames[0] <= 1) && !shielding[0] && iFrames[0] <= 1) {
            GreenfootImage img = new GreenfootImage("IMG_1958rt.png");
            if (imageLeft[0] || !imageRight[0]) {
                img.mirrorHorizontally();
            }
            setImage(img);
        }
        if (!player && (htFrames[1] > 1)) {
            GreenfootImage img = getImage();
            img.setColor(new Color(0,255,0,10));
                    img.fillOval(100, 75, 20, 20);
                    setImage(img);
        } else if (!player && (htFrames[1] <= 1) && !shielding[1] && iFrames[1] <= 1) {
            GreenfootImage img = new GreenfootImage("IMG_1958rt.png");
            if (imageLeft[1] || !imageRight[1]) {
                img.mirrorHorizontally();
            }
            setImage(img);
        }
    }
    private void checkForHit() {
        Hitbox h = (Hitbox) getOneIntersectingObject(Hitbox.class);
        if (h != null) {
            if (shielding[0] && player && shPoints[0] > 0) {
                shPoints[0] -= h.getShieldDamage();
            } else {
                if (player && !h.getPlayer() && iFrames[0] == 1) {
                HP[0] -= h.getDamage();
                h.hitSomething = true;
                htFrames[0] = 0;
                iFrames[0] = 200;
            }
            }
            if (shielding[1] && !player && shPoints[1] > 0) {
                shPoints[1] -= h.getShieldDamage();
            } else {
                if (!player && h.getPlayer() && iFrames[1] == 1) {
                HP[1] -= h.getDamage();
                h.hitSomething = true;
                htFrames[1] = 0;
                iFrames[1] = 200;
            }
            }
        }
        RandCatRocket r = (RandCatRocket) getOneIntersectingObject(RandCatRocket.class);
        if (r != null) {
            if (shielding[0] && player && !r.getPlayer()) {
                r.flipDirection();
                r.setDamage(r.getDamage()+10);
                r.setSpeed(r.getSpeed()+5);
                r.setPlayer(true);
            }
            if (player && !r.getPlayer() && !r.getExploding() && !shielding[0]) {
                r.startExplosion();
                HP[0] -= r.getDamage();
                htFrames[0] = 0;
            }
            if (shielding[1] && !player && r.getPlayer()) {
                r.flipDirection();
                r.setDamage(r.getDamage()+10);
                r.setSpeed(r.getSpeed()+5);
                r.setPlayer(false);
            }
            if (!player && r.getPlayer() && !r.getExploding() && !shielding[1]) {
                r.startExplosion();
                HP[1] -= r.getDamage();
                htFrames[1] = 0;
            }
        }
        BadT b = (BadT) getOneIntersectingObject(BadT.class);
        if (b!=null && iFrames[0] <= 1) {
            HP[0] -= Greenfoot.getRandomNumber(10) + 1;
            b.iFrames = 120;
            htFrames[0] = 1;
            iFrames[0] = 100;
        }
    }
    private void slash(boolean playe, boolean hitboxesVis) {
        if (player && playe) {
            if (!imageLeft[0] || imageRight[0]) {
                object(Hitbox.class, new Hitbox(100, 10, true, hitboxesVis), getX() + 94, getY() - 40);
                object(Hitbox.class, new Hitbox(100, 10, true, hitboxesVis), getX() + 99, getY() - 17);
                object(Hitbox.class, new Hitbox(100, 10, true, hitboxesVis), getX() + 95, getY() + 7);
                object(Hitbox.class, new Hitbox(100, 10, true, hitboxesVis), getX() + 88, getY() + 32);
                object(Hitbox.class, new Hitbox(100, 10, true, hitboxesVis), getX() + 72, getY() + 57);
                htFrames[0] = 100;
                object(Hitbox.class, new Hitbox(100, 10, true, hitboxesVis), getX() + 94, getY() - 40);
                object(Hitbox.class, new Hitbox(100, 10, true, hitboxesVis), getX() + 99, getY() - 17);
                object(Hitbox.class, new Hitbox(100, 10, true, hitboxesVis), getX() + 95, getY() + 7);
                object(Hitbox.class, new Hitbox(100, 10, true, hitboxesVis), getX() + 88, getY() + 32);
                object(Hitbox.class, new Hitbox(100, 10, true, hitboxesVis), getX() + 72, getY() + 57);
            } else  if (imageLeft[0] || !imageRight[0]) {
                object(Hitbox.class, new Hitbox(100, 10, true, hitboxesVis), getX() - 94, getY() - 40);
                object(Hitbox.class, new Hitbox(100, 10, true, hitboxesVis), getX() - 99, getY() - 17);
                object(Hitbox.class, new Hitbox(100, 10, true, hitboxesVis), getX() - 95, getY() + 7);
                object(Hitbox.class, new Hitbox(100, 10, true, hitboxesVis), getX() - 88, getY() + 32);
                object(Hitbox.class, new Hitbox(100, 10, true, hitboxesVis), getX() - 72, getY() + 57);
                htFrames[0] = 100;
                object(Hitbox.class, new Hitbox(100, 10, true, hitboxesVis), getX() - 94, getY() - 40);
                object(Hitbox.class, new Hitbox(100, 10, true, hitboxesVis), getX() - 99, getY() - 17);
                object(Hitbox.class, new Hitbox(100, 10, true, hitboxesVis), getX() - 95, getY() + 7);
                object(Hitbox.class, new Hitbox(100, 10, true, hitboxesVis), getX() - 88, getY() + 32);
                object(Hitbox.class, new Hitbox(100, 10, true, hitboxesVis), getX() - 72, getY() + 57);
            }
            
        }
        if (!player && !playe) {
            if (!imageLeft[1] || imageRight[1]) {
                object(Hitbox.class, new Hitbox(100, 10, false, hitboxesVis), getX() + 94, getY() - 40);
                object(Hitbox.class, new Hitbox(100, 10, false, hitboxesVis), getX() + 99, getY() - 17);
                object(Hitbox.class, new Hitbox(100, 10, false, hitboxesVis), getX() + 95, getY() + 7);
                object(Hitbox.class, new Hitbox(100, 10, false, hitboxesVis), getX() + 88, getY() + 32);
                object(Hitbox.class, new Hitbox(100, 10, false, hitboxesVis), getX() + 72, getY() + 57);
                htFrames[1] = 100;
                object(Hitbox.class, new Hitbox(100, 10, false, hitboxesVis), getX() + 94, getY() - 40);
                object(Hitbox.class, new Hitbox(100, 10, false, hitboxesVis), getX() + 99, getY() - 17);
                object(Hitbox.class, new Hitbox(100, 10, false, hitboxesVis), getX() + 95, getY() + 7);
                object(Hitbox.class, new Hitbox(100, 10, false, hitboxesVis), getX() + 88, getY() + 32);
                object(Hitbox.class, new Hitbox(100, 10, false, hitboxesVis), getX() + 72, getY() + 57);
            } else  if (imageLeft[1] || !imageRight[1]) {
                object(Hitbox.class, new Hitbox(100, 10, false, hitboxesVis), getX() - 94, getY() - 40);
                object(Hitbox.class, new Hitbox(100, 10, false, hitboxesVis), getX() - 99, getY() - 17);
                object(Hitbox.class, new Hitbox(100, 10, false, hitboxesVis), getX() - 95, getY() + 7);
                object(Hitbox.class, new Hitbox(100, 10, false, hitboxesVis), getX() - 88, getY() + 32);
                object(Hitbox.class, new Hitbox(100, 10, false, hitboxesVis), getX() - 72, getY() + 57);
                htFrames[1] = 100;
                object(Hitbox.class, new Hitbox(100, 10, false, hitboxesVis), getX() - 94, getY() - 40);
                object(Hitbox.class, new Hitbox(100, 10, false, hitboxesVis), getX() - 99, getY() - 17);
                object(Hitbox.class, new Hitbox(100, 10, false, hitboxesVis), getX() - 95, getY() + 7);
                object(Hitbox.class, new Hitbox(100, 10, false, hitboxesVis), getX() - 88, getY() + 32);
                object(Hitbox.class, new Hitbox(100, 10, false, hitboxesVis), getX() - 72, getY() + 57);
            }
        }
    }
    private void shield() {
                GreenfootImage img = getImage();
                GreenfootImage oimg = new GreenfootImage("IMG_1958y.png");
            if (player && Greenfoot.isKeyDown(player1ASDF[1])) {
                if (shPoints[0] <= 0) {
                    htFrames[0] = 1000;
                    shielding[0] = false;
                    img.setColor(new Color(0,255,0,10));
                    img.fillOval(100, 75, 20, 20);
                    setImage(img);
                }
                if (shPoints[0] > 0) {
                    shPoints[0]-= 2;
                    img = oimg;
                    if (!imageLeft[0] || imageRight[0]) {
                        img.mirrorHorizontally();
                    }
                    shielding[0] = true;
                    htFrames[0] = 5;
                    if (shPoints[0] <= 0) {
                        shPoints[0] = 0;
                    }
                    img.setColor(new Color(0,0,225, shPoints[0]));
                    img.fillOval(10, 10, 160, 160);
                    setImage(img);
                }
        }
        if (player && !Greenfoot.isKeyDown(player1ASDF[1]) && shielding[0]) {
            GreenfootImage qimg = oimg;
            if (!imageLeft[0] || imageRight[0]) {
                        qimg.mirrorHorizontally();
                    }
            setImage(qimg);
            shielding[0] = false;
        }
        if (player && !Greenfoot.isKeyDown(player1ASDF[1])) {
            counter++;
            shielding[0] = false;
            if (counter >= 3 && shPoints[0] < 255) {
                shPoints[0]++;
                counter = 0;
            }
        }
            if (!player && Greenfoot.isKeyDown(player2ASDF[1])) {
                
                if (shPoints[1] <= 0) {
                    htFrames[1] = 1000;
                    shielding[1] = false;
                    img.setColor(new Color(0,255,0,10));
                    img.fillOval(100, 75, 20, 20);
                    setImage(img);
                }
                if (shPoints[1] > 0) {
                    shPoints[1]-= 2;
                    img = oimg;
                    if (!imageLeft[1] || imageRight[1]) {
                        img.mirrorHorizontally();
                    }
                    if (shPoints[1] <= 0) {
                        shPoints[1] = 0;
                    }
                    
                    shielding[1] = true;
                    htFrames[1] = 5;
                    img.setColor(new Color(255,0,0, shPoints[1]));
                    img.fillOval(10, 10, 160, 160);
                    setImage(img);
                }
        }
        if (!player && !Greenfoot.isKeyDown(player2ASDF[1]) && shielding[1]) {
            setImage(oimg);
            shielding[1] = false;
        }
        if (!player && !Greenfoot.isKeyDown(player2ASDF[1])) {
            counter++;
            shielding[1] = false;
            if (counter >= 3 && shPoints[1] < 255) {
                shPoints[1]++;
                counter = 0;
            }
        }
    }
    private void displayHealth() {
        if (player) {
            if (HP[0] > 50) {
                ((MyWorld)getWorld()).myShowText(Integer.toString(HP[0]) + " HP P1", 200, 100);
            } else {
                ((MyWorld)getWorld()).myShowText(Integer.toString(HP[0]) + " HP P1", 200, 100, Color.RED);
            }
        }
        if (!player) {
            if (HP[1] > 50) {
                ((MyWorld)getWorld()).myShowText(Integer.toString(HP[1]) + " HP P2", 1000, 100);
            } else {
                ((MyWorld)getWorld()).myShowText(Integer.toString(HP[1]) + " HP P2", 1000, 100, Color.RED);
            }
        }
    }
    private boolean checkKey(String s) {
        if (s.length() == 1) {
            return true;
        }
        if (s.equals("enter") || s.equals("space") || s.equals("tab") || s.equals("backspace") || s.equals("escape") || s.equals("shift") || s.equals("control")) {
            return true;
        }
        return false;
    }
    private void checkForSettings() {
        if (Greenfoot.isKeyDown("y")) {
            String s = Greenfoot.ask("Which control to reconfigure?");
            if (s.equals(player1ASDF[0])) {
                String z = Greenfoot.ask("Reconfigure Player 1 Slash to what?");
                if (checkKey(z) && !s.equals(player1ASDF[1]) && !s.equals(player1ASDF[2]) && !s.equals(player1ASDF[3])) {
                    player1ASDF[0] = z;
                } else if (!checkKey(z)) {
                    String q = Greenfoot.ask("Incompatible key type.");
                }
            }
            if (s.equals(player1ASDF[1])) {
                String z = Greenfoot.ask("Reconfigure Player 1 Shield to what?");
                if (checkKey(z) && !s.equals(player1ASDF[0]) && !s.equals(player1ASDF[2]) && !s.equals(player1ASDF[3])) {
                    player1ASDF[1] = z;
                } else if (!checkKey(z)) {
                    String q = Greenfoot.ask("Incompatible key type.");
                }
            }
            if (s.equals(player1ASDF[2])) {
                String z = Greenfoot.ask("Reconfigure Player 1 Shield Poker to what?");
                if (checkKey(z) && !s.equals(player1ASDF[0]) && !s.equals(player1ASDF[1]) && !s.equals(player1ASDF[3])) {
                    player1ASDF[2] = z;
                } else if (!checkKey(z)) {
                    String q = Greenfoot.ask("Incompatible key type.");
                }
            }
            if (s.equals(player1ASDF[3])) {
                String z = Greenfoot.ask("Reconfigure Player 1 Random Cat Rocket to what?");
                if (checkKey(z) && !s.equals(player1ASDF[0]) && !s.equals(player1ASDF[2]) && !s.equals(player1ASDF[1])) {
                    player1ASDF[3] = z;
                } else if (!checkKey(z)) {
                    String q = Greenfoot.ask("Incompatible key type.");
                }
            }
             if (s.equals(player2ASDF[0])) {
                String z = Greenfoot.ask("Reconfigure Player 2 Slash to what?");
                if (checkKey(z) && !s.equals(player2ASDF[1]) && !s.equals(player2ASDF[2]) && !s.equals(player2ASDF[3])) {
                    player2ASDF[0] = z;
                } else if (!checkKey(z)) {
                    String q = Greenfoot.ask("Incompatible key type.");
                }
            }
             if (s.equals(player2ASDF[1])) {
                String z = Greenfoot.ask("Reconfigure Player 2 Shield to what?");
                if (checkKey(z) && !s.equals(player2ASDF[0]) && !s.equals(player2ASDF[2]) && !s.equals(player2ASDF[3])) {
                    player2ASDF[1] = z;
                } else if (!checkKey(z)) {
                    String q = Greenfoot.ask("Incompatible key type.");
                }
            }
             if (s.equals(player2ASDF[2])) {
                String z = Greenfoot.ask("Reconfigure Player 2 Shield Poker to what?");
                if (checkKey(z) && !s.equals(player2ASDF[1]) && !s.equals(player2ASDF[0]) && !s.equals(player2ASDF[3])) {
                    player2ASDF[2] = z;
                } else if (!checkKey(z)) {
                    String q = Greenfoot.ask("Incompatible key type.");
                }
            }
             if (s.equals(player2ASDF[3])) {
                String z = Greenfoot.ask("Reconfigure Player 2 Random Cat Rocket to what?");
                if (checkKey(z) && !s.equals(player2ASDF[1]) && !s.equals(player2ASDF[2]) && !s.equals(player2ASDF[0])) {
                    player2ASDF[3] = z;
                } else if (!checkKey(z)) {
                    String q = Greenfoot.ask("Incompatible key type.");
                }
            }
        }
    }
    private void checkForTurtle() {
        BadT b = (BadT) getOneIntersectingObject(BadT.class);
        if (b!= null) {
            HP[0] -= 5;
            iFrames[0] = 300;
        }
    }
    private void checkForSpecialMoves(boolean hitboxesVis) {
        if (player && HP[0] <= 50) {
            if (!imageLeft[0]) {
                forwardMove[0] = "d";
            } else if (imageLeft[0]) {
                forwardMove[0] = "a";
            }
            if (counterReset[0] >= 1) {
                counterReset[0]++;
                if (counterReset[0] >= 100) {
                    counterReset[0] = 0;
                    specialCounter[0] = 0;
                }
            }
            if (Greenfoot.isKeyDown(forwardMove[0]) && specialCounter[0] <= 0) {
                specialCounter[0] = 1;
                counterReset[0] = 1;
            }
            if (Greenfoot.isKeyDown("w") && specialCounter[0] == 1) {
                specialCounter[0] = 2;
            }
            if (Greenfoot.isKeyDown(forwardMove[0]) && specialCounter[0] == 2) {
                specialCounter[0] = 3;
            }
            if (Greenfoot.isKeyDown("w") && specialCounter[0] == 3) {
                specialCounter[0] = 4;
            }
            if (Greenfoot.isKeyDown(player1ASDF[0]) && specialCounter[0] == 4) {
                specialCounter[0] = 0;
                powerKitty(hitboxesVis);
                htFrames[0] = 120;
                powerKitty(hitboxesVis);
            }
        }if (!player && HP[1] <= 50) {
            if (!imageLeft[1]) {
                forwardMove[1] = "right";
            } else if (imageLeft[1]) {
                forwardMove[1] = "left";
            } 
            if (counterReset[1] >= 1) {
                counterReset[1]++;
                if (counterReset[1] >= 100) {
                    counterReset[1] = 0;
                    specialCounter[1] = 0;
                }
            }
            if (Greenfoot.isKeyDown(forwardMove[1]) && specialCounter[1] <= 0) {
                specialCounter[1] = 1;
                counterReset[1] = 1;
            }
            if (Greenfoot.isKeyDown("up") && specialCounter[1] == 1) {
                specialCounter[1] = 2;
            }
            if (Greenfoot.isKeyDown(forwardMove[1]) && specialCounter[1] == 2) {
                specialCounter[1] = 3;
            }
            if (Greenfoot.isKeyDown("up") && specialCounter[1] == 3) {
                specialCounter[1] = 4;
            }
            if (Greenfoot.isKeyDown(player1ASDF[1]) && specialCounter[1] == 4) {
                specialCounter[1] = 0;
                powerKitty(hitboxesVis);
                htFrames[1] = 120;
                powerKitty(hitboxesVis);
            }
        }
    }
    public void powerKitty(boolean hitboxesVis) {
        if (player && !imageLeft[0]) {
            object(Hitbox.class, new Hitbox(100, 30, true, 50, hitboxesVis), getX()+201,getY()-103);
        object(Hitbox.class, new Hitbox(100, 30, true, 50, hitboxesVis), getX()+199,getY()-60);
        object(Hitbox.class, new Hitbox(100, 30, true, 50, hitboxesVis), getX()+196,getY()-14);
        object(Hitbox.class, new Hitbox(100, 30, true, 50, hitboxesVis), getX()+194,getY()+34);
        object(Hitbox.class, new Hitbox(100, 30, true, 50, hitboxesVis), getX()+192,getY()+76);
        object(Hitbox.class, new Hitbox(100, 30, true, 50, hitboxesVis), getX()+152,getY()+60);
        object(Hitbox.class, new Hitbox(100, 30, true, 50, hitboxesVis), getX()+157,getY()+10);
        object(Hitbox.class, new Hitbox(100, 30, true, 50, hitboxesVis), getX()+156,getY()-38);
        object(Hitbox.class, new Hitbox(100, 30, true, 50, hitboxesVis), getX()+155,getY()-82);
        object(Hitbox.class, new Hitbox(100, 30, true, 50, hitboxesVis), getX()+115,getY()-57);
        object(Hitbox.class, new Hitbox(100, 30, true, 50, hitboxesVis), getX()+108,getY()-13);
        object(Hitbox.class, new Hitbox(100, 30, true, 50, hitboxesVis), getX()+116,getY()+33);
        } else if (player && imageLeft[0]) {
            object(Hitbox.class, new Hitbox(100, 30, true, 50, hitboxesVis), getX()+201,getY()-103);
        object(Hitbox.class, new Hitbox(100, 30, true, 50, hitboxesVis), getX()-199,getY()-60);
        object(Hitbox.class, new Hitbox(100, 30, true, 50, hitboxesVis), getX()-196,getY()-14);
        object(Hitbox.class, new Hitbox(100, 30, true, 50, hitboxesVis), getX()-194,getY()+34);
        object(Hitbox.class, new Hitbox(100, 30, true, 50, hitboxesVis), getX()-192,getY()+76);
        object(Hitbox.class, new Hitbox(100, 30, true, 50, hitboxesVis), getX()-152,getY()+60);
        object(Hitbox.class, new Hitbox(100, 30, true, 50, hitboxesVis), getX()-157,getY()+10);
        object(Hitbox.class, new Hitbox(100, 30, true, 50, hitboxesVis), getX()-156,getY()-38);
        object(Hitbox.class, new Hitbox(100, 30, true, 50, hitboxesVis), getX()-155,getY()-82);
        object(Hitbox.class, new Hitbox(100, 30, true, 50, hitboxesVis), getX()-115,getY()-57);
        object(Hitbox.class, new Hitbox(100, 30, true, 50, hitboxesVis), getX()-108,getY()-13);
        object(Hitbox.class, new Hitbox(100, 30, true, 50, hitboxesVis), getX()-116,getY()+33);
        }
        if (!player && !imageLeft[1]) {
            object(Hitbox.class, new Hitbox(100, 30, false, 50, hitboxesVis), getX()+201,getY()-103);
        object(Hitbox.class, new Hitbox(100, 30, false, 50, hitboxesVis), getX()+199,getY()-60);
        object(Hitbox.class, new Hitbox(100, 30, false, 50, hitboxesVis), getX()+196,getY()-14);
        object(Hitbox.class, new Hitbox(100, 30, false, 50, hitboxesVis), getX()+194,getY()+34);
        object(Hitbox.class, new Hitbox(100, 30, false, 50, hitboxesVis), getX()+192,getY()+76);
        object(Hitbox.class, new Hitbox(100, 30, false, 50, hitboxesVis), getX()+152,getY()+60);
        object(Hitbox.class, new Hitbox(100, 30, false, 50, hitboxesVis), getX()+157,getY()+10);
        object(Hitbox.class, new Hitbox(100, 30, false, 50, hitboxesVis), getX()+156,getY()-38);
        object(Hitbox.class, new Hitbox(100, 30, false, 50, hitboxesVis), getX()+155,getY()-82);
        object(Hitbox.class, new Hitbox(100, 30, false, 50, hitboxesVis), getX()+115,getY()-57);
        object(Hitbox.class, new Hitbox(100, 30, false, 50, hitboxesVis), getX()+108,getY()-13);
        object(Hitbox.class, new Hitbox(100, 30, false, 50, hitboxesVis), getX()+116,getY()+33);
        } else if (!player && imageLeft[1]) {
            object(Hitbox.class, new Hitbox(100, 30, false, 50, hitboxesVis), getX()+201,getY()-103);
        object(Hitbox.class, new Hitbox(100, 30, false, 50, hitboxesVis), getX()-199,getY()-60);
        object(Hitbox.class, new Hitbox(100, 30, false, 50, hitboxesVis), getX()-196,getY()-14);
        object(Hitbox.class, new Hitbox(100, 30, false, 50, hitboxesVis), getX()-194,getY()+34);
        object(Hitbox.class, new Hitbox(100, 30, false, 50, hitboxesVis), getX()-192,getY()+76);
        object(Hitbox.class, new Hitbox(100, 30, false, 50, hitboxesVis), getX()-152,getY()+60);
        object(Hitbox.class, new Hitbox(100, 30, false, 50, hitboxesVis), getX()-157,getY()+10);
        object(Hitbox.class, new Hitbox(100, 30, false, 50, hitboxesVis), getX()-156,getY()-38);
        object(Hitbox.class, new Hitbox(100, 30, false, 50, hitboxesVis), getX()-155,getY()-82);
        object(Hitbox.class, new Hitbox(100, 30, false, 50, hitboxesVis), getX()-115,getY()-57);
        object(Hitbox.class, new Hitbox(100, 30, false, 50, hitboxesVis), getX()-108,getY()-13);
        object(Hitbox.class, new Hitbox(100, 30, false, 50, hitboxesVis), getX()-116,getY()+33);
        }
    }
    private void checkForReset() {
        if (Greenfoot.isKeyDown("r") && (HP[0] <= 0 || HP[1] <= 0)) {
            ((MyWorld)getWorld()).resetBattle();
        }
    }
    private void facedGroovin() {
        if (checkForHT() && ((player && chargeCounter[0] == 0) || (!player && chargeCounter[1] == 0))) {
        if ((Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left"))) {
            if (player) {
                if (Greenfoot.isKeyDown("a") && x[0]>=0) {
                    if (iFrames[0] > 1) {
                        x[0] -= 5;
                    } else {
                        x[0] -= 3;
                    }
                    if (!imageLeft[0]) {
                GreenfootImage lt = new GreenfootImage("IMG_1958y.png");
                setImage(lt);
                imageLeft[0] = true;
                imageRight[0] = false;
                }
                }
            }
            if (!player) {
                if (Greenfoot.isKeyDown("left")&&x[1]>=0) {
                     if (iFrames[1] > 1) {
                        x[1] -= 5;
                    } else {
                        x[1] -= 3;
                    }
                     if (!imageLeft[1]) {
                GreenfootImage lt = new GreenfootImage("IMG_1958y.png");
                setImage(lt);
                imageLeft[1] = true;
                imageRight[1] = false;
                }
                }
            }
            
        }
         if ((Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right"))) {
            if (player) {
                if (Greenfoot.isKeyDown("d") && x[0]<=1200) {
                    if (iFrames[0] > 1) {
                        x[0] += 5;
                    } else {
                        x[0] += 3;
                    }
                    if (!imageRight[0]) { 
            GreenfootImage rt = new GreenfootImage("IMG_1958rt.png");
            setImage(rt);
            imageRight[0] = true;
            imageLeft[0] = false;
            }
                }
            }
            if (!player) {
                if (Greenfoot.isKeyDown("right")&&x[1]<=1200) {
                    if (iFrames[1] > 1) {
                        x[1] += 5;
                    } else {
                        x[1] += 3;
                    }
                     if (!imageRight[1]) { 
            GreenfootImage rt = new GreenfootImage("IMG_1958rt.png");
            setImage(rt);
            imageRight[1] = true;
            imageLeft[1] = false;
            }
                }
            }
            
            
        }
         if ((Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up"))) {
            if (player) {
                if (Greenfoot.isKeyDown("w") && y[0]>=0) {
                     if (iFrames[0] > 1) {
                        y[0] -= 5;
                    } else {
                        y[0] -= 3;
                    }
                }
            }
            if (!player) {
                if (Greenfoot.isKeyDown("up")&&y[0]>=0) {
                     if (iFrames[1] > 1) {
                        y[1] -= 5;
                    } else {
                        y[1] -= 3;
                    }
                }
            }
        }
         if ((Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("down"))) {
            if (player) {
                if (Greenfoot.isKeyDown("s") && y[0]<=800) {
                    if (iFrames[0] > 1) {
                        y[0] += 5;
                    } else {
                        y[0] += 3;
                    }
                }
            }
            if (!player) {
                if (Greenfoot.isKeyDown("down")&&y[0]<=800) {
                     if (iFrames[1] > 1) {
                        y[1] += 5;
                    } else {
                        y[1] += 3;
                    }
                }
            }
        }
        if (player) {
            setLocation(x[0],y[0]);
        }
        if (!player) {
            setLocation(x[1],y[1]);
        }
    }
    }
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Mover.gameMode == 1 || Mover.gameMode == 3) {
            if (Mover.gameMode ==3) {
                player2ASDF[0] = "1";
                player2ASDF[1] = "2";
                player2ASDF[2] = "3";
                player2ASDF[3] = "4";
            }
            if (Greenfoot.isKeyDown(player1ASDF[0]) && Greenfoot.isKeyDown(player1ASDF[1])) {
                htFrames[0] = 100;
            }
                if (Greenfoot.isKeyDown(player2ASDF[0]) && Greenfoot.isKeyDown(player2ASDF[1])) {
                htFrames[1] = 100;
            }
            if (Greenfoot.isKeyDown(player1ASDF[1]) && Greenfoot.isKeyDown(player1ASDF[2])) {
                htFrames[0] = 100;
            }
            if (Greenfoot.isKeyDown(player2ASDF[1]) && Greenfoot.isKeyDown(player2ASDF[2])) {
                htFrames[0] = 100;
            }
                facedGroovin();
            if ((player && htFrames[0] <= 1) || (!player && htFrames[1] <= 1)) {
                if ((player && iFrames[0] <= 1) || (!player && iFrames[1] <= 1)) {
                    attack(true);
                }
                shield();
            }
            
            manageHT();
            displayHealth();
            checkForInvin();
            displayFreeze();
            checkForHit();
            checkForWin();
            checkForSettings();
            checkForReset();
        }
    }
}
