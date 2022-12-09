package controller;

import modele.Game;
import view.ConsoleDisplay;

import java.io.EOFException;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
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
     * Method gamerPlay : In this method, the human player choose a boat and a place to shoot. Return true if he want to quit the game.
     */
    public boolean gamerPlay(boolean cheat) throws IOException {
        if (cheat) {
            displayer.display("BOT GRID :\n"+navalBattle.getPlayerGrid(1).toString()+"\n");
        }
        displayer.display(navalBattle.getPlayerGrid(0)+"\n\n");
        displayer.display(navalBattle.getEnemyGrid(1)+"\n");
        displayer.display("Enter a non boat ID, negative or higher than 15 value to quit\n");
        displayer.display("\nEnter the NAME "+navalBattle.getPlayerGrid(0).validBoat()+" : ");
        String boat = scan.next();
        if (navalBattle.getPlayerGrid(0).validBoat().contains(boat)) {
            displayer.display("Enter the target column : ");
            int targCol = scan.nextInt();
            displayer.display("Enter the target line : ");
            int targLine = scan.nextInt();
            if (targCol > 0 && targCol < 15 && targLine > 0 && targLine < 15) {
                System.out.println("\nGood coordinates\n");
                boolean res;
                if (boat.equals("B")) {
                    res = navalBattle.firebattelship(1, targCol - 1, targLine - 1);
                } else if (boat.equals("C")) {
                    res = navalBattle.firecruiser(1, targCol - 1, targLine - 1);
                } else {
                    res = navalBattle.fire(1, targCol - 1, targLine - 1);
                }
                if (res) {
                    displayer.display("\nHIT :D ! \n");
                } else {
                    displayer.display("\nMiss :( \n");
                }
            } else {
                System.out.println("\nNot a coor\n");
                return exitGame();
            }
        } else {
            System.out.println("\nNot a boat\n");
            return exitGame();
        }
        return false;
    }

    /**
     * Method botPlay : In this method, the bot player choose randomly a boat and a place to shoot
     */
    public void botPlay() throws IOException {
        ArrayList<String> valid = navalBattle.getPlayerGrid(1).validBoat();
        boolean res;
        int n = new Random().nextInt(valid.size());
        int line = new Random().nextInt(14);
        int col = new Random().nextInt(14);
        if (valid.get(n)=="B") {
            displayer.display("The bot chose to fire with Battleship at : ");
            displayer.display((line + 1) + ";" + (col + 1) + "\n");
            res = navalBattle.firebattelship(0, col - 1, line - 1);
        } else if (valid.get(n)=="C") {
            displayer.display("The bot chose to fire with Cruiser at : ");
            displayer.display((line + 1) + ";" + (col + 1) + "\n");
            res = navalBattle.firecruiser(0, col - 1, line - 1);
        } else if (valid.get(n)=="D") {
            displayer.display("The bot chose to fire with Destroyer at : ");
            displayer.display((line + 1) + ";" + (col + 1) + "\n");
            res = navalBattle.fire(0, col - 1, line - 1);
        } else {
            displayer.display("The bot chose to fire with Submarine at : ");
            displayer.display((line + 1) + ";" + (col + 1) + "\n");
            res = navalBattle.fire(0, col - 1, line - 1);
        }
        if (res) {
            displayer.display("and HIT\n");
        } else {
            displayer.display("and miss\n");
        }
    }

    /**
     * Method letsPlay : Bot and human player play one at a time. If cheat is true, the player can see the bot's battlefield.
     * @return String
     */
    public String letsPlay(boolean cheat) throws IOException {
        String out = navalBattle.check();
        while (out == null) {
            if (gamerPlay(cheat)) {
                return "quit";
            }
            this.botPlay();
            out = navalBattle.check();
            scan=scan.reset();
        }
        return out;
    }

    /**
     * Method exitGame : Permit to quit
     * @return boolean
     */
    public boolean exitGame() {
        displayer.display("\nYou quit the Game\n");
        return true;
    }

    /**
     * Method start : Display rules and return true if the player want to continue
     * @return boolean
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
     * Method askCheat : Ask to the player if he want to play the cheat mode and see the bot's battlefield.
     * @return boolean
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
