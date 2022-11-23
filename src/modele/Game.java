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

    public boolean start(){
        System.out.println("--------------------Game : BattleShip--------------------\n");
        System.out.println("You will have 2 grid, one thats is yours the other is the rival's grid\n");
        System.out.println("You will have 4 ships\n1)Cuirassé:C:7 boxes\t2)Croiseur:c:5 boxes\t3)Destroyeur:d:3boxes\t4)Sous-marin:s:1 boxe\n");
        System.out.println("Rules\na)Indicate coordinates of the rival's boxe you want to shot\nb)If you hit a ship it will show 'hit' otherwise 'miss'\n");
        System.out.println("In you other grid you will see where the rival hit\n");
        System.out.println("The goal of the game is to hit avery shipe of the rival before he hit yours\n");
        System.out.println("If you want to play enter 'y' otherwise enter 'n'\n" );

        switch (){
            case 'y': return true;
            case 'n' : return false;
            default  : return false;
        }

    }
}
