package modele;

import java.util.Random;

/**
 * @author Kularajasingam Kosakan
 */
public class PlayerGrid extends Grid{
    public PlayerGrid(){
        //this.mat;

    }
    public void alea() {
        int col = new Random(15).nextInt();
        int lig = new Random(15).nextInt();
        mat[lig][col]="|#";
    }
    public static void main(String[] args) {


    }
}
