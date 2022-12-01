package modele;

import java.util.Random;

/**
 * Class PlayerGrid
 * @author Kularajasingam Kosakan
 */
public class PlayerGrid extends Grid {


    public PlayerGrid() {
        super.fill();
        aleaBattleship();
        aleaCruiser();
        aleaDestroyer();
        aleaSubmarine();
    }

    /**
     * Method alea
     * 1st random boat
     */
    public void alea() {
        int col = new Random().nextInt(14);
        int lig = new Random().nextInt(14);
        mat[lig][col] = "|#";
    }

    public void aleaBoat(int length, String boatString) {
        String [][] save = this.mat; //voir java duplicate
        boolean outer = true;
        while (outer) {
            this.mat = save;
            Random ran = new Random();
            int iniCol = ran.nextInt(14);
            int iniLin = ran.nextInt(14);
            mat[iniLin][iniCol] = boatString;
            for (int i = 1; i < length; i++) {
                int col = iniCol + i;
                if (col > 14 || col < 0 || this.mat[iniLin][col]!="| ") {
                    break;
                } else {
                    mat[iniLin][col] = boatString;
                }
            }
            outer=false;
        }
    }

    public void aleaBattleship() {
        aleaBoat(7, "|B");
    }

    /**
     * method aleaCruiser
     * create a ship of 5 boxes randomly in the grid that use the method aleaboat
     */
    public void aleaCruiser() {
        aleaBoat(5, "|C");
    }
    /**
     * method aleaDestroyer
     * create a ship of 3 boxes randomly in the grid that use the method aleaboat
     */

    public void aleaDestroyer() {
        aleaBoat(3, "|D");
    }
    /**
     * method aleaSubmarine
     * create a ship of 1 box randomly in the grid that use the method aleaboat
     */
    public void aleaSubmarine() {
        aleaBoat(1, "|S");
    }

    public boolean check() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (mat[i][j] != "| " && mat[i][j] != "|X") {
                    return false;
                }
            }

        }
        return true;
    }



    /**
     * Method fire
     * @param c int
     * @param l int
     * @return
     */
    public boolean fire(int l, int c) {
        if ((mat[l][c] != "| ") && (mat[l][c] != "|X") && (mat[l][c] != "|0")) {
            mat[l][c]="|X";
            return true;
        }
        mat[l][c]="|O";
        return false;
    }
}
