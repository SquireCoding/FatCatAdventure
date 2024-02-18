import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Author: BenDowty
 * Version: See MyWorld version for game version
 */
public class FatCat extends Cats
{
    static int fishes = 10;
    static int HP = 150;
    static int x = 600;
    static int y = 400;
    static boolean setImage = false;
    static boolean imageLeft = false;
    static boolean imageRight = true;
    static boolean needToAdd[] = new boolean[4];
    static boolean isDead = false;
    static boolean shouldRun = false;
    static boolean overhealProtect = true;
    static int s = 0;
    static int mo = 0;
    static final String a = null;
    static boolean[] barrierHere = new boolean[3];
    static final boolean barrierHere3 = true;
    static int[] timer = new int[3];
    /**
     * Default Constructor.
     */
    public FatCat() {
        
    }
    /**
     * Intialize FatCat's location.
     * x  : starting x.
     * y : starting y.
     */
    public FatCat(int x, int y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Checks whether FatCat needs to add text.
     */
    private void needToAdd() {
        if (needToAdd[0]) {
            if (getWorld().getObjectsAt(550, 100, Text.class).size() == 0) {
                getWorld().addObject(new Text("Admin Priviledges Unlocked", Text.green), 550, 100);
            }
        }
        if (needToAdd[1]) {
            if (getWorld().getObjectsAt(150, 150, Text.class).size() == 0) {
                getWorld().addObject(new Text("Hard Mode On", Text.red), 150, 150);
            }
        }
        if (needToAdd[2]) {
            if (getWorld().getObjectsAt(150, 150, Text.class).size() == 0) {
                getWorld().addObject(new Text("Easy Mode On", Text.green), 150, 150);
            }
        }
        if (needToAdd[3]) {
            if (getWorld().getObjectsAt(150, 150, Text.class).size() == 0) {
                getWorld().addObject(new Text("Normal Difficulty On"), 150, 150);
            }
        }
    }
    /**
     * Updates FatCat's health in the top left.
     */
    private void updateTextFC() {
        if (FatCat.HP > 0) {
            ((MyWorld)getWorld()).myShowText(Integer.toString(FatCat.HP) + " HP", 100, 100);
            ((MyWorld)getWorld()).myShowText(Integer.toString(fishes) + "/10", 200, 100);
            if (getWorld().getObjectsAt(275, 100, FishModel.class).size() == 0) {
                getWorld().addObject(new FishModel(), 275, 100);
            }
        } else {
            if (getWorld().getObjectsAt(275, 100, FishModel.class) != null) {   
            getWorld().removeObjects(getWorld().getObjects(FishModel.class));
        }
            ((MyWorld)getWorld()).myShowText(null, 100, 100);
            ((MyWorld)getWorld()).myShowText(null, 200, 100);
        }
        }
    /**
     * Checks is FatCat is overhealed.
     * n : Set FatCat's default health.
     */
    public static void checkForOverheal(final int n) {
        if (FatCat.HP > n && overhealProtect) {
            FatCat.HP = n;
        }
        if (FatCat.HP < n && !overhealProtect) {
            overhealProtect = true;
        }
        if (FatCat.HP > n*2 && !overhealProtect) {
            FatCat.HP = n*2;
        }
    }
    /**
     * Determines whichOverheal (hint, hint) to use based on difficulty.
     */
    private void whichOverheal() {
        if (Mover.hard) {
            checkForOverheal(130);
        } else if (Mover.easy) {
            checkForOverheal(200);
        } else {
            checkForOverheal(150);
        }
    }
    /**
     * Makes sure FatCat stays dead after he dies.
     */
    private void stayDead() {
        if (FatCat.HP <= 0 && !Save.saveAvailable) {
            mo++;
            if (mo >= 2) {
                Greenfoot.stop();
            Save.canSave = false;
            }
        }
    }
    /**
     * Keeps other classes' HP from appearing when they aren't on screen.
     */
    private void keepScreenClean() {
        if (getWorld().getObjects(RandCat.class).size() == 0) {
            ((MyWorld)getWorld()).myShowText(null, 600, 100);
        }
        if (getWorld().getObjects(Turtle.class).size() == 0) {
            ((MyWorld)getWorld()).myShowText(null, 601, 100);
        }
        if (getWorld().getObjects(DrSteve.class).size() == 0) {
            ((MyWorld)getWorld()).myShowText(null, 850, 100);
        }
    }
    /**
     * Checks whether FatCat needs to equip the right cat image after being brought back from the dead.
     */
    private void setTheImage() {
        if (FatCat.setImage) {
            this.setImage(new GreenfootImage("IMG_1958rt.png"));
            setImage = false;
        }
    }
    /**
     * Basically move(), but all directions, and the image changes based on direction.
     */
    private void facedGroovin() {
        if (!(getWorld().getObjects(TextBox.class).size() != 0)) {
        if ((Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left")) && x>=0) {
            if ((Greenfoot.isKeyDown("a") && Greenfoot.isKeyDown("left"))) {
                x = x - 6;
            } else if (Mover.admin && Greenfoot.isKeyDown("l")) {
                x = x-30;
            } else {
                x -= 3;
            }
            
            if (!imageLeft) {
                GreenfootImage lt = new GreenfootImage("IMG_1958y.png");
                setImage(lt);
                imageLeft = true;
                imageRight = false;
                }
        }
         if ((Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right")) && x <= 1200) {
            if ((Greenfoot.isKeyDown("d") && Greenfoot.isKeyDown("right"))) {
                x=x+6;
            } else if (Mover.admin && Greenfoot.isKeyDown("l")) {
                x = x+30;
            } else {
                x += 3;
            }
            
            if (!imageRight) { 
            GreenfootImage rt = new GreenfootImage("IMG_1958rt.png");
            setImage(rt);
            imageRight = true;
            imageLeft = false;
            }
        }
         if ((Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up")) && y >= 0) {
            if ((Greenfoot.isKeyDown("w") && Greenfoot.isKeyDown("up"))) {
                y -= 6;
            } else if (Mover.admin && Greenfoot.isKeyDown("l")) {
                y -= 30;
            } else {
                y -= 3;
            }
        }
         if ((Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("down")) && y <= 800) {
            if (((Greenfoot.isKeyDown("s") && Greenfoot.isKeyDown("down")))) {
                y += 6;
            } else if (Mover.admin && Greenfoot.isKeyDown("l")) {
                y += 30;
            } else {
                y += 3;
            }
        }
        setLocation(x, y);
    }
    }
    /**
     * Sets the image to a dead fat cat when he dies.
     */
       public void gameOver() {
        if (FatCat.HP <= 0) {
            GreenfootImage myImage = new GreenfootImage("IMG_2007x.png");
            setImage(myImage);
            isDead = true;
        }
    }
    /**
     * Knocks back FatCat 100 pixels whenever he touches a barrier.
     */
    private void knockbackBarrier() {
        if (isTouching(Barrier.class)) {
            x -= 100;
        }
    }
    /**
     * Changes areas based on where FatCat is.
     */
    private void enterSector() {
        if (x >= 1200 && s<4) {
            x = 100;
            s++;
            getWorld().removeObjects(getWorld().getObjects(DrSteve.class));
            getWorld().removeObjects(getWorld().getObjects(Penguin.class));
            getWorld().removeObjects(getWorld().getObjects(RandCat.class));
            getWorld().removeObjects(getWorld().getObjects(Turtle.class));
            getWorld().removeObjects(getWorld().getObjects(Barrier.class));
            if (s==1) {
                getWorld().addObject(new Penguin(), 600, 200);
                if (barrierHere[0]) {
                    getWorld().addObject(new Barrier(), 1200, 400);
                }
            }
            if (s==2) {
                getWorld().addObject(new RandCat(), 800, 500);
                if (barrierHere[1]) {
                    getWorld().addObject(new Barrier(), 1200, 400);
                }
            }
            if (s==3) {
                getWorld().addObject(new Turtle(), 800, 500);
                if (barrierHere[2]) {
                    getWorld().addObject(new Barrier(), 1200, 400);
                }
            }
            if (s==4) {
                getWorld().addObject(new DrSteve(), 800, 400);
                if (barrierHere3) {
                    getWorld().addObject(new Barrier(), 1200, 400);
                }
            }
        }
        if (x <= 0 && s>=0 && s!= 0) {
            x = 1100;
            s--;
            getWorld().removeObjects(getWorld().getObjects(DrSteve.class));
            getWorld().removeObjects(getWorld().getObjects(Penguin.class));
            getWorld().removeObjects(getWorld().getObjects(RandCat.class));
            getWorld().removeObjects(getWorld().getObjects(Turtle.class));
            getWorld().removeObjects(getWorld().getObjects(Barrier.class));
            if (s==1) {
                getWorld().addObject(new Penguin(), 600, 200);
                if (barrierHere[0]) {
                    getWorld().addObject(new Barrier(), 1200, 400);
                }
            }
            if (s==2) {
                getWorld().addObject(new RandCat(), 800, 500);
                if (barrierHere[1]) {
                    getWorld().addObject(new Barrier(), 1200, 400);
                }
            }
            if (s==3) {
                getWorld().addObject(new Turtle(), 800, 500);
                if (barrierHere[2]) {
                    getWorld().addObject(new Barrier(), 1200, 400);
                }
            }
        }
    }
    /**
     * Act - do whatever FatCat wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Mover.gameMode == 2) {
            facedGroovin();
        talk();
        gameOver();
        updateTextFC();
        whichOverheal();
        doTutorial(100);
        closeTextBox();
        knockbackBarrier();
        enterSector();
        startBattle();
        setTheImage();
        stayDead();
        keepScreenClean();
        needToAdd();
        }
    }
}
