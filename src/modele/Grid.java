package modele;

public class Grid {
    String mat[][]= new String[14][14];//Matrice 8*8

    public void fill(){

        for (int i=0;i<14;i++){
            for(int j=0;j<14;j++){
                mat[i][j]="| ";
            }
        }
    }
    @Override
    public String toString() {
        String out ="\t|1 |2 |3 |4 |5 |6 |7 |8 |9 |10|11|12|13|14|\n";
        for (int i=0;i<14;i++) {
            out=out+"\n"+(i+1)+"\t";
            for (int j = 0; j < 14; j++) {
                out =out + mat[i][j]+" ";
            }

        }
        return out ;
    }
}

