package modele;

/**
 * Class EnemyGrid
 */
public class EnemyGrid extends Grid{

    /**
     * Constructor
     */
    public EnemyGrid(){
        this.fill();
    }

    /**
     * Method update
     * @param l int
     * @param c int
     * @param toAdd String
     * Put the String toAdd at given coordinates
     */
    public void update(int l, int c, String toAdd) {
        mat[l][c] = toAdd;
    }
}
