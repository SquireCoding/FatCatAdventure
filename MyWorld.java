import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * 
 * @BenDowty
 * 
 * @Current Version on this device: 1.1.5
 * @Version 1.2.1 {
 *     Publish Date: 5/23/22
 *     
 *     @Features: Added a battle mode that isn't turn based combat
 *     You can activate this by changing 'Mover.battleMode' to true in the world's constructor
 *          Within this battle mode you can:
 *              As p1:
 *                  z to do a slash
 *                  x to shield
 *                  c to use a shield popper
 *                  v to fire a chargeable rocket
 *              As p2:
 *                  m to do a slash
 *                  , to shield
 *                  . to use a shield popper
 *                  / to fire a chargeable rocket
 *              You can also press y to change any of your controls
 }
 * 
 * @Version 1.1.5 {
 *     Publish Date: 5/12/22
 *     @Patch Notes:
 *      Fixed a glitch where fatCat would give incorrect information about fish and various things
 }
 * 
 * 
 * @Version 1.1.4 {
 *     Publish Date: 5/9/22
 *     @Patch Notes:
 *          Fixed a glitch where the final cutscene wouldn't happen
 *          Fixed a glitch that where  DrSteve could be talking even though he wasn't on screen during the final cutscene
 *      
 *     @Balance Changes:
 *          MocBook dissapearing time changed from 50 frames to 100 frames in hard mode
 *          MocBook dissapearing time changed from 200 frames to 300 frames in normal mode
 }
 * 
 * @Version 1.1.3 {
 *     @Patch Notes:
 *     Fixed a glitch where you could load a save from a previous attempt, regardless of difficulty
 }
 * 
 * @Version 1.1.2 Notes: {
 *     Publish Date: 5/7/22
 *     Method Documentation is FINISHED 
 *     Constructor Documentation is done too
 *     
 *     @Balance Changes:
 *          In 'Hard' mode, in the DrSteve fight, you get one click to hit the MocBook; if you click anywhere except the mocbook it dissapears
 }
 * 
 * 
 * @Version 1.1.1 Notes: {
 *     Publish Date: 5/6/22
 *     @Patch Notes:
 *      Fixed a glitch where FatCat would get glued to a wall after walking into it
 *      Fixed a glitch that made it so if you died during DrSteve's cutscene, the game would softlock
 *      Fixed a glitch where you could move with the dead cat sprite and softlock yourself in battle
 *      Fixed a glitch where using a fish when you had none would still use your turn
 *      Fixed a glitch where using a fish when you were overhealed would delete health
 *      Fixed a glitch where you could still battle after death
 *      Fixed a glitch that caused infinite mocbooks to spawn in DrSteve fight on easy mode
 *     
 *     @Balance Changes:
 *     On 'Easy' Mode, the game autosaves
 *     If you are in 'Hard' Mode and you have more than 4 fish, the penguin will not restock your fishes
 *     If you die during a cutscene, you now will only be given 1 health
 *     If you die during a cutscene on easy mode, you now will only be given 25 health
 *     You can now die during cutscenes on hard mode
 *     You can only save 8 times total on hard mode
 }
 * 
 * 
 * @Version 1.1.0 Notes: {
 *     Publish Date: 5/6/22
 *  @Patch Notes:
 *      Fixed a glitch where Random Cat would do double damage
 *      Fixed a glitch where going into Turtle's sector from DrSteve's side would cause him to dissapear
 * 
 *  @Balance Changes:
 *      DrSteve does more damage
 *      MocBook can spawn on either side of DrSteve during second phase
 *      You cannot die during cutscenes. If your health goes below zero during a cutscene, you will be given 10 health
 *      If you try to activate both Hard and Easy in the opening, Hard will now override easy
 *  
 *  @Features:
 *      Added a 'Hard' Difficulty Where: (Activate hard difficulty by holding "h" during the opening scene)
 *          DrSteve does more damage
 *          Turtle does more damage
 *          RandCat does more damage
 *          DrSteve chance of missing is lower
 *          Turtle chance of missing is lower
 *          RandCat chance of missing is lower
 *          DrSteve chance of critting is higher
 *          Turtle chance of critting is higher
 *          RandCat chance of critting is higher
 *          FatCat does less damage
 *          FatCat chance of missing is higher
 *          FatCat chance of critting is lower
 *          FatCat chance of Bad Fish (enemy gets health) is higher
 *          FatCat chance of good fish (+150 hp rather than +50) is lower
 *          FatCat's fish heal 30 HP
 *          FatCat HP is capped at 130, with overheal max being 260
 *          FatCat cannot flee battles
 *          MocBook dissapears 4x faster in DrSteve (dissapears after about 5/6 of a second)
 *          MocBook can spawn anywhere on the whole screen
 *          If you manage to beat this without admin, DrSteve will reveal the admin passcode in the end.
 *  
 *      Added an 'Easy' Difficulty Where: 
 *          DrSteve does less damage
 *          Turtle does less damage
 *          RandCat does less damage
 *          DrSteve chance of missing is higher
 *          Turtle chance of missing is higher
 *          RandCat chance of missing is higher
 *          DrSteve chance of critting is lower
 *          Turtle chance of critting is lower
 *          RandCat chance of critting is lower
 *          FatCat does more damage
 *          FatCat chance of missing is lower
 *          FatCat chance of critting is higher
 *          FatCat chance of Bad Fish (enemy gets health) is lower
 *          FatCat chance of good fish (+150 hp rather than +50) is higher
 *          FatCat's fish heal 70 HP
 *          FatCat HP is capped at 200, with overheal max being 400
 *          FatCat when a flee is attempted, is is successful 100% of the time
 *          MocBook dissapears 3x slower in DrSteve (dissapears after about 10 seconds)
 *          MocBook spawns only on the left of DrSteve
 *        
 }
 * @Version 1.0.0 Notes:{
 *     Publish Date: 5/5/22
 *     Game is finished!
 }
 *
 */
