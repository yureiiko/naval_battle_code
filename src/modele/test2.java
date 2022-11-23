import java.util.Random;
import java.util.Arrays;
public class ShuffleExample
{

    static void rand( int array[], int a)
    {
        // Creating object for Random class
        Random rd = new Random();

        // Starting from the last element and swapping one by one.
        for (int i = a-1; i > 0; i--) {

            // Pick a random index from 0 to i
            int j = rd.nextInt(i+1);

            // Swap array[i] with the element at random index
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        // Printing the random generated array
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args)
    {

        int[] ar = {1, 2, 3, 4, 5, 6, 7, 8};
        int b = ar.length;
        rand (ar, b);
    }
}