public class Main {
    public static void main(String[] args) {
        // Création de la matrice
        int[][] matrice = new int[14][14];
        for(int i = 0; i < matrice.length; i++){
            matrice[i] = new int[14];
        }
        // Affichage de la matrice
        for(int i = 0; i < matrice.length; i++){
            for(int j = 0; j < matrice[i].length; j++){
                int alea = (int) (Math.random() * 2);
                matrice[i][j] = alea;
                System.out.print(matrice[i][j] + "   ");

            }
            System.out.println();
        }
    }
}