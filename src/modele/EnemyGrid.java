package modele;

/**
 * Class EnemyGrid
 */
public class EnemyGrid extends Grid{

    /**
     * Constructor
     * fill matrix
     */
    public EnemyGrid(){
        this.fill();
    }

    /**
     * Method update
     * @param l int
     * @param c int
     * @param toAdd String
     * Update EnemyGrid
     */
    public void update(int l, int c, String toAdd) {
        mat[l][c] = toAdd;
    }
}
