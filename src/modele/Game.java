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
        System.out.println("\nFire");
        if (gridPlayer[gridInd].fire(targLine,targCol)) {
            gridEnemy[gridInd].update(targLine,targCol,"|X");//call the method update and add X if ship hit
            return true;
        }
        gridEnemy[gridInd].update(targLine,targCol,"|O");//update with 0 if no ship hit
        return false;
    }

    public boolean firebattelship(int gridInd, int targCol, int targLine) {//gridInd permet d'appeler l'indice de l'element du tableau
        boolean out = this.fire(gridInd, targCol, targLine);
        for (int i=1 ; i<5 ; i++) {
            out = out||this.fire(gridInd, targCol-i, targLine);
            out = out||this.fire(gridInd, targCol+i, targLine);
        }
        return out;
    }

    public boolean firecruiser(int gridInd, int targCol, int targLine) {//gridInd permet d'appeler l'indice de l'element du tableau
        boolean out = this.fire(gridInd, targCol, targLine);
        for (int i=1 ; i<3 ; i++) {
            out = out||this.fire(gridInd, targCol-i, targLine);
            out = out||this.fire(gridInd, targCol+i, targLine);
        }
        return out;
    }

    /**
     * Method save
     * @param filename String
     * @throws IOException
     * Save the object in the file with the given name
     */
    public void save(String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this); oos.flush(); oos.close();
    }

    /**
     * Method load
     * @param filename String
     * @return Game
     * @throws IOException
     * @throws ClassNotFoundException
     * Load the object saved in the file with the given name
     */
    public Game load(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Game tmpGame = (Game) ois.readObject();
        ois.close();
        return tmpGame;
    }
}
