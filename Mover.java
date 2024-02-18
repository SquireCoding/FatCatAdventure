import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * 
 * Author: BenDowty
 * Version: See MyWorld version for game version
 */
public abstract class Mover extends Actor
{
    static boolean tutorial = true;
    static int moveOn = 0;
    static long miniMoveOn = 0;
    static boolean doFalse = false;
    static boolean admin = false;
    static int tips = 0;
    static String attackCounter;
    boolean pauseWorld = false;
    static boolean askingForBattle = false;
    static boolean hard = false;
    static boolean easy = false;
    public static int gameMode = 1;
    /**
     * Slowly shrinks the actor out of existince.
     */
    public void shrinkOutOfExistince() {
        GreenfootImage img = new GreenfootImage(getImage());
        img.scale(img.getWidth()/2, img.getHeight()/2);
        if (img.getWidth() <= 10 || img.getHeight() <= 10) {
            getWorld().removeObject(this);
        } else {
            setImage(img);
        }
    }
    public void moveTowards(int x, int y, int speed) {
        int xx = getX();
        int yy = getY();
        if (getX() > x) {
            xx -= speed;
        } else if (getX() < x) {
            xx += speed;
        }
        if (getY() > y) {
            yy -= speed;
        } else if (getY() < y) {
            yy += speed;
        }
        setLocation(xx,yy);
    }
    /**
     * Whenever "X" is pressed, the world is unpaused and the text box closed.
     */
    public void closeTextBox() {
        if (Greenfoot.isKeyDown("x") && !FatCat.isDead && !Cats.finalCutscene) {
            ((MyWorld)getWorld()).myShowText(null, 600, 675);
            getWorld().removeObjects(getWorld().getObjects(TextBox.class));
            pauseWorld = false;
        }
    }
    /**
     * Does the tutorial.
     * n : How many act cycles happen between each line of text.
     */
    public void doTutorial(int n) {
        closeTextBox();
            if (moveOn == 0 && tutorial) {
            miniMoveOn++;
            if (miniMoveOn == 1) {
                say("???: Hi, everybody!");
            }
            if (miniMoveOn == n) {
                say("???: Welcome to the game/final project.");
            }
            if (miniMoveOn == n*2) {
                
            if (Greenfoot.isKeyDown(";")) {
                        Mover.admin = true;
                    FatCat.needToAdd[0] = true;
                    } 
                say("???: I'm Fat Cat. I will be the player in this game.");
            }
            if (miniMoveOn == n*3) {
                say("Fat Cat: So, do you want the tutorial? (Hint: use Y and N to answer)");
                if (Greenfoot.isKeyDown("h")) {
                    Mover.hard = true;
                    FatCat.needToAdd[1] = true;
                } else if (Greenfoot.isKeyDown("e")) {
                    Mover.easy = true;
                    FatCat.needToAdd[2] = true;
                    FatCat.HP = 200;
                } else {
                    FatCat.needToAdd[3] = true;
                }
                moveOn++;
            }
        }
            if (Greenfoot.isKeyDown("n") && moveOn == 1) {
                doFalse = true;
                
            }
            if (doFalse) {
                miniMoveOn++;
                say("Fat Cat: Oh well. Suit yourself.");
                if (miniMoveOn == n*4) {
                    tutorial = false;
                moveOn = 0;
                noAskClose();
                doFalse = false;
                getWorld().removeObjects(getWorld().getObjects(Barrier.class));
                    FatCat.barrierHere[0] = false;
                }
            }
            if (Greenfoot.isKeyDown("y") && moveOn == 1) {
                say("Fat Cat: Great! Let me show you the basics.");
                moveOn++;
            }
            if (moveOn == 2) {
                miniMoveOn++;
                if (miniMoveOn == n*4) {
                    say("Fat Cat: First up: Closing text boxes. That is the bubble-like object that this text is within.");
                }
                if (miniMoveOn == n*5) {
                    say("Fat Cat: You can't move or anything while a text box is there, so you need to know how to close it.");
                }
                if (miniMoveOn == n*6) {
                    say("Fat Cat: Close text boxes by pressing X. Try it!");
                    moveOn++;
                }
            }
            if (moveOn == 3 && getWorld().getObjectsAt(600, 675, TextBox.class).size() == 0) {
                moveOn++;
            }
            if (moveOn == 4) {
                miniMoveOn++;
                if (miniMoveOn == n*7) {
                    say("Fat Cat: Perfect!");
                }
                if (miniMoveOn == n*8) {
                    say("Fat Cat: Alrighty, next is moving.");
                }
                if (miniMoveOn == n*9) {
                    say("Fat Cat: There isn't a lot of moving in this game, but there is a little bit, so you need to know it.");
                }
                if (miniMoveOn == n*10) {
                    say("Fat Cat:Side note: You may have noticed the red barriers. Don't try to walk through those.");
                }
                if (miniMoveOn == n*11) {
                    say("Fat Cat: First, close the text box. Then use WASD or Arrow Keys to move.");
                    moveOn++;
                }
            }
            if ((this.getX() != 600 || this.getY() != 400) && moveOn == 5) {
                moveOn++;
            }
            if (moveOn == 6) {
                miniMoveOn++;
                if (miniMoveOn == n*12) {
                    say("Fat Cat: Great Job!");
                }
                if (miniMoveOn == n*13) {
                    say("Fat Cat: Next up: talking with other actors.");
                }
                if (miniMoveOn == n*14) {
                    say("Fat Cat: There isn't a lot of talking in this game, but there is a little bit, so you need to know it.");
                }
                if (miniMoveOn == n*15) {
                    say("Fat Cat: You can talk with other characters by walking up and pressing z.");
                }
                if (miniMoveOn == n*16) {
                    say("Fat Cat: Walk to the next area and talk with the penguin.");
                    getWorld().removeObjects(getWorld().getObjects(Barrier.class));
                    moveOn++;
                }
            }
            if (moveOn == 8) {
                miniMoveOn++;
                if (miniMoveOn == n*17) {
                    say("Fat Cat: Nice.");
                }
                if (miniMoveOn == n*18) {
                    say("Fat Cat: OKAY, the next topic is battling. It's kinda the gist of the game.");
                }
                if (miniMoveOn == n*19) {
                    say("Fat Cat: Some actors will start to battle with you if you try to talk to them.");
                }
                if (miniMoveOn == n*20) {
                    say("Fat Cat: They're tense like that.");
                }
                if (miniMoveOn == n*21) {
                    say("Fat Cat: Here is a menu you will get when it's your turn in a battle.");
                    if (getX() >= 600) {
                        getWorld().addObject(new TurnMenu(), 1000, 400);
                    }
                    if (getX() < 600) {
                        getWorld().addObject(new TurnMenu(), 200, 400);
                    }
                }
                if (miniMoveOn == n*22) {
                    say("Fat Cat: I feel like this is pretty self-explanatory.");
                }
                if (miniMoveOn == n*23) {
                    say("Fat Cat: But just in case there is further confusion...");
                }
                if (miniMoveOn == n*24) {
                    say("Fat Cat: Use the keys on the right to do the action of the left during a battle.");
                }
                if (miniMoveOn == n*25) {
                    getWorld().removeObjects(getWorld().getObjects(TurnMenu.class));
                    say("Fat Cat: alrighty, just a few things left.");
                }
                if (miniMoveOn == n*26) {
                    say("Fat Cat: You can see in the top left the HP and the \"10/10\".");
                }
                if (miniMoveOn == n*27) {
                    say("Fat Cat: The HP is this: HP reaches 0, you die, and the game ends.");
                }
                if (miniMoveOn == n*28) {
                    say("Fat Cat: At that point, you can either reset, or load a save.");
                }
                if (miniMoveOn == n*29) {
                    say("Fat Cat: I'll explain that later.");
                }
                if (miniMoveOn == n*30) {
                    say("Fat Cat: The '10/10' is your fishes.");
                }
                if (miniMoveOn == n*31) {
                    if (Mover.hard) {
                        say("Fat Cat: During a battle, you can eat a fish and gain 30 HP back.");
                    } else if (Mover.easy) {
                        say("Fat Cat: During a battle, you can eat a fish and gain 70 HP back.");
                    } else {
                        say("Fat Cat: During a battle, you can eat a fish and gain 50 HP back.");
                    }
                }
                if (miniMoveOn == n*32) {
                    say("Fat Cat: You can only eat them if you have some, though,");
                }
                if (miniMoveOn == n*33) {
                    say("Fat Cat: Alright, there are only two things left to go over.");
                }
                if (miniMoveOn == n*34) {
                    say("Fat Cat: If you look in the top right corner, you will see a \"Save\" button.");
                }
                if (miniMoveOn == n*35) {
                    say("Fat Cat: Whenever you press this button, it will save your game.");
                }
                if (miniMoveOn == n*36) {
                    say("Fat Cat: And whenever you press the Load button, it will load that save.");
                }
                if (miniMoveOn == n*37) {
                    say("Fat Cat: But it will spawn you in the first sector every time you load.");
                }
                if (miniMoveOn == n*38) {
                    say("Fat Cat: Whelp, I think that's it. Go enjoy the game!");
                }
                if (miniMoveOn == n*39) {
                    say("01000001 01101101 01101111 01100111 01110101 01110011");
                    tutorial = false;
                    Greenfoot.delay(20);
                    ((MyWorld)getWorld()).myShowText(null, 600, 675);
            getWorld().removeObjects(getWorld().getObjects(TextBox.class));
            getWorld().removeObjects(getWorld().getObjects(Barrier.class));
            pauseWorld = false;
            FatCat.barrierHere[0] = false;
                }
            }
            
    }
    /**
     * Whenever you are touching a actor and pressing "Z", a text box will pop up with text.
     */
    public void talk() {
        if ((isTouching(FatCat.class) || isTouching(RandCat.class) ||isTouching(Penguin.class) || isTouching(Turtle.class) || isTouching(DrSteve.class)) && Greenfoot.isKeyDown("z") && !pauseWorld) {
            if (isTouching(FatCat.class)) {
                say("Fat Cat: Hey! I'm Fat Cat. Wait a minute, how'd you get two of me? there's only supposed to be one!");
            }
            if (isTouching(DrSteve.class)) {
                say("Dr Steve: This will be the final battle you face.");
                Mover.askingForBattle = true;
            }
            if (isTouching(RandCat.class)) {
                if (Cats.battleNumber == 1) {
                    say("Random Cat: Hey! Let's fight!");
                Mover.askingForBattle = true;
                } else if (tips == 0) {
                    say("Random Cat: I know when I'm beat. If you need fighting tips, come back and talk to me.");
                    tips = 1;
                }
                if (tips == 5) {
                    say("Tandom Cat: The \"R\" in my name is replaced with a \"T\".");
                    tips = 1;
                }
                if (tips == 4) {
                    say("Random Cat: If you get lucky, one of your fish can be extra good and give you more health!");
                    Greenfoot.delay(100);
                    say("Random Cat: Unfortuanetly, it can work in reverse where enemies will heal themselves.");
                    tips++;
                }
                if (tips == 3) {
                    say("Random Cat: Sometimes if you get lucky, you can get a random crit for extra damage!");
                    tips++;
                }
                if (tips == 2) {
                    say("Random Cat: Sometimes if you get lucky, your enemy can miss and not hit you!");
                    tips++;
                }
                if (tips == 1) {
                    say("Random Cat: There are three people to fight: Me, the Turtle, and Him.");
                    tips++;
                }
            }
            if (isTouching(Turtle.class)) {
                if (Cats.battleNumber == 2) {
                    say("Turtle: I have escaped and shall be tricked no more!");
                    Mover.askingForBattle = true;
                } else if (Cats.battleNumber == 1) {
                    say("Turtle: hacker");
                } else if (Cats.battleNumber > 2) {
                    say("Turtle: You may have beat me, but I'm nothing compared to the doctor.");
                }
            }
            if (isTouching(Penguin.class)) {
                if (tutorial) {
                    say("Penguin: Hello, mr cat!");
                if (moveOn == 7) {
                    moveOn++;
                }
                } else if (moveOn > 7 && tutorial) {
                    say("Fat Cat: We don't need to talk to him right now.");
                } else if (Mover.hard && FatCat.fishes > 4) {
                    say("Penguin: You have enough fishes already.");
                } else if (((RandCat.HP <= 0 || RandCat.HP == 100) && (Turtle.HP <= 0 || Turtle.HP == 300) && (DrSteve.HP <= 0 || DrSteve.HP == 400)) || Mover.easy) {
                    say("Penguin: Hello, my good sir! Here, have some fishes.");
                    FatCat.fishes = 10;
                } else {
                    say("Penguin: I think you're in the middle of fighting someone. Come back later.");
                }
                }
            }
        }
    /**
     * Saves the game automatically.
     */
        public void autoSave() {
            Save.fishes = FatCat.fishes;
            Save.HP = FatCat.HP;
            Save.x = FatCat.x;
            Save.drsteveHP = DrSteve.HP;
            Save.y = FatCat.y;
            Save.RandCatHP = RandCat.HP;
            Save.imageLeft = FatCat.imageLeft;
            Save.imageRight = FatCat.imageRight;
            Save.overhealProtect = FatCat.overhealProtect;
            Save.turtleHP = Turtle.HP;
            Save.battleNumber = Cats.battleNumber;
            for (int i = 0; i<=2; i++) {
                Save.barrierHere[i] = FatCat.barrierHere[i];
            }
            Save.saveAvailable = true;
            say("Autosave successful.");
    }
    /**
     * Closes the text box and unpauses the world without asking.
     */
    public void noAskClose() {
        ((MyWorld)getWorld()).myShowText(null, 600, 675);
            getWorld().removeObjects(getWorld().getObjects(TextBox.class));
            pauseWorld = false;
    }
    /**
     * This method pauses the world, adds a text box, and puts what you want in it.
     * z : The String of what you want to display in the text box.
     */
    public void say(String z) {
            pauseWorld = true;
            if (getWorld().getObjects(TextBox.class).size() == 0) {
                getWorld().addObject(new TextBox(), 600, 675);
            }
            ((MyWorld)getWorld()).myShowText(z, 600, 675);
    }
}
