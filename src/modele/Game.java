package modele;

import java.io.*;
import java.util.Scanner;

/**
 * Class Game
 * @author FLEURY CALAIS Camille
 */
public class Game implements Serializable {

    PlayerGrid [] gridPlayer;
    EnemyGrid [] gridEnemy;

    /**
     * Constructor
     */
    public Game() {
        gridPlayer = new PlayerGrid[2];
        gridEnemy = new EnemyGrid[2];
        gridPlayer[0] = new PlayerGrid();
        gridPlayer[1] = new PlayerGrid();
        gridEnemy[0] = new EnemyGrid();
        gridEnemy[1] = new EnemyGrid();
    }

    /**
     * Method getPlayerGrid
     * @param ind int
     * @return PlayerGrid
     */
    public PlayerGrid getPlayerGrid(int ind) {
        return gridPlayer[ind];
    }

    /**
     * Method getEnemyGrid
     * @param ind int
     * @return EnemyGrid
     */
    public EnemyGrid getEnemyGrid(int ind) {
        return gridEnemy[ind];
    }

    /**
     * Method check
     * @return String
     */
    public String check() {
        if (gridPlayer[0].check()) {
            return "bot";
        }
        if (gridPlayer[1].check()) {
            return "player";
        }
        return null;
    }

    /**
     * Method fire
     * @param gridInd int
     * @param targCol int
     * @param targLine int
     * @return boolean
     */
    public boolean fire(int gridInd, int targCol, int targLine) {//gridInd permet d'appeler l'indice de l'element du tableau
        //System.out.println("\nFire");
        boolean hit = gridPlayer[gridInd].fire(targLine,targCol);
        //System.out.println("\n "+hit);
        if (hit) {
            gridEnemy[gridInd].update(targLine,targCol,"|X");//call the method update and add X if ship hit
            return true;
        } else {
            gridEnemy[gridInd].update(targLine,targCol,"|O");//update with 0 if no ship hit
            return false;
        }
    }

    /**
     * Method firebattleshipe : Use the method fire to shoot in a square of 3 box length
     * @param gridInd int
     * @param targCol int
     * @param targLine int
     * @return boolean
     */
    public boolean firebattelship(int gridInd, int targCol, int targLine) {//gridInd permet d'appeler l'indice de l'element du tableau
        boolean out1 = this.fire(gridInd, targCol, targLine); //System.out.println("\nFire battleship");
        boolean out2 = this.fire(gridInd, targCol-1, targLine); //System.out.println("\nFire battleship");
        boolean out3 = this.fire(gridInd, targCol+1, targLine); //System.out.println("\nFire battleship");
        boolean out4 = this.fire(gridInd, targCol, targLine-1);
        boolean out5 = this.fire(gridInd, targCol-1, targLine-1);
        boolean out6 = this.fire(gridInd, targCol+1, targLine-1);
        boolean out7 = this.fire(gridInd, targCol, targLine+1);
        boolean out8 = this.fire(gridInd, targCol-1, targLine+1);
        boolean out9 = this.fire(gridInd, targCol+1, targLine+1);
        return out1||out2||out3||out4||out5||out6||out7||out8||out9;
    }

    /**
     * Method firecruiser : Use the method fire to shoot in a cross of length 3
     * @param gridInd int
     * @param targCol int
     * @param targLine int
     * @return boolean
     */
    public boolean firecruiser(int gridInd, int targCol, int targLine) {//gridInd permet d'appeler l'indice de l'element du tableau
        boolean out1 = this.fire(gridInd, targCol, targLine);
        boolean out2 = this.fire(gridInd, targCol, targLine-1);
        boolean out3 = this.fire(gridInd, targCol-1, targLine);
        boolean out4 = this.fire(gridInd, targCol+1, targLine);
        boolean out5 = this.fire(gridInd, targCol, targLine+1);
        return out1||out2||out3||out4||out5;
    }

    /**
     * Method save : Save the object in the file with the given name
     * @param filename String
     * @throws IOException
     */
    public void save(String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this); oos.flush(); oos.close();
    }

    /**
     * Method load : Load the object saved in the file with the given name
     * @param filename String
     * @return Game
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Game load(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Game tmpGame = (Game) ois.readObject();
        ois.close();
        return tmpGame;
    }
}
