package modele;

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

    public void fire(String targCol, String targLine) {

    }
}
