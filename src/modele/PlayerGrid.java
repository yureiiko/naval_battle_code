package modele;

import java.util.Random;

/**
 * Class PlayerGrid
 * @author Kularajasingam Kosakan
 */
public class PlayerGrid extends Grid {

    /**
     * Constructor
     */
    public PlayerGrid() {
        super.fill();
        aleaBattleship();
        aleaCruiser();
        aleaDestroyer();
        aleaSubmarine();
    }

    /**
     * Method aleaBoat
     * @param length int
     * @param boatString String
     * Place on the grid a boat of size length. The boatis identified on the grid by the String boatString
     */
    public void aleaBoat(int length, String boatString) {
        String [][] save = copyField(this.mat); //voir java duplicate
        boolean outer = true;
        while (outer) {
            this.mat = copyField(save);
            Random ran = new Random();
            int iniCol = ran.nextInt(14);
            int iniLin = ran.nextInt(14);
            mat[iniLin][iniCol] = boatString;
            if (length > 1) {
                for (int i = 1; i < length; i++) {
                    int col = iniCol + i;
                    if (col > 14 || col < 0 || this.mat[iniLin][col]!="| ") {
                        System.out.println("Out of field");
                        outer = true;
                        break;
                    } else {
                        mat[iniLin][col] = boatString;
                        outer = false;
                    }
                }
            } else {
                outer = false;
            }
        }
    }

    /**
     * method aleaBattleship
     * Create a ship of 7 boxes by using the method aleaboat
     */
    public void aleaBattleship() {
        aleaBoat(7, "|B");
    }
    /**
     * method aleaCruiser
     * Create a ship of 5 boxes by using the method aleaboat
     */
    public void aleaCruiser() {
        aleaBoat(5, "|C");
    }
    /**
     * method aleaDestroyer
     * Create a ship of 3 boxes by using the method aleaboat
     */
    public void aleaDestroyer() {
        aleaBoat(3, "|D");
    }
    /**
     * method aleaSubmarine
     * Create a ship of 1 boxes by using the method aleaboat
     */
    public void aleaSubmarine() {
        aleaBoat(1, "|S");
    }

    /**
     * Method check
     * @return boolean
     * Check the grid and return true if there no more boat
     */
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
     * @return boolean
     * Return true if there's a boat at the input coordinates
     */
    public boolean fire(int l, int c) {
        if ((mat[l][c] == "|B") || (mat[l][c] != "|C") || (mat[l][c] != "|D") || (mat[l][c] != "|S")) {
            mat[l][c]="|X";
            return true;
        }
        mat[l][c]="|O";
        return false;
    }

    /**
     * Method copyField
     * @param parField String [][]
     * @return String [][]
     * Used in the method aleaBoat to save the grid in case of problem
     */
    private String [][] copyField(String [][] parField) {
        String [][] out = new String[parField.length][parField[0].length];
        for (int i=0 ; i<parField.length ; i++) {
            for (int j=0 ; j<parField[i].length ; j++) {
                out[i][j] = parField[i][j];
            }
        }
        return out;
    }
}
