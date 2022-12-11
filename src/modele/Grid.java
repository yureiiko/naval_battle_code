package modele;

/**
 * Class Grid
 * @author Mouhamad Zainab
 */
public class Grid {
    String mat[][]= new String[15][15];//Matrice 8*8

    /**
     * Method fill : method that fill the grid
     */
    public void fill(){

        for (int i=0;i<15;i++){
            for(int j=0;j<15;j++){
                mat[i][j]="| ";
            }
        }
    }

    /**
     * Method toString : Method that permit to display the grid
     */
    @Override
    public String toString() {
        String out ="\t|1 |2 |3 |4 |5 |6 |7 |8 |9 |10|11|12|13|14|15|\n";
        for (int i=0;i<15;i++) {
            out=out+"\n"+(i+1)+"\t";
            for (int j = 0; j < 15; j++) {
                out =out + mat[i][j]+" ";
            }
            out=out +"|";
        }
        return out ;
    }
}

