package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class PlayerGrid
 * @author Kularajasingam Kosakan
 */
public class PlayerGrid extends Grid implements Serializable {

    /**
     * Constructor
     */
    public PlayerGrid() {
        super.fill();
        aleaBattleship();
        System.out.println("BattleShip");
        aleaCruiser();
        System.out.println("Cruiser");
        aleaDestroyer();
        System.out.println("Destroyer");
        aleaSubmarine();
        System.out.println("Submarin");
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
            //mat[iniLin][iniCol] = boatString;
            int [] coor = {iniLin, iniCol};
            int ranCoor = ran.nextInt(2);
            if (length > 0) {
                for (int i = 0; i < length; i++) {
                    coor[ranCoor] = coor[ranCoor]+1;
                    if (coor[0] > 14 || coor[0] < 0 || coor[1] > 14 || coor[1] < 0 || this.mat[coor[0]][coor[1]]!="| ") {
                        outer = true;
                        break;
                    } else {
                        mat[coor[0]][coor[1]] = boatString;
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
     * Create a ship of 7 boxes by using the method aleaBoat
     */
    public void aleaBattleship() {
        aleaBoat(7, "\u001B[36m|B\u001b[m");
    }
    /**
     * method aleaCruiser
     * Create a ship of 5 boxes by using the method aleaboat
     */
    public void aleaCruiser() {
        for (int i=0;i<2;i++){

        aleaBoat(5, "\u001B[34m|C\u001b[m");
    }}
    /**
     * method aleaDestroyer
     * Create a ship of 3 boxes by using the method aleaboat
     */
    public void aleaDestroyer() {
        for (int i=0;i<3;i++){
        aleaBoat(3, "\u001B[35m|D\u001b[m");
    }}
    /**
     * method aleaSubmarine
     * Create a ship of 1 boxes by using the method aleaboat
     */
    public void aleaSubmarine() {
        for (int i=0;i<4;i++){
        aleaBoat(1, "\u001B[33m|S\u001b[m");
    }}

    /**
     * Method check
     * @return boolean
     * Check the grid and return true if there is no more boat
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
     * Method validBoat : return all boat still in the game
     * @return ArrayList
     */
    public ArrayList<String> validBoat() {
        ArrayList<String> out = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                switch (mat[i][j]) {
                    case "\u001B[36m|B\u001b[m":
                        if (!out.contains("B"))
                            out.add("B");
                    case "\u001B[34m|C\u001b[m":
                        if (!out.contains("C"))
                            out.add("C");
                    case "\u001B[35m|D\u001b[m":
                        if (!out.contains("D"))
                            out.add("D");
                    case "\u001B[33m|S\u001b[m":
                        if (!out.contains("S"))
                            out.add("S");
                    default: continue;
                }
            }
        }
        return out;
    }

    /**
     * Method fire
     * @param c int
     * @param l int
     * @return boolean
     * Return true if there's a boat at the input coordinates
     */
    public boolean fire(int l, int c) {
        try {
            if ((mat[l][c] == "\u001B[36m|B\u001b[m") || (mat[l][c] == "\u001B[34m|C\u001b[m") || (mat[l][c] == "\u001B[35m|D\u001b[m") || (mat[l][c] == "\u001B[33m|S\u001b[m")) {
                mat[l][c]="|X";
                return true;
            }
            if (mat[l][c] == "|X") {
                return false;
            }
            mat[l][c]="|O";
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
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
