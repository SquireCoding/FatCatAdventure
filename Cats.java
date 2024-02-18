 import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Author: BenDowty
 * Version: See MyWorld version for game version
 */
public abstract class Cats extends Mover
{
    int d = 0;
    int fd = 0;
    static boolean cutScening = false;
    static boolean fatCatTurn = true;
    boolean turnKeep = true;
    static int phase = 1;
    static boolean battlin = false;
    static int y = 0;
    static int movin = 0;
    static boolean fled = false;
    static int battleNumber = 1;
    static boolean finalCutscene = false;
    /**
     * Activates a battle and runs it until it is finished.
     */
    public void startBattle() {
int fatCatChances = Greenfoot.getRandomNumber(17);
int fatCatDamage = Greenfoot.getRandomNumber(11);
int enemyChances = Greenfoot.getRandomNumber(17);
checkForWin(battleNumber);
if (!askingForBattle) {
            fled = false;
        }
if (askingForBattle) {
            setLocation(200, 400);   
        doOnce();
        checkForWin(battleNumber);
        checkForLost();
        doTurn(fatCatChances, fatCatDamage, battleNumber);
        adminHealth();
        testForCutscene(enemyChances);
}
}
/**
 * Tests to see if the midfight cutscene happens yet.
 * enemyChances : if the cutscene doesn't happen yet, then it will use a turn instead. enemyChances is the enemy's chances for that turn.
 */
private void testForCutscene(int enemyChances) {
    if (battleNumber == 3 && Cats.phase <= 2 && !fatCatTurn && DrSteve.HP <= 200) {
            doCutscene(200);
        } else {
            testForEnemyAttack(enemyChances);
        }
}
/**
 * This method does the midfight cutscene.
 * n : how many act cycles happen between each line of text.
 */
private void doCutscene(int n) {
    if (phase == 1) {
        cutScening = true;
        movin++;
        if (movin == 1) {
        say("Dr Steve: I feel like this is slightly unbalanced.");
    }
    if (movin == n*1) {
        say("Fat Cat: No, you just have bad stats.");
    }
    if (movin == n*2) {
        say("Dr Steve: I have an idea for how to do this, using my MocBook.");
        getWorld().addObject(new MocBook(), 650, 400);
    }
    if (movin == n*3) {
        getWorld().removeObjects(getWorld().getObjects(MocBook.class));
        say("Dr Stevie: Perfect.");
        DrSteve.hacking = true;
    }
    if (movin == n*4) {
        doEnemyAttack(16, Cats.battleNumber);
        if (Mover.easy && FatCat.HP <= 0) {
            FatCat.HP = 25;
        } else if (!Mover.hard && FatCat.HP <= 0) {
            FatCat.HP = 1;
        }
    }
    if (movin == n*5) {
        say("Fat Cat: Owch! You can't crit on purpose!");
    }
    if (movin == n*6) {
        if (Mover.admin) {
            say("Dr Steve:big talk from a cat that can press n and do the same thing");
        } else {
            say("Dr Steve: Realistically, I can do it as long as I want.");
        }
    }
    if (movin == n*7) {
        say("Fat Cat: I have an idea! Next time he pulls out the computer, click it!");
    }
    if (movin == n*8) {
        say("Dr Steve: I'm just going to do it again.");
    }
    if (movin == n*9) {
        getWorld().addObject(new MocBook(), 650, 400);
        say("Fat Cat: Now!");
        phase++;
    }
    }
    if (phase == 2 && MocBook.cutScened) {
        movin++;
        if (movin == n*10) {
            say("Dr Steve: What? Where did it go?");
        }
        if (movin == n*11) {
            say("Fat Cat: I got rid of it. Whenever you see him get out the computer, delete it!");
            phase++;
            cutScening = false;
            movin = 0;
            fatCatTurn = true;
        }
    }
}
/**
 * If you have admin status, this method will reset your health during a battle.
 */
private void adminHealth() {
    if (Greenfoot.isKeyDown("b") && Mover.admin) {
        FatCat.HP = 150;
        say("Fat Cat reset his health using cheats!");
        usedTurn();
    }
}
/** 
 * If you have admin status and you need to test something that involves missing, this method will give you a 100% chance of it.
 */
private void adminMiss() {
    if (Greenfoot.isKeyDown("m") && Mover.admin) {
        doAttack(12, 10, battleNumber);
        usedTurn();
    }
}
/**
 * If you have admin status and you need to test something that involves critical hits, this method will give you a 100% chance of it.
 */
private void adminCrit() {
    if (Greenfoot.isKeyDown("n") && Mover.admin) {
        doAttack(16, 10, battleNumber);
        usedTurn();
    }
}
/**
 * This method does a few things only once at the beginning of each battle.
 */
private void doOnce() {
    if (y == 0) {
            battlin = true;
            fatCatTurn = true;
            fled = false;
            y = 1;
        }
}
/**
 * This method tests if it is time for the enemy to do their attack yet. If it is, then it does an attack.
 * enemyChances : it passes this variable to doEnemyAttack. (doEnemyAttack(enemyChances)).
 */
private void testForEnemyAttack(int enemyChances) {
    if (Mover.easy) {
        if (!fatCatTurn && Mover.askingForBattle) {
          if (phase == 3) {
              if (!fatCatTurn && !cutScening) {
                  if (getWorld().getObjectsAt(650, 400, MocBook.class).size() == 0 && MocBook.allowedToHack[1] == false) {
                  getWorld().addObject(new MocBook(), 650, 400);
              }
              }
              if (MocBook.allowedToHack[1]) {
                  getWorld().removeObjects(getWorld().getObjects(MocBook.class));
                  if (MocBook.allowedToHack[0]) {
                      doEnemyAttack(16, battleNumber);
                  } else if (!MocBook.allowedToHack[0]) {
                      doEnemyAttack(enemyChances, battleNumber);
                  }
                  fatCatTurn = true;
                  MocBook.allowedToHack[1] = false;
              }
          } else {
              doEnemyAttack(enemyChances, battleNumber);
                  fatCatTurn = true;
          }
        }
    } else if (Mover.hard) {
        if (!fatCatTurn && Mover.askingForBattle) {
          if (phase == 3) {
              
              if (!fatCatTurn && !cutScening) {
                  int x = Greenfoot.getRandomNumber(1000);
              int y = Greenfoot.getRandomNumber(481);
                  if (getWorld().getObjects(MocBook.class).size() == 0 && MocBook.allowedToHack[1] == false) {
                  getWorld().addObject(new MocBook(), x + 100, y);
              }
              }
              if (MocBook.allowedToHack[1]) {
                  getWorld().removeObjects(getWorld().getObjects(MocBook.class));
                  if (MocBook.allowedToHack[0]) {
                      doEnemyAttack(16, battleNumber);
                  } else if (!MocBook.allowedToHack[0]) {
                      doEnemyAttack(enemyChances, battleNumber);
                  }
                  fatCatTurn = true;
                  MocBook.allowedToHack[1] = false;
              }
          } else {
              doEnemyAttack(enemyChances, battleNumber);
                  fatCatTurn = true;
          }
        }
    } else {
        if (!fatCatTurn && Mover.askingForBattle) {
          if (phase == 3) {
              if (!fatCatTurn && !cutScening && !MocBook.allowedToHack[1] && getWorld().getObjectsAt(650, 400, MocBook.class).size() == 0 && getWorld().getObjectsAt(1100, 400, MocBook.class).size() == 0) {
                  int r = Greenfoot.getRandomNumber(3);
                  if (getWorld().getObjectsAt(650, 400, MocBook.class).size() == 0 && r == 1) {
                  getWorld().addObject(new MocBook(), 650, 400);
              }
              if (getWorld().getObjectsAt(1100, 400, MocBook.class).size() == 0 && r == 2) {
                  getWorld().addObject(new MocBook(), 1100, 400);
              }
              }
              if (MocBook.allowedToHack[1]) {
                  getWorld().removeObjects(getWorld().getObjects(MocBook.class));
                  if (MocBook.allowedToHack[0]) {
                      doEnemyAttack(16, battleNumber);
                  } else if (!MocBook.allowedToHack[0]) {
                      doEnemyAttack(enemyChances, battleNumber);
                  }
                  fatCatTurn = true;
                  MocBook.allowedToHack[1] = false;
              }
          } else {
              doEnemyAttack(enemyChances, battleNumber);
                  fatCatTurn = true;
          }
        }
    }
    
}
/**
 * This method does a turn from the player.
 * It passes fatCatChances, fatCatDamage, and battleNumber to various kinds of turn options.
 */
private void doTurn(int fatCatChances, int fatCatDamage, int battleNumber) {
    if (fatCatTurn && !FatCat.isDead) {
        if (getWorld().getObjects(TurnMenu.class).size() == 0) {
            getWorld().addObject(new TurnMenu(), 500, 300);
        }
        testForAttack(fatCatChances, fatCatDamage, battleNumber);
        testForAdmin();
        adminMiss();
        adminCrit();
        testForFish(fatCatChances, battleNumber);
        testForPass();
        testForFlee(fatCatChances);
        resetTurnKeep();
        }
}
/**
 * this method tests to see if the player attacks in their turn.
 * fatCatChances, fatCatDamage, battleNumber : see doAttack, all these variables are passed to it.
 */
private void testForAttack(int fatCatChances, int fatCatDamage, int battleNumber) {
    if (Greenfoot.isKeyDown("a") && turnKeep && fatCatTurn) {
          doAttack(fatCatChances, fatCatDamage, battleNumber);
          usedTurn();
        }
}
/**
 * This method tests to see if the player uses a fish.
 * fatCatChances, battleNumber: See doFish, passes these variables to it.
 */
private void testForFish(int fatCatChances, int battleNumber) {
    if (Greenfoot.isKeyDown("s") && turnKeep && fatCatTurn) {
          doFish(fatCatChances, battleNumber);
        }
}
/**
 * Tests if the player, is admin, then automatically kills the current enemy instantly.
 */
private void testForAdmin() {
    if (Greenfoot.isKeyDown("j") && Greenfoot.isKeyDown("k") && Greenfoot.isKeyDown("l") && fatCatTurn && turnKeep && Mover.admin) {
                say("Fat Cat used admin commands! What a cheater!");
                if (Cats.battleNumber == 1) {
                    RandCat.HP -= 150;
                }
                if (Cats.battleNumber == 2) {
                    Turtle.HP -= 300;
                }
                if (Cats.battleNumber == 3) {
                    DrSteve.HP -= 400;
                }
                usedTurn();
            }
}
/**
 * Resets the turnkeep variable, which keeps the player from holding down a button to spam turns.
 */
private void resetTurnKeep() {
    if (!Greenfoot.isKeyDown("a") && !Greenfoot.isKeyDown("s") && !Greenfoot.isKeyDown("d") && !Greenfoot.isKeyDown("f") && !Greenfoot.isKeyDown("n") ) {
            turnKeep = true;
        }
}
/**
 * Tests to see if the player passes their turn.
 */
private void testForPass() {
    if (Greenfoot.isKeyDown("d") && turnKeep && fatCatTurn) {
            say("Fat Cat did nothing.");
            usedTurn();
            }
}
/**
 * Tests to see if the player flees on their turn.
 * fatCatChances : see tryToFlee, passes this to it.
 */
private void testForFlee(int fatCatChances) {
    if (Greenfoot.isKeyDown("f") && turnKeep && fatCatTurn) {
        tryToFlee(fatCatChances);            
        }
}
/**
 * Attempts to flee in normal mode, automatically flees in easy mode, deactivates flee for hard mode.
 * fatCatChances : The chances that FatCat successfully flees in normal mode.
 */
private void tryToFlee(int fatCatChances) {
   if (Mover.easy) {
       fled();
    } else if (Mover.hard) {
       say("You can't flee.");
   } else {
       if (fatCatChances<=10) {
           fled();
            }
       if (fatCatChances>10) {
            say("Fat Cat tried to flee, but couldn't!");
            usedTurn();
            }
   }
}
/**
 * tryToFlee calls this whenever a flee is successful.
 */
private void fled() {
    say("Fat Cat fled!");
                Greenfoot.delay(100);
                battlin = false;
            pauseWorld = false;
            y = 0;
            getWorld().removeObjects(getWorld().getObjects(TurnMenu.class));
            Mover.askingForBattle = false;
}
/**
 * Checks to see if FatCat has won the current battle, and in easy mode, autosaves after one has won.
 * battleNumber : determines which battle to do the winning scene for.
 */
private void checkForWin(int  battleNumber) {
    boolean won = false;
    if (battleNumber == 1) {
        if (RandCat.HP <= 0) {
            won = true;
        }
    }
    if (battleNumber == 2) {
        if (Turtle.HP <= 0) {
            won = true;
        }
    }
    if (won) {
        Greenfoot.delay(100);    
        say("You Won!");
            battlin = false;
            pauseWorld = false;
            y = 0;
            FatCat.barrierHere[battleNumber] = false;
            getWorld().removeObjects(getWorld().getObjects(Barrier.class));
            ((MyWorld)getWorld()).myShowText(null, 600, 100);
            getWorld().removeObjects(getWorld().getObjects(TurnMenu.class));
            Mover.askingForBattle = false;
            Cats.battleNumber++;
            if (Mover.easy) {
                Greenfoot.delay(100);
                autoSave();
            }
    }
    if (battleNumber == 3 && DrSteve.HP <= 0) {
        movin++;
        Mover.askingForBattle = false;
        finalCutscene = true;
        cutScening = true;
        pauseWorld = true;
        if (movin == 200) {
            say("Dr Steve: You have completed the final challenge and proved your worth.");
            phase++;
        }
        if (movin == 400) {
            if (Mover.admin) {
                say("Dr Steve: I doubt you did it legitimately, though.");
            } else if (Mover.hard && !Mover.admin) {
                say("Dr Steve: I am quite impressed by your skill.");
            }
        }
        if (Mover.hard && !Mover.admin) {
            if (movin == 600) {
                say("Dr Steve: You have completed the challenge.");
            }
            if (movin == 800) {
                say("Dr Steve: Your prize is... the coveted admin password.");
            }
            if (movin == 1000) {
                say("Dr Steve: During the opening, hold down " + MocBook.a + Save.a + Mover.attackCounter);
            }
            if (movin == 1200) {
                say("Dr Steve: Well, that's it.");
            }
            if (movin == 200*7) {
            getWorld().removeObjects(getWorld().getObjects(MocBook.class));
            say("Fat Cat: Well, I think the game is done.");
        }
        if (movin == 200*8) {
            say("Fat Cat: Thanks, for playing, and leave a good review/grade on your way out!");
        }
        if (movin >= 200*9) {
            say("Fat Cat: Goodbye!");
            Greenfoot.stop();
        }
        } else {
            if (movin == 200*3) {
            say("Dr Steve: I do have one final tactic, though!");
        }
        if (movin == 200*4) {
            say("Dr Steve: All I need is to...");
            getWorld().addObject(new MocBook(), 550, 400);
        }
        if (movin == 200*5) {
            say("MocBook: Update required.");
        }
        if (movin == 200*6) {
            say("Dr Steve: Nooooooooooooooooooooooooooooooooooooo");
            DrSteve.needToGo = true;
        }
        if (movin == 200*7) {
            getWorld().removeObjects(getWorld().getObjects(MocBook.class));
            say("Fat Cat: Well, I think the game is done.");
        }
        if (movin == 200*8) {
            say("Fat Cat: Thanks, for playing, and leave a good review/grade on your way out!");
        }
        if (movin >= 200*9) {
            say("Fat Cat: Goodbye!");
            Greenfoot.stop();
        }
        }
    }
}
/**
 * Checks to see if FatCat has lost, and then takes the neccesary steps to do what is needed next.
 */
private void checkForLost() {
    if (FatCat.HP <= 0 && !cutScening) {
        lost();
    }
}
/**
 * lost() is called by checkForLost whenever FatCat's HP is less or equal to zero.
 */
private void lost() {
        battlin = false;
        Mover.askingForBattle = false;
        FatCat.isDead = true;
        getWorld().removeObjects(getWorld().getObjects(TurnMenu.class));
        if (Save.saveAvailable) {
            say("You lost! But you can Load your Save file!");
        } else if (!Save.saveAvailable) {
            say("You lost, and you have no Save file to Load! Better luck next time.");
            Greenfoot.stop();
        }
}
/** 
 * A more functional version of shrinkOutOfExistince().
 */
public void realShrinkOutOfExistince() {
    GreenfootImage img = new GreenfootImage(getImage());
    while (img.getHeight() > 10 && img.getWidth() > 10) {
        img.scale(img.getHeight()/2, img.getWidth()/2);
    }
    if (img.getHeight() <= 10 || img.getWidth() <= 10) {
        getWorld().removeObject(this);
    }
    setImage(img);
}
/**
 * This removes the turn menu, changes the turn, and checks to see if the player has won.
 */
private void usedTurn() {
    getWorld().removeObjects(getWorld().getObjects(TurnMenu.class));
    fatCatTurn = false;
    turnKeep = false;
    checkForWin(battleNumber);
}
/**
 * Does an attack.
 * fatCatChances : The chances that FatCat will crit, miss, or hit.
 * fatCatDamage : The extra damage FatCat does.
 * battleNumber : this determines which enemy to subtract HP from.
 */
private void doAttack(int fatCatChances, int fatCatDamage, int battleNumber) {
          if (Mover.easy) {
              int d = 0;
              turnKeep = false;
            if (fatCatChances <= 12) {
              d = 14 + fatCatDamage;
              say(Integer.toString(d) + " damage done!");
          }
          if (fatCatChances > 13) {
              d = 70 + fatCatDamage;
              say("Critical Hit! " + Integer.toString(d) + " damage done!");
          }
          if (battleNumber == 1) {
              RandCat.HP -= d;
          }
          if (battleNumber == 2) {
              Turtle.HP -= d;
          }
          if (battleNumber == 3) {
              DrSteve.HP -= d;
          }
          } else if (Mover.hard) {
              int d = 0;
              turnKeep = false;
            if (fatCatChances <= 9) {
              d = 8 + fatCatDamage;
              say(Integer.toString(d) + " damage done!");
          }
          if (fatCatChances > 10 && fatCatChances <= 14) {
              d = 0;
              say("Miss!");
          }
          if (fatCatChances >= 15) {
              d = 50 + fatCatDamage;
              say("Critical Hit! " + Integer.toString(d) + " damage done!");
          }
          if (battleNumber == 1) {
              RandCat.HP -= d;
          }
          if (battleNumber == 2) {
              Turtle.HP -= d;
          }
          if (battleNumber == 3) {
              DrSteve.HP -= d;
          }
          } else {
              int d = 0;
          turnKeep = false;
            if (fatCatChances <= 10) {
              d = 13 + fatCatDamage;
              say(Integer.toString(d) + " damage done!");
          }
          if (fatCatChances > 10 && fatCatChances <= 14) {
              d = 0;
              say("Miss!");
          }
          if (fatCatChances >= 15) {
              d = 65 + fatCatDamage;
              say("Critical Hit! " + Integer.toString(d) + " damage done!");
          }
          if (battleNumber == 1) {
              RandCat.HP -= d;
          }
          if (battleNumber == 2) {
              Turtle.HP -= d;
          }
          if (battleNumber == 3) {
              DrSteve.HP -= d;
          }
          }
}
/**
 * Does an enemy attack.
 * enemyChances : the chances an enemy will crit, hit, or miss.
 * battleNumber : this determines which enemy to act as.
 */
private void doEnemyAttack(int enemyChances, int battleNumber) {
    if (Mover.easy) {
        if (Mover.askingForBattle) {
    if (phase != 3) {
        Greenfoot.delay(100);
    }
    int fd = 0;
    if (battleNumber == 2) {
            if (enemyChances <= 10) {
              fd = 1;
              say("Turtle did " + Integer.toString(fd) + " damage!");
          }
          if (enemyChances >= 11 && enemyChances <= 14) {
              fd = 0;
              say("Turtle missed!");
          }
          if (enemyChances >= 15) {
              fd = 90;
              say("Critical Hit! Turtle did " + Integer.toString(fd) + " damage!");
          }
          FatCat.HP = FatCat.HP - fd;
          fatCatTurn = true;
    } else {
        if (enemyChances <= 7) {
              if (battleNumber == 1) {
                  fd = 8;
              say("Random Cat did " + Integer.toString(fd) + " damage!");
              }
              if (battleNumber == 3) {
                  int dsf = Greenfoot.getRandomNumber(11);
                  fd = 6 + dsf;
                  say("Dr Steve did " + Integer.toString(fd) + " damage!");
              }
              
          }
        if (enemyChances > 7 && enemyChances <= 15) {
              if (battleNumber == 1) {
                  say("Random Cat missed!");
              }
              if (battleNumber == 3) {
                 say("Dr Steve missed!"); 
              }
          }
        if (enemyChances == 16) {
              if (battleNumber == 1) {
                  fd = 30;
              say("Critical Hit! Random Cat did " + Integer.toString(fd) + " damage!");
              }
              if (battleNumber == 3) {
                  int dsf = Greenfoot.getRandomNumber(11);
                  fd = 20 + dsf;
                  say("Critical Hit! Dr Steve did " + Integer.toString(fd) + " damage!");
              }
          }
          FatCat.HP -= fd;
    }
    if (!cutScening) {
        checkForLost();
    }
}        
    } else if (Mover.hard) {
        if (Mover.askingForBattle) {
    if (phase != 3) {
        Greenfoot.delay(100);
    }
    int fd = 0;
    if (battleNumber == 2) {
            if (enemyChances <= 10) {
              fd = 2;
              say("Turtle did " + Integer.toString(fd) + " damage!");
          }
          if (enemyChances == 11) {
              fd = 0;
              say("Turtle missed!");
          }
          if (enemyChances >= 12) {
              fd = 125;
              say("Critical Hit! Turtle did " + Integer.toString(fd) + " damage!");
          }
          FatCat.HP = FatCat.HP - fd;
          fatCatTurn = true;
    } else {
        if (enemyChances <= 8) {
              if (battleNumber == 1) {
                  fd = 12;
              say("Random Cat did " + Integer.toString(fd) + " damage!");
              }
              if (battleNumber == 3) {
                  int dsf = Greenfoot.getRandomNumber(11);
                  fd = 12 + dsf;
                  say("Dr Steve did " + Integer.toString(fd) + " damage!");
              }
              
          }
        if (enemyChances > 8 && enemyChances <= 12) {
              if (battleNumber == 1) {
                  say("Random Cat missed!");
              }
              if (battleNumber == 3) {
                 say("Dr Steve missed!"); 
              }
          }
        if (enemyChances >= 13) {
              if (battleNumber == 1) {
                  fd = 50;
              say("Critical Hit! Random Cat did " + Integer.toString(fd) + " damage!");
              }
              if (battleNumber == 3) {
                  int dsf = Greenfoot.getRandomNumber(11);
                  fd = 55 + dsf;
                  say("Critical Hit! Dr Steve did " + Integer.toString(fd) + " damage!");
              }
          }
          FatCat.HP -= fd;
    }
    if (!cutScening) {
        checkForLost();
    }
}
    } else {
        if (Mover.askingForBattle) {
    if (phase != 3) {
        Greenfoot.delay(100);
    }
    int fd = 0;
    if (battleNumber == 2) {
            if (enemyChances <= 10) {
              fd = 2;
              say("Turtle did " + Integer.toString(fd) + " damage!");
          }
          if (enemyChances > 10 && enemyChances <= 12) {
              fd = 0;
              say("Turtle missed!");
          }
          if (enemyChances >= 13) {
              fd = 125;
              say("Critical Hit! Turtle did " + Integer.toString(fd) + " damage!");
          }
          FatCat.HP = FatCat.HP - fd;
          fatCatTurn = true;
    } else {
        if (enemyChances <= 10) {
              if (battleNumber == 1) {
                  fd = 10;
              say("Random Cat did " + Integer.toString(fd) + " damage!");
              }
              if (battleNumber == 3) {
                  int dsf = Greenfoot.getRandomNumber(11);
                  fd = 10 + dsf;
                  say("Dr Steve did " + Integer.toString(fd) + " damage!");
              }
              
          }
        if (enemyChances > 10 && enemyChances <= 14) {
              if (battleNumber == 1) {
                  say("Random Cat missed!");
              }
              if (battleNumber == 3) {
                 say("Dr Steve missed!"); 
              }
          }
        if (enemyChances >= 15) {
              if (battleNumber == 1) {
                  fd = 50;
              say("Critical Hit! Random Cat did " + Integer.toString(fd) + " damage!");
              }
              if (battleNumber == 3) {
                  int dsf = Greenfoot.getRandomNumber(11);
                  fd = 55 + dsf;
                  say("Critical Hit! Dr Steve did " + Integer.toString(fd) + " damage!");
              }
          }
          FatCat.HP -= fd;
    }
    checkForLost();
}
    }
    
}
/**
 * Used a fish if FatCat has one, otherwise it tells him he has none.
 * fatCatChances : the chances that determine whether FatCat gets a normal fish, gets a super-fish, or the enemy gets health.
 * battleNumber : this determines which enemy to give HP when the enemy gets health.
 */
private void doFish(int fatCatChances, int battleNumber) {
    if (Mover.easy) {
        if (FatCat.fishes > 0) {
          FatCat.fishes--;
          if (fatCatChances <= 11) {
              FatCat.HP += 70;
              if (FatCat.HP <= 200) {
                  ((MyWorld)getWorld()).myShowText(Integer.toString(FatCat.HP) + " HP", 100, 100);
              } else if (FatCat.HP > 200 && FatCat.overhealProtect) {
                  FatCat.HP = 200;
                  ((MyWorld)getWorld()).myShowText(Integer.toString(FatCat.HP) + " HP", 100, 100);
              }
              say("Fat Cat ate one fish! +70 HP");
          }
          if (fatCatChances < 11) {
              FatCat.overhealProtect = false;
              FatCat.HP += 200;
              ((MyWorld)getWorld()).myShowText(Integer.toString(FatCat.HP) + " HP", 100, 100);
              say("Fat Cat ate one fish! It was suprisingly good! +200 HP!");
              
          }
          usedTurn();
          } else {
              say("You don't have any fishes to eat!");
          }
    } else if (Mover.hard) {
        if (FatCat.fishes >= 1) {
          FatCat.fishes--;
          if (fatCatChances < 10) {
              FatCat.HP += 30;
              if (FatCat.HP <= 130) {
                  ((MyWorld)getWorld()).myShowText(Integer.toString(FatCat.HP) + " HP", 100, 100);
              } else if (FatCat.HP > 130  && FatCat.overhealProtect) {
                  FatCat.HP = 130;
                  ((MyWorld)getWorld()).myShowText(Integer.toString(FatCat.HP) + " HP", 100, 100);
              }
              say("Fat Cat ate one fish! +30 HP");
          }
           if (fatCatChances > 10 && fatCatChances < 16) {
               if (battleNumber == 1) {
                   FatCat.HP -= 10;
              ((MyWorld)getWorld()).myShowText(Integer.toString(FatCat.HP) + " HP", 100, 100);
              say("Fat Cat ate a sour fish! Ugh! 10 damage taken!");
              
               }
              if (battleNumber == 2) {
                  say("Turtle: I'll take one of your fish!");
              Turtle.HP += 25;
              }
              if (battleNumber == 3) {
                  say("\"cancelMove();\"");
                  Greenfoot.delay(100);
                  say("\"Dr Steve HP + 40;\"");
                  DrSteve.HP += 40;
              }
          }
          if (fatCatChances == 16) {
              FatCat.overhealProtect = false;
              FatCat.HP += 130;
              ((MyWorld)getWorld()).myShowText(Integer.toString(FatCat.HP) + " HP", 100, 100);
              say("Fat Cat ate one fish! It was suprisingly good! +150 HP!");
              
          }
          usedTurn();
          } else {
              say("You don't have any fishes to eat! What a pity!");
          }
    } else {
        if (FatCat.fishes > 0) {
          FatCat.fishes--;
          if (fatCatChances < 12) {
              FatCat.HP += 50;
              if (FatCat.HP <= 150) {
                  ((MyWorld)getWorld()).myShowText(Integer.toString(FatCat.HP) + " HP", 100, 100);
              } else if (FatCat.HP > 150 && FatCat.overhealProtect) {
                  FatCat.HP = 150;
                  ((MyWorld)getWorld()).myShowText(Integer.toString(FatCat.HP) + " HP", 100, 100);
              }
              say("Fat Cat ate one fish! +50 HP");
          }
           if (fatCatChances == 13 || fatCatChances== 14) {
              if (battleNumber == 1) {
                   FatCat.HP -= 10;
                  ((MyWorld)getWorld()).myShowText(Integer.toString(FatCat.HP) + " HP", 100, 100);
                  say("Fat Cat ate a sour fish! Ugh! 10 damage taken!");
              
              }
              if (battleNumber == 2) {
                  say("Turtle: I'll take one of your fish!");
                  Turtle.HP += 25;
              }
              if (battleNumber == 3) {
                  say("\"cancelMove();\"");
                  Greenfoot.delay(100);
                  say("\"Dr Steve HP + 40;\"");
                  DrSteve.HP += 40;
              }
          }
          if (fatCatChances == 15 || fatCatChances == 16) {
              FatCat.overhealProtect = false;
              FatCat.HP += 150;
              ((MyWorld)getWorld()).myShowText(Integer.toString(FatCat.HP) + " HP", 100, 100);
              say("Fat Cat ate one fish! It was suprisingly good! +150 HP!");
          }
          usedTurn();
          } else {
              say("You don't have any fishes to eat!");
          }
    }
    
}
    /**
     * Act - do whatever the Cats wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        
    }
}
