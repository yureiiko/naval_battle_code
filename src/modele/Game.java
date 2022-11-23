package modele;

import java.util.Scanner;

/**
 * Class Game
 * @author FLEURY CALAIS Camille
 */
public class Game {

    PlayerGrid [] gridPlayer;
    EnemyGrid [] gridEnemy;

    public Game() {
        gridPlayer = new PlayerGrid[2];
        gridEnemy = new EnemyGrid[2];
    }

    public String [] fire() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the target column : ");
        String targCol = scanner.next();
        System.out.println("Enter the target line : ");
        String targLine = scanner.next();

        return new String[]{targCol, targLine};
    }
}
