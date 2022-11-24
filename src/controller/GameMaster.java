package controller;

import modele.Game;

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
        ini:
            try {
                System.out.print("\nEnter the target column : ");
                int col = scan.nextInt();
                System.out.print("\nEnter the target line : ");
                int line = scan.nextInt();
                navalBattle.fire(2, col-1, line-1);
            } catch (IndexOutOfBoundsException e) {
                System.out.print("\nEnter a number between 1 and 15");
                break ini;
            }
    }

    public void botPlay() {

    }

}