public class MyWorld extends World
{
    /**
     * Overrides showText by showing a black text instead of white. Other than that, completely identical to showText.
     */
    public void myShowText(String s, int x, int y) {
        if (s == null) {
            removeObjects(getObjectsAt(x, y, Text.class));
        }
        if (getObjectsAt(x, y, Text.class) != null) {
            removeObjects(getObjectsAt(x, y, Text.class));
        }
        if (s != null) {
            addObject(new Text(s), x, y);
        }
    }
    public void myShowText(String s, int x, int y, Color c) {
        if (s == null) {
            removeObjects(getObjectsAt(x, y, Text.class));
        }
        if (getObjectsAt(x, y, Text.class) != null) {
            removeObjects(getObjectsAt(x, y, Text.class));
        }
        if (s != null) {
            addObject(new Text(s, c), x, y);
        }
    }
    public void resetBattle() {
        removeObjects(getObjects(Player.class));
        removeObjects(getObjects(RandCatRocket.class));
        addObject(new Player(true), 100, 100);
        addObject(new Player(false), 600, 100);
        myShowText(null, 600, 400);
    }
    /**
     * Resets random variables.
     */
    public MyWorld(String j)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1);
        String o = j;
        if (!o.equals("1") && !o.equals("2") && !o.equals("3")) {
            try {
                throw new Exception("GamemodeNotWithinBounds");
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        Mover.gameMode = Integer.parseInt(o);
        if (Mover.gameMode == 1) {
            removeObjects(getObjects(null));
            addObject(new Player(true), 300, 400);
            addObject(new Player(false), 900, 400);
        } else if(Mover.gameMode == 3) {
            removeObjects(getObjects(null));
            addObject(new Player(true), 300, 400);
            addObject(new BadT(),900,400);
            BadT.killCount = 0;
        }else if (Mover.gameMode == 2) {
            FatCat.HP = 150;
        FatCat.s = 0;
        FatCat.fishes = 10;
        RandCat.HP = 100;
        Mover.askingForBattle= false;
        RandCat.lockInPlace = false;
        Cats.y = 0;
        addObject(new FatCat(600, 400), 600, 400);
        Mover.tutorial = true;
        Mover.moveOn = 0;
        Mover.miniMoveOn = 0;
        Mover.doFalse = false;
        Cats.battlin = false;
        addObject(new Save(), 1070, 50);
        addObject(new Load(), 1070, 150);
        FatCat.barrierHere[0] = true;
        FatCat.barrierHere[1] = true;
        FatCat.barrierHere[2] = true;
        FatCat.isDead = false;
        Cats.battleNumber = 1;
        Turtle.HP = 300;
        Save.savesLeft = 8;
        FatCat.overhealProtect = true;
        Cats.finalCutscene = false;
        Mover.tips = 0;
        Mover.hard = false;
        Mover.attackCounter = "l";
        Load.a = null;
        Mover.admin = false;
        FatCat.mo = 0;
        Mover.easy = false;
        for (int i = 0; i <= 3; i++) {
            FatCat.needToAdd[i] = false;
        }
        DrSteve.needToGo = false;
        Cats.movin = 0;
        FatCat.needToAdd[0] = false;
        Save.saveAvailable = false;
        DrSteve.HP = 400;
        Cats.phase = 1;
        Save.canSave = true;
        Cats.battleNumber = 1;
        if (FatCat.barrierHere[0]) {
            addObject(new Barrier(), 1200, 400);
        }
        }
        
    }
    public MyWorld() {
        this("2");
}
}
