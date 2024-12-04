import java.util.ArrayList;
import java.util.Scanner;

public class p4 {
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
                wordCount += scanXmas("XMAS", matrix, i, j);
            }
        }

        System.out.println(wordCount);
    }

    public static int scanXmas(String word, char matrix[][], int x, int y) {
        ArrayList<Integer[]> dirsToCheck = new ArrayList<>();

        if (matrix[y][x] != word.charAt(0)) return 0;

        // If execution gets here, the character at [x,y] is the first char of the word to search
        // Find the directions at which we find the second character of the word
        // TOP LEFT
        if (x > 0 && y > 0)
            dirsToCheck.add(new Integer[]{-1, -1});
        // TOP
        if (y > 0)
            dirsToCheck.add(new Integer[]{0, -1});
        // TOP RIGHT
        if (y > 0 && x < xLen - 1)
            dirsToCheck.add(new Integer[]{1, -1});
        // RIGHT
        if (x < xLen - 1)
            dirsToCheck.add(new Integer[]{1, 0});
        // BOTTOM RIGHT
        if (x < xLen - 1 && y < yLen - 1)
            dirsToCheck.add(new Integer[]{1, 1});
        // BOTTOM
        if (y < yLen - 1)
            dirsToCheck.add(new Integer[]{0, 1});
        // BOTTOM LEFT
        if (x > 0 && y < yLen - 1)
            dirsToCheck.add(new Integer[]{-1, 1});
        // LEFT
        if (x > 0)
            dirsToCheck.add(new Integer[]{-1, 0});

        int found = 0;

        // Check if the word is in every direction
        for (Integer[] dir : dirsToCheck) {
            boolean wordFound = true;
            int dirX = dir[0];
            int dirY = dir[1];
            int posX = x + dirX;
            int posY = y + dirY;

            for (char c : word.substring(1).toCharArray()) {
                // Check if every character of the word is in that direction in order, forming the entire word
                // Check if position is valid
                if (!accessible(matrix, posX, posY)) {
                    wordFound = false;
                    break;
                }

                // Check for the character
                if (matrix[posY][posX] != c) {
                    wordFound = false;
                    break;
                }

                // Increase position
                posX += dirX;
                posY += dirY;
            }

            if (wordFound) found++;
        }

        return found;
    }

    /* Checks if the position [x,y] is accessible in matrix */
    public static boolean accessible(char matrix[][], int x, int y) {
        if (x < 0 || x >= matrix[0].length) return false;
        if (y < 0 || y >= matrix.length) return false;
        return true;
    }
}
