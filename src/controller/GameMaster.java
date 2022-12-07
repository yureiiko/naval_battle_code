package controller;

import modele.Game;
import view.ConsoleDisplay;

import java.io.EOFException;
import java.io.IOException;
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
    public GameMaster() throws ClassNotFoundException, IOException {
        try {
            navalBattle = new Game().load("save/gamesave");
        } catch (EOFException e) {
            navalBattle = new Game();
        }
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
    public boolean gamerPlay(boolean cheat) throws IOException {
        if (cheat) {
            displayer.display("BOT GRID :\n"+navalBattle.getPlayerGrid(1).toString()+"\n");
        }
        displayer.display(navalBattle.getPlayerGrid(0)+"\n\n");
        displayer.display(navalBattle.getEnemyGrid(1)+"\n");
        displayer.display("Enter a negative or higher than 15 value to quit\n");
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
            displayer.display("\nYou quit the game\n");
            navalBattle.save("save/gamesave");
            return true;
        }
        return false;
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
    public String letsPlay(boolean cheat) throws IOException {
        String out = navalBattle.check();
        while (out == null) {
            if (gamerPlay(cheat)) {
                return "quit";
            }
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
        System.out.println("Rules\na)Indicate the coordinate of the rival's boxe you want to shoot\nb)If you hit a ship it will show 'hit' otherwise 'miss'\n");
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
     * Method askCheat
     * @return boolean
     * Ask to the player if he want to play the cheat mode
     */
    public boolean askCheat() {
        System.out.println("Do you want to use the cheat mode where you can see the enemy battlefield (y: yes / n : no) :");
        String awnser = this.scan.nextLine();
        switch (awnser) {
            case "y" : return true;
            default: return false;
        }
    }

    /**
     * Method main
     * @param args String []
     */
    public static void main(String [] args) throws IOException, ClassNotFoundException {
        GameMaster gm = new GameMaster();
        if (gm.start()) {
            long beg = System.currentTimeMillis();
            String out = gm.letsPlay(gm.askCheat());
            switch (out) {
                case "quit" : return;
                default: gm.getDisplayer().display("The "+out+" win !!");
            }
            long end = System.currentTimeMillis();
            gm.getDisplayer().display("\nDuration : "+(end-beg)/6000+" minutes");
        }
    }

}
