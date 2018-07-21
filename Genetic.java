import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

class Genetic{
    public static void main(String args[]){
        // To solve n-queen problem
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        int n = scanner.nextInt();

        // number of boards to try
        final int BOARD_COUNT = 50;

        // number of replaces
        final int REPLACE_COUNT = 2;

        int[][][] boards = new int[2][BOARD_COUNT][n];
        int[] fitness = new int[BOARD_COUNT];

        // fill up the table with random data
        for (int i = 0; i < BOARD_COUNT ; i++){
            for(int j = 0; j < n; j++){
                boards[0][i][j] = rand.nextInt(n) + 1;
            }
        }

        int temp = 0;
        int iteration = 0;

        int first = 0;
        int second = 0;

        boolean found = false;

        // do the mutations until solution foundConst
        while(!found){
            // get the fitness values
            for(int i = 0; i < BOARD_COUNT; i ++){
                temp = getFitness(boards[iteration][i], n);

                // check solution found
                if (temp == (n * (n-1))/2){
                    System.out.println(Arrays.toString(boards[iteration][i]));
                    found = true;
                    break;
                }

                fitness[i] = temp;
            }

            // calculate the selection probabilities based on fitnes
            for(int i = 1; i < BOARD_COUNT; i++){
                fitness[i] += fitness[i-1];
            }

            // do the mutations based on the probabilities
            
            for(int mutations = 0 ; mutations < BOARD_COUNT; mutations++){
                // first value
                temp = rand.nextInt(fitness[fitness.length - 1]);
                first = getLesserIndex(fitness, temp, 0, fitness.length - 1);

                // find the second
                temp = rand.nextInt(fitness[fitness.length - 1]);
                second = getLesserIndex(fitness, temp, 0, fitness.length - 1);

                // cut location
                temp = rand.nextInt(n);

                for(int i = 0; i < temp; i++){
                    boards[(iteration+1)%2][mutations][i] = boards[iteration][first][i];
                }

                for(int i = temp; i < n; i++){
                    boards[(iteration+1)%2][mutations][i] = boards[iteration][second][i];
                }

                // Do random replacing
                for(int i = 0; i < REPLACE_COUNT; i ++){
                    boards[(iteration+1)%2][mutations][rand.nextInt(n)] = rand.nextInt(n) + 1;
                }
            }

            iteration ++;
            iteration %= 2;
        } 
    }

    // Calculate the fitness
    public static int getFitness(int[] board, int n){
        // number of non attacking pairs
        int fitness = (n * (n - 1))/2;

        // check for each queen in column
        for(int i = 0; i < board.length ; i++){
            int pos = board[i];
            for(int j = 1 ; j < board.length - i; j++){
                // check horizontal
                if (board[i+j] == pos || board[i+j] == pos - j || board[i+j] == pos + j){
                    fitness--;
                    continue;
                }
            }
        }

        return fitness;
    }

    public static int getLesserIndex(int[] numbers, int value, int i, int j){
        if (i==j){
            return i;
        }

        if (j-i == 1){
            return (numbers[i]<value) ? i : j;
        }

        int loc = i + (j - i + 1) / 2;

        if (numbers[loc] == value)
            return loc;

        if (numbers[loc] < value){
            return getLesserIndex(numbers, value, loc, j);
        }else{
            return getLesserIndex(numbers, value, i, loc);
        }
        
    }
}