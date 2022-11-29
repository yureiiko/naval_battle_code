package controller;

import modele.Game;
import view.ConsoleDisplay;

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
     */
    public void gamerPlay(){
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
     */
    public String letsPlay() {
        String out = navalBattle.check();
        while (out == null) {
            this.gamerPlay();
            this.botPlay();
            out = navalBattle.check();
        }
        return out;
    }

    /**
     * Method main
     * @param args String []
     */
    public static void main(String [] args) {
        GameMaster gm = new GameMaster();
        gm.getDisplayer().display("The "+gm.letsPlay()+" win !!!");
    }

}
