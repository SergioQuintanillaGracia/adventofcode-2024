import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class p8 {
    static int X_SIZE = 50;
    static int Y_SIZE = 50;

    public static void main(String[] args) {
        // HashMap in the form {'0':{[2, 8], [3, 5], [0, 3], ...}, ...}
        // Where '0' is a character, and the array is a list of coordinates for antennas with
        // character '0'
        HashMap<Character, ArrayList<Integer[]>> antennas = new HashMap<>();
        boolean[][] antinodes = new boolean[X_SIZE][Y_SIZE];
        Scanner sc = new Scanner(System.in);

        // Read the input and store antenna positions
        int y = 0;
        while (sc.hasNext()) {
            String line = sc.nextLine();
            char[] charLine = line.toCharArray();

            // Check for antennas (characters different to `.`)
            for (int x = 0; x < charLine.length; x++) {
                char c = charLine[x];
                if (c != '.') {
                    Integer[] pos = new Integer[]{x, y};

                    if (antennas.containsKey(c)) {
                        // If the character is in the map, add the newly found antenna position
                        antennas.get(c).add(pos);

                    } else {
                        // If the character is not in the map, add it
                        ArrayList<Integer[]> positions = new ArrayList<>();
                        positions.add(pos);
                        antennas.put(c, positions);
                    }
                }
            }

            y++;
        }

        int count = 0;

        // Find antinodes
        for (char c : antennas.keySet()) {
            ArrayList<Integer[]> positions = antennas.get(c);

            // For every character, iterate through every antenna position with that character
            for (Integer[] p : positions) {
                for (Integer[] p2 : positions) {
                    if (p == p2) continue;  // If the antenna is the same, ignore it

                    int[] antinodePos = new int[]{2 * p2[0] - p[0], 2 * p2[1] - p[1]};

                    if (isValidPos(antinodePos)) {
                        // Add the antinode only if there is no antinode in that position
                        if (!antinodes[antinodePos[0]][antinodePos[1]]) {
                            antinodes[antinodePos[0]][antinodePos[1]] = true;
                            count++;
                        }
                    }
                }
            }
        }

        System.out.println(count);
    }

    public static boolean isValidPos(int[] pos) {
        if (pos[0] < 0 || pos[0] >= X_SIZE) return false;
        if (pos[1] < 0 || pos[1] >= Y_SIZE) return false;
        return true;
    }
}
