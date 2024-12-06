import java.util.Scanner;

public class p6_2 {
    static int X_SIZE = 130;
    static int Y_SIZE = 130;

    public static void main(String[] args) {
        char[][] map = new char[Y_SIZE][X_SIZE];
        boolean[][] visited = new boolean[Y_SIZE][X_SIZE];
        Scanner sc = new Scanner(System.in);

        // Fill the map with the input
        int mapi = 0;
        while (sc.hasNext()) {
            String line = sc.nextLine();
            char[] charLine = line.toCharArray();
            map[mapi++] = charLine;
        }

        int validObstaclePos = 0;
        int checkedPos = 0;  // Variable used to track execution progress

        // Put an obstacle at every position in the map and check if the number of steps
        // given by the guard is very large
        // If so, we assume the guard is in a loop (even though it may not be in one)
        for (int i = 0; i < Y_SIZE; i++) {
            for (int j = 0; j < X_SIZE; j++) {
                // Track program progress
                checkedPos++;
                System.out.printf("Checking %d / %d\n", checkedPos, X_SIZE * Y_SIZE);

                // Try adding an obstacle only if the current position is empty (no obstacle and no guard)
                if (map[j][i] != '.') continue;

                // Copy the map to a new variable
                char[][] newMap = new char[Y_SIZE][X_SIZE];
                for (int k = 0; k < Y_SIZE; k++) {
                    for (int l = 0; l < X_SIZE; l++) {
                        newMap[k][l] = map[k][l];
                    }
                }

                // Replace the character by an obstacle
                newMap[j][i] = '#';

                // Set initial position and rotation of the guard
                int[] guardPos = findGuardPos(newMap);
                int[] guardDir = guardFacingDir(newMap, guardPos);

                long maxIterations = 100000;
                long iterations = 0;

                // Run the loop for maxIterations iterations, or until the guard leaves the place
                while (iterations <= maxIterations) {
                    // Walk until an obstacle is found
                    int[] afterWalking = findObstacleInDir(newMap, visited, guardDir, guardPos);

                    // If the guard left the map, break the loop
                    if (afterWalking[0] == -1 || afterWalking[1] == -1) break;

                    // Set the current guard position to the one reached after walking
                    guardPos = new int[]{afterWalking[0], afterWalking[1]};
                    // Rotate the guard
                    guardDir = rotateDir(guardDir);

                    iterations++;
                }

                // If the loop ended due to a high number of iterations, assume that putting an obstacle
                // at that position causes the guard to enter a loop
                if (iterations > maxIterations) validObstaclePos++;
            }
        }

        System.out.println(validObstaclePos);
    }

    static int[] findGuardPos(char[][] map) {
        for (int i = 0; i < Y_SIZE; i++) {
            for (int j = 0; j < X_SIZE; j++) {
                if (map[i][j] != '#' && map[i][j] != '.') {
                    return new int[]{j, i};
                }
            }
        }

        return new int[]{};
    }

    /* Returns an array in the form [posX, posY], where the position is the
    place where the guard ends, not the position of the obstacle */
    static int[] findObstacleInDir(char[][] map, boolean[][] visited, int[] dir, int[] startingPos) {
        int x = startingPos[0];
        int y = startingPos[1];
        int futureX = x + dir[0];
        int futureY = y + dir[1];

        while (accessible(map, futureX, futureY)) {
            if (map[futureY][futureX] == '#')
                return new int[]{x, y};

            // Mark the position as visited
            visited[y][x] = true;

            // Move to the new position and calculate the next one
            x = futureX;
            y = futureY;
            futureX += dir[0];
            futureY += dir[1];
        }

        // Mark the position as visited
        visited[y][x] = true;

        return new int[]{-1, -1};
    }

    /* Rotates 90 degrees the passed direction */
    static int[] rotateDir(int[] dir) {
        if (dir[0] == 0 && dir[1] == -1) {
            dir[0] = 1;
            dir[1] = 0;
        } else if (dir[0] == 1 && dir[1] == 0) {
            dir[0] = 0;
            dir[1] = 1;
        } else if (dir[0] == 0 && dir[1] == 1) {
            dir[0] = -1;
            dir[1] = 0;
        } else if (dir[0] == -1 && dir[1] == 0) {
            dir[0] = 0;
            dir[1] = -1;
        }

        return dir;
    }

    /* Returns the guard facing direction based on the character that represents it in the map */
    static int[] guardFacingDir(char[][] map, int[] pos) {
        return switch (map[pos[1]][pos[0]]) {
            case '^' -> new int[]{0, -1};
            case '>' -> new int[]{1, 0};
            case 'v' -> new int[]{0, 1};
            case '<' -> new int[]{-1, 0};
            default -> new int[]{};
        };
    }

    /* Checks if the position [x,y] is accessible in matrix */
    public static boolean accessible(char[][] matrix, int x, int y) {
        if (x < 0 || x >= matrix[0].length) return false;
        if (y < 0 || y >= matrix.length) return false;
        return true;
    }

    /* Print a boolean matrix, used for debugging */
    public static void printMatrix(boolean[][] matrix) {
        System.out.printf("[");
        for (boolean[] line : matrix) {
            System.out.printf("[");
            for (boolean b : line) {
                System.out.printf("%d", b ? 1 : 0);
            }
            System.out.printf("]\n");
        }
        System.out.printf("]");
    }
}
