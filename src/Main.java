import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // create a random object to fill the 2D array
        Random rand = new Random();
        // create the 2D array to simulate the return results
        int[][] rawData = new int[24][240];

        for(int i = 0; i < rawData.length-1; i++){
            for(int j = 0; j < rawData[1].length-1; j++){
                rawData[i][j] = rand.nextInt(100);
                // System.out.print(rawData[i][j] + " | ");
            }
            System.out.println();
        }

        // we want to have an element/minute
        Queue<Integer> resultArray = new LinkedList<>();

        // find the delta diff of overlapping elements
        // first get the quarter of the length of one row of the data
        int quarter = rawData[1].length/4;
        // variable to measure the diff og the two arrays
        int[][] delta = new int[24][quarter];

        for(int i = quarter*3; i<rawData[1].length; i++){
            for(int j = 0; j < quarter; j++) {
                for (int rows = 0; rows < rawData.length - 1; rows++) {
                    System.out.println("Raw Data of the end of the first row: " + rawData[rows][i] + " ");
                    System.out.println("Raw Data of the beginning of second row: " + rawData[rows + 1][j] + " ");
                    System.out.print("Diff between the data: ");
                    System.out.println(rawData[rows][i] - rawData[rows+1][i] + " ");
                    // since we have j starting from 0 lets use it
                    delta[rows][j] = rawData[0][i] - rawData[1][i];
                    System.out.println("-----------------------------");
                }
            }
        }

        int[] toAddDelta = new int[24];

        // remove the oustanding values
        // if we have duplicates we are safe
        for (int row = 0; row < delta.length; row++) {
            for (int col = 0; col < delta[row].length; col++) {
                int num = delta[row][col];
                for (int otherCol = col + 1; otherCol < delta.length; otherCol++) {
                    if (num == delta[row][otherCol]) {
                        toAddDelta[row] = num;
                        break;
                    }
                }
            }
        }

        // only for the first row we have to make an exception
        for(int i = 0; i < quarter; i++){
            resultArray.add(rawData[0][i]);
        }

        // fill the result array
        for(int i = 1; i < rawData.length-1; i++) {
            for (int j = quarter; j < rawData[1].length - 1; j++) {
                resultArray.add(rawData[i][j] + toAddDelta[i-1]);
            }
        }




    }
}