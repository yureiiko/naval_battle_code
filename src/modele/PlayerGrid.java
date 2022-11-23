package modele;

import java.util.Random;

/**
 * @author Kularajasingam Kosakan
 */
public class PlayerGrid extends Grid{

    public PlayerGrid(){
        super.fill();
        alea();
    }
    public void alea() {
        int col = new Random().nextInt(14);
        int lig = new Random().nextInt(14);
        mat[lig][col]="|#";
    }
}
