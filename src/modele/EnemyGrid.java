package modele;

import java.io.Serializable;

/**
 * Class EnemyGrid
 * @author JOSEPH PACES Anoushka
 */
public class EnemyGrid extends Grid implements Serializable {

    /**
     * Constructor
     */
    public EnemyGrid(){
        this.fill();
    }

    /**
     * Method update : Put the String toAdd at given coordinates
     * @param l int
     * @param c int
     * @param toAdd String
     */
    public void update(int l, int c, String toAdd) {
        try {
            mat[l][c] = toAdd;
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }
    }
}
