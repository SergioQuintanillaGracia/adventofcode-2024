import java.util.Scanner;

public class p6 {
    static int X_SIZE = 130;
    static int Y_SIZE = 130;

    public static void main(String[] args) {
        char[][] map = new char[X_SIZE][Y_SIZE];
        boolean[][] visited = new boolean[X_SIZE][Y_SIZE];
        Scanner sc = new Scanner(System.in);

        // Fill the map with the input
        int mapi = 0;
        while (sc.hasNext()) {
            String line = sc.nextLine();
            char[] charLine = line.toCharArray();
            map[mapi++] = charLine;
        }

        // Set initial position and rotation of the guard
        int[] guardPos = findGuardPos(map);
        int[] guardDir = guardFacingDir(map, guardPos);

        while (true) {
            // Walk until an obstacle is found
            int[] afterWalking = findObstacleInDir(map, visited, guardDir, guardPos);

            // If the guard left the map, break the loop
            if (afterWalking[0] == -1 || afterWalking[1] == -1) break;

            // Set the current guard position to the one reached after walking
            guardPos = new int[]{afterWalking[0], afterWalking[1]};
            // Rotate the guard
            guardDir = rotateDir(guardDir);
        }

        // The amount of different positions visited by the guard is the amount of true values
        // inside `visited`
        System.out.println(countTrue(visited));
    }

    /* Counts the amount of true values inside a boolean matrix */
    static long countTrue(boolean[][] matrix) {
        long count = 0;

        for (boolean[] line : matrix) {
            for (boolean b : line) {
                if (b) count++;
            }
        }

        return count;
    }

    /* Returns the position of the guard in the map */
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
