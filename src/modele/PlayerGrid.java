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
    }

    /**
     * Method alea
     * 1st boat randomly placed
     */
    public void alea() {
        int col = new Random().nextInt(14);
        int lig = new Random().nextInt(14);
        mat[lig][col] = "|#";
    }

    public void aleaBattleship() {
        String [][] save = this.mat; //voir java duplicate
        boolean outer = true;
        while (outer) {
            this.mat = save;
            Random ran = new Random();
            int iniCol = ran.nextInt(14);
            int iniLin = ran.nextInt(14);
            mat[iniLin][iniCol] = "|B";
            for (int i = 1; i < 7; i++) {
                int col = iniCol + i;
                if (col > 14 && col < 0 && this.mat[iniLin][col]=="| ") {
                    break;
                } else {
                    mat[iniLin][col] = "|B";
                }
            }
            outer=false;
        }
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
     * Method alea2
     * 2nd boat randomly placed
     */
    public void alea2() {
        int col = new Random().nextInt(14);
        int lig = new Random().nextInt(14);
        mat[lig][col] = "|#";
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
