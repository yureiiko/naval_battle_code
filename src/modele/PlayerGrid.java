package modele;

import java.util.Random;

/**
 * Class PlayerGrid
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
    public boolean check(){
        for (int i=0;i<15;i++){
            for(int j=0;j<15;j++){
                if(mat[i][j]!="| "){
                    return false;
                }
                }

            }
        return true;
    }
}
