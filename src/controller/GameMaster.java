package controller;

import modele.Game;
import view.ConsoleDisplay;

import java.sql.Time;
import java.util.Random;
import java.util.Scanner;

/**
 * Class GameMaster
 * @author FLEURY CALAIS Camille
 */
public class GameMaster {

    private Game navalBattle;
    private Scanner scan;
    private ConsoleDisplay displayer;

    /**
     * Constructor
     */
    public GameMaster() {
        navalBattle = new Game();
        scan = new Scanner(System.in);
        displayer = new ConsoleDisplay();
    }

    /**
     * Method getDisplayer
     * @return ConsoleDisplay
     */
    public ConsoleDisplay getDisplayer() {
        return displayer;
    }

    /**
     * Method gamerPlay
     * In this method, the human player choose a place to shoot
     */
    public void gamerPlay(boolean cheat){
        if (cheat) {
            displayer.display("BOT GRID :\n"+navalBattle.getPlayerGrid(1).toString()+"\n");
        }
        displayer.display(navalBattle.getPlayerGrid(0)+"\n\n");
        displayer.display(navalBattle.getEnemyGrid(1)+"\n");
        ini:
            try {
                displayer.display("\nEnter the target column : ");
                int col = scan.nextInt();
                displayer.display("\nEnter the target line : ");
                int line = scan.nextInt();
                if (navalBattle.fire(1, col-1, line-1)) {
                    displayer.display("HIT :D !\n");
                } else {
                    displayer.display("miss :( \n");
                }
            } catch (IndexOutOfBoundsException e) {
                displayer.display("\nEnter a number between 1 and 15\n");
                break ini;
            }
    }

    /**
     * Method botPlay
     * In this method, the bot player choose randomly a place to shoot
     */
    public void botPlay() {
        ini :
            try {
                displayer.display("The bot fire at : ");
                int line = new Random().nextInt(14);
                int col = new Random().nextInt(14);
                displayer.display((line+1)+";"+(col+1)+"\n");
                if (navalBattle.fire(0, col-1, line-1)) {
                    displayer.display("and HIT\n");
                } else {
                    displayer.display("and miss\n");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break ini;
            }
    }

    /**
     * Method letsPlay
     * @return String
     * Bot and human player play one at a time
     */
    public String letsPlay(boolean cheat) {
        String out = navalBattle.check();
        while (out == null) {
            this.gamerPlay(cheat);
            this.botPlay();
            out = navalBattle.check();
        }
        return out;
    }

    /**
     * Method start
     * @return boolean
     * Display rules and return true if the player want to continue
     */
    public boolean start(){
        String x;

        Scanner sc =new Scanner(System.in);
        System.out.println("--------------------Game : BattleShip--------------------\n");
        System.out.println("You will have 2 grids, one that is yours the other one is the rival's grid\n");
        System.out.println("You will have 4 ships\n1) 1 Battleship:B:7 boxes\t2)Cruiser:C:5 boxes\t3)Destroyer:D:3boxes\t4)Sub-marine:S:1 boxe\n");
        System.out.println("Rules\na)Indicate the coordinate of the rival's boxe you want to shot\nb)If you hit a ship it will show 'hit' otherwise 'miss'\n");
        System.out.println("In you other grid you will see where the rival hit\n");
        System.out.println("The goal of the game is to hit every ships of the rival before he hit yours\n");
        System.out.println("If you want to play enter 'y' otherwise enter 'n'\n" );
        x=sc.next();
        switch (x){
            case "y": return true;
            default  : return false;
        }

    }

    /**
     * Method main
     * @param args String []
     */
    public static void main(String [] args) {
        GameMaster gm = new GameMaster();
        if (gm.start()) {
            long beg = System.currentTimeMillis();
            gm.getDisplayer().display("The "+gm.letsPlay(false)+" win !!");
            long end = System.currentTimeMillis();
            gm.getDisplayer().display("\nDuration : "+(end-beg)/6000+" minutes");
        }
    }

}
