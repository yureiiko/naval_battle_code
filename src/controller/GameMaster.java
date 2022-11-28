package controller;

import modele.Game;

import java.util.Random;
import java.util.Scanner;

/**
 * Class GameMaster
 * @author FLEURY CALAIS Camille
 */
public class GameMaster {

    Game navalBattle;
    Scanner scan;

    /**
     * Constructor
     */
    public GameMaster() {
        navalBattle = new Game();
        scan = new Scanner(System.in);
    }

    /**
     * Method gamerPlay
     */
    public void gamerPlay(){
        System.out.print(navalBattle.getPlayerGrid(0)+"\n");
        System.out.print(navalBattle.getEnemyGrid(0)+"\n");
        ini:
            try {
                System.out.print("\nEnter the target column : ");
                int col = scan.nextInt();
                System.out.print("\nEnter the target line : ");
                int line = scan.nextInt();
                if (navalBattle.fire(0, col-1, line-1)) {
                    System.out.print("HIT :D !\n");
                } else {
                    System.out.print("miss :( \n");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.print("\nEnter a number between 1 and 15");
                break ini;
            }
    }

    /**
     * Method botPlay
     */
    public void botPlay() {
        System.out.print("The bot fire at : ");
        int line = new Random().nextInt(14);
        int col = new Random().nextInt(14);
        System.out.print(line+";"+col+"\n");
        if (navalBattle.fire(1, col-1, line-1)) {
            System.out.print("and HIT\n");
        } else {
            System.out.print("and miss\n");
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
        System.out.println("The "+gm.letsPlay()+" win !!!");
    }

}
