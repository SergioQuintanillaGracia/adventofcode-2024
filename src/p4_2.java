import java.util.ArrayList;
import java.util.Scanner;

public class p4_2 {
    // NOTE: Remember to change hardcoded matrix size depending on input
    static final int xLen = 140;
    static final int yLen = 140;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char matrix[][] = new char[xLen][yLen];
        int currFillingPos = 0;

        // Get lines and fill the matrix
        while (sc.hasNext()) {
            char lineArr[] = sc.nextLine().toCharArray();
            matrix[currFillingPos++] = lineArr;
        }

        int wordCount = 0;

        // Scan for words in the matrix
        for (int i = 0; i < xLen; i++) {
            for (int j = 0; j < yLen; j++) {
                // If an XMAS word is found, add 1 to `wordCount`
                wordCount += scanX(matrix, i, j);
            }
        }

        System.out.println(wordCount);
    }

    public static int scanX(char matrix[][], int x, int y) {
        // Check if the central character is 'A'
        if (matrix[y][x] != 'A') return 0;

        // Solve the problem in the worst possible way, by manually checking every possible case
        // If you want to protect your vision, do NOT read the code below

        int matches = 0;

        // TOP LEFT
        if (x > 0 && y > 0 && matrix[y - 1][x - 1] == 'M') matches++;
        // TOP RIGHT
        if (y > 0 && x < xLen - 1 && matrix[y - 1][x + 1] == 'S') matches++;
        // BOTTOM RIGHT
        if (x < xLen - 1 && y < yLen - 1 && matrix[y + 1][x + 1] == 'S') matches++;
        // BOTTOM LEFT
        if (x > 0 && y < yLen - 1 && matrix[y + 1][x - 1] == 'M') matches++;

        if (matches == 4) return 1;
        matches = 0;

        // TOP LEFT
        if (x > 0 && y > 0 && matrix[y - 1][x - 1] == 'S') matches++;
        // TOP RIGHT
        if (y > 0 && x < xLen - 1 && matrix[y - 1][x + 1] == 'S') matches++;
        // BOTTOM RIGHT
        if (x < xLen - 1 && y < yLen - 1 && matrix[y + 1][x + 1] == 'M') matches++;
        // BOTTOM LEFT
        if (x > 0 && y < yLen - 1 && matrix[y + 1][x - 1] == 'M') matches++;

        if (matches == 4) return 1;
        matches = 0;

        // TOP LEFT
        if (x > 0 && y > 0 && matrix[y - 1][x - 1] == 'S') matches++;
        // TOP RIGHT
        if (y > 0 && x < xLen - 1 && matrix[y - 1][x + 1] == 'M') matches++;
        // BOTTOM RIGHT
        if (x < xLen - 1 && y < yLen - 1 && matrix[y + 1][x + 1] == 'M') matches++;
        // BOTTOM LEFT
        if (x > 0 && y < yLen - 1 && matrix[y + 1][x - 1] == 'S') matches++;

        if (matches == 4) return 1;
        matches = 0;

        // TOP LEFT
        if (x > 0 && y > 0 && matrix[y - 1][x - 1] == 'M') matches++;
        // TOP RIGHT
        if (y > 0 && x < xLen - 1 && matrix[y - 1][x + 1] == 'M') matches++;
        // BOTTOM RIGHT
        if (x < xLen - 1 && y < yLen - 1 && matrix[y + 1][x + 1] == 'S') matches++;
        // BOTTOM LEFT
        if (x > 0 && y < yLen - 1 && matrix[y + 1][x - 1] == 'S') matches++;

        if (matches == 4) return 1;

        return 0;
    }
}
